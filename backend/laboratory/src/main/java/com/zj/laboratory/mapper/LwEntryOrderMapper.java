package com.zj.laboratory.mapper;


import com.zj.laboratory.pojo.LwEntry;
import com.zj.laboratory.pojo.LwOrder;
import com.zj.laboratory.pojo.vo.OrderMonthVo;
import com.zj.laboratory.pojo.vo.OrderPointVo;
import com.zj.laboratory.utils.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
     *
     * @param page
     * @return
     */
    List<LwEntry> getByPage(Page<LwEntry> page);

    /**
     * 条件查询条数
     *
     * @param page
     * @return
     */
    Integer getCountByPage(Page<LwEntry> page);

    /**
     * 查询更新信息
     *
     * @param id
     * @return
     */
    LwEntry getUpdateInfo(Long id);

    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    LwEntry get(Long id);

    /**
     * 删除
     *
     * @param lwEntry
     */
    int delete(LwEntry lwEntry);

    /**
     * 有效
     *
     * @param lwEntry
     */
    int enableById(LwEntry lwEntry);

    /**
     * 失效
     *
     * @param lwEntry
     */
    int disableById(LwEntry lwEntry);

    /**
     * 添加
     *
     * @param lwEntry
     */
    void save(LwEntry lwEntry);

    /**
     * 修改
     *
     * @param lwEntry
     */
    int update(LwEntry lwEntry);

    /**
     * 小程序端获取 进场单列表
     *
     * @return
     */
    List<LwEntry> getListByPage(Page<LwEntry> page);

    /**
     * 小程序端获取条数
     * @param page
     * @return
     */
    Integer getListCountByPage(Page<LwEntry> page);
    /**
     * 根据类型查询
     * @param type
     * @return
     */
//    Integer selectCountByType(@Param("type") Integer type);

    /**
     * 审核
     * @param lwEntry
     * @return
     */
    int examine(LwEntry lwEntry);

    /**
     * 查询月订单数量
     * @return
     */
    List<OrderMonthVo> monthOrder();

    /**
     * 查询订单各状态占比
     * @return
     */
    List<OrderPointVo> orderPoint();
}
