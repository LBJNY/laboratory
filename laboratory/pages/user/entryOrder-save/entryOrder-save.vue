<template>
	<view class="page">
		<uni-forms ref="form" :value="lwEntryOrder" :rules="rules" validate-trigger="bind">
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
								<view class="item box-sizing">
									<!-- <view class="label text-label-grey">
										智家接口人
									</view>
									<input type="text" v-model="lwEntryOrder.entryManager" class="text-right"
										placeholder="请输入智家接口人" /> -->
									<uni-forms-item label="智家接口人" name="entryManager">
										<uni-easyinput v-model="lwEntryOrder.entryManager" placeholder="请填写接口人" />
									</uni-forms-item>
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
								<view class="item box-sizing">
									<!-- <view class="label text-label-grey">
										单位和部门名称
									</view>
									<input type="text" v-model="lwEntryOrder.deptName" class="" value="天翼智家"
										placeholder="请输入单位和部门名称" /> -->
									<uni-forms-item label="单位和部门名称" name="deptName">
										<uni-easyinput v-model="lwEntryOrder.deptName" placeholder="请输入单位和部门名称" />
									</uni-forms-item>
								</view>
								<view class="item box-sizing">
									<!-- <view class="label text-label-grey">
										服务项目名称
									</view>
									<input type="text" v-model="lwEntryOrder.projName" class="" value="zigbee测试"
										placeholder="请输入项目名称" /> -->
									<uni-forms-item label="服务项目名称" name="projName">
										<uni-easyinput v-model="lwEntryOrder.projName" placeholder="请输入项目名称" />
									</uni-forms-item>
								</view>
								<view class="item box-sizing">
									<!-- <view class="label text-label-grey">
										联系人
									</view>
									<input type="text" v-model="lwEntryOrder.contact" class="" value="lbj"
										placeholder="请输入联系人" /> -->
									<uni-forms-item label="联系人" name="contact">
										<uni-easyinput v-model="lwEntryOrder.contact" placeholder="请输入联系人" />
									</uni-forms-item>
								</view>
								<view class=" box-sizing">
									<!-- <view class="label text-label-grey">
										电话
									</view>
									<input type="text" v-model="lwEntryOrder.tel" class="" value="17639842742"
										placeholder="请输入电话" /> -->
									<uni-forms-item label="电话" name="tel">
										<uni-easyinput v-model="lwEntryOrder.tel" placeholder="请输入电话" />
									</uni-forms-item>
								</view>
								<view class="item box-sizing">
									<!-- <view class="label text-label-grey">
										邮箱
									</view>
									<input type="text" v-model="lwEntryOrder.email" class="" value="20000000@163.com"
										placeholder="请输入邮箱" /> -->
									<uni-forms-item label="邮箱" name="email">
										<uni-easyinput v-model="lwEntryOrder.email" placeholder="请输入邮箱" />
									</uni-forms-item>
								</view>
								<view class=" box-sizing">
									<!-- <view class="label text-label-grey">
										进出人员
									</view>
									<input type="text" v-model="lwEntryOrder.staff" class="" value="lbj,zzz,ccc"
										placeholder="请输入进出人员姓名" /> -->
									<uni-forms-item label="进出人员" name="staff">
										<uni-easyinput v-model="lwEntryOrder.staff" placeholder="请输入进出人员姓名" />
									</uni-forms-item>
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
								<!-- <textarea
									style="height: 300rpx !important; width: 100%; padding: 20rpx;background: #F8F9FB;"
									v-model="lwEntryOrder.description" maxlength="200" auto-blur
									placeholder="请输入工作说明" /> -->
								<uni-forms-item name="description">
									<textarea v-model="lwEntryOrder.description"
										style="height: 300rpx !important; width: 100%; padding: 20rpx;" maxlength="200"
										auto-blur placeholder="请输入服务内容"
										@input="binddata('description',$event.detail.value)" />
								</uni-forms-item>
							</view>
						</view>
						<view class="item">
							<view class="big-title">
								资源需求
							</view>
							<view class="detail bg-white">
								<!-- <textarea style="height: 300rpx !important; width: 100%; padding: 20rpx;"
									v-model="lwEntryOrder.requirement" maxlength="200" auto-blur
									placeholder="请输入资源需求" /> -->
								<uni-forms-item name="description">
									<textarea v-model="lwEntryOrder.requirement"
										style="height: 300rpx !important; width: 100%; padding: 20rpx;" maxlength="200"
										auto-blur placeholder="请输入服务内容"
										@input="binddata('requirement',$event.detail.value)" />
								</uni-forms-item>
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
									<view class="flex-sub radius text-right text-black">
										{{lwEntryOrder.deptName==null?'暂无':lwEntryOrder.deptName}}
									</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">服务项目名称</view>
									<view class="flex-sub radius text-right text-black">
										{{lwEntryOrder.projName==null?'暂无':lwEntryOrder.projName}}
									</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">联系人</view>
									<view class="flex-sub radius text-right text-black">
										{{lwEntryOrder.contact==null?'暂无':lwEntryOrder.contact}}
									</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">电话</view>
									<view class="flex-sub radius text-right text-black">
										{{lwEntryOrder.tel==null?'暂无':lwEntryOrder.tel}}
									</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">进场人员</view>
									<view class="flex-sub radius text-right text-black">
										{{lwEntryOrder.staff==null?'暂无':lwEntryOrder.staff}}
									</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">邮箱</view>
									<view class="flex-sub radius text-right  text-black">
										{{lwEntryOrder.email==null?'暂无':lwEntryOrder.email}}
									</view>
								</view>
								<view class="border-bottom-dashed">
									<view class="radius text-label-grey">工作说明</view>
									<textarea class="text-black"
										:value="lwEntryOrder.description==null?'暂无':lwEntryOrder.description"
										placeholder=""
										style="height: 200rpx;width: 100%;margin: 20rpx 0rpx;background-color: #F9F9FB;padding: 20rpx;"
										disabled />
								</view>
								<view class="" style="margin-top: 20rpx;">
									<view class="radius text-label-grey">资源要求</view>
									<textarea class="text-black"
										:value="lwEntryOrder.requirement==null?'暂无':lwEntryOrder.requirement"
										placeholder=""
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
				lwEntryOrder: {
					entryManager: null,
					deptName: null,
					projName: null,
					contact: null,
					tel: null,
					description: null,
					requirement: null
				},
				rules: {
					entryManager: {
						rules: [{
								required: true,
								errorMessage: '智家接口人不能为空'
							},
							{
								minLength: 2,
								maxLength: 10,
								errorMessage: '智家接口人长度在{minLength}到{maxLength}个字符'
							}
						]
					},
					deptName: {
						rules: [{
								required: true,
								errorMessage: '部门名称不能为空'
							},
							{
								minLength: 1,
								maxLength: 30,
								errorMessage: '部门名称长度不能超过{maxLength}字'
							}
						]
					},

					projName: {
						rules: [{
								required: true,
								errorMessage: '项目名称不能为空'
							},
							{
								minLength: 1,
								maxLength: 30,
								errorMessage: '项目名称长度不能超过{maxLength}字'
							}
						]
					},
					contact: {
						rules: [{
								required: true,
								errorMessage: '联系人不能为空'
							},
							{
								minLength: 1,
								maxLength: 20,
								errorMessage: '联系人长度不能超过{maxLength}字'
							}
						]
					},
					tel: {
						rules: [{
								required: true,
								errorMessage: '手机号不能为空'
							},
							{
								minLength: 1,
								maxLength: 11,
								errorMessage: '手机号长度不能超过{maxLength}位'
							},
							// {
							// 	pattern: /^1[34578]\d{9}$/,
							// 	errorMessage: '手机号格式错误'
							// }
						]
					},
					email: {
						rules: [{
								required: true,
								errorMessage: '邮箱不能为空'
							},
							{
								pattern: /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/,
								errorMessage: '邮箱格式错误'
							}
						]
					},
					description: {
						rules: [{
								required: true,
								errorMessage: '描述内容不能为空'
							},
							{
								maxLength: 200,
								errorMessage: '描述内容不能超过{maxLength}位'
							}
						]
					},
					requirement: {
						rules: [{
								required: true,
								errorMessage: '描述内容不能为空'
							},
							{
								maxLength: 200,
								errorMessage: '描述内容不能超过{maxLength}位'
							}
						]
					}
				},
				activeId: null
			};
		},
		onLoad(params) {
			if (params.activeId != undefined) {
				this.activeId = params.activeId
				this.getById(params.activeId)
			}
			console.log('结果:'+RegExp(/^1[34578]\d{9}$/).test('17639842742'))
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
				this.$refs.form.validate(valid => {
					console.log(valid)
					if (valid) {
						uni.showToast({
							title: valid[0].errorMessage,
							duration: 1500,
							icon: 'null'
						})
					} else {
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
					}
				})
			},
			// 更新操作
			update() {
				console.log('update')
				this.$refs.form.validate(valid => {
					console.log(valid)
					if (valid) {
						uni.showToast({
							title: valid[0].errorMessage,
							duration: 1500,
							icon: 'none'
						})
					} else {
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
				})

			}
		}
	}
</script>

<style lang="scss">
	@import 'entryOrder-save.scss';

	.base {
		.uni-forms-item__inner {
			.uni-forms-item__label {
				width: 46% !important;

				.label-text {
					color: #C8C8CD !important;
				}
			}

			.uni-easyinput__content-input {
				text-align: end !important;
			}

			.is-input-border {
				border: none !important;
			}

			padding-bottom: 0rpx !important;
		}

		.uni-error-message {
			bottom: -20px !important;
			left: 120rpx !important;
		}
	}

	.appliction {
		.uni-forms-item__label {
			width: 46% !important;

			.label-text {
				color: #C8C8CD !important;
			}
		}

		.is-input-border {
			border: none !important;
		}

		.uni-forms-item__inner {
			padding-bottom: 0rpx !important;
		}

		.uni-error-message {
			bottom: -20px !important;
			left: 176rpx !important;
		}
	}
</style>
