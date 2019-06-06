<#include "../Shared/Layout.ftl">
<@layout>

<div id="app">
    <!-- 按钮和快速搜索开始 -->
    <div class="nav-bar">
        <el-row :gutter="5" class="button-box">
            <el-col :span="20">
                <el-button-group>
                    <el-button type="primary" size="mini" icon="plus" v-on:click="editSku()">新增</el-button>
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
            <el-col :span="6">
                <el-input-box>
                    <el-select slot="prepend" v-model="search.p_text.text" placeholder="请选择">
                        <el-option label="商品名称" value="skuName"></el-option>
                        <el-option label="商品条码" value="skuNo"></el-option>
                        <el-option label="货号" value="goodsNo"></el-option>
                        <el-option label="商品ID" value="skuId"></el-option>
                        <el-option label="商品简称" value="shortName"></el-option>
                        <el-option label="英文名称" value="englishName"></el-option>
                    </el-select>
                    <el-input slot="body" v-model="search.p_text.value" auto-complete="off"></el-input>
                </el-input-box>
            </el-col>
            <el-col :span="6">
                <el-input-box>
                    <template slot="prepend">客户</template>
                    <el-select slot="body" v-model="search.customerId" filterable clearable>
                        <el-option v-for="item in tag.customerList"
                                   :key="item.customerId"
                                   :label="item.customerName"
                                   :value="item.customerId">
                        </el-option>
                    </el-select>
                </el-input-box>
            </el-col>
            <el-col :span="6">
                <el-input-box>
                    <template slot="prepend">保质期管理</template>
                    <el-select slot="body" v-model="search.isShelfLifeMgmt" filterable clearable>
                        <el-option v-for="item in tag.dataWhetherList"
                                   :key="item.key"
                                   :label="item.value"
                                   :value="item.key">
                        </el-option>
                    </el-select>
                </el-input-box>
            </el-col>
            <el-col :span="6">
                <el-input-box>
                    <template slot="prepend">SN管理</template>
                    <el-input slot="body" v-model="search.isSNMgmt" auto-complete="off"></el-input>
                </el-input-box>
            </el-col>
        </el-row>
    </div>
    <!-- 按钮和快速搜索结束 -->

    <!-- 数据列表开始 -->
    <div id="grid" class="dynamic-height"></div>
    <!-- 数据列表结束 -->

    <!-- 新增/修改商品对话框开始 -->
    <el-dialog :title="modalPage.title" :visible.sync="modalPage.show">
        <el-form :model="modalPage.model" ref="modalPage" size="mini" :rules="modalPage.validate" ref="modalPageForm">
            <el-row>
                <el-col span="8">
                    <el-form-item label="所属客户" :label-width="modalPage.width" prop="customerId" class="is-required">
                        <el-select v-model="modalPage.model.customerId" filterable clearable v-on:change="changeCustomerSelect()">
                            <el-option v-for="item in tag.customerList"
                                       :key="item.customerId"
                                       :label="item.customerName"
                                       :value="item.customerId">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col span="8">
                    <el-form-item label="供应商" :label-width="modalPage.width" prop="supplierId">
                        <el-select v-model="modalPage.model.supplierId" filterable clearable>
                            <el-option v-for="item in tag.supplierList"
                                       :key="item.supplierId"
                                       :label="item.supplierName"
                                       :value="item.supplierId">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col span="8">
                    <el-form-item label="商品类型" :label-width="modalPage.width" prop="skuType" class="is-required">
                        <el-select v-model="modalPage.model.skuType" filterable clearable>
                            <el-option v-for="item in tag.skuTypeList"
                                       :key="item.key"
                                       :label="item.value"
                                       :value="item.key">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col span="8">
                    <el-form-item label="商品条码" :label-width="modalPage.width" prop="skuNo" class="is-required">
                        <el-input v-model="modalPage.model.skuNo" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="8">
                    <el-form-item label="商品名称" :label-width="modalPage.width" prop="skuName" class="is-required">
                        <el-input v-model="modalPage.model.skuName" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="8">
                    <el-form-item label="货号" :label-width="modalPage.width" prop="goodsNo" class="is-required">
                        <el-input v-model="modalPage.model.goodsNo" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col span="8">
                    <el-form-item label="商品分类" :label-width="modalPage.width" prop="categoryId" class="is-required">
                        <el-input v-model="modalPage.model.categoryId" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="8">
                    <el-form-item label="安全库存" :label-width="modalPage.width" prop="safetyStock">
                        <el-input v-model="modalPage.model.safetyStock" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="8">
                    <el-form-item label="计量单位" :label-width="modalPage.width" prop="stockUnit">
                        <el-input v-model="modalPage.model.stockUnit" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col span="8">
                    <el-form-item label="商品简称" :label-width="modalPage.width" prop="shortName">
                        <el-input v-model="modalPage.model.shortName" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="8">
                    <el-form-item label="英文名称" :label-width="modalPage.width" prop="englishName">
                        <el-input v-model="modalPage.model.englishName" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="8">
                    <el-form-item label="品牌" :label-width="modalPage.width" prop="brandId">
                        <el-select v-model="modalPage.model.brandId" filterable clearable>
                            <el-option v-for="item in tag.brandList"
                                       :key="item.brandId"
                                       :label="item.brandName"
                                       :value="item.brandId">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col span="8">
                    <el-form-item label="尺码" :label-width="modalPage.width" prop="size">
                        <el-input v-model="modalPage.model.size" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="8">
                    <el-form-item label="商品颜色" :label-width="modalPage.width" prop="color">
                        <el-input v-model="modalPage.model.color" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="8">
                    <el-form-item label="季节" :label-width="modalPage.width" prop="seasonCode">
                        <el-select v-model="modalPage.model.seasonCode" filterable clearable>
                            <el-option v-for="item in tag.seasonTypeList"
                                       :key="item.key"
                                       :label="item.value"
                                       :value="item.key">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col span="4">
                    <el-form-item label="长" :label-width="modalPage.width" prop="length">
                        <el-input v-model="modalPage.model.length"></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="4">
                    <el-form-item label="宽" :label-width="modalPage.width" prop="width">
                        <el-input v-model="modalPage.model.width"></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="4">
                    <el-form-item label="高" :label-width="modalPage.width" prop="height">
                        <el-input v-model="modalPage.model.height"></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="4">
                    <el-form-item label="体积" :label-width="modalPage.width" prop="grossWeight">
                        <el-input v-model="modalPage.model.volume"></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="4">
                    <el-form-item label="毛重" :label-width="modalPage.width" prop="grossWeight">
                        <el-input v-model="modalPage.model.grossWeight"></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="4">
                    <el-form-item label="净重" :label-width="modalPage.width" prop="netWeight">
                        <el-input v-model="modalPage.model.netWeight"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col span="6">
                    <el-form-item label="吊牌价" :label-width="modalPage.width" prop="tagPrice">
                        <el-input v-model="modalPage.model.tagPrice" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="6">
                    <el-form-item label="零售价" :label-width="modalPage.width" prop="retailPrice">
                        <el-input v-model="modalPage.model.retailPrice" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="6">
                    <el-form-item label="成本价" :label-width="modalPage.width" prop="costPrice">
                        <el-input v-model="modalPage.model.costPrice" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="6">
                    <el-form-item label="采购价" :label-width="modalPage.width" prop="purchasePrice">
                        <el-input v-model="modalPage.model.purchasePrice" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col span="8">
                    <el-form-item label="保质期管理" :label-width="modalPage.width" prop="isShelfLifeMgmt">
                        <el-select filterable clearable size="mini" v-model="modalPage.model.isShelfLifeMgmt" placeholder="请选择">
                            <el-option v-for="item in tag.dataWhetherList"
                                       :key="item.key"
                                       :label="item.value"
                                       :value="item.key">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col span="8">
                    <el-form-item label="批次管理" :label-width="modalPage.width" prop="isBatchMgmt">
                        <el-select filterable clearable size="mini" v-model="modalPage.model.isBatchMgmt" placeholder="请选择">
                            <el-option v-for="item in tag.dataWhetherList"
                                       :key="item.key"
                                       :label="item.value"
                                       :value="item.key">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col span="8">
                    <el-form-item label="SN管理" :label-width="modalPage.width" prop="isSNMgmt">
                        <el-select filterable clearable size="mini" v-model="modalPage.model.isSNMgmt" placeholder="请选择">
                            <el-option v-for="item in tag.dataWhetherList"
                                       :key="item.key"
                                       :label="item.value"
                                       :value="item.key">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row v-show="modalPage.model.isShelfLifeMgmt=='Y'">
                <el-col span="6">
                    <el-form-item label="保质期(H)" :label-width="modalPage.width" prop="shelfLife">
                        <el-input v-model="modalPage.model.tagPrice" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="6">
                    <el-form-item label="禁收天数" :label-width="modalPage.width" prop="rejectLifecycle">
                        <el-input v-model="modalPage.model.retailPrice" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="6">
                    <el-form-item label="禁售天数" :label-width="modalPage.width" prop="lockupLifecycle">
                        <el-input v-model="modalPage.model.costPrice" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="6">
                    <el-form-item label="临期预警天数" :label-width="modalPage.width" prop="adventLifecycle">
                        <el-input v-model="modalPage.model.purchasePrice" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col span="24">
                    <el-form-item label="备注" :label-width="modalPage.width" prop="remark">
                        <el-input v-model="modalPage.model.remark" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button v-on:click="modalPage.show=false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" v-on:click="saveStock()">确 定</el-button>
        </div>
    </el-dialog>
    <!-- 新增/修改客户公司对话框结束 -->

    <!-- 启用/删除对话框开始 -->
    <el-dialog :title="modalValidate.title" :mask-closable="false" :visible.sync="modalValidate.show">
        <el-table :data="selectRows" :columns="modalValidate.table.columns" size="mini">
            <el-table-column property="skuNo" label="商品条码"></el-table-column>
            <el-table-column property="skuName" label="商品名称"></el-table-column>
            <el-table-column property="customerName" label="所属客户"></el-table-column>
            <el-table-column property="createTime" label="创建时间"></el-table-column>
        </el-table>
        <div slot="footer" class="dialog-footer">
            <el-button v-on:click="modalValidate.show=false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" v-on:click="updateDataValidate()">确 定</el-button>
        </div>
    </el-dialog>
    <!-- 启用/删除对话框结束-->
</div>
<script src="/sku/sku.js"></script>
</@layout>