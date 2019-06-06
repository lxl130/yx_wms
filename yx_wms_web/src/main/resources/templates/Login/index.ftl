<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">

    <!-- Start of Baidu Transcode -->
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <meta http-equiv="Cache-Control" content="no-transform" />
    <meta name="applicable-device" content="pc">

    <title>登录</title>
    <script src="http://staticyx.lnetco.com/global/md5.min.js"></script>
    <script src="http://staticyx.lnetco.com/global/jquery.min.3.1.1.js"></script>
    <script src="http://staticyx.lnetco.com/global/vue.min.2.3.4.js"></script>
    <script src="http://staticyx.lnetco.com/element-ui@1.3.7/lib/index.js"></script>
    <script src="http://staticyx.lnetco.com/global/store.min.js" async="async"></script>
    <link href="http://staticyx.lnetco.com/element-ui@1.3.7/lib/theme-default/index.css" rel="stylesheet" />
    <link href="http://staticyx.lnetco.com/css/stars.css" rel="stylesheet" />
    <link href="http://staticyx.lnetco.com/css/login.css" rel="stylesheet" />
</head>

<body>
<div id="app" class="prod-fallback-edoc" style="position: fixed;top: 0;left: 0;right: 0;bottom: 0;">
    <div id="stars"></div>
    <div id="stars2"></div>
    <div class="container">
        <div class="full-content-center">
            <p class="text-center">
                <a href="#"><img id="logo_img" src="http://staticyx.lnetco.com/imgs/logo2.png" alt="Logo" style="height: 36px;"></a>
            </p>
            <div class="login-wrap">
                <div class="login-block el-input ">
                    <img src="http://staticyx.lnetco.com/imgs/default-user.png" class="img-circle not-logged-avatar">
                    <div class="form-group login-input">
                        <i class="ivu-icon ivu-icon-person overlay"></i>
                        <input placeholder="手机号码" v-model="logInfo.phone" class="form-control text-input el-input__inner" type="text">
                    </div>
                    <div class="form-group login-input">
                        <i class="ivu-icon ivu-icon-eye-disabled overlay"></i>
                        <input v-on:keyup.enter="sbm" placeholder="密码" v-model="logInfo.passWord" class="form-control text-input el-input__inner" type="password" v-on:keyup.enter="sbm()">
                    </div>
                    <div id="loginBtnBox" style="position: relative;margin-bottom: 20px;">
                        <button v-on:click="sbm()" style="width: 100%" type="button" class="el-button el-button--primary  ">登录</button>

                    </div>
                    <div id="QR_code" style="overflow: hidden;background: rgb(255, 255, 255);">
                        <div style="width: 49%;float: left;">
                            <img src="http://staticyx.lnetco.com/apk/driver.jpg" style="width: 88%;margin: 12px;">
                            <div style='font-family:"微软雅黑";color: #555;text-align: center;padding-bottom: 12px;font-size: 16px;font-weight: 600;'>司机</div>
                        </div>
                        <div style="width: 49%;float: left;">
                            <img src="http://staticyx.lnetco.com/apk/moveandoperate.jpg" style="width: 88%;margin: 12px;">
                            <div style='font-family:"微软雅黑";color: #555;text-align: center;padding-bottom: 12px;font-size: 16px;font-weight: 600;'>运作</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var app;
    $(function () {
        app = new Vue({
            el: "#app",
            data: {
                logInfo: {
                    phone: "",
                    passWord: "",
                    key: md5([navigator.appName, navigator.userAgent, navigator.platform].join("&&"))
                },
                QRcodeApk: false
            },
            methods: {
                sbm() {
                    if (this.logInfo.phone.length <= 0) {
                        Vue.prototype.$message({
                            message: '账号不能为空',
                            type: 'warning'
                        });
                        return;
                    }
                    if (this.logInfo.passWord.length <= 0) {
                        Vue.prototype.$message({
                            message: '密码不能为空',
                            type: 'warning'
                        }); return;
                    }
                    if (this.logInfo.passWord.length < 6) {
                        Vue.prototype.$message({
                            message: '密码长度不能小于六位',
                            type: 'warning'
                        }); return;
                    }
                    $.ajax({
                        type: "POST",
                        url: "/Login/OnLogin",
                        dataType: "text",       //下载格式
                        contentType: "application/x-www-form-urlencoded",
                        data: this.logInfo,
                        success: function (result) {
                            if (result == "") {
                                window.location.href = "/Home/Index";
                            } else {
                                YX.Alert.error(result);//错误信息弹出
                            }
                        }
                    });
                }
            }
        });
        function rd(begin, end) { return Math.floor(Math.random() * (end - begin)) + begin; }
        $(".prod-fallback-edoc").css("background-image", "url('http://staticyx.lnetco.com/imgs/bg/" + rd(1, 25) + ".jpg')");
        //app.QRcodeApk = true;
        //$("#imgpshr123").css("opacity", "1");
        ({
            "xrds.lnetco.com": function () {
                document.getElementById("QR_code").innerHTML = `<div style="width: 100%;text-align: center;"><img src="http://staticyxv2.lnetco.com/apk/ams/1540893809.png" style="width: 45%;margin: 12px;"> <div style="font-family: 微软雅黑; color: rgb(85, 85, 85); text-align: center; padding-bottom: 12px; font-size: 16px; font-weight: 600;">AMS</div></div>`
            },
            "www.lnetco.com": function () {

            }
        })[document.domain]();
    });
</script>
<script src="http://staticyxv2.lnetco.com/js/logos.js"></script>
</body>

</html>