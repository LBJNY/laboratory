package com.zj.laboratory.mapper;

import com.zj.laboratory.pojo.LwNotice;
import com.zj.laboratory.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 小程序通知信息
 */
@Component
public interface LwNoticeMapper {

    /**
     * 获取列表
     * @param page
     * @return
     */
    List<LwNotice> getByPage(Page<LwNotice> page);

    /**
     * 获取总条数
     * @param page
     * @return
     */
    Integer countByPage(Page<LwNotice> page);

    /**
     * 根据
     * @param id
     * @return
     */
    LwNotice get(Long id);

    /**
     * 保存
     * @param lwNotice
     */
    void save(LwNotice lwNotice);

    /**
     * 删除
     * @param id
     */
    void delete(Long id);

    /**
     * 观看状态--看过
     * @param id
     */
    void look(Long id);

    /**
     * 查询前十
     * @param page
     * @return
     */
    List<LwNotice> getTopNoticeList(Page<LwNotice> page);

    /**
     * 批量插入
     * @param list
     */
    void saveBatch(List<LwNotice> list);
}
