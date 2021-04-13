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
						<view class="grid col-1 align-start" :class="role>0?'text-center':''">
							<!--管理员居中 text-center -->
							<view class="flex solid-bottom align-center box-sizing info"
								:class="role>0?'justify-content-center':''">
								<!--管理界面居中 style="justify-content: center;" -->
								<image src="../../static/image/entrust.jpg" mode="aspectFit" class="img"></image>
								<view class="cu-item txt">128</view>
							</view>
							<view class="des">服务委托单总数</view>
						</view>
					</view>
					<view class="cu-item　right-icon" v-if="role==0">
						<view class="lg text-blue cuIcon-roundaddfill addIcon" @click="toAddServiceOrder"></view>
					</view>
				</view>
			</view>
			<view class="flex-sub bg-white margin-xs radius number shadow">
				<view class="flex solid-bottom align-center box-sizing">
					<view class=" padding-xs margin-xs radius basis-xl left-text box-sizing">
						<view class="grid col-1 align-start" :class="role>0?'text-center':''">
							<view class="flex solid-bottom align-center box-sizing info"
								:class="role>0?'justify-content-center':''">
								<image src="../../static/image/entry.jpg" mode="aspectFit" class="img"></image>
								<view class="cu-item txt">256</view>
							</view>
							<view class="des">进场单总数</view>
						</view>
					</view>
					<view class="cu-item　right-icon" v-if="role==0">
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
						<view v-if="role==0">
							<scroll-view class="scroll-view_H" scroll-x="true" scroll-left="120"
								v-if="serviceOrderList!=null">
								<view class="listItem shadow">
									<view class="item">
										<view class="bg bg-blue"></view>
										<view class="headItem flex justify-between align-center">
											<view class="orderId">xxxxxxx.xxx</view>
											<view class="del flex box-sizing align-center justify-center">
												<view class="lg text-gray cuIcon-deletefill addIcon"></view>
												<view class="text-gray">删除</view>
											</view>
										</view>
										<view class='divider' style="padding-bottom: 10rpx;" />
										<uni-list class="middle box-sizing">
											<view>智家接口人：lbj</view>
											<view>单位部门名称：杭州微纳</view>
											<view>服务项目名称：IOT预算</view>
										</uni-list>
										<view class='divider' style="padding-top: 10rpx;" />
										<view class="bott flex justify-between align-center">
											<view class="status">进行中
											</view>
											<view class="btnT">
												<button class="cu-btn round bg-white blue text-blue"
													style="border: 1rpx #0081FF solid !important;line-height: 60rpx;"
													@click="toServiceOrderInfo(1)">查看详情</button>
											</view>
										</view>
									</view>
								</view>
							</scroll-view>
							<view class="order-none" v-else>
								暂无订单哦~~~
							</view>
						</view>
						<view v-if="role==1||role==3">
							<view v-if="serviceOrderList!=null">
								<view class="item" style="height: 264rpx;">
									<view class="bg bg-blue"></view>
									<view class="adminInfo">
										<view class="entryNo">aaa</view>
										<view class="currentTime">提交日期：2019-9-9</view>
									</view>
									<view class="bot">
										<view class="statusName">待审核
										</view>
										<view class="btn">
											<button class="cu-btn round bg-white blue text-blue"
												style="border: 1rpx #0081FF solid !important;line-height: 60rpx;"
												@click="toServiceOrderInfo(1)">点击审核</button>
										</view>
									</view>
								</view>
							</view>
							<view class="order-none" v-else>
								暂无订单哦~~~
							</view>
						</view>
						<view class="order-none" v-else>
							暂无权限哦~~~
						</view>
					</view>
					<view class="entryOrder" :class="TabCur==1?'':'display-none'">
						<scroll-view class="scroll-view_H" scroll-x="true" scroll-left="120">
							<view class="listItem shadow">
								<view class="item">
									<view class="bg bg-blue"></view>
									<view class="headItem flex justify-between align-center">
										<view class="orderId">xxxxxxx.xxx</view>
										<view class="del flex box-sizing align-center justify-center">
											<view class="lg text-gray cuIcon-deletefill addIcon"></view>
											<view class="text-gray">删除</view>
										</view>
									</view>
									<view class='divider' style="padding-bottom: 10rpx;" />
									<uni-list class="middle box-sizing">
										<view>智家接口人：lbj</view>
										<view>单位部门名称：杭州微纳</view>
										<view>服务项目名称：IOT预算</view>
									</uni-list>
									<view class='divider' style="padding-top: 10rpx;" />
									<view class="bott flex justify-between align-center">
										<view class="status">进行中
										</view>
										<view class="btnT">
											<button class="cu-btn round bg-white blue text-blue"
												style="border: 1rpx #0081FF solid !important;line-height: 60rpx;"
												@click="toEntryOrderInfo">查看详情</button>
										</view>
									</view>
								</view>
							</view>
						</scroll-view>

					</view>
				</view>
			</view>
		</view>
		<!-- <view class="bg-white" style="width: 100%;background-color: #FFFFFF;height: 1000rpx;">

		</view> -->
	</view>
</template>

<script>
	export default {
		data() {
			return {
				TabCur: 0,
				scrollLeft: 0,
				// 0:用户  1:服务委托单  2 进场单  3:顶级权限
				role: 1,
				// 服务委托单数量
				serviceOrderNumber: 0,
				// 进场单数量
				entryOrderNumber: 0,
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
			/**
			 * 跳转到添加服务委托单页面
			 */
			toAddServiceOrder() {
				uni.navigateTo({
					url: '../user/serviceOrder-save/serviceOrder-save'
				})
			},
			/**
			 * 删除服务委托单页
			 */
			toDeleteServiceOrder() {
				uni.navigateTo({
					url: '../user/serviceOrder-save/serviceOrder-save'
				})
			},
			/**
			 * 跳转到服务委托单详情
			 * @param {Object} id
			 */
			toServiceOrderInfo(id) {
				console.log(id)
				uni.navigateTo({
					url: '../user/serviceOrder-info/serviceOrder-info'
				})
			},
			/**
			 * 跳转到添加进场单页面
			 */
			toAddEntryOrder() {
				uni.navigateTo({
					url: '../user/entryOrder-save/entryOrder-save'
				})
			},
			/**
			 * 跳转到进场单详情页面
			 */
			toEntryOrderInfo(id) {
				console.log(id)
				uni.navigateTo({
					url: '../user/entryOrder-examine/entryOrder-examine'
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
	page{
		background: #FFFFFF !important;
	}
</style>
