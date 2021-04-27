package com.zj.laboratory.service;

import com.zj.laboratory.pojo.LwOrder;
import com.zj.laboratory.pojo.vo.LwServiceOrderVo;
import com.zj.laboratory.utils.Page;

public interface LwServiceOrderService {
    /**
     * 分页
     * @param page
     * @return
     */
    Page<LwServiceOrderVo> getByPage(Page<LwOrder> page);
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
     * 根据id获取
     * @param id
     * @return
     */
    LwServiceOrderVo get(Long id);

    /**
     * 添加
     * @param lwOrder
     */
    void save(LwOrder lwOrder);

    /**
     * 更新
     * @param lwOrder
     */
    void update(LwOrder lwOrder);
}
