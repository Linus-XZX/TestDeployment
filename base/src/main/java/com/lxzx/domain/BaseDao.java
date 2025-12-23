package com.lxzx.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Sort;

public class BaseDao implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sortName = "id";
    private String orderBy = "DESC";
    private List<Map<String, String>> multipleSort = null;

    private Sort.Direction sortDirection(String orderBy){
        if ("ASC".equals(orderBy) || "asc".equals(orderBy)) {
            return Sort.Direction.ASC;
        }
        return Sort.Direction.DESC;
    }

    private String sortName(){
        if (sortName.isEmpty()){
            return "id";
        }
        return sortName;
    }

    //获取排序信息
    public Sort getSort() {
        if (multipleSort.isEmpty() || multipleSort.get(0).isEmpty()) {
            return Sort.by(sortDirection(orderBy), sortName());
        } else {
            List<Sort.Order> sortList =new ArrayList<>();
            for (Map<String, String> sortInfo : multipleSort) {
                sortList.add(new Sort.Order(sortDirection(sortInfo.get("orderBy")), sortInfo.get("sortName")));
            }
            return Sort.by(sortList);
        }
    }
}
