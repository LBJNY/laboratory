import request from '@/utils/request'
const groupName = 'entryOrder'
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
	 * 添加
	 */
	save(lwEntryOrder) {
		return request({
			url: `/${groupName}/save`,
			method: 'post',
			data: lwEntryOrder
		})
	},
	/**
	 * 更新
	 */
	update(lwEntryOrder) {
		return request({
			url: `/${groupName}/update`,
			method: 'put',
			data: lwEntryOrder
		})
	},
	getCountList() {
		return request({
			url: `/${groupName}/getCountList`,
			method: 'get'
		})
	},
	/**
	 * 获取所有订单条数
	 */
	getEntryTotalCount() {
		return request({
			url: `/${groupName}/getEntryTotalCount`,
			method: 'get'
		})
	},
	/**
	 * 审核
	 * @param {Object} lwEntryOrder
	 */
	examine(lwEntryOrder){
		return request({
			url: `/${groupName}/examine`,
			method: 'put',
			data: lwEntryOrder
		})
	}
}
