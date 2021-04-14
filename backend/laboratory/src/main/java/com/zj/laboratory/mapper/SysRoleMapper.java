package com.zj.laboratory.mapper;



import com.zj.laboratory.pojo.SysRole;
import com.zj.laboratory.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色信息表(sys_role)数据Mapper
 *
*/
@Component
public interface SysRoleMapper  {
    /**
     * 添加角色
     * @param sysRole
     */
    void save(SysRole sysRole);

    /**
     * 修改角色
     * @param sysRole
     */
    void update(SysRole sysRole);

    /**
     * 根据id删除角色
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id获取角色
     * @param id
     * @return
     */
    SysRole get(Long id);

    /**
     * 分页查询角色列表
     * @param page
     * @return
     */
    List<SysRole> getByPage(Page<SysRole> page);

    /**
     * 获取总条数
     * @param page
     * @return
     */
    Integer countByPage(Page<SysRole> page);

    /**
     * 根据角色ID查询角色信息
     * @param roleIds
     * @return
     */
    List<SysRole> getByIds(List<Long> roleIds);

    /**
     * 获取所有角色信息
     * @return
     */
    List<SysRole> getAll();
}
