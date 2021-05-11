import request from '@/utils/request'
const groupName = 'notice'
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
	}
}
