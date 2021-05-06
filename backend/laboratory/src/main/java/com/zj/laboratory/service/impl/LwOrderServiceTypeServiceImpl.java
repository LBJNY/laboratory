package com.zj.laboratory.service.impl;

import com.zj.laboratory.mapper.LwOrderServiceTypeMapper;
import com.zj.laboratory.pojo.LoginUser;
import com.zj.laboratory.pojo.LwOrderFundSupport;
import com.zj.laboratory.pojo.LwOrderServiceType;
import com.zj.laboratory.pojo.vo.LwOrderServiceTypeVo;
import com.zj.laboratory.service.LwOrderServiceTypeService;
import com.zj.laboratory.utils.Page;
import com.zj.laboratory.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LwOrderServiceTypeServiceImpl implements LwOrderServiceTypeService {
    @Autowired
    private LwOrderServiceTypeMapper lwOrderServiceTypeMapper;
    @Override
    public Page<LwOrderServiceType> getByPage(Page<LwOrderServiceType> page) {
        List<LwOrderServiceType> list = lwOrderServiceTypeMapper.getByPage(page);
        Integer count = lwOrderServiceTypeMapper.getCountByPage(page);
        page.setList(list);
        page.setTotalCount(count);
        return page;
    }

    @Override
    public void update(LwOrderServiceType lwOrderServiceType) {
        LoginUser loginUser = ShiroUtils.getLoginUser();
        assert loginUser != null;
        lwOrderServiceType.setUpdateBy(loginUser.getNickName());
        lwOrderServiceType.setUpdateTime(new Date());
        lwOrderServiceTypeMapper.update(lwOrderServiceType);
    }

    @Override
    public void enableById(Integer id) {
        lwOrderServiceTypeMapper.enableById(id);
    }

    @Override
    public void disableById(Integer id) {
        lwOrderServiceTypeMapper.disableById(id);
    }

    @Override
    public void save(LwOrderServiceType lwOrderServiceType) {
        LoginUser loginUser = ShiroUtils.getLoginUser();
        assert loginUser != null;
        lwOrderServiceType.setCreateBy(loginUser.getNickName());
        lwOrderServiceType.setCreateTime(new Date());
        lwOrderServiceTypeMapper.save(lwOrderServiceType);
    }

    @Override
    public void delete(Integer id) {
        lwOrderServiceTypeMapper.delete(id);
    }

    @Override
    public LwOrderServiceType get(Integer id) {
        return lwOrderServiceTypeMapper.get(id);
    }

    @Override
    public List<LwOrderServiceTypeVo> getAll() {
        List<LwOrderServiceType> lwOrderServiceTypeList = lwOrderServiceTypeMapper.getAll();
        return lwOrderServiceTypeList.stream().map(lwOrderServiceType -> {
            LwOrderServiceTypeVo lwOrderServiceTypeVo = new LwOrderServiceTypeVo();
            lwOrderServiceTypeVo.setId(lwOrderServiceType.getId());
            lwOrderServiceTypeVo.setValue(lwOrderServiceType.getName());
            return lwOrderServiceTypeVo;
        }).collect(Collectors.toList());
    }
}
