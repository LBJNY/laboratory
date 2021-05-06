<template>
	<view class="page">
		<uni-forms ref="form" :model="lwServiceOrder" :rules="rules">
			<!-- 第一步:填写基础信息 begin -->
			<view class="step-first" :class="step==1?'':'display-none'">
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
							<view class="big-title">
								默认信息
							</view>
							<view class="detail box-sizing">
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										服务委托单编号
									</view>
									<input type="text" v-model="lwServiceOrder.serviceNo" disabled class="text-right"
										value="xxxxxxx123" />
								</view>
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										智家接口人
									</view>
									<input type="text" v-model="lwServiceOrder.serviceManager" placeholder="请输入智家接口人"
										class="text-right" value="杜宁馨" />
								</view>
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										提交申请日期
									</view>
									<input type="text" v-model="lwServiceOrder.currentDate" class="text-right"
										disabled />
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
									<input type="text" v-model="lwServiceOrder.deptName" class="" value="天翼智家"
										placeholder="请输入单位和部门名称" />
								</view>
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										服务项目名称
									</view>
									<input type="text" v-model="lwServiceOrder.projName" class="" value="zigbee测试"
										placeholder="请输入项目名称" />
								</view>
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										联系人
									</view>
									<input type="text" v-model="lwServiceOrder.contact" class="" value="lbj"
										placeholder="请输入联系人" />
								</view>
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										电话
									</view>
									<input type="text" v-model="lwServiceOrder.tel" class="" value="17639842742"
										placeholder="请输入电话" />
								</view>
								<view class="item flex justify-between align-center box-sizing">
									<view class="label text-label-grey">
										邮箱
									</view>
									<input type="text" v-model="lwServiceOrder.email" class="" value="20000000@163.com"
										placeholder="请输入邮箱" />
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
					<view class="big-title">
						服务内容
					</view>
					<view class="detail bg-white">
						<textarea v-model="lwServiceOrder.description"
							style="height: 600rpx !important; width: 100%; padding: 20rpx;" maxlength="200" auto-blur
							placeholder="请输入服务内容" />
					</view>
					<view class="flex justify-between next-step">
						<button class="cu-btn lg text-white return" @click="preStep">上一步</button>
						<button class="cu-btn bg-blue lg next" @click="nextStep">下一步</button>
					</view>
				</view>
			</view>
			<!-- 第二步:服务内容填写 end -->
			<!-- 第三步:资源需求填写 begin -->
			<view class="step-third step-common box-sizing flex justify-center" :class="step==3?'':'display-none'">
				<view class="container box-sizing ">
					<view class="big-title">
						资源要求
					</view>
					<view class="detail bg-white">
						<view class="item flex justify-between align-center box-sizing border-bottom">
							<view class="label text-label-grey">
								时间和周期要求
							</view>
							<input type="text" v-model="lwServiceOrder.timeReq" class="" value="20天"
								placeholder="请输入时间和周期" />
						</view>
						<view class="item flex justify-between align-center box-sizing border-bottom">
							<view class="label text-label-grey">
								场地和网络要求
							</view>
							<input type="text" v-model="lwServiceOrder.netReq" class="" value="测试室"
								placeholder="请输入场地和网络要求" />
						</view>
						<view class="item flex justify-between align-center box-sizing border-bottom">
							<view class="label text-label-grey">
								平台和软件要求
							</view>
							<input type="text" v-model="lwServiceOrder.softReq" class="" value="lbj"
								placeholder="请输入平台和软件要求" />
						</view>
						<view class="cu-form-group align-start" style="padding: 0rpx;">
							<view class="title text-label-grey">终端和仪表要求</view>
							<textarea maxlength="100" auto-blur v-model="lwServiceOrder.deviceReq"
								style="height: 60rpx !important; width: 100%; padding: 0rpx 0rpx 0rpx 52rpx;"
								placeholder="请输入终端和仪表要求"></textarea>
						</view>
						<view class="item cu-form-group align-start justify-start" style="padding: 0rpx; border:0rpx">
							<view class="title text-label-grey">
								服务人员要求
							</view>
							<view class="list align-center">
								<checkbox-group @change="checkboxChange">
									<view class="item flex justify-center">
										<checkbox class='round blue' :checked="checkbox[0].checked?true:false"
											:value="checkbox[0].value">
										</checkbox>
										<view class="number flex text-center align-center">
											<text>首席</text>
											<input type="text" v-model="checkbox[0].number"
												:disabled="checkbox[0].checked?false:true" />
											<text>人</text>
										</view>
									</view>
									<view class="item flex justify-center">
										<checkbox class='round blue' :checked="checkbox[1].checked?true:false"
											:value="checkbox[1].value">
										</checkbox>
										<view class="number flex text-center align-center">
											<text>资深</text>
											<input type="text" v-model="checkbox[1].number"
												:disabled="checkbox[1].checked?false:true" />
											<text>人</text>
										</view>
									</view>
									<view class="item flex justify-center">
										<checkbox class='round blue' :checked="checkbox[2].checked?true:false"
											:value="checkbox[2].value">
										</checkbox>
										<view class="number flex text-center align-center">
											<text>高级</text>
											<input type="text" v-model="checkbox[2].number"
												:disabled="checkbox[2].checked?false:true" />
											<text>人</text>
										</view>
									</view>
									<view class="item flex justify-center">
										<checkbox class='round blue' :checked="checkbox[3].checked?true:false"
											:value="checkbox[3].value">
										</checkbox>
										<view class="number flex text-center align-center">
											<text>中级</text>
											<input type="text" v-model="checkbox[3].number"
												:disabled="checkbox[3].checked?false:true" />
											<text>人</text>
										</view>
									</view>
									<view class="item flex justify-center">
										<checkbox class='round blue' :checked="checkbox[4].checked?true:false"
											:value="checkbox[4].value">
										</checkbox>
										<view class="number flex text-center align-center">
											<text>初级</text>
											<input type="text" v-model="checkbox[4].number"
												:disabled="checkbox[4].checked?false:true" />
											<text>人</text>
										</view>
									</view>
								</checkbox-group>
							</view>
						</view>
					</view>
					<view class="flex justify-between next-step">
						<button class="cu-btn lg text-white return" @click="preStep">上一步</button>
						<button class="cu-btn bg-blue lg next" @click="nextStep">下一步</button>
					</view>
				</view>

			</view>
			<!-- 第三步:资源需求填写 end -->
			<!-- 第四步:其他需求 begin -->
			<view class="step-four step-common box-sizing flex justify-center" :class="step==4?'':'display-none'">
				<view class="container box-sizing" style="width: 100%;">
					<view class="other">
						<view class="big-title">
							其他资源要求
						</view>
						<view class="detail bg-white">
							<textarea v-model="lwServiceOrder.otherReq"
								style="height: 300rpx !important; width: 100%; padding: 20rpx;" maxlength="200"
								placeholder="请输入其他资源要求" />
						</view>
					</view>
					<view class="service">
						<view class="big-title">
							服务属性
						</view>
						<view class="detail bg-white">
							<view class="item flex justify-between align-center box-sizing border-bottom">
								<view class="label text-label-grey">
									服务类型
								</view>
								<view>
									<xfl-select :list="serviceTypeList" :clearable="false" :showItemNum="5"
										:isCanInput="true" :style_Container="listBoxStyle" :placeholder="'placeholder'"
										:initValue="'--请选择--'" :selectHideType="'independent'" :props="item.id"
										@change="serviceTypeChange"
										style_Container="height:50rpx;border: 1px solid #919EFF;">
									</xfl-select>
								</view>
							</view>
							<view class="item flex justify-between align-center box-sizing">
								<view class="label text-label-grey">
									资金类型费用承担
								</view>
								<xfl-select :list="fundSupportList" :clearable="false" :showItemNum="5"
									:isCanInput="true" :style_Container="listBoxStyle" :placeholder="'placeholder'"
									:initValue="'--请选择--'" :selectHideType="'independent'" :props="item.id"
									@change="fundSupportChange"
									style_Container="height:50rpx;border: 1px solid #919EFF;">
								</xfl-select>
							</view>
						</view>
					</view>
					<view class="flex justify-between next-step">
						<button class="cu-btn lg text-white return" @click="preStep">上一步</button>
						<button class="cu-btn bg-blue lg next" @click="nextStep">提交</button>
					</view>
				</view>
			</view>
			<!-- 第四步:其他需求 end -->
			<!-- 第五步:确认提交页面 begin -->
			<view class="step-confirm" :class="step==5?'':'display-none'">
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
										{{lwServiceOrder.deptName==undefined?'暂无':lwServiceOrder.deptName}}
									</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">服务项目名称</view>
									<view class="flex-sub radius text-right text-black">
										{{lwServiceOrder.projName==undefined?'暂无':lwServiceOrder.projName}}
									</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">联系人</view>
									<view class="flex-sub radius text-right text-black">
										{{lwServiceOrder.contact==undefined?'暂无':lwServiceOrder.contact}}
									</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">电话</view>
									<view class="flex-sub radius text-right text-black">
										{{lwServiceOrder.tel==undefined?'暂无':lwServiceOrder.tel}}
									</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">邮箱</view>
									<view class="flex-sub radius text-right  text-black">
										{{lwServiceOrder.email==undefined?'暂无':lwServiceOrder.email}}
									</view>
								</view>
								<view class="border-bottom-dashed">
									<view class="radius text-label-grey">服务内容</view>
									<textarea class="text-black"
										:value="lwServiceOrder.description==undefined?'暂无':lwServiceOrder.description"
										style="height: 200rpx;width: 100%;margin: 20rpx 0rpx;" disabled />
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">时间和周期要求</view>
									<view class="flex-sub radius text-right text-black">
										{{lwServiceOrder.timeReq==undefined?'暂无':lwServiceOrder.timeReq}}
									</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">场地和网络要求</view>
									<view class="flex-sub radius text-right text-black">
										{{lwServiceOrder.netReq==undefined?'暂无':lwServiceOrder.netReq}}
									</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">平台和软件要求</view>
									<view class="flex-sub radius text-right  text-black">
										{{lwServiceOrder.softReq==undefined?'暂无':lwServiceOrder.softReq}}
									</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">终端和仪表要求</view>
									<textarea class=" text-black"
										:value="lwServiceOrder.deviceReq==undefined?'暂无':lwServiceOrder.deviceReq"
										style="height: 160rpx;width: 50%;" disabled />
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">服务人员要求</view>
									<view class="flex-sub radius text-right  text-black">
										{{personReq==null?'暂无':personReq}}
									</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">其他服务要求</view>
									<view class="flex-sub radius text-right text-black">
										{{lwServiceOrder.otherReq==undefined?'暂无':lwServiceOrder.otherReq}}
									</view>
								</view>
								<view class="flex item border-top-dashed">
									<view class="flex-sub radius text-label-grey">服务类型</view>
									<view class="flex-sub radius text-right text-black">
										{{lwServiceOrder.serviceType==undefined?'暂无':lwServiceOrder.serviceType}}
									</view>
								</view>
								<view class="flex item border-bottom-dashed">
									<view class="flex-sub radius text-label-grey">资金支持和费用承担</view>
									<view class="flex-sub radius text-right text-black">
										{{lwServiceOrder.fundSupport==undefined?'暂无':lwServiceOrder.fundSupport}}
									</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">服务委托单编号</view>
									<view class="flex-sub radius text-right text-black">
										{{lwServiceOrder.serviceNo==undefined?'暂无':lwServiceOrder.serviceNo}}
									</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">智家对接人</view>
									<view class="flex-sub radius text-right text-black">
										{{lwServiceOrder.serviceManager==undefined?'暂无':lwServiceOrder.serviceManager}}
									</view>
								</view>
								<view class="flex item">
									<view class="flex-sub radius text-label-grey">提交申请日期</view>
									<view class="flex-sub radius text-right text-black">
										{{lwServiceOrder.currentDate | date-format}}
									</view>
								</view>
							</view>
						</view>
						<view class="flex justify-between next-step" style="">
							<button class="cu-btn lg text-white return" @click="preStep">返回</button>
							<button class="cu-btn bg-blue lg next" @click="add">确认提交</button>
						</view>
					</view>
				</view>
			</view>
			<!-- 第五步:确认提交页面 end -->
		</uni-forms>
	</view>
</template>

<script>
	import address from 'utils/page-address.js';
	import xflSelect from '../../../components/xfl-select/xfl-select';
	import serviceOrderApi from '@/api/lw-service-order';
	import fundSupportApi from '@/api/lw-fund-support'
	import serviceTypeApi from '@/api/lw-service-type'
	export default {
		data() {
			return {
				//当前步骤
				step: 1,
				//人员字符串
				personReq: null,
				// 所需人员等级
				level: ['首席', '资深', '高级', '中级', '初级'],
				/**
				 * value   当前编号
				 * checked 是否选中
				 * number  数量
				 */
				checkbox: [{
						value: '0',
						checked: false,
						number: '0'
					},
					{
						value: '1',
						checked: false,
						number: 0
					},
					{
						value: '2',
						checked: false,
						number: 0
					},
					{
						value: '3',
						checked: false,
						number: 0
					},
					{
						value: '4',
						checked: false,
						number: 0
					}
				],
				// 服务委托单详情
				lwServiceOrder: {},
				activeId: null,
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
				// 资金支持类型
				fundSupportList: [],
				// 服务委托类型
				serviceTypeList: []

			}
		},
		watch: {
			checkbox: {
				deep: true,
				handler: function(newVal, oldVal) {
					const checkbox = this.checkbox,
						strs = [];
					for (var i = 0; i < checkbox.length; i++) {
						strs.push(checkbox[i].number)
					}
					const str = strs.join('-')
					// this.$set(this, 'personReq', str)
					this.$set(this.lwServiceOrder, 'personReq', str)
					this.getPersonReq()
				}
			}
		},
		onLoad(params) {
			if (params.activeId != undefined) {
				this.activeId = params.activeId
				this.getById(params.activeId)
			}

		},
		onShow() {
			this.getAllFundSupports()
			this.getAllServiceTypes()
			this.$set(this.lwServiceOrder, 'currentDate', this.getNowFormatDate())
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
			// 获取人员需求字符串
			getPersonReq() {
				var that = this
				var req = []
				var strArray = []
				if (that.lwServiceOrder.personReq != null && that.lwServiceOrder.personReq != '') {
					req = that.lwServiceOrder.personReq.split('-')
					for (let index = 0; index < req.length; index++) {
						const element = req[index]
						var strNext = ''
						if (element !== '0' && element !== '') {
							strNext += that.level[index] + req[index] + '人'
							strArray.push(strNext)
						}
					}
				}
				this.personReq = strArray.join(',')
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
			/**
			 * 复写 binddata 方法，如果只是为了校验，无复杂自定义操作，可忽略此方法
			 * @param {String} name 字段名称
			 * @param {String} value 表单域的值
			 */
			// binddata(name,value){
			// 通过 input 事件设置表单指定 name 的值
			//   this.$refs.form.setValue(name, value)
			// },
			// 触发提交表单
			// submit() {
			// 	this.$refs.form.submit().then(res => {
			// 		console.log('表单数据信息：', res);
			// 	}).catch(err => {
			// 		console.log('表单错误信息：', err);
			// 	})
			// }
			checkboxChange: function(e) {
				var checkbox = this.checkbox,
					values = e.detail.value;
				for (var i = 0, lenI = checkbox.length; i < lenI; ++i) {
					const item = checkbox[i]
					if (values.includes(item.value)) {
						this.$set(item, 'checked', true)
					} else {
						this.$set(item, 'checked', false)
					}
				}
			},
			visibleChange(isShow) { // 列表框的显示隐藏事件
				console.log('isShow::', isShow);
			},
			// 服务类型改变
			serviceTypeChange({
				newVal,
				index
			}) {
				console.log(newVal, index);
				this.$set(this.lwServiceOrder, 'serviceType', newVal)
			},
			// 资金支持改变
			fundSupportChange({
				newVal,
				index
			}) {
				console.log(newVal, index);
				this.$set(this.lwServiceOrder, 'fundSupport', newVal)
			},
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
				})
			},
			// 获取所有资金支持类型
			getAllFundSupports() {
				fundSupportApi.getAll().then(res => {
					this.fundSupportList = res.data
				})
			},
			// 获取所有服务委托类型
			getAllServiceTypes() {
				serviceTypeApi.getAll().then(res => {
					this.serviceTypeList = res.data
					console.log("长度" + this.serviceTypeList.length)
				})
			},
			// 添加
			add() {
				serviceOrderApi.save(this.lwServiceOrder).then(res => {
					console.log('表单数据信息：', res);
					uni.showToast({
						title: '表单提交成功!',
						duration: 3000,
						success() {
							uni.switchTab({
								url: address.serviceOrder
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
		},
		components: {
			xflSelect
		}
	}
</script>

<style lang="scss">
	@import 'serviceOrder-save.scss';
</style>
