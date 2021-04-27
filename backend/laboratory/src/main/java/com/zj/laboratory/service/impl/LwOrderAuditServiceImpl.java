package com.zj.laboratory.service.impl;

import com.zj.laboratory.mapper.LwOrderAuditMapper;
import com.zj.laboratory.service.LwOrderAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LwOrderAuditServiceImpl implements LwOrderAuditService {
    @Autowired
    private LwOrderAuditMapper lwOrderAuditMapper;
}
