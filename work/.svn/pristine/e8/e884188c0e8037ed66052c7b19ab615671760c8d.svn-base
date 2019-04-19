import fetch from '@/utils/fetch'
import fetcher from '@/utils/axios'
import apiUrls from './api'

const {
  login,
  getrolename, // GET 获取角色列表
  selectalldept, // GET 获取部门列表
  updatedept, // POST 更新部门
  deletedept, // POST 删除部门
  insertdept, // POST 新建部门
  getalluser, // GET 获取用户列表
  updateuser, // POST 更新用户
  deleteuser, // POST 删除用户
  insertuser, // POST 新建用户

  getpermission, // GET 获取权限列表
  getrolepermission, // GET 获取权限与角色关系列表
  getrolepermissionbyid, // GET 通过角色ID获取角色权限
  deleterole, // POST 删除角色名称及权限
  insertrole, // POST 新增角色名称及权限
  updaterole // POST 更新角色表名称
} = apiUrls.auth

// 登录
export async function Login(args) {
  const res = await fetch(login, args)
  return res
}

// todo 注册

// 获取权限列表
export async function Permission(args) {
  const res = await fetch(getpermission, args, 'GET')
  return res
}
// 获取权限与角色关系列表
export async function AllRolePer(args) {
  const res = await fetch(getrolepermission, args, 'GET')
  return res
}
// 通过角色ID获取角色权限
export async function GetRolePer(args) {
  const res = await fetch(getrolepermissionbyid, args, 'GET')
  return res
}
// 删除角色名称及权限
export async function DeleteRole(args) {
  const res = await fetch(deleterole, args)
  return res
}
// 新增角色名称及权限
export async function AddRole(args) {
  const res = await fetch(insertrole, args)
  return res
}
// 更新角色表名称
export async function UpdateRole(args) {
  const res = await fetch(updaterole, args)
  return res
}

// 获取角色列表
export async function RoleName(args) {
  const res = await fetch(getrolename, args, 'GET')
  return res
}
// 获取部门列表
export async function AllDept(args) {
  const res = await fetch(selectalldept, args, 'GET')
  return res
}
// 更新部门
export async function UpdateDept(args) {
  const res = await fetch(updatedept, args)
  return res
}
// 删除部门
export async function DeleteDept(args) {
  const res = await fetch(deletedept, args)
  return res
}
// 新建部门
export async function AddDept(args) {
  const res = await fetch(insertdept, args)
  return res
}
// 获取用户列表
export async function AllUser(args) {
  const res = await fetch(getalluser, args, 'GET')
  return res
}
// 更新用户
export async function UpdateUser(args) {
  const res = await fetch(updateuser, args)
  return res
}
// 删除用户
export async function DeleteUser(args) {
  const res = await fetch(deleteuser, args)
  return res
}
// 新建用户
export async function AddUser(args) {
  const res = await fetch(insertuser, args)
  return res
}
