package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.AdminLog;
import com.zsyc.zt.b2b.entity.Role;
import com.zsyc.zt.b2b.entity.RolePermission;
import com.zsyc.zt.b2b.entity.User;
import com.zsyc.zt.b2b.vo.RoleVo;
import com.zsyc.zt.b2b.vo.UserVo;
import com.zsyc.zt.inca.entity.PubTranslineDef;

import java.util.List;

/**
 * Created by lcs on 2020/7/15.
 */
public interface AdminService {
    /**
     * 添加用户
     *
     * @param userVo
     * @return
     */
    User addUser(UserVo userVo);

    /**
     * 用户登录
     *
     * @param user
     * @return
     */
    User login(User user, String ip);

    /**
     * 更新用户信息
     *
     * @return
     */
    User updateUser(UserVo user);

    /**
     * 密码重置
     *
     * @return
     */
    User resetPassword(Long userId, String oldPassword, String newPassword);

    /**
     * 获取用户信息
     */
    UserVo getUserInfo(Long userId);

    /**
     * 所有管理员
     *
     * @param page
     * @param userVo
     * @return
     */
    IPage<UserVo> getUserList(Page page, UserVo userVo);

    /**
     * 重置密码
     *
     * @param id
     */
    void resetUserPassword(Long id);


    /**
     * 删除账户
     *
     * @param id
     */
    void delUser(Long id);

    /**
     * 后台管理员操作日志
     *
     * @param adminLog
     */
    void addAdminLog(AdminLog adminLog);

    /**
     * 修改对应erp账号id
     *
     * @param id
     * @param erpEmployeeId
     */
    void updateUserErpEmployeeId(Long id, Long erpEmployeeId);

    /**
     * 是否启用
     *
     * @param id
     * @param isUse
     */
    void userIsUse(Long id, String isUse);

    /**
     * 添加角色
     */
    void addRole(RoleVo roleVo);

    /**
     * 修改角色
     */
    void updateRole(RoleVo roleVo);

    /**
     * 角色列表
     *
     * @param page
     * @param roleVo
     * @return
     */
    IPage<RoleVo> getRoleList(Page page, RoleVo roleVo);

    /**
     * 角色列表不分页
     * @param roleVo
     * @return
     */
    List<RoleVo> getRoleAll(RoleVo roleVo);

    /**
     * 系统日志
     *
     * @param page
     * @return
     */
    IPage<AdminLog> getAdminLogList(Page page);

    /**
     * 获取所有的线路
     * @return
     */
    List<PubTranslineDef> selectAllTransline();
}
