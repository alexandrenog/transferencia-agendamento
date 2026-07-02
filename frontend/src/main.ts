import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import '@awesome.me/webawesome/dist/styles/themes/default.css'
import '@awesome.me/webawesome/dist/styles/webawesome.css'
import '@awesome.me/webawesome/dist/components/page/page.js';
import '@awesome.me/webawesome/dist/components/button/button.js';
import '@awesome.me/webawesome/dist/components/input/input.js';
import '@awesome.me/webawesome/dist/components/callout/callout.js'
import '@awesome.me/webawesome/dist/components/known-date/known-date.js'
import '@awesome.me/webawesome/dist/webawesome.js'
import '@awesome.me/webawesome/dist/translations/pt.js'
import 'vue-sonner/style.css'


const app = createApp(App)

app.use(router)

app.mount('#app')
