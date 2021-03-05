package com.zsyc.zt.b2b.api.controller.admin;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.api.AccountHelper;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.service.*;
import com.zsyc.zt.b2b.vo.RoleVo;
import com.zsyc.zt.b2b.vo.UserVo;
import com.zsyc.zt.inca.entity.PubTranslineDef;
import io.swagger.annotations.*;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Api
@RestController
@RequestMapping("api/user")
public class AdminUserController {
    @Autowired
    private AccountHelper accountHelper;

    @Reference
    private AdminService adminService;

    @ApiOperation("当前登录用户")
    @GetMapping("getUserInfo")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
    })
    public UserVo getUserInfo(Long id) {
        return this.adminService.getUserInfo(id != null ? id : this.accountHelper.getUserId());
    }

    @ApiOperation("修改对应erp账号id")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "erpEmployeeId", value = "erp账号id", required = true, dataType = "Long"),
    })
    @GetMapping("updateUserErpEmployeeId")
    public void updateUserErpEmployeeId(Long id, Long erpEmployeeId, HttpServletRequest request) {
        this.adminService.updateUserErpEmployeeId(id, erpEmployeeId);
        this.adminService.addAdminLog(new AdminLog().setContent("修改对应erp账号id-->id：" + id).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminUserController&updateUserErpEmployeeId"));
    }


    @ApiOperation("添加账号")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @PostMapping(value = "addUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "erpEmployeeId", value = "erp账号id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "employeeNo", value = "工号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String"),
            @ApiImplicitParam(name = "telephone", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rePassword", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userVo.roleList.id", value = "角色id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userVo.roleList.roleName", value = "角色名称", required = true, dataType = "String"),
    })
    public void addUser(@RequestBody UserVo userVo, HttpServletRequest request) {
        userVo.setUserId(this.accountHelper.getUserId());
        this.adminService.addUser(userVo);
        this.adminService.addAdminLog(new AdminLog().setContent("添加账号-->账号：" + userVo.getUserName()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminUserController&addUser"));
    }

    @ApiOperation("修改账号信息")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @PostMapping(value = "updateUser")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "userName", value = "账号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "erpEmployeeId", value = "erp账号id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "employeeNo", value = "工号", required = true, dataType = "String"),
            @ApiImplicitParam(name = "email", value = "邮箱", dataType = "String"),
            @ApiImplicitParam(name = "telephone", value = "手机号", dataType = "String"),
            @ApiImplicitParam(name = "userVo.roleList.id", value = "角色id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "userVo.roleList.roleName", value = "角色名称", required = true, dataType = "String"),
    })
    public void updateUser(@RequestBody UserVo userVo, HttpServletRequest request) {
        userVo.setUserId(this.accountHelper.getUserId());
        this.adminService.updateUser(userVo);
        this.adminService.addAdminLog(new AdminLog().setContent("修改账号信息-->id：" + userVo.getId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminUserController&updateUser"));
    }


    @ApiOperation("修改密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "账号id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "oldPassword", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "newPassword", value = "密码", required = true, dataType = "String"),
    })
    @GetMapping("resetPassword")
    public void resetPassword(Long userId, String oldPassword, String newPassword, HttpServletRequest request) {
        this.adminService.resetPassword(userId, oldPassword, newPassword);
        this.adminService.addAdminLog(new AdminLog().setContent("修改密码-->id：" + userId).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminUserController&resetPassword"));
    }

    @ApiOperation("修改个人密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "oldPassword", value = "旧密码", required = true, dataType = "String")
    })
    @GetMapping("resetAdminPassword")
    public String resetAdminPassword(String newPassword, String oldPassword, HttpServletRequest request) {
        this.adminService.resetPassword(this.accountHelper.getUserId(), oldPassword, newPassword);
        this.adminService.addAdminLog(new AdminLog().setContent("修改密码-->id：" + this.accountHelper.getUserId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminUserController&resetAdminPassword"));
        return "";
    }

    @ApiOperation("账户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "userName", value = "名称", dataType = "Long"),
            @ApiImplicitParam(name = "role", value = "角色", dataType = "Long"),
            @ApiImplicitParam(name = "employeeName", value = "erp名称", dataType = "Long"),
            @ApiImplicitParam(name = "erpEmployeeId", value = "erpEmployeeId", dataType = "Long"),
    })
    @GetMapping(value = "getUserList")
    public IPage<UserVo> getUserList(Page page,UserVo userVo) {
        return this.adminService.getUserList(page, userVo);
    }

    @ApiOperation("重置密码-默认88888888")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
    })
    @GetMapping("resetUserPassword")
    public void resetUserPassword(Long id, HttpServletRequest request) {
        this.adminService.resetUserPassword(id);
        this.adminService.addAdminLog(new AdminLog().setContent("重置密码-默认88888888-->id：" + id).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminUserController&resetPassword"));
    }

    @ApiOperation("删除账户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
    })
    @GetMapping("delUser")
    public void delUser(Long id, HttpServletRequest request) {
        this.adminService.delUser(id);
        this.adminService.addAdminLog(new AdminLog().setContent("删除账户-->id：" + id).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminUserController&delUser"));
    }


    @ApiOperation("设置账号是否启用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "isUse", value = "是否启用", required = true, dataType = "String"),
    })
    @GetMapping("userIsUse")
    public void userIsUse(Long id, String isUse, HttpServletRequest request) {
        this.adminService.userIsUse(id, isUse);
        this.adminService.addAdminLog(new AdminLog().setContent("设置账号是否启用-->id：" + id).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminUserController&userIsUse"));
    }

    @ApiOperation("添加角色")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @PostMapping(value = "addRole")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rolePy", value = "角色名称拼音", required = true, dataType = "String"),
            @ApiImplicitParam(name = "roleVo.rolePermissionList.permissionPath", value = "权限路径", required = true, dataType = "String"),
            @ApiImplicitParam(name = "roleVo.rolePermissionList.permissionName", value = "权限名称", required = true, dataType = "String"),
    })
    public void addRole(@RequestBody RoleVo roleVo, HttpServletRequest request) {
        this.adminService.addRole(roleVo);
        this.adminService.addAdminLog(new AdminLog().setContent("添加角色-->角色：" + roleVo.getRoleName()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminUserController&addRole"));
    }

    @ApiOperation("修改角色")
    @ApiResponse(code = 200, message = "success", response = IPage.class)
    @PostMapping(value = "updateRole")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "roleName", value = "角色名称", required = true, dataType = "String"),
            @ApiImplicitParam(name = "rolePy", value = "角色名称拼音", required = true, dataType = "String"),
            @ApiImplicitParam(name = "roleVo.rolePermissionList.permissionPath", value = "权限路径", required = true, dataType = "String"),
            @ApiImplicitParam(name = "roleVo.rolePermissionList.permissionName", value = "权限名称", required = true, dataType = "String"),
    })
    public void updateRole(@RequestBody RoleVo roleVo, HttpServletRequest request) {
        this.adminService.updateRole(roleVo);
        this.adminService.addAdminLog(new AdminLog().setContent("修改角色-->id：" + roleVo.getId()).setIp(this.accountHelper.getIP(request)).setUserId(this.accountHelper.getUserId())
                .setUserName(this.accountHelper.getUserName()).setUrl("AdminUserController&updateRole"));
    }

    @ApiOperation("角色列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "roleName", value = "角色", dataType = "Long"),
    })
    @GetMapping(value = "getRoleList")
    public IPage<RoleVo> getRoleList(Page page, RoleVo roleVo) {
        return this.adminService.getRoleList(page, roleVo);
    }

    @ApiOperation("角色列表不分页")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleName", value = "角色", dataType = "Long"),
    })
    @GetMapping(value = "getRoleAll")
    public  List<RoleVo> getRoleAll(RoleVo roleVo){
        return this.adminService.getRoleAll(roleVo);
    }

    @ApiOperation("系统日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "current", value = "第几页", required = true, dataType = "Long"),
            @ApiImplicitParam(name = "size", value = "一页几条", required = true, dataType = "Long"),
    })
    @GetMapping(value = "getAdminLogList")
    public IPage<AdminLog> getAdminLogList(Page page) {
        return this.adminService.getAdminLogList(page);
    }

    @ApiOperation("获取所有的线路")
    @GetMapping(value = "selectAllTransline")
    public List<PubTranslineDef> selectAllTransline(){
        return this.adminService.selectAllTransline();
    }
}
