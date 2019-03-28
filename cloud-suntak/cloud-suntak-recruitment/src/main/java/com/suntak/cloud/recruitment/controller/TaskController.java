package com.suntak.cloud.recruitment.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.SqlTimestampConverter;
import org.apache.commons.lang.StringUtils;
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

import com.suntak.cloud.recruitment.client.UserServiceClient;
import com.suntak.cloud.recruitment.client.WechatServiceClient;
import com.suntak.cloud.recruitment.entity.T_hr_applicant;
import com.suntak.cloud.recruitment.entity.T_hr_task;
import com.suntak.cloud.recruitment.entity.T_hr_workflow_sub;
import com.suntak.cloud.recruitment.entity.ext.T_hr_task_ext;
import com.suntak.cloud.recruitment.service.ApplicantService;
import com.suntak.cloud.recruitment.service.TaskService;
import com.suntak.cloud.wechat.entity.request.Textcard;
import com.suntak.cloud.wechat.entity.request.TextcardRequestBody;
import com.suntak.exception.model.Response;
import com.suntak.admin.user.exception.BusinessException;

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
	
	@Value("${wechat.recruitment.Secret}")
	private String secret;
	
	@Autowired
	TaskService taskService;

	@Autowired
	ApplicantService applicantService;
	
	@Autowired
	WechatServiceClient wechatServiceClient;
	
	@Autowired
	UserServiceClient userServiceClient;
	
	@GetMapping("/task/{roles}/{userid}")
	public Response find(@PathVariable("userid") String userid, @PathVariable("roles") String strRole) throws Exception {
		Response resp = userServiceClient.findRoleByUsername(userid);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> roleList = (List<Map<String, String>>)resp.getData();
		String[] roles = null;
		if (roleList != null && roleList.size() > 0) {
			roles = new String[roleList.size()];
			for (int i = 0; i < roleList.size(); i++) {
				roles[i] = roleList.get(i).get("rolename");
			}
		}
		List<T_hr_task_ext> list = taskService.find(roles, userid);
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
				return qywechatNotification(t_hr_applicant.getOwnerid(), t_hr_applicant.getName(), "提交了基本资料，请抓紧处理！");
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
	 * 崇达招聘企业微信通知
	 * @param toUser
	 * @param name
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	private Response qywechatNotification(String toUser, String name, String content) throws Exception {
		TextcardRequestBody textcardRequestBody = new TextcardRequestBody();
		textcardRequestBody.setTouser(toUser);
		textcardRequestBody.setMsgtype("textcard");
		Textcard textcard = new Textcard();
		textcard.setTitle("崇达招聘");
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"gray\">")
		  .append(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()))
		  .append("</div>")
		  .append("<div class=\"highlight\">应聘人：【")
		  .append(name)
		  .append("】</div>")
		  .append("</div>")
		  .append("<div class=\"normal\">")
		  .append("你好，")
		  .append(content)
		  .append("</div>");
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
		textcardRequestBody.setTextcard(textcard);
		textcardRequestBody.setAgentid(agentId);
		return wechatServiceClient.sendTextcard(secret, textcardRequestBody);
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
	
	/**
	 * 处理任务
	 * @param t_hr_task
	 * @return
	 * @throws Exception 
	 * @author <a href="mailto:android_li@sina.cn">Joe</a>
	 */
	@PostMapping(value="/task")
	public Response handlerTask(@RequestBody T_hr_task_ext t_hr_task_ext) throws Exception {
		T_hr_task t_hr_task = new T_hr_task();
		ConvertUtils.register(new SqlTimestampConverter(null), Timestamp.class);  
		BeanUtils.copyProperties(t_hr_task, t_hr_task_ext);
		T_hr_workflow_sub t_hr_workflow_sub = taskService.handlerTask(t_hr_task);
		ExecutorService executor = Executors.newCachedThreadPool();
		executor.submit(() -> {
			try {
				if (StringUtils.isBlank(t_hr_task.getAssign())) {
					Response response = userServiceClient.findUserByRole(t_hr_task.getAssignrole());
					@SuppressWarnings("unchecked")
					List<Map<String, String>> list = (List<Map<String, String>>)response.getData();
					if (list != null && list.size() > 0) {
						StringBuilder toUser = new StringBuilder();
						for (Map<String, String> map : list) {
							toUser.append("|").append(map.get("username"));
						}
						qywechatNotification(toUser.substring(1), t_hr_task_ext.getName(), new StringBuilder().append("你有一个【").append(t_hr_workflow_sub.getSubflowname()).append("】任务待处理！").toString());
					} else {
						logger.error("企业微信通知失败,找不到该角色【{}】对应的用户",t_hr_task.getAssignrole());
					}
				} else {
					qywechatNotification(t_hr_task.getAssign(), t_hr_task_ext.getName(), new StringBuilder().append("你有一个【").append(t_hr_workflow_sub.getSubflowname()).append("】任务待处理！").toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("企业微信通知失败：", e);
			}
		});
		
		return new Response();
	}
}
