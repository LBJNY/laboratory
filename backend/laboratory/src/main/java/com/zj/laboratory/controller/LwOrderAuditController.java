package com.zj.laboratory.controller;

import com.zj.laboratory.service.LwOrderAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("audit")
public class LwOrderAuditController {
    @Autowired
    private LwOrderAuditService lwOrderAuditService;
}
