package com.zj.laboratory.service.impl;

import com.zj.laboratory.mapper.LwLevelMapper;
import com.zj.laboratory.pojo.LwLevel;
import com.zj.laboratory.service.LwLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LwLevelServiceImpl implements LwLevelService {
    @Autowired
    private LwLevelMapper lwLevelMapper;

    @Override
    public List<LwLevel> getList() {
        return lwLevelMapper.getList();
    }
}
