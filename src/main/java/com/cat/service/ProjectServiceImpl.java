package com.cat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cat.mapper.ProjectMapper;
import com.cat.model.Project;

@Service("projectService")
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    public List<Project> listProject() {
        return projectMapper.listProject();
    }

    public String getNowDate() {
        return projectMapper.getNowDate();
    }
}
