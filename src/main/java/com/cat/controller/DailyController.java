package com.cat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.cat.common.ArmyResult;
import com.cat.model.Daily;
import com.cat.model.DailyForm;
import com.cat.service.DailyService;
import com.util.PushMessageUtil;

@RestController
@RequestMapping("/daily")
public class DailyController {

    @Autowired
    private DailyService dailyService;

    @RequestMapping(value = "/insert", method = { RequestMethod.POST })
    public ArmyResult insertDaily(@Valid @RequestBody DailyForm dailyForm, BindingResult result) {
        List<Daily> dailyList = dailyForm.getDaList();
        if (null == dailyList || dailyList.size() == 0) {
            return ArmyResult.error("添加失败");
        }
        dailyService.insertDaily(dailyList);
        // 推送消息给领导
        PushMessageUtil.pushMessage(dailyForm.getUserId(), dailyForm);
        return ArmyResult.ok("添加成功");
    }

    @RequestMapping(value = "/list/{userId}/{month}", method = { RequestMethod.GET })
    public ArmyResult insertDaily(@PathVariable("userId") String userId, @PathVariable("month") String month) {
        return ArmyResult.of(dailyService.listMonthDaily(userId,month));
    }

}
