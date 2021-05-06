import request from '@/utils/request'
const groupName = 'serviceOrder'
export default {
  /**
   * 分页
   */
  getByPage(page) {
    return request({
      url: `/${groupName}/getListByPage`,
      method: 'post',
      data: page
    })
  },
  /**
   * 有效
   */
  enableById(id) {
    return request({
      url: `/${groupName}/enableById/${id}`,
      method: 'put'
    })
  },
  /**
   * 失效
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
   * 保存
   */
   save(lwServiceOrder) {
    return request({
      url: `/${groupName}/save`,
      method: 'post',
      data: lwServiceOrder
    })
  },
  /**
   * 更新
   */
   update(lwServiceOrder) {
    return request({
      url: `/${groupName}/update`,
      method: 'put',
      data: lwServiceOrder
    })
  },
  /**
   * 获取用户订单条数
   */
  getCountList() {
  	return request({
  		url: `/${groupName}/getCountList`,
  		method: 'get'
  	})
  },
  /**
   * 获取所有订单条数
   */
  getServiceTotalCount() {
  	return request({
  		url: `/${groupName}/getServiceTotalCount`,
  		method: 'get'
  	})
  },
  /**
   * 获取所有订单条数
   */
  addFeedBack(lwOrderFeedBack) {
  	return request({
  		url: `/${groupName}/addFeedBack`,
  		method: 'post',
		data: lwOrderFeedBack
  	})
  }
}
