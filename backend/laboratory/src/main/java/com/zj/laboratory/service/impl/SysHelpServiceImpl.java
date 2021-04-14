package com.zj.laboratory.service.impl;


import com.zj.laboratory.mapper.SysHelpMapper;
import com.zj.laboratory.pojo.LoginUser;
import com.zj.laboratory.pojo.SysHelp;
import com.zj.laboratory.service.SysHelpService;
import com.zj.laboratory.utils.Page;
import com.zj.laboratory.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 帮助中心service实现类
 */
@Service
public class SysHelpServiceImpl implements SysHelpService {

    @Autowired
    private SysHelpMapper sysHelpMapper;

    /**
     * 保存
     * @param sysHelp
     */
    @Override
    public void save(SysHelp sysHelp) {
        LoginUser loginUser = ShiroUtils.getLoginUser();
        sysHelp.setCreatedBy(loginUser.getUsername());
        sysHelp.setUpdateBy(loginUser.getUsername());
        sysHelpMapper.save(sysHelp);
    }

    /**
     * 修改
     * @param sysHelp
     */
    @Override
    public void update(SysHelp sysHelp) {
        LoginUser loginUser = ShiroUtils.getLoginUser();
        sysHelp.setUpdateBy(loginUser.getUsername());
        sysHelpMapper.update(sysHelp);
    }

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    @Override
    public SysHelp get(Long id) {
        return sysHelpMapper.get(id);
    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void delete(Long id) {
        sysHelpMapper.delete(id);
    }

    /**
     * 分页获取
     * @param page
     * @return
     */
    @Override
    public Page<SysHelp> getByPage(Page<SysHelp> page) {
        List<SysHelp> list = sysHelpMapper.getByPage(page);
        int totalCount = sysHelpMapper.countByPage(page);
        page.setList(list);
        page.setTotalCount(totalCount);
        return page;
    }


}
