package com.zhilian.rxapi.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018-1-4.
 */

public class LeaveTodoBean implements Serializable {
    private static final long serialVersionUID = 1124030774575179982L;

    private List<TodoItemBean> list;

    public List<TodoItemBean> getList() {
        return list;
    }

    public void setList(List<TodoItemBean> list) {
        this.list = list;
    }
}
