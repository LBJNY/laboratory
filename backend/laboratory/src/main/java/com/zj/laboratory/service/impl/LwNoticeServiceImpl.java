package com.zj.laboratory.service.impl;

import com.zj.laboratory.enums.RoleEnum;
import com.zj.laboratory.enums.StateEnums;
import com.zj.laboratory.mapper.LwNoticeMapper;
import com.zj.laboratory.mapper.LwUserMapper;
import com.zj.laboratory.pojo.LoginUser;
import com.zj.laboratory.pojo.LwNotice;
import com.zj.laboratory.pojo.LwUser;
import com.zj.laboratory.pojo.SysMenu;
import com.zj.laboratory.pojo.vo.LwEntryOrderVo;
import com.zj.laboratory.service.LwNoticeService;
import com.zj.laboratory.utils.Page;
import com.zj.laboratory.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LwNoticeServiceImpl implements LwNoticeService {
    @Autowired
    private LwNoticeMapper lwNoticeMapper;
    @Autowired
    private LwUserMapper lwUserMapper;

    @Override
    public Page<LwNotice> getByPage(Page<LwNotice> page) {
        List<LwNotice> noticeList = lwNoticeMapper.getByPage(page);
        Integer pageCount = lwNoticeMapper.countByPage(page);
        page.setList(noticeList);
        page.setTotalCount(pageCount);
        return page;
    }

    @Override
    public LwNotice get(Long id) {
        return lwNoticeMapper.get(id);
    }

    @Override
    public void delete(Long id) {
        lwNoticeMapper.delete(id);
    }

    @Override
    public Page<LwNotice> getTopNoticeList(Page<LwNotice> page) {
        // 1.获取当前用户权限信息
        LoginUser loginUser = ShiroUtils.getLoginUser();
        //最新的用户信息--权限
        assert loginUser != null;
        LwUser newLwUser = lwUserMapper.getUpdateInfo(loginUser.getId());
        // 是否是管理员
        if (newLwUser.getReviewerType().equals(RoleEnum.REVIEWER_TYPE_USER.getType())
                &&Integer.parseInt(page.getParams().get("role").toString())==RoleEnum.ADMIN.getType()) {
            if (newLwUser.getLevel().equals(RoleEnum.LEVEL_ADMIN.getType())){//顶级审核员
                page.getParams().put("noticeType", StateEnums.NOTICE_ALL.getCode());
            }else if (newLwUser.getLevel().equals(RoleEnum.LEVEL_SERVICE_ORDER.getType())){//服务委托单审核员
                page.getParams().put("noticeType", StateEnums.NOTICE_SERVICE_ORDER.getCode());
            }else if (newLwUser.getLevel().equals(RoleEnum.LEVEL_ENTRY_ORDER.getType())){//进场单审核员
                page.getParams().put("noticeType", StateEnums.NOTICE_ENTRY_ORDER.getCode());
            }
        }
        List<LwNotice> lwNoticeList = lwNoticeMapper.getTopNoticeList(page);
        page.setList(lwNoticeList);
        return page;
    }
}
