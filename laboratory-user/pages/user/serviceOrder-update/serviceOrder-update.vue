<template>
	<view class="page">
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
				<view class="bg-white shadow main">
					<view class="big-title text-black">
						委托单详情
					</view>
					<view class="detail">
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">单位和部门名称</view>
							<view class="flex-sub radius text-right text-black">智家</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">服务项目名称</view>
							<view class="flex-sub radius text-right text-black">IOT测试</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">联系人</view>
							<view class="flex-sub radius text-right text-black">杜宁馨</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">电话</view>
							<view class="flex-sub radius text-right text-black">17639842745</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">邮箱</view>
							<view class="flex-sub radius text-right  text-black">20000@163.com</view>
						</view>
						<view class="border-bottom-dashed">
							<view class="radius text-label-grey">服务内容</view>
							<textarea class="text-black" value="aaaaaaaaaaaaa" placeholder=""
								style="height: 200rpx;width: 100%;margin: 20rpx 0rpx;" disabled />
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">时间和周期要求</view>
							<view class="flex-sub radius text-right text-black">xxxxxxxxxxxxxx</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">场地和网络要求</view>
							<view class="flex-sub radius text-right text-black">xxxxxxxxxxx</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">平台和软件要求</view>
							<view class="flex-sub radius text-right  text-black">xxxxxxxxxxxxxxxxx</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">终端和仪表要求</view>
							<textarea class=" text-black text-right" value="aaaaaaaaaaaaa" placeholder=""
								style="height: 160rpx;width: 50%;" disabled />
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">服务人员要求</view>
							<view class="flex-sub radius text-right  text-black">高级1人</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">其他服务要求</view>
							<view class="flex-sub radius text-right text-black">无</view>
						</view>
						<view class="flex item border-top-dashed">
							<view class="flex-sub radius text-label-grey">服务类型</view>
							<view class="flex-sub radius text-right text-black">公司内部创新孵化项目</view>
						</view>
						<view class="flex item border-bottom-dashed">
							<view class="flex-sub radius text-label-grey">资金支持和费用承担</view>
							<view class="flex-sub radius text-right text-black">可承担外包人员成本费</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">服务委托单编号</view>
							<view class="flex-sub radius text-right text-black">xxx00001</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">智家对接人</view>
							<view class="flex-sub radius text-right text-black">杜宁馨</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">提交申请日期</view>
							<view class="flex-sub radius text-right text-black">2020-9-9</view>
						</view>
					</view>
				</view>
				<view class="flex justify-between next-step" style="">
					<button class="cu-btn lg text-white return" @click="returnBack">返回</button>
					<button class="cu-btn lg bg-blue update" @click="toUpdate">确认修改</button>
				</view>
			</view>

		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {}
		},
		methods: {
			toUpdate() {
				console.log(1111)
				uni.navigateTo({
					url: '/pages/user/serviceOrder-save/serviceOrder-save'
				})
			},
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
	@import 'serviceOrder-update.scss';
</style>
