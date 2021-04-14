package com.zj.laboratory.mapper;



import com.zj.laboratory.pojo.SysMenu;
import com.zj.laboratory.utils.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 菜单权限表(sys_menu)数据Mapper
 *
*/
@Component
public interface SysMenuMapper {

    /**
     * 根据父级菜单ID和名称查询
     * @param sysMenu
     * @return
     */
    SysMenu getByParentIdAndName(SysMenu sysMenu);

    /**
     * 添加
     * @param sysMenu
     */
    void save(SysMenu sysMenu);

    /**
     * 修改
     * @param sysMenu
     */
    void update(SysMenu sysMenu);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Long id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    SysMenu getById(Long id);

    /**
     * 查询总数
     * @param page
     * @return
     */
    Integer countByPage(Page<SysMenu> page);

    /**
     * 分页查询
     * @param page
     * @return
     */
    List<SysMenu> getByPage(Page<SysMenu> page);

    /**
     * 获取所有菜单
     * @return
     */
    List<SysMenu> getAll();

    /**
     * 根据角色Id获取所有菜单
     * @param roleId
     * @return
     */
    List<SysMenu> getRoleSelectMenu(Long roleId);

    /**
     * 根据用户ID查询启用中的菜单（权限不查）
     * @param id
     * @return
     */
    List<SysMenu> getEnableMenuByUserId(Long id);

    List<String> getMenuCodeByUserId(Long userId);
}
