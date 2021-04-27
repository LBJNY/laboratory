package com.zj.laboratory.mapper;


import com.zj.laboratory.pojo.LwEntry;
import com.zj.laboratory.utils.Page;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 进场单表(lw_entry)数据Mapper
 *
 * @author lbj
 * @since 2021-04-21 09:59:24
*/
@Component
public interface LwEntryOrderMapper {

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<LwEntry> getByPage(Page<LwEntry> page);

    /**
     * 条件查询条数
     * @param page
     * @return
     */
    Integer getCountByPage(Page<LwEntry> page);

    /**
     * 查询更新信息
     * @param id
     * @return
     */
    LwEntry getUpdateInfo(Long id);

    /**
     * 根据id查询详情
     * @param id
     * @return
     */
    LwEntry get(Long id);

    /**
     * 删除
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
     * 添加
     * @param lwEntry
     */
    void save(LwEntry lwEntry);

    /**
     * 修改
     * @param lwEntry
     */
    void update(LwEntry lwEntry);
}
