package com.suntak.cloud.oa.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suntak.cloud.oa.client.WechatClient;
import com.suntak.cloud.oa.entity.Cux_oa_personal_dev_plan_v;
import com.suntak.cloud.oa.entity.Cux_oa_qywx_aqscfx_v;
import com.suntak.cloud.oa.entity.Cux_oa_qywx_grlhzhpj_v;
import com.suntak.cloud.oa.entity.Cux_oa_qywx_jjjchz_v;
import com.suntak.cloud.oa.entity.Cux_oa_qywx_jjjcmx_v;
import com.suntak.cloud.oa.entity.Cux_oa_qywx_jngzmx_v;
import com.suntak.cloud.oa.entity.Cux_oa_qywx_tdlhzhpj_v;
import com.suntak.cloud.oa.service.JjjchzService;
import com.suntak.cloud.oa.service.JjjcmxService;
import com.suntak.cloud.oa.service.OaService;
import com.suntak.cloud.wechat.entity.request.Textcard;
import com.suntak.cloud.wechat.entity.request.TextcardRequestBody;
import com.suntak.exception.model.Response;

import io.swagger.annotations.Api;
import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

/**
 * @Package com.suntak.cloud.oa.controller
 * @Description: oa自动提醒
 * @date Jan 28, 2019 1:06:19 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api(value = "oa")
@RestController
@RequestMapping(path="/api/v1/oa", produces = { "application/json" })
public class RemindController {
	
	private static final Logger logger = LoggerFactory.getLogger(JjjchzController.class);
	private static final ExecutorService executor = new ThreadPoolExecutor(10, 200, 0L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
	public static final String ASSISTANT_NAME = "【崇达技术】";
	
	@Value("${wechat.qy.AgentId}")
	private String agentId;
	
	@Value("${wechat.qy.Secret}")
	private String secret;

	@Autowired
	private WechatClient wechatClient;

	@Value("${wechat.remind.url.jjjc}")
	private String jjjcUrl;
	
	@Value("${wechat.remind.url.jjjcmx}")
	private String jjjcmxUrl;
	
	@Value("${wechat.remind.url.jngzmx}")
	private String jngzmxUrl;
	
	@Value("${wechat.remind.url.tdlhzhpj}")
	private String tdlhzhpjUrl;
	
	@Value("${wechat.remind.url.grlhzhpj}")
	private String grlhzhpjUrl;
	
	@Value("${wechat.remind.url.plan}")
	private String plan;
	
	@Value("${wechat.remind.url.aqscfx}")
	private String aqscfxUrl;
	
	@Autowired
	private JjjchzService jjjchzService;
	
	@Autowired
	private JjjcmxService jjjcmxService;
	
	@Autowired
	@Qualifier("jngzmxService")
	private OaService jngzmxService;
	
	@Autowired
	@Qualifier("tdlhzhpjService")
	private OaService tdlhzhpjService;
	
	@Autowired
	@Qualifier("grlhzhpjService")
	private OaService grlhzhpjService;
	
	@Autowired
	@Qualifier("personalDevPlanService")
	private OaService personalDevPlanService;
	
    @Autowired
    @Qualifier("aqscfxService")
    private OaService aqscfxService;
	
	@GetMapping(value="/remind")
	public Response remind() throws Exception {
		//发送经济奖惩通知
		executor.submit(() -> {
			List<Cux_oa_qywx_jjjchz_v> list = jjjchzService.findJjjcByConditions();
			for (Cux_oa_qywx_jjjchz_v cux_oa_qywx_jjjchz_v: list) {
			    if (StringUtils.isBlank(cux_oa_qywx_jjjchz_v.getId())) {
			        continue;
			    }
				executor.submit(() -> {
					try {
						Response response = sendTextcard("经济奖惩确认", cux_oa_qywx_jjjchz_v.getL_code(), getOauthUrl(jjjcUrl+cux_oa_qywx_jjjchz_v.getId()));
						if (response.getStatus() == 200) {
							jjjchzService.updateById(cux_oa_qywx_jjjchz_v.getId());
						} else {
							logger.error(response.getMessage());
						}
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
					}
				});
			}
			return list;
		});
		
		//发送报废/返工/修理经济奖惩通知
		executor.submit(() -> {
			List<Cux_oa_qywx_jjjcmx_v> list = jjjcmxService.findJjjcmxByConditions();
			for (Cux_oa_qywx_jjjcmx_v cux_oa_qywx_jjjcmx_v: list) {
			    if (StringUtils.isBlank(cux_oa_qywx_jjjcmx_v.getId())) {
			        continue;
			    }
				executor.submit(() -> {
					try {
						Response response = sendTextcard("报废/返工/修理经济奖惩确认", cux_oa_qywx_jjjcmx_v.getL_code(), getOauthUrl(jjjcmxUrl+cux_oa_qywx_jjjcmx_v.getId()));
						if (response.getStatus() == 200) {
							jjjcmxService.updateById(cux_oa_qywx_jjjcmx_v.getId());
						} else {
							logger.error(response.getMessage());
						}
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
					}
				});
			}
			return list;
		});
		
		executor.submit(() -> {
			List<?> list = jngzmxService.findByConditions();
			for (Object object: list) {
			    Cux_oa_qywx_jngzmx_v cux_oa_qywx_jngzmx_v = (Cux_oa_qywx_jngzmx_v)object;
			    if (StringUtils.isBlank(cux_oa_qywx_jngzmx_v.getId())) {
			        continue;
			    }
				executor.submit(() -> {
					try {
						Response response = sendTextcard("技能工资明细确认", cux_oa_qywx_jngzmx_v.getL_code(), getOauthUrl(jngzmxUrl+cux_oa_qywx_jngzmx_v.getId()));
						if (response.getStatus() == 200) {
							jngzmxService.updateById(cux_oa_qywx_jngzmx_v.getId());
						} else {
							logger.error(response.getMessage());
						}
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
					}
				});
			}
			return list;
		});
		
		executor.submit(() -> {
			List<?> list = tdlhzhpjService.findByConditions();
			for (Object object: list) {
			    Cux_oa_qywx_tdlhzhpj_v cux_oa_qywx_tdlhzhpj_v = (Cux_oa_qywx_tdlhzhpj_v)object;
			    if (StringUtils.isBlank(cux_oa_qywx_tdlhzhpj_v.getId())) {
                    continue;
                }
			    executor.submit(() -> {
					try {
						Response response = sendTextcard("团队量化综合评价确认", cux_oa_qywx_tdlhzhpj_v.getL_code(), getOauthUrl(tdlhzhpjUrl+cux_oa_qywx_tdlhzhpj_v.getId()));
						if (response.getStatus() == 200) {
							tdlhzhpjService.updateById(cux_oa_qywx_tdlhzhpj_v.getId());
						} else {
							logger.error(response.getMessage());
						}
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
					}
				});
			}
			return list;
		});
		
		executor.submit(() -> {
			List<?> list = grlhzhpjService.findByConditions();
			for (Object object: list) {
			    Cux_oa_qywx_grlhzhpj_v cux_oa_qywx_grlhzhpj_v = (Cux_oa_qywx_grlhzhpj_v)object;
			    if (StringUtils.isBlank(cux_oa_qywx_grlhzhpj_v.getId())) {
                    continue;
                }
			    executor.submit(() -> {
					try {
						Response response = sendTextcard("个人量化综合评价确认", cux_oa_qywx_grlhzhpj_v.getL_code(), getOauthUrl(grlhzhpjUrl+cux_oa_qywx_grlhzhpj_v.getId()));
						if (response.getStatus() == 200) {
							grlhzhpjService.updateById(cux_oa_qywx_grlhzhpj_v.getId());
						} else {
							logger.error(response.getMessage());
						}
					} catch (Exception e) {
						e.printStackTrace();
						logger.error(e.getMessage());
					}
				});
			}
			return list;
		});
		
		executor.submit(() -> {
		    List<?> list = personalDevPlanService.findByConditions();
		    for (Object object: list) {
		        Cux_oa_personal_dev_plan_v cux_oa_personal_dev_plan_v = (Cux_oa_personal_dev_plan_v)object;
		        if (StringUtils.isBlank(cux_oa_personal_dev_plan_v.getForm_id())) {
                    continue;
                }
		        executor.submit(() -> {
		            try {
		                Response response = sendTextcard("个人学历及职业资格提升计划确认", cux_oa_personal_dev_plan_v.getEmp_code(), getOauthUrl(plan+cux_oa_personal_dev_plan_v.getForm_id()));
		                if (response.getStatus() == 200) {
		                    personalDevPlanService.updateById(cux_oa_personal_dev_plan_v.getForm_id());
		                } else {
		                    logger.error(response.getMessage());
		                }
		            } catch (Exception e) {
		                e.printStackTrace();
		                logger.error(e.getMessage());
		            }
		        });
		    }
		    return list;
		});
		
		executor.submit(() -> {
            List<?> list = aqscfxService.findByConditions();
            for (Object object: list) {
                Cux_oa_qywx_aqscfx_v cux_oa_qywx_aqscfx_v = (Cux_oa_qywx_aqscfx_v)object;
                if (StringUtils.isBlank(cux_oa_qywx_aqscfx_v.getId())) {
                    continue;
                }
                executor.submit(() -> {
                    try {
                        Response response = sendTextcard("安全责任书确认", cux_oa_qywx_aqscfx_v.getL_code(), getOauthUrl(aqscfxUrl+cux_oa_qywx_aqscfx_v.getId()));
                        if (response.getStatus() == 200) {
                            aqscfxService.updateById(cux_oa_qywx_aqscfx_v.getId());
                        } else {
                            logger.error(response.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.error(e.getMessage());
                    }
                });
            }
            return list;
        });
        
		return new Response();
	}
	

	private static String getOauthUrl(String url) throws UnsupportedEncodingException {
		url = URLEncoder.encode(url, "UTF-8");
		return "https://open.weixin.qq.com/connect/oauth2/authorize?appid=ww8973695804b8d199&redirect_uri="+url+"&response_type=code&scope=snsapi_userinfo&agentid=1000028&state=STATE#wechat_redirect";
	}
	
	private Response sendTextcard(String title, String users, String url) {
		TextcardRequestBody textcardRequestBody = new TextcardRequestBody();
		textcardRequestBody.setAgentid(agentId);
		textcardRequestBody.setTouser(users);
		Textcard textcard = new Textcard();
		textcard.setTitle(title);
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"gray\">")
		  .append(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()))
		  .append("</div>")
		  .append("<div class=\"normal\">")
		  .append("请点击查看按钮，进行确认操作！")
		  .append(ASSISTANT_NAME)
		  .append("很高兴为你服务！")
		  .append("</div>");
		textcard.setDescription(sb.toString());
		textcard.setUrl(url);
		textcard.setBtntxt("查看");
		textcardRequestBody.setTextcard(textcard);
		return wechatClient.sendTextcard(secret, textcardRequestBody);
	}
}
