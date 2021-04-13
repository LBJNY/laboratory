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
			<view class="back" @click="">
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
				<view class="appliction  commmon box-sizing">
					<view class="big-title text-black">
						<strong>服务工单完成记录</strong>
					</view>
					<view class="detail bg-white">
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">测试服务进度</view>
							<radio-group name="" class="radio-group">
								<label>
									<radio class="blue radio-a" :class="radio=='A'?'checked':''" :checked="radio=='A'?true:false" value="A"></radio>
									<text>全部完成</text>
								</label>
								<label>
									<radio class='blue radio-a' :class="radio=='D'?'checked':''"
										:checked="radio=='D'?true:false" value="D">
									</radio><text>部分完成</text>
								</label>
								<label>
									<radio class='red radio-a' :class="radio=='D'?'checked':''"
										:checked="radio=='D'?true:false" value="D">
									</radio><text>没有完成</text>
								</label>
							</radio-group>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">测试服务质量</view>
							<radio-group name="" class="radio-group">
								<label style="margin-right: 48rpx;">
									<radio class='blue radio-a' :class="radio=='D'?'checked':''"
										:checked="radio=='D'?true:false" value="D">
									</radio>
									<text>很好</text>
								</label>
								<label style="margin-right: 48rpx;">
									<radio class='blue radio-a' :class="radio=='D'?'checked':''"
										:checked="radio=='D'?true:false" value="D">
									</radio><text>一般</text>
								</label>
								<label style="margin-right: 46rpx;">
									<radio class='red radio-a' :class="radio=='D'?'checked':''"
										:checked="radio=='D'?true:false" value="D">
									</radio><text>较差</text>
								</label>
							</radio-group>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">测试服务收费</view>
							<radio-group name="" class="radio-group">
								<label>
									<radio class='blue radio-a' :class="radio=='D'?'checked':''"
										:checked="radio=='D'?true:false" value="D">
									</radio>
									<text>非常合理</text>
								</label>
								<label>
									<radio class='blue radio-a' :class="radio=='D'?'checked':''"
										:checked="radio=='D'?true:false" value="D">
									</radio><text>基本合理</text>
								</label>
								<label>
									<radio class='red radio-a' :class="radio=='D'?'checked':''"
										:checked="radio=='D'?true:false" value="D">
									</radio><text>不太合理</text>
								</label>
							</radio-group>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">整体满意度</view>
							<radio-group name="" class="radio-group">
								<label>
									<radio class='blue radio-a' :class="radio=='D'?'checked':''"
										:checked="radio=='D'?true:false" value="D">
									</radio>
									<text>非常满意</text>
								</label>
								<label>
									<radio class='blue radio-a' :class="radio=='D'?'checked':''"
										:checked="radio=='D'?true:false" value="D">
									</radio><text>基本满意</text>
								</label>
								<label>
									<radio class='red radio-a' :class="radio=='D'?'checked':''"
										:checked="radio=='D'?true:false" value="D">
									</radio><text>不太满意</text>
								</label>
							</radio-group>
						</view>
						<view class="flex item">
							<view class="flex-sub radius text-label-grey">申请单位和部门确认</view>
							<xfl-select :list="serviceType" :clearable="false" :showItemNum="5" :isCanInput="true"
								:style_Container="listBoxStyle" :placeholder="'placeholder'" :initValue="'--请选择--'"
								:selectHideType="'independent'" :props="item.id" @change="change"
								style_Container="height:50rpx;border: 1px solid #919EFF;width:280rpx;font-size: 26rpx;">
							</xfl-select>
						</view>
						<view class="flex">
							<view class="flex-sub radius text-label-grey">完工日期</view>
							<view class="bg-white flex align-center" style="line-height: 34rpx; font-size: 26rpx;">
								<uni-datetime-picker style="width: 280rpx;"></uni-datetime-picker>
							</view>
						</view>
					</view>

				</view>
			</view>
		</view>
		<view class="flex justify-center next-step" style="">
			<view class="container justify-between flex">
				<button class="cu-btn lg text-white return" @click="returnBack">返回</button>
				<button class="cu-btn lg text-white bg-blue confirm">确认提交</button>
			</view>
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
	@import 'serviceOrder-feedback.scss';
</style>
