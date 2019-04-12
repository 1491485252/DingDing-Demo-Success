package com.cat.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author Justsy
 * 
 */
public class Daily implements Serializable {
    private static final long serialVersionUID = 1L;

    private String projectName;
    private String time;
    private String content;
    private String workHours;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public String getWorkHours() {
        return workHours;
    }

    public void setWorkHours(String workHours) {
        this.workHours = workHours;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
