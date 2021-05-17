<template>
	<view class="page box-sizing">
		<!-- 顶部图片Begin -->
		<view class="banner center">
			<image style="width: 94%;" mode="widthFix" src="../../static/image/banner.jpg" @error="imageError"></image>
		</view>
		<!-- 顶部图片End -->
		<!-- 订单数量-添加Begin -->
		<view class="flex numbers">
			<view class="flex-sub bg-white margin-xs radius number box-sizing shadow ">
				<view class="flex solid-bottom align-center box-sizing">
					<view class=" padding-xs margin-xs radius basis-xl left-text box-sizing">
						<view class="grid col-1 align-start" :class="pageType>0?'text-center':''">
							<!--管理员居中 text-center -->
							<view class="flex solid-bottom align-center box-sizing info"
								:class="pageType>0?'justify-content-center':''">
								<!--管理界面居中 style="justify-content: center;" -->
								<image src="../../static/image/entrust.jpg" mode="aspectFit" class="img"></image>
								<view class="cu-item txt">{{serviceOrderStatistic.serviceCount}}</view>
							</view>
							<view class="des">服务委托单总数</view>
						</view>
					</view>
					<view class="cu-item　right-icon" v-if="pageType==0">
						<view class="lg text-blue cuIcon-roundaddfill addIcon" @click="toAddServiceOrder"></view>
					</view>
				</view>
			</view>
			<view class="flex-sub bg-white margin-xs radius number shadow">
				<view class="flex solid-bottom align-center box-sizing">
					<view class=" padding-xs margin-xs radius basis-xl left-text box-sizing">
						<view class="grid col-1 align-start" :class="pageType>0?'text-center':''">
							<view class="flex solid-bottom align-center box-sizing info"
								:class="pageType>0?'justify-content-center':''">
								<image src="../../static/image/entry.jpg" mode="aspectFit" class="img"></image>
								<view class="cu-item txt">{{entryOrderStatistic.entryCount}}</view>
							</view>
							<view class="des">进场单总数</view>
						</view>
					</view>
					<view class="cu-item　right-icon" v-if="pageType==0">
						<view class="lg text-blue cuIcon-roundaddfill addIcon" @click="toAddEntryOrder"></view>
					</view>
				</view>
			</view>
		</view>
		<!-- 订单数量-添加End -->
		<!-- 通知Begin -->
		<view class="inform">
			<view class="inform-info flex">
				<view class="picture flex align-center">
					<image src="/static/image/gg_ico.png" mode="widthFix"></image>
				</view>
				<view class="info">
					<swiper class="swiper" :circular="true" :vertical="true" :indicator-dots="false" :autoplay="true"
						:interval="3000" :duration="1000" v-if="noticeList.length>0">
						<swiper-item class="flex align-center" v-for="item in  noticeList" :key='item.id'>
							<view class="swiper-item" @click="getNoticeInfo(item.noticeId)">
								<text class="one-omit">{{item.noticeTitle}}</text>
							</view>
						</swiper-item>
					</swiper>
				</view>
			</view>
		</view>
		<!-- 通知End -->
		<view class="bottom ">
			<view class="title">
				今日订单
			</view>
			<view class="content bg-white">
				<scroll-view scroll-x class="bg-white nav" style="border-radius: 20rpx;">
					<view class="flex text-center">
						<view class="cu-item flex-sub" :class="0==TabCur?'text-blue cur':'text-light-gray cur'"
							@tap="tabSelect" :data-id="0">
							<view>
								服务委托单
							</view>
						</view>
						<view class="cu-item flex-sub" :class="1==TabCur?'text-blue cur':'text-light-gray cur'"
							@tap="tabSelect" :data-id="1">
							进场单
						</view>
					</view>
				</scroll-view>
				<view class="order box-sizing">
					<view class="serviceOrder" :class="TabCur==0?'':'display-none'">
						<view class="user" v-if="pageType==0">
							<scroll-view class="scroll-view_H" scroll-x="true" @scrolltolower="serviceOrderToLower"
								scroll-left="120" v-if="serviceOrderList.length>0">
								<view id="demo1" class="listItem shadow item" v-for="item in serviceOrderList"
									:key="item.id">
									<view class="bg bg-blue" v-if="item.verifyStatus==3"></view>
									<view class="bg bg-red" v-else-if="item.verifyStatus==4"></view>
									<view class="bg bg-orange" v-else></view>
									<view class="headItem flex justify-between align-center">
										<view class="serviceNo">{{item.serviceNo}}</view>
										<view class="del flex box-sizing align-center justify-center">
											<button class="cu-btn block text-gray bg-white delete btn" :id="item.id"
												@click="del">
												<text class="lg text-gray cuIcon-deletefill addIcon"></text> 删除</button>
										</view>
									</view>
									<view class='divider' style="padding-bottom: 10rpx;" />
									<uni-list class="middle box-sizing">
										<view>智家接口人：{{item.serviceManager}}</view>
										<view>单位部门名称：{{item.deptName}}</view>
										<view>服务项目名称：{{item.projName}}</view>
									</uni-list>
									<view class='divider' style="padding-top: 10rpx;" />
									<view class="bott flex justify-between align-center">
										<view class="status text-blue" v-if="item.verifyStatus==3">审核通过</view>
										<view class="status text-red" v-else-if="item.verifyStatus==4">
											{{item.verifyStatusName}}
										</view>
										<view class="status text-orange" v-else>{{item.verifyStatusName}}</view>
										<view class="btnT">
											<button class="cu-btn round bg-white blue text-blue"
												style="border: 1rpx #0081FF solid !important;line-height: 60rpx;"
												:id="item.id" @click="toServiceOrderInfo">查看详情</button>
										</view>
									</view>
								</view>
							</scroll-view>
							<view class="order-none text-label-grey" v-else>
								暂无订单哦~~~
							</view>
						</view>
						<view v-else>
							<view class="admin" v-if="level==0||level==2">
								<view v-if="serviceOrderList.length>0">
									<view class="listItem" v-for="item in serviceOrderList" :key="item.id">
										<view class="item">
											<view class="bg bg-blue" v-if="item.verifyStatus==3"></view>
											<view class="bg bg-red" v-else-if="item.verifyStatus==4"></view>
											<view class="bg bg-orange" v-else></view>
											<view class="adminInfo flex justify-between">
												<view class="serviceNo">{{item.serviceNo}}</view>
												<view class="data text-label-grey">
													提交日期：{{item.currentDate | date-format }}</view>
											</view>
											<view class="bot flex justify-between">
												<view class="status text-blue" v-if="item.verifyStatus==3">审核通过</view>
												<view class="status text-red" v-else-if="item.verifyStatus==4">
													{{item.verifyStatusName}}
												</view>
												<view class="status text-orange" v-else>{{item.verifyStatusName}}</view>
												<view class="btn">
													<button class="cu-btn round bg-white blue text-blue"
														style="border: 1rpx #0081FF solid !important;height: 50rpx;"
														@click="toServiceOrderExamine" :id="item.id"
														v-if="item.verifyStatus<3">点击审核</button>
													<button class="cu-btn round bg-white blue text-blue"
														style="border: 1rpx #0081FF solid !important;height: 50rpx;"
														:id="item.id" @click="toAdminServiceOrderInfo"
														v-if="item.verifyStatus>=3">查看详情</button>
												</view>
											</view>
										</view>
									</view>
								</view>
								<view class="order-none text-label-grey" v-else>
									暂无订单哦~~~
								</view>
							</view>
							<view class="order-none text-label-grey" v-else>
								暂无权限哦~~~
							</view>
						</view>
					</view>
					<view class="entryOrder" :class="TabCur==1?'':'display-none'">
						<view class="user" v-if="pageType==0">
							<scroll-view class="scroll-view_H" scroll-x="true" @scrolltolower="entryOrderToLower"
								scroll-left="120" v-if="entryOrderList.length>0">
								<view class="listItem item shadow" v-for="item in entryOrderList" :key="item.id">
									<view class="bg bg-blue" v-if="item.verifyStatus==1"></view>
									<view class="bg bg-red" v-else-if="item.verifyStatus==2"></view>
									<view class="bg bg-orange" v-else></view>
									<view class="headItem flex justify-between align-center">
										<view class="entryNo">{{item.entryNo}}</view>
										<view class="del flex box-sizing align-center justify-center">
											<button class="cu-btn block text-gray bg-white delete btn" :id="item.id"
												@click="del">
												<text class="lg text-gray cuIcon-deletefill addIcon"></text> 删除</button>
										</view>
									</view>
									<view class='divider' style="padding-bottom: 10rpx;" />
									<uni-list class="middle box-sizing">
										<view>智家接口人：{{item.entryManager}}</view>
										<view>单位部门名称：{{item.deptName}}</view>
										<view>服务项目名称：{{item.projName}}</view>
									</uni-list>
									<view class='divider' style="padding-top: 10rpx;" />
									<view class="bott flex justify-between align-center">
										<view class="status text-blue" v-if="item.verifyStatus==1">审核通过</view>
										<view class="status text-red" v-else-if="item.verifyStatus==2">
											{{item.verifyStatusName}}
										</view>
										<view class="status text-orange" v-else>{{item.verifyStatusName}}</view>
										<view class="btnT">
											<button class="cu-btn round bg-white blue text-blue"
												style="border: 1rpx #0081FF solid !important;line-height: 60rpx;"
												:id="item.id" @click="toEntryOrderExamine">查看详情</button>
										</view>
									</view>
								</view>
							</scroll-view>
							<view class="order-none text-label-grey" v-else>
								暂无订单哦~~~
							</view>
						</view>
						<view v-else>
							<view class="admin" v-if="level==0||level==3">
								<view v-if="entryOrderList.length>0">
									<view class="listItem" v-for="item in entryOrderList" :key="item.id">
										<view class="item">
											<view class="bg bg-blue" v-if="item.verifyStatus==1"></view>
											<view class="bg bg-red" v-else-if="item.verifyStatus==2"></view>
											<view class="bg bg-orange" v-else></view>
											<view class="adminInfo flex justify-between">
												<view class="entryNo">{{item.entryNo}}</view>
												<view class="data text-label-grey">
													提交日期：{{item.currentDate | date-format}}</view>
											</view>
											<view class="bot flex justify-between">
												<view class="status text-blue" v-if="item.verifyStatus==1">审核通过</view>
												<view class="status text-red" v-else-if="item.verifyStatus==2">
													{{item.verifyStatusName}}
												</view>
												<view class="status text-orange" v-else>{{item.verifyStatusName}}</view>
												<view class="btn">
													<button class="cu-btn round bg-white blue text-blue"
														style="border: 1rpx #0081FF solid !important;height: 50rpx;"
														:id="item.id" @click="toAdminEntryOrderExamine"
														v-if="item.verifyStatus===0">点击审核</button>
													<button class="cu-btn round bg-white blue text-blue"
														style="border: 1rpx #0081FF solid !important;height: 50rpx;"
														:id="item.id" @click="toAdminEntryOrderInfo"
														v-else>查看详情</button>
												</view>
											</view>
										</view>
									</view>
								</view>
								<view class="order-none text-label-grey" v-else>
									暂无订单哦~~~
								</view>
							</view>
							<view class="order-none text-label-grey" v-else>
								暂无权限哦~~~
							</view>

						</view>
					</view>
				</view>
			</view>
		</view>
		<!-- <view class="bg-white" style="width: 100%;background-color: #FFFFFF;height: 1000rpx;">

		</view> -->
	</view>
</template>

<script>
	import address from 'utils/page-address.js';
	import serviceOrderApi from '@/api/lw-service-order';
	import entryOrderApi from '@/api/lw-entry-order';
	export default {
		data() {
			return {
				TabCur: 0,
				scrollLeft: 0,
				// 1:用户  2:服务委托单  3:进场单  0:顶级权限
				level: 1,
				// 服务委托单数量
				serviceOrderStatistic: {},
				// 进场单数量
				entryOrderStatistic: {},
				// 服务委托单列表
				serviceOrderList: [],
				// 进场单列表
				entryOrderList: [],
				// 通知信息列表
				noticeList: [],
				// 分页对象
				servicePage: {
					// 分页传参
					params: {
						role: 0,
						// currentDate:true
					},
					currentPage: 1,
					pageSize: 5
				},
				// 分页对象
				entryPage: {
					// 分页传参
					params: {
						role: 0,
						// currentDate:true
					},
					currentPage: 1,
					pageSize: 5
				},
				noticePage: {
					// 分页传参
					params: {
						role: 0,
					},
					currentPage: 1,
					pageSize: 5
				},
				// 页面类型
				pageType: 0
			};
		},
		onShow() {
			this.init()
		},
		onReachBottom() {
			if (this.pageType === this.role.admin_page_num) {
				if (this.TabCur === 0 && (this.level === this.role.service_order_reviewer || this.level === this.role
						.admin_reviewer)) {
					this.serviceOrderToBottom()
				} else if (this.TabCur === 1 && (this.level === this.role.entry_order_reviewer || this.level === this.role
						.admin_reviewer)) {
					this.entryOrderToBottom()
				}
			}
		},
		methods: {
			/*  日期处理*/
			dateFormat: function(row, column) {
				var date = row[column.property]
				if (date === undefined) {
					return ''
				}
				return this.$moment(date).format('YYYY-MM-DD HH:mm:ss')
			},
			// 初始化页面
			init() {
				// 获取当前页面类型 0:用户 1管理员
				this.pageType = uni.getStorageSync('pageType')
				console.log("pageType:" + this.pageType)
				if (this.pageType != this.role.user_page_num) {
					this.level = uni.getStorageSync('loginUser').level
					console.log('level:' + this.level)
				}
				this.serviceOrderList = []
				this.entryOrderList = []
				this.servicePage.params.role = uni.getStorageSync('pageType')
				this.entryPage.params.role = uni.getStorageSync('pageType')
				this.noticePage.params.role = uni.getStorageSync('pageType')
				this.getEntryOrderList()
				this.getEntryOrderNumber()
				this.getServiceOrderList()
				this.getServiceOrderNumber()
				//this.getNoticeList()
			},
			tabSelect(e) {
				this.TabCur = e.currentTarget.dataset.id;
				this.scrollLeft = (e.currentTarget.dataset.id - 1) * 60
				// uni.getSystemInfogetSystemInfo({
				// 	success: function(res) {
				// 		that.setData({
				// 			"wh": res.windowHeight
				// 		})
				// 	}
				// })
			},

			// 获取进场单数量
			getEntryOrderNumber() {
				if (this.pageType == this.role.admin_page_num) {
					entryOrderApi.getEntryTotalCount().then(res => {
						this.entryOrderStatistic = res.data
					})
				} else {
					entryOrderApi.getCountList().then(res => {
						this.entryOrderStatistic = res.data
					})
				}
			},
			// 获取进场单列表
			getEntryOrderList() {
				entryOrderApi.getByPage(this.entryPage).then(res => {
					this.entryPage = res.data
					if (this.entryOrderList.length === 0) {
						this.entryOrderList = res.data.list
					} else {
						this.entryOrderList = this.entryOrderList.concat(res.data.list)
					}
				})
			},
			// 获取服务委托单数量
			getServiceOrderNumber() {
				if (this.pageType == this.role.admin_page_num) {
					serviceOrderApi.getServiceTotalCount().then(res => {
						this.serviceOrderStatistic = res.data
					})
				} else {
					serviceOrderApi.getCountList().then(res => {
						this.serviceOrderStatistic = res.data
					})
				}
			},
			// 获取服务委托单列表
			getServiceOrderList() {
				serviceOrderApi.getByPage(this.servicePage).then(res => {
					this.servicePage = res.data
					if (this.serviceOrderList.length === 0) {
						this.serviceOrderList = res.data.list
					} else {
						this.serviceOrderList = this.serviceOrderList.concat(res.data.list)
					}
				})
			},
			// 服务委托单滑动到最右边
			serviceOrderToLower() {
				if (this.servicePage.list !== undefined) {
					if (this.servicePage.list.length === this.servicePage.pageSize) {
						this.servicePage.currentPage = this.servicePage.currentPage + 1
						this.getServiceOrderList()
					}
				}
			},
			entryOrderToLower() {
				if (this.entryPage.list !== undefined) {
					if (this.entryPage.list.length === this.entryPage.pageSize) {
						this.entryPage.currentPage = this.entryPage.currentPage + 1
						this.getEntryOrderList()
					}
				}
			},
			// 服务委托单滑动到底部
			serviceOrderToBottom() {
				if (this.servicePage.list !== undefined) {
					if (this.servicePage.list.length === this.servicePage.pageSize) {
						this.servicePage.currentPage = this.servicePage.currentPage + 1
						this.getServiceOrderList()
					}
				}
			},
			// 进场单滑动到底部
			entryOrderToBottom() {
				if (this.entryPage.list !== undefined) {
					if (this.entryPage.list.length === this.entryPage.pageSize) {
						this.entryPage.currentPage = this.entryPage.currentPage + 1
						this.getEntryOrderList()
					}
				}
			},
			// 获取公告列表
			getNoticeList() {
				serviceOrderApi.getByPage(this.servicePage).then(res => {
					this.noticeList = []

					this.noticeList = res.data
				})
			},

			/**
			 * 服务委托单---用户
			 */

			// 跳转到添加服务委托单页面---用户
			toAddServiceOrder() {
				uni.navigateTo({
					url: address.user_serviceOrder_save
				})
			},
			// 跳转到服务委托单审核详情---用户
			toServiceOrderInfo(event) {
				var id = event.currentTarget.id
				uni.navigateTo({
					url: address.user_serviceOrder_examine + '?activeId=' + id
				})
			},
			// 删除服务委托单---用户
			del(event) {
				var id = event.currentTarget.id
				serviceOrderApi.deleteById(id).then(res => {
					uni.showToast({
						title: '删除成功!',
						duration: 3000,
						success() {
							uni.switchTab({
								url: address.serviceOrder
							})
						}
					})
					this.init()
				}).catch(err => {
					uni.showToast({
						title: '删除失败,请稍后重试',
						duration: 2000,
						icon: none
					});
				})
			},


			/**
			 * 服务委托单---管理员
			 */

			// 跳转到服务委托单审核页面---管理员
			toServiceOrderExamine(event) {
				var id = event.currentTarget.id
				uni.navigateTo({
					url: address.admin_serviceOrder_examine + '?activeId=' + id
				})
			},
			// 跳转到服务委托单审核详情---管理员
			toAdminServiceOrderInfo(event) {
				var id = event.currentTarget.id
				uni.navigateTo({
					url: address.admin_serviceOrder_info + '?activeId=' + id
				})
			},

			/**
			 * 进场单---用户
			 */

			// 删除进场单---用户
			del(event) {
				var id = event.currentTarget.id
				entryOrderApi.deleteById(id).then(res => {
					uni.showToast({
						title: '删除成功!',
						duration: 3000,
						success() {
							uni.switchTab({
								url: address.entryOrder
							})
						}
					})
					this.init()
				}).catch(err => {
					uni.showToast({
						title: '删除失败,请稍后重试',
						duration: 2000,
						icon: none
					});
				})
			},

			// 跳转到添加进场单页面---用户
			toAddEntryOrder() {
				uni.navigateTo({
					url: address.user_entryOrder_save
				})
			},
			// 跳转到进场单审核---用户
			toEntryOrderExamine(event) {
				var id = event.currentTarget.id
				console.log(address.user_entryOrder_examine)
				uni.navigateTo({
					url: address.user_entryOrder_examine + '?activeId=' + id
				})
			},
			// 跳转到进场单详情-修改页面---用户
			toEntryOrderInfo(event) {
				var id = event.currentTarget.id
				uni.navigateTo({
					url: address.user_entryOrder_update + '?activeId=' + id
				})
			},

			/**
			 * 进场单---管理员
			 */
			// 跳转到订单详情页面---管理员
			toAdminEntryOrderInfo(event) {
				var id = event.currentTarget.id
				uni.navigateTo({
					url: address.admin_entryOrder_info + '?activeId=' + id
				})
			},
			// 跳转到审核页面---管理员
			toAdminEntryOrderExamine(event) {
				var id = event.currentTarget.id
				uni.navigateTo({
					url: address.admin_entryOrder_examine + '?activeId=' + id
				})
			}
		}
	}
</script>

<style scoped lang="scss">
	@import 'home.scss';

	.divider {
		align-self: center;
		width: 100%;
		color: #987cb9;
		size: 10;
	}

	.scroll-view_H {
		white-space: nowrap;
		width: 100%;
	}
</style>
<style>
	page {
		background: #FFFFFF !important;
	}
</style>
