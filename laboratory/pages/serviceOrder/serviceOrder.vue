<template>
	<view class="page">
		<view class="top flex justify-center">
			<view class="content bg-white" :class="pageType===0?'content-user':'content-admin'">
				<view class="numbers flex justify-center text-center">
					<view class="flex-sub padding-sm margin-xs radius all">
						<view class="allNumber text-black number">
							{{serviceOrderStatistic.serviceCount}}
						</view>
						<view class="des text-gray">
							总数
						</view>
					</view>
					<view class="flex-sub padding-sm margin-xs radius approval">
						<view class="approvalNumber text-orange number">
							{{serviceOrderStatistic.serviceCount-serviceOrderStatistic.servicePassCount- serviceOrderStatistic.serviceFailCount}}
						</view>
						<view class="des text-gray">
							审批中
						</view>
					</view>
					<view class="flex-sub padding-sm margin-xs radius complete">
						<view class="approvalNumber text-blue number">
							{{serviceOrderStatistic.servicePassCount}}
						</view>
						<view class="des text-gray">
							已通过
						</view>
					</view>
				</view>
				<view class="btn add flex-direction flex align-center" v-if="pageType==0">
					<button class="cu-btn bg-blue xs" @click="toAddOrder">
						<view class="lg text-white cuIcon-roundaddfill addIcon"></view>添加服务委托单
					</button>
				</view>
			</view>
		</view>
		<view class="bottom">
			<view class="sort bg-white flex align-center" :class="pageType===0?'sort-user':'sort-admin'">
				<view class="list flex align-center" @click="handleSort(1)">
					<text :class="{'action':searchParam.sorted===1}">进度</text>
					<view class="icon_j">
						<text :class="{'action':searchParam.sorted===1 && searchParam.sortType == 1}"
							class="iconfont icon-sanjiao up"></text>
						<text :class="{'action':searchParam.sorted===1 && searchParam.sortType == 2}"
							class="iconfont icon-sanjiao down"></text>
					</view>
				</view>
				<view class="list flex align-center" @click="handleSort(2)">
					<text :class="{'action':searchParam.sorted===2}">时间</text>
					<view class="icon_j">
						<text :class="{'action':searchParam.sorted===2 && searchParam.sortType == 1}"
							class="iconfont icon-sanjiao up"></text>
						<text :class="{'action':searchParam.sorted===2 && searchParam.sortType == 2}"
							class="iconfont icon-sanjiao down"></text>
					</view>
				</view>
			</view>
			<view class="orderList">
				<view class="container" v-if="pageType==0">
					<view v-if="serviceOrderList.length>0">
						<view class="listItem align-center justify-center box-sizing" v-for="item in serviceOrderList"
							:key="item.id">
							<view class="bg bg-blue" v-if="item.verifyStatus==3"></view>
							<view class="bg bg-red" v-else-if="item.verifyStatus==4"></view>
							<view class="bg bg-orange" v-else></view>
							<view class="flex">
								<view class="basis-sm detail">
									<view class="orderId">{{item.serviceNo}}</view>
									<view class="orderTime">提交日期：{{item.currentDate | date-format}}</view>
								</view>
								<view class="basis-lg step" >
									<uni-steps :options="item.progress" :active="item.verifyStatus<3?1:2"
										v-if="item.verifyStatus==3" activeColor="#0081FF">
									</uni-steps>
									<uni-steps :options="item.progress" :active="item.verifyStatus<3?1:2"
										v-else-if="item.verifyStatus==4" activeColor="red">
									</uni-steps>
									<uni-steps :options="item.progress" :active="item.verifyStatus<3?1:2" v-else
										activeColor="orange">
									</uni-steps>
								</view>
							</view>
							<view class="bott flex justify-between">
								<view class="choose flex">
									<view class="flex-sub btnT" v-if="item.verifyStatus!=3">
										<button class="cu-btn round bg-white blue text-blue" :id="item.id" @click="toOrderUpdate">
											查看详情
										</button>
									</view>
									<view class="flex-sub btnT" v-if="item.verifyStatus<=3">
										<button class="cu-btn round bg-white blue text-blue" :id="item.id" @click="toOrderExamine">
											查看进度
										</button>
									</view>
									<view class="flex-sub btnT" v-if="item.verifyStatus==3 && item.isFeedBack==1">
										<button class="cu-btn round bg-white blue text-blue" :id="item.id" @click="toOrderFeedBack">
											提交反馈
										</button>
									</view>
								</view>
								<view class="del flex box-sizing align-center justify-center" >
									<button class="cu-btn block text-gray bg-white delete" :id="item.id" @click="del">
										<text class="lg text-gray cuIcon-deletefill addIcon"></text> 删除</button>
								</view>
							</view>
						</view>
					</view>
					<view class="order-none text-label-grey" v-else>
						暂无订单~~~
					</view>
				</view>
				<view v-else class="flex justify-center admin" style="width: 100%;">
					<view class="container" v-if="level==0||level==2">
						<view v-if="serviceOrderList.length>0" >
							<view class="listItem align-center justify-center box-sizing"
								v-for="item in serviceOrderList" :key="item.id">
								<view class="bg bg-blue" v-if="item.verifyStatus==3"></view>
								<view class="bg bg-red" v-else-if="item.verifyStatus==4"></view>
								<view class="bg bg-orange" v-else></view>
								<view class="flex justify-between">
									<view class="basis-sm detail">
										<view class="orderId">{{item.serviceNo}}</view>
										<view class="orderTime">提交日期：{{item.currentDate | date-format}}</view>
									</view>
									<view class="verifyStatus">
										<!-- <text class="bgColorOrange">待审核</text> -->
										<view class="status bg-blue text-white" v-if="item.verifyStatus==3">审核通过</view>
										<view class="status bg-red text-white" v-else-if="item.verifyStatus==4">
											{{item.verifyStatusName}}
										</view>
										<view class="status bg-orange text-white" v-else>{{item.verifyStatusName}}</view>
									</view>
								</view>
								<view class="bott flex justify-between">
									<view class="choose flex">
										<view class="flex-sub btnT"  v-if="item.verifyStatus<3">
											<button class="cu-btn round bg-white blue text-blue"
											:id="item.id"
												@click="toAdminEntryOrderExamine">
												点击审核
											</button>
										</view>
										<view class="flex-sub btnT"  v-if="item.verifyStatus<3">
											<button class="cu-btn round bg-white blue text-blue"
											:id="item.id"
												@click="toAdminEntryOrderInfo">
												查看进度
											</button>
										</view>
										<view class="flex-sub btnT"  v-if="item.verifyStatus>=3">
											<button class="cu-btn round bg-white blue text-blue"
											:id="item.id"
												@click="toAdminEntryOrderInfo">
												查看详情
											</button>
										</view>
									</view>
								</view>
							</view>
						</view>
						<view class="order-none text-label-grey" v-else>
							暂无订单~~~
						</view>
					</view>
					<view class="order-none text-label-grey" v-else>
						暂无权限哦~~~
					</view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import address from 'utils/page-address.js';
	import serviceOrderApi from '@/api/lw-service-order';
	export default {
		data() {
			return {
				// 1:用户  2:服务委托单  3:进场单  0:顶级权限
				level: 1,
				// 服务委托单数量
				serviceOrderStatistic: {},
				// 服务委托单列表
				serviceOrderList: [],
				// 分页对象
				servicePage: {
					// 分页传参
					params: {
						role: 0,
						// 约定 1:进度 2:日期
						sortColumn: 1,
						// 1 逆序 0正序
						sortMethod: 1
					},
					currentPage: 1,
					pageSize: 5,
				},
				// 页面类型
				pageType: 0,
				// 搜索参数
				searchParam: {
					// 排序列
					sorted: 1,
					// 正序倒序排列，1正序，2倒序
					sortType: 2
				}
			}
		},
		onShow() {
			this.init()
		},
		onReachBottom() {
			this.serviceOrderToBottom()
		},
		methods: {
			// 初始化页面
			init() {
				// 获取当前页面类型 0:用户 1管理员
				this.pageType = uni.getStorageSync('pageType')
				if (this.pageType != this.role.user_page_num) {
					this.level = uni.getStorageSync('loginUser').level
				}
				this.$set(this.servicePage, 'currentPage', 1)
				this.serviceOrderList = []
				this.servicePage.params.role = uni.getStorageSync('pageType')
				this.getServiceOrderList()
				this.getServiceOrderNumber()
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
					// console.log(this.serviceOrderList)
				})
			},
			// 服务委托单滑动到底部
			serviceOrderToBottom() {
				if (this.servicePage.list.length === this.servicePage.pageSize) {
					this.servicePage.currentPage = this.servicePage.currentPage + 1
					this.getServiceOrderList()
				}
			},
			/**
			 * 删除
			 */
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
			// 跳转到修改页面
			toOrderUpdate(event){
				console.log(11111)
				var id = event.currentTarget.id
				uni.navigateTo({
					url: address.user_serviceOrder_update + '?activeId=' + id
				})
			},
			// 处理排序
			handleSort(type) {
				let _this = this
				console.log(this.searchParam.sorted + "  " + this.searchParam.sortType)
				this.searchParam.sorted = type
				switch (type) {
					case 1:
						_this.servicePage.params.sortColumn = 1
						console.log(this.searchParam.sortType === 1)
						if (this.searchParam.sortType === 1) {
							this.searchParam.sortType = 2;
							_this.servicePage.params.sortMethod = 2
						} else {
							this.searchParam.sortType = 1;
							_this.servicePage.params.sortMethod = 1
						}
						break;
					case 2:
						_this.servicePage.params.sortColumn = 2
						if (this.searchParam.sortType === 1) {
							this.searchParam.sortType = 2;
							_this.servicePage.params.sortMethod = 2
						} else {
							this.searchParam.sortType = 1;
							_this.servicePage.params.sortMethod = 1
						}
						break;
				}
				this.servicePage.currentPage = 1
				this.serviceOrderList = []
				console.log(this.servicePage)
				this.getServiceOrderList()
			},
			/**
			 * 跳转到添加服务委托单页面
			 */
			toAddOrder() {
				uni.navigateTo({
					url: address.user_serviceOrder_save
				})
			},
			/**
			 * 跳转到服务委托单详情
			 */
			toOrderInfo(event) {
				var id=event.currentTarget.id
				uni.navigateTo({
					url: address.user_serviceOrder_info+'?activeId='+id
				})
			},
			/**
			 * 跳转到服务委托单审核详情---用户
			 */
			toOrderExamine(event) {
				var id=event.currentTarget.id
				uni.navigateTo({
					url: address.user_serviceOrder_examine+'?activeId='+id
				})
			},
			// 跳转到订单详情页面---管理员
			toAdminEntryOrderInfo(event) {
				var id = event.currentTarget.id
				uni.navigateTo({
					url: address.admin_serviceOrder_info + '?activeId=' + id
				})
			},
			// 跳转到审核页面---管理员
			toAdminEntryOrderExamine(event){
				var id = event.currentTarget.id
				uni.navigateTo({
					url: address.admin_serviceOrder_examine + '?activeId=' + id
				})
			},
			// 跳转到反馈页面---用户
			toOrderFeedBack(event){
				var id=event.currentTarget.id
				uni.navigateTo({
					url: address.user_serviceOrder_feedback+'?activeId='+id
				})
			},
		},
		mounted() {
			this.handleSort(1)
		}
	}
</script>

<style lang="scss">
	@import 'serviceOrder.scss';

	.uni-steps__row-title {
		font-size: 24rpx !important;
	}
	.delete {
		padding: 0px 10rpx;
		font-size: 28rpx;
		height: 60rpx;
	}
</style>
