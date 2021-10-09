import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  },
  {
    path:'/register',
    name : 'register',
    component : () => import('../views/client_register.vue')
  },
  {
    path:'/result',
    name:'result',
    component : () => import('../views/client_result.vue')
  },
  {
      path:'/check',
      name:'check',
      component : () => import('../views/client_check.vue')
    }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
