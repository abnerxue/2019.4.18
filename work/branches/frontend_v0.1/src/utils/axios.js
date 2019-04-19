import axios from 'axios'
import { baseURL } from '@/global/config'

const fetcher = axios.create({
  method: 'post',
  headers: {
    'Content-Type': 'application/json;charset=UTF-8'
  },
  baseURL
})

const token = localStorage.getItem('token')
fetcher.defaults.headers.common['token'] = token

export default function fetch(apiUrl, arg) {
  return fetcher.post(apiUrl, arg).then((data) => {
    if (data.data.code === 400) {
      localStorage.clear()
      this.$router.push({ path: this.$urls.LOGIN })
    }
    if (data) {
      return data.data
    }
    return Promise.reject(data.data)
  })
}
