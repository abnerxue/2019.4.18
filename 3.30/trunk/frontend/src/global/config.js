/* eslint-disable */
let baseURL = ''

if (process.env.NODE_ENV === 'development') {
  baseURL = 'http://192.168.0.10:8084/' // 开发（玉琪）
  // baseURL = 'http://192.168.0.106:8080/' // 开发(丁总)
} else if (process.env.NODE_ENV === 'testing') {
  baseURL = 'http://192.168.0.10:8084/' // 测试
} else if (process.env.NODE_ENV === 'pre') {
  baseURL = '' // 预发
} else if (process.env.NODE_ENV === 'production') {
  baseURL = 'http://192.168.0.10:8084/' // 生产
}

export { baseURL }
