package com.cat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cat.common.ArmyResult;
import com.cat.model.Project;
import com.cat.service.ProjectService;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    
    @RequestMapping(value = "/search", method = {RequestMethod.POST, RequestMethod.GET})
    public ArmyResult searchProject() {
        List<Project> projectList = projectService.listProject();
        return ArmyResult.of(projectList);
    }
    
    @RequestMapping(value = "/searchNowDate", method = {RequestMethod.GET})
    public ArmyResult searchNowDate() {
        String nowDate = projectService.getNowDate();
        return ArmyResult.of(nowDate);
    }
}
