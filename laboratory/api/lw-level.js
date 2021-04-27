import request from '@/utils/request'
const groupName = 'level'
export default {
  /**
   * 查询列表
   */
   getList() {
    return request({
      url: `/${groupName}/getList`,
      method: 'get'
    })
  }
}
