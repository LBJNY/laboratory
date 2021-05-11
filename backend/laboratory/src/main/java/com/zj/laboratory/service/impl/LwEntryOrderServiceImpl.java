package com.zj.laboratory.service.impl;

import com.zj.laboratory.enums.ResultEnum;
import com.zj.laboratory.enums.RoleEnum;
import com.zj.laboratory.enums.StateEnums;
import com.zj.laboratory.exception.LaboratoryException;
import com.zj.laboratory.mapper.LwEntryOrderMapper;
import com.zj.laboratory.mapper.LwNoticeMapper;
import com.zj.laboratory.mapper.LwUserMapper;
import com.zj.laboratory.mapper.LwUserStatisticMapper;
import com.zj.laboratory.pojo.*;
import com.zj.laboratory.pojo.vo.LwEntryOrderVo;
import com.zj.laboratory.pojo.vo.LwOrderProgressVo;
import com.zj.laboratory.pojo.vo.LwReviewerVo;
import com.zj.laboratory.pojo.vo.LwServiceOrderVo;
import com.zj.laboratory.service.LwEntryOrderService;
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
public class LwEntryOrderServiceImpl implements LwEntryOrderService {
    @Autowired
    private LwEntryOrderMapper lwEntryOrderMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private LwUserStatisticMapper lwUserStatisticMapper;
    @Autowired
    private LwUserMapper lwUserMapper;
    @Autowired
    private LwNoticeMapper lwNoticeMapper;

    @Override
    public Page<LwEntryOrderVo> getByPage(Page<LwEntry> page) {
        List<LwEntry> list = lwEntryOrderMapper.getByPage(page);
        List<LwEntryOrderVo> voList = list.stream().map(lwEntry -> {
            LwEntryOrderVo lwEntryOrderVo = new LwEntryOrderVo();
            BeanUtils.copyProperties(lwEntry, lwEntryOrderVo);
            return lwEntryOrderVo;
        }).collect(Collectors.toList());
        Integer count = lwEntryOrderMapper.getCountByPage(page);
        Page<LwEntryOrderVo> voPage = new Page<>();
        voPage.setPageSize(page.getPageSize());
        voPage.setCurrentPage(page.getCurrentPage());
        voPage.setParams(page.getParams());
        voPage.setList(voList);
        voPage.setTotalCount(count);
        return voPage;
    }

    @Override
    public LwEntryOrderVo get(Long id) {
        LwEntry lwEntry = lwEntryOrderMapper.get(id);
        LwEntryOrderVo lwEntryOrderVo = new LwEntryOrderVo();
        BeanUtils.copyProperties(lwEntry, lwEntryOrderVo);
        if (StateEnums.ENTRY_ORDER_PASS.getCode() == lwEntry.getVerifyStatus()) {
            lwEntryOrderVo.setVerifyStatusName(StateEnums.ENTRY_ORDER_PASS.getMsg());
        } else if (StateEnums.ENTRY_ORDER_FAIL.getCode() == lwEntry.getVerifyStatus()) {
            lwEntryOrderVo.setVerifyStatusName(StateEnums.ENTRY_ORDER_FAIL.getMsg());
        } else {
            lwEntryOrderVo.setVerifyStatusName(StateEnums.ENTRY_ORDER_PENDING.getMsg());
        }
        return lwEntryOrderVo;
    }

    @Override
    public LwEntryOrderVo getUpdateInfo(Long id) {
        LwEntry lwEntry = lwEntryOrderMapper.getUpdateInfo(id);
        LwEntryOrderVo lwEntryOrderVo = new LwEntryOrderVo();
        BeanUtils.copyProperties(lwEntry, lwEntryOrderVo);
        return lwEntryOrderVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        LoginUser loginUser = ShiroUtils.getLoginUser();
        assert loginUser != null;
        // 查询当前用户统计信息
        LwUserStatistic lwUserStatistic = lwUserStatisticMapper.getByUserId(loginUser.getId());
        // 查询当前订单信息
        LwEntry lwEntry = lwEntryOrderMapper.get(id);
        // 根据订单状态更新统计信息
        if (lwEntry.getVerifyStatus() == StateEnums.ENTRY_ORDER_PASS.getCode()) {
            lwUserStatistic.setEntryCount(lwUserStatistic.getEntryPassCount() - 1);
        } else if (lwEntry.getVerifyStatus() == StateEnums.ENTRY_ORDER_FAIL.getCode()) {
            lwUserStatistic.setEntryCount(lwUserStatistic.getEntryFailCount() - 1);
        }
        lwUserStatistic.setEntryCount(lwUserStatistic.getEntryCount() - 1);
        //执行更新统计信息操作
        lwUserStatisticMapper.update(lwUserStatistic);
        // 执行删除操作
        // 1.先查询
        LwEntry delEntryOrder = lwEntryOrderMapper.get(id);
        // 2.删除
        int res = lwEntryOrderMapper.delete(delEntryOrder);
        if (res == 0) {
            throw new LaboratoryException("操作失败,请稍后重试!");
        }
    }

    @Override
    public void enableById(Long id) {
        // 1.先查询
        LwEntry enableEntryOrder = lwEntryOrderMapper.get(id);
        // 2.启用
        int res = lwEntryOrderMapper.enableById(enableEntryOrder);
        if (res == 0) {
            throw new LaboratoryException("操作失败,请稍后重试!");
        }
    }

    @Override
    public void disableById(Long id) {
        // 1.先查询
        LwEntry enableEntryOrder = lwEntryOrderMapper.get(id);
        // 2.禁用
        int res = lwEntryOrderMapper.disableById(enableEntryOrder);
        if (res == 0) {
            throw new LaboratoryException("操作失败,请稍后重试!");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(LwEntry lwEntry) {
        // 保存进场单信息
        lwEntry.setId(idWorker.nextId());
        LoginUser loginUser = ShiroUtils.getLoginUser();
        if (StringUtils.isBlank(lwEntry.getEntryNo())) {
            lwEntry.setEntryNo(String.valueOf(idWorker.nextId()));
        }
        lwEntry.setApplicantId(loginUser.getId());
        lwEntry.setCurrentDate(new Date());
        lwEntry.setUpdateTime(new Date());
        if (!StringUtils.isBlank(loginUser.getName())) {
            lwEntry.setApplicantName(loginUser.getName());
            lwEntry.setUpdateBy(loginUser.getName());
        } else {
            lwEntry.setApplicantName(loginUser.getNickName());
            lwEntry.setUpdateBy(loginUser.getNickName());
        }
        lwEntryOrderMapper.save(lwEntry);

        // 添加统计表信息
        lwUserStatisticMapper.increaseEntryOrderCount(loginUser.getId());

        // 添加通知消息
        // 1.查询所有审查员
        List<LwUser> reviewerList = lwUserMapper.getByLevel(RoleEnum.LEVEL_ENTRY_ORDER.getType());
        List<LwUser> adminReviewerList = lwUserMapper.getByLevel(RoleEnum.LEVEL_ADMIN.getType());
        if (reviewerList.size()<=0&&adminReviewerList.size()<=0){
            throw new LaboratoryException("暂无审核员,请联系负责人添加审核员!");
        }
        List<Long> idList=new ArrayList<>();
        //普通审核人
        reviewerList.forEach(lwUser -> {
            idList.add(lwUser.getId());
        });
        //顶级审核人
        adminReviewerList.forEach(lwUser -> {
            idList.add(lwUser.getId());
        });
        // 2.构建通知信息
        LwNotice lwNotice=new LwNotice();
        lwNotice.setId(idWorker.nextId());
        lwNotice.setCreatedBy(getName(loginUser));
        lwNotice.setNoticeTitle("新增一条进场单!");
        lwNotice.setNoticeContent("新增进场单:订单编号是"+lwEntry.getId()+",创建人为"+lwEntry.getApplicantName());
        lwNotice.setRole(StateEnums.NOTICE_ROLE_ADMIN.getCode());
        List<LwNotice> lwNoticeList = idList.stream().map(id -> {
            lwNotice.setUserId(id);
            return lwNotice;
        }).collect(Collectors.toList());
        // 3.调用方法
        lwNoticeMapper.saveBatch(lwNoticeList);
    }

    @Override
    public void update(LwEntry lwEntry) {
        LoginUser loginUser = ShiroUtils.getLoginUser();
        lwEntry.setUpdateTime(new Date());
        if (!StringUtils.isBlank(loginUser.getName())) {
            lwEntry.setUpdateBy(loginUser.getName());
        } else {
            lwEntry.setUpdateBy(loginUser.getNickName());
        }
        LwEntry selEntryOrder = lwEntryOrderMapper.get(lwEntry.getId());
        lwEntry.setVersion(selEntryOrder.getVersion());
        lwEntryOrderMapper.update(lwEntry);
    }

    @Override
    public Page<LwEntryOrderVo> getListByPage(Page<LwEntry> page) {
        Integer role = Integer.parseInt(page.getParams().get("role").toString());

        if (RoleEnum.ADMIN.getType() != role) {
            LoginUser loginUser = ShiroUtils.getLoginUser();
            if (loginUser == null) {
                throw new LaboratoryException(ResultEnum.NOT_LOGIN);
            }
            page.getParams().put("applicantId", loginUser.getId());
        }
        Integer count = lwEntryOrderMapper.getListCountByPage(page);
        List<LwEntry> list = lwEntryOrderMapper.getListByPage(page);
        List<LwEntryOrderVo> voList = list.stream().map(lwEntry -> {
            LwEntryOrderVo lwEntryOrderVo = new LwEntryOrderVo();
            BeanUtils.copyProperties(lwEntry, lwEntryOrderVo);
            List<LwOrderProgressVo> progress = new ArrayList<>();
            progress.add(new LwOrderProgressVo(StateEnums.ORDER_IN_APPROVAL.getMsg()));
            if (lwEntry.getVerifyStatus() == 1) {
                progress.add(new LwOrderProgressVo(StateEnums.ORDER_AUDIT_SUCCESS.getMsg()));
            } else if (lwEntry.getVerifyStatus() == 2) {
                progress.add(new LwOrderProgressVo(StateEnums.ORDER_AUDIT_FAILED.getMsg()));
            } else {
                progress.add(new LwOrderProgressVo(StateEnums.ORDER_AUDIT_OVER.getMsg()));
            }
            lwEntryOrderVo.setProgress(progress);
            return lwEntryOrderVo;
        }).collect(Collectors.toList());
        Page<LwEntryOrderVo> voPage = new Page<>();
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
        return lwUserStatisticMapper.getEntryOrderCountByUserId(loginUser.getId());
    }

    @Override
    public LwUserStatistic getEntryTotolCount() {
        return lwUserStatisticMapper.getEntryTotolCount();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void examine(LwEntry lwEntry) {
        // 查询当前用户是否有权限
        LoginUser loginUser = ShiroUtils.getLoginUser();
        // 判断是否有审查权限
        if (!(loginUser.getLevel() == RoleEnum.LEVEL_ENTRY_ORDER.getType() || loginUser.getLevel() == RoleEnum.LEVEL_ADMIN.getType())) {
            throw new LaboratoryException("您未拥有审查权限!");
        }
        if (!(loginUser.getReviewerType() == RoleEnum.REVIEWER_TYPE_LAB.getType() || loginUser.getReviewerType() == RoleEnum.REVIEWER_TYPE_ADMIN.getType())) {
            throw new LaboratoryException("您未拥有审查权限!");
        }
        LwEntry getEntryOrder = lwEntryOrderMapper.get(lwEntry.getId());
        // 判断是否审核完成
        if (getEntryOrder.getVerifyStatus() > StateEnums.ENTRY_ORDER_PENDING.getCode()) {
            throw new LaboratoryException("该订单已审核完成,请勿重复审核!");
        }

        // 对象赋值
        lwEntry.setVersion(getEntryOrder.getVersion());
        lwEntry.setOfficerId(loginUser.getId());
        if (!StringUtils.isBlank(loginUser.getName())) {
            lwEntry.setOfficerName(loginUser.getName());
            lwEntry.setUpdateBy(loginUser.getName());
        } else {
            lwEntry.setOfficerName(loginUser.getNickName());
            lwEntry.setUpdateBy(loginUser.getNickName());
        }
        lwEntry.setUpdateTime(new Date());
        // 执行操作并判断
        int res = lwEntryOrderMapper.examine(lwEntry);
        if (res == 0) {
            lwUserStatisticMapper.increaseEntryOrderFailCount(loginUser.getId());
            throw new LaboratoryException("审核失败,请稍后重试");
        } else {
            lwUserStatisticMapper.increaseEntryOrderPassCount(loginUser.getId());
        }
    }
    // 获取名称
    protected String getName(LoginUser lwUser) {
        if (lwUser.getName() != null) {
            return lwUser.getName();
        } else {
            return lwUser.getNickName();
        }
    }
}
