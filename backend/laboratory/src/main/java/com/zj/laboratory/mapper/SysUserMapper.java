package com.zj.laboratory.mapper;


import com.zj.laboratory.pojo.SysUser;
import com.zj.laboratory.pojo.dto.UpdatePwdDto;
import com.zj.laboratory.utils.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SysUserMapper {

    /**
     * 更新指定用户的登陆时间--更新为当前时间
     * @param username 用户名
     */
    void updateLoginTime(String username);

    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    SysUser getByUserName(String username);

    /**
     * 添加用户
     * @param sysUser
     */
    void save(SysUser sysUser);

    /**
     * 更新用户信息
     * @param sysUser
     */
    void update(SysUser sysUser);

    /**
     * 删除用户信息--逻辑删除
     * @param id
     */
    void delete(Long id);

    /**
     * 根据用户id获取用户信息
     * @param id
     * @return
     */
    SysUser getById(Long id);

    /**
     * 更新用户启用状态
     * @param sysUser
     */
    void updateStatus(SysUser sysUser);

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<SysUser> getByPage(Page<SysUser> page);

    /**
     * 查询总条数
     * @param page
     * @return
     */
    Integer countByPage(Page<SysUser> page);


    /**
     * 修改密码
     * @param updatePwdDto
     */
    Long updatePwd(UpdatePwdDto updatePwdDto);
}
