package com.zj.laboratory.mapper;

import com.zj.laboratory.pojo.LwReviewerType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 审查员类型(lw_reviewer_type)数据Mapper
 *
 * @author lbj
 * @since 2021-04-20 09:09:43
*/
@Component
public interface LwReviewerTypeMapper  {
    /**
     * 获取列表
     * @return
     */
    List<LwReviewerType> getList();
}
