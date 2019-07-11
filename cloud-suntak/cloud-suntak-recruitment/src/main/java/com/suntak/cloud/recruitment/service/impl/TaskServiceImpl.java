package com.suntak.cloud.recruitment.service.impl;

import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.axis2.AxisFault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.suntak.autotask.BPMService.BPMServiceStub;
import com.suntak.autotask.bean.OaFormXmlBean;
import com.suntak.autotask.utils.OaConfigInfo;
import com.suntak.autotask.utils.OaInterfaceUtil;
import com.suntak.autotask.utils.XmlUtil;
import com.suntak.cloud.recruitment.client.EhrClient;
import com.suntak.cloud.recruitment.entity.T_hr_applicant;
import com.suntak.cloud.recruitment.entity.T_hr_attachment;
import com.suntak.cloud.recruitment.entity.T_hr_educationhistory;
import com.suntak.cloud.recruitment.entity.T_hr_familymember;
import com.suntak.cloud.recruitment.entity.T_hr_resume;
import com.suntak.cloud.recruitment.entity.T_hr_task;
import com.suntak.cloud.recruitment.entity.T_hr_workflow_sub;
import com.suntak.cloud.recruitment.entity.T_hr_workhistory;
import com.suntak.cloud.recruitment.entity.ext.T_hr_task_ext;
import com.suntak.cloud.recruitment.mapper.ApplicantMapper;
import com.suntak.cloud.recruitment.mapper.EducationHistoryMapper;
import com.suntak.cloud.recruitment.mapper.FamilyMemberMapper;
import com.suntak.cloud.recruitment.mapper.TaskMapper;
import com.suntak.cloud.recruitment.mapper.WorkHistoryMapper;
import com.suntak.cloud.recruitment.mapper.WorkflowSubMapper;
import com.suntak.cloud.recruitment.service.AttachmentService;
import com.suntak.cloud.recruitment.service.OaService;
import com.suntak.cloud.recruitment.service.ResumeService;
import com.suntak.cloud.recruitment.service.TaskService;
import com.suntak.exception.model.Response;

/**
 * @Package com.suntak.cloud.recruitment.service.impl
 * @Description: 任务服务
 * @date 2018年8月22日 上午9:14:22
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service
public class TaskServiceImpl implements TaskService {

    private final static ExecutorService executor = new ThreadPoolExecutor(20, 200, 3L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());
    private final static Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    @Value("${cloud.environment.oa}")
    private String environment;
    
    @Autowired
    private TaskMapper<T_hr_task> taskMapper;

    @Autowired
    private WorkflowSubMapper<T_hr_workflow_sub> workflowSubMapper;

    @Autowired
    private ApplicantMapper applicantMapper;
    
    @Autowired
    private EhrClient ehrClient;
    
    @Autowired
    private ResumeService resumeService;
    
    @Autowired
    private OaService oaService;
    
    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private EducationHistoryMapper<T_hr_educationhistory> educationHistoryMapper;

    @Autowired
    private FamilyMemberMapper<T_hr_familymember> familyMemberMapper;

    @Autowired
    private WorkHistoryMapper<T_hr_workhistory> workHistoryMapper;

    @Override
    public void insert(T_hr_task t_hr_task) throws Exception {
        t_hr_task.setStatus((short) 1);
        t_hr_task.setCreatestamp(new Timestamp(System.currentTimeMillis()));
        t_hr_task.setUpdatestamp(new Timestamp(System.currentTimeMillis()));
        taskMapper.insert(t_hr_task);
    }

    @Transactional
    @Override
    public T_hr_workflow_sub handlerTask(T_hr_task t_hr_task, String userid) throws Exception {
        try {
            Future<T_hr_workflow_sub> future = executor.submit(() -> {
                List<T_hr_workflow_sub> list = workflowSubMapper.findWorkflowSub(t_hr_task.getSubflowid(),
                        t_hr_task.getAgree());
                if (list != null && list.size() > 0) {
                    return list.get(0);
                }
                return null;
            });
            Response response = ehrClient.getEhrUser(userid);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsBytes(response.getData()));
            String name = jsonNode.get("empname").asText();
            t_hr_task.setHandlercode(userid);
            t_hr_task.setHandlername(name);
            taskMapper.updateTask(t_hr_task);

            T_hr_workflow_sub t_hr_workflow_sub = future.get();
            if (t_hr_workflow_sub != null) {
                T_hr_task task = new T_hr_task();
                task.setAssignrole(t_hr_workflow_sub.getRole());
                task.setSubflowid(t_hr_workflow_sub.getSubflowid());
                task.setAssign(t_hr_task.getAssign());
                task.setApplicantid(t_hr_task.getApplicantid());
                task.setStatus((short) 1);
                task.setCreatestamp(new Timestamp(System.currentTimeMillis()));
                task.setUpdatestamp(new Timestamp(System.currentTimeMillis()));
                taskMapper.insert(task);
            } else {
                if ("1".equals(t_hr_task.getAgree())) {
                    launchForm(t_hr_task.getApplicantid(), userid);
                }
            }

            return t_hr_workflow_sub;
        } catch (DuplicateKeyException e) {
            throw new Exception("该任务已经处理，请勿重复处理！");
        } catch (Exception e) {
            throw e;
        }
    }

    private long[] getAttachment(T_hr_attachment t_hr_attachment, List<T_hr_resume> resumes) throws Exception{
        int len = 6;
        if (resumes != null && resumes.size() > 0) {
            len = 6 + resumes.size();
        }
        long[] attactments = new long[len];
        String avatar = t_hr_attachment.getAvatar();
        String idcardpositive = t_hr_attachment.getIdcardpositive();
        String idcardnegative = t_hr_attachment.getIdcardnegative();
        String diploma = t_hr_attachment.getDiploma();
        String othercertificate1 = t_hr_attachment.getOthercertificate1();
        String othercertificate2 = t_hr_attachment.getOthercertificate2();
        attactments[0] = avatar != null ? Long.parseLong(avatar) : 0;
        attactments[1] = idcardpositive != null ? Long.parseLong(idcardpositive) : 0;
        attactments[2] = idcardnegative != null ? Long.parseLong(idcardnegative) : 0;
        attactments[3] = diploma != null ? Long.parseLong(diploma) : 0;
        attactments[4] = othercertificate1 != null ? Long.parseLong(othercertificate1) : 0;
        attactments[5] = othercertificate2 != null ? Long.parseLong(othercertificate2) : 0;
        
        int index = 6;
        for (T_hr_resume t_hr_resume: resumes) {
            String resumeid = t_hr_resume.getResume();
            attactments[index++] = resumeid != null ? Long.parseLong(resumeid) : 0;
        }
        return attactments;
    }
    @Override
    public void launchForm(String applicantid, String empcode) throws Exception {
        Future<T_hr_applicant> applicant = executor.submit(() -> {
            T_hr_applicant t_hr_applicant = new T_hr_applicant();
            t_hr_applicant.setApplicantid(applicantid);
            return applicantMapper.findById(t_hr_applicant);
        });
        Future<List<T_hr_educationhistory>> educationhistory = executor.submit(() -> {
            return educationHistoryMapper.findByApplicantid(applicantid);
        });
        Future<List<T_hr_familymember>> familymembers = executor.submit(() -> {
            return familyMemberMapper.findByApplicantid(applicantid);
        });
        Future<List<T_hr_workhistory>> workhistorys = executor.submit(() -> {
            return workHistoryMapper.findByApplicantid(applicantid);
        });
        Future<List<T_hr_resume>> resumeFuture = executor.submit(() -> {
            return resumeService.findByApplicantid(applicantid);
        });
        Future<T_hr_attachment> attachmentFuture = executor.submit(() -> {
            return attachmentService.findById(applicantid);
        });
        Future<String> loginName = executor.submit(() -> {
            return oaService.findLoginNameByCode(empcode);
        });
        Future<T_hr_task> firstViewFuture = executor.submit(() -> {
            List<T_hr_task> list = taskMapper.findTaskByApplicantid(applicantid, "1");
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
            return null;
        });
        Future<T_hr_task> secondViewFuture = executor.submit(() -> {
            List<T_hr_task> list = taskMapper.findTaskByApplicantid(applicantid, "2");
            if (list != null && list.size() > 0) {
                return list.get(0);
            }
            return null;
        });
        OaFormXmlBean oaForm = new OaFormXmlBean();
        oaForm.setTableName("formmain_6902");
        T_hr_applicant t_hr_applicant = applicant.get();
        T_hr_task firstTask = firstViewFuture.get();
        oaForm.setTableHeaderDataMap(genTableHeaderDataMap(t_hr_applicant, firstTask,
                secondViewFuture.get(), educationhistory.get()));
        oaForm.setTableLinesDataList(
                genTableLinesDataList(educationhistory.get(), familymembers.get(), workhistorys.get()));

        String data = getOaFormXmlString(oaForm);
        // 连接的环境信息
        try {
            OaConfigInfo conf = new OaConfigInfo(environment);

            String token = OaInterfaceUtil.getOaToken(conf);
            long[] attactments = getAttachment(attachmentFuture.get(), resumeFuture.get());
            
            // 发起流程表单
            BPMServiceStub bpmServiceStub = new BPMServiceStub(conf);// new BPMServiceStub();
            BPMServiceStub.LaunchFormCollaboration launchFormCollaboration = new BPMServiceStub.LaunchFormCollaboration();
            launchFormCollaboration.setToken(token);
            launchFormCollaboration.setSenderLoginName(loginName.get()); // 发起者的登录名（登录协同的登录名）
            launchFormCollaboration.setTemplateCode("OA_WXwork_01"); // 模板编号
            launchFormCollaboration.setSubject(t_hr_applicant.getName()+"-"+firstTask.getAttribute14()+"-员工履历、面试评价及录用审批表"); // 协同的标题
            launchFormCollaboration.setData(data); // XML格式的表单数据
            launchFormCollaboration.setAttachments(attactments);
            launchFormCollaboration.setParam("1");
            launchFormCollaboration.setRelateDoc("");

            BPMServiceStub.LaunchFormCollaborationResponse launchFormCollaborationResp = bpmServiceStub
                    .launchFormCollaboration(launchFormCollaboration);
            BPMServiceStub.ServiceResponse serviceResp = launchFormCollaborationResp.get_return();
            Long errorNumber = serviceResp.getErrorNumber(); // 错误代码
            String errorMessage = serviceResp.getErrorMessage(); // 错误消息
            Long processId = serviceResp.getResult(); // 流程ID

            logger.info("错误代码：", errorNumber, "错误消息：", errorMessage, "流程ID：", processId);
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

    private String getSex(Integer sex) {
        if (sex == 1) {
            return "4291749513415969290"; // 男
        }
        return "-7563197266539297658"; // 女
    }
    
    private String getMedicalhistory(Integer medicalhistory) {
        if (medicalhistory == 1) {
            return "-3620634840999376943";
        }
        return "-3451440124422436818";
    }
    
    private String getCrimehistory(Integer crimehistory) {
        if (crimehistory == 1) {
            return "-3620634840999376943";
        }
        return "-3451440124422436818";
    }
    
    private String getPregnancy(Integer pregnancy) {
        if (pregnancy == 1) {
            return "-3620634840999376943"; 
        }
        return "-3451440124422436818";
    }

    /**
     * 
     * @param applicant
     * @param firstViewTask
     * @param secondViewTask
     * @return
     * @return: Map<String,String>
     * @throws ParseException 
     * @throws @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    private Map<String, String> genTableHeaderDataMap(T_hr_applicant applicant, T_hr_task firstViewTask,
            T_hr_task secondViewTask, List<T_hr_educationhistory> educationhistorys) throws ParseException {
        T_hr_educationhistory educationhistory = educationhistorys.get(0);
        Map<String, String> tableHeaderDataMap = new HashMap<String, String>();
        tableHeaderDataMap.put("公司logo", ""); // 公司logo
        tableHeaderDataMap.put("所属公司", firstViewTask.getAttribute14()); // 所属公司
        tableHeaderDataMap.put("编号", ""); // 编号
        tableHeaderDataMap.put("姓名", applicant.getName()); // 姓名
        tableHeaderDataMap.put("曾用名", applicant.getFormername()); // 曾用名
        tableHeaderDataMap.put("性别", getSex(applicant.getSex())); // 性别
        tableHeaderDataMap.put("出生日期", new SimpleDateFormat("yyyy-MM-dd").format(applicant.getBirthday())); // 出生日期
        tableHeaderDataMap.put("身份证号", applicant.getIdcard()); // 身份证号
        tableHeaderDataMap.put("籍贯", applicant.getNativeplace()); // 籍贯
        tableHeaderDataMap.put("民族", applicant.getNation()); // 民族
        tableHeaderDataMap.put("电话", applicant.getPhone()); // 电话
//        tableHeaderDataMap.put("未婚", "0"); // 未婚
//        tableHeaderDataMap.put("已婚", "0"); // 已婚
//        tableHeaderDataMap.put("离婚", "0"); // 离婚
//        tableHeaderDataMap.put("丧偶", "0"); // 丧偶
//        tableHeaderDataMap.put("党员", "0"); // 党员
//        tableHeaderDataMap.put("团员", "0"); // 团员
//        tableHeaderDataMap.put("群众", "0"); // 群众
        tableHeaderDataMap.put("邮箱", applicant.getEmail()); // 邮箱
//        tableHeaderDataMap.put("深户", "0"); // 深户
//        tableHeaderDataMap.put("非农户", "0"); // 非农户
//        tableHeaderDataMap.put("农村户", "0"); // 农村户
        tableHeaderDataMap.put("学历", educationhistory.getCertificate()); // 学历
        tableHeaderDataMap.put("专业", educationhistory.getProfession()); // 专业
        tableHeaderDataMap.put("户籍所在地", applicant.getNativeplace()); // 户籍所在地
        tableHeaderDataMap.put("目前住址", applicant.getAddress()); // 目前住址
        tableHeaderDataMap.put("身高", applicant.getHeight() == null ? "" : applicant.getHeight() + ""); // 身高
        tableHeaderDataMap.put("体重", applicant.getWeight() == null ? "" : applicant.getWeight() + ""); // 体重
        tableHeaderDataMap.put("视力", applicant.getLeftvision() + "|" + applicant.getRightvision()); // 视力
        tableHeaderDataMap.put("有无职业病史", getMedicalhistory(applicant.getMedicalhistory())); // 有无职业病史
        tableHeaderDataMap.put("有无犯罪史", getCrimehistory(applicant.getCrimehistory())); // 有无犯罪史
        tableHeaderDataMap.put("目前有无怀孕", getPregnancy(applicant.getPregnancy())); // 目前有无怀孕
        tableHeaderDataMap.put("职业病史说明", applicant.getMedicalhistorydesc()); // 职业病史说明
        tableHeaderDataMap.put("普通话", applicant.getMandarin() == null ? "0" : "1"); // 普通话
        tableHeaderDataMap.put("英语级", applicant.getEnglish() == null ? "0" : "1"); // 英语级
        tableHeaderDataMap.put("日语级", applicant.getJapanese() == null ? "0" : "1"); // 日语级
        tableHeaderDataMap.put("其它", applicant.getOther() == null ? "0" : "1"); // 其它
        tableHeaderDataMap.put("其他语言能力", ""); // 其他语言能力
        tableHeaderDataMap.put("技术职称或职业资格", applicant.getJobtitle()); // 技术职称或职业资格
        tableHeaderDataMap.put("个人特长或爱好", applicant.getHobby()); // 个人特长或爱好
        tableHeaderDataMap.put("紧急联系人姓名", applicant.getContactname()); // 紧急联系人姓名
        tableHeaderDataMap.put("紧急联系人与本人关系", applicant.getContactrelationship()); // 紧急联系人与本人关系
        tableHeaderDataMap.put("紧急联系人工作单位或部门", applicant.getContactcompany()); // 紧急联系人工作单位或部门
        tableHeaderDataMap.put("紧急联系人职务", applicant.getContactposition()); // 紧急联系人职务
        tableHeaderDataMap.put("紧急联系人电话", applicant.getContactphone()); // 紧急联系人电话
        tableHeaderDataMap.put("亲属在本公司是否",
                applicant.getRelativesname() == null ? "2958853362500325158" : "-5805300948442788403"); // 亲属在本公司是否
        tableHeaderDataMap.put("亲属在本公司姓名", applicant.getRelativesname()); // 亲属在本公司姓名
        tableHeaderDataMap.put("亲属在本公司部门", applicant.getRelativesdepartment()); // 亲属在本公司部门
        tableHeaderDataMap.put("亲属在本公司职位", applicant.getRelativesposition()); // 亲属在本公司职位
        tableHeaderDataMap.put("亲属在本公司关系", applicant.getRelativesrelationship()); // 亲属在本公司关系
        tableHeaderDataMap.put("应聘人签名", ""); // 应聘人签名
        tableHeaderDataMap.put("填表日期", new SimpleDateFormat("yyyy-MM-dd").format(applicant.getCreatestamp())); // 填表日期
        tableHeaderDataMap.put("普通话级别", applicant.getMandarin()); // 普通话级别
        tableHeaderDataMap.put("英语级级别", applicant.getEnglish()); // 英语级级别
        tableHeaderDataMap.put("日语级级别", applicant.getJapanese()); // 日语级级别
        tableHeaderDataMap.put("初试评价1", firstViewTask.getAttribute1()); // 初试评价1
        tableHeaderDataMap.put("初试评价2", firstViewTask.getAttribute2()); // 初试评价2
        tableHeaderDataMap.put("初试评价3", firstViewTask.getAttribute3()); // 初试评价3
        tableHeaderDataMap.put("初试评价4", firstViewTask.getAttribute4()); // 初试评价4
        tableHeaderDataMap.put("初试评价5", firstViewTask.getAttribute5()); // 初试评价5
        tableHeaderDataMap.put("初试评价6", firstViewTask.getAttribute6()); // 初试评价6
        tableHeaderDataMap.put("初试评价7", firstViewTask.getAttribute7()); // 初试评价7
        tableHeaderDataMap.put("初试评价8", firstViewTask.getAttribute8()); // 初试评价8
        tableHeaderDataMap.put("身份证", "true".equals(firstViewTask.getAttribute9()) ? "1" : "0"); // 身份证
        tableHeaderDataMap.put("毕业证", "true".equals(firstViewTask.getAttribute10()) ? "1" : "0"); // 毕业证
        tableHeaderDataMap.put("学位证", "true".equals(firstViewTask.getAttribute11()) ? "1" : "0"); // 学位证
        tableHeaderDataMap.put("其它证书", "true".equals(firstViewTask.getAttribute12()) ? "1" : "0"); // 其它证书
        tableHeaderDataMap.put("其它证书名称", ""); // 其它证书名称
        tableHeaderDataMap.put("可到职日期", new SimpleDateFormat("yyyy-MM-dd").format(new Date(new SimpleDateFormat("yyyy-MM-dd").parse(firstViewTask.getAttribute13()).getTime()))); // 可到职日期
        tableHeaderDataMap.put("初试综合评价", firstViewTask.getRemark()); // 初试综合评价
        tableHeaderDataMap.put("初试结果", "6431127814935300153"); // 初试结果，默认是通过
        tableHeaderDataMap.put("面试人", firstViewTask.getAssign()); // 面试人
        tableHeaderDataMap.put("面试日期", new SimpleDateFormat("yyyy-MM-dd").format(firstViewTask.getCreatestamp())); // 面试日期

        tableHeaderDataMap.put("复试评价1", secondViewTask.getAttribute1()); // field0074
        tableHeaderDataMap.put("复试评价2", secondViewTask.getAttribute2()); // field0075
        tableHeaderDataMap.put("复试评价3", secondViewTask.getAttribute3()); // field0076
        tableHeaderDataMap.put("复试评价4", secondViewTask.getAttribute4()); // field0077
        tableHeaderDataMap.put("复试评价5", secondViewTask.getAttribute5()); // field0078
        tableHeaderDataMap.put("复试综合评价", secondViewTask.getRemark()); // field0079
        tableHeaderDataMap.put("是否录用", "-976858712531973633"); // 是否录用
//        tableHeaderDataMap.put("不录用意见", ""); //    不录用意见
//        tableHeaderDataMap.put("其它意见", ""); //    其它意见
//        tableHeaderDataMap.put("意见", ""); //    意见
        tableHeaderDataMap.put("复试人", secondViewTask.getHandlername()); //    复试人
        tableHeaderDataMap.put("复试日期", new SimpleDateFormat("yyyy-MM-dd").format(firstViewTask.getCreatestamp())); // 复试日期
//        tableHeaderDataMap.put("录用公司", ""); //    录用公司
//        tableHeaderDataMap.put("录用部门", ""); //    录用部门
//        tableHeaderDataMap.put("录用课别", ""); //    录用课别
//        tableHeaderDataMap.put("职务工种", ""); //    职务工种
//        tableHeaderDataMap.put("是否录用", ""); //    是否录用
//        tableHeaderDataMap.put("职级", ""); //    职级
//        tableHeaderDataMap.put("包月固定工资", ""); //    包月固定工资
//        tableHeaderDataMap.put("基本工资制", ""); //    基本工资制
//        tableHeaderDataMap.put("底薪", ""); //    底薪
//        tableHeaderDataMap.put("岗位津贴", ""); //    岗位津贴
//        tableHeaderDataMap.put("技能工资", ""); //    技能工资
//        tableHeaderDataMap.put("部门主管", ""); //    部门主管
//        tableHeaderDataMap.put("部门经理", ""); //    部门经理
//        tableHeaderDataMap.put("部门总监", ""); //    部门总监
//        tableHeaderDataMap.put("工厂总经理", ""); //    工厂总经理
//        tableHeaderDataMap.put("副总裁", ""); //    副总裁
//        tableHeaderDataMap.put("高级副总裁", ""); //    高级副总裁
//        tableHeaderDataMap.put("集团人力资源部", ""); //    集团人力资源部
//        tableHeaderDataMap.put("董事长", ""); //    董事长
        tableHeaderDataMap.put("户口类型", applicant.getResidencetype()); //    户口类型
        tableHeaderDataMap.put("婚姻状况", applicant.getMarrystatus()); //    户口类型
        tableHeaderDataMap.put("政治面貌", applicant.getPoliticalstatus()); //    户口类型
        return tableHeaderDataMap;
    }

    private List<List<Map<String, String>>> genTableLinesDataList(List<T_hr_educationhistory> educationhistorys,
            List<T_hr_familymember> familymembers, List<T_hr_workhistory> workhistorys) {
        List<List<Map<String, String>>> list = new ArrayList<List<Map<String, String>>>();
        List<Map<String, String>> educationhistoryList = new ArrayList<>();
        for (T_hr_educationhistory t_hr_educationhistory : educationhistorys) {
            Map<String, String> map = new HashMap<>();
            map.put("教育经历起止时间", new SimpleDateFormat("yyyy-MM-dd").format(t_hr_educationhistory.getStarttime()) + "~"
                    + new SimpleDateFormat("yyyy-MM-dd").format(t_hr_educationhistory.getEndtime()));
            map.put("教育经历毕业学校或培训机构", t_hr_educationhistory.getSchool());
            map.put("教育经历学习专业或培训项目", t_hr_educationhistory.getProfession());
            map.put("教育经历获得证书", t_hr_educationhistory.getCertificate());
            educationhistoryList.add(map);
        }
        List<Map<String, String>> workhistorysList = new ArrayList<>();
        for (T_hr_workhistory workhistory : workhistorys) {
            Map<String, String> map = new HashMap<>();
            map.put("工作经历起止时间", new SimpleDateFormat("yyyy-MM-dd").format(workhistory.getStarttime()) + "~"
                    + new SimpleDateFormat("yyyy-MM-dd").format(workhistory.getEndtime()));
            map.put("工作经历工作单位", workhistory.getCompany());
            map.put("工作经历部门", workhistory.getDepartment());
            map.put("工作经历职位", workhistory.getPosition());
            map.put("工作经历薪资", workhistory.getSalary() + "");
            map.put("工作经历离职原因", workhistory.getResignationreason());
            workhistorysList.add(map);
        }
        List<Map<String, String>> familymembersList = new ArrayList<>();
        for (T_hr_familymember familymember : familymembers) {
            Map<String, String> map = new HashMap<>();
            map.put("家庭成员姓名", familymember.getName());
            map.put("家庭成员与本人关系", familymember.getRelationship());
            map.put("家庭成员职业", familymember.getCareer());
            map.put("家庭成员联络地址", familymember.getAddress());
            map.put("家庭成员电话", familymember.getPhone());
            familymembersList.add(map);
        }
        list.add(educationhistoryList);
        list.add(workhistorysList);
        list.add(familymembersList);
        return list;
    }

    private String getOaFormXmlString(OaFormXmlBean oaForm) throws Exception {
        String formXmlString = "";
        Document document = XmlUtil.getDocumentObj();// 获取document对象
        Element columentEle = null;
        Element valueEle = null;
        Element rowEle = null;

        Element formExportEle = document.createElement("formExport");// 构建formExport元素
        formExportEle.setAttribute("version", oaForm.getFormExportVersion());
        document.appendChild(formExportEle);

        Element summaryEle = document.createElement("summary");// 构建summary元素，包含id和name两个属性
        summaryEle.setAttribute("id", "");
        summaryEle.setAttribute("name", oaForm.getTableName());
        formExportEle.appendChild(summaryEle);

        Element headerValuesEle = document.createElement("values");// 构建表数据values元素
        formExportEle.appendChild(headerValuesEle);

        // 构建表数据
        if (oaForm.getTableHeaderDataMap() != null && oaForm.getTableHeaderDataMap().size() != 0) {
            for (Entry<String, String> entry : oaForm.getTableHeaderDataMap().entrySet()) {
                columentEle = document.createElement("column");
                columentEle.setAttribute("name", entry.getKey());
                headerValuesEle.appendChild(columentEle);

                valueEle = document.createElement("value");
                valueEle.setTextContent(entry.getValue());
                columentEle.appendChild(valueEle);
            }
        }

        Element subFormsEle = document.createElement("subForms");// 构建标识子表单（重复表）元素
        formExportEle.appendChild(subFormsEle);

        Element subFormEle = document.createElement("subForm");// 构建标识单个重复表元素
        subFormsEle.appendChild(subFormEle);

        Element linesValuesEle = document.createElement("values");// 构建重复表values元素
        subFormEle.appendChild(linesValuesEle);

        // 构建重复表数据
        if (oaForm.getTableLinesDataList() != null && oaForm.getTableLinesDataList().size() != 0) {
            for (List<Map<String, String>> list : oaForm.getTableLinesDataList()) {
                for (Map<String, String> subMap : list) {
                    rowEle = document.createElement("row");
                    linesValuesEle.appendChild(rowEle);
                    for (Entry<String, String> entry : subMap.entrySet()) {
                        columentEle = document.createElement("column");
                        columentEle.setAttribute("name", entry.getKey());
                        rowEle.appendChild(columentEle);

                        valueEle = document.createElement("value");
                        valueEle.setTextContent(entry.getValue());
                        columentEle.appendChild(valueEle);
                    }
                }
            }

        }

        formXmlString = XmlUtil.transferDocument2XmlString(document);

        return formXmlString;
    }

    @Override
    public List<T_hr_task_ext> find(String[] roles, String userid) throws Exception {
        StringBuilder strRoles = new StringBuilder();
        if (roles != null) {
            for (String role : roles) {
                strRoles.append(",'").append(role).append("'");
            }
        } else {
            strRoles.append(",''");
        }
        return taskMapper.findTask(strRoles.substring(1), userid);
    }

}
