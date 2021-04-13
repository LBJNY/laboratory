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
				<view class=" shadow main">
					<view class="item bg-white border-radius">
						<view class="big-title text-black">
							进场单详情
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
								<view class="flex-sub radius text-label-grey">进场人员</view>
								<view class="flex-sub radius text-right text-black">lbj,111,222</view>
							</view>
							<view class="flex item">
								<view class="flex-sub radius text-label-grey">邮箱</view>
								<view class="flex-sub radius text-right  text-black">20000@163.com</view>
							</view>
							<view class="border-bottom-dashed">
								<view class="radius text-label-grey">工作说明</view>
								<textarea class="text-black" value="aaaaaaaaaaaaa" placeholder=""
									style="height: 200rpx;width: 100%;margin: 20rpx 0rpx;background-color: #F9F9FB;padding: 20rpx;"
									disabled />
							</view>
							<view class="" style="margin-top: 20rpx;">
								<view class="radius text-label-grey">资源要求</view>
								<textarea class="text-black" value="aaaaaaaaaaaaa" placeholder=""
									style="height: 200rpx;width: 100%;margin: 20rpx 0rpx;background-color: #F9F9FB;padding: 20rpx;"
									disabled />
							</view>
							<view class="flex item border-bottom-dashed">
								<view class="flex-sub radius text-label-grey">进场时间</view>
								<view class="flex-sub radius text-right text-black">2019-9-9</view>
							</view>
							<view class="flex item ">
								<view class="flex-sub radius text-label-grey">进场单编号</view>
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
					<view class="item">
						<view class="big-title text-black">
							审核意见
						</view>
						<view class="detail bg-white border-radius">
							<view class="flex item">
								<view class="flex-sub radius text-label-grey">实验室审核意见</view>
								<radio-group name="" class="radio-group">
									<label>
										<radio class='blue radio radio-a' :class="radio=='B'?'checked':''"
											:checked="radio=='B'?true:false" value="B"></radio>
										<text>通过</text>
									</label>
									<label>
										<radio class='red radio radio-a' :class="radio=='B'?'checked':''"
											:checked="radio=='B'?true:false" value="B"></radio>
										<text>不通过</text>
									</label>
								</radio-group>
							</view>
						</view>
					</view>

				</view>
				<view class="flex justify-center next-step" style="">
					<button class="cu-btn lg text-white return" @click="returnBack">返回</button>
					<button class="cu-btn lg text-white bg-blue" @click="toUpdate(1)"
						style="margin-left: 20rpx;">确认修改</button>
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

			}
		},
		methods: {
			returnBack: function() {
				uni.navigateBack()
			},
			toUpdate(id) {
				uni.navigateTo({
					url: address.admin_entryOrder_examine
				})
			}
		}
	}
</script>

<style lang="scss">
	@import 'entryOrder-examine.scss';
</style>
