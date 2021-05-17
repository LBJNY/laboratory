package com.zj.laboratory.service;

import com.zj.laboratory.pojo.*;
import com.zj.laboratory.pojo.vo.LwServiceOrderVo;
import com.zj.laboratory.pojo.vo.OrderMonthVo;
import com.zj.laboratory.pojo.vo.OrderPointVo;
import com.zj.laboratory.utils.Page;

import java.util.List;

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

    /**
     * 获取服务委托单列表
     * @param page
     * @return
     */
    Page<LwServiceOrderVo> getListByPage(Page<LwOrder> page);
    /**
     * 获取当前用户进场单有关条数
     * @return
     */
    LwUserStatistic getCountList();

    /**
     * 查询服务委托单总条数
     * @return
     */
    LwUserStatistic getServiceTotolCount();

    /**
     * 添加反馈
     * @param lwOrderFeedback
     */
    void save(LwOrderFeedback lwOrderFeedback);


    /**
     * 订单审核
     * @param lwOrderAudit
     */
    void examine(LwOrderAudit lwOrderAudit);

    /**
     * 查询月订单数量
     * @return
     */
    List<OrderMonthVo> monthOrder();

    /**
     * 各种状态占比
     * @return
     */
    List<OrderPointVo> orderPoint();


}
