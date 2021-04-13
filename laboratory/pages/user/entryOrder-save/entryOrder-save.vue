<template>
	<view class="page">
		<uni-forms ref="form" :value="formData" :rules="rules">
			<!-- 第一步:填写基础信息 begin -->
			<view class="step-first" :class="step==1?'':'display-none'">
				<view class="head bg-light-blue flex justify-between text-white">
					<view class="des">
						<view class="company">
							天翼智慧家庭科技有限公司
						</view>
						<view class="order-type">
							实验室进场单
						</view>
					</view>
					<view class="back" @click="">
						<text class="cuIcon-close lg"></text>
					</view>
				</view>
				<view class="content flex justify-center">
					<view class="container box-sizing">
						<view class="base bg-white commmon box-sizing">
							<view class="big-title">
								默认信息
							</view>
							<view class="detail box-sizing">
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										进场单编号
									</view>
									<input type="text" disabled class="text-right" value="xxxxxxx123" />
								</view>
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										智家接口人
									</view>
									<input type="text" disabled class="text-right" value="杜宁馨" />
								</view>
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										提交申请日期
									</view>
									<input type="text" disabled class="text-right" value="2019-9-9" />
								</view>
							</view>
						</view>
						<view class="appliction  commmon box-sizing">
							<view class="big-title">
								申请信息
							</view>
							<view class="detail bg-white">
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										单位和部门名称
									</view>
									<input type="text" class="" value="天翼智家" placeholder="请输入单位和部门名称" />
								</view>
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										服务项目名称
									</view>
									<input type="text" class="" value="zigbee测试" placeholder="请输入项目名称" />
								</view>
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										联系人
									</view>
									<input type="text" class="" value="lbj" placeholder="请输入联系人" />
								</view>
								<view class="flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										电话
									</view>
									<input type="text" class="" value="17639842742" placeholder="请输入电话" />
								</view>
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										邮箱
									</view>
									<input type="text" class="" value="20000000@163.com" placeholder="请输入邮箱" />
								</view>
								<view class="flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										进出人员
									</view>
									<input type="text" class="" value="lbj,zzz,ccc" placeholder="请输入进出人员姓名" />
								</view>
							</view>
						</view>
					</view>
				</view>
				<view class="flex justify-center next-step">
					<button class="cu-btn bg-blue margin-tb-sm lg" style="width: 40%;" @click="nextStep">下一步</button>
				</view>
			</view>
			<!-- 第一步:填写基础信息 end -->
			<!-- 第二步:服务内容填写 begin -->
			<view class="step-second step-common box-sizing flex justify-center" :class="step==2?'':'display-none'">
				<view class="container box-sizing ">
					<view class="detail">
						<view class="item">
							<view class="big-title">
								工作说明
							</view>
							<view class="detail bg-white">
								<textarea style="height: 300rpx !important; width: 100%; padding: 20rpx;"
									maxlength="200" auto-blur placeholder="请输入工作说明" />
							</view>
						</view>
						<view class="item">
							<view class="big-title">
								资源需求
							</view>
							<view class="detail bg-white">
								<textarea style="height: 300rpx !important; width: 100%; padding: 20rpx;"
									maxlength="200" auto-blur placeholder="请输入资源需求" />
							</view>
						</view>
						<view class="item">
							<view class="big-title">
								进场时间
							</view>
							<view class="detail bg-white">
								<uni-datetime-picker></uni-datetime-picker>
							</view>
						</view>
						<view class="item">
							<view class="big-title">
								出场时间
							</view>
							<view class="detail bg-white">
								<uni-datetime-picker></uni-datetime-picker>
							</view>
						</view>
					</view>
					<view class="flex justify-between next-step">
						<button class="cu-btn lg text-white return" @click="preStep">上一步</button>
						<button class="cu-btn bg-blue lg next" @click="nextStep">下一步</button>
					</view>
				</view>
			</view>
			<!-- 第二步:服务内容填写 end -->
			<!-- 第三步:确认提交页面 begin -->
			<view class="step-confirm" :class="step==3?'':'display-none'">
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
						<view class="flex justify-between next-step" style="">
							<button class="cu-btn lg text-white return" @click="preStep">返回</button>
							<button class="cu-btn bg-blue lg next" @click="">确认提交</button>
						</view>
					</view>
				</view>
			</view>
			<!-- 第三步:确认提交页面 end -->
		</uni-forms>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				//当前步骤
				step: 1,
				formData: {
					name: '',
					email: ''
				},
				rules: {
					// // 对name字段进行必填验证
					// name: {
					// 	rules: [
					// 		//验证
					// 		{},
					// 		//错误提示
					// 		{}
					// 	]
					// },
					// // 对email字段进行必填验证
					// email: {
					// 	rules: [{}]
					// }
				},
			};
		},
		methods: {
			// 下一步
			nextStep() {
				this.step++
				console.log(this.step)
			},
			// 上一步
			preStep() {
				this.step--
			}
		}
	}
</script>

<style lang="scss">
	@import 'entryOrder-save.scss';
</style>
