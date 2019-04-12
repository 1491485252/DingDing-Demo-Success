package com.cat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cat.model.Daily;

@Mapper
public interface DailyMapper {

    void insertDaily(List<Daily> dailyList);

}
