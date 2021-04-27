import request from '@/utils/request'
const groupName = 'reviewerType'
export default {
  /**
   * 查询列表
   */
   getList() {
    return request({
      url: `/${groupName}/getList`,
      method: 'get'
    })
  },
}
