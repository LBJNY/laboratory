<template>
	<view class="step-confirm">
		<view class="head bg-light-blue flex justify-between text-white">
			<view class="des box-sizing ">
				<view class="company">
					天翼智慧家庭科技有限公司
				</view>
				<view class="order-type">
					实验室服务委托单
				</view>
			</view>
		</view>
		<view class="content flex justify-center">
			<view class="container">
				<view class=" shadow main">
					<view class="item bg-white border-radius">
						<view class="big-title text-black">
							委托单详情
						</view>
						<view class="detail">
							<view class="flex item">
								<view class="flex-sub radius text-label-grey">单位和部门名称</view>
								<view class="flex-sub radius text-right text-black">{{lwEntryOrder.deptName}}</view>
							</view>
							<view class="flex item">
								<view class="flex-sub radius text-label-grey">服务项目名称</view>
								<view class="flex-sub radius text-right text-black">{{lwEntryOrder.projName}}</view>
							</view>
							<view class="flex item">
								<view class="flex-sub radius text-label-grey">联系人</view>
								<view class="flex-sub radius text-right text-black">{{lwEntryOrder.contact}}</view>
							</view>
							<view class="flex item">
								<view class="flex-sub radius text-label-grey">电话</view>
								<view class="flex-sub radius text-right text-black">{{lwEntryOrder.tel}}</view>
							</view>
							<view class="flex item">
								<view class="flex-sub radius text-label-grey">进场人员</view>
								<view class="flex-sub radius text-right text-black">{{lwEntryOrder.staff}}</view>
							</view>
							<view class="flex item">
								<view class="flex-sub radius text-label-grey">邮箱</view>
								<view class="flex-sub radius text-right  text-black">{{lwEntryOrder.email}}</view>
							</view>
							<view class="border-bottom-dashed">
								<view class="radius text-label-grey">工作说明</view>
								<textarea class="text-black" v-model="lwEntryOrder.description"
									style="height: 200rpx;width: 100%;margin: 20rpx 0rpx;background-color: #F9F9FB;padding: 20rpx;"
									disabled />
							</view>
							<view class="" style="margin-top: 20rpx;">
								<view class="radius text-label-grey">资源要求</view>
								<textarea class="text-black" v-model="lwEntryOrder.requirement"
									style="height: 200rpx;width: 100%;margin: 20rpx 0rpx;background-color: #F9F9FB;padding: 20rpx;"
									disabled />
							</view>
							<view class="flex item border-bottom-dashed">
								<view class="flex-sub radius text-label-grey">进场时间</view>
								<view class="flex-sub radius text-right text-black">{{lwEntryOrder.sDate | date-format}}
								</view>
							</view>
							<view class="flex item ">
								<view class="flex-sub radius text-label-grey">进场单编号</view>
								<view class="flex-sub radius text-right text-black">{{lwEntryOrder.entryNo}}</view>
							</view>
							<view class="flex item">
								<view class="flex-sub radius text-label-grey">智家对接人</view>
								<view class="flex-sub radius text-right text-black">{{lwEntryOrder.entryManager}}</view>
							</view>
							<view class="flex item">
								<view class="flex-sub radius text-label-grey">提交申请日期</view>
								<view class="flex-sub radius text-right text-black">
									{{lwEntryOrder.currentDate|date-format}}
								</view>
							</view>
							<!-- <view class="item">

							</view> -->
						</view>
					</view>
					<view class="item">
						<view class="big-title text-black">
							审核意见
						</view>
						<view class="detail bg-white border-radius">
							<view class="flex item">
								<view class="flex-sub radius text-label-grey">实验室审核意见</view>
								<view class="flex-sub radius text-right text-orange"
									v-if="lwEntryOrder.verifyStatus==0">待审核</view>
								<view class="flex-sub radius text-right text-light-blue"
									v-if="lwEntryOrder.verifyStatus==1">审核通过</view>
								<view class="flex-sub radius text-right text-light-blue"
									v-if="lwEntryOrder.verifyStatus==2">审核失败</view>
							</view>
						</view>
					</view>

				</view>
				<view class="flex justify-center next-step" style="">
					<button class="cu-btn lg text-white return" @click="returnBack">返回</button>
					<button class="cu-btn lg text-white bg-blue" :id="lwEntryOrder.id" @click="toUpdate">点击修改</button>
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
				// 进场单详情
				lwEntryOrder: {},
				activeId: null
			}
		},
		onLoad(params) {
			this.activeId = params.activeId
			this.getById(params.activeId)
		},
		methods: {
			toUpdate() {
				console.log(1111)
				uni.navigateTo({
					url: address.user_entryOrder_save
				})
			},
			/**
			 * 返回
			 */
			returnBack: function() {
				uni.navigateBack()
			},
			// 根据id查询
			getById(id) {
				entryOrderApi.get(id).then(res => {
					this.lwEntryOrder = res.data
				})
			}
		}
	}
</script>

<style lang="scss">
	@import 'entryOrder-update.scss';
</style>
