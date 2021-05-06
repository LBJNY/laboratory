package com.zj.laboratory.mapper;


import com.zj.laboratory.pojo.LwOrderFeedback;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * 反馈信息表(lw_order_feedback)数据Mapper
 *
 * @author lbj
 * @since 2021-04-19 09:13:37
 * @description 由 Mybatisplus Code Generator 创建
*/
@Component
public interface LwOrderFeedbackMapper {

    /**
     * 根据serviceNo获取反馈信息
     * @param serviceNo
     * @return
     */
    LwOrderFeedback get(@Param("serviceNo") Long serviceNo);

    /**
     * 添加反馈
     * @param lwOrderFeedback
     */
    void save(LwOrderFeedback lwOrderFeedback);
}
