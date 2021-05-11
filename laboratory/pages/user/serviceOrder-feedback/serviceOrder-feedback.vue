<template>
	<view class="page">
		<view class="head bg-light-blue flex justify-between text-white">
			<view class="des">
				<view class="company">
					天翼智慧家庭科技有限公司
				</view>
				<view class="order-type">
					实验室服务委托单
				</view>
			</view>
			<view class="back" @click="">
				<text class="cuIcon-close lg"></text>
			</view>
		</view>
		<view class="content flex justify-center">
			<view class="container box-sizing">
				<view class="base bg-white commmon box-sizing">
					<view class="big-title border-bottom-solid text-black">
						<strong>默认信息</strong>
					</view>
					<view class="detail box-sizing">
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">服务委托单编号</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.serviceNo}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">智家对接人</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.serviceManager}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">提交申请日期</view>
							<view class="flex-sub radius text-right text-black">
								{{lwServiceOrder.currentDate | date-format}}
							</view>
						</view>
					</view>
				</view>
				<view class="appliction  commmon box-sizing">
					<view class="big-title text-black">
						<strong>审核意见</strong>
					</view>
					<view class="detail bg-white">
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">申请单位和部门审核意见</view>
							<view class="flex-sub radius text-right text-light-blue"
								v-if="lwServiceOrder.lwOrderAudit.deptAdvice==0">审核成功</view>
							<view class="flex-sub radius text-right text-orange"
								v-if="lwServiceOrder.lwOrderAudit.deptAdvice==1">审核失败</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">实验室审核意见</view>
							<view class="flex-sub radius text-right text-light-blue"
								v-if="lwServiceOrder.lwOrderAudit.labAdvice==0">审核成功</view>
							<view class="flex-sub radius text-right text-red"
								v-if="lwServiceOrder.lwOrderAudit.labAdvice==1">审核失败</view>
						</view>
						<view class="flex">
							<view class="flex-sub radius text-label-grey">创新合作部审核意见</view>
							<view class="flex-sub radius text-right text-light-blue"
								v-if="lwServiceOrder.lwOrderAudit.chAdvice==0" border-bottom-none>审核成功</view>
							<view class="flex-sub radius text-right text-red"
								v-if="lwServiceOrder.lwOrderAudit.chAdvice==1" border-bottom-none>审核失败</view>
						</view>
					</view>
				</view>
				<view class="appliction  commmon box-sizing">
					<view class="big-title text-black">
						<strong>服务工单完成记录</strong>
					</view>
					<view class="detail bg-white">
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">测试服务进度</view>
							<radio-group @change="serviceAdvanceChange" v-model="lwOrderFeedBack.serviceAdvance"
								class="radio-group">
								<label>
									<radio class="blue radio-a" :class="radio=='0'?'checked':''"
										:checked="radio=='0'?true:false" value="0"></radio>
									<text>全部完成</text>
								</label>
								<label>
									<radio class='blue radio-a' :class="radio=='1'?'checked':''"
										:checked="radio=='1'?true:false" value="1">
									</radio><text>部分完成</text>
								</label>
								<label>
									<radio class='red radio-a' :class="radio=='2'?'checked':''"
										:checked="radio=='2'?true:false" value="2">
									</radio><text>没有完成</text>
								</label>
							</radio-group>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">测试服务质量</view>
							<radio-group @change="serviceQualityChange" v-model="lwOrderFeedBack.serviceQuality"
								class="radio-group">
								<label style="margin-right: 48rpx;">
									<radio class='blue radio-a' :class="radio=='0'?'checked':''"
										:checked="radio=='0'?true:false" value="0">
									</radio>
									<text>很好</text>
								</label>
								<label style="margin-right: 48rpx;">
									<radio class='blue radio-a' :class="radio=='1'?'checked':''"
										:checked="radio=='1'?true:false" value="1">
									</radio><text>一般</text>
								</label>
								<label style="margin-right: 46rpx;">
									<radio class='red radio-a' :class="radio=='2'?'checked':''"
										:checked="radio=='2'?true:false" value="2">
									</radio><text>较差</text>
								</label>
							</radio-group>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">测试服务收费</view>
							<radio-group @change="serviceChargeChange" v-model="lwOrderFeedBack.serviceCharge"
								class="radio-group">
								<label>
									<radio class='blue radio-a' :class="radio=='0'?'checked':''"
										:checked="radio=='0'?true:false" value="0">
									</radio>
									<text>非常合理</text>
								</label>
								<label>
									<radio class='blue radio-a' :class="radio=='1'?'checked':''"
										:checked="radio=='1'?true:false" value="1">
									</radio><text>基本合理</text>
								</label>
								<label>
									<radio class='red radio-a' :class="radio=='2'?'checked':''"
										:checked="radio=='2'?true:false" value="2">
									</radio><text>不太合理</text>
								</label>
							</radio-group>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">整体满意度</view>
							<radio-group @change="satisfactionChange" v-model="lwOrderFeedBack.satisfaction"
								class="radio-group">
								<label>
									<radio class='blue radio-a' :class="radio=='0'?'checked':''"
										:checked="radio=='0'?true:false" value="0">
									</radio>
									<text>非常满意</text>
								</label>
								<label>
									<radio class='blue radio-a' :class="radio=='1'?'checked':''"
										:checked="radio=='1'?true:false" value="1">
									</radio><text>基本满意</text>
								</label>
								<label>
									<radio class='red radio-a' :class="radio=='2'?'checked':''"
										:checked="radio=='2'?true:false" value="2">
									</radio><text>不太满意</text>
								</label>
							</radio-group>
						</view>
						<view class="flex item align-center">
							<view class="flex-sub radius text-label-grey">申请单位和部门确认</view>
							<input v-model="lwOrderFeedBack.signature" style="border: 1px solid #E5E5E5;width: 320rpx;padding: 8rpx;height: 60rpx;"
								placeholder="请输入" />
						</view>
						<view class="flex">
							<view class="flex-sub radius text-label-grey">完工日期</view>
							<view class="bg-white flex align-center" style="line-height: 34rpx; font-size: 26rpx;">
								<uni-datetime-picker style="width: 320rpx;" v-model="lwOrderFeedBack.completion">
								</uni-datetime-picker>
							</view>
						</view>
					</view>

				</view>
			</view>
		</view>
		<view class="flex justify-center next-step" style="">
			<view class="container justify-between flex">
				<button class="cu-btn lg text-white return" @click="returnBack">返回</button>
				<button class="cu-btn lg text-white bg-blue confirm" @click="add">确认提交</button>
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
				// 服务委托单详情
				lwServiceOrder: {},
				activeId: null,
				// 反馈信息
				lwOrderFeedBack: {}
			}
		},
		onLoad(params) {
			this.activeId = params.activeId
			this.getById(params.activeId)
		},

		methods: {
			/**
			 * 返回
			 */
			returnBack: function() {
				uni.navigateBack()
			},
			// 根据id查询
			getById(id) {
				serviceOrderApi.get(id).then(res => {
					this.lwServiceOrder = res.data
					this.$set(this.lwOrderFeedBack, 'serviceNo', this.lwServiceOrder.serviceNo)
					console.log(res.data)
				})
			},
			// 添加反馈
			add() {
				console.log(this.lwOrderFeedBack)
				serviceOrderApi.addFeedBack(this.lwOrderFeedBack).then(res => {
					if(res.code==20000){
						uni.switchTab({
							url: address.serviceOrder
						})
					}
				})
			},
			serviceAdvanceChange(event) {
				let serviceAdvance = event.detail.value
				this.$set(this.lwOrderFeedBack, 'serviceAdvance', serviceAdvance)
			},
			serviceQualityChange(event) {
				let serviceQuality = event.detail.value
				this.$set(this.lwOrderFeedBack, 'serviceQuality', serviceQuality)
			},
			serviceChargeChange(event) {
				let serviceCharge = event.detail.value
				this.$set(this.lwOrderFeedBack, 'serviceCharge', serviceCharge)
			},
			satisfactionChange(event) {
				let satisfaction = event.detail.value
				this.$set(this.lwOrderFeedBack, 'satisfaction', satisfaction)
			}
		}
	}
</script>


<style lang="scss">
	@import 'serviceOrder-feedback.scss';
</style>
