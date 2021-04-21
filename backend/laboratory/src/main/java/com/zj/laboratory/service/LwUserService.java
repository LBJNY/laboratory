package com.zj.laboratory.service;



import com.zj.laboratory.pojo.LwUser;
import com.zj.laboratory.pojo.dto.LwUserBindDto;
import com.zj.laboratory.pojo.vo.LwUserStatisticVo;
import com.zj.laboratory.pojo.vo.LwUserVo;
import com.zj.laboratory.utils.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 小程序用户Service
 */
public interface LwUserService {
    /**
     * 根据openid查询
     *
     * @param openId
     * @return
     */
    LwUser getByOpenId(String openId);

    /**
     * 注册
     *
     * @param toLwUser
     */
    void register(LwUser toLwUser);

    /**
     * 绑定用户
     *
     * @param shopUserBindDto
     * @return
     */
    LwUser bindUser(LwUserBindDto shopUserBindDto);

    /**
     * 分页查询
     *
     * @param page
     * @return
     */
    Page<LwUser> getByPage(Page<LwUser> page);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    LwUserVo get(Long id);

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
     * 查询累计消费最多的前十个用户
     *
     * @return
     */
    List<LwUserStatisticVo> getTopStatistic();

    /**
     * 更新用户权限
     * @param lwUser
     */
    void update(LwUser lwUser);

    /**
     * 查询要更新的信息
     * @param id
     * @return
     */
    LwUserVo getUpdateInfo(Long id);
}
