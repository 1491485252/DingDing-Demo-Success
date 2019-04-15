package com.cat.common;

import java.io.Serializable;

public class ArmyEmptyRow implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer total = 30;
    private Object rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

}
