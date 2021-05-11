package com.zj.laboratory.service.impl;


import com.zj.laboratory.enums.ResultEnum;
import com.zj.laboratory.enums.RoleEnum;
import com.zj.laboratory.enums.StateEnums;
import com.zj.laboratory.exception.LaboratoryException;
import com.zj.laboratory.mapper.LwServiceOrderMapper;
import com.zj.laboratory.mapper.LwUserMapper;
import com.zj.laboratory.mapper.LwUserStatisticMapper;
import com.zj.laboratory.pojo.*;
import com.zj.laboratory.pojo.dto.LwUserBindDto;
import com.zj.laboratory.pojo.vo.LwReviewerVo;
import com.zj.laboratory.pojo.vo.LwUserStatisticVo;
import com.zj.laboratory.pojo.vo.LwUserVo;
import com.zj.laboratory.service.LwUserService;
import com.zj.laboratory.utils.IdWorker;
import com.zj.laboratory.utils.Page;
import com.zj.laboratory.utils.ShiroUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商城用户ServiceImpl
 */
@Service
public class LwUserServiceImpl implements LwUserService {
    @Autowired
    private LwUserMapper lwUserMapper;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private LwUserStatisticMapper lwUserStatisticMapper;
    @Autowired
    private LwServiceOrderMapper lwServiceOrderMapper;

    /**
     * 根据用户ID查询用户是否存在
     *
     * @param username
     * @return
     */
    @Override
    public LwUser getByOpenId(String username) {
        return lwUserMapper.getByOpenId(username);
    }

    /**
     * 注册
     *
     * @param lwUser
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(LwUser lwUser) {
        //保存用户信息
        long userId = idWorker.nextId();
        lwUser.setId(userId);
        lwUserMapper.save(lwUser);

        //创建用户统计对象
        LwUserStatistic lwUserStatistic = new LwUserStatistic();
        long statisticId = idWorker.nextId();
        lwUserStatistic.setId(statisticId);
        lwUserStatistic.setUserId(userId);
        lwUserStatisticMapper.save(lwUserStatistic);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LwUser bindUser(LwUserBindDto lwUserBindDto) {
        LwUser lwUser = lwUserMapper.getByPhone(lwUserBindDto.getPhone());
        if (lwUser != null) {
            throw new LaboratoryException(ResultEnum.USER_REAL_EXISTS);
        }
        LoginUser loginUser = ShiroUtils.getLoginUser();
        String openId = loginUser.getOpenId();
        lwUser = lwUserMapper.getByOpenId(openId);
        lwUser.setPhone(lwUserBindDto.getPhone());
        lwUserMapper.updateLoginInfo(lwUser);
        return lwUser;

    }

    @Override
    public Page<LwUser> getByPage(Page<LwUser> page) {
        List<LwUser> list = lwUserMapper.getByPage(page);
        Integer count = lwUserMapper.getCountByPage(page);
        page.setList(list);
        page.setTotalCount(count);
        return page;
    }

    @Override
    public LwUserVo get(Long id) {
        LwUser lwUser = lwUserMapper.get(id);
        LwUserVo vo = new LwUserVo();
        BeanUtils.copyProperties(lwUser, vo);

        // 查询统计信息
        LwUserStatistic statistic = lwUserStatisticMapper.getByUserId(id);
        vo.setLwUserStatistic(statistic);
        return vo;
    }

    @Override
    public void delete(Long id) {
        lwUserMapper.delete(id);
    }

    @Override
    public void enableById(Long id) {
        lwUserMapper.enableById(id);
    }

    @Override
    public void disableById(Long id) {
        lwUserMapper.disableById(id);
    }


    @Override
    public List<LwUserStatisticVo> getTopStatistic() {
        List<LwUserStatistic> list = lwUserStatisticMapper.getTopStatistic();
        List<Long> userIds = list.stream().map(LwUserStatistic::getUserId).collect(Collectors.toList());
        List<LwUser> userList = lwUserMapper.getByIds(userIds);
        List<LwUserStatisticVo> voList = new ArrayList<>();
        for (LwUserStatistic statistic : list) {
            LwUser lwUser = userList.stream().filter(u -> u.getId().equals(statistic.getUserId())).findFirst().orElse(null);
            if (lwUser == null) {
                continue;
            }
            LwUserStatisticVo serviceCount = new LwUserStatisticVo();
            serviceCount.setUserName(lwUser.getNickname() + lwUser.getPhone());
            serviceCount.setName("服务委托单");
            serviceCount.setCount(new BigDecimal(statistic.getServiceCount()));
            voList.add(serviceCount);

            LwUserStatisticVo entryCount = new LwUserStatisticVo();
            entryCount.setUserName(lwUser.getNickname() + lwUser.getPhone());
            entryCount.setName("进场单");
            entryCount.setCount(new BigDecimal(statistic.getEntryCount()));
            voList.add(entryCount);

            LwUserStatisticVo feedCount = new LwUserStatisticVo();
            feedCount.setUserName(lwUser.getNickname() + lwUser.getPhone());
            feedCount.setName("反馈数");
            feedCount.setCount(new BigDecimal(statistic.getFeedCount()));
            voList.add(feedCount);



            LwUserStatisticVo login = new LwUserStatisticVo();
            login.setUserName(lwUser.getNickname() + lwUser.getPhone());
            login.setName("登录次数");
            login.setCount(new BigDecimal(statistic.getLoginCount()));
            voList.add(login);
        }
        return voList;
    }

    @Override
    public void update(LwUser lwUser) {
        lwUser.setUpdateTime(new Date());
        lwUserMapper.update(lwUser);
    }

    @Override
    public LwUserVo getUpdateInfo(Long id) {
        LwUser updateInfo = lwUserMapper.getUpdateInfo(id);
        LwUserVo lwUserVo=new LwUserVo();
        BeanUtils.copyProperties(updateInfo,lwUserVo);
        return lwUserVo;
    }
    @Override
    public List<LwReviewerVo> getReviewerList(String serviceNo) {
        //LwOrderAudit byServiceNo = lwOrderAuditMapper.getByServiceNo(serviceNo);
        LwOrder lwServiceOrderByServiceNo = lwServiceOrderMapper.getByServiceNo(serviceNo);
        List<LwUser> lwUserList=null;
        // 判断订单状态
        if (lwServiceOrderByServiceNo.getVerifyStatus()== StateEnums.SERVICE_ORDER_PENDING.getCode()){
            //如果是待审核状态---查询实验室审核人员
            lwUserList=lwUserMapper.getByReviewerType(RoleEnum.REVIEWER_TYPE_LAB.getType());
        }else if (lwServiceOrderByServiceNo.getVerifyStatus()==StateEnums.SERVICE_ORDER_DEPT.getCode()){
            //如果是部门审核通过状态---查询创新合作部审核人员
            lwUserList=lwUserMapper.getByReviewerType(RoleEnum.REVIEWER_TYPE_CH.getType());
        }
        List<LwUser> adminReviewer = lwUserMapper.getByReviewerType(RoleEnum.REVIEWER_TYPE_ADMIN.getType());
        assert lwUserList != null;
        if (lwUserList.size()<=0&&adminReviewer.size()<=0){
            throw new LaboratoryException("暂无审核员,请联系负责人添加审核员!");
        }
        // 创建审核人列表
        List<LwReviewerVo> reviewerList=new ArrayList<>();
        //普通审核人
        lwUserList.forEach(lwUser -> {
            LwReviewerVo lwReviewerVo=new LwReviewerVo();
            lwReviewerVo.setId(lwUser.getId());
            lwReviewerVo.setValue(getName(lwUser));
            reviewerList.add(lwReviewerVo);
        });
        //顶级审核人
        adminReviewer.forEach(lwUser -> {
            LwReviewerVo lwReviewerVo=new LwReviewerVo();
            lwReviewerVo.setId(lwUser.getId());
            lwReviewerVo.setValue(getName(lwUser));
            reviewerList.add(lwReviewerVo);
        });
        return reviewerList;
    }
    // 获取名称
    protected String getName(LwUser lwUser) {
        if (lwUser.getName() != null) {
            return lwUser.getName();
        } else {
            return lwUser.getNickname();
        }
    }
}
