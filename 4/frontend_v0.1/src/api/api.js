const api = {
  global: {
    selectdistrict: 'selectdistrict', // GET 获取审方平台区域
    selectunitname: 'selectunitname', // GET 获取 医院
    selectauditcenter: 'selectauditcenter', // GET 获取 审方中心
    getprehisbyid: 'getprehisbyid' // GET 获取处方单信息
  },
  // 字典
  dictionaries: {
    selectalloperator: 'selectalloperator', // GET 获取运算符字典列表
    selectallunit: 'selectallunit', // GET 获取药品单位字典列表
    selectallcrowd: 'selectallcrowd', // GET 所有人群字典
    selectalltype2: 'selectalltype2', // GET 精麻毒药品
    selectallhosstaff: 'selectallhosstaff', // GET 医院医生和药师
    selectallhosdept: 'selectallhosdept', // GET 医院科室
    selectalltype: 'selectalltype', // GET 处方类型
    selectallclassify2: 'selectallclassify2', // GET 药品小分类
    selectallclassify1: 'selectallclassify1', // GET 药品大分类
    selectallhospital: 'selectallhospital', // GET 医院
    selectallauditstate: 'selectallauditstate', // GET 审核状态
    selectallauditlevel: 'selectallauditlevel', // GET 审核水平
    selectcategory: 'selectcategory' // GET 获取药品分类字典
  },
  auth: {
    login: 'getlogin', // POST login
    // 部门 & 用户
    selectalldept: 'selectalldept', // GET 获取部门列表
    getrolename: 'getrolename', // GET 获取角色列表
    updatedept: 'updatedept', // POST 更新部门
    deletedept: 'deletedept', // POST 删除部门
    insertdept: 'insertdept', // POST 新建部门
    getalluser: 'getalluser', // GET 获取用户列表
    updateuser: 'updateuser', // POST 更新用户
    deleteuser: 'deleteuser', // POST 删除用户
    insertuser: 'insertuser', // POST 新建用户
    // 角色
    getpermission: 'getpermission', // GET 获取权限列表
    getrolepermission: 'getrolepermission', // GET 获取权限与角色关系列表
    getrolepermissionbyid: 'getrolepermissionbyid', // GET 通过角色ID获取角色权限
    updaterole: 'updaterole', // POST 更新角色表名称
    deleterole: 'deleterole', // POST 删除角色名称及权限
    insertrole: 'insertrole' // POST 新增角色名称及权限
  },
  // 主页
  home: {
    getpendinghistory: 'getpendinghistory', // POST 获取 待处理处方
    getallprescriptionhistory: 'getallprescriptionhistory', // POST 获取 全部历史处方信息
    selectallauditrulesfold: 'selectallauditrulesfold', // GET 选择代码问题
    hospitalDataList: 'index/hospitalDataList' // GET 各医院数据列表
  },
  // 查看
  search: {
    getallproblemhistory: 'getallproblemhistory', // GET 所有问题处方
    getallhistory: 'getallhistory', // GET 所有处方查询
    getprehisbyid: 'getprehisbyid' // GET 通过处方主键查询处方详情
  },
  // 报表
  report: {
    getreportsummary: 'getreportsummary', // GET 查询处方审核汇总表
    getreportsummarydetail: 'getreportsummarydetail' // GET 查询处方审核明细表
  },

  // 数据维护
  dataMana: {
    getallmedcine: 'getallmedcine', // GET 获取平台药品信息
    getmedcinebyid: 'getmedcinebyid', // GET 平台药品信息展开按钮
    savemedcine: 'savemedcine', // GET 更新平台药品
    getallmedcinehos: 'getallmedcinehos', // GET 获取机构药品信息
    savemedcinehos: 'savemedcinehos', // POST 批量更新机构药品信息
    // 标签管理
    getlabellist: 'getlabellist', // GET 获取标签列表
    getmedicinelistbylabelid: 'getmedicinelistbylabelid', // GET 获取单个标签下的药品
    addlabel: 'addlabel', // POST 添加标签
    updatelabelname: 'updatelabelname', // POST 修改标签
    deletelabelbyid: 'deletelabelbyid', // POST 删除标签
    updatelabelmadicinedetail: 'updatelabelmadicinedetail', // POST 编辑标签下的药品
    // 规则管理
    getallmedcinefold: 'getallmedcinefold', // GET 药品信息下拉
    getallindicationbydiagnosisid: 'getallindicationbydiagnosisid', // GET 获取适用症列表
    updatetpathologyphysiologyindication: 'updatetpathologyphysiologyindication', // POST 修改病生理
    updatetgroupindication: 'updatetgroupindication', // POST 修改人群适应症
    updateageindication: 'updateageindication', // POST 修改年龄适应症
    updatetgestationalweekindication: 'updatetgestationalweekindication', // POST 修改孕周适应症
    //  检查限用
    getcheckitemslist: 'getcheckitemslist', // GET 检查项目列表
    getmedicinelimit: 'getmedicinelimit', // GET 获取药品限用列表
    getmedicinelimitdetilbyrulename: 'getmedicinelimitdetilbyrulename', // GET 获取药品限用信息
    addmedicinelimit: 'addmedicinelimit', // POST 添加规则
    deletemedicinelimitbyrulename: 'deletemedicinelimitbyrulename', // POST 删除规则
    updatemedicinelimitbyrulename: 'updatemedicinelimitbyrulename', // POST 修改规则
    // 用量与时长限制
    selectdosagebymedcineid: 'selectdosagebymedcineid', // GET 根据药品ID获取总剂量限用列表
    insertdosage: 'insertdosage', // POST 新增总剂量限用
    deletedosagebyid: 'deletedosagebyid', // POST 删除总剂量限用
    updatedosagebyid: 'updatedosagebyid', // POST 更新总剂量限用
    selectdosagebyid: 'selectdosagebyid', // GET 根据主键ID获取总剂量限用信息
    selectdurationbymedcineid: 'selectdurationbymedcineid', // GET 根据药物ID获取用药天数限用列表
    insertduration: 'insertduration', // POST 新增用药天数限制
    deletedurationbyid: 'deletedurationbyid', // POST 删除用药天数限用
    updatedurationbyid: 'updatedurationbyid', // POST 更新用药天数限制
    selectdurationbyid: 'selectdurationbyid', // GET 根据主键ID获取用药天数限用信息
    // 药品属性和通用规则
    selectcrowdbymedcineid: 'selectcrowdbymedcineid', // GET 人群限用
    savecrowdrestriction: 'savecrowdrestriction', // POST 人群限用更新
    selectdeptbymedcineid: 'selectdeptbymedcineid', // GET 科室限用
    savedeptrestriction: 'savedeptrestriction', // POST 科室限用更新
    // 配伍管理
    selectcompatibilitybyid: 'selectcompatibilitybyid', // GET 通过id获取配伍管理信息
    selectcompatibilitybymedcineid: 'selectcompatibilitybymedcineid', // GET 获取配伍禁忌
    deletecompatibilitybyid: 'deletecompatibilitybyid', // GET 删除配伍
    insertcompatibility: 'insertcompatibility', // POST 增加配伍
    savecompatibility: 'savecompatibility', // POST 批量保存配伍
    selectallcompatibility: 'selectallcompatibility', // GET 获取所有药品、分类、标签列表
    selectreaction: 'selectreaction', // GET 作用机制与结果智能联想输入

    // 核项目与级别设置
    selectallaudit: 'selectallaudit', // GET 获取全部审核项目与级别设置
    updateauditrules: 'updateauditrules', // POST 更新审核项目与级别设置
    // 诊断与药品限用
    selectalldiagnosis: 'selectalldiagnosis', // GET 诊断信息
    selectlimitbydiagnosisid: 'selectlimitbydiagnosisid', // GET 获取 限用药品及等级设置
    deletediagnosisidmedcine: 'deletediagnosisidmedcine', // POST 删除 限用药物
    insertdiagnsislimit: 'insertdiagnsislimit', // POST 增加 限用药物
    savediagnsislimit: 'savediagnsislimit', // POST 保存 限用药物

    // 适用药及疗程维护
    selectapplicablebydiagnosisid: 'selectapplicablebydiagnosisid', // GET 根据诊断表的id获取适用药品列表
    deleteapplicablebyid: 'deleteapplicablebyid', // POST  通过id删除药物适用表
    insertdiagnsisapplicable: 'insertdiagnsisapplicable', // POST 添加药品适用
    selecttreatmentbydiagnosisid: 'selecttreatmentbydiagnosisid', // GET 根据诊断表id查询疗程信息
    updattreatmentbyid: 'updattreatmentbyid' // POST 保存疗程
  },
  // 设置
  setting: {
    selectallconfig: 'selectallconfig', // GET 获取全部系统设置
    updateconfig: 'updateconfig', // POST 更新系统设置
    selectallrangeconfig: 'selectallrangeconfig', // GET 获取审核范围设置列表
    updaterangeconfig: 'updaterangeconfig', // POST 更新审核范围设置
    selectsetuptime: 'selectsetuptime', // GET 获取超日用量时间设置列表
    selectsetuprule: 'selectsetuprule', // GET 获取药物规则设置列表
    getchecklimitlist: 'getchecklimitlist', // GET 获取肝肾检查项目列表
    getchecklimitlistbyliverkidneydicId: 'getchecklimitlistbyliverkidneydicId', // GET 获取详情
    addchecklimit: 'addchecklimit', // POST 添加规则
    updatechecklimit: 'updatechecklimit', // POST 修改规则
    delatechecklimit: 'delatechecklimit', // GET  删除规则
    getcheckitems: 'getcheckitems', // GET 获取检查项目列表
    getliverlist: 'getliverlist', // GET 获取肝功能列表
    getkidneydiclist: 'getkidneydiclist' // GET 获取肾功能列表
  },
  // 审方中心
  center: {
    linkauditcenter: 'linkauditcenter', // POST 连接审方中心
    insertdistrict: 'insertdistrict', // POST 增加区域
    deletedistrict: 'deletedistrict', // POST 删除区域
    updatedistrict: 'updatedistrict', // POST 更新区域
    inserthospital: 'inserthospital', // POST 增加医院
    deletehospital: 'deletehospital', // POST 删除医院
    updatehospital: 'updatehospital', // POST 更新医院
    updateauditcenter: 'updateauditcenter', // POST 更新审方中心
    deleteauditcenter: 'deleteauditcenter', // POST 删除审方中心
    insertauditcenter: 'insertauditcenter' // POST 增加审方中心
  }
}

export default api
