const list = [
  {
    "id": 1,
    "district": "吴中区",
    "unitName": "苏大附一院",
    "auditCenterName": "苏大附一院2号",
    "ip": "127.0.0.2",
    "platformAuditState": true
  },
  {
    "id": 2,
    "district": "姑苏区",
    "unitName": "苏大附二院",
    "auditCenterName": "苏大附二院1号",
    "ip": "127.0.0.3",
    "platformAuditState": true
  },
  {
    "id": 3,
    "district": "新区",
    "unitName": "苏大附一院",
    "auditCenterName": "苏大附一院2号",
    "ip": "127.0.0.2",
    "platformAuditState": true
  },
  {
    "id": 4,
    "district": "姑苏区",
    "unitName": "苏大附一院",
    "auditCenterName": "苏大附一院2号",
    "ip": "127.0.0.2",
    "platformAuditState": true
  },
  {
    "id": 5,
    "district": "吴中区",
    "unitName": "苏大附二院",
    "auditCenterName": "苏大附二院5号",
    "ip": "127.0.0.3",
    "platformAuditState": true
  }
]

function formartList (list = []) {
  let districtId = 0
  const districtArr = [...new Set(list.map(item => item.district))].map(item => ({
    label: item,
    value: item,
    id: 'd' + ++districtId
  }))
  // 医院名字
  list.forEach(item => {
    let parent = districtArr.find(x => x.label === item.district),
      children = parent.children
    if (children) {
      !children.find(x => x.label === item.unitName) && children.push({ label: item.unitName, value: item.unitName, id: 'u' + ++districtId })
    } else {
      parent.children = [{ label: item.unitName, value: item.unitName, id: ++districtId }]
    }
  })
  // 科室
  list.forEach(item => {
    let unitObj = districtArr.find(x => x.label === item.district).children.find(y => y.label === item.unitName),
      obj = {
        label: item.auditCenterName,
        value: item.id,
        id: item.id,
        ip: item.ip,
        platformAuditState: item.platformAuditState
      }
    if (unitObj.children) {
      unitObj.children.push(obj)
    } else {
      unitObj.children = [obj]
    }
  })
  return districtArr
}

formartList(list)