package com.zj.laboratory.mapper;


import com.zj.laboratory.pojo.LwUserStatistic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户统计信息(lw_user_statistic)数据Mapper
 *
 * @author lbj
 * @since 2021-04-19 13:36:47
*/
@Component
public interface LwUserStatisticMapper {

    /**
     * 添加用户统计对象
     * @param lwUserStatistic
     */
    void save(LwUserStatistic lwUserStatistic);

    /**
     * 根据用户ID查询
     * @param id
     * @return
     */
    LwUserStatistic getByUserId(Long id);

    /**
     * 更新
     * @param statistic
     */
    void update(LwUserStatistic statistic);

    /**
     * 获取用户数量前十
     * @return
     */
    List<LwUserStatistic> getTopStatistic();

    /**
     * 获取服务委托单条数
     * @return
     */
    LwUserStatistic getServcieOrderCountByUserId(@Param("userId") Long userId);
    /**
     *获取进场单条数
     * @return
     */
    LwUserStatistic getEntryOrderCountByUserId(@Param("userId")Long userId);

    /**
     * 增加登陆次数
     */
    void increaseLoginCount(@Param("userId")Long userId);

    /**
     * 增加服务委托单数量
     * @param userId
     */
    void increaseServiceOrderCount(@Param("userId")Long userId);

    /**
     * 增加服务委托单通过数量
     * @param userId
     */
    void increaseServiceOrderPassCount(@Param("userId")Long userId);

    /**
     * 增加服务委托单失败数量
     * @param userId
     */
    void increaseServiceOrderFailCount(@Param("userId")Long userId);

    /**
     * 增加服务进场单总数
     * @param userId
     */
    void increaseEntryOrderCount(@Param("userId")Long userId);

    /**
     * 增加进场单通过数量
     * @param userId
     */
    void increaseEntryOrderPassCount(@Param("userId")Long userId);

    /**
     * 增加进场单失败数量
     * @param userId
     */
    void increaseEntryOrderFailCount(@Param("userId")Long userId);


    /**
     * 查询服务委托单各项总条数
     * @return
     */
    LwUserStatistic getServiceTotolCount();
    /**
     * 查询进场单各项总条数
     * @return
     */
    LwUserStatistic getEntryTotolCount();
}
