import fetch from '../utils/fetch'
import apiUrls from './api'

const {
  getallproblemhistory,
  getallhistory,
  getprehisbyid
} = apiUrls.search

// 所有问题处方
export async function Allproblemhistory(args) {
  const res = await fetch(getallproblemhistory, args, 'GET')
  return res
}
// 所有处方查询
export async function Allhistory(args) {
  const res = await fetch(getallhistory, args, 'GET')
  return res
}
// 通过处方主键查询处方详情
export async function Prehisbyid(args) {
  const res = await fetch(getprehisbyid, args, 'GET')
  return res
}
