package com.suntak.cloud.microservices.service.impl;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.suntak.autotask.BPMService.BPMServiceStub;
import com.suntak.autotask.bean.OaFormXmlBean;
import com.suntak.autotask.utils.OaConfigInfo;
import com.suntak.autotask.utils.OaInterfaceUtil;
import com.suntak.autotask.utils.XmlUtil;
import com.suntak.cloud.microservices.client.OaClient;
import com.suntak.cloud.microservices.entity.Signcard;
import com.suntak.cloud.microservices.mapper.SigncardMapper;
import com.suntak.cloud.microservices.service.SigncardService;
import com.suntak.cloud.oa.entity.Oa_user_info;

@Service
public class SigncardServiceImpl implements SigncardService {

	@Value("${cloud.environment.oa}")
    private String environment;
	
	@Autowired
	private SigncardMapper signcardMapper;
    
    @Autowired
    private OaClient oaClient;
	
    @Transactional(rollbackFor = Exception.class)
	@Override
	public Boolean insert(Signcard signcard) throws Exception {
		signcard.setCreatestamp(new Timestamp(System.currentTimeMillis()));
		signcard.setYearmonth(new SimpleDateFormat("yyyy-MM").format(signcard.getSignTime()));
		Boolean flag = signcardMapper.insert(signcard) > 0;
		if (flag) {
			lunchOA(signcard);
		}
		return flag;
	}

	@Override
	public Boolean updateStatus(Signcard signcard) {
		return signcardMapper.updateStatus(signcard) > 0;
	}

	private Long lunchOA(Signcard signcard) throws Exception {
        OaFormXmlBean oaForm = new OaFormXmlBean();
        oaForm.setTableName("formmain_2129");
        oaForm.setTableHeaderDataMap(genTableHeaderDataMap(signcard));
        Oa_user_info oa_user_info = findLoginNameByCode(signcard.getEmpcode());
        oaForm.setTableLinesDataList(genTableLinesDataList(signcard, oa_user_info));

        String data = XmlUtil.getOaFormXmlString(oaForm);
        // 连接的环境信息
        try {
            OaConfigInfo conf = new OaConfigInfo(environment);

            String token = OaInterfaceUtil.getOaToken(conf);
            
            // 发起流程表单
            BPMServiceStub bpmServiceStub = new BPMServiceStub(conf);// new BPMServiceStub();
            BPMServiceStub.LaunchFormCollaboration launchFormCollaboration = new BPMServiceStub.LaunchFormCollaboration();
            launchFormCollaboration.setToken(token);
            launchFormCollaboration.setSenderLoginName(oa_user_info.getLogin_name()); // 发起者的登录名（登录协同的登录名）
            launchFormCollaboration.setData(data); // XML格式的表单数据
            launchFormCollaboration.setParam("0"); //0-发起流程 1-待发
            launchFormCollaboration.setRelateDoc("");
            launchFormCollaboration.setTemplateCode("NC_ASCB_01"); // 模板编号
            
            BPMServiceStub.LaunchFormCollaborationResponse launchFormCollaborationResp = bpmServiceStub
                    .launchFormCollaboration(launchFormCollaboration);
            BPMServiceStub.ServiceResponse serviceResp = launchFormCollaborationResp.get_return();
            Long errorNumber = serviceResp.getErrorNumber(); // 错误代码
            String errorMessage = serviceResp.getErrorMessage(); // 错误消息
            if (errorNumber != 0) {
            	throw new Exception("同步OA失败:"+errorMessage);
            }
            return serviceResp.getResult();
        } catch (AxisFault e) {
            throw e;
        } catch (RemoteException e) {
            throw e;
        } catch (com.suntak.autotask.authorityService.ServiceException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
	
	private Map<String, String> genTableHeaderDataMap(Signcard signcard) {
        Map<String, String> tableHeaderDataMap = new HashMap<String, String>();
    	tableHeaderDataMap.put("所在公司", signcard.getCompany()); // 所在公司
        tableHeaderDataMap.put("薪资类型", signcard.getSalaryType()); 
        tableHeaderDataMap.put("数据来源", "企业微信"); 
        return tableHeaderDataMap;
    }
    
    private List<List<Map<String,String>>> genTableLinesDataList(Signcard signcard, Oa_user_info oa_user_info) {
    	List<List<Map<String,String>>> list  = new ArrayList<List<Map<String, String>>>();
    	List<Map<String, String>> list0 = new ArrayList<>();
    	Map<String, String> map = new HashMap<>();
		map.put("签卡人姓名", oa_user_info.getId());
		map.put("签卡人工号", signcard.getEmpcode());
		map.put("签卡人职务", oa_user_info.getOrg_post_id());
		map.put("签卡人签卡事由", signcard.getCardType());
		map.put("签卡人补卡时间", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(signcard.getSignTime()));
		map.put("重复表部门ID", oa_user_info.getDeptcode());
		map.put("重复表所属部门", oa_user_info.getOrg_department_id());
		list0.add(map);
		list.add(list0);
    	return list;
    }
    
    private Oa_user_info findLoginNameByCode(String code) throws Exception {
        Object obj = oaClient.findByCode(code).getData();
        if (obj == null) {
            throw new Exception("该工号【"+code+"】在OA中找不到对应的登录名！");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(objectMapper.writeValueAsBytes(obj), Oa_user_info.class);
    }
}
