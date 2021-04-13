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
							<view class="flex-sub radius text-right text-black">xxxxxxx123</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">智家接口人</view>
							<view class="flex-sub radius text-right text-black">杜宁馨</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">提交申请日期</view>
							<view class="flex-sub radius text-right text-black">2019-9-9</view>
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
							<view class="flex-sub radius text-right text-light-blue">审核通过</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">实验室审核意见</view>
							<view class="flex-sub radius text-right text-light-blue">审核通过</view>
						</view>
						<view class="flex">
							<view class="flex-sub radius text-label-grey">创新合作部审核意见</view>
							<view class="flex-sub radius text-right text-orange border-bottom-none">待审核</view>
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
	export default{
		data(){
			return{
				
			}
		},
		methods:{
			/**
			 * 返回
			 */
			returnBack: function(){
				uni.navigateBack()
			}
		}
	}
</script>


<style lang="scss">
	@import 'serviceOrder-examine.scss';
</style>
