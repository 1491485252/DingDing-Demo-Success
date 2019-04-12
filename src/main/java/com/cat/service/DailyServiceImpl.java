package com.cat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.mapper.DailyMapper;
import com.cat.model.Daily;

@Service("dailyService")
public class DailyServiceImpl implements DailyService {

    @Autowired
    private DailyMapper dailyMapper;

    public void insertDaily(List<Daily> dailyList) {
        dailyMapper.insertDaily(dailyList);
        
    }

}
