let app;
$(document).ready(function () {
    app = new Vue({
        //定义页面作用域
        el: "#app",
        //定义页面变量
        data: {
            userShow: true,
            modalPage: {
                title: "新增",
                show: false,
                model: {
                    companyName: "",
                    companyCode: "",
                    companyFullName: ""
                },
                width: "100px"
            },
            //新增公司管理员
            modelUserAdmin: {
                title: "",
                model: {},
                show: false,
                width: "100px"
            },
            modalValidate: {
                title: "  ",
                show: false,
                query: undefined,
                table: {
                    validate: "",
                    height: "60%",
                    columns: [{ title: "公司编号", key: "companyId" }, { title: "公司名称", key: "companyName" }]
                }
            },
            selectRows: []
        },
        //定义页面方法
        methods: {
            //页面初始化
            init() {
                $(window).resize();
            },
            //查询数据
            onSearch() {
                gridConf.dataSource.filter(YX.Kendo.sformat(this.search));
            },
            //弹出新增公司页面
            addCompany(companyId) {
                this.modalPage.show = true;
                app.modalPage.model = {companyName: "", companyCode: "", companyFullName: ""};

                if(!companyId){
                    this.modalPage.title = "新增公司";
                    return;
                }
                this.modalPage.title = "修改公司";
                $.ajax({
                    url: "/Company/GetCompanyInfo?companyId=" + companyId,
                    success: function (result) {
                        app.modalPage.model = result;
                    }
                });
            },
            //保存公司信息
            saveCompany() {
                if(!app.modalPage.model.companyName){
                    YX.Alert.warning("请输入公司简称！");
                    return;
                }
                if(!app.modalPage.model.companyCode){
                    YX.Alert.warning("请输入公司代码！");
                    return;
                }
                if(!app.modalPage.model.companyFullName){
                    YX.Alert.warning("请输入公司全称！");
                    return;
                }
                $.ajax({
                    url: "/Company/SaveCompany",
                    data: JSON.stringify(app.modalPage.model),
                    dataType: "text",//返回值类型（默认是JSON）
                    success: function (result) {
                        if (result > 0) {
                            YX.Alert.success("操作成功！");
                            app.modalPage.show = false;//关闭弾罩层
                            app.onSearch();//重新查询
                        } else {
                            YX.Alert.error("数据操作失败！");//错误信息弹出
                        }
                    }
                });
            },
            //弹出(新增公司管理员)页面
            addCompanyAdmin() {
                this.selectRows = YX.Kendo.getTreeSelectRows(grid);
                if (this.selectRows.length <= 0) {
                    YX.Alert.warning("请选择一条公司！");
                    return;
                }
                app.userShow = true;
                this.modelUserAdmin.model = { passWord: 123456, entryDate: null };
                this.modelUserAdmin.title = "新增公司管理员用户";
                this.modelUserAdmin.show = true;
            },
            //保存公司管理员
            saveCompanyAdmin() {
                this.selectRows = YX.Kendo.getTreeSelectRows(app.grid);
                app.modelUserAdmin.model.companyId = this.selectRows[0].companyId;
                app.modelUserAdmin.model.groupId = this.selectRows[0].groupId;
                $.ajax({
                    url: "/User/InsertUserAdminData",
                    data: JSON.stringify(app.modelUserAdmin.model),
                    dataType: "text", //返回值类型（默认是JSON）
                    success: function (result) {
                        if (result > 0) {
                            app.modelUserAdmin.show = false; //关闭弾罩层
                            YX.Alert.success("操作成功！");
                            app.onSearch(); //重新查询
                        } else {
                            YX.Alert.error("数据操作失败！"); //错误信息弹出
                        }
                    }
                });
            },
            //启用，删除
            changeDataValidate(title) {
                this.selectRows = YX.Kendo.getTreeSelectRows(app.grid);
                if (this.selectRows.length <= 0) {
                    YX.Alert.warning("请选择一条数据！");
                    return;
                }
                this.modalValidate.title = "确定要" + title + "以下数据吗?";
                switch (title) {
                    case "启用":
                        this.modalValidate.table.validate = "Y";
                        break;
                    case "删除":
                        this.modalValidate.table.validate = "603";
                        break;
                }
                this.modalValidate.show = true;
            },
            //更改数据状态
            updateDataValidate() {
                this.selectRows = YX.Kendo.getTreeSelectRows(app.grid);
                if (this.selectRows.length <= 0) {
                    YX.Alert.warning("请选择一条数据！");
                    return;
                }
                let companyId = this.selectRows[0].companyId;
                let validate = this.modalValidate.table.validate;
                $.ajax({
                    url: "/Company/UpdateDataValidate?companyId="+ companyId +"&validate="+ validate,
                    dataType: "text",//返回值类型（默认是JSON）
                    success: function (result) {
                        if (result > 0) {
                            YX.Alert.success("操作成功！");
                            app.onSearch();//重新查询
                        } else {
                            YX.Alert.error("数据操作失败！");//错误信息弹出
                        }
                        app.modalValidate.show = false;//关闭弹出页面
                    }
                });
            }
        }
    });

    //数据列表初始化
    grid = $("#grid").kendoTreeList(app.options = YX.Kendo.getGridConf({
        selectable: "row",
        pageable: false,
        //设置数据列
        columns: [
            {
                title: "公司简称",
                field: "companyName",
                template: "<a title='修改' onclick=\"app.addCompany('#= companyId#')\" >#= companyName# </a>",
                width: 200
            },
            {
                title: "公司编号",
                field: "companyCode",
                width: 100
            },
            {
                title: "公司全称",
                field: "companyFullName",
                width: 150
            },
            {
                title: "公司管理员",
                field: "adminName",
                width: 100
            },
            {
                title: "创建人",
                field: "createUserName",
                width: 100
            },
            {
                title: "创建时间",
                field: "createTime",
                width: 200
            },
            {
                title: "可用状态",
                field: "validateName",
                width: 150
            }
        ],
        //设置数据源
        dataSource: YX.Kendo.getTreeListSrc("companyId", "/Company/GetMyCompanyList", "parentId")
    }));
    //页面初始化
    app.init();
    //===延时渲染===
    setTimeout(() => {
        window.onresize = () => { YX.Kendo.gridResize() };
        $(".main_loading").fadeOut(500);
        YX.Kendo.gridResize();
    }, 200);
});