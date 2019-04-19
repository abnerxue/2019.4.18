import Vue from 'vue'
import Router from 'vue-router'
import * as urls from './routepath'

const Layout = () => import('@/view/layout') // layout
const NotFound = () => import('@/view/error') // 404

const Login = () => import('@/view/login') // 登录
const Home = () => import('@/view/home/index') // 主页
const Report = () => import('@/view/report/index') // 报表

// 药品信息管理
const DrugInfoPass = () => import('@/view/drug/paas') // 平台药品信息管理
const DrugInfoOrg = () => import('@/view/drug/org') // 机构药品信息管理
const TagSetting = () => import('@/view/drug/tag') // 标签管理
// 规则维护
// const RuleCom = () => import('@/view/rule/check') // 检查结果与药品限用
const RuleCheck = () => import('@/view/rule/check') // 检查限用
const RuleCheck2 = () => import('@/view/rule/check2') // 用量及时长限制
const RuleCombined = () => import('@/view/rule/combined') // 配伍管理
const RuleReview = () => import('@/view/rule/review') // 审核项目与级别设置
const RuleDiagnose = () => import('@/view/rule/diagnose') // 诊断与药品限用

const AllPrescription = () => import('@/view/search/all') // 所有处方查询
const ProblemPrescription = () => import('@/view/search/problem') // 问题处方查询
const PrescriptionDetail = () => import('@/view/search/detail') // 处方查询详情

const SettingPrescription = () => import('@/view/setting/prescription') // 审方设置
const SettingSystem = () => import('@/view/setting/system') // 系统设置
const SettingRange = () => import('@/view/setting/range') // 用药时间设置

const AuthRole = () => import('@/view/auth/role') // 部门与用户管理
const AuthUser = () => import('@/view/auth/user') // 角色管理

Vue.use(Router)

const vueRouter = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      redirect: urls.LOGIN
    },
    {
      path: urls.LOGIN,
      name: 'login',
      component: Login,
      meta: { title: '登录' }
    },
    {
      key: 'nav',
      path: urls.HOME,
      name: 'home',
      icon: 'icon1-1',
      meta: { title: '主页', isSingle: true },
      component: Layout,
      children: [
        {
          key: 'menu',
          meta: { title: '主页' },
          icon: 'icon1-1',
          path: urls.HOME,
          name: 'home',
          component: Home
        }
      ]
    },
    {
      key: 'nav',
      path: urls.SEARCH,
      name: 'search',
      icon: 'icon1-2',
      meta: { title: '工作台' },
      component: Layout,
      children: [
        {
          key: 'menu',
          meta: { title: '所有处方查询' },
          icon: 'icon2-5-1',
          path: urls.ALL_PRESCRIPTION,
          name: 'allPrescription',
          component: AllPrescription
        },
        {
          key: 'menu',
          meta: { title: '问题处方查询' },
          icon: 'icon-pro',
          path: urls.PROBLEM_PRESCRIPTION,
          name: 'problem',
          component: ProblemPrescription
        },
        {
          key: '',
          meta: {
            isParent: true,
            title: '审方详情'
          },
          icon: '',
          path: urls.PRESCRIPTION_DETAIL,
          name: 'detail',
          component: PrescriptionDetail
        }
      ]
    },
    {
      key: 'nav',
      path: urls.REPORT,
      name: 'report',
      icon: 'icon1-4',
      meta: { title: '审核报表' },
      component: Layout,
      children: [
        {
          key: 'menu',
          meta: { title: '医院审方情况统计' },
          icon: 'report1',
          path: '',
          name: 'report1',
          component: Report,
          children: [
            {
              key: 'level3',
              meta: { title: '各医院门诊审方时间汇总表' },
              path: urls.REPORT11,
              name: 'report11',
              icon: 'icon2-4-3',
              component: Report
            },
            {
              key: 'level3',
              meta: { title: '医院门诊审方结果构成比' },
              path: urls.REPORT12,
              name: 'report12',
              icon: 'icon2-4-3',
              component: Report
            },
            {
              key: 'level3',
              meta: { title: '各医院门诊药品用量分析' },
              path: urls.REPORT13,
              name: 'report13',
              icon: 'icon2-4-3',
              component: Report
            },
            {
              key: 'level3',
              meta: { title: '药品使用频次分析' },
              path: urls.REPORT14,
              name: 'report14',
              icon: 'icon2-4-3',
              component: Report
            },
            {
              key: 'level3',
              meta: { title: '药品使用金额分析' },
              path: urls.REPORT15,
              name: 'report15',
              icon: 'icon2-4-3',
              component: Report
            },
            {
              key: 'level3',
              meta: { title: '医院审方时间曲线图' },
              path: urls.REPORT16,
              name: 'report16',
              icon: 'icon2-4-3',
              component: Report
            },
            {
              key: 'level3',
              meta: { title: '各医院门诊审方结果构成比比较' },
              path: urls.REPORT17,
              name: 'report17',
              icon: 'icon2-4-3',
              component: Report
            }
          ]
        },
        {
          key: 'menu',
          meta: { title: '问题处方情况统计' },
          icon: 'report2',
          path: '',
          name: 'report2',
          component: Report,
          children: [
            {
              key: 'level3',
              meta: { title: '门诊审方打回原因分析' },
              path: urls.REPORT21,
              name: 'report21',
              icon: 'icon2-4-3',
              component: Report
            },
            {
              key: 'level3',
              meta: { title: '各医院门诊审方打回明细表' },
              path: urls.REPORT22,
              name: 'report22',
              icon: 'icon2-4-3',
              component: Report
            }
          ]
        },
        {
          key: 'menu',
          meta: { title: '抗菌药物专项分析' },
          icon: 'report3',
          path: '',
          name: 'report3',
          component: Report,
          children: [
            {
              key: 'level3',
              meta: { title: '医院门诊抗菌药物使用情况统计表' },
              path: urls.REPORT31,
              name: 'report31',
              icon: 'icon2-4-3',
              component: Report
            },
            {
              key: 'level3',
              meta: { title: '各医院门诊抗菌药物使用分析' },
              path: urls.REPORT32,
              name: 'report32',
              icon: 'icon2-4-3',
              component: Report
            }
          ]
        },
        {
          key: 'menu',
          meta: { title: '基本药物统计' },
          icon: 'report4',
          path: '',
          name: 'report4',
          component: Report,
          children: [
            {
              key: 'level3',
              meta: { title: '医院门诊基本药物使用情况统计表' },
              path: urls.REPORT41,
              name: 'report41',
              icon: 'icon2-4-3',
              component: Report
            },
            {
              key: 'level3',
              meta: { title: '医院基本药物使用情况分析' },
              path: urls.REPORT42,
              name: 'report42',
              icon: 'icon2-4-3',
              component: Report
            }
          ]
        },
        {
          key: 'menu',
          meta: { title: '平台与各单位审核规则差异对照表' },
          icon: 'report5',
          path: urls.REPORT5,
          name: 'report5',
          component: Report
        }
      ]
    },
    {
      key: 'nav',
      path: urls.REPORT,
      name: 'drug',
      icon: 'icon4',
      meta: { title: '药品信息管理' },
      component: Layout,
      children: [
        {
          key: 'menu',
          meta: { title: '平台药品信息管理' },
          icon: 'icon2-4-1',
          path: urls.DRUGINFO_PAAS,
          name: 'dataManaPaas',
          component: DrugInfoPass
        },
        {
          key: 'menu',
          meta: { title: '机构药品信息管理' },
          icon: 'icon2-4-2',
          path: urls.DRUGINFO_ORG,
          name: 'dataManaOrg',
          component: DrugInfoOrg
        },
        {
          key: 'menu',
          meta: { title: '标签管理' },
          icon: 'icon2-4-2',
          path: urls.DRUGINFO_TAG,
          name: 'tagSetting',
          component: TagSetting
        }
      ]
    },
    {
      key: 'nav',
      path: urls.DRUGINFO,
      name: 'dataSetting',
      icon: 'icon2-2-2',
      meta: { title: '规则维护' },
      component: Layout,
      children: [
        {
          key: 'menu',
          meta: { title: '检查限用' },
          icon: 'icon2-2-1',
          name: 'ruleCheck',
          path: urls.RULE_CHECK,
          component: RuleCheck
        },
        {
          key: 'menu',
          meta: { title: '用量及时长限制' },
          icon: 'icon2-2-1',
          name: 'ruleCheck2',
          path: urls.RULE_CHECK2,
          component: RuleCheck2
        },
        {
          key: 'menu',
          meta: { title: '配伍管理' },
          icon: 'icon2-2-1',
          name: 'combined',
          path: urls.RULE_COMBINED,
          component: RuleCombined
        },
        {
          key: 'menu',
          meta: { title: '诊断限用' },
          name: 'diagnose',
          icon: 'icon2-2-1',
          path: urls.RULE_DIAGNOSE,
          component: RuleDiagnose
        },
        {
          key: 'menu',
          meta: { title: '审核项目与级别设置' },
          name: 'review',
          icon: 'icon2-2-1',
          path: urls.RULE_REVIEW,
          component: RuleReview
        }
      ]
    },
    {
      key: 'nav',
      meta: { title: '用户与角色管理' },
      icon: 'icon1-5',
      path: urls.AUTH,
      name: 'auth',
      component: Layout,
      children: [
        {
          key: 'menu',
          meta: { title: '部门与用户管理' },
          icon: 'icon-user1',
          path: urls.USER,
          name: 'user',
          component: AuthUser
        },
        {
          key: 'menu',
          meta: { title: '角色管理' },
          icon: 'icon-user2',
          path: urls.ROLE,
          name: 'role',
          component: AuthRole
        }
      ]
    },
    {
      key: 'nav',
      path: urls.PRESCRIPTION,
      name: 'prescription',
      icon: 'icon2-5-2 ',
      meta: { title: '启用审方中心设置', isSingle: true },
      component: Layout,
      children: [
        {
          key: 'menu',
          meta: { title: '启用审方中心设置' },
          icon: 'icon2-5-2',
          path: urls.PRESCRIPTION,
          name: 'prescription',
          component: SettingPrescription
        }
      ]
    },
    {
      key: 'nav',
      meta: { title: '设置' },
      icon: 'icon1-6',
      path: urls.SETTING,
      name: 'setting',
      component: Layout,
      children: [
        {
          key: 'menu',
          meta: { title: '系统设置' },
          icon: 'icon2-6-1',
          path: urls.SYSTEM,
          name: 'icon-system',
          component: SettingSystem
        },
        {
          key: 'menu',
          meta: { title: '审核范围设置' },
          icon: 'icon-range',
          name: 'range',
          path: urls.RANGE,
          component: SettingRange
        }
      ]
    },
    {
      path: '*',
      name: 'err',
      component: NotFound
    }
  ],

  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition
    }

    if (from.meta.keepAlive) {
      from.meta.savedPosition = document.body.scrollTop
    }
    return {
      x: 0,
      y: to.meta.savedPosition || 0
    }
  }
})
vueRouter.beforeEach((to, from, next) => {
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()
})

export default vueRouter
