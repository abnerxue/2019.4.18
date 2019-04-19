import fetch from '@/utils/fetch'
import fetcher from '@/utils/axios'

import apiUrls from './api'

const {
  getallmedcinehos, // 获取机构药品信息
  getallmedcine, // 获取平台药品信息
  savemedcinehos, // 批量更新机构药品信息
  getallmedcinefold, // 规则管理 - 药品信息下拉
  getmedcinebyid, // 平台药品信息展开按钮
  savemedcine, // 更新平台药品
  // 标签管理
  getlabellist, // 获取标签列表
  getmedicinelistbylabelid, // 获取单个标签下的药品
  addlabel, // 添加标签
  updatelabelname, // 修改标签
  deletelabelbyid, // 删除标签
  updatelabelmadicinedetail, // 编辑标签下的药品
  //  检查限用
  getcheckitemslist, // GET 检查项目列表
  getmedicinelimitdetilbyrulename, // GET 获取药品限用信息
  getmedicinelimit, // GET 获取药品限用列表
  addmedicinelimit, // POST 添加规则
  deletemedicinelimitbyrulename, // POST 删除规则
  updatemedicinelimitbyrulename, // POST 修改规则
  // 用量与时长限制
  selectdosagebymedcineid, // GET 根据药品ID获取总剂量限用列表
  selectdosagebyid, // GET 根据主键ID获取总剂量限用信息
  insertdosage, // POST 新增总剂量限用
  deletedosagebyid, // POST 删除总剂量限用
  updatedosagebyid, // POST 更新总剂量限用
  selectdurationbymedcineid, // GET 根据药物ID获取用药天数限用列表
  insertduration, // POST 新增用药天数限制
  deletedurationbyid, // POST 删除用药天数限用
  updatedurationbyid, // POST 更新用药天数限制
  selectdurationbyid, // GET 根据主键ID获取用药天数限用信息
  // 诊断与药品限用
  selectalldiagnosis, // 诊断与药品限用 - 诊断信息
  selectlimitbydiagnosisid, // POST 获取 限用药品及等级设置
  deletediagnosisidmedcine, // POST 删除 限用药物
  insertdiagnsislimit, // POST 增加 限用药物
  savediagnsislimit, // POST 保存 限用药物
  selecttreatmentbydiagnosisid, // POST 获取疗程
  updattreatmentbyid, // POST 保存疗程
  // 药品属性和通用规则
  selectcrowdbymedcineid, // 药品属性和通用规则 - 人群限用
  savecrowdrestriction, // 药品属性和通用规则 - 人群限用更新
  selectdeptbymedcineid, // 药品属性和通用规则 - 科室限用
  savedeptrestriction, // 药品属性和通用规则 - 科室限用更新
  // 核项目与级别设置
  selectallaudit, // 获取 全部审核项目与级别设置
  updateauditrules, // 更新 审核项目与级别设置
  // 配伍管理
  selectcompatibilitybyid, // GET 通过id获取配伍管理信息
  selectcompatibilitybymedcineid, // 配伍禁忌
  deletecompatibilitybyid, // 配伍禁忌 - 删除
  savecompatibility, // 批量保存配伍
  insertcompatibility // 配伍禁忌 - 增加
} = apiUrls.dataMana

// 检查项目列表
export async function AllCheckItemsList(args) {
  const res = await fetch(getcheckitemslist, args, 'GET')
  return res
}
// 获取药品限用信息
export async function GetMedicinelimit(args) {
  const res = await fetch(getmedicinelimitdetilbyrulename, args, 'GET')
  return res
}
// 获取药品限用列表
export async function AllMedicinelimit(args) {
  const res = await fetch(getmedicinelimit, args, 'GET')
  return res
}
// 添加规则
export async function AddMedicinelimit(args) {
  const res = await fetcher(addmedicinelimit, args)
  return res
}
// 删除规则
export async function DeleteMedicinelimit(args) {
  const res = await fetch(deletemedicinelimitbyrulename, args)
  return res
}
// 修改规则
export async function UpdateMedicinelimit(args) {
  const res = await fetcher(updatemedicinelimitbyrulename, args)
  return res
}

// 获取平台药品信息
export async function Allmedcine(args) {
  const res = await fetch(getallmedcine, args, 'GET')
  return res
}
// 平台药品信息展开按钮
export async function Medcinebyid(args) {
  const res = await fetch(getmedcinebyid, args, 'GET')
  return res
}
// 更新平台药品
export async function SaveMedcine(args) {
  const res = await fetcher(savemedcine, args, 'GET')
  return res
}
// 获取机构药品信息
export async function Allmedcinehos(args) {
  const res = await fetch(getallmedcinehos, args, 'GET')
  return res
}
// 批量更新机构药品信息
export async function SaveMedcinehos(args) {
  const res = await fetcher(savemedcinehos, args)
  return res
}
// 根据药品ID获取总剂量限用列表
export async function Alldosage(args) {
  const res = await fetch(selectdosagebymedcineid, args, 'GET')
  return res
}
// 根据主键ID获取总剂量限用信息
export async function GetDosageInfo(args) {
  const res = await fetch(selectdosagebyid, args, 'GET')
  return res
}
// 新增总剂量限用
export async function Adddosage(args) {
  const res = await fetch(insertdosage, args)
  return res
}
// 删除总剂量限用
export async function Deletedosage(args) {
  const res = await fetch(deletedosagebyid, args)
  return res
}
// 更新总剂量限用
export async function Updatedosage(args) {
  const res = await fetch(updatedosagebyid, args)
  return res
}
// 根据药物ID获取用药天数限用列表
export async function Allduration(args) {
  const res = await fetch(selectdurationbymedcineid, args, 'GET')
  return res
}
// 根据主键ID获取用药天数限用信息
export async function GetDurationInfo(args) {
  const res = await fetch(selectdurationbyid, args, 'GET')
  return res
}
// 新增用药天数限制
export async function Addduration(args) {
  const res = await fetch(insertduration, args)
  return res
}
// 删除用药天数限用
export async function Deleteduration(args) {
  const res = await fetch(deletedurationbyid, args)
  return res
}
// 更新用药天数限制
export async function Updateduration(args) {
  const res = await fetch(updatedurationbyid, args)
  return res
}
// 规则管理 - 药品信息下拉
export async function Allmedcinefold(args) {
  const res = await fetch(getallmedcinefold, args, 'GET')
  return res
}
// 规则管理 - 配伍禁忌
export async function GetCompatibilitybyid(args) {
  const res = await fetch(selectcompatibilitybyid, args, 'GET')
  return res
}
// 规则管理 - 配伍禁忌
export async function SelectComp(args) {
  const res = await fetch(selectcompatibilitybymedcineid, args, 'GET')
  return res
}
// 规则管理 - 配伍禁忌 - 增加
export async function AddComp(args) {
  const res = await fetch(insertcompatibility, args)
  return res
}
// 规则管理 - 配伍禁忌 - 批量保存
export async function SaveComp(args) {
  const res = await fetcher(savecompatibility, args)
  return res
}
// 规则管理 - 配伍禁忌 - 删除
export async function DeleteComp(args) {
  const res = await fetch(deletecompatibilitybyid, args, 'GET')
  return res
}
// 规则管理 - 药品属性和通用规则 - 人群限用
export async function Selectcrowdbymedcineid(args) {
  const res = await fetch(selectcrowdbymedcineid, args, 'GET')
  return res
}
// 规则管理 - 药品属性和通用规则 - 人群限用更新
export async function SaveCrowd(args) {
  const res = await fetcher(savecrowdrestriction, args)
  return res
}
// 规则管理 - 药品属性和通用规则 - 科室限用
export async function Selectdeptbymedcineid(args) {
  const res = await fetch(selectdeptbymedcineid, args, 'GET')
  return res
}
// 规则管理 - 药品属性和通用规则 - 科室限用更新
export async function SaveDept(args) {
  const res = await fetcher(savedeptrestriction, args)
  return res
}
// 获取全部审核项目与级别设置
export async function Selectallaudit(args) {
  const res = await fetch(selectallaudit, args, 'GET')
  return res
}
// 更新 审核项目与级别设置
export async function Updateauditrules(args) {
  const res = await fetcher(updateauditrules, args)
  return res
}
// 诊断与药品限用 - 诊断信息
export async function SelectAllDiagnosis(args) {
  const res = await fetch(selectalldiagnosis, args, 'GET')
  return res
}
// 获取 限用药品及等级设置
export async function SelectLimit(args) {
  const res = await fetch(selectlimitbydiagnosisid, args, 'GET')
  return res
}
// 删除 限用药物
export async function DeleteLimit(args) {
  const res = await fetch(deletediagnosisidmedcine, args)
  return res
}
// 增加 限用药物
export async function AddLimit(args) {
  const res = await fetch(insertdiagnsislimit, args)
  return res
}
// 保存 限用药物
export async function SaveLimit(args) {
  const res = await fetcher(savediagnsislimit, args)
  return res
}
// 获取疗程
export async function SelectTreatment(args) {
  const res = await fetch(selecttreatmentbydiagnosisid, args, 'GET')
  return res
}
// 保存疗程
export async function UpdatTreatment(args) {
  const res = await fetch(updattreatmentbyid, args)
  return res
}
// 标签管理 获取标签列表
export async function GetLabelList(args) {
  const res = await fetch(getlabellist, args, 'GET')
  return res
}
// 标签管理 获取单个标签下的药品
export async function GetMedicineListByLabelId(args) {
  const res = await fetch(getmedicinelistbylabelid, args, 'GET')
  return res
}
// 标签管理 添加标签
export async function AddLabel(args) {
  const res = await fetch(addlabel, args)
  return res
}
// 标签管理 修改标签
export async function UpdateLabelName(args) {
  const res = await fetch(updatelabelname, args)
  return res
}
// 标签管理 删除标签
export async function DeleteLabel(args) {
  const res = await fetch(deletelabelbyid, args)
  return res
}
// 标签管理 编辑标签下的药品
export async function UpdateMedicineDetail(args) {
  const res = await fetcher(updatelabelmadicinedetail, args)
  return res
}
