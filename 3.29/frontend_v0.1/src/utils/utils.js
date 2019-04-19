/* eslint-disable */

export function getParameterStr(data) {
  if (typeof data !== 'object') {
    return ''
  }
  return Object.keys(data)
    .map((key, index) => {
      if (data[key] && index !== 0) {
        return `&${key}=${data[key]}`
      } else if (data[key] && index === 0) {
        return `${key}=${data[key]}`
      } else {
        return ''
      }
    })
    .join('')
}

/**
 * 校验密码
 * @param {*} password
 * 字母+数字组合
 * 密码长度: 8-16
 */
export const checkPsd = password => {
  const regPsd = /^(?!([a-zA-Z]+|\d+)$)[a-zA-Z\d]{8,16}$/
  if (password) {
    return regPsd.test(password)
  } else {
    return false
  }
}

// 6位数字
export const checkNum = num => {
  const regNum = /^\d{6}$/
  if (num) {
    return regNum.test(num)
  } else {
    return false
  }
}

// 校验纯中文
export const checkLenAndIsChinese = name => {
  const reg = /^[\u4e00-\u9fa5]+$/
  if (name) {
    return reg.test(name)
  } else {
    return false
  }
}

/*** 解析地址栏
 *
 * @param name
 * @returns {value}
 */
export const getUrlParam = name => {
  var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)') // 构造一个含有目标参数的正则表达式对象
  var r = window.location.search.substr(1).match(reg) // 匹配目标参数
  if (r != null) return unescape(r[2])
  return null // 返回参数值
}
