package com.suntak.autotask.utils;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import com.suntak.autotask.BPMService.BPMServiceStub;
import com.suntak.autotask.authorityService.AuthorityServiceStub;

/**
 * 接口辅助类 - 20190219
 * @author zzhong
 *
 */
public class OaInterfaceUtil {
	
	/**
	 * 获取oa的token - ZZHONG20190219
	 * @param conf
	 * @return
	 * @throws Exception
	 */
	public static String getOaToken(OaConfigInfo conf) throws Exception{
		// 获取身份令牌
		AuthorityServiceStub authorityStub = new AuthorityServiceStub(conf);
		AuthorityServiceStub.Authenticate authenticate = new AuthorityServiceStub.Authenticate();
		authenticate.setUserName("service-admin"); // 接口登录名
		authenticate.setPassword("mustchange"); // 接口密码
		AuthorityServiceStub.AuthenticateResponse authenticateResp = authorityStub.authenticate(authenticate);
		AuthorityServiceStub.UserToken userToken = authenticateResp.get_return(); // 得到令牌
		String token = userToken.getId();
		System.out.println(token);
		return token;
	}
	
	
	/**
	 * 发起自动流程 - ZZHONG20190219
	 * @param submitMemberLoginName
	 * @param formData
	 * @param formNo
	 */
	public static void sendFormDataGetProcess(String submitMemberLoginName,String formData,String formNo,String attachments,long[] attachmentIds){
		// 连接的环境信息
		try{
			OaConfigInfo conf = new OaConfigInfo("test");

			System.out.println("connect url:" + conf.getUrl());
			System.out.println("username:" + conf.getUsername());
			System.out.println("password:" + conf.getPassword());

			String token = OaInterfaceUtil.getOaToken(conf);

			// 发起流程表单
			 BPMServiceStub bpmServiceStub = new BPMServiceStub(conf);//new BPMServiceStub();
			 BPMServiceStub.LaunchFormCollaboration launchFormCollaboration = new BPMServiceStub.LaunchFormCollaboration();
			 launchFormCollaboration.setToken(token);
			 launchFormCollaboration.setSenderLoginName(submitMemberLoginName); //发起者的登录名（登录协同的登录名）
			 launchFormCollaboration.setTemplateCode(formNo); //模板编号
			 launchFormCollaboration.setSubject(""); // 协同的标题
			 launchFormCollaboration.setData(formData); // XML格式的表单数据
			 launchFormCollaboration.setAttachments(attachmentIds);
			 launchFormCollaboration.setParam("0");
			 launchFormCollaboration.setRelateDoc(attachments);
			 
			 BPMServiceStub.LaunchFormCollaborationResponse launchFormCollaborationResp = bpmServiceStub.launchFormCollaboration(launchFormCollaboration);
			 BPMServiceStub.ServiceResponse serviceResp = launchFormCollaborationResp.get_return();
			 Long errorNumber = serviceResp.getErrorNumber(); // 错误代码
			 String errorMessage = serviceResp.getErrorMessage(); // 错误消息
			 Long processId = serviceResp.getResult(); // 流程ID
			 
			 System.out.println("错误代码：" + errorNumber + "错误消息：" + errorMessage + "流程ID：" + processId);
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
	
	public static void main(String[] args) throws Exception {
	    sendFormDataGetProcess("submitMemberLoginName", "<formExport version=\"2.0\"></formExport>", "EIntoFactory_01", "", null);
	}
	
}
