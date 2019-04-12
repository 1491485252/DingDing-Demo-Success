package com.cat.service;

import java.util.List;

import com.cat.model.Project;

public interface ProjectService {

    List<Project> listProject();

    String getNowDate();

}
