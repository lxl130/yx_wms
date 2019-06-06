var app, grid, gridConf;
const alertQuerys = ["disable", "enable", "delete"];
var isSupplier = false;
$(document).ready(function () {
    app = new Vue({
        //定义页面作用域
        el: "#app",
        //定义页面变量
        data: {
            tag: {
                companys: [],
                activeName: "second"
            },
            typeName: "普通用户",
            menuCheckAll: true,
            ConpanycheckAll: true,
            checkAll: true,
            search: {p_sss: {text: "", value: ""}, orgCompanyId: ""},
            multiselect: false,
            modalPage: {
                title: "新增",
                show: false,
                model: {},
                modelRule: {
                    orgDeptId: [
                        {required: true, message: '请选择部门', trigger: 'change'}
                    ],
                    orgJobId: [
                        {required: true, message: '请选择岗位', trigger: 'change'}
                    ],
                    email: [
                        {required: true, message: '请填写邮箱', trigger: 'blur'}
                    ],
                    phone: [
                        {required: true, message: '请填写手机号码', trigger: 'blur'},
                        {pattern: /^1(1|2|3|4|5|6|7|8|9)\d{9}$/, message: "手机格式不正确!", trigger: 'change'}
                    ],
                    realName: [
                        {required: true, message: '请填写真实姓名', trigger: 'blur'}
                    ],
                },
                width: "100px"
            },
            modalAlert: {
                title: "  ",
                show: false,
                query: undefined,
                table: {
                    height: "60%",
                    columns: [{title: "手机号码", key: "phone"}, {title: "真实姓名", key: "realName"}]
                }
            },
            selectRows: [],
            //设置客户
            modalCustomer: {
                title: "设置客户权限",
                show: false,
                model: {},
                dataFile: []
            },
            //设置仓库
            modalStock: {
                title: "设置仓库权限",
                show: false,
                model: {},
                dataFile: []
            },
            SettingsProvider2: [],
            modalMenuPermissions: {
                title: "设置用户权限",
                show: false,
                model: {},
                dataFile: [],
                dataOrigin: [],
                defaultProps: {
                    children: "chindren",
                    label: "menuName"
                }
            }
        },
        //定义页面方法
        methods: {
            //页面初始化
            init() {
                this.search.p_sss.text = "phone"; //设置快速搜索默认值
                $(window).resize();
            },
            //查询数据
            onSearch() {
                gridConf.dataSource.filter(YX.Kendo.sformat(app.search));
            },
            //切换表格行单选和多选
            multiSelect() {
                this.multiselect = !this.multiselect;
                app.options.selectable = this.multiselect ? "multiple" : "row";
                YX.kUI.reSet(app.grid, app.options);
                $(window).resize();
            },
            //选中数据列表中的全部数据
            selectAll() {
                YX.kUI.gridSelectAll(this.grid);
            },
            //弹出页面
            openModal(uid) {
                if (uid) {
                    this.modalPage.title = "修改用户";
                    $.ajax({
                        url: "/User/GetUserById",
                        contentType: "application/x-www-form-urlencoded",
                        data: "userId=" + uid,
                        success: function (result) {
                            app.modalPage.model = result;
                        }
                    });
                } else {
                    this.modalPage.model = {
                        passWord: 123456,
                        entryDate: null,
                        orgDeptId: null,
                        orgJobId: null,
                        orgCompanyId: isSupplier ? this.modalPage.model.orgCompanyId : null,
                        driverLicenseType: null,
                        driverLicenseDueDate: '',
                        sex: 9901,
                        userValidate: 5003,
                        driverQuoteType: null
                    };
                    this.modalPage.title = "新增用户";
                    app.tag.isSJ = false;
                }
                this.modalPage.show = true;
            },
            //启用·停用选中数据弹出框
            onOperationUserValidate(url, title) {
                this.modalAlert.url = url;
                this.selectRows = YX.Kendo.getGridSelectRows(grid);
                if (this.selectRows.length <= 0) {
                    YX.Alert.warning("请选择一条数据！");
                    return;
                }
                this.openAlert({
                    title: "确定要" + title + "以下数据吗?",
                    query: "delete"
                });
                setTimeout(function () {
                    YX.Alert.warning("请谨慎操作！");
                }, 50);
            },
            //关闭修改/新增用户信息对话框
            onDown() {
                this.$refs["modalPage"].resetFields();
                app.modalPage.show = false;
            },
            //弹出框
            openAlert(o) {
                if (alertQuerys.indexOf(o.query) >= 0) {
                    this.modalAlert.show = true;
                    this.modalAlert.query = o.query;
                    this.modalAlert.title = o.title;
                    this.modalAlert.body = o.body;
                }
            },
            //多选启用，停用和删除
            onOpp() {
                var rows = YX.Kendo.getGridSelectRows(grid);
                var ids = [];
                for (var i = 0; i < rows.length; i++) {
                    ids.push(rows[i].userId);
                }
                $.ajax({
                    url: app.modalAlert.url,
                    data: JSON.stringify(ids),
                    dataType: "text", //返回值类型（默认是JSON）
                    success: function (result) {
                        if (result == "") {
                            YX.Alert.success("操作成功！");
                            app.onSearch(); //重新查询
                        } else {
                            YX.Alert.error(result); //错误信息弹出
                        }
                        app.modalAlert.show = false; //关闭弹出页面
                    }
                });
            },
            //弹出菜单权限对话框
            onMenuPermissions() {
                this.selectRows = YX.Kendo.getGridSelectRows(grid);
                if (this.selectRows.length <= 0) {
                    YX.Alert.warning("请选择一条数据！");
                    return;
                }
                ;
                var userId = this.selectRows[0].userId;
                app.modalMenuPermissions.dataFile = [];
                //调取数据
                $.ajax({
                    url: "/Menus/GetMenus",
                    contentType: "application/x-www-form-urlencoded",
                    data: {
                        requestId: userId,
                        type: "user"
                    },
                    success: function (result) {
                        app.modalMenuPermissions.dataOrigin = result.AllVal;
                        if (app.modalMenuPermissions.dataOrigin.length == 0) {
                            YX.Alert.warning("请前往组织架构页面,配置该用户岗位的菜单权限！");
                        }
                        var TitVal = JSON.parse(result.TitVal);
                        for (var itm of TitVal) {
                            app.modalMenuPermissions.dataFile.push(itm.menuId);
                        }
                        app.modalMenuPermissions.dataFile = smMenuKey(app.modalMenuPermissions.dataFile);
                        app.$refs.menuPermissions.setCheckedKeys(app.modalMenuPermissions.dataFile);
                    }
                });
                app.modalMenuPermissions.show = true;
            },
            //设置菜单权限提交方法
            onSetMenuPermissions() {
                var data = fullMenuKey(this.$refs.menuPermissions.getCheckedKeys());
                this.selectRows = YX.Kendo.getGridSelectRows(grid);
                var userId = this.selectRows[0].userId;
                $.ajax({
                    url: "/User/SetUserMenu",
                    data: {request: data, userId: userId},
                    contentType: "application/x-www-form-urlencoded",
                    dataType: "text", //返回值类型（默认是JSON）
                    success: function (result) {
                        if (result == "") {
                            app.modalCustomer.show = false;
                            YX.Alert.success("操作成功！");
                            app.onSearch(); //重新查询
                        } else {
                            YX.Alert.error(result); //错误信息弹出
                        }
                    }
                });
                app.modalMenuPermissions.show = false;
            },
            //关闭菜单权限对话框
            handleClose() {
                app.modalMenuPermissions.show = false;
            },

            //弹出设置客户对话框
            onSetCustomer() {
                this.selectRows = YX.Kendo.getGridSelectRows(grid);
                if (this.selectRows.length <= 0) {
                    YX.Alert.warning("请选择一条数据！");
                    return;
                }
                ;
                var userId = this.selectRows[0].userId;
                $.ajax({
                    url: "/User/GetNeedAddUserCustomerList",
                    contentType: "application/x-www-form-urlencoded",
                    data: "userId=" + userId,
                    success: function (result) {
                        app.modalCustomer.dataFile = result;
                    }
                });
                app.modalCustomer.show = true;
            },

            //设置客户提交方法
            onSettingCustomer() {
                var data = app.modalCustomer.dataFile;
                this.selectRows = YX.Kendo.getGridSelectRows(grid);
                var userId = this.selectRows[0].userId;
                //执行（只提交选中的数据）
                data = app.IsChecked(data, userId);
                $.ajax({
                    url: "/User/SetCustomers",
                    data: "request=" + JSON.stringify(data) + "&userId=" + userId,
                    contentType: "application/x-www-form-urlencoded",
                    dataType: "text", //返回值类型（默认是JSON）
                    success: function (result) {
                        if (result == "") {
                            app.modalCustomer.show = false;
                            YX.Alert.success("操作成功！");
                            app.onSearch(); //重新查询
                        } else {
                            YX.Alert.error(result); //错误信息弹出
                        }
                    }
                });
            },
            onSetStock() {
                this.selectRows = YX.Kendo.getGridSelectRows(grid);
                if (this.selectRows.length <= 0) {
                    YX.Alert.warning("请选择一条数据！");
                    return;
                }
                ;
                var userId = this.selectRows[0].userId;
                $.ajax({
                    url: "/User/GetNeedAddUserStockList",
                    contentType: "application/x-www-form-urlencoded",
                    data: "userId=" + userId,
                    success: function (result) {
                        app.modalStock.dataFile = result;
                    }
                });
                app.modalStock.show = true;
            },
            onSettingStock() {
                var data = app.modalStock.dataFile;
                this.selectRows = YX.Kendo.getGridSelectRows(grid);
                var userId = this.selectRows[0].userId;
                //执行（只提交选中的数据）
                data = app.IsChecked(data, userId);
                $.ajax({
                    url: "/User/SetStocks",
                    data: "request=" + JSON.stringify(data) + "&userId=" + userId,
                    contentType: "application/x-www-form-urlencoded",
                    dataType: "text", //返回值类型（默认是JSON）
                    success: function (result) {
                        if (result == "") {
                            app.modalStock.show = false;
                            YX.Alert.success("操作成功！");
                            app.onSearch(); //重新查询
                        } else {
                            YX.Alert.error(result); //错误信息弹出
                        }
                    }
                });
            },
            //设置客户表格全选/全不选
            onAll() {
                app.checkAll = !app.checkAll;
                for (var itm of app.modalCustomer.dataFile) {
                    itm.isChecked = app.checkAll;
                }
            },
            onAllStock() {
                app.checkAll = !app.checkAll;
                for (var itm of app.modalStock.dataFile) {
                    itm.isChecked = app.checkAll;
                }
            },
            //设置菜单权限全选
            setMenuPermissionsCheckAll() {
                var checkAll = [];
                if (app.menuCheckAll == true) {
                    for (var i = 0; i < app.modalMenuPermissions.dataOrigin.length; i++) {
                        checkAll.push(app.modalMenuPermissions.dataOrigin[i].menuId);
                    }
                    ;
                    this.$refs.menuPermissions.setCheckedKeys(checkAll);
                    return app.menuCheckAll = false;
                } else {
                    this.$refs.menuPermissions.setCheckedKeys(checkAll);
                    return app.menuCheckAll = true;
                }
                ;
            }
        }
    });

    //===grid 表格配置与 初始化===
    gridConf = YX.Kendo.getGridConf({
        selectable: false,
        //设置数据列
        columns: [
            {selectable: true, width: "32px"}, {
                title: " ",
                template: (dataItem) => gridConf.dataSource.data().indexOf(dataItem) + 1,
            width
:
    30
},
    {
        field: "realName",
            template
    :
        "<a title='修改' onclick=\"app.openModal('#= userId#')\" >#= realName# </a>",
            title
    :
        "姓名",
            width
    :
        100
    }
,
    {
        title: "手机号码",
            field
    :
        "phone",
            width
    :
        120
    }
,
    {
        title: "集团名称",
            field
    :
        "groupName",
            width
    :
        140
    }
,
    {
        title: "公司名称",
            field
    :
        "companyName",
            width
    :
        150
    }
,
    {
        title: "所属部门",
            field
    :
        "deptName",
            width
    :
        120
    }
,
    {
        title: "所属岗位",
            field
    :
        "jobName",
            width
    :
        120
    }
,
    {
        title: "邮箱",
            field
    :
        "email",
            width
    :
        200
    }
,
    {
        title: "用工类型",
            field
    :
        "employType",
            width
    :
        120
    }
],
    //设置数据源
    dataSource: YX.Kendo.getGridSrc("id", "/User/GetUserList", YX.Kendo.sformat(app.search)),
})
    ;
    grid = $("#grid").kendoGrid(gridConf);
    app.init();
    //===延时渲染===
    setTimeout(() => {
        window.onresize = ()=> {YX.Kendo.gridResize()};
    $(".main_loading").fadeOut(500);
    YX.Kendo.gridResize();
},
    200
)
    ;
});
