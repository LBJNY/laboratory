package com.zj.laboratory.mapper;

import com.zj.laboratory.pojo.LwOrderFundSupport;
import com.zj.laboratory.pojo.LwOrderServiceType;
import com.zj.laboratory.utils.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 订单类型(lw_order_servicetype)数据Mapper
 *
 * @author lbj
 * @since 2021-04-20 09:09:43
 */
@Component
public interface LwOrderServiceTypeMapper {

    /**
     * 获取列表
     *
     * @param page
     * @return
     */
    List<LwOrderServiceType> getByPage(Page<LwOrderServiceType> page);

    /**
     * 获取条数
     *
     * @param page
     * @return
     */
    Integer getCountByPage(Page<LwOrderServiceType> page);

    /**
     * 更新信息
     *
     * @param lwOrderServiceType
     */
    void update(LwOrderServiceType lwOrderServiceType);

    /**
     * 解禁
     *
     * @param id
     */
    void enableById(@Param("id") Integer id);

    /**
     * 封禁
     *
     * @param id
     */
    void disableById(@Param("id") Integer id);

    /**
     * 添加
     *
     * @param lwOrderServiceType
     */
    void save(LwOrderServiceType lwOrderServiceType);

    /**
     * 删除
     *
     * @param id
     */
    void delete(@Param("id") Integer id);

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    LwOrderServiceType get(@Param("id") Integer id);

    /**
     * 获取所有
     */
    List<LwOrderServiceType> getAll();
}
