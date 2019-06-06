<#include "../Shared/Layout.ftl">
<@layout>

<div id="app">
    <!-- 按钮和快速搜索开始 -->
    <div class="nav-bar" style="padding: .5em">
        <el-row :gutter="5">
            <el-col :span="14">
                <el-button-group>
                    <el-button type="primary" v-if="multiselect?'':'disabled=true'" size="mini" icon="el-icon-circle-plus" v-on:click="openModal()">新增用户</el-button>
                </el-button-group>
                <el-button-group>
                    <el-button type="primary" v-if="multiselect?'':'disabled=true'" size="mini" icon="el-icon-setting" v-on:click="onSetCustomer()">设置客户</el-button>
                    <el-button type="primary" v-if="multiselect?'':'disabled=true'" size="mini" icon="el-icon-setting" v-on:click="onSetStock()">设置仓库</el-button>
                    <el-button type="primary" v-if="multiselect?'':'disabled=true'" size="mini" icon="el-icon-setting" v-on:click="onMenuPermissions()">设置菜单权限</el-button>
                </el-button-group>
                <el-button-group>
                    <el-button type="primary" size="mini" icon="check" v-on:click="onOperationUserValidate('/User/ResetUserPwd','重置密码')">重置密码</el-button>
                    <el-button type="danger" size="mini" icon="el-icon-delete" v-on:click="onOperationUserValidate('/User/DeleteUser','重置密码')">删除</el-button>
                </el-button-group>
            </el-col>
            <el-col :span="5">
                <el-input-box>
                    <template slot="prepend">
                        所属公司
                    </template>
                    <el-select slot="body" size="mini" v-model="search.orgCompanyId" filterable clearable placeholder="请选择">
                        <el-option v-for="item in tag.companys" :label="item.orgCompanyName" :value="item.orgCompanyId" />
                    </el-select>
                </el-input-box>
            </el-col>
            <el-col :span="5">
                <el-input placeholder="请输入内容" size="mini" v-model="search.p_sss.value" style="width: 100%">
                    <el-select v-model="search.p_sss.text" slot="prepend" placeholder="请选择" style="min-width: 100px">
                        <el-option label="真实姓名" value="realName"></el-option>
                        <el-option label="手机号码" value="phone"></el-option>
                    </el-select>
                    <el-button slot="append" v-on:click="onSearch()">搜索</el-button>
                </el-input>
            </el-col>
        </el-row>
    </div>
    <!-- 按钮和快速搜索结束 -->

    <!-- 数据列表开始 -->
    <div id="grid" class="dynamic-height"></div>
    <!-- 数据列表结束 -->

    <!--设置菜单权限开始-->
    <el-dialog :title="modalMenuPermissions.title" :visible.sync="modalMenuPermissions.show" size="mini" :before-close="handleClose">
        <el-tree style=" height: 400px; overflow: auto;border:solid 1px #ebeef5" :data="modalMenuPermissions.dataOrigin" show-checkbox node-key="menuId" show-checkbox="true" :default-expanded-keys="modalMenuPermissions.dataFile" :default-checked-keys="modalMenuPermissions.dataFile" :props="modalMenuPermissions.defaultProps" ref="menuPermissions">
        </el-tree>
        <div slot="footer" class="dialog-footer">
            <el-row>
                <el-col :span="2">
                    <el-button v-on:click="setMenuPermissionsCheckAll()" size="mini">全选</el-button>
                </el-col>
                <el-col :span="22">
                    <el-button v-on:click="modalMenuPermissions.show = false" size="mini">取 消</el-button>
                    <el-button type="primary" v-on:click="onSetMenuPermissions()" size="mini">确 定</el-button>
                </el-col>
            </el-row>
        </div>
    </el-dialog>
    <!--设置菜单权限结束-->

    <!--设置客户开始-->
    <el-dialog :title="modalCustomer.title" :visible.sync="modalCustomer.show">
        <el-form :model="modalCustomer.dataFile" :rules="modalPage.modelRule">
            <el-table ref="SettingsProvider2" :data="modalCustomer.dataFile" size="mini" border tooltip-effect="dark" style="width: 100%; max-height: 400px;overflow: auto" v-on:selection-change="handleSelectionChange()">
                <el-table-column prop="checked" width="40">
                    <template scope="scope">
                        <el-checkbox v-model="scope.row.isChecked"></el-checkbox>
                    </template>
                </el-table-column>
                <el-table-column prop="orgUnitNumber" label="客户编码">
                </el-table-column>
                <el-table-column prop="companyName" label="客户名称">
                </el-table-column>
            </el-table>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-row>
                <el-col :span="2">
                    <el-button v-on:click="onAll()" size="mini">全选</el-button>
                </el-col>
                <el-col :span="22">
                    <el-button v-on:click="modalCustomer.show = false" size="mini">取 消</el-button>
                    <el-button type="primary" v-on:click="onSettingCustomer()" size="mini">确 定</el-button>
                </el-col>
            </el-row>
        </div>
    </el-dialog>
    <!--设置客户结束-->

    <!--设置仓库开始-->
    <el-dialog :title="modalStock.title" :visible.sync="modalStock.show">
        <el-form :model="modalStock.dataFile" :rules="modalPage.modelRule">
            <el-table ref="SettingsProvider2" :data="modalStock.dataFile" size="mini" border tooltip-effect="dark" style="width: 100%; max-height: 400px;overflow: auto" v-on:selection-change="handleSelectionChange()">
                <el-table-column prop="checked" width="40">
                    <template scope="scope">
                        <el-checkbox v-model="scope.row.isChecked"></el-checkbox>
                    </template>
                </el-table-column>
                <el-table-column prop="stockName" label="仓库名称">
                </el-table-column>
                <el-table-column prop="stockCode" label="仓库编码">
                </el-table-column>
            </el-table>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-row>
                <el-col :span="2">
                    <el-button v-on:click="onAllStock()" size="mini">全选</el-button>
                </el-col>
                <el-col :span="22">
                    <el-button v-on:click="modalStock.show = false" size="mini">取 消</el-button>
                    <el-button type="primary" v-on:click="onSettingStock()" size="mini">确 定</el-button>
                </el-col>
            </el-row>
        </div>
    </el-dialog>
    <!--设置仓库结束-->

    <!--启用/删除对话框开始-->
    <el-dialog :title="modalAlert.title" :mask-closable="false" :visible.sync="modalAlert.show">
        <el-table :data="selectRows" :columns="modalAlert.table.columns" size="mini">
            <el-table-column property="phone" label="手机号码"></el-table-column>
            <el-table-column property="realName" label="真实姓名"></el-table-column>
        </el-table>
        <div slot="footer" class="dialog-footer">
            <el-button v-on:click="modalAlert.show = false" size="mini">取 消</el-button>
            <el-button type="primary" v-on:click="onOpp()" size="mini">确 定</el-button>
        </div>
    </el-dialog>
    <!--启用/删除对话框开始-->

    <!-- 新增/修改用户对话框开始 -->
    <el-dialog :title="modalPage.title" :visible.sync="modalPage.show" :close-on-click-modal="false">
        <el-form label-position="right" label-width="100px":model="modalPage.model" :rules="modalPage.modelRule" ref="modalPage" size="mini">
            <el-tabs v-model="tag.activeName">
                <el-tab-pane label="用户管理" name="second">
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="手机号码"  prop="phone" class="is-required">
                                <el-input v-model="modalPage.model.phone" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="密码">
                                <el-input :disabled="true" v-model="modalPage.model.passWord" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="真实姓名" :label-width="modalPage.width" prop="realName">
                                <el-input v-model="modalPage.model.realName" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="用户类型" :label-width="modalPage.width">
                                <el-input :disabled="true" v-model="typeName" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="性别" :label-width="modalPage.width">
                                <el-select v-model="modalPage.model.sex" style="width: 100%">
                                    <el-option v-for="itm in tag.GetUserSexList" :label="itm.Text" :value="Number(itm.Value)"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="状态" :label-width="modalPage.width">
                                <el-select v-model="modalPage.model.userValidate" filterable style="width: 100%">
                                    <el-option v-for="itm in tag.GetUserValidateList" :label="itm.Text" :value="Number(itm.Value)"></el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item :label-width="modalPage.width" label="所属公司" prop="companyType">
                                <el-select v-model="modalPage.model.orgCompanyId" filterable placeholder="请选择" v-on:change="onDeptList(modalPage.model.orgCompanyId)" style="width: 100%">
                                    <el-option v-for="item in tag.companys" :key="item.orgCompanyId" :label="item.orgCompanyName" :value="item.orgCompanyId">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item :label-width="modalPage.width" label="所属部门" prop="orgDeptId">
                                <el-select v-model="modalPage.model.orgDeptId" filterable placeholder="请选择" v-on:change="onJobList(modalPage.model.orgDeptId)" style="width: 100%">
                                    <el-option v-for="item in tag.depts" :key="item.Id" :label="item.orgUnitName" :value="item.Id">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>

                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item :label-width="modalPage.width" label="所属岗位" prop="orgJobId">
                                <el-select v-model="modalPage.model.orgJobId" filterable placeholder="请选择" style="width: 100%">
                                    <el-option v-for="item in tag.jobs" :key="item.Id" :label="item.orgUnitName" :value="item.Id">
                                    </el-option>
                                </el-select>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="用户邮箱" :label-width="modalPage.width" prop="email">
                                <el-input v-model="modalPage.model.email" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>

                    </el-row>
                    <el-row>
                        <el-col :span="12">
                            <el-form-item label="关联QQ" :label-width="modalPage.width" prop="qq">
                                <el-input v-model="modalPage.model.qq" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>
                        <el-col :span="12">
                            <el-form-item label="座机号码" :label-width="modalPage.width" prop="telPhone">
                                <el-input v-model="modalPage.model.telPhone" auto-complete="off"></el-input>
                            </el-form-item>
                        </el-col>

                    </el-row>
                    <el-row>

                        <el-col :span="12">
                            <el-form-item label="入职日期" :label-width="modalPage.width" prop="entryDate">
                                <el-date-picker v-model="modalPage.model.entryDate" type="datetime" editable="true" placeholder="选择日期" auto-complete="off" style="width: 100%">
                                </el-date-picker>

                            </el-form-item>
                        </el-col>
                    </el-row>
                    <el-row>
                        <el-form-item label="身份证号" :label-width="modalPage.width" prop="PIN">
                            <el-input v-model="modalPage.model.PIN" auto-complete="off"></el-input>
                        </el-form-item>
                    </el-row>

                </el-tab-pane>
            </el-tabs>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button v-on:click="onDown()" size="mini">取 消</el-button>
            <el-button type="primary" v-on:click="onSave()"  size="mini">确 定</el-button>
        </div>
    </el-dialog>
    <!-- 新增/修改用户对话框结束 -->
</div>
<script src="/user/user.js"></script>
</@layout>