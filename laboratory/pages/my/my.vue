<template>
	<view class="page">
		<view class="head flex">
			<view class="left flex align-center">
				<view class="cu-avatar round xl avatar" :style="{backgroundImage: 'url(' + loginUser.header + ')'}">
				</view>
				<view class="detail">
					<view class="nickName">{{loginUser.nickName}}</view>
				</view>
			</view>
			<view class="right align-end">
				<text class="lg text-gray cuIcon-right"></text>
			</view>
		</view>
		<view class="list bg-white flex justify-center">
			<view class="container">
				<view class="list-item flex justify-between">
					<view class="left">
						<text class="lg text-green cuIcon-messagefill icon"></text>
						<text class="txt">消息中心</text>
					</view>
					<view class="right">
						<text class="lg text-gray cuIcon-right"></text>
					</view>
				</view>
			</view>
		</view>
		<view class="flex  justify-center btn" v-if="level !== 0">
			<button class="cu-btn bg-blue margin-tb-sm lg" style="width: 90%;"
				@click="changePage">切换界面(当前:{{pageName}})</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				// 页面类型
				pageType: 0,
				pageName: '用户',
				// 0:用户  1:服务委托单  2 进场单  3:顶级权限
				level: 0,
				// 当前登录用户信息
				loginUser: null
			}
		},
		onShow() {
			this.pageType = uni.getStorageSync('pageType')
			this.level = uni.getStorageSync('loginUser').level
			if (this.pageType == this.role.admin_page_num) {
				this.pageName = '管理员'
			} else if (this.pageType == this.role.user_page_num) {
				this.pageName = '用户'
			}
			this.loginUser = uni.getStorageSync('loginUser')
			console.log(this.loginUser)
		},
		methods: {
			changePage() {
				if (this.pageType == this.role.admin_page_num) {
					this.pageType == this.role.user_page_num
					uni.setStorageSync('pageType',this.role.user_page_num)
					this.pageName = '用户'
				} else if (this.pageType == this.role.user_page_num) {
					this.pageType == this.role.admin_page_num
					uni.setStorageSync('pageType',this.role.admin_page_num)
					this.pageName = '管理员'
				}
			}
		}
	}
</script>

<style lang="scss">
	@import 'my.scss';
</style>
