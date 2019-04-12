package com.cat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.cat.model.Project;
import com.cat.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    
    @RequestMapping(value = "/search", method = {RequestMethod.POST, RequestMethod.GET})
    public String searchProject() {
        System.out.println("searchProject进去了");
        List<Project> projectList = projectService.listProject();
        System.out.println(JSON.toJSON(projectList));
        return JSON.toJSONString(projectList);
    }
    
    @RequestMapping(value = "/searchNowDate", method = {RequestMethod.GET})
    public String searchNowDate() {
        String nowDate = projectService.getNowDate();
        return nowDate;
    }
}
