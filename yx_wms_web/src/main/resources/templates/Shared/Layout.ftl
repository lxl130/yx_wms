<#macro layout>
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

        <title>WMS-雨歇</title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="http://staticyxv2.lnetco.com/css/loading_xbox.css" rel="stylesheet" />
        <link href="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/styles/kendo.common-bootstrap.min.css" rel="stylesheet" />
        <link href="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/styles/kendo.material.min.css" rel="stylesheet" />
        <link href="http://staticyxv2.lnetco.com/assets/element-ui@2.6.1/lib/theme-chalk/index.css" rel="stylesheet" />
        <link href="http://staticyxv2.lnetco.com/assets/material-design-icons@3.0.1/iconfont/material-icons.min.css" rel="stylesheet" />
        <link href="http://staticyxv2.lnetco.com/css/common.css" rel="stylesheet">
        <link href="http://staticyxv2.lnetco.com/css/element.custom.css" rel="stylesheet" />
        <link href="http://staticyxv2.lnetco.com/css/kendo.custom.css" rel="stylesheet" />
        <script src="http://staticyxv2.lnetco.com/assets/jquery@3.3.1/jquery.min.js"></script>

        <script>
            const Production = (document.domain.indexOf("lnetco.com") >= 0);
        </script>
        <!-- 公共样式 -->
        <style>
            div.nav-bar{padding: .5em;background-color: #EEEEEE;}
            div.nav-bar div.el-select{min-width: 100px}
            div.el-dialog form div.el-table{width:100%;overflow:auto;}
            div.el-dialog form div.el-select{width:100%;}

            div.button-box{border-bottom:solid 1px #fff;padding-bottom: 5px;margin-bottom: 3px;}
            div.button-box div.el-col-4{text-align: right;}

            div.search-box{padding: 5px 1% 0 0;}

            div.search-box div.el-input-box div.el-input{width: 90%;}
            div.search-box div.el-input-box span.el-cascader{width: 100%;}
            div.search-box div.el-input-box div.el-select{width: 90%;}
            div.search-box div.el-input-box div.el-select div.el-input{width: 100%;}

            div.search-box div.el-col-4 div.el-input-box div.el-input{width: 90%;}
            div.search-box div.el-col-4 div.el-input-box span.el-cascader{width: 100%;}
            div.search-box div.el-col-4 div.el-input-box div.el-select{width: 90%;}
            div.search-box div.el-col-4 div.el-input-box div.el-select div.el-input{width: 100%;}

            div.search-box div.el-col-8 div.el-input-group{width: 96%;}
        </style>
    </head>
    <body ondrop="return false" ondragover="return false">
    <!--加载动画-->
    <!--div id="main_loading" class="main_loading" style="background-color: #f5f5f5;position: fixed;top: 0;left: 0;right: 0;bottom: 0;z-index: 99999;">
        <div class="loading_xbox center-display">
            <div>加载中</div>
            <div>&nbsp;</div>
            <div class="loading_xbox_xs">
                <div class="pace_activity"></div>
            </div>
        </div>
    </div-->
    <!--  -->
    <#nested />
    <script src="http://staticyxv2.lnetco.com/assets/vue@2.6.6/dist/vue.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/js/kendo.ui.core.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/js/kendo.data.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/js/kendo.columnsorter.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/js/kendo.columnmenu.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/js/kendo.groupable.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/js/kendo.pager.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/js/kendo.selectable.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/js/kendo.sortable.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/js/kendo.reorderable.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/js/kendo.resizable.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/js/kendo.progressbar.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/js/kendo.grid.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/js/kendo.dom.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/js/kendo.columnsorter.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/js/kendo.treeview.draganddrop.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/js/kendo.treelist.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/kendo@2019.1.220/js/messages/kendo.messages.zh-CN.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/xlsx@0.11.18/dist/xlsx.core.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/element-ui@2.6.1/lib/index.js"></script>
    <script src="http://staticyxv2.lnetco.com/assets/moment@2.22.2/min/moment.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/js/kendo.utils.js"></script>
    <script src="http://staticyxv2.lnetco.com/js/xlsx.utils.js"></script>
    <script src="http://staticyxv2.lnetco.com/js/jquery.custom.js"></script>
    <script src="http://staticyxv2.lnetco.com/js/lutils.min.js"></script>
    <script src="http://staticyxv2.lnetco.com/js/element.custom.js"></script>
    <script type="application/javascript">
        YX.appSearchFormat = function(search) {
            let o = YX.Kendo.sformat(search);
            let obj = new Object();
            for (let k in o) {
                if(typeof (o[k]) == "string"){
                    obj[k] = o[k];
                }
                else {
                    if(Object.prototype.toString.call(o[k]) == "[object Array]") {
                        let a = Array.from(o[k]);
                        if(a.length == 2){
                            obj[k+"0"] = a[0];
                            obj[k+"1"] = a[1];
                        }
                    }
                }
            }
            return obj;
        };
        YX.onClear = function (o) {
            for (let k in o) {
                if (k.indexOf("p_") >= 0) {
                    if (o[k].value) {
                        o[k].value = "";
                    }
                    continue;
                }

                if(Object.prototype.toString.call(o[k]) == "[object Array]") {
                    o[k] = [];
                }
                else
                    o[k] = "";
            }
        }
    </script>
</body>
</html>
</#macro>