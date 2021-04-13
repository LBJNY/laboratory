<script>
	export default {
		onLaunch: function() {
			console.log('App Launch')
		},
		onShow: function() {
			console.log('App Show')
		},
		onHide: function() {
			console.log('App Hide')
		}
	}
</script>

<style>
	/*每个页面公共css */
	@import 'colorui/main.css';
	@import 'colorui/icon.css';
	@import 'style/FontStyle.css';
	@import 'style/common.css';
	uni-radio .uni-radio-input {
		width: 20rpx !important;
		height: 20rpx !important;
	}
	/* page{
		background-color: #FFFFFF;
	} */
</style>
