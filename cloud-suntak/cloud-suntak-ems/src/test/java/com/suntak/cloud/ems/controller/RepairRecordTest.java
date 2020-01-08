package com.suntak.cloud.ems.controller;

import org.junit.Test;

import com.google.gson.Gson;
import com.suntak.cloud.ems.entity.Ems_dm_repair_record;
import com.suntak.cloud.ems.entity.RepairRecordRequestBody;

/** 
 * @Package com.suntak.cloud.ems.controller 
 * @Description: TODO
 * @date Mar 19, 2019 2:35:25 PM 
 * @author <a href="mailto:android_li@sina.cn">Joe</a>
 */
public class RepairRecordTest {

    @Test
    public void testInsert() throws Exception {
//        String json = JwtUtil.parseToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ7XCJvcmdfaWRcIjowLFwiaWRcIjo2MTI0LFwibmFtZVwiOlwi5p2O6IyC5rqQXCIsXCJsb2dpbl9uYW1lXCI6XCIwMDYxMjRcIixcImVtcGxveWVyX2lkXCI6XCIwMDYxMjRcIn0ifQ.3QeqJENKFcP8EPoV2zv1zikEdgk7XQZoz_NH4vu8-mXCY45qMRqYwiUo8usYLdtI-DeMEejJyrzh67p_4Biowg");
//        System.out.println(json);
    	RepairRecordRequestBody repairRecordRequestBody = new RepairRecordRequestBody();
    	Ems_dm_repair_record Ems_dm_repair_record = new Ems_dm_repair_record();
    	repairRecordRequestBody.setRepairRecord(Ems_dm_repair_record);
    	Ems_dm_repair_record repairRecord = repairRecordRequestBody.getRepairRecord();
    	repairRecord.setApply_phone("15012794433");
    	System.out.println(new Gson().toJson(repairRecordRequestBody.getRepairRecord()));
    }
}
