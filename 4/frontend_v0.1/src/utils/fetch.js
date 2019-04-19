/* eslint-disable */
import qs from 'qs'
import Loading from './loading'
import { baseURL } from '../global/config'
import VueRouter from 'vue-router';
// import toast from './toast'

const loading = new Loading({
  loadlingid: 'loading'
})

export default async (url = '', args = {}, method = 'POST') => {
  let newURL = url
  if (/^(http|https):\/\//.test(url)) {
    newURL = url
  } else {
    newURL = baseURL + url
  }

  let data = args
  // if (isToken) {
  //   data = Object.assign(args, {
  //     token: localStorage.getItem('token')
  //   })
  // }

  if (data.isLoading !== false) {
    loading.show()
  }

  if (window.fetch) {
    let token = localStorage.getItem('token')
    const requestConfig = {
      credentials: 'include',
      method,
      headers: {
        Accept: 'application/json',
        token: token
      },
      mode: 'cors',
      cache: 'force-cache'
    }

    if (method === 'POST') {
      Object.defineProperty(requestConfig, 'body', {
        value: qs.stringify(data)
      })
      requestConfig.headers['Content-Type'] = 'application/x-www-form-urlencoded'
    } else if (method === 'GET') {
      const date = new Date().getTime()
      newURL = `${newURL}?${qs.stringify(data)}&${date}`
    }

    try {
      const response = await fetch(newURL, requestConfig)
      const responseJson = await response.json()
      loading.hide()
      if (responseJson.code === 0) {
        return responseJson
      } else if (responseJson.code === 400) {
        // token 失效
        // toast('登录已失效，请重新登录')
        localStorage.clear()
        this.$router.push({ path: this.$urls.LOGIN })
      } else {
        // toast(responseJson.msg)
        return responseJson
      }
      throw new Error(responseJson.code)
    } catch (error) {
      throw new Error(error)
    } finally {
      loading.hide()
    }
  } else {
    return new Promise((resolve, reject) => {
      let requestObj
      if (window.XMLHttpRequest) {
        requestObj = new XMLHttpRequest()
      } else {
        requestObj = new ActiveXObject() // eslint-disable-line
      }

      const sendData = qs.stringify(data)

      requestObj.open(method, newURL, true)
      if (method === 'POST') {
        requestObj.setRequestHeader('Content-type', 'application/x-www-form-urlencoded')
      } else if (method === 'GET') {
        const date = new Date().getTime()
        newURL = `${newURL}?${qs.stringify(data)}&${date}`
      }
      requestObj.send(sendData)

      requestObj.onreadystatechange = () => {
        if (requestObj.readyState === 4) {
          loading.hide()
          if (requestObj.status === 200) {
            let obj = requestObj.response
            if (typeof obj !== 'object') {
              obj = JSON.parse(obj)
            }
            if (obj.code === 0) {
              resolve(obj)
            }
            reject(obj.code)
          } else {
            reject(requestObj)
          }
        }
      }
    })
  }
}
