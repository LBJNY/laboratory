package com.zj.laboratory.service;

import com.zj.laboratory.pojo.LwOrderFundSupport;
import com.zj.laboratory.pojo.LwOrderServiceType;
import com.zj.laboratory.utils.Page;

public interface LwOrderServiceTypeService {
    /**
     * 分页获取
     * @param page
     * @return
     */
    Page<LwOrderServiceType> getByPage(Page<LwOrderServiceType> page);

    /**
     * 更新
     * @param lwOrderServiceType
     */
    void update(LwOrderServiceType lwOrderServiceType);

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
     * @param lwOrderServiceType
     */
    void save(LwOrderServiceType lwOrderServiceType);

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
    LwOrderServiceType get(Integer id);
}
