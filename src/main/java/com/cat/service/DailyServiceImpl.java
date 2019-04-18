package com.cat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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

    @Override
    public List<Daily> listMonthDaily(String userId, String month) {
        Map<String, String> params = new HashMap<String, String>(2);
        params.put("userId", userId);
        params.put("month", month);
        return dailyMapper.listDailyByMonthAndUserId(params);
    }
}
