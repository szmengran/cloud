package com.suntak.autotask.dbcon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.suntak.autotask.bean.PaymentConfigBean;
import com.suntak.autotask.bean.PaymentInfoBean;

/**
 * OA数据库连接类 - 20190216
 * @author zzhong
 *
 */
public class OaDbCon {
	
	static Log log = LogFactory.getLog(OaDbCon.class);
	
	/**
	 * 获取自动发单配置 - ZZHONG20190216
	 * @param conn
	 * @param submitDay
	 * @return
	 */
	public List<PaymentConfigBean> getPaymentConfigs(Connection conn,String submitDay){
		log.debug("获取自动发单配置数据。");
		List<PaymentConfigBean> paymentConfigs = new ArrayList<PaymentConfigBean>();
		PreparedStatement ptst = null;
		PaymentConfigBean paymentConfig = null;
		StringBuffer sql = new StringBuffer();
			sql.append(" select config.org_id org_id,config.template_no template_no,config.mon mon,config.mon_item,config.submit_day submit_day,config.submit_member submit_member ");
			sql.append(" ,config.submit_member_name submit_member_name,config.submit_member_login_name submit_member_login_name,config.connected_org_id connected_org_id ");
			sql.append(" from cux_oa_payment_config_v config where 1=1 and config.submit_day = ? ");
		try{
			ptst = conn.prepareStatement(sql.toString());
			ptst.setString(1, submitDay);
			ResultSet rs = ptst.executeQuery();
			while(rs.next()){
				paymentConfig = new PaymentConfigBean();
				paymentConfig.setOrgId(rs.getString("org_id"));
				paymentConfig.setMon(rs.getString("mon"));
				paymentConfig.setMonItem(rs.getString("mon_item"));
				paymentConfig.setSubmitDay(rs.getString("submit_day"));
				paymentConfig.setSubmitMember(rs.getString("submit_member"));
				paymentConfig.setSubmitMemberName(rs.getString("submit_member_name"));
				paymentConfig.setSubmitMemberLoginName(rs.getString("submit_member_login_name"));
				paymentConfig.setConnectedOrgId(rs.getString("connected_org_id"));
				paymentConfig.setTemplateNo(rs.getString("template_no"));
				paymentConfigs.add(paymentConfig);
			}
			rs.close();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if(ptst != null){
				try{
					ptst.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return paymentConfigs;
	}
	
	
	/**
	 * 获取汇总支付信息 - ZZHONG20190216
	 * @param conn
	 * @param orgIds
	 * @return
	 */
	public List<PaymentInfoBean> getPaymentInfos(Connection conn,String orgIds){
		log.debug("获取汇总支付信息。");
		List<PaymentInfoBean> paymentInfos = new ArrayList<PaymentInfoBean>();
		PreparedStatement ptst = null;
		PaymentInfoBean paymentInfo = null;
		
		String[] orgIdArray = orgIds.split(",");
		
		String questionMarkStr = "";
		for(int i = 0; i < orgIdArray.length; i++){
			if(i == orgIdArray.length-1){
				questionMarkStr += "?";
			}else{
				questionMarkStr += "?,";
			}
		}
		
		StringBuffer sql = new StringBuffer();
			sql.append(" select cocp.org_id org_id,cocp.org_name org_name,cocp.form_no form_no,cocp.pay_man pay_man,cocp.pay_date pay_date,cocp.currency currency ");
			sql.append(" ,cocp.original_price original_price,cocp.cny_price cny_price,cocp.form_type form_type,cocp.pay_reason pay_reason,cocp.summary_id summary_id ");
			sql.append(" from cux_oa_collect_payment_info_v cocp where cocp.org_id in (" + questionMarkStr + ") " );
			
			log.debug(sql.toString());
		try{
			ptst = conn.prepareStatement(sql.toString());
			
			for(int j = 0; j < orgIdArray.length; j++){
				ptst.setString(j+1, orgIdArray[j]);
			}
			
			ResultSet rs = ptst.executeQuery();
			while(rs.next()){
				paymentInfo = new PaymentInfoBean();
				paymentInfo.setOrgId(rs.getString("org_id"));
				paymentInfo.setOrgName(rs.getString("org_name"));
				paymentInfo.setFormNo(rs.getString("form_no"));
				paymentInfo.setPayMan(rs.getString("pay_man"));
				paymentInfo.setPayDate(rs.getString("pay_date"));
				paymentInfo.setCurrency(rs.getString("currency"));
				paymentInfo.setOriginalPrice(rs.getString("original_price"));
				paymentInfo.setCnyPrice(rs.getString("cny_price"));
				paymentInfo.setFormType(rs.getString("form_type"));
				paymentInfo.setPayReason(rs.getString("pay_reason"));
				paymentInfo.setSummaryId(rs.getString("summary_id"));
				paymentInfos.add(paymentInfo);
			}
			rs.close();
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			if(ptst != null){
				try{
					ptst.close();
				}catch(SQLException e){
					e.printStackTrace();
				}
			}
		}
		return paymentInfos;
	}
	
	
}
