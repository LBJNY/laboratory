package com.zj.laboratory.service.impl;

import com.zj.laboratory.enums.StateEnums;
import com.zj.laboratory.mapper.LwEntryOrderMapper;
import com.zj.laboratory.pojo.LoginUser;
import com.zj.laboratory.pojo.LwEntry;
import com.zj.laboratory.pojo.LwUser;
import com.zj.laboratory.pojo.vo.LwEntryOrderVo;
import com.zj.laboratory.service.LwEntryOrderService;
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
public class LwEntryOrderServiceImpl implements LwEntryOrderService {
    @Autowired
    private LwEntryOrderMapper lwEntryOrderMapper;
    @Autowired
    private IdWorker idWorker;

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
    public void delete(Long id) {
        lwEntryOrderMapper.delete(id);
    }

    @Override
    public void enableById(Long id) {
        lwEntryOrderMapper.enableById(id);
    }

    @Override
    public void disableById(Long id) {
        lwEntryOrderMapper.disableById(id);
    }

    @Override
    public void save(LwEntry lwEntry) {
        lwEntry.setId(idWorker.nextId());
        LoginUser loginUser = ShiroUtils.getLoginUser();
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
        lwEntryOrderMapper.update(lwEntry);
    }
}
