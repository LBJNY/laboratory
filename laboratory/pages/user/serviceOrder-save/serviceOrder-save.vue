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
								<view class="item flex justify-between align-center box-sizing">
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
						<textarea style="height: 600rpx !important; width: 100%; padding: 20rpx;" maxlength="200"
							auto-blur placeholder="请输入服务内容" />
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
							<input type="text" class="" value="20天" placeholder="请输入时间和周期" />
						</view>
						<view class="item flex justify-between align-center box-sizing border-bottom">
							<view class="label text-label-grey">
								场地和网络要求
							</view>
							<input type="text" class="" value="测试室" placeholder="请输入场地和网络要求" />
						</view>
						<view class="item flex justify-between align-center box-sizing border-bottom">
							<view class="label text-label-grey">
								平台和软件要求
							</view>
							<input type="text" class="" value="lbj" placeholder="请输入平台和软件要求" />
						</view>
						<view class="cu-form-group align-start" style="padding: 0rpx;">
							<view class="title text-label-grey">终端和仪表要求</view>
							<textarea maxlength="100" auto-blur
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
											<input type="number" :value="checkbox[0].number"
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
											<input type="number" :value="checkbox[1].number"
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
											<input type="number" :value="checkbox[2].number"
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
											<input type="number" :value="checkbox[3].number"
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
											<input type="number" :value="checkbox[4].number"
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
							<textarea style="height: 300rpx !important; width: 100%; padding: 20rpx;" maxlength="200"
								auto-blur placeholder="请输入其他资源要求" />
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
									<xfl-select :list="serviceType" :clearable="false" :showItemNum="5"
										:isCanInput="true" :style_Container="listBoxStyle" :placeholder="'placeholder'"
										:initValue="'--请选择--'" :selectHideType="'independent'" :props="item.id"
										@change="change" style_Container="height:50rpx;border: 1px solid #919EFF;">
									</xfl-select>
								</view>
							</view>
							<view class="item flex justify-between align-center box-sizing">
								<view class="label text-label-grey">
									资金类型费用承担
								</view>
								<xfl-select :list="serviceType" :clearable="false" :showItemNum="5" :isCanInput="true"
									:style_Container="listBoxStyle" :placeholder="'placeholder'" :initValue="'--请选择--'"
									:selectHideType="'independent'" :props="item.id" @change="change"
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
									<textarea class=" text-black" value="aaaaaaaaaaaaa" placeholder=""
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
							<button class="cu-btn lg text-white return" @click="preStep">返回</button>
							<button class="cu-btn bg-blue lg next" @click="">确认提交</button>
						</view>
					</view>
				</view>
			</view>
			<!-- 第五步:确认提交页面 end -->
		</uni-forms>
	</view>
</template>

<script>
	import xflSelect from '../../../components/xfl-select/xfl-select';
	export default {
		data() {
			return {
				//当前步骤
				step: 1,
				//人员字符串
				people: null,
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
				//服务类型
				serviceType: [{
						id: 1,
						value: '综合'
					},
					{
						id: 2,
						value: '人力'
					},
					{
						id: 3,
						value: '物力'
					},
				],
				typeNames: ['综合', '人力', '物力'],
				editSelectTypeLists: null
			}
		},
		created() {
			this.editSelectTypeLists = this.serviceType[0].id; /* 默认选中 */
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
					const str = strs.join(',')
					this.$set(this, 'people', str)
				}
			}
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
			change({
				newVal,
				oldVal,
				index,
				orignItem
			}) {
				console.log(newVal, oldVal, index, orignItem);
			},
			/**
			 * 返回
			 */
			returnBack: function(){
				uni.navigateBack()
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
