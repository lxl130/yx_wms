/* 客户管理 */

let app;
$(document).ready(function () {
    app = new Vue({
        //定义页面作用域
        el: "#app",
        //定义页面变量
        data: {
            search: { p_sss: { text: "stockName", value: "" }, stockCode: "", stockId: ""},
            checkAll: true,
            tag: {
                companyList: [],
                customerList: [],
                stockTypeList: [],
                regions: [],//发货人省市区数据源
                regionVal: [],//发货人省市区选中的数据源
                regionObj: [],//发货人省市区选中的数据源
            },
            modalPage: {
                title: "添加仓库",
                show: false,
                width: "100px",
                model: {
                    stockId: "",
                    stockCode: "",
                    stockName: "",
                    companyId: "",
                    groupId: ""
                },
                //表单绑定验证
                validate: {
                    companyId: [
                        {
                            validator: (rule, value, callback) => {
                                if (!lutils.validate.required(value)) {
                                    callback(new Error("请输入数据"));
                                } else {
                                    callback();
                                }
                            }, trigger: "blur"
                        }
                    ],
                    stockType: [
                        {
                            validator: (rule, value, callback) => {
                                if (!lutils.validate.required(value)) {
                                    callback(new Error("请输入数据"));
                                } else {
                                    callback();
                                }
                            }, trigger: "blur"
                        }
                    ],
                    stockName: [
                        {
                            validator: (rule, value, callback) => {
                                if (!lutils.validate.required(value)) {
                                    callback(new Error("请输入仓库名称"));
                                } else {
                                    callback();
                                }
                            }, trigger: "blur"
                        }
                    ],
                    stockCode: [
                        {
                            validator: (rule, value, callback) => {
                                if (!lutils.validate.required(value)) {
                                    callback(new Error("请输入仓库编码"));
                                } else {
                                    callback();
                                }
                            }, trigger: "blur"
                        }
                    ],
                    senderName: [
                        {
                            validator: (rule, value, callback) => {
                                if (!lutils.validate.required(value)) {
                                    callback(new Error("请输入发件人姓名"));
                                } else {
                                    callback();
                                }
                            }, trigger: "blur"
                        }
                    ],
                    zipCode: [
                        {
                            validator: (rule, value, callback) => {
                                if (!lutils.validate.required(value)) {
                                    callback(new Error("请输入发件人邮编"));
                                } else {
                                    callback();
                                }
                            }, trigger: "blur"
                        }
                    ],
                    senderEmail: [
                        {
                            validator: (rule, value, callback) => {
                                if (!lutils.validate.required(value)) {
                                    callback(new Error("请输入发件人电子邮箱"));
                                } else {
                                    callback();
                                }
                            }, trigger: "blur"
                        }
                    ],
                    senderMobPhone: [
                        {
                            validator: (rule, value, callback) => {
                                if (!lutils.validate.required(value)) {
                                    callback(new Error("请输入发件人移动电话"));
                                } else {
                                    callback();
                                }
                            }, trigger: "blur"
                        }
                    ],
                    senderTelephone: [
                        {
                            validator: (rule, value, callback) => {
                                if (!lutils.validate.required(value)) {
                                    callback(new Error("请输入发件人固定电话"));
                                } else {
                                    callback();
                                }
                            }, trigger: "blur"
                        }
                    ],
                    detailAddress: [
                        {
                            validator: (rule, value, callback) => {
                                if (!lutils.validate.required(value)) {
                                    callback(new Error("请输入发件人详细地址"));
                                } else {
                                    callback();
                                }
                            }, trigger: "blur"
                        }
                    ],
                    region: [
                        {
                            validator: (rule, value, callback) => {
                                if (rule.field == "region") {
                                    if (app.tag.regionVal.length < 3) {
                                        callback(new Error("请输入发件人省市区"));
                                    } else {
                                        callback();
                                    }
                                } else {
                                    callback();
                                }
                            }, trigger: "blur"
                        }
                    ]
                }
            },
            modalContact: {
                title: "编辑仓库与客户的关联关系",
                show: false,
                width: "100px",
                stockId: "",
                companyId: "",
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

                //省市区（数据源）
                $.getJSON("http://staticyx.lnetco.com/cons/region.json", function (result) {
                    app.tag.regions = result || [];
                });
                //获取仓库类型列表
                $.ajax({
                    url: "/SelectList/GetStockTypeSelectList",
                    success: function (result) {
                        app.tag.stockTypeList = result;
                    }
                });
                //获取用户拥有的子公司列表
                $.ajax({
                    url: "/SelectList/GetUserCompanySelectList",
                    success: function (result) {
                        app.tag.companyList = result;
                    }
                });
                //获取用户拥有的客户公司列表
                $.ajax({
                    url: "/SelectList/GetUserCustomerSelectList",
                    success: function (result) {
                        app.tag.customerList = result;
                    }
                });
            },
            //查询数据
            onSearch() {
                this.options.dataSource.filter(YX.Kendo.sformat(app.search));
            },
            //编辑仓库信息
            editStock(stockId) {
                app.modalPage.show = true;
                app.modalPage.model = {};
                app.tag.regionVal = [];

                //重置表单验证警告信息
                app.$nextTick(function() {
                    //$nextTick方法为解决第一次执行clearValidate报错
                    app.$refs.modalPage.clearValidate();
                });
                if(!stockId){
                    app.modalPage.title = "添加仓库信息";
                    return;
                }
                //加载仓库信息
                $.ajax({
                    url: "/Stock/GetStockInfo?stockId="+stockId,
                    success: function (result) {
                        app.modalPage.model = result;
                        app.tag.regionVal = [
                            app.modalPage.model.provinceId,
                            app.modalPage.model.cityId,
                            app.modalPage.model.areaId
                        ];
                        app.regionValFromChange();
                    }
                });
                app.modalPage.title = "编辑仓库信息";
            },
            //发货省市区
            regionChange() {
                app.tag.regionObj = YX.Cascader.getObj(app.tag.regionVal, app.tag.regions);
            },
            //保存仓库信息
            saveStock() {
                //验证数据完整性和正确性
                app.$refs.modalPage.validate((valid) => {
                    if(!valid) return false;

                    //省市区赋值
                    if (app.tag.regionObj[0] != null) {
                        app.modalPage.model.province = app.tag.regionObj[0].label;
                        app.modalPage.model.provinceId = app.tag.regionObj[0].value;
                    }
                    if (app.tag.regionObj[1] != null) {
                        app.modalPage.model.city = app.tag.regionObj[1].label;
                        app.modalPage.model.cityId = app.tag.regionObj[1].value;
                    }
                    if (app.tag.regionObj[2] != null) {
                        app.modalPage.model.area = app.tag.regionObj[2].label;
                        app.modalPage.model.areaId = app.tag.regionObj[2].value;
                    }

                    //保存数据
                    $.ajax({
                        url: "/Stock/SaveStock",
                        data: JSON.stringify(app.modalPage.model),
                        dataType: "text", //返回值类型（默认是JSON）
                        success: function (result) {
                            if (result > 0) {
                                app.modalPage.show = false;//关闭弾罩层
                                YX.Alert.success("操作成功！");
                                app.onSearch(); //重新查询
                            } else {
                                YX.Alert.error("保存仓库失败，请联系管理员！");
                            }
                        }
                    });
                });
            },
            //修改仓库关联客户
            editStockCustomerContact(stockId, companyId) {
                if (!stockId) return;
                app.modalContact.contactList = [];
                app.modalContact.stockId = stockId;
                app.modalContact.companyId = companyId;
                $.ajax({
                    url: "/Stock/GetStockCustomerContactList?stockId="+stockId,
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
            //保存仓库关联客户
            saveStockCustomerContact() {
                let list = app.modalContact.contactList.filter(i => i.isChecked);
                let query = "?stockId="+app.modalContact.stockId+"&companyId="+app.modalContact.companyId;
                $.ajax({
                    url: "/Stock/SaveStockCustomerContact"+query,
                    data: JSON.stringify(list),
                    dataType: "text", //返回值类型（默认是JSON）
                    success: function (result) {
                        if (result > 0) {
                            app.modalContact.show = false;//关闭弾罩层
                            YX.Alert.success("操作成功！");
                            app.onSearch(); //重新查询
                        } else {
                            YX.Alert.error("操作数据失败，请联系管理员！");
                        }
                    }
                });
            },
            //弹出启用/删除框
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
                    case "删除":
                        this.modalValidate.table.validate = "603";
                        break;
                }
                this.modalValidate.show = true;
            },
            //保存数据状态
            updateDataValidate() {
                let stockId = this.selectRows[0].stockId;
                let validate = this.modalValidate.table.validate;
                $.ajax({
                    url: "/Stock/UpdateDataValidate?stockId="+ stockId +"&validate="+ validate,
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
                title: "仓库编码",
                field: "stockCode",
                width: 60
            },
            {
                title: "仓库名称",
                template: "<a title='修改' onclick=\"app.editStock('#= stockId#')\" >#= stockName# </a>",
                width: 100
            },
            {
                title: "仓库类型",
                field: "stockTypeName",
                width: 60
            },
            {
                title: "所属公司",
                field: "companyName",
                width: 100
            },
            {
                title: "关联客户数量",
                template: "<a onclick=\"app.editStockCustomerContact('#= stockId#', '#= companyId#')\">已关联#= customerCount#个客户</a>",
                width: 100
            },
            {
                title: "位置",
                field: "position",
                width: 100
            },
            {
                title: "联系人信息",
                field: "senderInfo",
                width: 100
            },
            {
                title: "创建时间",
                field: "createTime",
                width: 100
            }
        ],
        //设置数据源
        dataSource: YX.Kendo.getGridSrc("listId", "/Stock/GetStockList")
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