import request from '@/utils/request'
const groupName = 'user'
export default {
  /**
   * 分页
   */
  getByPage(page) {
    return request({
      url: `/${groupName}/getByPage`,
      method: 'post',
      data: page
    })
  },
  /**
   * 启用
   */
  enableById(id) {
    return request({
      url: `/${groupName}/enableById/${id}`,
      method: 'put'
    })
  },
  /**
   * 禁用
   */
  disableById(id) {
    return request({
      url: `/${groupName}/disableById/${id}`,
      method: 'put'
    })
  },
  /**
   * 删除
   */
  deleteById(id) {
    return request({
      url: `/${groupName}/delete/${id}`,
      method: 'delete'
    })
  },
  /**
   * 根据id查询
   */
  get(id) {
    return request({
      url: `/${groupName}/get/${id}`,
      method: 'get'
    })
  },
  /**
   * 根据id查询更新信息
   */
   getUpdateInfo(id) {
    return request({
      url: `/${groupName}/getUpdateInfo/${id}`,
      method: 'get'
    })
  },
  /**
   * 更新用户权限,名称
   */
  update(lwUser){
    return request({
      url: `/${groupName}/update`,
      method: 'put',
      data: lwUser 
    })
  },
  /**
   * 根据订单编号查询审核人员列表
   */
   getReviewerList(serviceNo) {
    return request({
      url: `/${groupName}/getReviewerList/${serviceNo}`,
      method: 'get'
    })
  },
}
