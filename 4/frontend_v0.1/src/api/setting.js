import fetcher from '@/utils/axios'
import fetch from '@/utils/fetch'
import apiUrls from './api'

const {
  selectallconfig,
  updateconfig,
  selectallrangeconfig,
  updaterangeconfig,
  selectsetuptime,
  selectsetuprule,
  getchecklimitlist,
  addchecklimit, // POST 添加规则
  updatechecklimit, // POST 修改规则
  delatechecklimit, // GET  删除规则
  getcheckitems, // GET 获取检查项目列表
  getchecklimitlistbyliverkidneydicId, // GET 获取详情
  getliverlist, // GET 获取肝功能列表
  getkidneydiclist // GET 获取肾功能列表
} = apiUrls.setting

// 获取全部系统设置
export async function SelectAllConfig(args) {
  const res = await fetch(selectallconfig, args, 'GET')
  return res
}
// 更新系统设置
export async function UpdateConfig(args) {
  const res = await fetcher(updateconfig, args)
  return res
}
// 获取审核范围设置列表
export async function AllRangeConfig(args) {
  const res = await fetch(selectallrangeconfig, args, 'GET')
  return res
}
// 更新审核范围设置
export async function UpdateRangeConfig(args) {
  const res = await fetcher(updaterangeconfig, args)
  return res
}
// 获取超日用量时间设置列表
export async function SelectSetupTime(args) {
  const res = await fetch(selectsetuptime, args, 'GET')
  return res
}
// 获取药物规则设置列表
export async function SelectSetupRule(args) {
  const res = await fetch(selectsetuprule, args, 'GET')
  return res
}
// 获取肝肾检查项目列表
export async function GetChecklimitlist(args) {
  const res = await fetch(getchecklimitlist, args, 'GET')
  return res
}

// 添加规则
export async function AddCheckLimit(args) {
  const res = await fetcher(addchecklimit, args)
  return res
}
// 修改规则
export async function updateCheckLimit(args) {
  const res = await fetcher(updatechecklimit, args)
  return res
}
// 删除规则
export async function DelateCheckLimit(args) {
  const res = await fetch(delatechecklimit, args)
  return res
}
// 获取检查项目列表
export async function GetCheckItems(args) {
  const res = await fetch(getcheckitems, args, 'GET')
  return res
}
// 获取详情
export async function GetCheckItemsDetail(args) {
  const res = await fetch(getchecklimitlistbyliverkidneydicId, args, 'GET')
  return res
}
// 获取肝功能列表
export async function GetLiverList(args) {
  const res = await fetch(getliverlist, args, 'GET')
  return res
}
// 获取肾功能列表
export async function GetkiDneydicList(args) {
  const res = await fetch(getkidneydiclist, args, 'GET')
  return res
}
