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
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.deptName}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">服务项目名称</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.projName}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">联系人</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.contact}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">电话</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.tel}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">邮箱</view>
							<view class="flex-sub radius text-right  text-black">{{lwServiceOrder.email}}</view>
						</view>
						<view class="border-bottom-dashed">
							<view class="radius text-label-grey">服务内容</view>
							<textarea class="text-black"
								style="background: #F8F9FB; height: 200rpx;width: 100%;margin: 20rpx 0rpx;" disabled
								v-model="lwServiceOrder.description" />
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">时间和周期要求</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.timeReq}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">场地和网络要求</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.netReq}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">平台和软件要求</view>
							<view class="flex-sub radius text-right  text-black">{{lwServiceOrder.softReq}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">终端和仪表要求</view>
							<textarea class=" text-black text-right" value="aaaaaaaaaaaaa" placeholder=""
								style="height: 160rpx;width: 50%;" disabled v-model="lwServiceOrder.deviceReq" />
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">服务人员要求</view>
							<view class="flex-sub radius text-right  text-black">{{personReq==null?'暂无':personReq}}
							</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">其他服务要求</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.otherReq}}</view>
						</view>
						<view class="flex item border-top-dashed">
							<view class="flex-sub radius text-label-grey">服务类型</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.serviceType}}</view>
						</view>
						<view class="flex item border-bottom-dashed">
							<view class="flex-sub radius text-label-grey">资金支持和费用承担</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.fundSupport}}</view>
						</view>
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
						<view class="dept">
							<view class="flex item"
								:class="lwServiceOrder.lwOrderAudit.deptAdvice==null?'':'display-none'">
								<view class="flex-sub radius text-label-grey">申请单位和部门审核意见</view>
								<radio-group name="" class="radio-group" @change="deptChange">
									<label>
										<radio class='blue radio radio-a' :class="radio==0?'checked':''"
											:checked="radio==0?true:false" value="0"></radio>
										<text>通过</text>
									</label>
									<label>
										<radio class='red radio radio-a' :class="radio==1?'checked':''"
											:checked="radio==1?true:false" value="1"></radio>
										<text>不通过</text>
									</label>
								</radio-group>
							</view>
							<view class="flex item"
								:class="lwServiceOrder.lwOrderAudit.deptAdvice!==null?'':'display-none'">
								<view class="flex-sub radius text-label-grey">申请单位和部门审核意见</view>
								<view class="flex-sub radius text-right text-light-blue"
									v-if="lwServiceOrder.lwOrderAudit.deptAdvice==0">审核成功</view>
								<view class="flex-sub radius text-right text-red"
									v-if="lwServiceOrder.lwOrderAudit.deptAdvice==1">审核失败</view>
							</view>
						</view>
						<view class="lab">
							<view class="flex item"
								:class="lwServiceOrder.lwOrderAudit.deptAdvice==0&&lwServiceOrder.lwOrderAudit.labAdvice==null?'':'display-none'">
								<view class="flex-sub radius text-label-grey">实验室审核意见</view>
								<radio-group name="" class="radio-group" @change="labChange">
									<label>
										<radio class='blue radio radio-a' :class="radio==0?'checked':''"
											:checked="radio=='0'?true:false" value="0"></radio>
										<text>通过</text>
									</label>
									<label>
										<radio class='red radio radio-a' :class="radio==1?'checked':''"
											:checked="radio==1?true:false" value="1"></radio>
										<text>不通过</text>
									</label>
								</radio-group>
							</view>
							<view class="flex item" :class="labIsDisplay==true?'':'display-none'">
								<view class="flex-sub radius text-label-grey">转发至(实验室审核人)</view>
								<xfl-select :list="reviewerList" :clearable="false" :showItemNum="5" :isCanInput="true"
									:style_Container="listBoxStyle" :placeholder="'请选择转发人'"
									:selectHideType="'independent'" :props="item.id" @change="labReviwerChange"
									style_Container="height:50rpx;border: 1px solid #919EFF;width:280rpx;font-size: 26rpx;">
								</xfl-select>
							</view>
							<view class="flex item"
								:class="lwServiceOrder.lwOrderAudit.labAdvice!==null?'':'display-none'">
								<view class="flex-sub radius text-label-grey">实验室审核意见</view>
								<view class="flex-sub radius text-right text-light-blue"
									v-if="lwServiceOrder.lwOrderAudit.labAdvice==0">审核成功</view>
								<view class="flex-sub radius text-right text-red"
									v-if="lwServiceOrder.lwOrderAudit.labAdvice==1">审核失败</view>
							</view>
						</view>
						<view class="ch">
							<view class="flex item" :class="lwServiceOrder.lwOrderAudit.labAdvice==0&&lwServiceOrder.lwOrderAudit.chAdvice==null?'':'display-none'">
								<view class="flex-sub radius text-label-grey">创新合作部审核意见</view>
								<radio-group name="" class="radio-group" @change="chChange">
									<label>
										<radio class='blue radio radio-a' :class="radio==0?'checked':''"
											:checked="radio==0?true:false" value="0"></radio>
										<text>通过</text>
									</label>
									<label>
										<radio class='red radio radio-a' :class="radio==1?'checked':''"
											:checked="radio==1?true:false" value="1"></radio>
										<text>不通过</text>
									</label>
								</radio-group>
							</view>
							<view class="flex item" :class="chIsDisplay==true?'':'display-none'">
								<view class="flex-sub radius text-label-grey">转发至(创新合作部)</view>
								<xfl-select :list="reviewerList" :clearable="false" :showItemNum="5" :isCanInput="true"
									:style_Container="listBoxStyle" :placeholder="'请选择转发人'"
									:selectHideType="'independent'" :props="item.id" @change="chReviwerchange"
									style_Container="height:50rpx;border: 1px solid #919EFF;width:280rpx;font-size: 26rpx;">
								</xfl-select>
							</view>
						</view>
					</view>
				</view>
				<view class="flex justify-between next-step" style="">
					<button class="cu-btn lg text-white return" @click="returnBack">返回</button>
					<button class="cu-btn lg text-white bg-blue" @click="toExamine">确认审核</button>
				</view>
			</view>

		</view>
	</view>
</template>

<script>
	import address from 'utils/page-address.js';
	import serviceOrderApi from '@/api/lw-service-order';
	import userApi from '@/api/lw-user'
	export default {
		data() {
			return {
				// 服务委托单详情
				lwServiceOrder: {},
				// 审核信息
				lwOrderAudit: {},
				// 所需人员等级
				level: ['首席', '资深', '高级', '中级', '初级'],
				// 所需人员字符串
				personReq: null,
				// 订单编号
				activeId: null,
				// 审核人类型
				reviewerList: [],
				// 控制显示--实验室审核  true:显示 false:不显示
				labIsDisplay: false,
				// 控制显示--创和审核   true:显示 false:不显示
				chIsDisplay: false
			}
		},
		onLoad(params) {
			this.activeId = params.activeId
			this.getById(params.activeId)

		},
		methods: {
			returnBack: function() {
				uni.navigateBack()
			},
			// 根据id查询
			getById(id) {
				serviceOrderApi.get(id).then(res => {
					this.lwServiceOrder = res.data
					this.$set(this.lwOrderAudit, 'serviceNo', this.lwServiceOrder.serviceNo)
					console.log(this.lwServiceOrder)
				})
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
						if (element !== '0') {
							strNext += that.level[index] + req[index] + '人'
							strArray.push(strNext)
						}
					}
				}
				this.personReq = strArray.join(',')
			},
			// 部门意见改变
			deptChange(event) {
				console.log('值:' + event.detail.value)
				var deptAdvice = event.detail.value
				this.$set(this.lwOrderAudit, 'deptAdvice', deptAdvice)
				if (deptAdvice === '0') {
					this.labIsDisplay = true
					this.getReviewerList()
				} else {
					this.labIsDisplay = false
				}
			},
			// 选择实验室审核人
			labReviwerChange(event) {
				console.log(event.orignItem.id)
				var labOfficerId = event.orignItem.id
				this.$set(this.lwOrderAudit, 'labOfficerId', labOfficerId)
			},
			// 选择创新合作部审核人
			chReviwerchange(event) {
				console.log(event.orignItem.id)
				var chOfficerId = event.orignItem.id
				this.$set(this.lwOrderAudit, 'chOfficerId', chOfficerId)
			},
			// 实验室审核意见改变
			labChange(event) {
				console.log(event.detail.value)
				var labAdvice = event.detail.value
				this.$set(this.lwOrderAudit, 'labAdvice', labAdvice)
				if (labAdvice === '0') {
					this.chIsDisplay = true
					this.getReviewerList()
				} else {
					this.chIsDisplay = false
				}
			},
			// 创和意见改变
			chChange(event) {
				console.log(event.detail.value)
				var chAdvice = event.detail.value
				this.$set(this.lwOrderAudit, 'chAdvice', chAdvice)
			},
			// 获取审核人列表
			getReviewerList() {
				userApi.getReviewerList(this.lwServiceOrder.serviceNo).then(res => {
					this.reviewerList = res.data
					console.log(this.reviewerList)
				})
			},
			toExamine() {
				console.log(this.lwOrderAudit)
				serviceOrderApi.examine(this.lwOrderAudit).then(res => {
					uni.showToast({
						title: '审核成功!',
						duration: 3000,
						success() {
							uni.switchTab({
								url: address.serviceOrder
							})
						}
					})
				})
			}
		}
	}
</script>

<style lang="scss">
	@import 'serviceOrder-examine.scss';
</style>
