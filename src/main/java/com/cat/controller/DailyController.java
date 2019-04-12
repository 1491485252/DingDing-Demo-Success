package com.cat.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cat.model.Daily;
import com.cat.model.DailyForm;
import com.cat.service.DailyService;

@RestController
@RequestMapping("/daily")
public class DailyController {

    @Autowired
    private DailyService dailyService;
    
    @RequestMapping(value = "/insert", method = {RequestMethod.POST})
    public String insertDaily(@Valid @RequestBody DailyForm dailyForm, BindingResult result) {
        List<Daily> dailyList = dailyForm.getDaList();
        if(null == dailyList || dailyList.size() == 0) {
            return "ERROR";
        }
        for(int i=0, size = dailyList.size(); i< size ; i++) {
            System.out.println(dailyList.get(i));
        }
        dailyService.insertDaily(dailyList);
        return "OK";
    }
}
