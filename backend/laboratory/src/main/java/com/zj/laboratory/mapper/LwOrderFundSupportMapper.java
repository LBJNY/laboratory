package com.zj.laboratory.mapper;


import com.zj.laboratory.pojo.LwOrderFundSupport;
import com.zj.laboratory.pojo.LwUser;
import com.zj.laboratory.utils.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 资金支持类型(lw_order_fundsupport)数据Mapper
 *
 * @author lbj
 * @since 2021-04-20 09:09:42
*/
@Component
public interface LwOrderFundSupportMapper {

    /**
     * 获取列表
     * @param page
     * @return
     */
    List<LwOrderFundSupport> getByPage(Page<LwOrderFundSupport> page);

    /**
     * 获取条数
     * @param page
     * @return
     */
    Integer getCountByPage(Page<LwOrderFundSupport> page);

    /**
     * 更新信息
     * @param lwOrderFundSupport
     */
    void update(LwOrderFundSupport lwOrderFundSupport);

    /**
     * 解禁
     * @param id
     */
    void enableById(@Param("id") Integer id);

    /**
     * 封禁
     * @param id
     */
    void disableById(@Param("id") Integer id);

    /**
     * 添加
     * @param lwOrderFundSupport
     */
    void save(LwOrderFundSupport lwOrderFundSupport);

    /**
     * 删除
     * @param id
     */
    void delete(@Param("id") Integer id);

    /**
     * 根据id获取
     * @param id
     * @return
     */
    LwOrderFundSupport get(@Param("id") Integer id);

    /**
     * 获取所有
     */
    List<LwOrderFundSupport> getAll();
}
