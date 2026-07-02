import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../components/HomePage.vue'
import AgendamentoPage from '../components/AgendamentoPage.vue'

const routes = [
  { path: '/', component: HomePage },
  { path: '/agendamento', component: AgendamentoPage }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
