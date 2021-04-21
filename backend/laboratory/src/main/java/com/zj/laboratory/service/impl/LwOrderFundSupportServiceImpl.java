package com.zj.laboratory.service.impl;

import com.zj.laboratory.mapper.LwOrderFundSupportMapper;
import com.zj.laboratory.pojo.LoginUser;
import com.zj.laboratory.pojo.LwOrderFundSupport;
import com.zj.laboratory.pojo.LwUser;
import com.zj.laboratory.service.LwOrderFundSupportService;
import com.zj.laboratory.utils.Page;
import com.zj.laboratory.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LwOrderFundSupportServiceImpl implements LwOrderFundSupportService {
    @Autowired
    private LwOrderFundSupportMapper lwOrderFundSupportMapper;

    @Override
    public Page<LwOrderFundSupport> getByPage(Page<LwOrderFundSupport> page) {
        List<LwOrderFundSupport> list = lwOrderFundSupportMapper.getByPage(page);
        Integer count = lwOrderFundSupportMapper.getCountByPage(page);
        page.setList(list);
        page.setTotalCount(count);
        return page;
    }

    @Override
    public void update(LwOrderFundSupport lwOrderFundSupport) {
        LoginUser loginUser = ShiroUtils.getLoginUser();
        assert loginUser != null;
        lwOrderFundSupport.setUpdateBy(loginUser.getNickName());
        lwOrderFundSupport.setUpdateTime(new Date());
        lwOrderFundSupportMapper.update(lwOrderFundSupport);
    }

    @Override
    public void enableById(Integer id) {
        lwOrderFundSupportMapper.enableById(id);
    }

    @Override
    public void disableById(Integer id) {
        lwOrderFundSupportMapper.disableById(id);
    }

    @Override
    public void save(LwOrderFundSupport lwOrderFundSupport) {
        LoginUser loginUser = ShiroUtils.getLoginUser();
        assert loginUser != null;
        lwOrderFundSupport.setCreateBy(loginUser.getNickName());
        lwOrderFundSupport.setCreateTime(new Date());
        lwOrderFundSupportMapper.save(lwOrderFundSupport);
    }

    @Override
    public void delete(Integer id) {
        lwOrderFundSupportMapper.delete(id);
    }

    @Override
    public LwOrderFundSupport get(Integer id) {
        return lwOrderFundSupportMapper.get(id);
    }
}
