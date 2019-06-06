<#include "../Shared/Layout.ftl">
<@layout>

<div id="app">
    <!-- 按钮和快速搜索开始 -->
    <div class="nav-bar">
        <el-row :gutter="5" class="button-box">
            <el-col :span="20">
                <el-button-group>
                    <el-button type="primary" size="mini" icon="plus" v-on:click="editCustomer()">新增客户</el-button>
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
                    <template slot="prepend">客户名称</template>
                    <el-input slot="body" v-model="search.customerName" auto-complete="off"></el-input>
                </el-input-box>
            </el-col>
            <el-col :span="6">
                <el-input-box>
                    <template slot="prepend">客户编码</template>
                    <el-input slot="body" v-model="search.customerCode" auto-complete="off"></el-input>
                </el-input-box>
            </el-col>
            <el-col :span="6">
                <el-input-box>
                    <template slot="prepend">客户ID</template>
                    <el-input slot="body" v-model="search.customerId" auto-complete="off"></el-input>
                </el-input-box>
            </el-col>
        </el-row>
    </div>
    <!-- 按钮和快速搜索结束 -->

    <!-- 数据列表开始 -->
    <div id="grid" class="dynamic-height"></div>
    <!-- 数据列表结束 -->

    <!-- 新增/修改客户公司对话框开始 -->
    <el-dialog :title="modalPage.title" :visible.sync="modalPage.show">
        <el-form :model="modalPage.model" ref="modalPage" size="mini">
            <el-row>
                <el-col :span="12">
                    <el-form-item label="公司名称" :label-width="modalPage.width" prop="customerName" class="is-required">
                        <el-input v-model="modalPage.model.customerName" auto-complete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="公司代码" :label-width="modalPage.width" prop="customerCode" class="is-required">
                        <el-input v-model="modalPage.model.customerCode" auto-complete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="公司全称" :label-width="modalPage.width" prop="customerFullName" class="is-required">
                        <el-input v-model="modalPage.model.customerFullName" auto-complete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button v-on:click="modalPage.show=false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" v-on:click="saveCustomer()">确 定</el-button>
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
                <el-table-column prop="companyId" width="200" label="子公司ID"></el-table-column>
                <el-table-column prop="companyName" label="子公司名称"></el-table-column>
            </el-table>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-row>
                <el-col :span="2">
                    <el-button v-on:click="selectAll()" size="mini">全选</el-button>
                </el-col>
                <el-col :span="22">
                    <el-button v-on:click="modalContact.show=false" size="mini">取 消</el-button>
                    <el-button type="primary" v-on:click="saveCompanyContact()" size="mini">确 定</el-button>
                </el-col>
            </el-row>
        </div>
    </el-dialog>
    <!-- 新增/修改客户与子公司对话框结束 -->

    <!-- 启用/删除对话框开始 -->
    <el-dialog :title="modalValidate.title" :mask-closable="false" :visible.sync="modalValidate.show">
        <el-table :data="selectRows" :columns="modalValidate.table.columns" size="mini">
            <el-table-column property="customerName" label="公司名称"></el-table-column>
            <el-table-column property="createUserName" label="创建人"></el-table-column>
            <el-table-column property="createTime" label="创建时间"></el-table-column>
        </el-table>
        <div slot="footer" class="dialog-footer">
            <el-button v-on:click="modalValidate.show=false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" v-on:click="updateDataValidate()">确 定</el-button>
        </div>
    </el-dialog>
    <!-- 启用/删除对话框结束-->
</div>
<script src="/company/customer.js"></script>
</@layout>