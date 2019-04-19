<template>
  <div class="wrap d-flex flex-column">
    <div class="wrap-cont d-flex">
      <div class="box text-center">
        <h1
          class="text-white"
          style="margin-bottom: 14px;"
        >区域智慧审方平台（RIPAP）</h1>
        <div class="box-cont">
          <el-card class="box-card">
            <p
              class="text-white"
              style="font-size: 22px; margin-bottom: 20px;"
            >登&nbsp;&nbsp;录</p>
            <el-form
              :model="form"
              :rules="rules"
              ref="form"
              size="medium"
            >
              <el-form-item
                class="form-item"
                prop="name"
              >
                <img
                  src="../assets/images/user.png"
                  alt=""
                >
                <el-input
                  v-model="form.name"
                  placeholder="请输入工号"
                  clearable
                ></el-input>
              </el-form-item>
              <div class="space"></div>
              <el-form-item
                label=""
                class="form-item"
                prop="password"
              >
                <img
                  src="../assets/images/password.png"
                  alt=""
                >
                <el-input
                  v-model="form.password"
                  placeholder="请输入密码"
                  clearable
                  type="password"
                ></el-input>
              </el-form-item>
              <!-- <p
                class="pointer text-right"
                @click="registerVisible = true"
                style="width:100%; color:#001268; margin: 10px 0;"
              >注册</p> -->
              <div class="space"></div>
              <div class="space"></div>
              <el-form-item>
                <el-button
                  type="primary"
                  @click="submitLogin('form')"
                  style="width:100%; "
                  :loading="loading"
                >{{buttonText}}</el-button>
              </el-form-item>
            </el-form>
          </el-card>
        </div>
      </div>
    </div>
    <div class="footer f5 text-center">
      <!-- <img src="../assets/images/footer.png" /> -->
      技术支持：苏州惠邦医疗科技有限公司
    </div>

    <el-dialog
      title="注册"
      :visible.sync="registerVisible"
      width="350px"
      center
    >
      <el-form
        size="medium"
        label-width="100px"
        :model="formRegister"
      >
        <el-form-item label="用户名：">
          <el-input v-model="formRegister.name"></el-input>
        </el-form-item>
        <div class="space"></div>
        <el-form-item label="密 码：">
          <el-input v-model="formRegister.password"></el-input>
        </el-form-item>
        <div class="space"></div>
        <el-form-item label="重复密码：">
          <el-input v-model="formRegister.comPas"></el-input>
        </el-form-item>
      </el-form>
      <span
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          @click="registerVisible = false"
          size="small"
        >取 消</el-button>
        <el-button
          size="small"
          type="primary"
          @click="handleRegister"
        >提 交</el-button>
      </span>
    </el-dialog>
  </div>

</template>

<script>
import { Login } from '@/api/auth'

export default {
  data() {
    return {
      registerVisible: false,
      loading: false,
      buttonText: '立即登录',
      form: {
        name: '',
        password: ''
      },
      rules: {
        name: [{
          required: true,
          message: '请输入工号',
          trigger: 'blur'
        }],
        password: [{
          required: true,
          message: '请输入密码',
          trigger: 'blur'
        }]
      },
      formRegister: {
        name: '',
        password: '',
        comPas: ''
      }
    }
  },

  methods: {
    handleRegister() {
      // todo 注册事件
      // if (success)
      this.$alert('恭喜！注册成功！请等待管理员审核。', '提示', {
        confirmButtonText: '确定'
      })
    },
    submitLogin(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.loading = true
          this.buttonText = '正在登录...'
          this.handleLogin()
        } else {
          console.log('error submit!!')
        }
      })
    },
    async handleLogin() {
      const res = await Login(this.form)
      this.loading = false
      this.buttonText = '立即登录'
      if (res.code === 0) {
        this.$toast('登录成功！')
        localStorage.setItem('user', JSON.stringify(this.form))
        localStorage.setItem('token', res.result.token)
        this.$router.push({ path: this.$urls.HOME })
      } else {
        this.$toast(res.msg)
      }
    }
  }
}

</script>

<style lang="less" scoped>
.wrap {
  width: 100%;
  height: 100%;

  &-cont {
    flex: 1;
    align-items: center;
    background: url(../assets/images/bg.jpg) no-repeat center;
    background-size: 100% 100%;
  }

  .footer {
    width: 100%;
    height: 50px;
    background: #313131;
    color: #fff;
    line-height: 50px;
    img {
      display: block;
      margin: auto;
    }
  }
}
.box {
  flex: 1;

  &-cont {
    background: rgba(255, 255, 255, 0.3);
  }

  &-card {
    margin: auto;
    width: 360px;
    background: none;
    border: none;
    box-shadow: none;

    .form-item {
      position: relative;
      img {
        position: absolute;
        top: 6px;
        left: 10px;
        z-index: 1;
        width: 20px;
        padding-right: 6px;
        border-right: 1px solid #dcdcdc;
      }
    }
  }
}
</style>

<style>
.form-item .el-input__inner {
  padding-left: 46px !important;
}
</style>
