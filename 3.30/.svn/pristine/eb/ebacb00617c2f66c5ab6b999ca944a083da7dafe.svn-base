import fetcher from '@/utils/axios'
import fetch from '@/utils/fetch'
import apiUrls from './api'

const { selectallconfig, updateconfig, selectallrangeconfig, updaterangeconfig } = apiUrls.setting

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
