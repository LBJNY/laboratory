package com.zj.laboratory.service.impl;

import com.zj.laboratory.enums.StateEnums;
import com.zj.laboratory.mapper.LwOrderAuditMapper;
import com.zj.laboratory.mapper.LwOrderFeedbackMapper;
import com.zj.laboratory.mapper.LwServiceOrderMapper;
import com.zj.laboratory.pojo.*;
import com.zj.laboratory.pojo.vo.LwEntryOrderVo;
import com.zj.laboratory.pojo.vo.LwOrderFeedbackVo;
import com.zj.laboratory.pojo.vo.LwServiceOrderVo;
import com.zj.laboratory.service.LwServiceOrderService;
import com.zj.laboratory.utils.IdWorker;
import com.zj.laboratory.utils.Page;
import com.zj.laboratory.utils.ShiroUtils;
import com.zj.laboratory.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LwServiceOrderServiceImpl implements LwServiceOrderService {
    @Autowired
    private LwServiceOrderMapper lwServiceOrderMapper;
    @Autowired
    private LwOrderFeedbackMapper lwOrderFeedbackMapper;
    @Autowired
    private LwOrderAuditMapper lwOrderAuditMapper;
    @Autowired
    private IdWorker idWorker;

    @Override
    public Page<LwServiceOrderVo> getByPage(Page<LwOrder> page) {
        List<LwOrder> list = lwServiceOrderMapper.getByPage(page);
        List<LwServiceOrderVo> voList = list.stream().map(lwServiceOrder -> {
            LwServiceOrderVo lwServiceOrderVo = new LwServiceOrderVo();
            BeanUtils.copyProperties(lwServiceOrder, lwServiceOrderVo);
            return lwServiceOrderVo;
        }).collect(Collectors.toList());
        Integer count = lwServiceOrderMapper.getCountByPage(page);
        Page<LwServiceOrderVo> voPage = new Page<>();
        voPage.setPageSize(page.getPageSize());
        voPage.setCurrentPage(page.getCurrentPage());
        voPage.setParams(page.getParams());
        voPage.setList(voList);
        voPage.setTotalCount(count);
        return voPage;
    }

    @Override
    public void delete(Long id) {
        lwServiceOrderMapper.delete(id);
    }

    @Override
    public void enableById(Long id) {
        lwServiceOrderMapper.enableById(id);
    }

    @Override
    public void disableById(Long id) {
        lwServiceOrderMapper.disableById(id);
    }

    @Override
    public LwServiceOrderVo get(Long id) {
        LwOrder lwOrder = lwServiceOrderMapper.get(id);
        LwServiceOrderVo lwServiceOrderVo = new LwServiceOrderVo();
        BeanUtils.copyProperties(lwOrder, lwServiceOrderVo);
        LwOrderFeedback lwOrderFeedback = lwOrderFeedbackMapper.get(id);
        LwOrderFeedbackVo lwOrderFeedbackVo = new LwOrderFeedbackVo();
        if (lwOrderFeedback != null) {
            BeanUtils.copyProperties(lwOrderFeedback, lwOrderFeedbackVo);
            lwServiceOrderVo.setLwOrderFeedbackVo(lwOrderFeedbackVo);
        }
        LwOrderAudit lwOrderAudit = lwOrderAuditMapper.getByServiceNo(id);
        if (lwOrderAudit != null) {
            lwServiceOrderVo.setLwOrderAudit(lwOrderAudit);
        }
        return lwServiceOrderVo;
    }

    @Override
    public void save(LwOrder lwOrder) {
        lwOrder.setId(idWorker.nextId());
        LoginUser loginUser = ShiroUtils.getLoginUser();
        lwOrder.setApplicationId(loginUser.getId());
        lwOrder.setCurrentDate(new Date());
        lwOrder.setUpdateTime(new Date());
        if (!StringUtils.isBlank(loginUser.getName())) {
            lwOrder.setApplicantName(loginUser.getName());
            lwOrder.setUpdateBy(loginUser.getName());
        } else {
            lwOrder.setApplicantName(loginUser.getNickName());
            lwOrder.setUpdateBy(loginUser.getNickName());
        }
        lwServiceOrderMapper.save(lwOrder);
    }

    @Override
    public void update(LwOrder lwOrder) {
        LoginUser loginUser = ShiroUtils.getLoginUser();
        lwOrder.setUpdateTime(new Date());
        if (!StringUtils.isBlank(loginUser.getName())) {
            lwOrder.setApplicantName(loginUser.getName());
            lwOrder.setUpdateBy(loginUser.getName());
        } else {
            lwOrder.setApplicantName(loginUser.getNickName());
            lwOrder.setUpdateBy(loginUser.getNickName());
        }
        lwServiceOrderMapper.update(lwOrder);
    }
}
