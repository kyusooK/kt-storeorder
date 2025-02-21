
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import OrderappOrderManager from "./components/listers/OrderappOrderCards"
import OrderappOrderDetail from "./components/listers/OrderappOrderDetail"

import OrderPageView from "./components/OrderPageView"
import OrderPageViewDetail from "./components/OrderPageViewDetail"
import UserUserManager from "./components/listers/UserUserCards"
import UserUserDetail from "./components/listers/UserUserDetail"

import MarketingPromotionManager from "./components/listers/MarketingPromotionCards"
import MarketingPromotionDetail from "./components/listers/MarketingPromotionDetail"
import MarketingCouponManager from "./components/listers/MarketingCouponCards"
import MarketingCouponDetail from "./components/listers/MarketingCouponDetail"

import StoreMenuManager from "./components/listers/StoreMenuCards"
import StoreMenuDetail from "./components/listers/StoreMenuDetail"
import StoreStroeManager from "./components/listers/StoreStroeCards"
import StoreStroeDetail from "./components/listers/StoreStroeDetail"

import GetMenuView from "./components/GetMenuView"
import GetMenuViewDetail from "./components/GetMenuViewDetail"

export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/orderapps/orders',
                name: 'OrderappOrderManager',
                component: OrderappOrderManager
            },
            {
                path: '/orderapps/orders/:id',
                name: 'OrderappOrderDetail',
                component: OrderappOrderDetail
            },

            {
                path: '/orderapps/orderPages',
                name: 'OrderPageView',
                component: OrderPageView
            },
            {
                path: '/orderapps/orderPages/:id',
                name: 'OrderPageViewDetail',
                component: OrderPageViewDetail
            },
            {
                path: '/users/users',
                name: 'UserUserManager',
                component: UserUserManager
            },
            {
                path: '/users/users/:id',
                name: 'UserUserDetail',
                component: UserUserDetail
            },

            {
                path: '/marketings/promotions',
                name: 'MarketingPromotionManager',
                component: MarketingPromotionManager
            },
            {
                path: '/marketings/promotions/:id',
                name: 'MarketingPromotionDetail',
                component: MarketingPromotionDetail
            },
            {
                path: '/marketings/coupons',
                name: 'MarketingCouponManager',
                component: MarketingCouponManager
            },
            {
                path: '/marketings/coupons/:id',
                name: 'MarketingCouponDetail',
                component: MarketingCouponDetail
            },

            {
                path: '/stores/menus',
                name: 'StoreMenuManager',
                component: StoreMenuManager
            },
            {
                path: '/stores/menus/:id',
                name: 'StoreMenuDetail',
                component: StoreMenuDetail
            },
            {
                path: '/stores/stroes',
                name: 'StoreStroeManager',
                component: StoreStroeManager
            },
            {
                path: '/stores/stroes/:id',
                name: 'StoreStroeDetail',
                component: StoreStroeDetail
            },

            {
                path: '/stores/getMenus',
                name: 'GetMenuView',
                component: GetMenuView
            },
            {
                path: '/stores/getMenus/:id',
                name: 'GetMenuViewDetail',
                component: GetMenuViewDetail
            },


    ]
})
