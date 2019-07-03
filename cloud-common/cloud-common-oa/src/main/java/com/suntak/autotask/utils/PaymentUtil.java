package com.suntak.autotask.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.suntak.autotask.bean.PaymentConfigBean;
import com.suntak.autotask.bean.PaymentInfoBean;

/**
 * 付款辅助类 - 20190216
 * @author zzhong
 *
 */
public class PaymentUtil {
	
	/*5609100628134807954	深圳市崇达电路技术股份有限公司
	3650387499380656055	大连崇达电路有限公司
	-6495234705343299168	深圳崇达多层线路板有限公司
	6489003421364994161	江门崇达电路技术有限公司一厂
	-555854590763580910	深圳崇达多层线路板有限公司工会基层委员会
	7795324460883004165	江门崇达电路技术有限公司工会委员会
	-6866460268310672236	崇达科技有限公司
	-2909770516084604651	珠海崇达电路技术有限公司一厂
	1396307147766976165	江门崇达电路技术有限公司二厂*/
	
	/*  logo
	 2919952288753074615	大连崇达电路有限公司
	4834018183832422373	崇达标志
	-3351326996914155761	崇達科技有限公司
	-3706096552681324158	珠海崇达电路技术有限公司
	1322286928033564228	崇达技术股份有限公司
	-7029517347548348310	深圳崇达多层线路板有限公司
	-5814863409063705555	江门崇达电路技术有限公司
	-4388304232862954122	崇达SUNTAK(绿)
	3406907541203405463	崇达技术股份有限公司（绿）
	-8066477899620989483	江门崇达电路技术有限公司二厂
	-4328118675100252008	深圳市崇达电路技术股份有限公司
	-6164444797790371798	大连崇达电子有限公司*/

	
	/**
	 * 获取付款头部数据map - ZZHONG20190216
	 * @param paymentConfig
	 * @return
	 */
	public static Map<String,String> getPaymentHeaderDataMap(PaymentConfigBean paymentConfig){
		Map<String,String> headerDataMap = new HashMap<String,String>();
		headerDataMap.put("月旬", paymentConfig.getMon());
		
		if(paymentConfig.getOrgId() != null && paymentConfig.getOrgId().contains("5609100628134807954")
				||paymentConfig.getConnectedOrgId() != null && paymentConfig.getConnectedOrgId().contains("5609100628134807954")){
			headerDataMap.put("股份公司", "1");
			headerDataMap.put("公司logo","1322286928033564228");
		}else if(paymentConfig.getOrgId() != null && paymentConfig.getOrgId().contains("6489003421364994161")
				||paymentConfig.getConnectedOrgId() != null && paymentConfig.getConnectedOrgId().contains("6489003421364994161")){
			headerDataMap.put("江门一厂", "1");
			headerDataMap.put("公司logo","-5814863409063705555");
		}else if(paymentConfig.getOrgId() != null && paymentConfig.getOrgId().contains("1396307147766976165")
				||paymentConfig.getConnectedOrgId() != null && paymentConfig.getConnectedOrgId().contains("1396307147766976165")){
			headerDataMap.put("江门二厂", "1");
			headerDataMap.put("公司logo","-5814863409063705555");
		}else if(paymentConfig.getOrgId() != null && paymentConfig.getOrgId().contains("-6495234705343299168")
				||paymentConfig.getConnectedOrgId() != null && paymentConfig.getConnectedOrgId().contains("-6495234705343299168")){
			headerDataMap.put("深圳厂", "1");
			headerDataMap.put("公司logo","-7029517347548348310");
		}else if(paymentConfig.getOrgId() != null && paymentConfig.getOrgId().contains("3650387499380656055")
				||paymentConfig.getConnectedOrgId() != null && paymentConfig.getConnectedOrgId().contains("3650387499380656055")){
			headerDataMap.put("大连厂", "1");
			headerDataMap.put("公司logo","2919952288753074615");
		}else if(paymentConfig.getOrgId() != null && paymentConfig.getOrgId().contains("-555854590763580910")
				||paymentConfig.getConnectedOrgId() != null && paymentConfig.getConnectedOrgId().contains("-555854590763580910")){
			headerDataMap.put("深圳工会", "1");
			headerDataMap.put("公司logo","1322286928033564228");
		}else if(paymentConfig.getOrgId() != null && paymentConfig.getOrgId().contains("7795324460883004165")
				||paymentConfig.getConnectedOrgId() != null && paymentConfig.getConnectedOrgId().contains("7795324460883004165")){
			headerDataMap.put("江门工会", "1");
			headerDataMap.put("公司logo","-5814863409063705555");
		}else if(paymentConfig.getOrgId() != null && paymentConfig.getOrgId().contains("-6866460268310672236")
				||paymentConfig.getConnectedOrgId() != null && paymentConfig.getConnectedOrgId().contains("-6866460268310672236")){
			headerDataMap.put("崇达科技", "1");
			headerDataMap.put("公司logo","1322286928033564228");
		}
		return headerDataMap;
	}
	
	/**
	 * 获取付款行数据list - ZZHONG20190218
	 * @param paymentInfos
	 * @return
	 */
	public static List<Map<String,String>> getPaymentLinesDataList(List<PaymentInfoBean> paymentInfos){
		List<Map<String,String>> linesDataList = new ArrayList<Map<String,String>>();
		Map<String,String> lineDataMap = null;
		for(PaymentInfoBean paymentInfo : paymentInfos){
			lineDataMap = new HashMap<String,String>();
			lineDataMap.put("单据号码", paymentInfo.getFormNo());
			lineDataMap.put("摘要", paymentInfo.getPayReason());
			lineDataMap.put("付款日期", paymentInfo.getPayDate());
			lineDataMap.put("币别", paymentInfo.getCurrency());
			lineDataMap.put("网银实际付款",paymentInfo.getOriginalPrice());
			lineDataMap.put("折人民币金额", paymentInfo.getCnyPrice());
			lineDataMap.put("报销人供应商", paymentInfo.getPayMan());
			lineDataMap.put("列支工厂", paymentInfo.getOrgId());
			lineDataMap.put("附件", paymentInfo.getSummaryId());
			lineDataMap.put("回写", "1");
			linesDataList.add(lineDataMap);
		}
		return linesDataList;
	}
	
	
	
}
