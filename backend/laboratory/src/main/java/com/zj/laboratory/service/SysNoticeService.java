package com.zj.laboratory.service;



import com.zj.laboratory.pojo.SysNotice;
import com.zj.laboratory.utils.Page;

import java.util.List;

public interface SysNoticeService {
    /**
     * 添加公告
     * @param sysNotice
     */
    void save(SysNotice sysNotice);

    /**
     * 修改公告
     * @param sysNotice
     */
    void update(SysNotice sysNotice);

    /**
     * 根据Id获取公告
     * @param id
     * @return
     */
    SysNotice get(Long id);

    /**
     * 根据Id删除公告
     * @param id
     */
    void delete(Long id);

    /**
     * 启用公告
     * @param id
     */
    void enable(Long id);

    /**
     * 禁用公告
     * @param id
     */
    void disable(Long id);

    /**
     * 分页查询公告
     * @param page
     * @return
     */
    Page<SysNotice> getByPage(Page<SysNotice> page);

    /**
     * 获取通知公告列表
     * @return
     */
    List<SysNotice> getNoticeList();
}
