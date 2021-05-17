<template>
	<view>
		<view>
			<view class='header'>
				<image src='/static/logo.png'></image>
			</view>
			<view class='content'>
				<view>申请获取以下权限</view>
				<text>获得你的手机号码并进行绑定</text>
			</view>
			<button class="bottom" type='primary' open-type="getPhoneNumber"
				@getphonenumber="getPhoneNumber">手机号授权</button>
		</view>
	</view>
</template>

<script>
	import WXBizDataCrypt from "@/common/WXBizDataCrypt.js";
	import weChatApi from '@/api/wechat.js'
	export default {
		data() {
			return {
				sessionKey: null,
				OpenId: '',
				encryptDto: {}
			};
		},
		onShow() {
			this.sessionKey = uni.getStorageSync('sessionKey')
			this.$set(this.encryptDto, 'sessionKey', uni.getStorageSync('sessionKey'))
			console.log(this.sessionKey)
		},
		methods: {
			getPhoneNumber(e) {
				console.log(e.detail);
				// let pc = new WXBizDataCrypt('wx5902fd0785847676', this.SessionKey); //wxXXXXXXX为你的小程序APPID  
				// console.log(pc)
				//let data = pc.decryptData(e.detail.encryptedData, e.detail.iv);
				//console.log(data)
				if (e.detail.errMsg == 'getPhoneNumber:fail user deny') {
					console.log('取消授权')
				} else {
					console.log('通过授权')
					if (uni.checkSession()) {
						var encryptedData = e.detail.encryptedData
						var iv = e.detail.iv
						this.$set(this.encryptDto, 'encryptedData', encryptedData)
						this.$set(this.encryptDto, 'iv', iv)
						console.log(this.encryptDto)
						weChatApi.bindPhoneNumber(this.encryptDto).then(res => {
							console.log(res)
							uni.showToast({
								title: "授权成功",
								duration: 2000,
								success() {
									uni.switchTab({
										url: '/pages/home/home'
									})
								}
							})
						}).catch(err => {
							uni.showToast({
								title: '授权失败,请稍后重试',
								duration: 2000,
								icon: 'none'
							});
						})
					}
				}
			}
		}
	}
</script>

<style lang="scss">
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
