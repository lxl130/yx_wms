<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>新易泰供应链</title>
    <link href="https://cdn.bootcss.com/material-design-icons/3.0.1/iconfont/material-icons.min.css" rel="stylesheet" />
    <link href="http://staticyxv2.lnetco.com/assets/kendo@2018.2.516/styles/kendo.office365.min.css" rel="stylesheet" />
    <link href="http://staticyxv2.lnetco.com/assets/kendo@2018.2.516/styles/kendo.common-office365.min.css" rel="stylesheet" />
    <link href="http://staticyxv2.lnetco.com/assets/element-ui@2.4.2/lib/theme-chalk/index.css" rel="stylesheet" />
    <link href="http://staticyxv2.lnetco.com/css/common.css" rel="stylesheet">
    <link href="http://staticyxv2.lnetco.com/css/element.custom.css" rel="stylesheet" />
    <link href="http://staticyxv2.lnetco.com/layouts/horizontal.v2/main.css" rel="stylesheet" />
    <link href="http://staticyxv2.lnetco.com/css/loading_xbox.css" rel="stylesheet" />
    <style>
        .k-menu-scroll-wrapper .k-animation-container {}
        .k-menu-scroll-wrapper,
        .k-menu.k-header,
        .k-menu-scroll-wrapper.vertical .k-menu-group {
            border: none;
        }
        .k-menu-scroll-wrapper .k-menu-group .k-item > .k-link {
            padding: .786em 1.071em .786em 1.714em;
        }
        .k-panelbar > .k-item > .k-link.k-header {
            font-size: 1em;
            line-height: 3.5em;
        }

        .k-item-space {
            width: 5px;
            height: 2px;
            display: inline-block;
        }
        #tabstrip .k-item-space{
            display:none;
        }
        .topbar-top .topbar-user {
            width: auto;
            min-width: 90px;
        }

        /*.sidebar div.scrollbar_cursor {
            width: 10px;
            top: 0;
            left: 0;
            bottom: 0;
            position: absolute;
            cursor: pointer;
        }*/

        #menuList {
            overflow: -moz-hidden-unscrollable;
        }

        #menuList::-webkit-scrollbar {
            display: none;
        }

        #tab-0 {
            padding-left: 40px;
        }
    </style>
</head>

<body>
<div id="topbarTheme" class="is-hide"></div>
<header class="topbar">
    <!--顶部  -->
    <div class="topbar-top">
        <a class="topbar-logo" href="#">
            <img id="logo_img" src="http://staticyxv2.lnetco.com/layouts/horizontal.v2/logo2.png" />
        </a>
        <div class="topbar-menu">
            <div class="topbar-menu-link">
                <a href="javascript:void(0)" onclick="$('#tabHomeLabel')[0].click()">
                    <i class="material-icons">home</i>首页
                </a>
            </div>
            <div class="topbar-menu-link">
                <a href="javascript:void(0)" @*data-onclick="addTabByAjax('./系统通知.html')"*@ data-msgcunt="(0)">
                    <i class="material-icons">notifications</i>通知
                </a>
            </div>
            <div id="topbar-login-btn" class="topbar-menu-link topbar-user  hover-dropdown">
                <div class=" hover-dropdown">
                    <a href="javascript:void(0)" style="width: 100%;display: block;">
                        <i class="material-icons" style="position: relative;top: 8px;padding-left: 4px;">account_circle</i>
                        @ViewData["realName"]
                        <i class="material-icons down-icon">keyboard_arrow_down</i>
                        <i class="material-icons up-icon">keyboard_arrow_up</i>
                    </a>
                    <ul class="hover-dropdown-list">
                        <li>
                            <a onclick="settingPage.loadDataPage()">
                                <i class="material-icons">art_track</i>个人信息
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="addTab(this)" data-id="help" data-href="http://staticyx.lnetco.com/wmshelp1/">
                                <i class="material-icons">live_help</i>帮助文档
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="addTabByAjax( 'http://staticyxv2.lnetco.com/layouts/horizontal.v2/theme.html')">
                                <i class="material-icons">brush</i>主题样式
                            </a>
                        </li>
                        <li>
                            <a href="http://erpv2.yx.com/Home/horizontal">
                                <i class="material-icons">view_carousel</i>旧版布局
                            </a>
                        </li>
                        <li>
                            <a href="javascript:void(0)" data-href="./apk.html" data-id="apk" onclick="addTab(this)">
                                <i class="material-icons">android</i>下载安卓版
                            </a>
                        </li>
                        <li>
                            <div class="clean-line"></div>
                        </li>
                        <li>
                            <a href="javascript:void(0)" onclick="onLogout()">
                                <i class="material-icons">exit_to_app</i>退出系统
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <input type="checkbox" id="switchNav" hidden class="is-hide" />
    <a class="topbar-nav-icon checked" href="javascript:void(0)" onclick="$('#switchNav')[0].click()">
        <i class="material-icons">dehaze</i>
    </a>
    <div class="topbar-all-nav-dropdown">
        <div style="margin: 12px 15px;height: 1px;"></div>
        <div id="menuList" style="height: 82%;">
            <!-- 菜单 -->
        </div>
        <div class="clean-line"></div>
        <div style="position: absolute;padding: 10px 0px;left: 0;right: 0;bottom: 0;text-align: center;">新易泰物流@2019</div>
    </div>
    <!-- <div class="topbar-bottom">
    </div> -->
</header>
<!--选项卡  -->
<div id="tabstrip">
    <ul>
        <li class="k-state-active k-state-home" id="tabHomeLabel">
            <i class="material-icons">home</i>主页
        </li>
    </ul>
    <div>
        <iframe src=""></iframe>
    </div>
</div>
<!--选项卡右键菜单  -->
<ul id="rightClickMenu">
    <li class="r-close">
        关闭
    </li>
    <li class="k-separator"></li>
    <li class="r-openNewTab">
        在新页面打开
    </li>
    <li class="k-separator"></li>
    <li class="r-reload">
        刷新
    </li>
</ul>
<!--  -->
<!--加载动画-->
<div id="main_loading" class="main_loading" style="background-color: #f5f5f5;position: fixed;top: 0;left: 0;right: 0;bottom: 0;z-index: 10000;">
    <div class="loading_xbox center-display">
        <div>加载中</div>
        <div>&nbsp;</div>
        <div class="loading_xbox_xs">
            <div class="pace_activity"></div>
        </div>
    </div>
</div>
<script src="http://staticyxv2.lnetco.com/assets/jquery@3.3.1/jquery.min.js"></script>
<script src="http://staticyxv2.lnetco.com/assets/kendo@2018.2.516/js/kendo.all.min.js"></script>
<script src="http://staticyxv2.lnetco.com/assets/kendo@2018.2.516/js/messages/kendo.messages.zh-CN.min.js"></script>
<script src="http://staticyxv2.lnetco.com/assets/vue@2.5.16/dist/vue.min.js"></script>
<script src="http://staticyxv2.lnetco.com/assets/element-ui@2.4.2/lib/index.js"></script>
<script>
    var menuBar = [];
    var themeUrl = 'http://staticyxv2.lnetco.com/layouts/horizontal.v2/theme/';
</script>
<script src="http://staticyxv2.lnetco.com/layouts/horizontal.v2/layout.js"></script>
<script src="http://staticyxv2.lnetco.com/js/logos.js"></script>
<script>
    //注销
    var onLogout = function () {
        Vue.prototype.$confirm('当前登录用户将被注销,是否继续?', '注销', {
            confirmButtonText: '确定',
            cancelButtonText: '取消',
            type: 'warning'
        }).then(() => {
            Vue.prototype.$message({
                type: 'success',
                message: '注销成功'
            });
            setTimeout(function () {
                window.location.href = "/login";
            }, 1200);
        }).catch(() => {
            Vue.prototype.$message({
                type: 'info',
                message: '已取消注销操作'
            });
        });
    };

    // $(document).ready(function () {
    //     $(".k-content").height(getMainH());
    // });
    // });

</script>
<!--设置页面-->
</body>
</html>