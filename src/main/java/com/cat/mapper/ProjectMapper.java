package com.cat.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cat.model.Project;

@Mapper
public interface ProjectMapper {

    List<Project> listProject();

    String getNowDate();

}
