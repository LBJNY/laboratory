<template>
	<view class="page">
		<view class="top flex justify-center">
			<view class="content bg-white" :class="pageType===0?'content-user':'content-admin'">
				<view class="numbers flex justify-center text-center">
					<view class="flex-sub padding-sm margin-xs radius all">
						<view class="allNumber text-black number">
							{{entryOrderStatistic.entryCount}}
						</view>
						<view class="des text-gray">
							总数
						</view>
					</view>
					<view class="flex-sub padding-sm margin-xs radius approval">
						<view class="approvalNumber text-orange number">
							{{entryOrderStatistic.entryCount-entryOrderStatistic.entryPassCount- entryOrderStatistic.entryFailCount}}
						</view>
						<view class="des text-gray">
							审批中
						</view>
					</view>
					<view class="flex-sub padding-sm margin-xs radius complete">
						<view class="approvalNumber text-blue number">
							{{entryOrderStatistic.entryPassCount}}
						</view>
						<view class="des text-gray">
							已通过
						</view>
					</view>
				</view>
				<view class="btn add flex-direction flex align-center" v-if="pageType==0">
					<button class="cu-btn bg-blue xs" @click="toAddEntryOrder">
						<view class="lg text-white cuIcon-roundaddfill addIcon"></view>添加进场单
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
					<view v-if="entryOrderList.length>0">
						<view class="listItem align-center justify-center box-sizing" v-for="item in entryOrderList"
							:key="item.id">
							<view class="bg bg-blue" v-if="item.verifyStatus==1"></view>
							<view class="bg bg-red" v-else-if="item.verifyStatus==2"></view>
							<view class="bg bg-orange" v-else></view>
							<view class="flex">
								<view class="basis-sm detail">
									<view class="orderId">{{item.entryNo}}</view>
									<view class="orderTime">提交日期：{{item.currentDate | date-format}}</view>
								</view>
								<view class="basis-lg step">
									<uni-steps :options="item.progress" :active="1" v-if="item.verifyStatus==1"
										activeColor="#0081FF">
									</uni-steps>
									<uni-steps :options="item.progress" :active="2" v-else-if="item.verifyStatus==2"
										activeColor="red">
									</uni-steps>
									<uni-steps :options="item.progress" :active="0" v-else activeColor="orange">
									</uni-steps>
								</view>
							</view>
							<view class="bott flex justify-between">
								<view class="choose flex">
									<view class="flex-sub btnT">
										<button class="cu-btn round bg-white blue text-blue" :id="item.id"
											@click="toOrderExamine">
											查看详情
										</button>
									</view>
								</view>
								<view class="del flex box-sizing align-center justify-center">
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
					<view class="container" v-if="level==0||level==3">
						<view v-if="entryOrderList.length>0">
							<view class="listItem align-center justify-center box-sizing" v-for="item in entryOrderList"
								:key="item.id">
								<view class="bg bg-blue" v-if="item.verifyStatus==1"></view>
								<view class="bg bg-red" v-else-if="item.verifyStatus==2"></view>
								<view class="bg bg-orange" v-else></view>
								<view class="flex justify-between">
									<view class="basis-sm detail">
										<view class="orderId">{{item.entryNo}}</view>
										<view class="orderTime">提交日期：{{item.currentDate | date-format}}</view>
									</view>
									<view class="flex align-center status ">
										<view class="status bg-blue text-white" v-if="item.verifyStatus==1">审核通过</view>
										<view class="status bg-red text-white" v-else-if="item.verifyStatus==2">
											{{item.verifyStatusName}}
										</view>
										<view class="status bg-orange text-white" v-else>{{item.verifyStatusName}}
										</view>
									</view>
								</view>
								<view class="bott flex justify-between">
									<view class="choose flex">
										<view class="flex-sub btnT" v-if="item.verifyStatus==0">
											<button class="cu-btn round bg-white blue text-blue" :id="item.id"
												@click="toAdminEntryOrderExamine">
												点击审核
											</button>
										</view>
										<view class="flex-sub btnT">
											<button class="cu-btn round bg-white blue text-blue" @click="toAdminEntryOrderInfo"
												:id="item.id">
												查看进度
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
	import entryOrderApi from '@/api/lw-entry-order';
	export default {
		data() {
			return {
				// 搜索参数
				searchParam: {
					// 排序列
					sorted: 1,
					// 正序倒序排列，1正序，2倒序
					sortType: 2,
					// 当前页
					pageNumber: 1,
					// 每页条数
					pageSize: 10
				},
				// 1:用户  2:服务委托单  3:进场单  0:顶级权限
				level: 1,
				// 进场单数量
				entryOrderStatistic: {},
				// 进场单列表
				entryOrderList: [],
				// 分页对象
				entryPage: {
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
			}
		},
		onShow() {
			this.init()
		},
		onReachBottom() {
			this.entryOrderToBottom()
		},
		methods: {
			// 初始化页面
			init() {
				// 获取当前页面类型 0:用户 1管理员
				this.pageType = uni.getStorageSync('pageType')
				if (this.pageType != this.role.user_page_num) {
					this.level = uni.getStorageSync('loginUser').level
				}
				this.$set(this.entryPage, 'currentPage', 1)
				this.entryOrderList = []
				this.entryPage.params.role = uni.getStorageSync('pageType')
				this.getEntryOrderList()
				this.getEntryOrderNumber()
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
					console.log(res.data)
					if (this.entryOrderList.length === 0) {
						this.entryOrderList = res.data.list
					} else {
						this.entryOrderList = this.entryOrderList.concat(res.data.list)
					}
					// console.log(this.serviceOrderList)
				})
			},
			// 进场单滑动到底部
			entryOrderToBottom() {
				if (this.entryPage.list.length === this.entryPage.pageSize) {
					this.entryPage.currentPage = this.entryPage.currentPage + 1
					this.getEntryOrderList()
				}
			},
			/**
			 * 删除---用户
			 */
			del(event) {
				var id = event.currentTarget.id
				console.log(id)
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
			/**
			 * 跳转到添加进场单页面---用户
			 */
			toAddEntryOrder() {
				uni.navigateTo({
					url: address.user_entryOrder_save
				})
			},
			// 处理排序
			handleSort(type) {
				let _this = this
				console.log(this.searchParam.sorted + "  " + this.searchParam.sortType)
				this.searchParam.sorted = type
				switch (type) {
					case 1:
						_this.entryPage.params.sortColumn = 1
						console.log(this.searchParam.sortType === 1)
						if (this.searchParam.sortType === 1) {
							this.searchParam.sortType = 2;
							_this.entryPage.params.sortMethod = 2
						} else {
							this.searchParam.sortType = 1;
							_this.entryPage.params.sortMethod = 1
						}
						break;
					case 2:
						_this.entryPage.params.sortColumn = 2
						if (this.searchParam.sortType === 1) {
							this.searchParam.sortType = 2;
							_this.entryPage.params.sortMethod = 2
						} else {
							this.searchParam.sortType = 1;
							_this.entryPage.params.sortMethod = 1
						}
						break;
				}
				this.entryPage.currentPage = 1
				this.entryOrderList = []
				console.log(this.entryPage)
				this.getEntryOrderList()
			},
			/**
			 * 跳转到进场单审核详情---用户
			 */
			toOrderExamine(event) {
				var id = event.currentTarget.id
				console.log(address.user_entryOrder_examine)
				uni.navigateTo({
					url: address.user_entryOrder_examine + '?activeId=' + id
				})
			},
			/**
			 * 跳转到进场单审核详情---用户
			 */
			toOrderinfo(event) {
				var id = event.currentTarget.id
				console.log(address.user_entryOrder_update)
				uni.navigateTo({
					url: address.user_entryOrder_update + '?activeId=' + id
				})
			},

			// 跳转到订单详情页面---管理员
			toAdminEntryOrderInfo(event) {
				var id = event.currentTarget.id
				uni.navigateTo({
					url: address.admin_entryOrder_info + '?activeId=' + id
				})
			},
			// 跳转到审核页面---管理员
			toAdminEntryOrderExamine(event){
				var id = event.currentTarget.id
				uni.navigateTo({
					url: address.admin_entryOrder_examine + '?activeId=' + id
				})
			}
		},
		mounted() {
			this.handleSort(1)
		}
	}
</script>

<style lang="scss">
	@import 'entryOrder.scss';

	.delete {
		padding: 0px 10rpx;
		font-size: 28rpx;
		height: 60rpx;
	}
</style>
