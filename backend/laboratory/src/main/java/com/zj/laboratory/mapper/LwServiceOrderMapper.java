package com.zj.laboratory.mapper;


import com.zj.laboratory.pojo.LwEntry;
import com.zj.laboratory.pojo.LwOrder;
import com.zj.laboratory.utils.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 服务委托单表(lw_order)数据Mapper
 *
 * @author lbj
 * @since 2021-04-21 09:59:24
 */
@Component
public interface LwServiceOrderMapper {

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    List<LwOrder> getByPage(Page<LwOrder> page);

    /**
     * 条件查询条数
     *
     * @param page
     * @return
     */
    Integer getCountByPage(Page<LwOrder> page);

    /**
     * 根据id删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 有效
     *
     * @param id
     */
    void enableById(Long id);

    /**
     * 失效
     *
     * @param id
     */
    void disableById(Long id);

    /**
     * 根据id获取详细信息
     *
     * @param id
     * @return
     */
    LwOrder get(Long id);

    /**
     * 添加
     *
     * @param lwOrder
     */
    void save(LwOrder lwOrder);

    /**
     * 修改
     *
     * @param lwOrder
     */
    void update(LwOrder lwOrder);

    /**
     * 小程序端获取 服务委托单列表
     *
     * @return
     */
    List<LwOrder> getListByPage(Page<LwOrder> page);

    /**
     * 小程序端获取条数
     * @param page
     * @return
     */
    Integer getListCountByPage(Page<LwOrder> page);

    /**
     * 更新反馈状态
     * @param serviceNo
     */
    void updateFeedBack(@Param("serviceNo")String serviceNo);
}
