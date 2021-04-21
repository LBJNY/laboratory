package com.zj.laboratory.mapper;


import com.zj.laboratory.pojo.LwUserStatistic;
import org.apache.ibatis.annotations.Mapper;
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
}
