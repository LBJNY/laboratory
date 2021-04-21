package com.zj.laboratory.service;

import com.zj.laboratory.pojo.LwLevel;
import com.zj.laboratory.pojo.LwReviewerType;

import java.util.List;

public interface LwReviewerTypeService {
    /**
     * 获取列表
     * @return
     */
    List<LwReviewerType> getList();
}
