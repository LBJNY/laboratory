<template>
	<view class="page">
		<view class="top flex justify-center">
			<view class="content bg-white content-admin">
				<view class="numbers flex justify-center text-center">
					<view class="flex-sub padding-sm margin-xs radius all">
						<view class="allNumber text-black number">
							123
						</view>
						<view class="des text-gray">
							总数
						</view>
					</view>
					<view class="flex-sub padding-sm margin-xs radius approval">
						<view class="approvalNumber text-orange number">
							123
						</view>
						<view class="des text-gray">
							审批中
						</view>
					</view>
					<view class="flex-sub padding-sm margin-xs radius complete">
						<view class="approvalNumber text-blue number">
							123
						</view>
						<view class="des text-gray">
							已通过
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="bottom">
			<view class="sort bg-white flex align-center sort-admin">
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
				<view class="container" v-if="role==2||role==3">
					<view class="listItem align-center justify-center box-sizing" v-if="entryOrderList!=null">
						<view class="bg bg-blue"></view>
						<view class="flex justify-between">
							<view class="basis-sm detail">
								<view class="orderId">xxxxxxxx</view>
								<view class="orderTime">提交日期：2019-9-9</view>
							</view>
							<view class="flex align-center status ">
								<text class="bgColorOrange">待审核</text>
							</view>
						</view>
						<view class="bott flex justify-between">
							<view class="choose flex">
								<view class="flex-sub btnT">
									<button class="cu-btn round bg-white blue text-blue" @click="toOrderExamine(id)">
										点击审核
									</button>
								</view>
								<view class="flex-sub btnT">
									<button class="cu-btn round bg-white blue text-blue" @click="toOrderinfo(1)">
										查看进度
									</button>
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
</template>

<script>
	import address from 'utils/page-address.js';
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
				// 当前用户角色
				role: 2,
				entryOrderList: [{
					id: 0
				}]
			}
		},

		methods: {
			// 处理排序
			handleSort(type) {
				console.log(type + " " + this.searchParam.sorted)
				this.searchParam.sorted = type
				switch (type) {
					case 1:
						if (this.searchParam.sortType === 1) {
							this.searchParam.sortType = 2;
						} else {
							this.searchParam.sortType = 1;
						}
						break;
					case 2:
						if (this.searchParam.sortType === 1) {
							this.searchParam.sortType = 2;
						} else {
							this.searchParam.sortType = 1;
						}
						break;
				}
			},
			/**
			 * 跳转到服务委托单审核
			 */
			toOrderExamine(id) {
				uni.navigateTo({
					url: address.admin_entryOrder_examine
				})
			},
			/**
			 * 跳转到服务委托单审核详情
			 */
			toOrderinfo(id) {
				uni.navigateTo({
					url: address.admin_entryOrder_info
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
</style>
