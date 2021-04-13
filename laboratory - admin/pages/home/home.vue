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
						<view class="grid col-1 align-start text-center">
							<!--管理员居中 text-center -->
							<view class="flex solid-bottom align-center box-sizing info justify-content-center">
								<!--管理界面居中 style="justify-content: center;" -->
								<image src="../../static/image/entrust.jpg" mode="aspectFit" class="img"></image>
								<view class="cu-item txt">128</view>
							</view>
							<view class="des">服务委托单总数</view>
						</view>
					</view>
				</view>
			</view>
			<view class="flex-sub bg-white margin-xs radius number shadow">
				<view class="flex solid-bottom align-center box-sizing">
					<view class=" padding-xs margin-xs radius basis-xl left-text box-sizing">
						<view class="grid col-1 align-start text-center" :class="role>0?'':''">
							<view class="flex solid-bottom align-center box-sizing info justify-content-center">
								<image src="../../static/image/entry.jpg" mode="aspectFit" class="img"></image>
								<view class="cu-item txt">256</view>
							</view>
							<view class="des">进场单总数</view>
						</view>
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
						:interval="3000" :duration="1000">
						<!-- <swiper-item class="flex align-center" v-for="item in  noticeList" :key='item.noticeId'>
							<view class="swiper-item" @click="getNoticeInfo(item.noticeId)">
								<text class="one-omit">{{item.noticeTitle}}</text>
							</view>
						</swiper-item> -->
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

						<view class="admin" v-if="role==1||role==3">
							<view v-if="serviceOrderList!=null">
								<view class="listItem">
									<view class="item">
										<view class="bg bg-blue"></view>
										<view class="adminInfo flex justify-between">
											<view class="number">aaa</view>
											<view class="data text-label-grey">提交日期：2019-9-9</view>
										</view>
										<view class="bot flex justify-between">
											<view class="statusName text-blue">待审核
											</view>
											<view class="btn">
												<button class="cu-btn round bg-white blue text-blue"
													style="border: 1rpx #0081FF solid !important;height: 50rpx;"
													@click="toServiceOrderExamine(1)">点击审核</button>
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
					<view class="entryOrder" :class="TabCur==1?'':'display-none'">
						<view class="admin" v-if="role==2||role==3">
							<view v-if="entryOrderList!=null">
								<view class="listItem">
									<view class="item">
										<view class="bg bg-blue"></view>
										<view class="adminInfo flex justify-between">
											<view class="number">aaa</view>
											<view class="data text-label-grey">提交日期：2019-9-9</view>
										</view>
										<view class="bot flex justify-between">
											<view class="statusName text-blue">待审核
											</view>
											<view class="btn">
												<button class="cu-btn round bg-white blue text-blue"
													style="border: 1rpx #0081FF solid !important;height: 50rpx;"
													@click="toServiceOrderInfo(1)">点击审核</button>
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
</template>

<script>
	import address from 'utils/page-address.js';
	export default {
		data() {
			return {
				TabCur: 0,
				scrollLeft: 0,
				// 服务委托单数量
				serviceOrderNumber: 0,
				// 进场单数量
				entryOrderNumber: 0,
				//1:服务委托单 2:进场单  3:admin
				role: 3,
				// 服务委托单列表
				serviceOrderList: [{
					id: 1
				}],
				// 进场单列表
				entryOrderList: [{
					id: 1
				}]
			};
		},
		methods: {
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

			// 跳转到审核页面
			toServiceOrderExamine(id) {
				uni.navigateTo({
					url: address.admin_serviceOrder_examine
				})
			},
			/**
			 * 跳转到服务委托单详情
			 * @param {Object} id
			 */
			toServiceOrderInfo(id) {
				uni.navigateTo({
					url: address.user_serviceOrder_info
				})
			},

			/**
			 * 跳转到进场单详情页面
			 */
			toEntryOrderInfo(id) {
				console.log(id)
				uni.navigateTo({
					url: address.user_entryOrder_examine
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
</style>
<style>
	page {
		background: #FFFFFF !important;
	}
</style>
