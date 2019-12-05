package com.suntak.reserve.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suntak.autotask.BPMService.BPMServiceStub;
import com.suntak.autotask.bean.OaFormXmlBean;
import com.suntak.autotask.utils.OaConfigInfo;
import com.suntak.autotask.utils.OaInterfaceUtil;
import com.suntak.autotask.utils.XmlUtil;
import com.suntak.exception.model.Response;
import com.suntak.reserve.client.EhrClient;
import com.suntak.reserve.client.OaClient;
import com.suntak.reserve.entity.TReserveRecord;
import com.suntak.reserve.mapper.RecordMapper;
import com.suntak.reserve.service.RecordService;

@Service
public class RecordServiceImpl implements RecordService {

    private final static Logger logger = LoggerFactory.getLogger(RecordServiceImpl.class);
    private final static Map<String, String> valueMap;
    static
    {
    	valueMap = new HashMap<>();
    	valueMap.put("客户", "-8371153456677172738");
    	valueMap.put("供应商", "3374083860620397179");
    	valueMap.put("面试", "-4038728341160180610");
    	valueMap.put("其他", "7153142958563937952");
    	
    	valueMap.put("股份公司", "8163545834096379752");
    	valueMap.put("深圳崇达", "-8853867940050089820");
    	valueMap.put("江门一厂", "7822342995263704355");
    	valueMap.put("江门二厂", "-1951120998846214957");
    	valueMap.put("大连工厂", "2553468865221708210");
    	valueMap.put("珠海崇达", "-1655071850535237631");
    	valueMap.put("大连电子", "-8846783729634230161");
    	valueMap.put("南通崇达", "-218053470321359033");
    }

    
    @Value("${cloud.environment.oa}")
    private String environment;
    
	@Autowired
	private RecordMapper recordMapper;
	
	@Autowired
	private EhrClient ehrClient;
	
    @Autowired
    private OaClient oaClient;
	
	@Override
	public List<TReserveRecord> findRecordByOpenid(String openid) {
		return recordMapper.findRecordByOpenid(openid);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@Override
	public Boolean insert(TReserveRecord tReserveRecord) throws Exception {
		tReserveRecord.setCreatestamp(new Timestamp(System.currentTimeMillis()));
		Response response = ehrClient.findContactByPhone(tReserveRecord.getContact_phone());
		if (response.getStatus() != 200) {
			return false;
		}
		if (response.getData() == null) {
			throw new IllegalArgumentException("在人资系统中找不到该电话号码关连的员工信息！");
		}
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsBytes(response.getData()));
		String name = jsonNode.get("name").asText();
		String empCode = jsonNode.get("userid").asText();
		tReserveRecord.setContact(name);
		Boolean flag = recordMapper.insert(tReserveRecord) > 0;
		if (flag) {
			launchForm(tReserveRecord, empCode);
		}
		return flag;
	}

	private void launchForm(TReserveRecord tReserveRecord, String empCode) throws Exception {
       
		Object obj = oaClient.findByCode(empCode).getData();
        if (obj == null) {
            throw new Exception("该工号【"+empCode+"】在OA中找不到对应的登录名！");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsBytes(obj));
        String loginName = jsonNode.get("login_name").asText();
        String id = jsonNode.get("id").asText();
        
        OaFormXmlBean oaForm = new OaFormXmlBean();
        oaForm.setTableName("formmain_4730");
        oaForm.setTableHeaderDataMap(genTableHeaderDataMap(tReserveRecord, id));

        String data = XmlUtil.getOaFormXmlString(oaForm);
        logger.info(data);
        // 连接的环境信息
        OaConfigInfo conf = new OaConfigInfo(environment);

        String token = OaInterfaceUtil.getOaToken(conf);
        
        // 发起流程表单
        BPMServiceStub bpmServiceStub = new BPMServiceStub(conf);// new BPMServiceStub();
        BPMServiceStub.LaunchFormCollaboration launchFormCollaboration = new BPMServiceStub.LaunchFormCollaboration();
        launchFormCollaboration.setToken(token);
        launchFormCollaboration.setSenderLoginName(loginName); // 发起者的登录名（登录协同的登录名）
        launchFormCollaboration.setSubject(tReserveRecord.getCompany()+"("+tReserveRecord.getNum()+"人)到访"+tReserveRecord.getOrg_name()+"外部人员来访登记表"); // 协同的标题
        launchFormCollaboration.setTemplateCode("OA_EXTERNAL_VISIT"); // 模板编号
        launchFormCollaboration.setData(data); // XML格式的表单数据
        launchFormCollaboration.setParam("0"); //0-发起流程 1-待发

        BPMServiceStub.LaunchFormCollaborationResponse launchFormCollaborationResp = bpmServiceStub
                .launchFormCollaboration(launchFormCollaboration);
        BPMServiceStub.ServiceResponse serviceResp = launchFormCollaborationResp.get_return();
        Long errorNumber = serviceResp.getErrorNumber(); // 错误代码
        String errorMessage = serviceResp.getErrorMessage(); // 错误消息
        Long processId = serviceResp.getResult(); // 流程ID

        if (errorNumber != 0) {
        	logger.info("错误代码：{}，错误消息：{}， 流程ID：{}", errorNumber , errorMessage, processId);
        	throw new Exception("填写的数据不正确，无法通过");
        }
    }
	
	private Map<String, String> genTableHeaderDataMap(TReserveRecord tReserveRecord, String id) throws Exception {
        Map<String, String> tableHeaderDataMap = new HashMap<String, String>();
        tableHeaderDataMap.put("接待联系人", id);
        tableHeaderDataMap.put("来访单位", tReserveRecord.getCompany());
        tableHeaderDataMap.put("来访时间", new SimpleDateFormat("yyyy-MM-dd").format(tReserveRecord.getVisitor_date())+" "+tReserveRecord.getVisitor_times());
        tableHeaderDataMap.put("来访类型", valueMap.get(tReserveRecord.getType()));
        tableHeaderDataMap.put("到访公司", valueMap.get(tReserveRecord.getOrg_name()));
        tableHeaderDataMap.put("来访车牌号", tReserveRecord.getNumber_plate());
        tableHeaderDataMap.put("备注", tReserveRecord.getRemark());
        tableHeaderDataMap.put("随访人数", tReserveRecord.getNum() == null ? "0" : (tReserveRecord.getNum() +""));
        tableHeaderDataMap.put("来访人", tReserveRecord.getVisitor());
        tableHeaderDataMap.put("来访人电话", tReserveRecord.getPhone());
        tableHeaderDataMap.put("随访人姓名", tReserveRecord.getOther_visitor());
        tableHeaderDataMap.put("随身重要物品", tReserveRecord.getBelongings());
        return tableHeaderDataMap;
    }
	
}
