package com.zj.laboratory.service.impl;


import com.zj.laboratory.enums.ResultEnum;
import com.zj.laboratory.exception.LaboratoryException;
import com.zj.laboratory.mapper.LwUserMapper;
import com.zj.laboratory.mapper.LwUserStatisticMapper;
import com.zj.laboratory.pojo.LoginUser;
import com.zj.laboratory.pojo.LwUser;
import com.zj.laboratory.pojo.LwUserStatistic;
import com.zj.laboratory.pojo.dto.LwUserBindDto;
import com.zj.laboratory.pojo.vo.LwUserStatisticVo;
import com.zj.laboratory.pojo.vo.LwUserVo;
import com.zj.laboratory.service.LwUserService;
import com.zj.laboratory.utils.IdWorker;
import com.zj.laboratory.utils.Page;
import com.zj.laboratory.utils.ShiroUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
     * @param shopUser
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(LwUser shopUser) {
        //保存用户信息
        long userId = idWorker.nextId();
        shopUser.setId(userId);
        lwUserMapper.save(shopUser);

        //创建用户统计对象
        LwUserStatistic lwUserStatistic = new LwUserStatistic();
        long statisticId = idWorker.nextId();
        lwUserStatistic.setId(statisticId);
        lwUserStatistic.setUserId(userId);
        lwUserStatisticMapper.save(lwUserStatistic);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public LwUser bindUser(LwUserBindDto shopUserBindDto) {
        LwUser lwUser = lwUserMapper.getByPhone(shopUserBindDto.getPhone());
        if (lwUser != null) {
            throw new LaboratoryException(ResultEnum.USER_REAL_EXISTS);
        }
        LoginUser loginUser = ShiroUtils.getLoginUser();
        String openId = loginUser.getOpenId();
        lwUser = lwUserMapper.getByOpenId(openId);
        lwUser.setPhone(shopUserBindDto.getPhone());
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
        LwUser shopUser = lwUserMapper.get(id);
        LwUserVo vo = new LwUserVo();
        BeanUtils.copyProperties(shopUser, vo);

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
            LwUser shopUser = userList.stream().filter(u -> u.getId().equals(statistic.getUserId())).findFirst().orElse(null);
            if (shopUser == null) {
                continue;
            }
            LwUserStatisticVo serviceCount = new LwUserStatisticVo();
            serviceCount.setUserName(shopUser.getNickname() + shopUser.getPhone());
            serviceCount.setName("服务委托单");
            serviceCount.setCount(new BigDecimal(statistic.getServiceCount()));
            voList.add(serviceCount);

            LwUserStatisticVo entryCount = new LwUserStatisticVo();
            entryCount.setUserName(shopUser.getNickname() + shopUser.getPhone());
            entryCount.setName("进场单");
            entryCount.setCount(new BigDecimal(statistic.getEntryCount()));
            voList.add(entryCount);

            LwUserStatisticVo feedCount = new LwUserStatisticVo();
            feedCount.setUserName(shopUser.getNickname() + shopUser.getPhone());
            feedCount.setName("反馈数");
            feedCount.setCount(new BigDecimal(statistic.getFeedCount()));
            voList.add(feedCount);



            LwUserStatisticVo login = new LwUserStatisticVo();
            login.setUserName(shopUser.getNickname() + shopUser.getPhone());
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
}
