import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import '@awesome.me/webawesome/dist/styles/themes/default.css'
import '@awesome.me/webawesome/dist/styles/webawesome.css'

const app = createApp(App)

app.use(router)

app.mount('#app')
