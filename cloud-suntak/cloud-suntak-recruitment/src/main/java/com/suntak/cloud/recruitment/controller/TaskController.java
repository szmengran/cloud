package com.suntak.cloud.recruitment.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.recruitment.client.WechatServiceClient;
import com.suntak.cloud.recruitment.entity.T_hr_applicant;
import com.suntak.cloud.recruitment.entity.T_hr_task;
import com.suntak.cloud.recruitment.entity.ext.T_hr_task_ext;
import com.suntak.cloud.recruitment.service.ApplicantService;
import com.suntak.cloud.recruitment.service.TaskService;
import com.suntak.cloud.wechat.entity.MsgRequestBody;
import com.suntak.cloud.wechat.entity.Textcard;
import com.suntak.exception.model.Response;
import com.szmengran.admin.user.exception.BusinessException;

import io.swagger.annotations.Api;

/**
 * @Package com.suntak.cloud.recruitment.controller
 * @Description: TODO
 * @date 2018年8月22日 上午9:23:22
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "任务服务")
@RestController
@RequestMapping(value="/api/v1/recruitment")
public class TaskController {
	
	Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	@Value("${wechat.qy.AppID}")
    private String appID;
	
	@Value("${wechat.recruitment.AgentId}")
	private String agentId;
	
	@Value("${task.interview.url}")
	private String tasklisturl;
	
	@Value("${task.interview.startrole}")
	private String startrole;
	@Value("${task.interview.workflowid}")
	private Integer workflowid;
	
	@Autowired
	TaskService taskService;

	@Autowired
	ApplicantService applicantService;
	
	@Autowired
	WechatServiceClient wechatServiceClient;
	
	@PostMapping("/task")
	public Response insert(@RequestBody T_hr_task t_hr_task) throws Exception {
		taskService.insert(t_hr_task);
		return new Response();
	}
	
	@GetMapping("/task/{roles}/{userid}")
	public Response find(@PathVariable("userid") String userid, @PathVariable("roles") String strRole) throws Exception {
		List<T_hr_task_ext> list = taskService.find(strRole.split(","), userid);
		Response response = new Response();
		response.setData(list);
		return response;
	}
	
	/**
	 * 应聘者填完资料之后，给应聘专员发送企业微信提醒通知
	 * @param applicantid
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@GetMapping(value="/step/finish/{applicantid}")
	public Response finish(@PathVariable("applicantid") String applicantid) throws Exception {
		T_hr_applicant t_hr_applicant = applicantService.findById(applicantid);
		if (null == t_hr_applicant.getStatus() || t_hr_applicant.getStatus() == 0) { //状态为0表示信息还没有提交过
			ExecutorService executor = Executors.newCachedThreadPool();
			
			Future<Boolean> createFuture = executor.submit(() -> {
				return createTask(t_hr_applicant);
			});
			
			Future<Response> sendFuture = executor.submit(() -> {
				MsgRequestBody msgRequestBody = new MsgRequestBody();
				msgRequestBody.setTouser(t_hr_applicant.getOwnerid());
				msgRequestBody.setMsgtype("textcard");
				Textcard textcard = new Textcard();
				textcard.setTitle("崇达招聘");
				StringBuilder sb = new StringBuilder();
				sb.append("<div class=\"gray\">")
				  .append(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()))
				  .append("</div> <div class=\"normal\">")
				  .append("你好，")
				  .append(t_hr_applicant.getName())
				  .append("提交了个人基本资料，请安排初试！</div>");
				textcard.setDescription(sb.toString());
				StringBuilder url = new StringBuilder();
				url.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=")
				   .append(appID)
				   .append("&redirect_uri=")
				   .append(tasklisturl) //跳转到员工的任务列表中
				   .append("&response_type=code&scope=snsapi_base&agentid=")
				   .append(agentId)
				   .append("&state=STATE#wechat_redirect");
				textcard.setUrl(url.toString());
				textcard.setBtntxt("更多");
				msgRequestBody.setTextcard(textcard);
				msgRequestBody.setAgentid(agentId);
				return wechatServiceClient.sendTextcard(msgRequestBody);
			});
			Boolean createFlag = createFuture.get();
			Response response = sendFuture.get();
			
			if (createFlag && response.getStatus() == 200) {
				t_hr_applicant.setStatus(1); //更新状态
				applicantService.update(t_hr_applicant);
			} else {
				logger.error(response.getMessage());
				throw new BusinessException(1000001, "系统繁忙，请稍后再试！");
			}
		}
		return new Response();
	}
	
	/**
	 * 创建任务
	 * @param t_hr_applicant
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	private Boolean createTask(T_hr_applicant t_hr_applicant) throws Exception {
		T_hr_task t_hr_task = new T_hr_task();
		t_hr_task.setSubflowid(workflowid);
		t_hr_task.setAssignrole(startrole);
		t_hr_task.setAssign(t_hr_applicant.getOwnerid());
		t_hr_task.setApplicantid(t_hr_applicant.getApplicantid());
		taskService.insert(t_hr_task);
		return true;
	}
}
