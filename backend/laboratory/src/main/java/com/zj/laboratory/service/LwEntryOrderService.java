package com.zj.laboratory.service;

import com.zj.laboratory.pojo.LwEntry;
import com.zj.laboratory.pojo.vo.LwEntryOrderVo;
import com.zj.laboratory.utils.Page;

public interface LwEntryOrderService {
    /**
     * 分页
     * @param page
     * @return
     */
    Page<LwEntryOrderVo> getByPage(Page<LwEntry> page);

    /**
     * 根据d的获取信息
     * @param id
     * @return
     */
    LwEntryOrderVo get(Long id);

    /**
     * 根据id获取要修改的信息
     * @param id
     * @return
     */
    LwEntryOrderVo getUpdateInfo(Long id);

    /**
     * 根据id删除
     * @param id
     */
    void delete(Long id);

    /**
     * 有效
     * @param id
     */
    void enableById(Long id);

    /**
     * 失效
     * @param id
     */
    void disableById(Long id);

    /**
     * 添加
     * @param lwEntry
     */
    void save(LwEntry lwEntry);

    /**
     * 修改
     * @param lwEntry
     */
    void update(LwEntry lwEntry);
}
