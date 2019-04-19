import Vue from 'vue'
import Router from 'vue-router'
import * as urls from './routepath'

const Layout = () => import('@/view/layout') // layout
const NotFound = () => import('@/view/error') // 404

const Login = () => import('@/view/login') // 登录
// const Home = () => import('@/view/home/index') // 主页
// const Report = () => import('@/view/report/index') // 报表
const ReportReport = () => import('@/view/report/report') // 审核报表

// 知识库

// 处方点评

// 历史处方查询

// 药品信息管理
// const DrugInfoPass = () => import('@/view/drug/paas') // 平台药品信息管理
// const DrugInfoOrg = () => import('@/view/drug/org') // 机构药品信息管理
const TagSetting = () => import('@/view/drug/tag') // 标签管理
const DrugInfoBasic = () => import('@/view/drug/basic') // 药品基本信息管理
// 规则维护
// const RuleCom = () => import('@/view/rule/check') // 检查结果与药品限用
// const RuleCheck = () => import('@/view/rule/check') // 检查限用
const RuleCheck2 = () => import('@/view/rule/check2') // 用量及时长限制
const RuleCombined = () => import('@/view/rule/combined') // 配伍管理
const RuleReview = () => import('@/view/rule/review') // 审核项目与级别设置
// const RuleDiagnose = () => import('@/view/rule/diagnose') // 诊断与药品限用
const RuleAdaptation = () => import('@/view/rule/adaptation') // 适应症限用
const RuleContraindication = () => import('@/view/rule/contraindication') // 抗感染药物禁忌症
const RuleMaintain = () => import('@/view/rule/maintain') // 适用药及疗程维护

// const AllPrescription = () => import('@/view/search/all') // 所有处方查询
// const ProblemPrescription = () => import('@/view/search/problem') // 问题处方查询
// const PrescriptionDetail = () => import('@/view/search/detail') // 处方查询详情

// const SettingPrescription = () => import('@/view/setting/prescription') // 审方设置
const SettingSystem = () => import('@/view/setting/system') // 系统设置
// const SettingRange = () => import('@/view/setting/range') // 用药时间设置
const SettingMedicine = () => import('@/view/setting/medicine') // 用药时间设置
const SettingExcess = () => import('@/view/setting/excess') // 超日用量时间设置
const SettingRule = () => import('@/view/setting/rule') // 药物规则设置
const SettingRestrict = () => import('@/view/setting/restrict') // 检查限用

// const AuthRole = () => import('@/view/auth/role') // 部门与用户管理
// const AuthUser = () => import('@/view/auth/user') // 角色管理

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
      path: urls.SEARCH,
      name: 'search',
      icon: 'icon1-1',
      meta: { title: '工作台' },
      component: Layout,
      children: []
    },
    {
      key: 'nav',
      path: urls.KNOWLEDGE,
      name: 'knowledge',
      icon: 'icon2-4-1',
      meta: { title: '知识库' },
      component: Layout,
      children: []
    },
    {
      key: 'nav',
      path: urls.COMMENT,
      name: 'comment',
      icon: 'icon2-4-1',
      meta: { title: '处方点评' },
      component: Layout,
      children: []
    },
    {
      key: 'nav',
      path: urls.HISTORY_P,
      name: 'history',
      icon: 'icon1-2',
      meta: { title: '历史处方查看' },
      component: Layout,
      children: []
    },
    {
      key: 'nav',
      path: urls.REPORT,
      name: 'report',
      icon: 'icon1-4',
      meta: { title: '审核报表', isSingle: true },
      component: Layout,
      children: [
        {
          key: 'menu',
          meta: { title: '审核报表' },
          icon: 'report5',
          path: urls.REPORT,
          name: 'report',
          component: ReportReport
        }
      ]
    },
    {
      key: 'nav',
      path: urls.REPORT,
      name: 'drug',
      icon: 'report3',
      meta: { title: '药品信息管理' },
      component: Layout,
      children: [
        {
          key: 'menu',
          meta: { title: '药品基本信息管理' },
          icon: 'icon2-2-1',
          path: urls.DRUGINFO_BASIC,
          name: 'dataManaBasic',
          component: DrugInfoBasic
        },
        {
          key: 'menu',
          meta: { title: '标签管理' },
          icon: 'icon2-2-1',
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
      icon: 'report2',
      meta: { title: '规则维护' },
      component: Layout,
      children: [
        {
          key: 'menu',
          meta: { title: '适应症限用' },
          name: 'adaptation',
          icon: 'icon2-2-1',
          path: urls.RULE_ADAPTATION,
          component: RuleAdaptation
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
          meta: { title: '抗感染药物禁忌症' },
          icon: 'icon2-2-1',
          name: 'contraindication',
          path: urls.RULE_CONTRAINDICATION,
          component: RuleContraindication
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
          meta: { title: '适用药及疗程维护' },
          name: 'maintain',
          icon: 'icon2-2-1',
          path: urls.RULE_MAINTAIN,
          component: RuleMaintain
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
      path: urls.AUTH,
      name: 'auth',
      icon: 'icon-user1',
      meta: { title: '用户与部门' },
      component: Layout,
      children: []
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
          icon: 'icon1-6',
          path: urls.SYSTEM,
          name: 'icon-system',
          component: SettingSystem
        },
        {
          key: 'menu',
          meta: { title: '药品使用量设置' },
          icon: 'icon1-6',
          path: urls.MEDICINE,
          name: 'icon-medicine',
          component: SettingMedicine
        },
        {
          key: 'menu',
          meta: { title: '超日用量时间设置' },
          icon: 'icon1-6',
          path: urls.EXCESS,
          name: 'icon-excess',
          component: SettingExcess
        },
        {
          key: 'menu',
          meta: { title: '药物规则设置' },
          icon: 'icon1-6',
          path: urls.SETTING_RULE,
          name: 'icon-rule',
          component: SettingRule
        },
        {
          key: 'menu',
          meta: { title: '检查值设置' },
          icon: 'icon1-6',
          path: urls.RESTRICT,
          name: 'icon-restrict',
          component: SettingRestrict
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
