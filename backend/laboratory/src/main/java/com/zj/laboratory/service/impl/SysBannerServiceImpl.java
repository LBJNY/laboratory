package com.zj.laboratory.service.impl;


import com.zj.laboratory.enums.StateEnums;
import com.zj.laboratory.mapper.SysBannerMapper;
import com.zj.laboratory.pojo.LoginUser;
import com.zj.laboratory.pojo.SysBanner;
import com.zj.laboratory.service.SysBannerService;
import com.zj.laboratory.utils.Page;
import com.zj.laboratory.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 */
@Service
public class SysBannerServiceImpl implements SysBannerService {
    @Autowired
    private SysBannerMapper sysBannerMapper;

    @Override
    public void save(SysBanner sysBanner) {
        LoginUser loginUser = ShiroUtils.getLoginUser();
        sysBanner.setCreateBy(loginUser.getUsername());
        sysBanner.setUpdateBy(loginUser.getUsername());
        sysBannerMapper.save(sysBanner);
    }

    @Override
    public void update(SysBanner sysBanner) {
        LoginUser loginUser = ShiroUtils.getLoginUser();
        sysBanner.setUpdateBy(loginUser.getUsername());
        sysBannerMapper.update(sysBanner);
    }

    @Override
    public SysBanner get(Long id) {
        return sysBannerMapper.get(id);
    }

    @Override
    public void delete(Long id) {
        sysBannerMapper.delete(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enable(Long id) {
        SysBanner banner = sysBannerMapper.get(id);
        banner.setStatus(StateEnums.ENABLED.getCode());
        sysBannerMapper.updateStatus(banner);
    }

    @Override
    public void disable(Long id) {
        SysBanner banner = sysBannerMapper.get(id);
        banner.setStatus(StateEnums.NOT_ENABLE.getCode());
        sysBannerMapper.updateStatus(banner);
    }

    @Override
    public Page<SysBanner> getByPage(Page<SysBanner> page) {
        List<SysBanner> list = sysBannerMapper.getByPage(page);
        int totalCount = sysBannerMapper.countByPage(page);
        page.setList(list);
        page.setTotalCount(totalCount);
        return page;
    }

    @Override
    public List<Banner> getBannerList() {

        return sysBannerMapper.getBannerList();
    }

    @Override
    public void addClickCount(Long id) {
        sysBannerMapper.updateClickCount(id);
    }
}