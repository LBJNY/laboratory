package com.zj.laboratory.mapper;

import com.zj.laboratory.pojo.LwReviewerType;
import com.zj.laboratory.pojo.LwUser;
import com.zj.laboratory.utils.Page;
import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 小程序用户表(lw_user)数据DAO
 *
 * @author lbj
 * @since 2021-04-19 09:13:37
 */
@Component
public interface LwUserMapper {
    /**
     * 根据openid查询
     *
     * @param openId
     * @return
     */
    LwUser getByOpenId(String openId);

    /**
     * 保存
     *
     * @param shopUser
     */
    void save(LwUser shopUser);

    /**
     * 更新登录信息（手机号、密码）
     *
     * @param shopUser
     */
    void updateLoginInfo(LwUser shopUser);

    /**
     * 根据手机号查询
     *
     * @param phone
     * @return
     */
    LwUser getByPhone(String phone);

    /**
     * 根据id，物理删除用户
     *
     * @param id
     */
    void clearById(Long id);


    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    List<LwUser> getByPage(Page<LwUser> page);

    /**
     * 查询总数
     *
     * @param page
     * @return
     */
    Integer getCountByPage(Page<LwUser> page);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    LwUser get(Long id);

    /**
     * 根据id删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 解封
     *
     * @param id
     */
    void enableById(Long id);

    /**
     * 封禁
     *
     * @param id
     */
    void disableById(Long id);

    /**
     * 根据用户ID查询
     *
     * @param userIds
     * @return
     */
    List<LwUser> getByIds(List<Long> userIds);

    /**
     * 更新用户权限
     * @param lwUser
     */
    void update(LwUser lwUser);

    /**
     * 获取更新信息
     * @param id
     * @return
     */
    LwUser getUpdateInfo(@Param("id") Long id);
}