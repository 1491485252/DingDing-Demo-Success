package com.cat.model;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 
 * @author Justsy
 * 
 */
public class DailyForm implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Daily> daList;

    public List<Daily> getDaList() {
        return daList;
    }

    public void setDaList(List<Daily> daList) {
        this.daList = daList;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
