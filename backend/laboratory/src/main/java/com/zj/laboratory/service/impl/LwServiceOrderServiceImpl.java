package com.zj.laboratory.service.impl;

import com.zj.laboratory.enums.ResultEnum;
import com.zj.laboratory.enums.RoleEnum;
import com.zj.laboratory.enums.StateEnums;
import com.zj.laboratory.exception.LaboratoryException;
import com.zj.laboratory.mapper.LwOrderAuditMapper;
import com.zj.laboratory.mapper.LwOrderFeedbackMapper;
import com.zj.laboratory.mapper.LwServiceOrderMapper;
import com.zj.laboratory.mapper.LwUserStatisticMapper;
import com.zj.laboratory.pojo.*;
import com.zj.laboratory.pojo.vo.LwEntryOrderVo;
import com.zj.laboratory.pojo.vo.LwOrderFeedbackVo;
import com.zj.laboratory.pojo.vo.LwOrderProgressVo;
import com.zj.laboratory.pojo.vo.LwServiceOrderVo;
import com.zj.laboratory.service.LwServiceOrderService;
import com.zj.laboratory.utils.IdWorker;
import com.zj.laboratory.utils.Page;
import com.zj.laboratory.utils.ShiroUtils;
import com.zj.laboratory.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
    private LwUserStatisticMapper lwUserStatisticMapper;
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
        LoginUser loginUser = ShiroUtils.getLoginUser();
        assert loginUser != null;
        // 查询当前用户统计信息
        LwUserStatistic lwUserStatistic = lwUserStatisticMapper.getByUserId(loginUser.getId());
        // 查询当前订单信息
        LwOrder lwOrder = lwServiceOrderMapper.get(id);
        // 根据订单状态更新统计信息
        if ( lwOrder.getVerifyStatus()== StateEnums.SERVICE_ORDER_CHO.getCode()) {
            lwUserStatistic.setEntryCount(lwUserStatistic.getServicePassCount() - 1);
        } else if (lwOrder.getVerifyStatus() == StateEnums.SERVICE_ORDER_FAIL.getCode()) {
            lwUserStatistic.setEntryCount(lwUserStatistic.getEntryFailCount() - 1);
        }
        lwUserStatistic.setEntryCount(lwUserStatistic.getServiceCount() - 1);
        //执行更新统计信息操作
        lwUserStatisticMapper.update(lwUserStatistic);
        //执行删除
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
        LwOrderAudit lwOrderAudit = lwOrderAuditMapper.getByServiceNo(lwOrder.getServiceNo());
        if (lwOrderAudit != null) {
            lwServiceOrderVo.setLwOrderAudit(lwOrderAudit);
        }
        return lwServiceOrderVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(LwOrder lwOrder) {
        lwOrder.setId(idWorker.nextId());
        if (StringUtils.isBlank(lwOrder.getServiceNo())) {
            lwOrder.setServiceNo(String.valueOf(idWorker.nextId()));
        }
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

        // 添加审核信息
        LwOrderAudit lwOrderAudit = new LwOrderAudit();
        lwOrderAudit.setId(idWorker.nextId());
        lwOrderAudit.setServiceNo(lwOrder.getServiceNo());
        lwOrderAuditMapper.save(lwOrderAudit);
        // 添加服务委托单统计信息
        lwUserStatisticMapper.increaseServiceOrderCount(loginUser.getId());
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

    @Override
    public Page<LwServiceOrderVo> getListByPage(Page<LwOrder> page) {
        // 判断当前页面是审核员页面 or 用户页面
        Integer role = Integer.parseInt(page.getParams().get("role").toString());
        if (RoleEnum.ADMIN.getType() != role) {
            LoginUser loginUser = ShiroUtils.getLoginUser();
            if (loginUser == null) {
                throw new LaboratoryException(ResultEnum.NOT_LOGIN);
            }
            page.getParams().put("applicationId", loginUser.getId());
        }
        Integer count = lwServiceOrderMapper.getListCountByPage(page);
        List<LwOrder> list = lwServiceOrderMapper.getListByPage(page);
        // 获取返回信息
        List<LwServiceOrderVo> voList = list.stream().map(lwServiceOrder -> {
            LwServiceOrderVo lwServiceOrderVo = new LwServiceOrderVo();
            BeanUtils.copyProperties(lwServiceOrder, lwServiceOrderVo);
            List<LwOrderProgressVo> progress = new ArrayList<>();
            progress.add(new LwOrderProgressVo(StateEnums.ORDER_CONFIRM.getMsg()));
            progress.add(new LwOrderProgressVo(StateEnums.ORDER_IN_APPROVAL.getMsg()));
            if (StateEnums.SERVICE_ORDER_CHO.getCode() == lwServiceOrder.getVerifyStatus()) {
                progress.add(new LwOrderProgressVo(StateEnums.ORDER_AUDIT_SUCCESS.getMsg()));
            } else if (StateEnums.SERVICE_ORDER_FAIL.getCode() == lwServiceOrder.getVerifyStatus()) {
                progress.add(new LwOrderProgressVo(StateEnums.ORDER_AUDIT_FAILED.getMsg()));
            } else {
                progress.add(new LwOrderProgressVo(StateEnums.ORDER_AUDIT_OVER.getMsg()));
            }
            lwServiceOrderVo.setProgress(progress);
            return lwServiceOrderVo;
        }).collect(Collectors.toList());
        Page<LwServiceOrderVo> voPage = new Page<>();
        voPage.setTotalCount(count);
        voPage.setPageSize(page.getPageSize());
        voPage.setCurrentPage(page.getCurrentPage());
        voPage.setParams(page.getParams());
        voPage.setList(voList);
        if (!StringUtils.isBlank(page.getSortColumn())) {
            voPage.setSortColumn(page.getSortColumn());
            voPage.setSortMethod(page.getSortMethod());
        }
        return voPage;
    }

    @Override
    public LwUserStatistic getCountList() {
        LoginUser loginUser = ShiroUtils.getLoginUser();
        assert loginUser != null;
        return lwUserStatisticMapper.getServcieOrderCountByUserId(loginUser.getId());
    }

    @Override
    public LwUserStatistic getServiceTotolCount() {
        return lwUserStatisticMapper.getServiceTotolCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(LwOrderFeedback lwOrderFeedback) {
        lwOrderFeedback.setId(idWorker.nextId());
        lwOrderFeedbackMapper.save(lwOrderFeedback);
        lwServiceOrderMapper.updateFeedBack(lwOrderFeedback.getServiceNo());
    }
}
