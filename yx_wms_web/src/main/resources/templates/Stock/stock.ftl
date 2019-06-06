<#include "../Shared/Layout.ftl">
<@layout>

<div id="app">
    <!-- 按钮和快速搜索开始 -->
    <div class="nav-bar">
        <el-row :gutter="5" class="button-box">
            <el-col :span="20">
                <el-button-group>
                    <el-button type="primary" size="mini" icon="plus" v-on:click="editStock()">新增仓库</el-button>
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
                    <template slot="prepend">仓库名称</template>
                    <el-input slot="body" v-model="search.stockName" auto-complete="off"></el-input>
                </el-input-box>
            </el-col>
            <el-col :span="6">
                <el-input-box>
                    <template slot="prepend">仓库编码</template>
                    <el-input slot="body" v-model="search.stockCode" auto-complete="off"></el-input>
                </el-input-box>
            </el-col>
            <el-col :span="6">
                <el-input-box>
                    <template slot="prepend">仓库ID</template>
                    <el-input slot="body" v-model="search.stockId" auto-complete="off"></el-input>
                </el-input-box>
            </el-col>
        </el-row>
    </div>
    <!-- 按钮和快速搜索结束 -->

    <!-- 数据列表开始 -->
    <div id="grid" class="dynamic-height"></div>
    <!-- 数据列表结束 -->

    <!-- 新增/修改仓库对话框开始 -->
    <el-dialog :title="modalPage.title" :visible.sync="modalPage.show">
        <el-form :model="modalPage.model" ref="modalPage" size="mini" :rules="modalPage.validate" ref="modalPageForm">
            <el-row>
                <el-col :span="12">
                    <el-form-item label="所属公司" :label-width="modalPage.width" prop="companyId" class="is-required">
                        <el-select v-model="modalPage.model.companyId" filterable clearable>
                            <el-option v-for="item in tag.companyList"
                                       :key="item.companyId"
                                       :label="item.companyName"
                                       :value="item.companyId">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="仓库类型" :label-width="modalPage.width" prop="stockType" class="is-required">
                        <el-select v-model="modalPage.model.stockType" filterable clearable>
                            <el-option v-for="item in tag.stockTypeList"
                                       :key="item.key"
                                       :label="item.value"
                                       :value="item.key">
                            </el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="仓库名称" :label-width="modalPage.width" prop="stockName" class="is-required">
                        <el-input v-model="modalPage.model.stockName" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="仓库代码" :label-width="modalPage.width" prop="stockCode" class="is-required">
                        <el-input v-model="modalPage.model.stockCode" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col span="12">
                    <el-form-item label="发件人姓名" :label-width="modalPage.width" prop="senderName" class="is-required">
                        <el-input v-model="modalPage.model.senderName" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>

                <el-col span="12">
                    <el-form-item label="发件人邮编" :label-width="modalPage.width" prop="zipCode" class="is-required">
                        <el-input v-model="modalPage.model.zipCode" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col span="12">
                    <el-form-item label="发件人邮箱" :label-width="modalPage.width" prop="senderEmail" class="is-required">
                        <el-input v-model="modalPage.model.senderEmail" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="12">
                    <el-form-item label="发件人手机" :label-width="modalPage.width" prop="senderMobPhone" class="is-required">
                        <el-input v-model="modalPage.model.senderMobPhone" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col span="12">
                    <el-form-item label="发件人电话" :label-width="modalPage.width" prop="senderTelephone" class="is-required">
                        <el-input v-model="modalPage.model.senderTelephone" placeholder="请输入..."></el-input>
                    </el-form-item>
                </el-col>
                <el-col span="12">
                    <el-form-item label="发件人省市" prop="region" :label-width="modalPage.width" class="is-required">
                        <el-cascader :options="tag.regions" v-model="tag.regionVal" change-on-select filterable v-on:change="regionChange()" style="width:100%"></el-cascader>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col span="24">
                    <el-form-item label="发件人地址" :label-width="modalPage.width" prop="detailAddress" class="is-required">
                        <el-input v-model="modalPage.model.detailAddress" placeholder="请输入..."></el-input>
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

    <!-- 新增/修改客户与子公司对话框开始 -->
    <el-dialog :title="modalContact.title" :visible.sync="modalContact.show">
        <el-form :model="modalContact.modal">
            <el-table :data="modalContact.contactList" size="mini" border tooltip-effect="dark" style="max-height:400px">
                <el-table-column prop="checked" width="40">
                    <template scope="scope">
                        <el-checkbox v-model="scope.row.isChecked"></el-checkbox>
                    </template>
                </el-table-column>
                <el-table-column prop="customerId" width="200" label="客户公司ID"></el-table-column>
                <el-table-column prop="customerName" label="客户公司名称"></el-table-column>
            </el-table>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-row>
                <el-col :span="2">
                    <el-button v-on:click="selectAll()" size="mini">全选</el-button>
                </el-col>
                <el-col :span="22">
                    <el-button v-on:click="modalContact.show=false" size="mini">取 消</el-button>
                    <el-button type="primary" v-on:click="saveStockCustomerContact()" size="mini">确 定</el-button>
                </el-col>
            </el-row>
        </div>
    </el-dialog>
    <!-- 新增/修改客户与子公司对话框结束 -->

    <!-- 启用/删除对话框开始 -->
    <el-dialog :title="modalValidate.title" :mask-closable="false" :visible.sync="modalValidate.show">
        <el-table :data="selectRows" :columns="modalValidate.table.columns" size="mini">
            <el-table-column property="stockName" label="仓库名称"></el-table-column>
            <el-table-column property="companyName" label="所属公司"></el-table-column>
            <el-table-column property="createTime" label="创建时间"></el-table-column>
        </el-table>
        <div slot="footer" class="dialog-footer">
            <el-button v-on:click="modalValidate.show=false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" v-on:click="updateDataValidate()">确 定</el-button>
        </div>
    </el-dialog>
    <!-- 启用/删除对话框结束-->
</div>
<script src="/stock/stock.js"></script>
</@layout>