package com.yx.model.Global;

import com.github.pagehelper.PageHelper;

import java.util.*;

/// <summary> 数据列表页面请求数据 </summary>
public class GridRequest
{
    public GridRequest()
    {
        if (filter == null) filter = new Filter();
    }

    /// <summary> 待获取记录条数 </summary>
    private int take;

    /// <summary> 跳过记录条数 </summary>
    private int skip;

    /// <summary> 当前页码 </summary>
    private int page;

    /// <summary> 每页记录数 </summary>
    private int pageSize;

    /// <summary> 总记录数 </summary>
    private int total;

    /// <summary> 查询条件列表 </summary>
    private Filter filter;

    private Sort[] sort;

    private Sort sorted;

    /// <summary> 添加筛选条件 </summary>
    public void AddFilter(String key, Object value)
    {
        String strValue;
        if(value instanceof Enum)
        {
            strValue = Integer.toString(Integer.parseInt(value.toString()));
        }
        else
        {
            strValue = value.toString();
        }
        AddFilter(key, strValue);
    }

    /// <summary> 添加筛选条件 </summary>
    public void AddFilter(String key, String value)
    {
        if (filter == null || filter.filters == null || filter.filters.length == 0){

            filter = new Filter();
            filter.filters = new HashMap[1];
        }

        if (filter.filters[0] == null) filter.filters[0] = new HashMap<String, String>();
        filter.filters[0].put(key, value);
    }

    public Sort getSorted() {
        if (sort == null || sort.length == 0)
            return new Sort();
        return sort[0];
    }

    public void setSorted(Sort sorted) {
        this.sorted = sorted;
    }

    public int getTake() {
        return take;
    }

    public void setTake(int take) {
        this.take = take;
    }

    public int getSkip() {
        return skip;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setSort(Sort[] sort) {
        this.sort = sort;
    }

    public Sort[] getSort() {
        return sort;
    }

    public void setFilter(Filter filter){
        this.filter = filter;
    }

    public Filter getFilter(){
        return filter;
    }


    /// <summary> 筛选条件 </summary>
    public class Filter
    {
        /// <summary> 连接条件 </summary>
        private String logic;

        /// <summary> 查询条件列表 </summary>
        private HashMap<String, String>[] filters;

        /// <summary> 查询条件列表 </summary>
        private HashMap<String, String> filterList;

        public String getLogic() {
            return logic;
        }

        public void setLogic(String logic) {
            this.logic = logic;
        }

        public void setFilters(HashMap<String, String>[] filters) {
            this.filters = filters;
        }

        public HashMap<String, String>[] getFilters() {
            return filters;
        }

        public HashMap<String, String> getFilterList() {
            if (filters == null || filters.length == 0)
                return new HashMap<String, String>();

            return filters[0];
        }

        public void setFilterList(HashMap<String, String> filterList) {
            this.filterList = filterList;
        }
    }

    public class Sort
    {
        private String field;
        private String dir;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }
    }

}
