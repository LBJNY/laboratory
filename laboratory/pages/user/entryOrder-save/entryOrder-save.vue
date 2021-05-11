<template>
	<view class="page">
		<uni-forms ref="form" :model="lwEntryOrder" :rules="rules">
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
									<input type="text" v-model="lwEntryOrder.entryNo" disabled class="text-right"
										value="xxxxxxx123" />
								</view>
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										智家接口人
									</view>
									<input type="text" v-model="lwEntryOrder.entryManager" class="text-right"
										placeholder="请输入智家接口人" />
								</view>
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										提交申请日期
									</view>
									<view class="">
										{{lwEntryOrder.currentDate|date-format}}
									</view>
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
									<input type="text" v-model="lwEntryOrder.deptName" class="" value="天翼智家"
										placeholder="请输入单位和部门名称" />
								</view>
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										服务项目名称
									</view>
									<input type="text" v-model="lwEntryOrder.projName" class="" value="zigbee测试"
										placeholder="请输入项目名称" />
								</view>
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										联系人
									</view>
									<input type="text" v-model="lwEntryOrder.contact" class="" value="lbj"
										placeholder="请输入联系人" />
								</view>
								<view class="flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										电话
									</view>
									<input type="text" v-model="lwEntryOrder.tel" class="" value="17639842742"
										placeholder="请输入电话" />
								</view>
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										邮箱
									</view>
									<input type="text" v-model="lwEntryOrder.email" class="" value="20000000@163.com"
										placeholder="请输入邮箱" />
								</view>
								<view class="flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										进出人员
									</view>
									<input type="text" v-model="lwEntryOrder.staff" class="" value="lbj,zzz,ccc"
										placeholder="请输入进出人员姓名" />
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
								<textarea
									style="height: 300rpx !important; width: 100%; padding: 20rpx;background: #F8F9FB;"
									v-model="lwEntryOrder.description" maxlength="200" auto-blur
									placeholder="请输入工作说明" />
							</view>
						</view>
						<view class="item">
							<view class="big-title">
								资源需求
							</view>
							<view class="detail bg-white">
								<textarea style="height: 300rpx !important; width: 100%; padding: 20rpx;"
									v-model="lwEntryOrder.requirement" maxlength="200" auto-blur
									placeholder="请输入资源需求" />
							</view>
						</view>
						<view class="item">
							<view class="big-title">
								进场时间
							</view>
							<view class="detail bg-white">
								<uni-datetime-picker v-model="lwEntryOrder.sDate"></uni-datetime-picker>
							</view>
						</view>
						<view class="item">
							<view class="big-title">
								出场时间
							</view>
							<view class="detail bg-white">
								<uni-datetime-picker v-model="lwEntryOrder.eDate"></uni-datetime-picker>
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
									<textarea class="text-black" :value="lwEntryOrder.description" placeholder=""
										style="height: 200rpx;width: 100%;margin: 20rpx 0rpx;background-color: #F9F9FB;padding: 20rpx;"
										disabled />
								</view>
								<view class="" style="margin-top: 20rpx;">
									<view class="radius text-label-grey">资源要求</view>
									<textarea class="text-black" :value="lwEntryOrder.requirement" placeholder=""
										style="height: 200rpx;width: 100%;margin: 20rpx 0rpx;background-color: #F9F9FB;padding: 20rpx;"
										disabled />
								</view>
								<view class="flex item border-bottom-dashed">
									<view class="flex-sub radius text-label-grey">进场时间</view>
									<view class="flex-sub radius text-right text-black">
										{{lwEntryOrder.sDate | date-format}}
									</view>
								</view>
								<view class="flex item ">
									<view class="flex-sub radius text-label-grey">进场单编号</view>
									<view class="flex-sub radius text-right text-black">{{lwEntryOrder.entryNo}}</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">智家对接人</view>
									<view class="flex-sub radius text-right text-black">
										{{lwEntryOrder.entryManager==undefined?'暂无':lwEntryOrder.entryManager}}
									</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">提交申请日期</view>
									<view class="flex-sub radius text-right text-black">
										{{lwEntryOrder.currentDate | date-format}}
									</view>
								</view>
							</view>
						</view>
						<view class="flex justify-between next-step" style="">
							<button class="cu-btn lg text-white return" @click="preStep">返回</button>
							<button class="cu-btn bg-blue lg next" @click="activeId==null?add():update()">确认提交</button>
						</view>
					</view>
				</view>
			</view>
			<!-- 第三步:确认提交页面 end -->
		</uni-forms>
	</view>
</template>

<script>
	import address from 'utils/page-address.js';
	import entryOrderApi from '@/api/lw-entry-order';
	export default {
		data() {
			return {
				//当前步骤
				step: 1,
				// 进场单详情
				lwEntryOrder: {},
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
				activeId: null
			};
		},
		onLoad(params) {
			if (params.activeId != undefined) {
				this.activeId = params.activeId
				this.getById(params.activeId)
			}
		},
		onShow() {
			this.$set(this.lwEntryOrder, 'currentDate', this.getNowFormatDate())
		},
		methods: {
			// 获取当前日期
			getNowFormatDate() {
				var date = new Date();
				var seperator1 = "-";
				var year = date.getFullYear();
				var month = date.getMonth() + 1;
				var strDate = date.getDate();
				if (month >= 1 && month <= 9) {
					month = "0" + month;
				}
				if (strDate >= 0 && strDate <= 9) {
					strDate = "0" + strDate;
				}
				var currentdate = year + seperator1 + month + seperator1 + strDate;
				return currentdate;
			},
			// 下一步
			nextStep() {
				this.step++
				console.log(this.step)
			},
			// 上一步
			preStep() {
				this.step--
			},
			// 根据id查询
			getById(id) {
				entryOrderApi.get(id).then(res => {
					this.lwEntryOrder = res.data
					console.log(this.lwEntryOrder)
				})
			},
			// 添加
			add() {
				console.log('add')
				entryOrderApi.save(this.lwEntryOrder).then(res => {
					console.log('表单数据信息：', res);
					uni.showToast({
						title: '表单提交成功!',
						duration: 3000,
						success() {
							uni.switchTab({
								url: address.entryOrder
							})
						}
					})
				}).catch(err => {
					console.log('表单错误信息：', err);
					uni.showToast({
						title: '表单信息错误,请检查后重新提交!',
						duration: 2000,
						icon: none
					});
				})
			},
			// 更新操作
			update() {
				console.log('update')
				entryOrderApi.update(this.lwEntryOrder).then(res => {
					console.log('表单数据信息：', res);
					uni.showToast({
						title: '订单修改成功!',
						duration: 3000,
						success() {
							uni.switchTab({
								url: address.entryOrder
							})
						}
					})
				}).catch(err => {
					console.log('表单错误信息：', err);
					uni.showToast({
						title: '订单信息错误,请检查后重新提交!',
						duration: 2000,
						icon: none
					});
				})
			}
		}
	}
</script>

<style lang="scss">
	@import 'entryOrder-save.scss';
</style>
