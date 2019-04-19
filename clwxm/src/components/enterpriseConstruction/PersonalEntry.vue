<style lang="less" scoped>
.labelName {
  font-size: 14px;
  line-height: 50px;
  width: 80px;
  text-align: center;
  display: flex;
  align-items: center;
  margin-left: 20px;
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
  // margin-left: -175px;
}

.title {
  line-height: 24px;
  width: 80px;
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

.addUserModal {
  z-index: 19891016;
  width: 650px;
  height: 420px;
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

.displayShowUserModal {
  display: none;
}

.showUserModalBlock {
  display: block;
}

.attibute {
  width: 100px;
}

#personTable {
  min-width: 580px;
}

.searchItem {
  margin-left: -10%;
  margin-top: -10px;
}
</style>

<style lang="less">
.ivu-form-item-content {
  display: flex;
}

.ivu-form-item {
  margin-bottom: 40px;
}

.ivu-input {
  padding-left: 10px !important;
}
</style>

<template>
  <div style="background: #282c35;color: #fff;height:calc(100vh - 100px);position: absolute;top: 80px;">
    <div style="background: #18191D;max-height: calc(100vh- 120px);margin-left: 20px; margin-top: 20px;width:calc(100vw - 320px);height:97%;">
      <h5 class="title">人员录入</h5>
      <div>
        <div style="display: flex;min-width: 600px;flex-direction: row;align-items: center;justify-content: space-around;position: relative;margin-top: 45px;margin-bottom: 30px;">
          <div class="searchItem">
            <div class="labelName">用户账号：</div>
            <Input
              v-model="userAccount"
              placeholder="请输入用户账号"
              class="plateNum"
            />
            <div class="labelName">用户姓名：</div>
            <Input
              v-model="userName"
              placeholder="请输入用户姓名"
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
      </div>
      <div style="padding-left: 10px;padding-right: 10px;">
        <Table
          :columns="columns"
          :data="tableData"
          id="personTable"
        ></Table>
        <Page
          :total="tableDataLength"
          :current="pageIndex"
          @on-change="changePage"
          :page-size="pageSize"
        ></Page>
      </div>
    </div>
    <div
      class="addUserModal"
      id="addUserModal"
      :class="{displayShowUserModal:isDisplayShowUserModal,showUserModalBlock:isShowUserModal}"
    >
      <div
        class="showLineTitle"
        id="showAddUser"
      >
        <div>添加人员</div>
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
            label="用户账号："
          >
            <Input
              type="text"
              v-model="formInline.userAccount"
              onkeydown="if(event.keyCode==32) return false"
            ></Input>
          </FormItem>
          <FormItem
            prop="userName"
            label="用户姓名："
          >
            <Input
              type="text"
              v-model="formInline.userName"
              onkeydown="if(event.keyCode==32) return false"
            ></Input>
          </FormItem>
        </div>
        <div>
          <FormItem
            prop="phone"
            label="联系方式："
          >
            <Input
              type="text"
              v-model="formInline.phone"
              onkeydown="if(event.keyCode==32) return false"
            ></Input>
          </FormItem>
          <FormItem
            prop="mail"
            label="常用邮箱："
          >
            <Input
              type="text"
              v-model="formInline.mail"
              onkeydown="if(event.keyCode==32) return false"
            ></Input>
          </FormItem>
        </div>
        <div>
          <FormItem
            prop="password"
            label="密码："
            ref="password"
            style="margin-left: 25px;"
          >
            <Input
              type="password"
              v-model="formInline.password"
              placeholder="密码长度至少6位"
              onkeydown="if(event.keyCode==32) return false"
            ></Input>
          </FormItem>
          <FormItem
            prop="cfmpassword"
            label="确认密码："
          >
            <Input
              type="password"
              v-model="formInline.cfmpassword"
              placeholder="密码长度至少6位"
              onkeydown="if(event.keyCode==32) return false"
            ></Input>
          </FormItem>
        </div>
        <FormItem>
          <div style="margin-top:30px;">
            <Button
              type="primary"
              @click="handleReset('formInline')"
              style="margin-right:20px;"
            >重置</Button>
            <Button
              type="primary"
              @click="handleSubmit('formInline')"
            >保存</Button>
          </div>
        </FormItem>
      </Form>
    </div>
  </div>
</template>
<script>
import dragDown from "../../../static/js/dragDown.js";

export default {
  name: "personalEntry",
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
      isDisplayShowUserModal: true,
      isShowUserModal: false,
      plateNum: "",
      userAccount: "",
      userName: "",
      formInline: {
        userAccount: "",
        userName: "",
        phone: "",
        mail: "",
        password: ""
      },

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
        ],
        phone: [
          {
            required: true,
            message: "请输入联系方式",
            trigger: "blur"
          },
          { validator: validatePhone, trigger: "blur" }
        ],
        mail: [
          {
            required: true,
            message: "请输入邮箱",
            trigger: "blur"
          },
          { type: "email", message: "邮箱格式错误", trigger: "blur" }
        ],
        password: [
          {
            required: true,
            message: "请输入密码",
            trigger: "blur"
          },
          {
            type: "string",
            min: 6,
            message: "密码长度不少于6位",
            trigger: "blur"
          }
        ],
        cfmpassword: [
          {
            required: true,
            message: "请确认密码",
            trigger: "blur"
          },
          {
            type: "string",
            min: 6,
            message: "密码长度不少于6位",
            trigger: "blur"
          },
          { validator: validateCfmPhone, trigger: "blur" }
        ]
      },
      columns: [
        {
          title: "用户账号",
          key: "yhzh",
          // fixed: "left",
          minWidth: 100,
          align: "center"
        },
        {
          title: "用户姓名",
          key: "yhxm",
          minWidth: 100,
          align: "center"
        },

        {
          title: "联系方式",
          key: "lxfs",
          minWidth: 100,
          align: "center"
        },
        {
          title: "邮箱",
          key: "yhyx",
          minWidth: 100,
          align: "center"
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
                    type: "error",
                    size: "small"
                  },
                  on: {
                    click: () => {
                      this.$Modal.confirm({
                        title: "请确认",
                        content: "<p>是否删除</p>",
                        onOk: () => {
                          this.deleteUser(params.row.yhzh);
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
      tableDataLength: 10,
      pageSize: 10,
      pageIndex: 1,
      totalTableData: []
    };
  },
  mounted() {
    this.getTableData(this.pageIndex);
    if (!localStorage.getItem("token") || localStorage.getItem("token") == "") {
      // this.$router.push({ path: "/" });
      // parent.location.href = "/";
      this.getToken();
    }
    new dragDown.dragMover("showAddUser");
    new dragDown.dragMover("showLineTitle");
    if (window.innerHeight < 900) {
      this.pageSize = 5;
    } else {
      this.pageSize = 10;
    }
    if (window.innerHeight < 900) {
      $("#personTable").css("height", (window.innerHeight * 450) / 980 + "px");
      $(".ivu-table-body").css(
        "height",
        $("#personTable").height() - 40 + "px"
      );
      // $(".ivu-table-body .ivu-table-overflowY").css("height", 440 + "px");
    }
  },
  methods: {
    addPerson: function() {
      $(".addUserModal").fadeIn();
      this.isDisplayShowUserModal = false;
      this.isShowUserModal = true;
    },
    closeUserModal: function() {
      $(".addUserModal").fadeOut();
      this.isDisplayShowUserModal = true;
      this.isShowUserModal = false;
    },
    deleteUser(userAccount) {
      let vm = this;
      let dataJson = {
        yhzh: userAccount
      };
      vm.$http({
        method: "post",
        url: "/gm-data/api/interface/fwebuser",
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
      let vm = this;
      let dataJson = {
        yhzh: vm.formInline.userAccount,
        yhxm: vm.formInline.userName,
        lxfs: vm.formInline.phone,
        yhyx: vm.formInline.mail,
        yhma: vm.formInline.password
      };
      this.$refs[name].validate(valid => {
        if (valid) {
          vm.$http({
            method: "post",
            url: "/gm-data/api/interface/fwebuser",
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
      let dataJson = {
        pageIndex: pageIndex,
        yhzh: vm.userAccount,
        yhxm: vm.userName
      };
      let dataParams = {
        pageIndex: pageIndex,
        yhzh: vm.userAccount,
        yhxm: vm.userName
      };

      this.$http({
        method: "post",
        url: "/gm-data/api/interface/fwebuser",
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
          vm.tableData = response.data.result.array;
          vm.tableDataLength = response.data.result.count;
        })
        .catch(function(response) {
          console.log(response);
        });
    }
  }
};
</script>



