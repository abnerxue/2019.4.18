import fetch from '../utils/fetch'
import apiUrls from './api'

const { selectdistrict, selectunitname, selectauditcenter, getprehisbyid } = apiUrls.global

// 获取审方平台区域
export async function AllDistrict(args) {
  const res = await fetch(selectdistrict, args, 'GET')
  return res
}
// 获取 医院
export async function AllUnit(args) {
  const res = await fetch(selectunitname, args, 'GET')
  return res
}
// 获取 审方中心
export async function AllAuditCenter(args) {
  const res = await fetch(selectauditcenter, args, 'GET')
  return res
}
// 获取 处方单信息
export async function GetPrehis(args) {
  const res = await fetch(getprehisbyid, args, 'GET')
  return res
}
