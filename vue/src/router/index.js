import { createRouter, createWebHistory } from 'vue-router'
import Layout from "../layout/Layout";
import cusLogin from "../views/cus/cusLogin";
import cusLayout from "../layout/cus/cusLayout";
import cus from "../layout/cus/cus";
import cusRegister from "../views/cus/cusRegister";
import man from "../layout/man/man";
import manLogin from "../views/man/manLogin";
import Choose from "../views/Choose";

//对全局根节点router-view下的内容进行布置
const routes = [
  {
    path: '/choose',
    name: 'choose',
    component: Choose
  },
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    // 重定向，自动跳转
    redirect: "/choose",
  },
  {
    path: '/cus/',
    name: 'cus',
    component: cus,
    redirect: "/cus/cusLogin",
    children: [
      {
        path: '/cus/cusLogin',
        name: 'cusLogin',
        component: cusLogin
      },
      {
        path: '/cus/cusRegister',
        name: 'cusRegister',
        component: cusRegister
      },
      {
        path: 'cus/temp',
        name: 'cusLayout',
        redirect: "/cus/cusLogin",
        component: () => import("../layout/cus/cusLayout"),
        children: [
          {
            path: '/cus/cusHome',
            name: 'cusHome',
            component: () => import("../views/cus/cusHome"),
          },
          {
            path: '/cus/cusPerson',
            name: 'cusPerson',
            component: () => import("../views/cus/cusPerson"),
          },
          {
            path: '/cus/cusRecharge',
            name: 'cusRecharge',
            component: () => import("../views/cus/cusRecharge"),
          },
          {
            path: '/cus/cusGoods',
            name: 'cusGoods',
            component: () => import("../views/cus/cusGoods"),
          },
          {
            path: '/cus/cusList',
            name: 'cusList',
            component: () => import("../views/cus/cusList"),
          },
        ]
      },
    ]
  },
  {
    path: '/man/',
    name: 'man',
    component: man,
    redirect: "/man/manLogin",
    children: [
      {
        path: '/man/manLogin',
        name: 'manLogin',
        component: manLogin
      },
      {
        path: 'man/temp',
        name: 'manLayout',
        redirect: "/man/manLogin",
        component: () => import("../layout/man/manLayout"),
        children: [
          {
            path: '/man/manHome',
            name: 'manHome',
            component: () => import("../views/man/manHome"),
          },
          {
            path: '/man/manCus',
            name: 'manCus',
            component: () => import("../views/man/manCus"),
          },
          {
            path: '/man/manPerson',
            name: 'manPerson',
            component: () => import("../views/man/manPerson"),
          },
          {
            path: '/man/manCategory',
            name: 'manCategory',
            component: () => import("../views/man/manCategory"),
          },
          {
            path: '/man/manGoods',
            name: 'manGoods',
            component: () => import("../views/man/manGoods"),
          },
          {
            path: '/man/manList',
            name: 'manList',
            component: () => import("../views/man/manList"),
          },
        ]
      },
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
