package com.yx.model.Global;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class GridResponse<T> {
    public GridResponse(List<T> list){
        PageInfo<T> pageInfo = new PageInfo(list);
        this.data = pageInfo.getList();
        this.total = pageInfo.getTotal();
    }

    /// <summary> 记录总数 </summary>
    private long total;

    /// <summary> 数据源 </summary>
    private List<T> data;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
