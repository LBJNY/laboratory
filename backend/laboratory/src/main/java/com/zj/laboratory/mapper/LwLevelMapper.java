package com.zj.laboratory.mapper;


import com.zj.laboratory.pojo.LwLevel;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 审查等级表(lw_level)数据Mapper
 *
 * @author lbj
 * @since 2021-04-19 09:13:37
*/
@Component
public interface LwLevelMapper  {

    List<LwLevel> getList();
}
