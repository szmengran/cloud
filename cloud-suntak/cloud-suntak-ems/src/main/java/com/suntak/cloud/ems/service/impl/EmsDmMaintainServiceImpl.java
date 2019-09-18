package com.suntak.cloud.ems.service.impl;

import java.rmi.RemoteException;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suntak.autotask.BPMService.BPMServiceStub;
import com.suntak.autotask.bean.OaFormXmlBean;
import com.suntak.autotask.utils.OaConfigInfo;
import com.suntak.autotask.utils.OaInterfaceUtil;
import com.suntak.autotask.utils.XmlUtil;
import com.suntak.cloud.ems.client.OaClient;
import com.suntak.cloud.ems.entity.Ems_dm_maintain;
import com.suntak.cloud.ems.entity.Ems_dm_maintain_content;
import com.suntak.cloud.ems.mapper.MaintainContentMapper;
import com.suntak.cloud.ems.mapper.MaintainMapper;
import com.suntak.cloud.ems.service.EmsDmMaintainService;

/** 
 * @Package com.suntak.cloud.ems.service.impl 
 * @Description: TODO
 * @date Aug 26, 2019 9:43:33 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class EmsDmMaintainServiceImpl implements EmsDmMaintainService {

	@Value("${cloud.environment.oa}")
    private String environment;
	
    @Autowired
    private MaintainMapper maintainMapper;
    
    @Autowired
    private MaintainContentMapper maintainContentMapper;
    
    @Autowired
    private OaClient oaClient;
    
    @Override
    public List<Ems_dm_maintain> findMaintain(Integer organization_id, String keyword, Integer userid, Integer id) throws Exception {
        return maintainMapper.findMaintain(organization_id, keyword, userid, id);
    }

    @Transactional
    @Override
    public void saveOrUpdate(String empcode, Ems_dm_maintain maintain, Ems_dm_maintain_content[] maintainContents) throws Exception {
        int count = maintainMapper.updateMaintainStatus(maintain);
        if (count != 1) {
        	throw new Exception("更新保养单主表数量不正确，count：【"+count+"】");
        }
        for (Ems_dm_maintain_content content: maintainContents) {
        	content.setMaintain_id(maintain.getId());
        	if (content.getId() != null) {
        		count = maintainContentMapper.update(content);
        		if (count != 1) {
                	throw new Exception("更新保养单从表数量不正确，count：【"+count+"】");
                }
        	} else {
        		maintainContentMapper.insert(content);
        	}
        }
        lunchOA(empcode, maintain, maintainContents);
    }
    
    private Map<String, String> genTableHeaderDataMap(Ems_dm_maintain maintain) {
        Map<String, String> tableHeaderDataMap = new HashMap<String, String>();
    	tableHeaderDataMap.put("公司logo", ""); // 公司logo
        tableHeaderDataMap.put("编号", ""); 
        tableHeaderDataMap.put("设备名称", maintain.getEquipment_name()); 
        tableHeaderDataMap.put("设备编号", maintain.getEquipment_no()); 
        tableHeaderDataMap.put("使用部门", maintain.getProcedure()); 
        tableHeaderDataMap.put("保养级别", maintain.getMaintain_level()); 
        tableHeaderDataMap.put("执行日期", new SimpleDateFormat("yyyy-mm-dd").format(maintain.getExecute_time())); 
        tableHeaderDataMap.put("保养人", maintain.getMaintain_person()); 
        tableHeaderDataMap.put("保养结果", maintain.getMaintain_result()); 
        tableHeaderDataMap.put("物料使用情况", maintain.getMlt_use_state()); 
        tableHeaderDataMap.put("工序验收人", ""); 
        tableHeaderDataMap.put("工艺确认", ""); 
        tableHeaderDataMap.put("品保确认", ""); 
        tableHeaderDataMap.put("设备责任人", ""); 
        tableHeaderDataMap.put("设备部审核", ""); 
        tableHeaderDataMap.put("主管工程师签字", ""); 
        tableHeaderDataMap.put("主管确认", "");
        tableHeaderDataMap.put("维保类别", maintain.getTypename());
        tableHeaderDataMap.put("工厂ID", maintain.getOrganization_id()+"");
        return tableHeaderDataMap;
    }
    
    private List<List<Map<String, String>>> genTableLinesDataList(Ems_dm_maintain maintain, Ems_dm_maintain_content[] contents) {
    	List<List<Map<String, String>>> list = new ArrayList<List<Map<String, String>>>();
    	List<Map<String, String>> list0 = new ArrayList<>();
    	if ("1".equals(maintain.getType())) {
    		for (Ems_dm_maintain_content content: contents) {
    			Map<String, String> map = new HashMap<>();
    			map.put("序号", content.getIndex_()+"");
    			map.put("保养内容", content.getContent());
    			map.put("处理方法-检查", content.getM_check());
    			map.put("处理方法-清洁", content.getM_clear());
    			map.put("处理方法-调整", content.getM_adjust());
    			map.put("处理方法-润滑", content.getM_lubric());
    			map.put("处理方法-修理", content.getM_repair());
    			map.put("处理方法-更换", content.getM_change());
    			list0.add(map);
    		}
    		
    	} else {
    		for (Ems_dm_maintain_content content: contents) {
    			Map<String, String> map = new HashMap<>();
    			map.put("公设类-项目", content.getIndex_()+"");
    			map.put("公设类-内容", content.getContent());
    			map.put("执行情况-检查", content.getP_check());
    			map.put("执行情况-清洁", content.getP_clear());
    			map.put("执行情况-更换", content.getP_change());
    			map.put("执行情况-加油", content.getP_gasup());
    			map.put("执行情况-校正", content.getP_correction());
    			map.put("执行情况-紧固", content.getP_fastening());
    			map.put("执行情况-疏通", content.getP_dredge());
    			map.put("执行情况-温度", content.getP_temperature());
    			list0.add(map);
    		}
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
    
    private void lunchOA(String empcode, Ems_dm_maintain maintain, Ems_dm_maintain_content[] maintainContents) throws Exception {
        OaFormXmlBean oaForm = new OaFormXmlBean();
        oaForm.setTableName("formmain_5353");
        oaForm.setTableHeaderDataMap(genTableHeaderDataMap(maintain));
        oaForm.setTableLinesDataList(genTableLinesDataList(maintain, maintainContents));

        String data = XmlUtil.getOaFormXmlString(oaForm);
        // 连接的环境信息
        try {
            OaConfigInfo conf = new OaConfigInfo(environment);

            String token = OaInterfaceUtil.getOaToken(conf);
            
            // 发起流程表单
            BPMServiceStub bpmServiceStub = new BPMServiceStub(conf);// new BPMServiceStub();
            BPMServiceStub.LaunchFormCollaboration launchFormCollaboration = new BPMServiceStub.LaunchFormCollaboration();
            launchFormCollaboration.setToken(token);
            launchFormCollaboration.setSenderLoginName(findLoginNameByCode("006124")); // 发起者的登录名（登录协同的登录名）
            if ("1".equals(maintain.getType())) {
            	launchFormCollaboration.setTemplateCode("WB_01"); // 模板编号
                launchFormCollaboration.setSubject("设备定期维护和保养记录表-设备类"); // 协同的标题
            } else {
            	launchFormCollaboration.setTemplateCode("WB_02"); // 模板编号
                launchFormCollaboration.setSubject("设备定期维护和保养记录表-公设类"); // 协同的标题
            }
            launchFormCollaboration.setData(data); // XML格式的表单数据
            launchFormCollaboration.setParam("0"); //0-发起流程 1-待发
            launchFormCollaboration.setRelateDoc("");

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
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (com.suntak.autotask.authorityService.ServiceException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
