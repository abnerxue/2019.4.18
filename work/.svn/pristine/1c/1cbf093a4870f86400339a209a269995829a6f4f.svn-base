import fetch from '../utils/fetch'
import apiUrls from './api'

const { getpendinghistory, getallprescriptionhistory, selectallauditrulesfold, hospitalDataList } = apiUrls.home

// 获取待处理处方
export async function AllPendinghistory(args) {
  const res = await fetch(getpendinghistory, args)
  return res
}

// 获取全部历史处方信息
export async function AllPrehistory(args) {
  const res = await fetch(getallprescriptionhistory, args)
  return res
}

// 选择代码问题
export async function AllAuditrulesfold(args) {
  const res = await fetch(selectallauditrulesfold, args, 'GET')
  return res
}

// 各医院数据列表
export async function HospitalDataList(args) {
  const res = await fetch(hospitalDataList, args, 'GET')
  return res
}
