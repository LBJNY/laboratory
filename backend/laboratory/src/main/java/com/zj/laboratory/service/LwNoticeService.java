package com.zj.laboratory.service;

import com.zj.laboratory.pojo.LwNotice;
import com.zj.laboratory.pojo.vo.LwEntryOrderVo;
import com.zj.laboratory.utils.Page;

/**
 * 小程序通知类
 */
public interface LwNoticeService {

    /**
     * 分页获取
     * @param page
     * @return
     */
    Page<LwNotice> getByPage(Page<LwNotice> page);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    LwNotice get(Long id);

    /**
     * 根据id删除
     * @param id
     */
    void delete(Long id);

    /**
     *
     * @param page
     * @return
     */
    Page<LwNotice> getTopNoticeList(Page<LwNotice> page);
}
