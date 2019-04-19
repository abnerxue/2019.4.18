import fetch from '../utils/fetch'
import apiUrls from './api'

const {
  selectallcrowd,
  selectalltype2,
  selectallhosdept,
  selectallhosstaff,
  selectalltype,
  selectallclassify2,
  selectallclassify1,
  selectallhospital,
  selectallauditstate,
  selectallauditlevel,
  selectalloperator,
  selectallunit,
  selectcategory
} = apiUrls.dictionaries

// 获取运算符字典列表
export async function AllOperator(args) {
  const res = await fetch(selectalloperator, args, 'GET')
  return res
}
// 获取药品单位字典列表
export async function AllUnitType(args) {
  const res = await fetch(selectallunit, args, 'GET')
  return res
}

// 所有人群
export async function Allcrowd(args) {
  const res = await fetch(selectallcrowd, args, 'GET')
  return res
}
// 精麻毒药品
export async function Alltype2(args) {
  const res = await fetch(selectalltype2, args, 'GET')
  return res
}

// 医院科室
export async function Allhosdept(args) {
  const res = await fetch(selectallhosdept, args, 'GET')
  return res
}

// 医院医生和药师
export async function Allhosstaff(args) {
  const res = await fetch(selectallhosstaff, args, 'GET')
  return res
}

// 处方类型
export async function Alltype(args) {
  const res = await fetch(selectalltype, args, 'GET')
  return res
}
// 药品小分类
export async function Allclassify2(args) {
  const res = await fetch(selectallclassify2, args, 'GET')
  return res
}
// 药品大分类
export async function Allclassify1(args) {
  const res = await fetch(selectallclassify1, args, 'GET')
  return res
}
// 医院
export async function Allhospital(args) {
  const res = await fetch(selectallhospital, args, 'GET')
  return res
}
// 审核状态
// 1:超时通过,2:自动通过,3:提醒,4:单签通过,5:双签通过,6:人工打回,7:自动打回
export async function Allauditstate(args) {
  const res = await fetch(selectallauditstate, args, 'GET')
  return res
}
// 审核等级
// 1:通过，2:提醒，3:人工审核，4:打回
export async function Allauditlevel(args) {
  const res = await fetch(selectallauditlevel, args, 'GET')
  return res
}
// 获取药品分类字典
export async function Allcategory(args) {
  const res = await fetch(selectcategory, args, 'GET')
  return res
}
