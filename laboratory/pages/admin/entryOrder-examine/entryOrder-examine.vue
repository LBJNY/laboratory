<template>
	<view class="page">
		<view class="head bg-light-blue flex justify-between text-white">
			<view class="des box-sizing ">
				<view class="company">
					天翼智慧家庭科技有限公司
				</view>
				<view class="order-type">
					实验室进场单
				</view>
			</view>
		</view>
		<view class="content flex justify-center">
			<view class="container">
				<view class=" shadow main">
					<view class="item bg-white border-radius">
						<view class="big-title text-black">
							进场单详情
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
						</view>
					</view>
					<view class="item">
						<view class="big-title text-black">
							审核意见
						</view>
						<view class="detail bg-white border-radius">
							<view class="flex item">
								<view class="flex-sub radius text-label-grey">实验室审核意见</view>
								<radio-group class="radio-group" @change="examineChange">
									<label>
										<radio class='blue radio radio-a' :class="radio==1?'checked':''"
											:checked="radio==1?true:false" value="1"></radio>
										<text>通过</text>
									</label>
									<label>
										<radio class='red radio radio-a' :class="radio==2?'checked':''"
											:checked="radio==2?true:false" value="2"></radio>
										<text>不通过</text>
									</label>
								</radio-group>
							</view>
						</view>
					</view>

				</view>
				<view class="flex justify-center next-step" style="">
					<button class="cu-btn lg text-white return" @click="returnBack">返回</button>
					<button class="cu-btn lg text-white bg-blue" @click="toExamine"
						style="margin-left: 20rpx;">确认审核</button>
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
				// 审核进场单对象
				exLwEntryOrder: {},
				// 订单id
				activeId: null,
				// 审核结果
				radio: null
			}
		},
		onLoad(params) {
			this.activeId = params.activeId
			this.getById(params.activeId)
		},
		methods: {
			returnBack: function() {
				uni.navigateBack()
			},
			// 单选按钮点击
			examineChange(event) {
				var verifyStatus = event.detail.value
				this.radio = verifyStatus
				this.$set(this.exLwEntryOrder, 'verifyStatus', verifyStatus)
			},
			// 根据id查询
			getById(id) {
				entryOrderApi.get(id).then(res => {
					this.lwEntryOrder = res.data
					this.$set(this.exLwEntryOrder, 'id', this.lwEntryOrder.id)
				})
			},
			// 审核
			toExamine() {
				console.log(this.lwEntryOrder)
				if (this.radio !== null) {
					entryOrderApi.examine(this.exLwEntryOrder).then(res => {
						uni.showToast({
							title: '审核成功!',
							duration: 3000,
							success() {
								uni.switchTab({
									url: address.entryOrder
								})
							}
						})
					})
				}else{
					uni.showToast({
						title: '请选择审核意见!',
						duration: 500,
						icon: 'none',
					})
				}
			}
		}
	}
</script>

<style lang="scss">
	@import 'entryOrder-examine.scss';
</style>
