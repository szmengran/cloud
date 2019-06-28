package com.suntak.autotask.task;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import com.suntak.autotask.bean.OaFormXmlBean;
import com.suntak.autotask.bean.OaPaymentFormXmlBean;
import com.suntak.autotask.bean.PaymentConfigBean;
import com.suntak.autotask.bean.PaymentInfoBean;
import com.suntak.autotask.dbcon.OaDbCon;
import com.suntak.autotask.utils.DateUtil;
import com.suntak.autotask.utils.DbUtil;
import com.suntak.autotask.utils.OaInterfaceUtil;
import com.suntak.autotask.utils.PaymentUtil;
import com.suntak.autotask.utils.XmlUtil;
/**
 * 提交集中付款申请表类 - 20190218
 * @author zzhong
 *
 */
public class SubmitPaymentFormTask extends TimerTask{
	
	/**
	 * 自动发单运行方法 - ZZHONG20190219
	 */
	public void run(){
		try{
			//Connection conn = DbUtil.getConnection(DbUtil.oaTestJNDI);
			Connection conn = DbUtil.getConnectionTest();
			OaDbCon dbCon = new OaDbCon();
			String today = DateUtil.getTodayOfMonth();//获取当天
			String orgIds = "";
			String attachments = "col|";
			List<PaymentConfigBean> paymentConfigList = dbCon.getPaymentConfigs(conn, today);//获取自动发单配置
		
			for(PaymentConfigBean paymentConfig : paymentConfigList){//循环配置
				orgIds = paymentConfig.getOrgId();
				if(paymentConfig.getConnectedOrgId() != null && !"".equals(paymentConfig.getConnectedOrgId())){
					orgIds += ("," + paymentConfig.getConnectedOrgId());
				}
				Map<String,String> paymentHeaderDataMap = PaymentUtil.getPaymentHeaderDataMap(paymentConfig);//获取头部信息
				List<Map<String,String>> paymentLineDataList = PaymentUtil.getPaymentLinesDataList(dbCon.getPaymentInfos(conn, orgIds));//获取行信息
				
				
				long[] attachmentIds = new long[paymentLineDataList.size()];
				
				for(int i = 0; i< paymentLineDataList.size(); i++){
					if(i == paymentLineDataList.size() - 1){
						attachments +=  paymentLineDataList.get(i).get("附件");
					}else{
						attachments +=  paymentLineDataList.get(i).get("附件") + ",";
					}
					attachments = "doc|-505608302030338953";
					//attachmentIds[i] = Long.parseLong(paymentLineDataList.get(i).get("附件"));
					attachmentIds[0] = Long.parseLong("-505608302030338953");
					//"col|" +
				}
				if(paymentLineDataList.size() == 0){
					continue;//如果没有要提交的数据，则跳入下一个循环对象
				}
				
				OaFormXmlBean oaPaymentFormXml = new OaPaymentFormXmlBean(paymentHeaderDataMap,paymentLineDataList);//构造xml数据
				String oaPaymentFormXmlStr = XmlUtil.getOaFormXmlString(oaPaymentFormXml);//xml转xml字符串
				System.out.println(oaPaymentFormXmlStr);
				OaInterfaceUtil.sendFormDataGetProcess(paymentConfig.getSubmitMemberLoginName(), oaPaymentFormXmlStr,  paymentConfig.getTemplateNo(),attachments,attachmentIds);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		SubmitPaymentFormTask spf = new SubmitPaymentFormTask();
		spf.run();
	}
}
