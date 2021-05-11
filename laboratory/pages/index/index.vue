<template>
	<view>
		<view v-if="isCanUse">
			<view>
				<view class='header'>
					<image src='/static/logo.png'></image>
				</view>
				<view class='content'>
					<view>申请获取以下权限</view>
					<text>获得你的公开信息(昵称，头像、地区等)</text>
				</view>
				<!-- <button class='bottom' type='primary' open-type="getUserInfo" withCredentials="true" lang="zh_CN"
					@getuserinfo="wxGetUserInfo">
					授权登录
				</button> -->
				<button class='bottom' type='primary' lang="zh_CN" @click="wxGetUserInfo" :disabled="isDisabled==true?'true':'false'">
					登录
				</button>
			</view>
		</view>
	</view>
</template>

<script>
	import weChatApi from '@/api/wechat.js'
	export default {
		data() {
			return {
				SessionKey: '',
				OpenId: '',
				// 当前登录用户
				loginUser: null,
				//登录按钮是否不可用
				isDisabled:true,
				// 页面类型  0:用户页面  1:管理员界面
				pageType: 0,
				isCanUse: uni.getStorageSync('isCanUse') || true //默认为true
			};
		},
		methods: {
			//第一授权获取用户信息===》按钮触发
			wxGetUserInfo() {
				// uni.showLoading({
				// 	title: '登录中...'
				// });
				let _this = this;
				//if (uni.getStorageSync('loginUser') && !_this.isCanUse) {
				uni.getUserProfile({
					desc: '登录',
					success: (res) => {
						console.log(uni.getStorageSync('loginUser'))
						if (!uni.getStorageSync('loginUser')) {
							try {
								uni.setStorageSync('isCanUse', false); //记录是否第一次授权  false:表示不是第一次授权
								_this.getUserInfo(res);
							} catch (e) {}
							_this.login()
						}
						// wx.showLoading()
					},
					fail: (res) => {
						uni.showToast({
							title: '用户取消授权',
							duration: 2000
						});
					},
				})
				//}
			},
			//预登录  获取用户openId..  授权是向后台注册
			login() {
				let _this = this;
				// uni.showLoading({
				// 	title: '登录中...'
				// });
				// 登录获取
				uni.login({
					provider: "weixin",
					success: (res) => {
						console.log(res)
						// 获取微信登录用的code
						const code = res.code
						weChatApi.loginByCode(code).then(res => {
							if (res.data.token) {
								uni.setStorageSync('Authorization', res.data.token)
								weChatApi.getLoginInfo().then(res => {
									uni.setStorageSync('loginUser', res.data)
									_this.loginUser = res.data
									_this.navigateTo()
								})
								this.navigateTo()
								console.log(res.data.token)
							} else {
								uni.setStorageSync('openId', res.data.openId)
								console.log("openid:" + res.data.openId)
								this.$set(this,'isDisabled',false)
							}
							// uni.hideLoading()
							console.log(uni.getStorageSync('loginUser'))
						})
					}
				})

			},
			navigateTo() {
				if (this.loginUser) {
					console.log('page:'+(this.loginUser.level > this.role.user))
					if (this.loginUser.level != this.role.user) {
						uni.redirectTo({
							url: '/pages/selectPage/selectPage'
						})
					} else {
						uni.setStorageSync('pageType', this.role.user_page_num)
						uni.switchTab({
							url: '/pages/home/home'
						})
					}
				}
			},
			//获取用户信息
			getUserInfo(detail) {
				const userInfo = detail.userInfo
				console.log(detail)
				// 获取到openId
				const openId = uni.getStorageSync('openId')
				userInfo.openId = openId
				weChatApi.regitserLogin(userInfo).then(res => {
					uni.showToast({
						title: "登录成功"
					})
					uni.setStorageSync('Authorization', res.data.token)
					console.log(res.data)
				})

			},
			toHome() {
				uni.redirectTo({
					url: '../home/home'
				})
			}
		},
		onShow() {
			// 登录获取
			this.login();
			console.log('admin-reviewer:'+this.role.admin_reviewer)
		},
		onLoad() { //默认加载

		}
	}
</script>

<style>
	.header {
		margin: 90rpx 0 90rpx 50rpx;
		border-bottom: 1px solid #ccc;
		text-align: center;
		width: 650rpx;
		height: 300rpx;
		line-height: 450rpx;
	}

	.header image {
		width: 200rpx;
		height: 200rpx;
	}

	.content {
		margin-left: 50rpx;
		margin-bottom: 90rpx;
	}

	.content text {
		display: block;
		color: #9d9d9d;
		margin-top: 40rpx;
	}

	.bottom {
		border-radius: 80rpx;
		margin: 70rpx 50rpx;
		font-size: 35rpx;
	}
</style>
