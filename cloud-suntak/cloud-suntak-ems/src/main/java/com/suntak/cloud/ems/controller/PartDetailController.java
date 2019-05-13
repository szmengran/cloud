package com.suntak.cloud.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.suntak.admin.user.exception.BusinessException;
import com.suntak.cloud.ems.client.MicroserviceClient;
import com.suntak.cloud.ems.entity.ext.PartDetailRequest;
import com.suntak.cloud.ems.service.PartDetailService;
import com.suntak.ehr.entity.EhrUser;
import com.suntak.exception.model.Response;
import com.suntak.utils.JwtUtil;

import io.swagger.annotations.Api;

/** 
 * @Package com.suntak.cloud.ems.controller 
 * @Description: 配件明细信息操作API
 * @date Mar 25, 2019 3:14:51 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Api("配件明细信息操作API")
@RestController
@RequestMapping("/api/v1/ems")
public class PartDetailController {
    
    @Autowired
    private PartDetailService partDetailService;

    @Autowired
    private MicroserviceClient microserviceClient;
    
    /**
     * 配件信息查找
     * @param partDetailRequest
     * @return
     * @throws Exception      
     * @return: Response      
     * @throws   
     * @author <a href="mailto:android_li@sina.cn">Joe</a>
     */
    @PostMapping("/part/{token}")
    public Response findPartDetailInfo(@PathVariable("token") String token, @RequestBody PartDetailRequest partDetailRequest) throws Exception {
        String userJson = JwtUtil.parseToken(token);
        if (userJson == null) {
            throw new BusinessException(10007001);
        }
        EhrUser ehrUser = new Gson().fromJson(userJson, EhrUser.class);
        Integer org_id = null;
        if (!"0012".equals(ehrUser.getCompanycode())) {
            Response companyResponse = microserviceClient.getOrgIdByCompanyCode(ehrUser.getCompanycode()); 
            org_id = (Integer)companyResponse.getData();
        }
        Integer pageNum = partDetailRequest.getPageNum();
        Integer pageSize = partDetailRequest.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
            pageSize = 10;
        }
        String keyword = partDetailRequest.getKeyword();
//        Integer org_id = partDetailRequest.getOrg_id();
        PageHelper.startPage(pageNum, pageSize, "part_no desc");
        Response response = new Response();
        response.setData(partDetailService.findPartInfo(keyword, org_id));
        return response;
    }
}
