<template>
	<view class="page">
		<view class="head flex">
			<view class="left flex align-center">
				<view class="cu-avatar round xl avatar"
					style="background-image:url(https://ossweb-img.qq.com/images/lol/web201310/skin/big81005.jpg);">
				</view>
				<view class="detail">
					<view class="nickName">aaa</view>
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
		<view class="flex  justify-center btn">
			<button class="cu-btn bg-blue margin-tb-sm lg" style="width: 90%;" @click="changePage">切换界面(当前:用户)</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				
			}
		},
		methods:{
			changePage(){
				// "iconPath": "/static/image/entrust.jpg",
				// "selectedIconPath": "/static/image/entrust-h.jpg",
				// "pagePath": "pages/serviceOrder/serviceOrder",
				// "text": "服务委托单"
				// uni.setTabBarItem({
				//   index: 0,
				//   text: '服务委托单',
				//   iconPath: '/static/image/entrust.jpg',
				//   selectedIconPath: '/static/image/entrust-h.jpg',
				//   pagePath:'pages/serviceOrder/serviceOrder'
				// })
				// uni.switchTab({
				// 	url:'pages/serviceOrder/serviceOrder'
				// })
			}
		}
	}
</script>

<style lang="scss">
	@import 'my.scss';
</style>
