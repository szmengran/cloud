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
import com.suntak.cloud.ems.entity.ext.Oz_org_userinfo_ext;
import com.suntak.cloud.ems.entity.ext.PartDetailRequest;
import com.suntak.cloud.ems.service.PartDetailService;
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
        Oz_org_userinfo_ext userinfo = new Gson().fromJson(userJson, Oz_org_userinfo_ext.class);
        Integer pageNum = partDetailRequest.getPageNum();
        Integer pageSize = partDetailRequest.getPageSize();
        if (pageNum == null) {
            pageNum = 1;
            pageSize = 10;
        }
        String keyword = partDetailRequest.getKeyword();
        PageHelper.startPage(pageNum, pageSize, "part_no desc");
        Response response = new Response();
        response.setData(partDetailService.findPartInfo(keyword, userinfo.getOrg_id()));
        return response;
    }
}
