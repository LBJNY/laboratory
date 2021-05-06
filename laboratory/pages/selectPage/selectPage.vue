<template>
	<view>
		<view class="">
			<view class="container">
				<view class="title text-center">请选择要进入的页面</view>
				<view class="btns align-center flex justify-center">
					<button class="cu-btn bg-blue lg btn" @click="selectPage(1)">管理员页面</button>
					<button class="cu-btn bg-blue lg btn" @click="selectPage(0)">用户页面</button>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				// 当前登录用户
				loginUser: null,
			};
		},
		onShow() {
			this.loginUser=uni.getStorageSync('loginUser')
		},
		methods:{
			// 挑选页面  管理员 Or 用户(默认)
			selectPage(pageNum) {
				let _this = this;
				console.log('page:'+(pageNum=== this.role.admin_page_num))
				if (pageNum=== this.role.admin_page_num) {
					console.log('管理员')
					uni.setStorageSync('pageType', this.role.admin_page_num)
				} else {
					console.log('用户')
					uni.setStorageSync('pageType', this.role.user_page_num)
				}
				uni.switchTab({
					url: '/pages/home/home'
				})
			},
		}
	}
</script>

<style lang="scss">
	@import 'selectPage.scss';
</style>
