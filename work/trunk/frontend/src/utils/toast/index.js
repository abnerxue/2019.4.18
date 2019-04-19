import './index.less'
// import icon from '../../assets/alert_error.png'

export default function toast(msg) {
  const div = document.createElement('div')
  div.className = 'toast'
  // div.innerHTML = `<div class="toast-message"><img src=${icon} /><p>${msg}</p></div>`
  div.innerHTML = `<div class="toast-message"><p>${msg}</p></div>`
  document.body.appendChild(div)
  setTimeout(() => {
    document.body.removeChild(div)
  }, 1500)
}
