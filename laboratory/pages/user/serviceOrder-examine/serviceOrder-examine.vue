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
			<view class="back" @click="returnBack">
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
							<view class="flex-sub radius text-right text-orange"
								v-if="lwServiceOrder.lwOrderAudit.deptAdvice==0">待审核</view>
							<view class="flex-sub radius text-right text-light-blue"
								v-if="lwServiceOrder.lwOrderAudit.deptAdvice==1">审核成功</view>
							<view class="flex-sub radius text-right text-orange"
								v-if="lwServiceOrder.lwOrderAudit.deptAdvice==2">审核失败</view>
						</view>
						<view class="flex item" v-if="lwServiceOrder.lwOrderAudit.deptAdvice==1">
							<view class="flex-sub radius text-label-grey">实验室审核意见</view>
							<view class="flex-sub radius text-right text-light-blue"
								v-if="lwServiceOrder.lwOrderAudit.labAdvice==0">审核成功</view>
							<view class="flex-sub radius text-right text-red"
								v-if="lwServiceOrder.lwOrderAudit.labAdvice==1">审核失败</view>
						</view>
						<view class="flex" v-if="lwServiceOrder.lwOrderAudit.labAdvice==0">
							<view class="flex-sub radius text-label-grey">创新合作部审核意见</view>
							<view class="flex-sub radius text-right text-light-blue"
								v-if="lwServiceOrder.lwOrderAudit.chAdvice==0" border-bottom-none>审核成功</view>
							<view class="flex-sub radius text-right text-red"
								v-if="lwServiceOrder.lwOrderAudit.chAdvice==1" border-bottom-none>审核失败</view>
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="flex justify-center next-step" style="">
			<button class="cu-btn lg text-white return" @click="returnBack">返回</button>
		</view>
	</view>
</template>

<script>
	import serviceOrderApi from '@/api/lw-service-order';
	export default {
		data() {
			return {
				// 服务委托单详情
				lwServiceOrder: {},
				activeId: null
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
					console.log(res.data)
				})
			},
		}
	}
</script>


<style lang="scss">
	@import 'serviceOrder-examine.scss';
</style>
