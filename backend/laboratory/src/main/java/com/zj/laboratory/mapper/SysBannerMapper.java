package com.zj.laboratory.mapper;


import com.zj.laboratory.pojo.SysBanner;
import com.zj.laboratory.utils.Page;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SysBannerMapper {

    /**
     * 保存
     *
     * @param sysBanner
     */
    void save(SysBanner sysBanner);

    /**
     * 修改
     *
     * @param sysBanner
     */
    void update(SysBanner sysBanner);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    SysBanner get(Long id);

    /**
     * 根据id删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    List<SysBanner> getByPage(Page<SysBanner> page);

    /**
     * 查询总数
     *
     * @param page
     * @return
     */
    int countByPage(Page<SysBanner> page);

    /**
     * 更新状态
     *
     * @param banner
     */
    void updateStatus(SysBanner banner);

    /**
     * 首页获取轮播图
     *
     * @return
     */
    List<Banner> getBannerList();

    /**
     * 点击量+1
     *
     * @param id
     */
    void updateClickCount(Long id);
}
