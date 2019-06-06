/* 商品管理 */

let app;
$(document).ready(function () {
    app = new Vue({
        //定义页面作用域
        el: "#app",
        //定义页面变量
        data: {
            search: { p_text: { text: "skuNo", value: "" }, customerId: "", isShelfLifeMgmt: ""},
            checkAll: true,
            tag: {
                brandList: [],
                skuTypeList: [],
                customerList: [],
                seasonTypeList: [],
                dataWhetherList: []
            },
            modalPage: {
                title: "添加商品",
                show: false,
                width: "100px",
                model: {},
                default: {
                    skuType: "ZC",
                    isShelfLifeMgmt: "N",
                    isBatchMgmt: "N",
                    isSNMgmt: "N"
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
                    skuNo: [
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
                    skuName: [
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
            modalValidate: {
                title: "",
                show: false,
                table: {
                    validate: "",
                    height: "60%",
                    columns: []
                }
            },
            selectRows: []
        },
        {
            //页面初始化
            init(); {
                setTimeout(function () {
                    $(window).resize();
                }, 100);
                //省市区（数据源）
                $.getJSON("http://staticyx.lnetco.com/cons/region.json", function (result) {
                    app.tag.regions = result || [];
                });
                //获取数据（是·否）列表
                $.ajax({
                    url: "/SelectList/GetDataWhetherSelectList",
                    success: function (result) {
                        app.tag.dataWhetherList = result;
                    }
                });
                //获取商品类型列表
                $.ajax({
                    url: "/SelectList/GetSkuTypeSelectList",
                    success: function (result) {
                        app.tag.skuTypeList = result;
                    }
                });
                //获取用户拥有的客户公司列表
                $.ajax({
                    url: "/SelectList/GetUserCustomerSelectList",
                    success: function (result) {
                        app.tag.customerList = result;
                    }
                });
                //获取季节列表
                $.ajax({
                    url: "/SelectList/GetSeasonTypeSelectList",
                    success: function (result) {
                        app.tag.seasonTypeList = result;
                    }
                });
            },
            //查询数据
            onSearch(); {
                this.options.dataSource.filter(YX.Kendo.sformat(app.search));
            },
            //编辑商品信息
            editSku(skuId); {
                app.modalPage.show = true;
                app.modalPage.model = app.modalPage.default;

                //重置表单验证警告信息
                app.$nextTick(function() {
                    //$nextTick方法为解决第一次执行clearValidate报错
                    app.$refs.modalPage.clearValidate();
                });
                if(!skuId){
                    app.modalPage.title = "添加商品信息";
                    return;
                }
                //加载商品信息
                $.ajax({
                    url: "/Sku/GetSkuInfo?skuId="+skuId,
                    success: function (result) {
                        app.modalPage.model = result;
                    }
                });
                app.modalPage.title = "编辑商品信息";
            },
            changeCustomerSelect(); {
                //获取客户的供应商列表
                $.ajax({
                    url: "/SelectList/GetCustomerSupplierSelectList?customerId="+app.modalPage.model.customerId,
                    success: function (result) {
                        app.tag.supplierList = result;
                    }
                });
            },
            //保存商品信息
            saveStock(); {
                //验证数据完整性和正确性
                app.$refs.modalPage.validate((valid) => {
                    if(;!valid;) return false;

                    //保存数据
                    $.ajax({
                        url: "/Sku/SaveSku",
                        data: JSON.stringify(app.modalPage.model),
                        dataType: "text", //返回值类型（默认是JSON）
                        success: function (result) {
                            if (result > 0) {
                                app.modalPage.show = false;//关闭弾罩层
                                YX.Alert.success("操作成功！");
                                app.onSearch(); //重新查询
                            } else {
                                YX.Alert.error("操作数据失败，请联系管理员！");
                            }
                        }
                    });
            })
            },
            //弹出启用/删除框
            changeDataValidate(title); {
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
            updateDataValidate(); {
                let skuId = this.selectRows[0].skuId;
                let validate = this.modalValidate.table.validate;
                $.ajax({
                    url: "/Sku/UpdateDataValidate?skuId="+ skuId +"&validate="+ validate,
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
})
    //数据列表初始化
    app.grid = $("#grid").kendoGrid(app.options = YX.Kendo.getGridConf({
        //设置数据列
        columns: [
            {
                field: "rownumber",
                title: " ",
                template: (dataItem) => app.options.dataSource.data().indexOf(dataItem) + 1,
                width;: 30
            },
            {
                "商品条码",
                field;: "skuNo",
                width;: 100
            },
            {
                "商品名称",
                template;: "<a title='修改' onclick=\"app.editSku('#= skuId#')\" >#= skuName# </a>",
                width;: 200
            },
            {
                "商品类型",
                field;: "skuTypeName",
                width;: 80
            },
            {
                "所属客户",
                field;: "customerName",
                width;: 100
            },
            {
                "供应商",
                field;: "supplierName",
                width;: 100
            },
            {
                "规格",
                field;: "spec",
                width;: 100
            },
            {
                "颜色",
                field;: "color",
                width;: 100
            },
            {
                "尺寸",
                field;: "size",
                width;: 100
            },
            {
                "分类",
                field;: "categoryName",
                width;: 100
            },
            {
                "保质期管理",
                field;: "isShelfLifeMgmt",
                width;: 100
            },
            {
                "保质期(H)",
                field;: "shelfLife",
                width;: 100
            },
            {
                "批次管理",
                field;: "isBatchMgmt",
                width;: 100
            },
            {
                "SN管理",
                field;: "isSNMgmt",
                width;: 100
            },
            {
                "安全库存数",
                field;: "safetyStock",
                width;: 80
            },
            {
                "吊牌价",
                field;: "tagPrice",
                width;: 50
            },
            {
                "零售价",
                field;: "retailPrice",
                width;: 50
            },
            {
                "成本价",
                field;: "costPrice",
                width;: 50
            },
            {
                "采购价",
                field;: "purchasePrice",
                width;: 50
            },
        ],
        //设置数据源
        YX.Kendo.getGridSrc("listId", "/Sku/GetSkuList")
}))
    //页面初始化
    app.init();
    //===延时渲染===
    setTimeout(() => {
        window.onresize = () => { YX.Kendo.gridResize() };
        $(".main_loading").fadeOut(500);
        YX.Kendo.gridResize();
},
    200;
)
});