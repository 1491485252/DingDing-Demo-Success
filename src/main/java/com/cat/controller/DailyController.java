package com.cat.controller;


import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cat.common.ArmyResult;
import com.cat.model.Daily;
import com.cat.model.DailyForm;
import com.cat.service.DailyService;
import com.dingtalk.api.response.OapiUserListbypageResponse.Userlist;
import com.util.PushMessageUtil;
import com.util.UserDepartUtils;

@RestController
@RequestMapping("/daily")
public class DailyController {

    @Autowired
    private DailyService dailyService;
    
    @RequestMapping(value = "/insert", method = {RequestMethod.POST})
    public ArmyResult insertDaily(@Valid @RequestBody DailyForm dailyForm, BindingResult result) {
        List<Daily> dailyList = dailyForm.getDaList();
        if(null == dailyList || dailyList.size() == 0) {
            return ArmyResult.error("添加失败");
        }
        dailyService.insertDaily(dailyList);
        // 推送消息给领导
        PushMessageUtil.pushMessage(dailyForm.getUserId(), JSONObject.toJSONString(dailyForm));
        return ArmyResult.ok("添加成功");
    }

}
