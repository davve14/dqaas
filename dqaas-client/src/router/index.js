import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import Browse from '@/components/Browse'
import ViewTable from '@/components/ViewTable'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/browse/tables',
      name: 'Browse',
      component: Browse
    },
    {
      path: '/browse/table/:guid',
      name: 'ViewTable',
      component: ViewTable
    }
  ]
})
