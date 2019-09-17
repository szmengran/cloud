package com.suntak.cloud.ems.service.impl;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis2.AxisFault;
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
import com.suntak.cloud.ems.client.OaClient;
import com.suntak.cloud.ems.entity.Cux_oa_org_info_v;
import com.suntak.cloud.ems.entity.Ems_dm_order_head;
import com.suntak.cloud.ems.entity.Ems_dm_order_line;
import com.suntak.cloud.ems.mapper.Cux_oa_org_info_vMapper;
import com.suntak.cloud.ems.mapper.EmsDmOrderHeadMapper;
import com.suntak.cloud.ems.mapper.EmsDmOrderLineMapper;
import com.suntak.cloud.ems.service.EmsDmOrderHeadService;

/** 
 * @Package com.suntak.cloud.ems.service.impl 
 * @Description: 配件领用头服务
 * @date Jul 23, 2019 10:05:59 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class EmsDmOrderHeadServiceImpl implements EmsDmOrderHeadService {

	@Value("${cloud.environment.oa}")
    private String environment;
	
    @Autowired
    private EmsDmOrderHeadMapper emsDmOrderHeadMapper;
    
    @Autowired
    private EmsDmOrderLineMapper emsDmOrderLineMapper;
    
    @Autowired
    private Cux_oa_org_info_vMapper cux_oa_org_info_vMapper;
    
    @Autowired
    private OaClient oaClient;
    
    @Override
    public List<Ems_dm_order_head> findOrders(Integer use_p_id) throws Exception {
        return emsDmOrderHeadMapper.findOrders(use_p_id);
    }

    @Transactional
    @Override
    public Long insert(String empcode, Ems_dm_order_head orderHead, Ems_dm_order_line[] lines) throws Exception {
        Long id = emsDmOrderHeadMapper.findSeq();
        orderHead.setId(id);
        orderHead.setDate_time(new Timestamp(System.currentTimeMillis()));
        orderHead.setEbs_state(-1);
        emsDmOrderHeadMapper.insert(orderHead);
        for (Ems_dm_order_line order_line: lines) {
            order_line.setHead_id(id);
            emsDmOrderLineMapper.insert(order_line);
        }
        lunchOA(empcode, orderHead, lines);
        return id;
    }

    private Map<String, String> genTableHeaderDataMap(Ems_dm_order_head orderHead) {
        Map<String, String> tableHeaderDataMap = new HashMap<String, String>();
        tableHeaderDataMap.put("数据来源", "企业微信"); 
        List<Cux_oa_org_info_v> infos = cux_oa_org_info_vMapper.findByOrg_id(orderHead.getOrganization_id());
        if (infos != null && infos.size() > 0) {
        	tableHeaderDataMap.put("工厂ID", infos.get(0).getOrg_code()); 
        }
        return tableHeaderDataMap;
    }
    
    private List<List<Map<String, String>>> genTableLinesDataList(Ems_dm_order_line[] lines) {
    	List<List<Map<String, String>>> list = new ArrayList<List<Map<String, String>>>();
    	List<Map<String, String>> list0 = new ArrayList<>();
    	for (Ems_dm_order_line line: lines) {
			Map<String, String> map = new HashMap<>();
			map.put("物料编码", line.getPart_no()+"");
			map.put("物料描述", line.getPart_name());
			map.put("申请数量", line.getQty()+"");
			map.put("单位", line.getUom_code()+"");
			map.put("仓库代码", line.getSubinventory_code());
			map.put("库存数量", line.getActual_qty()+"");
			map.put("消耗工序", line.getItem_type());
			map.put("设备名称", line.getEquipment_name());
			map.put("设备号", line.getEquipment_no());
			map.put("领用人", line.getEbs_user_name());
			map.put("备注", line.getItem_refer());
			list0.add(map);
		}
    	list.add(list0);
    	return list;
    }
    
    private String findLoginNameByCode(String code) throws Exception {
        Object obj = oaClient.findByCode(code).getData();
        if (obj == null) {
            throw new Exception("该工号【"+code+"】在OA中找不到对应的登录名！");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsBytes(obj));
        return jsonNode.get("login_name").asText();
    }
	
    private void lunchOA(String empcode, Ems_dm_order_head orderHead, Ems_dm_order_line[] lines) throws Exception {
        OaFormXmlBean oaForm = new OaFormXmlBean();
        oaForm.setTableName("formmain_6737");
        oaForm.setTableHeaderDataMap(genTableHeaderDataMap(orderHead));
        oaForm.setTableLinesDataList(genTableLinesDataList(lines));

        String data = XmlUtil.getOaFormXmlString(oaForm);
        // 连接的环境信息
        try {
            OaConfigInfo conf = new OaConfigInfo(environment);

            String token = OaInterfaceUtil.getOaToken(conf);
            
            // 发起流程表单
            BPMServiceStub bpmServiceStub = new BPMServiceStub(conf);// new BPMServiceStub();
            BPMServiceStub.LaunchFormCollaboration launchFormCollaboration = new BPMServiceStub.LaunchFormCollaboration();
            launchFormCollaboration.setToken(token);
            launchFormCollaboration.setSenderLoginName(findLoginNameByCode(empcode)); // 发起者的登录名（登录协同的登录名）
            launchFormCollaboration.setSubject("");
            launchFormCollaboration.setTemplateCode("MAT_01"); // 模板编号
            launchFormCollaboration.setData(data); // XML格式的表单数据
            launchFormCollaboration.setParam("1"); //0-发起流程 1-待发
            launchFormCollaboration.setAttachments(null);

            BPMServiceStub.LaunchFormCollaborationResponse launchFormCollaborationResp = bpmServiceStub
                    .launchFormCollaboration(launchFormCollaboration);
            BPMServiceStub.ServiceResponse serviceResp = launchFormCollaborationResp.get_return();
            Long errorNumber = serviceResp.getErrorNumber(); // 错误代码
            String errorMessage = serviceResp.getErrorMessage(); // 错误消息
            if (errorNumber != 0) {
            	throw new Exception("同步OA失败:"+errorMessage);
            }
        } catch (AxisFault e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        	throw new Exception("同步OA失败:"+e.getMessage());
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        	throw new Exception("同步OA失败:"+e.getMessage());
        } catch (com.suntak.autotask.authorityService.ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        	throw new Exception("同步OA失败:"+e.getMessage());
        }
    }
}
