<style>
#login {
  width: 100%;
  height: 100%;
  background-image: url(/static/image/25.png);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  position: fixed;
  background-attachment: fixed;
  padding: 20px 50px !important;
}
.back {
  width: 947px;
  height: 403px;
  margin: -20px auto;
  background-image: url(/static/image/dl.png);
  min-height: 400px;
  position: relative;
  top: 130px;
  padding: 65px;
  background-position: 50% 50%;
  padding-top: 100px;
}
.foot_p {
  font-size: 13px;
  color: white;
  opacity: 0.4;
  line-height: 50px;
  margin: 480px 0px 8px;
  margin-top: 18%;
}
input {
  padding-left: 40px !important;
  height: 34px !important;
}
.login_btn {
  background-color: rgb(66, 139, 202);
  width: 300px;
  height: 34px;
  margin-top: 40px;
  color: white;
  border-radius: 2px;
}
.login_btn:hover {
  background-color: rgb(27, 106, 170);
  cursor: pointer;
}
.icon-user {
  background-image: url(/static/image/user.png);
  display: inline-block;
  height: 16px;
  width: 16px;
  margin-right: 8px;
}
.icon-lock {
  background-image: url(/static/image/pwd.png);
  display: inline-block;
  height: 17px;
  width: 16px;
  margin-right: 8px;
}
.spl {
  width: 1px;
  background-color: rgb(153, 153, 153);
  height: 17px;
  display: inline-block;
}
.icon_mov {
  position: absolute;
  top: 6px;
  left: 8px;
  z-index: 1000;
}

#login .ivu-input {
  padding-left: 40px !important;
}
</style>

<template>
  <div id="login">
    <div class="back">
      <Form
        ref="loginForm"
        :model="loginForm"
        :rules="ruleInline"
      >
        <div style="width:300px;height:200px;top:0px;margin:60px auto;font-size:14px;">
          <label style="display:block;position:relative;">
            <span>
              <Form-item prop="user">
                <label class="icon_mov">
                  <i class="icon-user"></i>
                  <span class="spl"></span>
                </label>
                <i-input
                  type="text"
                  v-model="loginForm.user"
                  placeholder="用户账户"
                />
              </Form-item>
            </span>
          </label>
          <label style="display:block;position:relative;">
            <span>
              <Form-item prop="password">
                <label class="icon_mov">
                  <i class="icon-lock"></i>
                  <span class="spl"></span>
                </label>
                <i-input
                  type="password"
                  v-model="loginForm.password"
                  placeholder="用户密码"
                  @on-enter="handleSubmit('loginForm')"
                  @on-keypress="capsLockEnter($event)"
                />
              </Form-item>
            </span>
          </label>
          <Form-item>
            <button
              type="button"
              class="login_btn"
              @click="handleSubmit('loginForm')"
            >登&nbsp;&nbsp;&nbsp;&nbsp;录</button>
          </Form-item>
        </div>
      </Form>
    </div>
    <p class="foot_p">{{copyright}}</p>
  </div>
</template>
<script>
import $ from "jquery";
import axios from "axios";
export default {
  data() {
    return {
      copyright:
        "@2017-2020苏州高迈智能科技有限公司版权所有 苏州网安备32059002001790号",
      loginForm: {
        user: "",
        password: ""
      },
      ruleInline: {
        user: [{ required: true, message: "请填写用户名", trigger: "blur" }],
        password: [
          { required: true, message: "请填写密码", trigger: "blur" },
          {
            type: "string",
            min: 6,
            message: "密码长度不能小于6位",
            trigger: "blur"
          }
        ]
      }
    };
  },
  mounted() {
    $(".back").css("height", (document.body.clientHeight * 403) / 980 + "px");
    $(".back").css("width", (window.innerWidth * 947) / 1920 + "px");
    if (window.innerHeight < 900) {
      $(".foot_p").css("margin-top", "18%");
    } else {
      $(".foot_p").css("margin-top", "47%");
    }

    if (
      localStorage.getItem("token") &&
      localStorage.getItem("token") != "" &&
      localStorage.getItem("token") != null
    ) {
      this.$router.push("/homePage/controlCenter/mapDistribution");
    }
    // $(".foot_p").css("margin-top", window.innerHeight * 500 / 980 + "px");
    // window.onresize = function() {

    // };
    // $(".foot_p").css("margin-top", window.innerHeight * 360 / 980 + "px");
  },
  methods: {
    capsLockEnter: function(e) {
      var valueCapsLock = e.keyCode ? e.keyCode : e.which; // 按键
      if (valueCapsLock >= 65 && valueCapsLock <= 90) {
        // 输入了大写字母，并且shift键没有按住，说明Caps Lock打开
        this.$Message.warning("大写键盘已锁定！");
        return false;
      }
    },
    handleSubmit(name) {
      let vm = this;
      this.$refs[name].validate(valid => {
        if (valid) {
          vm.$http({
            method: "post",
            url:
              "/gm-data/api/frmAdminusers/" +
              vm.loginForm.user +
              "," +
              vm.loginForm.password +
              "/login",
            // data: {
            //   yhzh: this.loginForm.user,
            //   yhma: this.loginForm.password
            // },
            headers: {
              "Content-Type": "application/json;charset=utf-8",
              DTS: new Date().getTime(),
              http_method: "search",
              http_mod: "S001"
            },
            dataType: "json",
            crossDomain: true, //== !(document.all),
            cache: false
          })
            .then(function(response) {
              if (response.data.code == 1) {
                vm.$Message.error(response.data.message);
              } else {
                // vm.$store.state.username = vm.loginForm.user;
                // vm.$store.state.password = vm.loginForm.password;
                window.localStorage.setItem("password", vm.loginForm.password);
                window.localStorage.setItem("username", vm.loginForm.user);
                localStorage.setItem("token", response.data.result.USER_TOKEN);
                axios.defaults.headers.common[
                  "USER_TOKEN"
                ] = localStorage.getItem("token");
                vm.$router.push({
                  path: "/homePage/controlCenter/mapDistribution"
                });
              }
            })
            .catch(function(response) {
              //vm.$Message.error(response.message);
            });
        } else {
          this.$Message.error("表单验证失败!");
        }
      });
    },
    getToken: function() {
      return this.$http({
        method: "post",
        url:
          "/gm-data/api/frmAdminusers/" +
          vm.loginForm.user +
          "," +
          vm.loginForm.password +
          "/login",
        headers: {
          DTS: new Date().getTime(),
          http_method: "search"
        },
        dataType: "json",
        crossDomain: true, //== !(document.all),
        cache: false
      })
        .then(function(response) {
          localStorage.setItem("token", response.data.result.USER_TOKEN);
          axios.defaults.headers.common["USER_TOKEN"] = localStorage.getItem(
            "token"
          );
          return Promise.resolve(response);
        })
        .catch(function(response) {
          return Promise.resolve(response);
        });
    }
  }
};
</script>
