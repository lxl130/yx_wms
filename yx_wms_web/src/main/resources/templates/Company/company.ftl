<#include "../Shared/Layout.ftl">
<@layout>

<div id="app">
    <!-- 按钮和快速搜索开始 -->
    <div class="nav-bar">
        <el-row :gutter="5" class="button-box">
            <el-col :span="20">
                <el-button-group>
                    <el-button type="primary" size="mini" icon="plus" v-on:click="addCompany()">新增公司</el-button>
                    <el-button type="primary" size="mini" icon="plus" v-on:click="addCompanyAdmin()">新增公司管理员</el-button>
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
    </div>
    <!-- 按钮和快速搜索结束 -->
    <!-- 数据列表开始 -->
    <div id="grid" class="dynamic-height"></div>
    <!-- 数据列表结束 -->
    <!-- 新增/修改公司对话框开始 -->
    <el-dialog :title="modalPage.title" :visible.sync="modalPage.show">
        <el-form :model="modalPage.model" :rules="modalPage.modelRule" ref="modalPage" size="mini">
            <el-row>
                <el-col :span="12">
                    <el-form-item label="公司名称" :label-width="modalPage.width" prop="companyName" class="is-required">
                        <el-input v-model="modalPage.model.companyName" auto-complete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="公司邮箱" :label-width="modalPage.width" prop="companyEmail" class="is-required">
                        <el-input v-model="modalPage.model.companyEmail" auto-complete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="公司全称" :label-width="modalPage.width" prop="companyFullName">
                        <el-input v-model="modalPage.model.companyFullName" auto-complete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="公司编号" :label-width="modalPage.width" prop="companyCode">
                        <el-input v-model="modalPage.model.companyCode" auto-complete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button v-on:click="modalPage.show=false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" v-on:click="saveCompany('modalPage')">确 定</el-button>
        </div>
    </el-dialog>
    <!-- 新增/修改公司对话框结束 -->

    <!-- 新增/修改公司管理员对话框开始 -->
    <el-dialog :title="modelUserAdmin.title" :visible.sync="modelUserAdmin.show">
        <el-form :model="modelUserAdmin.model" :rules="modelUserAdmin.modelRule" ref="modelUserAdmin" size="mini">
            <el-row>
                @*<el-col :span="12">
                <el-form-item label="电话号码" :label-width="modelUserAdmin.width" prop="phone">
                    <el-input v-model="modelUserAdmin.model.userNo" auto-complete="off"></el-input>
                </el-form-item>
            </el-col>*@
                <el-col :span="12">
                    <el-form-item label="电话号码" :label-width="modelUserAdmin.width" prop="phone" class="is-required">
                        <el-input v-model="modelUserAdmin.model.phone" auto-complete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item id="userPass" label="密码" :label-width="modelUserAdmin.width" v-show="userShow">
                        <el-input :disabled="true" v-model="modelUserAdmin.model.passWord" auto-complete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="真实姓名" :label-width="modelUserAdmin.width" prop="realName">
                        <el-input v-model="modelUserAdmin.model.realName" auto-complete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="用户类型" :label-width="modelUserAdmin.width">
                        <el-input :disabled="true" v-model="modelUserAdmin.model.realName" auto-complete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="用户邮箱" :label-width="modelUserAdmin.width" prop="email">
                        <el-input v-model="modelUserAdmin.model.email" auto-complete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="关联QQ" :label-width="modelUserAdmin.width" prop="qq">
                        <el-input v-model="modelUserAdmin.model.qq" auto-complete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="座机号码" :label-width="modelUserAdmin.width" prop="telPhone">
                        <el-input v-model="modelUserAdmin.model.telPhone" auto-complete="off"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="入职日期" :label-width="modelUserAdmin.width" prop="entryDate">
                        <el-date-picker v-model="modelUserAdmin.model.entryDate" type="datetime" editable="true" placeholder="选择日期" auto-complete="off" style="width: 100%">
                        </el-date-picker>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row>
                <el-col :span="12">
                    <el-form-item label="身份证号" :label-width="modelUserAdmin.width" prop="PIN">
                        <el-input v-model="modelUserAdmin.model.PIN" auto-complete="off"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button v-on:click="modelUserAdmin.show=false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" v-on:click="saveUserAdmin('modelUserAdmin')">确 定</el-button>
        </div>
    </el-dialog>
    <!-- 新增/修改公司管理员对话框关闭 -->

    <!-- 启用 删除 对话框 开始 -->
    <el-dialog :title="modalValidate.title" :mask-closable="false" :visible.sync="modalValidate.show">
        <el-table :data="selectRows" :columns="modalValidate.table.columns" size="mini">
            <el-table-column property="companyName" label="公司名称"></el-table-column>
            <el-table-column property="createUserName" label="创建人"></el-table-column>
            <el-table-column property="createTime" label="创建时间"></el-table-column>
        </el-table>
        <div slot="footer" class="dialog-footer">
            <el-button v-on:click="modalValidate.show=false" size="mini">取 消</el-button>
            <el-button type="primary" size="mini" v-on:click="updateDataValidate()">确 定</el-button>
        </div>
    </el-dialog>
    <!--启用 删除 对话框 开始-->
</div>
<script src="/company/company.js"></script>
</@layout>