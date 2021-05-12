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
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.deptName==null?'暂无':lwServiceOrder.deptName}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">服务项目名称</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.projName==null?'暂无':lwServiceOrder.projName}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">联系人</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.contact==null?'暂无':lwServiceOrder.contact}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">电话</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.tel==null?'暂无':lwServiceOrder.tel}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">邮箱</view>
							<view class="flex-sub radius text-right  text-black">{{lwServiceOrder.email==null?'暂无':lwServiceOrder.email}}</view>
						</view>
						<view class="border-bottom-dashed">
							<view class="radius text-label-grey">服务内容</view>
							<textarea class="text-black"  style="background: #F8F9FB; height: 200rpx;width: 100%;margin: 20rpx 0rpx;" disabled
								v-model="lwServiceOrder.description==null?'暂无':lwServiceOrder.description" />
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">时间和周期要求</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.timeReq==null?'暂无':lwServiceOrder.timeReq}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">场地和网络要求</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.netReq==null?'暂无':lwServiceOrder.netReq}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">平台和软件要求</view>
							<view class="flex-sub radius text-right  text-black">{{lwServiceOrder.softReq==null?'暂无':lwServiceOrder.softReq}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">终端和仪表要求</view>
							<textarea class=" text-black text-right" value="aaaaaaaaaaaaa" placeholder=""
								style="height: 160rpx;width: 50%;" disabled v-model="lwServiceOrder.deviceReq==null?'暂无':lwServiceOrder.deviceReq" />
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">服务人员要求</view>
							<view class="flex-sub radius text-right  text-black">{{personReq==null?'暂无':personReq}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">其他服务要求</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.otherReq==null?'暂无':lwServiceOrder.otherReq}}</view>
						</view>
						<view class="flex item border-top-dashed">
							<view class="flex-sub radius text-label-grey">服务类型</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.serviceType==null?'暂无':lwServiceOrder.serviceType}}</view>
						</view>
						<view class="flex item border-bottom-dashed">
							<view class="flex-sub radius text-label-grey">资金支持和费用承担</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.fundSupport==null?'暂无':lwServiceOrder.fundSupport}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">服务委托单编号</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.serviceNo==null?'暂无':lwServiceOrder.serviceNo}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">智家对接人</view>
							<view class="flex-sub radius text-right text-black">{{lwServiceOrder.serviceManager==null?'暂无':lwServiceOrder.serviceManager}}</view>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">提交申请日期</view>
							<view class="flex-sub radius text-right text-black">
								{{lwServiceOrder.currentDate | date-format}}</view>
						</view>
					</view>
				</view>
				<view class="flex justify-between next-step" style="">
					<button class="cu-btn lg text-white return" @click="returnBack">返回</button>
					<button class="cu-btn lg bg-blue update" :id="lw" @click="toUpdate">确认修改</button>
				</view>
			</view>

		</view>
	</view>
</template>

<script>
	import address from 'utils/page-address.js';
	import serviceOrderApi from '@/api/lw-service-order';
	export default {
		data() {
			return {
				// 服务委托单详情
				lwServiceOrder: {},
				// 订单ID
				activeId: null,
				// 所需人员字符串
				personReq: null,
				// 所需人员等级
				level: ['首席', '资深', '高级', '中级', '初级'],
			}
		},
		onLoad(params) {
			this.activeId = params.activeId
			this.getById(params.activeId)
		},
		methods: {
			// 根据id查询
			getById(id) {
				serviceOrderApi.get(id).then(res => {
					this.lwServiceOrder = res.data
					console.log(res.data)
					this.getPersonReq()
				})
			},
			// 跳转到确认修改页面
			toUpdate(event) {
				var id = event.currentTarget.id
				uni.navigateTo({
					url: address.user_serviceOrder_save + '?activeId=' + this.lwServiceOrder.id
				})
			},
			/**
			 * 返回
			 */
			returnBack: function() {
				uni.navigateBack()
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
			}
		}
	}
</script>

<style lang="scss">
	@import 'serviceOrder-update.scss';
</style>
