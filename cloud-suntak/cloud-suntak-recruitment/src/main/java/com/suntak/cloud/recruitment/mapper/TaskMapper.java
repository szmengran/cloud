package com.suntak.cloud.recruitment.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.jdbc.SQL;

import com.suntak.cloud.recruitment.entity.T_hr_task;
import com.suntak.cloud.recruitment.entity.ext.T_hr_task_ext;
import com.szmengran.mybatis.utils.mapper.IMapper;

/** 
 * @Package com.suntak.cloud.recruitment.mapper 
 * @Description: 面试任务数据库操作
 * @date Jun 11, 2019 10:54:30 AM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
@Mapper
public interface TaskMapper<T> extends IMapper<T> {
    
    @Update("update t_hr_task set attribute1=#{attribute1}, attribute2=#{attribute2},"
            + " attribute3=#{attribute3}, attribute4=#{attribute4}, attribute5=#{attribute5},"
            + " attribute6=#{attribute6}, attribute7=#{attribute7}, attribute8=#{attribute8},"
            + " attribute9=#{attribute9}, attribute10=#{attribute10}, attribute11=#{attribute11},"
            + " attribute12=#{attribute12}, attribute13=#{attribute13}, attribute14=#{attribute14},"
            + " attribute15=#{attribute15}, agree=#{agree}, remark=#{remark}, handlercode=#{handlercode}, handlername=#{handlername},"
            + " status=0 where taskid=#{taskid}")
    int updateTask(T_hr_task task) throws Exception;
    
    @SelectProvider(type = SqlProvider.class, method = "findTask")
    List<T_hr_task_ext> findTask(@Param("roles") String roles, @Param("userid") String userid) throws Exception;
    
    class SqlProvider {
        public String findTask(Map<String, Object> param){
            String roles = (String)param.get("roles");
            return new SQL(){
                {
                    SELECT("a.*,c.name, b.subflowname, b.preflowid, b.url");
                    FROM("t_hr_task a ,t_hr_workflow_sub b, t_hr_workflow_main c");
                    WHERE("a.subflowid = b.subflowid");
                    WHERE("b.workflowid = c.workflowid");
                    WHERE("a.status=1");
                    WHERE("((assignrole in ("+roles+") and assign is null) or assign =#{userid})");
                }
            }.toString();
        }
    }
    @Select("select * from t_hr_task where applicantid=#{applicantid} and subflowid=#{subflowid} and status=0 order by createstamp desc")
    List<T_hr_task> findTaskByApplicantid(@Param("applicantid") String applicantid, @Param("subflowid") String subflowid) throws Exception;
}
