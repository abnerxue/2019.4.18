<style lang="less" scoped>
.labelName {
  font-size: 14px;
  line-height: 50px;
  width: 80px;
  text-align: center;
  display: flex;
  align-items: center;
}

.cdzmc {
  width: 12.5vw;
  margin-top: 5px;
  border-radius: 4px;
  font-size: 14px;
  color: #333333;
  height: 38px;
}

.plateNum {
  width: 12.5vw;
  margin-top: 5px;
  border-radius: 4px;
  font-size: 14px;
  color: #333333;
  height: 38px;
}

.searchItem {
  display: flex;
  align-items: center;
  margin-left: -110px;
}

.title {
  width: 100px;
  line-height: 24px;
  height: 24px;
  font-size: 16px;
  color: #fff;
  margin-left: 20px;
  border-left: 4px solid #0084f3;
  position: relative;
  top: 20px;
  margin-bottom: 20px;
  text-align: left;
  padding-left: 10px;
}

.ivu-table-wrapper {
  margin: 10px;
}

#vehicleTable {
  min-width: 580px;
}

.displayShowUserModal {
  display: none;
}

.showUserModalBlock {
  display: block;
}

.displayShowUserModal {
  display: none;
}

.showUserModalBlock {
  display: block;
}

.addRoleModal {
  z-index: 19891016;
  width: 650px;
  height: 700px;
  background-color: #fff;
  position: absolute;
  left: 300px;
  top: 165px;
  box-shadow: rgba(0, 0, 0, 0.2) 2px 2px 2px 2px;
}

.showLineTitle {
  height: 40px;
  line-height: 40px;
  background: rgb(0, 132, 243);
  font-size: 14px;
  text-align: left;
  vertical-align: middle;
  padding-left: 15px;
  color: #fff;
  cursor: move;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.scroll {
  z-index: 10;
  width: 100%;
  height: 360px;
  overflow: auto;
  float: right;
  position: absolute;
  top: 8%;
  border: none;
  margin-top: 120px;
  padding-left: 150px;
}
.scrollbar {
  width: 10px;
  height: 0;
  margin: 0 auto;
}
.scroll-1::-webkit-scrollbar {
  /*滚动条整体样式*/
  width: 5px; /*高宽分别对应横竖滚动条的尺寸*/
  height: 1px;
}
.scroll-1::-webkit-scrollbar-thumb {
  /*滚动条里面小方块*/
  border-radius: 5px;
  -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  background: #535353;
}
.scroll-1::-webkit-scrollbar-track {
  /*滚动条里面轨道*/
  -webkit-box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  border-radius: 5px;
  background: #ededed;
}
</style>
<template>
  <div
    style="background: #282c35;color: #fff;height:calc(100vh - 100px);position: absolute;top: 80px;min-width: 625px;"
    id="actionStatistics"
  >
    <div style="background: #18191D;max-height: calc(100vh- 120px);margin-left: 20px; margin-top: 25px;width:calc(100vw - 320px);height:97%;">
      <h5 class="title">角色管理</h5>
      <div style="display: flex;min-width: 600px;flex-direction: row;align-items: center;justify-content: space-around;position: relative;margin-top: 45px;margin-bottom: 30px;">
        <div class="searchItem">
          <div class="labelName">角色代号：</div>
          <Input
            v-model="userName"
            placeholder="请输入角色代号"
            class="plateNum"
          />
          <div
            class="labelName"
            style="margin-left:20px;"
          >角色名称：</div>
          <Input
            v-model="roleName"
            placeholder="请输入角色名称"
            class="plateNum"
          />
        </div>
        <div class="buttonList">
          <Button
            type="primary"
            @click="addPerson"
          >添加</Button>
          <Button
            type="primary"
            @click="searchList"
            style="margin-left: 20px;"
          >查询</Button>
        </div>
      </div>
      <div style="padding-left: 10px;padding-right: 10px;">
        <Table
          :columns="columns"
          :data="tableData"
          id="vehicleTable"
        ></Table>
        <Page
          :total="tableDataLength"
          :current="pageIndex"
          @on-change="changePage"
          :page-size="pageSize"
          style="margin-top:0px;"
        ></Page>
      </div>
    </div>
    <div
      class="addRoleModal"
      id="addRoleModal"
      :class="{displayShowUserModal:isDisplayShowUserModal,showUserModalBlock:isShowUserModal}"
    >
      <div
        class="showLineTitle"
        id="showAddUser"
      >
        <div>添加角色</div>
        <img
          alt
          src="/static/image/icon_close.png"
          style="margin-right: 10px;width:10px;height:10px;cursor:pointer"
          @click="closeUserModal"
        >
      </div>
      <Form
        ref="formInline"
        :model="formInline"
        :rules="ruleInline"
        inline
        style="color:black;margin-top: 40px;"
      >
        <div>
          <FormItem
            prop="userAccount"
            label="角色代号："
          >
            <Input
              type="text"
              v-model="formInline.userAccount"
              onkeydown="if(event.keyCode==32) return false"
            ></Input>
          </FormItem>

          <FormItem
            prop="userName"
            label="角色名称："
          >
            <Input
              v-model="formInline.userName"
              type="text"
              onkeydown="if(event.keyCode==32) return false"
            ></Input>
          </FormItem>
        </div>
        <div class="scroll scroll-1">
          <FormItem
            prop="userRole"
            label="角色权限："
            ref="userRole"
            style="margin-left: -80px;margin-top: 10px;"
          >
            <Tree
              :data="userRoleList"
              show-checkbox
              multiple
              ref="treeData"
              @on-check-change="getSelectedNode($event)"
            ></Tree>
          </FormItem>
        </div>
        <FormItem>
          <div style="margin-top:440px;">
            <Button
              type="primary"
              @click="handleReset('formInline')"
              style="margin-right:20px;"
            >重置</Button>
            <div v-if="saveOrUpdate == '保存'"><Button
                type="primary"
                @click="handleSubmit('formInline')"
              >{{saveOrUpdate}}</Button></div>
            <div v-else>
              <Button
                type="primary"
                @click="handleUpdate('formInline')"
              >{{saveOrUpdate}}</Button>
            </div>
          </div>
        </FormItem>
      </Form>
    </div>
  </div>
</template>
<script>
import dragDown from "../../../static/js/dragDown.js";

export default {
  name: "actionStatistics",
  data() {
    const validateUserName = (rule, value, callback) => {
      if (value.trim() === "") {
        callback(new Error("请输入用户名称"));
      } else {
        if (/[@#\$%\^&\*]+/g.test(value.trim())) {
          callback(new Error("含有非法字符，请重新输入"));
        }
        callback();
      }
    };

    const validateUserAccount = (rule, value, callback) => {
      if (value.trim() === "") {
        callback(new Error("请输入账户名称"));
      }
      callback();
    };

    const validatePhone = (rule, value, callback) => {
      if (value.trim() === "") {
        callback(new Error("请输入联系方式"));
      } else {
        if (!/^[1][3,4,5,7,8，9][0-9]{9}$/.test(value.trim())) {
          callback(new Error("手机号码格式错误"));
        }
        callback();
      }
    };

    const validateCfmPhone = (rule, value, callback) => {
      if (value.trim() === "") {
        callback(new Error("请输入密码"));
      } else {
        if (value.trim() !== this.formInline.password.trim()) {
          callback(new Error("两次输入的密码不一致"));
        }
        callback();
      }
    };
    return {
      saveOrUpdate: "保存",
      treeTitleList: [],
      treeData: [],
      userRoleList: [],
      isDisplayShowUserModal: true,
      isShowUserModal: false,
      userName: "",
      roleName: "",
      actionDetailList: [],
      formInline: {
        userAccount: "",
        userName: ""
      },
      userName: "",
      roleName: "",
      ruleInline: {
        userAccount: [
          {
            required: true,
            message: "请输入账号",
            trigger: "blur"
          },
          { validator: validateUserAccount, trigger: "blur" }
        ],
        userName: [
          {
            required: true,
            message: "请输入用户姓名",
            trigger: "blur"
          },
          { validator: validateUserName, trigger: "blur" }
        ]
      },
      columns: [
        {
          title: "角色代号",
          key: "jsdh",
          // fixed: "left",
          minWidth: 100,
          align: "left"
        },
        {
          title: "角色名称",
          key: "jsmc",
          minWidth: 100,
          align: "left"
        },
        {
          title: "备注",
          key: "bz",
          minWidth: 100,
          align: "left"
        },
        {
          title: "操作",
          key: "action",
          minWidth: 150,
          align: "center",
          // fixed: "right",
          render: (h, params) => {
            return h("div", [
              h(
                "Button",
                {
                  props: {
                    type: "info",
                    size: "small"
                  },
                  on: {
                    click: () => {
                      this.formInline.userAccount = params.row.jsdh;
                      this.formInline.userName = params.row.jsmc;
                      this.();
                      this.saveOrUpdate = "更新";
                      this.isdisplayPassword = true;
                      this.isShowDisplayPassword = false;
                    }
                  }
                },
                "编辑"
              ),
              h(
                "Button",
                {
                  props: {
                    type: "error",
                    size: "small"
                  },
                  style: {
                    marginLeft: "20px"
                  },
                  on: {
                    click: () => {
                      this.$Modal.confirm({
                        title: "请确认",
                        content: "<p>是否删除</p>",
                        onOk: () => {
                          this.deleteUser(params.row.jsdh);
                          this.getTableData(1);
                        },
                        onCancel: () => {
                          //this.$Message.info('Clicked cancel');
                        }
                      });
                    }
                  }
                },
                "删除"
              )
            ]);
          }
        }
      ],
      tableData: [],
      tableDataLength: 5,
      pageSize: 10,
      pageIndex: 1,
      totalTableData: [],
      titleKeyList: [],
      treeTitleList: []
    };
  },
  mounted() {
    this.getTableData(this.pageIndex);
    new dragDown.dragMover("showAddUser");
    if (!localStorage.getItem("token") || localStorage.getItem("token") == "") {
      this.getToken();
    }
    if (window.innerHeight < 900) {
      this.pageSize = 5;
    } else {
      this.pageSize = 10;
    }

    if (window.innerWidth < 900) {
      $("#actionStatistics").css("max-width", "620px");
    }
    if (window.innerHeight < 900) {
      $(".ivu-page").css("margin-top", "0px !important");
      $("#vehicleTable").css("height", (window.innerHeight * 450) / 980 + "px");
      $(".ivu-table-body .ivu-table-overflowY").css("height", 440 + "px");
    }
    this.getUserRole();
  },
  methods: {
    getEditUserRole: function() {
      let vm = this;
      return vm
        .$http({
          method: "post",
          url: "/gm-data/api/fweb/fwebroles/" + this.formInline.userAccount,
          headers: {
            DTS: new Date().getTime(),
            http_method: "search",
            http_mod: "S001"
          },
          dataType: "json",
          crossDomain: true, //== !(document.all),
          cache: false
        })
        .then(function(response) {
          vm.roleList = response.data.result;
          return Promise.resolve(vm.roleList);
        })
        .catch(function(response) {
          vm.$Message.error();
        });
    },
    getSelectedNode: function() {
      this.treeTitleList = [];
      let treeData = this.$refs.treeData.getCheckedNodes();
      treeData.forEach(element => {
        this.treeTitleList.push(element.title);
      });
    },
    editPerson: function() {
      this.getEditUserRole().then(resolve => {
        this.getUserRole().then(resolve => {
          $(".addRoleModal").fadeIn();
        });
      });

      this.addPersonisDisplayShowUserModal = false;
      this.isShowUserModal = true;
    },
    addPerson: function() {
      this.formInline.userAccount = "";
      this.formInline.userName = "";
      this.getAddUserRole().then(resolve => {
        $(".addRoleModal").fadeIn();
      });

      this.addPersonisDisplayShowUserModal = false;
      this.isShowUserModal = true;
    },
    getAddUserRole: function() {
      let vm = this;
      return vm
        .$http({
          method: "post",
          url: "/gm-data/api/interface/menulist",
          headers: {
            DTS: new Date().getTime(),
            http_method: "search",
            http_mod: "S001"
          },
          data: { yhzh: window.localStorage.username },
          params: { yhzh: window.localStorage.username },
          dataType: "json",
          crossDomain: true, //== !(document.all),
          cache: false
        })
        .then(function(response) {
          let result = response.data.result;
          vm.result = result;
        })
        .then(function(result) {
          vm.$http({
            method: "post",
            url: "/gm-data/api/interface/menulist",
            headers: {
              DTS: new Date().getTime(),
              http_method: "search",
              http_mod: "S001"
            },
            data: { yhzh: window.localStorage.username },
            params: { yhzh: window.localStorage.username },
            dataType: "json",
            crossDomain: true, //== !(document.all),
            cache: false
          }).then(function(response) {
            let result = response.data.result;
            vm.result = result;
            let resListssss = [];
            for (var i = 0; i < result.length; i++) {
              if (result[i].children != undefined) {
                let childList = [];
                for (var j = 0; j < result[i].children.length; j++) {
                  let child = {
                    title: result[i].children[j].cdmc,
                    expand: true
                  };
                  childList.push(child);
                }
                let hasChild = {
                  title: result[i].cdmc,
                  expand: true,
                  disableCheckbox: true,
                  children: childList
                };

                resListssss.push(hasChild);
              } else {
                var dom = {
                  title: result[i].cdmc,
                  expand: true
                };
                resListssss.push(dom);
              }
              vm.userRoleList = resListssss;
            }
          });
          return Promise.resolve(vm.userRoleList);
        })
        .catch(function(response) {
          vm.$Message.error();
        });
    },
    getUserRole: function() {
      let vm = this;
      return vm
        .$http({
          method: "post",
          url: "/gm-data/api/interface/menulist",
          headers: {
            DTS: new Date().getTime(),
            http_method: "search",
            http_mod: "S001"
          },
          data: { yhzh: window.localStorage.username },
          params: { yhzh: window.localStorage.username },
          dataType: "json",
          crossDomain: true, //== !(document.all),
          cache: false
        })
        .then(function(response) {
          let result = response.data.result;
          vm.result = result;
        })
        .then(function(result) {
          vm.$http({
            method: "post",
            url: "/gm-data/api/interface/menulist",
            headers: {
              DTS: new Date().getTime(),
              http_method: "search",
              http_mod: "S001"
            },
            data: { yhzh: window.localStorage.username },
            params: { yhzh: window.localStorage.username },
            dataType: "json",
            crossDomain: true, //== !(document.all),
            cache: false
          }).then(function(response) {
            let result = response.data.result;
            vm.result = result;
            let resListssss = [];
            for (var i = 0; i < result.length; i++) {
              if (result[i].children != undefined) {
                let childList = [];
                for (var j = 0; j < result[i].children.length; j++) {
                  let child = {
                    title: result[i].children[j].cdmc,
                    expand: true
                  };
                  if (vm.roleList.indexOf(result[i].children[j].cdmc) != -1) {
                    child.checked = true;
                  }
                  childList.push(child);
                }
                let hasChild = {
                  title: result[i].cdmc,
                  expand: true,
                  disableCheckbox: true,
                  children: childList
                };

                resListssss.push(hasChild);
              } else {
                var dom = {
                  title: result[i].cdmc,
                  expand: true
                };
                if (vm.roleList.indexOf(result[i].cdmc) != -1) {
                  dom.checked = true;
                }
                resListssss.push(dom);
              }
              vm.userRoleList = resListssss;
            }
          });
          return Promise.resolve(vm.userRoleList);
        })
        .catch(function(response) {
          vm.$Message.error();
        });
    },
    closeUserModal: function() {
      $(".addRoleModal").fadeOut();
      this.isDisplayShowUserModal = true;
      this.isShowUserModal = false;
    },
    deleteUser(userAccount) {
      let vm = this;
      let dataJson = {
        jsdh: userAccount
      };
      vm.$http({
        method: "post",
        url: "/gm-data/api/fweb/fwebroles",
        headers: {
          DTS: new Date().getTime(),
          http_method: "delete",
          http_mod: "S001"
        },
        data: dataJson,
        params: dataJson,
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      })
        .then(function(response) {
          this.getTableData(this.pageIndex);
        })
        .catch(function(response) {
          vm.$Message.error();
        });
    },
    handleSubmit(name) {
      let treeData = [];
      $(".ivu-tree")
        .find("input[type='checkbox']:not(:disabled)")
        .each(function() {
          if (
            $(this)
              .parent()
              .hasClass("ivu-checkbox-checked")
          ) {
            treeData.push(
              $(this)
                .parent()
                .parent()
                .next()
                .html()
            );
          }
        });
      let vm = this;
      if (treeData.length <= 0) {
        this.$Message.warning("请选择角色权限");
        return;
      }
      let dataJson = {
        jsdh: vm.formInline.userAccount,
        jsmc: vm.formInline.userName,
        cdmcArr: treeData.join(",")
      };
      this.$refs[name].validate(valid => {
        if (valid) {
          vm.$http({
            method: "post",
            url: "/gm-data/api/fweb/fwebroles",
            headers: {
              DTS: new Date().getTime(),
              http_method: "insert",
              http_mod: "S001"
            },
            data: dataJson,
            params: dataJson,
            dataType: "json",
            crossDomain: true, //== !(document.all),
            cache: false
          })
            .then(function(response) {
              if (response.data.message) {
                vm.$Message.warning(response.data.message);
              } else {
                vm.$Message.info("保存成功！");
              }
              vm.closeUserModal();
              vm.getTableData(1);
            })
            .catch(function(response) {
              vm.$Message.error();
            });
        } else {
          this.$Message.error("保存失败，请重新输入!");
        }
      });
    },

    handleUpdate(name) {
      let treeData = [];
      $(".ivu-tree")
        .find("input[type='checkbox']:not(:disabled)")
        .each(function() {
          if (
            $(this)
              .parent()
              .hasClass("ivu-checkbox-checked")
          ) {
            treeData.push(
              $(this)
                .parent()
                .parent()
                .next()
                .html()
            );
          }
        });

      let vm = this;
      if (treeData.length <= 0) {
        this.$Message.warning("请选择角色权限");
        return;
      }
      let dataJson = {
        jsdh: vm.formInline.userAccount,
        cdmcArr: treeData.join(","),
        jsmc: vm.formInline.userName
      };
      this.$refs[name].validate(valid => {
        if (valid) {
          vm.$http({
            method: "post",
            url: "/gm-data/api/fweb/fwebroles",
            headers: {
              DTS: new Date().getTime(),
              http_method: "update",
              http_mod: "S001"
            },
            data: dataJson,
            params: dataJson,
            dataType: "json",
            crossDomain: true, //== !(document.all),
            cache: false
          })
            .then(function(response) {
              if (response.data.message) {
                vm.$Message.warning(response.data.message);
              } else {
                vm.$Message.info("更新成功！");
              }
              vm.closeUserModal();
              vm.getTableData(1);
            })
            .catch(function(response) {
              vm.$Message.error();
            });
        } else {
          this.$Message.error("更新失败，请重新输入!");
        }
      });
    },
    handleReset(name) {
      this.$refs[name].resetFields();
    },
    changePage(index) {
      this.getTableData(index);
    },
    searchList() {
      this.getTableData(1);
    },
    getTableData(pageIndex) {
      let vm = this;
      let kssjValue = "";
      let jssjValue = "";
      let dataJson = {
        jsdh: vm.userName,
        jsmc: vm.roleName,
        pageSize: vm.pageSize,
        pageIndex: pageIndex
      };

      let dataParams = {
        jsdh: vm.userName,
        jsmc: vm.roleName,
        pageSize: vm.pageSize,
        pageIndex: pageIndex
      };

      this.$http({
        method: "post",
        url: "/gm-data/api/fweb/fwebroles",
        headers: {
          DTS: new Date().getTime(),
          http_method: "search",
          http_mod: "S001"
        },
        data: dataJson,
        params: dataParams,
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      })
        .then(function(response) {
          if (response.data.code == "10003") {
            localStorage.clear();
            axios.defaults.headers.common["USER_TOKEN"] = "";
            this.getToken();
          }
          let tableList = response.data.result.array;
          vm.tableData = tableList;
          vm.tableDataLength = response.data.result.count;
        })
        .catch(function(response) {
          console.log(response);
        });
    }
  }
};
</script>



