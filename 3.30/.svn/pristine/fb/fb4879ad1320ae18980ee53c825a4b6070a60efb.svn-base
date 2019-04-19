import fetch from '../utils/fetch'
import apiUrls from './api'

const {
  linkauditcenter, // POST 连接审方中心
  insertdistrict, // POST 增加区域
  deletedistrict, // POST 删除区域
  updatedistrict, // POST 更新区域
  inserthospital,
  deletehospital,
  updatehospital,
  updateauditcenter,
  deleteauditcenter,
  insertauditcenter
} = apiUrls.center

// 连接审方中心
export async function LinkAuditCenter(args) {
  const res = await fetch(linkauditcenter, args)
  return res
}

// 增加区域
export async function AddDistrict(args) {
  const res = await fetch(insertdistrict, args)
  return res
}

// 删除区域
export async function DeleteDistrict(args) {
  const res = await fetch(deletedistrict, args)
  return res
}

// 更新区域
export async function UpdateDistrict(args) {
  const res = await fetch(updatedistrict, args)
  return res
}

// 增加医院
export async function AddHospital(args) {
  const res = await fetch(inserthospital, args)
  return res
}

// 删除医院
export async function DeleteHospital(args) {
  const res = await fetch(deletehospital, args)
  return res
}

// 更新医院
export async function UpdateHospital(args) {
  const res = await fetch(updatehospital, args)
  return res
}

// 更新审方中心
export async function UpdateAuditCenter(args) {
  const res = await fetch(updateauditcenter, args)
  return res
}

// 删除审方中心
export async function DeleteAuditCenter(args) {
  const res = await fetch(deleteauditcenter, args)
  return res
}

// 增加审方中心
export async function AddAuditCenter(args) {
  const res = await fetch(insertauditcenter, args)
  return res
}
