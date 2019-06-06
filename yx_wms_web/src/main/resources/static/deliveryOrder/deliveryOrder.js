/* 发货单管理 */

let app;
$(document).ready(function () {
    app = new Vue({
        //定义页面作用域
        el: "#app",
        //定义页面变量
        data: {
            search: {
                p_date: { text: "orderTime", value: "" },
                p_text: { text: "deliveryOrderNo", value: "" },
                regionTxt: [],//发货人省市区选中的数据源
                provinceId: "",
                cityId: "",
                areaId: ""
            },
            searchSkuModel: {
                skuId: "",
                skuNo: ""
            },
            tag: {
                createTypeDisplay: true,//如果是系统对接，不允许修改明细
                activeName: "first",
                regions: [],
                shopList: [],
                stockList: [],
                expressList: [],
                companyList: [],
                customerList: [],
                platformList: [],
                dataWhetherList: [],
                deliveryOrderTypeList: []
            },
            modalOrder: {
                show: false,
                width: "100px",
                regionVal: [],//发货人省市区选中的数据源
                regionObj: [],//发货人省市区选中的数据源
                model: {
                    deliveryOrderId: "",
                    customerId: "",
                    stockId: "",
                    items: []
                },
                //表单绑定验证
                validate: {
                    customerId: [
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
                    stockId: [
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
                    deliveryOrderNo: [
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
                    orderTime: [
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
                    receiverName: [
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
                    receiverMobile: [
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
                    region: [
                        {
                            validator: (rule, value, callback) => {
                                if (rule.field == "region") {
                                    if (app.tag.regionToVal.length < 3) {
                                        callback(new Error("请输入数据"));
                                    } else {
                                        callback();
                                    }
                                } else {
                                    callback();
                                }
                            }, trigger: "blur"
                        }
                    ],
                    receiverAddress: [
                        {
                            validator: (rule, value, callback) => {
                                if (!lutils.validate.required(value)) {
                                    callback(new Error("请输入数据"));
                                } else {
                                    callback();
                                }
                            }, trigger: "blur"
                        }
                    ]
                }
            },
            modalOrderItem: {
                show: false,
                CusSkuList: [],//客户商品信息
                CanSkuList: [],//选择的商品信息
                listTotal: 0,
                pagination: {
                    current: 1,//分页游标
                    sizes: [20, 50, 100, 200, 300, 500],//分页列表
                    size: 20,//默认分页大小
                },
                model: {
                    skuId: "",
                    skuName: "",
                    deliveryOrderId: ""
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
                //获取销售平台列表
                $.ajax({
                    url: "/SelectList/GetPlatformSelectList",
                    success: function (result) {
                        app.tag.platformList = result;
                    }
                });
                //获取数据（是·否）列表
                $.ajax({
                    url: "/SelectList/GetDataWhetherSelectList",
                    success: function (result) {
                        app.tag.dataWhetherList = result;
                    }
                });
                //获取发货单类型的下拉列表
                $.ajax({
                    url: "/SelectList/GetDeliveryOrderTypeSelectList",
                    success: function (result) {
                        app.tag.deliveryOrderTypeList = result;
                    }
                });
                //获取物流公司列表
                $.ajax({
                    url: "/SelectList/GetExpressSelectList",
                    success: function (result) {
                        app.tag.expressList = result;
                    }
                });
                //获取用户拥有的子公司列表
                $.ajax({
                    url: "/SelectList/GetUserCompanySelectList",
                    success: function (result) {
                        app.tag.companyList = result;
                    }
                });
                //获取用户拥有的仓库列表
                $.ajax({
                    url: "/SelectList/GetUserStockSelectList",
                    success: function (result) {
                        app.tag.stockList = result;
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
                app.options.dataSource.filter(YX.appSearchFormat(app.search));
            },
            //搜索框省市区
            searchRegionChange() {
                app.search.provinceId = app.search.regionTxt[0];
                app.search.cityId = app.search.regionTxt[1];
                app.search.areaId = app.search.regionTxt[2];
            },
            //修改仓库
            stockChange(stockId) {
                let stock = app.tag.stockList.find(v => v.stockId === stockId);
                if (stock) {
                    app.modalOrder.model.companyId = stock.companyId;
                }
            },
            //编辑客户公司信息
            addDeliveryOrder() {
                app.modalOrder.model = {};
                app.modalOrder.model.items = [];
                //重置表单验证警告信息
                app.$nextTick(function() {
                    //$nextTick方法为解决第一次执行clearValidate报错
                    app.$refs.modalOrder.clearValidate();
                });
                app.modalOrder.show = true;
            },
            //收货省市区
            regionChange() {
                app.modalOrder.regionObj = YX.Cascader.getObj(app.modalOrder.regionVal, app.tag.regions);
            },
            //弹出商品列表
            addOrderItem() {
                if (!app.modalOrder.model.customerId) {
                    YX.Alert.warning("请选择客户！");
                    return;
                }
                if (!app.modalOrder.model.stockId) {
                    YX.Alert.warning("请选择仓库！");
                    return;
                }
                app.modalOrderItem.model.customerId = app.modalOrder.model.customerId;
                app.modalOrderItem.model.stockId = app.modalOrder.model.stockId;
                app.skuPaginationChangeCurrent(1);
                app.modalOrderItem.show = true;//弹出客户的商品信息框
            },
            //UI调用kendo的分页数据时的数据格式化函数
            getSendPageInfo(pagination, filter) {
                let d = {"take": pagination.size, "skip": (pagination.current - 1) * pagination.size, "page": pagination.current, "pageSize": pagination.size};
                d.filter = filter;
                return d;
            },
            //获取多选的商品信息
            selectOrderSku(val) {
                app.modalOrderItem.CanSkuList = val;
            },
            //修改分页
            skuPaginationChangeSize(v) {
                app.modalOrderItem.pagination.size = v;
                app.searchSku();
            },
            //修改分页
            skuPaginationChangeCurrent(v) {
                app.modalOrderItem.pagination.current = v;
                app.searchSku();
            },
            //显示商品列表
            searchSku() {
                $(".main_loading").fadeIn(250);//加载中开启
                let obj = { logic: "and", filters: [] }, o = {};
                for (let k in app.searchSkuModel) {
                    let s = app.searchSkuModel[k].trim();
                    if (s) o[k] = s;
                }
                obj.filters.push(o);
                $.ajax({
                    url: "/Sku/GetSkuList",
                    data: JSON.stringify(app.getSendPageInfo(app.modalOrderItem.pagination, obj)),
                    success: function (result) {
                        app.modalOrderItem.CusSkuList = result.data;
                        app.modalOrderItem.listTotal = result.total;
                    }
                });
                $(".main_loading").fadeOut(250);//加载中关闭
            },
            //点击确定（商品弹出框）
            AddSkuInfo() {
                app.modalOrderItem.show = false;
                if (app.modalOrderItem.CanSkuList.length == 0) return;

                let n = {}, m = []; //过滤缓存
                for (let i of app.modalOrderItem.CanSkuList) { //<==先追加
                    let ItemModel = {
                        deliveryOrderItemId: i.deliveryOrderItemId,
                        skuName: i.skuName,
                        skuNo: i.skuNo,
                        skuId: i.skuId,
                        retailPrice: i.retailPrice,
                        grossWeight: i.grossWeight,
                        skuTypeId: i.skuTypeId,
                        planQty: i.planQty
                    }
                    app.modalOrder.model.items.push(ItemModel);
                }
                for (let i of app.modalOrder.model.items) {//去重
                    if (!n[i.skuId]) {
                        n[i.skuId] = i;
                    } else {
                        n[i.skuId].planQty += i.planQty;
                    }
                }
                for (let k in n) {
                    m.push(n[k]);
                }
                app.modalOrder.model.items = m;
            },
            //删除明细
            handleDelete(index) {
                app.modalOrder.model.items.splice(index, 1);
            },
            //保存发货单信息
            saveDeliveryOrder() {
                //验证数据完整性和正确性
                app.$refs.modalOrder.validate((valid) => {
                    if(!valid) return false;
                });

                //省市区赋值
                if (app.modalOrder.regionVal[0] != null) {
                    app.modalOrder.model.receiverProvince = app.modalOrder.regionObj[0].label;
                    app.modalOrder.model.receiverProvinceId = app.modalOrder.regionObj[0].value;
                }
                if (app.modalOrder.regionVal[1] != null) {
                    app.modalOrder.model.receiverCity = app.modalOrder.regionObj[1].label;
                    app.modalOrder.model.receiverCityId = app.modalOrder.regionObj[1].value;
                }
                if (app.modalOrder.regionVal[2] != null) {
                    app.modalOrder.model.receiverArea = app.modalOrder.regionObj[2].label;
                    app.modalOrder.model.receiverAreaId = app.modalOrder.regionObj[2].value;
                }

                //保存数据
                $.ajax({
                    url: "/DeliveryOrder/SaveDeliveryOrder",
                    data: JSON.stringify(app.modalOrder.model),
                    dataType: "text", //返回值类型（默认是JSON）
                    success: function (result) {
                        if (result > 0) {
                            app.modalOrder.show = false;//关闭弾罩层
                            YX.Alert.success("操作成功！");
                            app.onSearch(); //重新查询
                        } else {
                            YX.Alert.error("保存数据失败，请联系管理员！");
                        }
                    }
                });
            },
            //查看发货单详情
            getDeliveryOrderInfo(deliveryOrderId) {
                //保存数据
                $.ajax({
                    url: "/DeliveryOrder/GetDeliveryOrderInfo?deliveryOrderId="+deliveryOrderId,
                    success: function (result) {
                        app.modalOrder.model = result;
                    }
                });
                app.modalOrder.show = true;
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
                title: "发货单号",
                template: "<a title='修改' onclick=\"app.getDeliveryOrderInfo('#= deliveryOrderId#')\" >#= deliveryOrderNo# </a>",
                width: 100
            },
            {
                title: "客户名称",
                field: "customerName",
                width: 100
            },
            {
                title: "发货仓库",
                field: "stockName",
                width: 100
            },
            {
                title: "是否取消",
                field: "isCancel",
                width: 100
            },
            {
                title: "ERP单号",
                field: "erpOrderNo",
                width: 100
            },
            {
                title: "WMS单号",
                field: "wmsOrderNo",
                width: 100
            },
            {
                title: "承运商",
                field: "expressName",
                width: 100
            },
            {
                title: "来源平台",
                field: "platformName",
                width: 100
            },
            {
                title: "订单日期",
                field: "orderTime",
                width: 100
            },
            {
                title: "下单日期",
                field: "buyTime",
                width: 100
            },
            {
                title: "付款日期",
                field: "payTime",
                width: 100
            },
            {
                title: "买家昵称",
                field: "buyerNick",
                width: 100
            },
            {
                title: "订单总数量",
                field: "planTotalQty",
                width: 80
            },
            {
                title: "收货地",
                field: "position",
                width: 130
            },
            {
                title: "收货人",
                field: "receiverInfo",
                width: 150
            },
        ],
        //设置数据源
        dataSource: YX.Kendo.getGridSrc("listId", "/DeliveryOrder/GetDeliveryOrderList")
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