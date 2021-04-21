package com.zj.laboratory.service.impl;

import com.zj.laboratory.mapper.LwReviewerTypeMapper;
import com.zj.laboratory.pojo.LwLevel;
import com.zj.laboratory.pojo.LwReviewerType;
import com.zj.laboratory.service.LwReviewerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LwReviewerTypeServiceImpl implements LwReviewerTypeService {
    @Autowired
    private LwReviewerTypeMapper lwReviewerTypeMapper;
    @Override
    public List<LwReviewerType> getList() {
        return lwReviewerTypeMapper.getList();
    }
}
