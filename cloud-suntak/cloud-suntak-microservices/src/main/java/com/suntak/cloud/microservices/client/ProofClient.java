package com.suntak.cloud.microservices.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Package com.suntak.cloud.microservices
 * @Description: 收入证明请求
 * @date Nov 28, 2018 1:53:07 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@FeignClient(name = "proof", url = "http://webservice.suntakpcb.com:8080/")
public interface ProofClient {
	
	@GetMapping(value = "sign/tpl.action?code={code}&emp_num={emp_code}&sert={sert}&reason={reason}&tax_flag={tax_flag}")
	public String applyProofOfIncome(@PathVariable("code") String code, @PathVariable("emp_code") String emp_code, @PathVariable("sert") String sert, @PathVariable("reason") String reason, @PathVariable("tax_flag") String tax_flag) throws Exception;
	
	@GetMapping(value = "sign/tpl.action?code={code}&emp_num={emp_code}&sert={sert}")
	public String applyProofOfResidence(@PathVariable("code") String code, @PathVariable("emp_code") String emp_code, @PathVariable("sert") String sert) throws Exception;
	
}
