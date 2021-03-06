package com.suntak.cloud.ehr.mapper;
/**
 * @Package com.szmengran.account.mapper
 * @Description: TODO
 * @date Nov 2, 2018 8:10:28 PM
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import com.suntak.cloud.ehr.entity.T_wechat_department;
import com.szmengran.mybatis.utils.mapper.IMapper;

@Mapper
public interface DepartmentMapper extends IMapper<T_wechat_department>{

    @Delete("delete from t_wechat_department")
    int deleteAll() throws Exception;
}
