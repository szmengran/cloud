package com.suntak.cloud.recruitment.controller;
/** 
 * @Package com.suntak.cloud.recruitment.controller 
 * @Description: OA对接
 * @date Jun 14, 2019 10:07:37 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;
import com.suntak.autotask.utils.OaConfigInfo;
import com.suntak.cloud.recruitment.entity.FileInfo;
import com.suntak.cloud.recruitment.entity.T_hr_attachment;
import com.suntak.cloud.recruitment.entity.T_hr_resume;
import com.suntak.cloud.recruitment.service.AttachmentService;
import com.suntak.cloud.recruitment.service.OaService;
import com.suntak.cloud.recruitment.service.ResumeService;
import com.suntak.exception.model.Response;

@RestController
@RequestMapping(path = "/api/v1/recruitment", produces = { "application/json" })
public class OaController {

    private static final String FILE_BOUNDARY = "-----";
    private static final Logger logger = LoggerFactory.getLogger(OaController.class);
    private static final ExecutorService executor = new ThreadPoolExecutor(20, 2000, 3L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
    
    @Autowired
    private OaService oaService;
    
    @Autowired
    private AttachmentService attachmentService;
    
    @Autowired
    private ResumeService resumeService;
    
    @Value("${cloud.environment.oa}")
    private String environment;
    
    @PostMapping("/uploadAvator/{applicantid}/{ownerid}")
    public Response uploadAvator(@RequestParam("file") MultipartFile file, @PathVariable("applicantid") String applicantid, @PathVariable("ownerid") String ownerid) throws Exception {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("请选择文件");
        }
        Future<T_hr_attachment> future = executor.submit(() -> {
            return attachmentService.findById(applicantid);
        });
        Response response = new Response();
        FileInfo fileInfo = upload(file, ownerid, "头像");
        
        T_hr_attachment attachment = future.get();
        if (attachment == null) {
            attachment = new T_hr_attachment();
            attachment.setApplicantid(applicantid);
            attachment.setAvatar(fileInfo.getFileUrl());
            attachmentService.insert(attachment);
        } else {
            attachment.setAvatar(fileInfo.getFileUrl());
            attachmentService.update(attachment);
        }
        response.setData(fileInfo);
        return response;
    }
    
    @PostMapping("/uploadIdcardpositive/{applicantid}/{ownerid}")
    public Response uploadIdcardpositive(@RequestParam("file") MultipartFile file, @PathVariable("applicantid") String applicantid, @PathVariable("ownerid") String ownerid) throws Exception {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("请选择文件");
        }
        Future<T_hr_attachment> future = executor.submit(() -> {
            return attachmentService.findById(applicantid);
        });
        Response response = new Response();
        FileInfo fileInfo = upload(file, ownerid, "身份证正面照");
        
        T_hr_attachment attachment = future.get();
        if (attachment == null) {
            attachment = new T_hr_attachment();
            attachment.setApplicantid(applicantid);
            attachment.setIdcardpositive(fileInfo.getFileUrl());
            attachmentService.insert(attachment);
        } else {
            attachment.setIdcardpositive(fileInfo.getFileUrl());
            attachmentService.update(attachment);
        }
        response.setData(fileInfo);
        return response;
    }
    
    @PostMapping("/uploadIdcardnegative/{applicantid}/{ownerid}")
    public Response uploadIdcardnegative(@RequestParam("file") MultipartFile file, @PathVariable("applicantid") String applicantid, @PathVariable("ownerid") String ownerid) throws Exception {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("请选择文件");
        }
        Future<T_hr_attachment> future = executor.submit(() -> {
            return attachmentService.findById(applicantid);
        });
        Response response = new Response();
        FileInfo fileInfo = upload(file, ownerid, "身份证反面照");
        
        T_hr_attachment attachment = future.get();
        if (attachment == null) {
            attachment = new T_hr_attachment();
            attachment.setApplicantid(applicantid);
            attachment.setIdcardnegative(fileInfo.getFileUrl());
            attachmentService.insert(attachment);
        } else {
            attachment.setIdcardnegative(fileInfo.getFileUrl());
            attachmentService.update(attachment);
        }
        response.setData(fileInfo);
        return response;
    }
    
    @PostMapping("/uploadDiploma/{applicantid}/{ownerid}")
    public Response uploadDiploma(@RequestParam("file") MultipartFile file, @PathVariable("applicantid") String applicantid, @PathVariable("ownerid") String ownerid) throws Exception {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("请选择文件");
        }
        Future<T_hr_attachment> future = executor.submit(() -> {
            return attachmentService.findById(applicantid);
        });
        Response response = new Response();
        FileInfo fileInfo = upload(file, ownerid, "学位证件照");
        
        T_hr_attachment attachment = future.get();
        if (attachment == null) {
            attachment = new T_hr_attachment();
            attachment.setApplicantid(applicantid);
            attachment.setDiploma(fileInfo.getFileUrl());
            attachmentService.insert(attachment);
        } else {
            attachment.setDiploma(fileInfo.getFileUrl());
            attachmentService.update(attachment);
        }
        response.setData(fileInfo);
        return response;
    }
    
    @PostMapping("/uploadOthercertificate1/{applicantid}/{ownerid}")
    public Response uploadOthercertificate1(@RequestParam("file") MultipartFile file, @PathVariable("applicantid") String applicantid, @PathVariable("ownerid") String ownerid) throws Exception {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("请选择文件");
        }
        Future<T_hr_attachment> future = executor.submit(() -> {
            return attachmentService.findById(applicantid);
        });
        Response response = new Response();
        FileInfo fileInfo = upload(file, ownerid, "其它证书照");
        
        T_hr_attachment attachment = future.get();
        if (attachment == null) {
            attachment = new T_hr_attachment();
            attachment.setApplicantid(applicantid);
            attachment.setOthercertificate1(fileInfo.getFileUrl());
            attachmentService.insert(attachment);
        } else {
            attachment.setOthercertificate1(fileInfo.getFileUrl());
            attachmentService.update(attachment);
        }
        response.setData(fileInfo);
        return response;
    }
    
    @PostMapping("/uploadOthercertificate2/{applicantid}/{ownerid}")
    public Response uploadOthercertificate2(@RequestParam("file") MultipartFile file, @PathVariable("applicantid") String applicantid, @PathVariable("ownerid") String ownerid) throws Exception {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("请选择文件");
        }
        Future<T_hr_attachment> future = executor.submit(() -> {
            return attachmentService.findById(applicantid);
        });
        Response response = new Response();
        FileInfo fileInfo = upload(file, ownerid, "其它证书照");
        
        T_hr_attachment attachment = future.get();
        if (attachment == null) {
            attachment = new T_hr_attachment();
            attachment.setApplicantid(applicantid);
            attachment.setOthercertificate2(fileInfo.getFileUrl());
            attachmentService.insert(attachment);
        } else {
            attachment.setOthercertificate2(fileInfo.getFileUrl());
            attachmentService.update(attachment);
        }
        response.setData(fileInfo);
        return response;
    }
    
    @PostMapping("/uploadResume/{applicantid}/{ownerid}")
    public Response uploadResume(@RequestParam("file") MultipartFile file, @PathVariable("applicantid") String applicantid, @PathVariable("ownerid") String ownerid) throws Exception {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("请选择文件");
        }
        Response response = new Response();
        FileInfo fileInfo = upload(file, ownerid, "简历照片");
        T_hr_resume resume = new T_hr_resume();
        resume.setApplicantid(applicantid);
        resume.setResume(fileInfo.getFileUrl());
        resumeService.insert(resume);
        response.setData(fileInfo);
        return response;
    }
    
    @DeleteMapping("/deleteResume/{applicantid}/{resume}")
    public Response deleteResume(@PathVariable("applicantid") String applicantid, @PathVariable("resume") String resume) throws Exception {
        resumeService.delete(applicantid, resume);
        return new Response();
    }
    
    @DeleteMapping("/attachment/{applicantid}/{type}")
    public Response deleteAttachment(@PathVariable("applicantid") String applicantid, @PathVariable("type") String type) throws Exception {
        T_hr_attachment attachment = attachmentService.findById(applicantid);
        if (attachment == null) {
            return new Response();
        }
        switch(type) {
        case "avatar": {
            attachment.setAvatar(null);
            attachmentService.update(attachment);
            break;
        }
        case "idcardpositive": {
            attachment.setIdcardpositive(null);
            attachmentService.update(attachment);
            break;
        }
        case "idcardnegative": {
            attachment.setIdcardnegative(null);
            attachmentService.update(attachment);
            break;
        }
        case "diploma": {
            attachment.setDiploma(null);
            attachmentService.update(attachment);
            break;
        }
        case "medicalreport": {
            attachment.setMedicalreport(null);
            attachmentService.update(attachment);
            break;
        }
        case "othercertificate1": {
            attachment.setOthercertificate1(null);
            attachmentService.update(attachment);
            break;
        }
        case "othercertificate2": {
            attachment.setOthercertificate2(null);
            attachmentService.update(attachment);
            break;
        }
    }
        return new Response();
    }
    
    private FileInfo upload(MultipartFile file, String ownerid, String name) throws Exception{
        FileInfo fileInfo = null;
        try {
            OaConfigInfo conf = new OaConfigInfo(environment);
            String loginName = oaService.findLoginNameByCode(ownerid);
            String token = getUserToken(conf, loginName);
            URL preUrl = null;
            preUrl = new URL(conf.getUrl()+"/seeyon/rest/attachment?token="+token+"&applicationCategory=0&extensions=&firstSave=true");
            // 设置请求头
            HttpURLConnection hc = (HttpURLConnection)preUrl.openConnection();
            hc.setDoOutput(true);
            hc.setUseCaches(false);
            hc.setRequestProperty("contentType", "charset=utf-8");
            hc.setRequestMethod("POST");
            hc.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + FILE_BOUNDARY);
            //
            DataOutputStream dos = new DataOutputStream(hc.getOutputStream());
            dos.write(getStartData(file, name));
            BufferedInputStream input = new BufferedInputStream(file.getInputStream());
            int data = 0;
            while((data = input.read()) != -1) {
                dos.write(data);
            }
            dos.write(("\r\n--" + FILE_BOUNDARY + "--\r\n").getBytes());
            dos.flush();
            dos.close();
            StringBuffer sb = new StringBuffer();
            InputStream is = hc.getInputStream();
            int ch;
            while((ch = is.read()) != -1) {
                sb.append((char)ch);
            }
            if(is != null)
                is.close();
            if(input != null)
                input.close();
            if(sb.toString() != null && !"".equals(sb.toString())) {
                logger.info("附件上传成功！！ID:", sb.toString());
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(sb.toString());
                JsonNode obj = jsonNode.get("atts").get(0);
                fileInfo = new FileInfo();
                fileInfo.setFilename(obj.get("filename").asText());
                fileInfo.setFileUrl(obj.get("fileUrl").asText());
                fileInfo.setSize(obj.get("size").asText());
                fileInfo.setId(obj.get("id").asText());
            } else {
                logger.error("附件上传失败！");
            }
        }catch(Exception e) {
            logger.error("附件上传失败！！错误信息：", e.getMessage());
        }
        return fileInfo;
    }
    
    private static String getUserToken(OaConfigInfo conf, String loginName) throws JsonProcessingException, IOException {
        // 取得指定服务主机的客户端管理器。
        CTPServiceClientManager clientManager = CTPServiceClientManager.getInstance(conf.getUrl());
        // 取得REST动态客户机。
        CTPRestClient client = clientManager.getRestClient();
        String jsonString = client.post("token", "{\"userName\":\"" + conf.getRestuser() + "\",\"password\":\""
                + conf.getRestpass() + "\",\"loginName\":\"" + loginName + "\"}", String.class);
        
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);
        
        return jsonNode.get("id").asText();
    }
    
    /**
     * @param file
     * @return
     */
    private static byte[] getStartData(MultipartFile file, String name) throws Exception {
        String arg[] = file.getOriginalFilename().split("\\.");
        String suffix = "";
        if (arg.length > 1) {
            suffix = arg[1];
        }
        String filename = name + "." + suffix;
        StringBuffer sb = new StringBuffer();
        sb.append("--");
        sb.append(FILE_BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data; \r\n name=\"1\"; filename=\"" + filename + "\"\r\n");
        sb.append("Content-Type: msoffice\r\n\r\n");
        return sb.toString().getBytes("UTF-8");
    }

    @GetMapping("/download/{fileid}/{ownerid}")
    public void download(HttpServletResponse response, @PathVariable("fileid") String fileid, @PathVariable("ownerid") String ownerid) throws Exception {
        OaConfigInfo conf = new OaConfigInfo(environment);
        String loginName = oaService.findLoginNameByCode(ownerid);
        String token = getUserToken(conf, loginName);
        URL preUrl = null;
        URLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            preUrl = new URL(conf.getUrl()+"/seeyon/rest/attachment/file/"+fileid+"?fileName=doc&token="+token);
            urlConnection = preUrl.openConnection();
            inputStream = urlConnection.getInputStream();
            int ch;
            while ((ch = inputStream.read()) != -1) {
                response.getWriter().write(ch);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (inputStream != null) inputStream.close();
        }
    }
}
