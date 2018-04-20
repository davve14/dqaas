import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Browse from '@/components/Browse'
import CreateRule from '@/components/CreateRule'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/browse',
      name: 'Browse',
      component: Browse
    },
    {
      path: '/createrule',
      name: 'CreateRule',
      component: CreateRule
    }
  ]
})
