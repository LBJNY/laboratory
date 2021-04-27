import request from '@/utils/request'
const groupName = 'fundSupport'
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
   * 更新用户权限,名称
   */
  update(lwFundSupport){
    return request({
      url: `/${groupName}/update`,
      method: 'put',
      data: lwFundSupport 
    })
  },
  /**
   * 添加
   */
   save(lwFundSupport){
    return request({
      url:`/${groupName}/save`,
      method:'post',
      data:lwFundSupport
    })
  },
  /**
   * 获取所有
   */
   getAll(){
    return request({
      url:`/${groupName}/getAll`,
      method:'get'
    })
  },
}
