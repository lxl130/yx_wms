/* 客户管理 */

let app;
$(document).ready(function () {
    app = new Vue({
        //定义页面作用域
        el: "#app",
        //定义页面变量
        data: {
            search: { p_sss: { text: "customerName", value: "" }, customerCode: "", customerId: ""},
            checkAll: true,
            modalPage: {
                title: "添加客户公司",
                show: false,
                width: "100px",
                model: {
                    customerId: "",
                    customerCode: "",
                    customerName: "",
                    customerFullName: "",
                    groupId: ""
                }
            },
            modalContact: {
                title: "编辑客户与子公司的关联关系",
                show: false,
                width: "100px",
                customerId: "",
                contactList: []
            },
            modalValidate: {
                title: "  ",
                show: false,
                table: {
                    validate: "",
                    height: "60%",
                    columns: []
                }
            },
            selectRows: []
        },
        methods: {
            //页面初始化
            init() {
                setTimeout(function () {
                    $(window).resize();
                }, 100);
            },
            //查询数据
            onSearch() {
                this.options.dataSource.filter(YX.Kendo.sformat(app.search));
            },
            //编辑客户公司信息
            editCustomer(customerId) {
                this.modalPage.show = true;
                app.modalPage.model = {};
                if(!customerId){
                    this.modalPage.title = "添加客户公司信息";
                    return;
                }
                //加载客户公司信息
                $.ajax({
                    url: "/Customer/GetCustomerInfo",
                    contentType: "application/x-www-form-urlencoded",
                    data:{customerId: customerId},
                    async: false,
                    success: function (result) {
                        app.modalPage.model = result;
                    }
                });
                this.modalPage.title = "编辑客户公司信息";
            },
            //保存客户公司信息
            saveCustomer() {
                if (!app.modalPage.model.customerCode) {
                    YX.Alert.warning("客户公司编号不能为空！");
                    return;
                }
                if (!app.modalPage.model.customerName) {
                    YX.Alert.warning("客户公司名称不能为空！");
                    return;
                }
                if (!app.modalPage.model.customerFullName) {
                    YX.Alert.warning("客户公司全称不能为空！");
                    return;
                }
                $.ajax({
                    url: "/Customer/SaveCustomer",
                    data: JSON.stringify(app.modalPage.model),
                    dataType: "text", //返回值类型（默认是JSON）
                    success: function (result) {
                        if (result > 0) {
                            app.modalPage.show = false;//关闭弾罩层
                            YX.Alert.success("操作成功！");
                            app.onSearch(); //重新查询
                        } else {
                            YX.Alert.error("保存客户公司失败，请联系管理员！");
                        }
                    }
                });
            },
            //修改关联子公司
            editCompanyContact(customerId) {
                if (!customerId) return;
                app.modalContact.contactList = [];
                app.modalContact.customerId = customerId;
                $.ajax({
                    url: "/Customer/GetCompanyContactList?customerId="+customerId,
                    success: function (result) {
                        app.modalContact.contactList = result;
                    }
                });
                app.modalContact.show = true;
            },
            //设置子公司列表全选/全不选
            selectAll() {
                app.checkAll = !app.checkAll;
                for (let item of app.modalContact.contactList) {
                    item.isChecked = app.checkAll;
                }
            },
            //保存关联子公司信息
            saveCompanyContact() {
                let list = app.modalContact.contactList.filter(i => i.isChecked);
                $.ajax({
                    url: "/Customer/SaveCompanyContact?customerId="+app.modalContact.customerId,
                    data: JSON.stringify(list),
                    dataType: "text", //返回值类型（默认是JSON）
                    success: function (result) {
                        if (result > 0) {
                            app.modalContact.show = false;//关闭弾罩层
                            YX.Alert.success("操作成功！");
                            app.onSearch(); //重新查询
                        } else {
                            YX.Alert.error("保存客户与子公司关系失败，请联系管理员！");
                        }
                    }
                });
            },
            //启用，删除
            changeDataValidate(title) {
                this.selectRows = YX.Kendo.getGridSelectRows(app.grid);
                if (this.selectRows.length <= 0) {
                    YX.Alert.warning("请选择一条数据！");
                    return;
                }
                this.modalValidate.title = "确定要" + title + "以下数据吗?";
                switch (title) {
                    case "启用":
                        this.modalValidate.table.validate = "Y";
                        break;
                    case "停用":
                        this.modalValidate.table.validate = "602";
                        break;
                    case "删除":
                        this.modalValidate.table.validate = "603";
                        break;
                }
                this.modalValidate.show = true;
            },
            //更改数据状态
            updateDataValidate() {
                this.selectRows = YX.Kendo.getGridSelectRows(app.grid);
                if (this.selectRows.length <= 0) {
                    YX.Alert.warning("请选择一条数据！");
                    return;
                }
                let customerId = this.selectRows[0].customerId;
                let validate = this.modalValidate.table.validate;
                $.ajax({
                    url: "/Customer/UpdateDataValidate?customerId="+ customerId +"&validate="+ validate,
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
    app.grid = $("#grid").kendoGrid(app.options = YX.Kendo.getGridConf({
        //设置数据列
        columns: [
            {
                field: "rownumber",
                title: " ",
                template: (dataItem) => app.options.dataSource.data().indexOf(dataItem) + 1,
                width: 30
            },
            {
                title: "客户名称",
                template: "<a title='修改' onclick=\"app.editCustomer('#= customerId#')\" >#= customerName# </a>",
                width: 150
            },
            {
                title: "客户编码",
                field: "customerCode",
                width: 100
            },
            {
                title: "已关联子公司",
                template: "<a title='修改' onclick=\"app.editCompanyContact('#= customerId#')\" >#= companyNames# </a>",
                width: 300
            },
            {
                title: "创建人",
                field: "createUserName",
                width: 100
            },
            {
                title: "创建时间",
                field: "createTime",
                width: 100
            }
        ],
        //设置数据源
        dataSource: YX.Kendo.getGridSrc("listId", "/Customer/GetCustomerList")
    }));
    //页面初始化
    app.init();
    //===延时渲染===
    setTimeout(() => {
        window.onresize = () => { YX.Kendo.gridResize() };
        $(".main_loading").fadeOut(500);
        YX.Kendo.gridResize();
    }, 200);
})