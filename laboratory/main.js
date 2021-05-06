import Vue from 'vue'
import App from './App'

import '@/utils/data-format' // 加载过滤器
import cuCustom from 'colorui/components/cu-custom.vue'
import role from '@/utils/role.js';
Vue.prototype.role=role;
Vue.config.productionTip = false

App.mpType = 'app'


Vue.component('cu-custom',cuCustom)
const app = new Vue({
    ...App
})
app.$mount()
