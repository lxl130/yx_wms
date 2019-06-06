<#include "../Shared/Layout.ftl">
<@layout>

<div id="app">
    <!-- 按钮和快速搜索开始 -->
    <div class="nav-bar" size="mini">
        <el-row :gutter="5" class="button-box">
            <el-col :span="20">
                <el-button-group>
                    <el-button type="primary" size="mini" icon="plus" v-on:click="addDeliveryOrder()">新增发货单</el-button>
                    <el-button type="primary" size="mini" icon="plus" v-on:click="modDeliveryOrder()">修改发货单</el-button>
                </el-button-group>
                <el-button-group>
                    <el-button type="primary" size="mini" icon="check" v-on:click="changeDataValidate('启用')">启用</el-button>
                    <el-button type="primary" size="mini" icon="delete" v-on:click="changeDataValidate('删除')">删除</el-button>
                </el-button-group>
            </el-col>
            <el-col :span="4">
                <el-button-group>
                    <el-button type="primary" size="mini" icon="plus" v-on:click="YX.onClear(app.search)">清 空</el-button>
                    <el-button type="primary" size="mini" icon="plus" v-on:click="onSearch()">查 询</el-button>
                </el-button-group>
            </el-col>
        </el-row>
        <el-row class="search-box">
            <el-col :span="4">
                <el-input-box>
                    <template slot="prepend">所属公司</template>
                    <el-select slot="body" filterable clearable size="mini" v-model="search.companyId" placeholder="请选择">
                        <el-option v-for="item in tag.companyList"
                                   :key="item.companyId"
                                   :label="item.companyName"
                                   :value="item.companyId">
                        </el-option>
                    </el-select>
                </el-input-box>
            </el-col>
            <el-col :span="4">
                <el-input-box>
                    <template slot="prepend">所属客户</template>
                    <el-select slot="body" filterable clearable size="mini" v-model="search.customerId" placeholder="请选择">
                        <el-option v-for="item in tag.customerList"
                                   :key="item.customerId"
                                   :label="item.customerName"
                                   :value="item.customerId">
                        </el-option>
                    </el-select>
                </el-input-box>
            </el-col>
            <el-col :span="4">
                <el-input-box>
                    <template slot="prepend">发货仓库</template>
                    <el-select slot="body" filterable clearable size="mini" v-model="search.stockId" placeholder="请选择">
                        <el-option v-for="item in tag.stockList"
                                   :key="item.stockId"
                                   :label="item.stockName"
                                   :value="item.stockId">
                        </el-option>
                    </el-select>
                </el-input-box>
            </el-col>
            <el-col :span="4">
                <el-input-box>
                    <template slot="prepend">承运商</template>
                    <el-select slot="body" filterable clearable size="mini" v-model="search.expressId" placeholder="请选择">
                        <el-option v-for="item in tag.expressList"
                                   :key="item.expressId"
                                   :label="item.expressName"
                                   :value="item.expressId">
                        </el-option>
                    </el-select>
                </el-input-box>
            </el-col>
            <el-col :span="8">
                <el-input-box>
                    <el-select v-model="search.p_date.text" slot="prepend" placeholder="请选择">
                        <el-option label="订单时间" value="orderTime"></el-option>
                        <el-option label="下单时间" value="buyTime"></el-option>
                        <el-option label="支付时间" value="payTime"></el-option>
                    </el-select>
                    <el-date-picker slot="body" size="mini" type="datetimerange"
                                    v-model="search.p_date.value" range-separator="-"
                                    start-placeholder="开始日期" end-placeholder="截止日期"
                                    value-format="yyyy-MM-dd HH:mm:ss"
                                    :default-time="['00:00:00', '23:59:59']">
                    </el-date-picker>
                </el-input-box>
            </el-col>
        </el-row>
        <el-row class="search-box">
            <el-col :span="4">
                <el-input-box>
                    <el-select slot="prepend" v-model="search.p_text.text" placeholder="请选择">
                        <el-option label="发货单ID" value="deliveryOrderId"></el-option>
                        <el-option label="发货单号" value="deliveryOrderNo"></el-option>
                        <el-option label="ERP单号" value="erpOrderNo"></el-option>
                        <el-option label="WMS单号" value="wmsOrderNo"></el-option>
                        <el-option label="快递单号" value="expressNo"></el-option>
                        <el-option label="买家昵称" value="buyerNick"></el-option>
                        <el-option label="买家手机" value="receiverMobile"></el-option>
                        <el-option label="卖家昵称" value="sellerNick"></el-option>
                    </el-select>
                    <el-input slot="body" v-model="search.p_text.value" auto-complete="off"></el-input>
                </el-input-box>
            </el-col>
            <el-col :span="4">
                <el-input-box>
                    <template slot="prepend">销售平台</template>
                    <el-select slot="body" filterable clearable size="mini" v-model="search.platformId" placeholder="请选择">
                        <el-option v-for="item in tag.platformList"
                                   :key="item.key"
                                   :label="item.value"
                                   :value="item.key">
                        </el-option>
                    </el-select>
                </el-input-box>
            </el-col>
            <el-col :span="4">
                <el-input-box>
                    <template slot="prepend">销售店铺</template>
                    <el-select slot="body" filterable clearable size="mini" v-model="search.shopId" placeholder="请选择">
                        <el-option v-for="item in tag.shopList"
                                   :key="item.shopId"
                                   :label="item.shopName"
                                   :value="item.shopId">
                        </el-option>
                    </el-select>
                </el-input-box>
            </el-col>
            <el-col :span="4">
                <el-input-box size="mini">
                    <template slot="prepend">收货区域</template>
                    <el-cascader slot="body" size="mini" :options="tag.regions" v-model="search.regionTxt" change-on-select filterable v-on:change="searchRegionChange()"></el-cascader>
                </el-input-box>
            </el-col>
            <el-col :span="4">
                <el-input-box>
                    <template slot="prepend">订单类型</template>
                    <el-select slot="body" filterable clearable size="mini" v-model="search.deliveryOrderTypeId" placeholder="请选择">
                        <el-option v-for="item in tag.deliveryOrderTypeList"
                                   :key="item.key"
                                   :label="item.value"
                                   :value="item.key">
                        </el-option>
                    </el-select>
                </el-input-box>
            </el-col>
            <el-col :span="4">
                <el-input-box>
                    <template slot="prepend">是否紧急</template>
                    <el-select slot="body" filterable clearable size="mini" v-model="search.isUrgency" placeholder="请选择">
                        <el-option v-for="item in tag.dataWhetherList"
                                   :key="item.key"
                                   :label="item.value"
                                   :value="item.key">
                        </el-option>
                    </el-select>
                </el-input-box>
            </el-col>
        </el-row>
    </div>
    <!-- 按钮和快速搜索结束 -->

    <!-- 数据列表开始 -->
    <div id="grid" class="dynamic-height"></div>
    <!-- 数据列表结束 -->

    <!-- 新增/修改客户公司对话框开始 -->
    <el-dialog title="新增发货单" :visible.sync="modalOrder.show" width="60%">
        <el-form :model="modalOrder.model" ref="modalOrder" size="mini">
            <el-tabs v-model="tag.activeName">
                <el-tab-pane label="发货信息" name="first">
                    <el-row>
                        <el-col :span="8">
                            <el-form-item label="客户" :label-width="modalOrder.width" prop="customerId" class="is-required">
                                <el-select filterable clearable size="mini" v-model="modalOrder.model.customerId" placeholder="请选择">
                                    <el-option v-for="item in tag.customerList"
                                               :key="item.customerId"
                                               :label="item.customerName"
                                               :value="item.customerId">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="发货仓库" :label-width="modalOrder.width" prop="stockId" class="is-required">
                                <el-select filterable clearable size="mini" v-model="modalOrder.model.stockId" v-on:change="stockChange" placeholder="请选择">
                                    <el-option v-for="item in tag.stockList"
                                               :key="item.stockId"
                                               :label="item.stockName"
                                               :value="item.stockId">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="承运商" :label-width="modalOrder.width" prop="expressId" class="is-required">
                                <el-select filterable clearable size="mini" v-model="modalOrder.model.expressId" placeholder="请选择">
                                    <el-option v-for="item in tag.expressList"
                                               :key="item.expressId"
                                               :label="item.expressName"
                                               :value="item.expressId">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                            <el-form-item label="来源单号" :label-width="modalOrder.width" prop="deliveryOrderNo" class="is-required">
                                <el-input v-model="modalOrder.model.deliveryOrderNo" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="ERP单号" :label-width="modalOrder.width" prop="erpOrderNo">
                                <el-input v-model="modalOrder.model.erpOrderNo" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="WMS单号" :label-width="modalOrder.width" prop="wmsOrderNo">
                                <el-input v-model="modalOrder.model.wmsOrderNo" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                            <el-form-item label="订单日期" :label-width="modalOrder.width" prop="orderTime" class="is-required">
                                <el-input v-model="modalOrder.model.orderTime" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="是否紧急" :label-width="modalOrder.width" prop="isUrgency">
                                <el-select filterable clearable size="mini" v-model="modalOrder.model.isUrgency" placeholder="请选择">
                                    <el-option v-for="item in tag.dataWhetherList"
                                               :key="item.key"
                                               :label="item.value"
                                               :value="item.key">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="订单类型" :label-width="modalOrder.width" prop="deliveryOrderType">
                                <el-select filterable clearable size="mini" v-model="modalOrder.model.deliveryOrderType" placeholder="请选择">
                                    <el-option v-for="item in tag.deliveryOrderTypeList"
                                               :key="item.key"
                                               :label="item.value"
                                               :value="item.key">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                            <el-form-item label="来源平台" :label-width="modalOrder.width" prop="platformId">
                                <el-select filterable clearable size="mini" v-model="modalOrder.model.platformId">
                                    <el-option v-for="item in tag.platformList"
                                               :key="item.key"
                                               :label="item.value"
                                               :value="item.key">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="来源店铺" :label-width="modalOrder.width" prop="shopId">
                                <el-select filterable clearable size="mini" v-model="modalOrder.model.shopId" placeholder="请选择">
                                    <el-option v-for="item in tag.shopList"
                                               :key="item.shopId"
                                               :label="item.shopName"
                                               :value="item.shopId">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="买家昵称" prop="buyerNick" :label-width="modalOrder.width">
                                <el-input v-model="modalOrder.model.buyerNick" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                            <el-form-item label="收件人公司" :label-width="modalOrder.width" prop="receiverCompany">
                                <el-input v-model="modalOrder.model.receiverCompany" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="收件人名称" :label-width="modalOrder.width" prop="receiverName" class="is-required">
                                <el-input v-model="modalOrder.model.receiverName" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="8">
                            <el-form-item label="收件人手机" :label-width="modalOrder.width" prop="receiverMobile" class="is-required">
                                <el-input v-model="modalOrder.model.receiverMobile" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="8">
                            <el-form-item label="收件人省市" prop="region" :label-width="modalOrder.width" class="is-required">
                                <el-cascader :options="tag.regions" v-model="modalOrder.regionVal" change-on-select filterable v-on:change="regionChange()" style="width:100%"></el-cascader>
                            </el-form-item>
                        </el-col>
                        <el-col :span="16">
                            <el-form-item label="收件人地址" :label-width="modalOrder.width" prop="receiverAddress" class="is-required">
                                <el-input v-model="modalOrder.model.receiverAddress" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="买家备注" :label-width="modalOrder.width" prop="buyerMessage">
                                <el-input v-model="modalOrder.model.buyerMessage" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="24">
                            <el-form-item label="订单备注" :label-width="modalOrder.width" prop="orderRemark">
                                <el-input v-model="modalOrder.model.orderRemark" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                </el-tab-pane>
                <el-tab-pane label="商品信息" name="second">
                    <el-button size="mini" v-on:click="addOrderItem" style="margin-bottom:10px;" v-if="tag.createTypeDisplay">添加商品</el-button>
                    <el-table :data="modalOrder.model.items" size="mini" highlight-current-row border style="width: 100%" class="el-tb-edit" ref="SkuTable" height="330px">
                        <el-table-column type="index" width="50"></el-table-column>
                        <el-table-column prop="skuName" label="商品名称" width="220">
                            <template scope="scope">
                                <span>{{scope.row.skuName}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="skuNo" label="商品编码" width="220">
                            <template scope="scope">
                                <span>{{scope.row.skuNo}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="grossWeight" label="毛重" width="130">
                            <template scope="scope">
                                <span>{{scope.row.grossWeight}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="planQty" label="应发商品数量" width="160">
                            <template scope="scope">
                                <el-input-number min="1" size="mini" v-model.number="scope.row.planQty" placeholder="请输入内容"></el-input-number>
                                <span>{{scope.row.planQty}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="retailPrice" label="零售价">
                            <template scope="scope">
                                <span>{{scope.row.retailPrice}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="actualPrice" label="实际成交价">
                            <template scope="scope">
                                <el-input size="mini" v-model="scope.row.actualPrice" placeholder="请输入内容"></el-input>
                                <span>{{scope.row.actualPrice}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column prop="remark" label="备注">
                            <template scope="scope">
                                <span>{{scope.row.remark}}</span>
                            </template>
                        </el-table-column>
                        <el-table-column label="操作" width="70">
                            <template slot-scope="scope">
                                <el-button size="mini" type="danger" v-on:click="handleDelete(scope.$index, scope.row)">删除</el-button>
                            </template>
                        </el-table-column>
                    </el-table>
                </el-tab-pane>
            </el-tabs>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button v-on:click="modalOrder.show=false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" v-on:click="saveDeliveryOrder()">确 定</el-button>
        </div>
    </el-dialog>
    <!-- 新增/修改客户公司对话框结束 -->

    <!--开始新增订单商品弹出框 -->
    <el-dialog title="商品信息" :visible.sync="modalOrderItem.show" width="80%">
        <el-row style="margin-bottom:10px">
            <el-col :span="12">
                <el-input placeholder="请输入商品编码" size="mini" v-model="modalOrderItem.skuNo">
                    <template slot="prepend">
                        商品编码
                    </template>
                    <el-button slot="append" icon="el-icon-search" v-on:click="addCustomSku()"></el-button>
                </el-input>
            </el-col>
            <el-col :span="6"></el-col>
            <el-col :span="6"></el-col>
        </el-row>
        <el-table border ref="multipleTable" :data="modalOrderItem.CusSkuList" size="mini" tooltip-effect="dark" style="width: 100%" v-on:selection-change="selectOrderSku" height="300">
            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="skuNo" label="商品编码" show-overflow-tooltip></el-table-column>
            <el-table-column prop="skuName" label="商品名称" show-overflow-tooltip></el-table-column>
            <el-table-column prop="barCode" label="商品条码" show-overflow-tooltip></el-table-column>
            <el-table-column prop="quantity" label="可用库存" show-overflow-tooltip></el-table-column>
            <el-table-column label="出库数量" show-overflow-tooltip>
                <template slot-scope="scope">
                    <el-input-number v-model.number="scope.row.planQty" min="1" size="mini"></el-input-number>
                </template>
            </el-table-column>
        </el-table>
        <div style="padding: 10px 0;">
            <el-pagination :current-page.sync="modalOrderItem.pagination.current"
                           :page-sizes="modalOrderItem.pagination.sizes" :page-size="modalOrderItem.pagination.size"
                           layout="sizes,prev,pager,next,total" :total="modalOrderItem.listTotal"
                           v-on:size-change="skuPaginationChangeSize" v-on:current-change="skuPaginationChangeCurrent"
                           v-on:prev-click="skuPaginationChangeCurrent" v-on:next-click="skuPaginationChangeCurrent">
            </el-pagination>
        </div>
        <span slot="footer" class="dialog-footer">
            <el-button v-on:click="modalOrderItem.show=false" size="mini">取 消</el-button>
            <el-button type="primary" v-on:click="AddSkuInfo()" size="mini">确 定</el-button>
        </span>
    </el-dialog>
    <!-- 新增订单商品弹出框结束 -->
</div>
<script src="/deliveryOrder/deliveryOrder.js"></script>
</@layout>