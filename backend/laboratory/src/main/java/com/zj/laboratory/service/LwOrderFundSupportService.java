package com.zj.laboratory.service;

import com.zj.laboratory.pojo.LwOrderFundSupport;
import com.zj.laboratory.pojo.LwUser;
import com.zj.laboratory.utils.Page;

public interface LwOrderFundSupportService {
    /**
     * 分页获取
     * @param page
     * @return
     */
    Page<LwOrderFundSupport> getByPage(Page<LwOrderFundSupport> page);

    /**
     * 更新
     * @param lwOrderFundSupport
     */
    void update(LwOrderFundSupport lwOrderFundSupport);

    /**
     * 启用
     * @param id
     */
    void enableById(Integer id);

    /**
     * 禁用
     * @param id
     */
    void disableById(Integer id);

    /**
     * 添加
     * @param lwOrderFundSupport
     */
    void save(LwOrderFundSupport lwOrderFundSupport);

    /**
     * 删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    LwOrderFundSupport get(Integer id);
}
