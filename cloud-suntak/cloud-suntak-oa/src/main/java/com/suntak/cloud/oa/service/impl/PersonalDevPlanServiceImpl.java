package com.suntak.cloud.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suntak.cloud.oa.entity.Cux_oa_personal_dev_plan_v;
import com.suntak.cloud.oa.mapper.PersonalDevPlanMapper;
import com.suntak.cloud.oa.mapper.PersonalEduPlanMapper;
import com.suntak.cloud.oa.mapper.PersonalMajorPlanMapper;
import com.suntak.cloud.oa.service.OaService;

/** 
 * @Package com.suntak.cloud.oa.service.impl 
 * @Description: 学历提升计划服务
 * @date Mar 28, 2019 4:16:50 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Service("personalDevPlanService")
public class PersonalDevPlanServiceImpl implements OaService {
    
    @Autowired
    private PersonalDevPlanMapper personalDevPlanMapper;
    
    @Autowired
    private PersonalEduPlanMapper personalEduPlanMapper;
    
    @Autowired
    private PersonalMajorPlanMapper personalMajorPlanMapper;
    
    
    @Override
    public List<?> findByConditions() throws Exception {
        return personalDevPlanMapper.findInfoByConditions();
    }

    @Override
    public Object findById(String id) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        List<?> list = personalEduPlanMapper.findInfoByConditions(id);
        List<?> majorList = personalMajorPlanMapper.findInfoByConditions(id);
        Cux_oa_personal_dev_plan_v cux_oa_personal_dev_plan_v = new Cux_oa_personal_dev_plan_v();
        cux_oa_personal_dev_plan_v.setForm_id(id);
        cux_oa_personal_dev_plan_v = personalDevPlanMapper.findById(cux_oa_personal_dev_plan_v);
        map.put("edus", list);
        map.put("majors", majorList);
        map.put("plan", cux_oa_personal_dev_plan_v);
        return map;
    }

    @Override
    public Boolean updateById(String id) throws Exception {
        return personalDevPlanMapper.updateById(id) > 0;
    }

    @Override
    public Boolean signById(String id, String empno, String name) throws Exception {
        return personalDevPlanMapper.signById(id, empno, name) > 0;
    }

}
