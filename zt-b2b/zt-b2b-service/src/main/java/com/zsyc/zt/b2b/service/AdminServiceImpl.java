package com.zsyc.zt.b2b.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.b2b.common.Constant;
import com.zsyc.zt.b2b.entity.*;
import com.zsyc.zt.b2b.mapper.*;
import com.zsyc.zt.b2b.vo.RoleVo;
import com.zsyc.zt.b2b.vo.UserVo;
import com.zsyc.zt.inca.entity.PubTranslineDef;
import com.zsyc.zt.inca.service.CustomService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by lcs on 2020/7/15.
 */
@Service
@Transactional
@Slf4j
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserMapper userMapper;
    @Reference
    private OauthService oauthService;
    @Autowired
    private AdminLogMapper adminLogMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Reference(version = Constant.DUBBO_VERSION.INCA)
    private CustomService customService;

    @Override
    public User addUser(UserVo userVo) {
        AssertExt.notBlank(userVo.getUserName(), "账号为空");
        AssertExt.notBlank(userVo.getPassword(), "密码为空");
        AssertExt.isTrue(userVo.getPassword().equals(userVo.getRePassword()), "两次密码不一致");
        AssertExt.matches("^[A-Za-z0-9][A-Za-z0-9_\\-]{3,20}[A-Za-z0-9]$", userVo.getUserName(),
                "账号是数字、字母、下划线、中横线(不能以下划线、中横线开头结尾)；长度5到20位");
        AssertExt.matches(".{6,20}", userVo.getPassword(), "密码长度6到20位");
        AssertExt.notNull(userVo.getRoleList(), "角色为空");
        AssertExt.hasId(userVo.getErpEmployeeId(),"erp编号id为空");
        User userDB = this.userMapper.selectOne(new QueryWrapper<User>().eq("user_name", userVo.getUserName()));

        AssertExt.isTrue(userDB == null, "账号[%s] 已存在", userVo.getUserName());

        this.customService.validEmployee(userVo.getErpEmployeeId());

        User userErpEmployeeId = this.userMapper.selectOne(new QueryWrapper<User>().eq("ERP_EMPLOYEE_ID", userVo.getErpEmployeeId()));

        AssertExt.isTrue(userErpEmployeeId == null, "erp编号id [%s] 已存在", userVo.getErpEmployeeId());

        User user = new User();
        BeanUtils.copyProperties(userVo, user);

        user.setSalt(RandomStringUtils.random(10, true, true));
        user.setPassword(DigestUtils.sha1Hex(userVo.getPassword() + user.getSalt()));
        user.setId(null);
        user.setCreateTime(LocalDateTime.now());
        user.setIsDel(Constant.IS_DEL.NO);
        this.userMapper.insert(user);
        String roleName = "";
        for (Role role : userVo.getRoleList()) {
            UserRole userRole = new UserRole();
            userRole.setCreateUserId(userVo.getUserId());
            userRole.setSysRoleId(role.getId());
            userRole.setUserInfoId(user.getId());
            userRole.setCreateTime(LocalDateTime.now());
            this.userRoleMapper.insert(userRole);
            roleName += role.getRoleName()+",";
        }
        user.setRole(roleName);
        this.userMapper.updateById(user);
        return user;
    }

    @Override
    public User login(User user, String ip) {
        AssertExt.notBlank(user.getUserName(), "账号为空");
        AssertExt.notBlank(user.getPassword(), "密码为空");

        User userDB = this.userMapper.selectOne(new QueryWrapper<User>().eq("user_name", user.getUserName()).eq("IS_DEL", Constant.IS_DEL.NO));
        AssertExt.notNull(userDB, "账号[%s] 不存在", user.getUserName());

        AssertExt.hasId(userDB.getErpEmployeeId(),"erp编号id为空，请联系管理员设置");

        boolean check = DigestUtils.sha1Hex(user.getPassword() + userDB.getSalt()).equals(userDB.getPassword());
        AssertExt.isTrue(check, "密码错误");
        AssertExt.isTrue(userDB.getIsUse().equals(User.EIsUse.ON.val()), "该帐号已被禁用");

        this.addAdminLog(new AdminLog().setContent("后台账号登录").setIp(ip)
                .setUserName(userDB.getUserName()).setUrl("AdminUserController&resetPassword"));

        userDB.setEndTime(LocalDateTime.now());
        this.userMapper.updateById(userDB);

        userDB.setPassword(null);
        userDB.setSalt(null);

        return userDB;
    }

    @Override
    public User updateUser(UserVo user) {
        AssertExt.hasId(user.getId(), "userId为空");
        User userDB = this.userMapper.selectById(user.getId());
        AssertExt.notNull(userDB, "账号id[%s] 不存在", user.getId());

        User userDB1 = this.userMapper.selectOne(new QueryWrapper<User>().ne("id",user.getId()).eq("user_name", userDB.getUserName()));
        AssertExt.isTrue(userDB1 == null, "账号[%s] 已存在", userDB.getUserName());

        BeanUtils.copyProperties(user, userDB);

        this.userRoleMapper.delete(new QueryWrapper<UserRole>().eq("USER_INFO_ID", userDB.getId()));

        String roleName = "";
        for (Role role : user.getRoleList()) {
            UserRole userRole = new UserRole();
            userRole.setCreateUserId(user.getUserId());
            userRole.setSysRoleId(role.getId());
            userRole.setUserInfoId(user.getId());
            userRole.setCreateTime(LocalDateTime.now());
            userRole.setId(null);
            this.userRoleMapper.insert(userRole);
            roleName += role.getRoleName()+",";
        }
        userDB.setRole(roleName);
        this.userMapper.updateById(userDB);


        return userDB;
    }

    @Override
    public User resetPassword(Long userId, String oldPassword, String newPassword) {
        AssertExt.hasId(userId, "userId为空");
        //AssertExt.notBlank(oldPassword, "原密码为空");
        AssertExt.notBlank(newPassword, "新密码为空");

        AssertExt.matches(".{6,20}", newPassword, "密码长度8到20位");
        User userDB = this.userMapper.selectById(userId);
        AssertExt.notNull(userDB, "账号id[%s] 不存在", userId);

      /*  boolean check = DigestUtils.sha1Hex(oldPassword + userDB.getSalt()).equals(userDB.getPassword());
        AssertExt.isTrue(check, "原密码错误");*/

        userDB.setPassword(DigestUtils.sha1Hex(newPassword + userDB.getSalt()));
        this.userMapper.updateById(userDB);
        this.oauthService.kickOutUser(userDB.getUserName());
        return userDB;
    }

    @Override
    public UserVo getUserInfo(Long userId) {
        AssertExt.hasId(userId, "id为空");
        User userDB = this.userMapper.selectById(userId);
        AssertExt.notNull(userDB, "不是后台管理人员[%s]", userId);
        userDB.setPassword(null);
        userDB.setSalt(null);
        UserVo user = new UserVo();
        BeanUtils.copyProperties(userDB, user);
        List<RoleVo> roleVoList = this.roleMapper.getRoleListByUserId(userId);
        user.setRoleList(roleVoList);

        List<Long> longList = roleVoList.stream().map(roleVo -> {
            return roleVo.getId();
        }).collect(Collectors.toList());
        if (longList.size() > 0) {
            user.setRolePermissionList(this.rolePermissionMapper.selectList(new QueryWrapper<RolePermission>().in("SYS_ROLE_ID", longList)));
        }

        return user;
    }

    @Override
    public IPage<UserVo> getUserList(Page page, UserVo userVo) {

        IPage<UserVo> userIPage= this.userMapper.getUserList(page,userVo);
        userIPage.getRecords().forEach(user -> {
            user.setRoleList(this.roleMapper.getRoleListByUserId(user.getId()));
        });
        return userIPage;
    }

    @Override
    public void resetUserPassword(Long id) {
        AssertExt.hasId(id, "id为空");
        User userDB = this.userMapper.selectById(id);
        AssertExt.notNull(userDB, "无效id[%s]", id);
        userDB.setPassword(DigestUtils.sha1Hex("88888888" + userDB.getSalt()));
        this.userMapper.updateById(userDB);
        this.oauthService.kickOutUser(userDB.getUserName());
    }

    @Override
    public void delUser(Long id) {
        AssertExt.hasId(id, "id为空");
        User userDB = this.userMapper.selectById(id);
        AssertExt.notNull(userDB, "无效id[%s]", id);
        userDB.setIsDel(Constant.IS_DEL.YES);
        this.userMapper.updateById(userDB);
    }

    @Override
    public void addAdminLog(AdminLog adminLog) {
        adminLog.setCreateTime(LocalDateTime.now());
        this.adminLogMapper.insert(adminLog);
    }

    @Override
    public void updateUserErpEmployeeId(Long id, Long erpEmployeeId) {
        AssertExt.hasId(id, "id为空");
        AssertExt.hasId(erpEmployeeId, "erpEmployeeId为空");
        User userDB = this.userMapper.selectById(id);
        AssertExt.notNull(userDB, "无效id[%s]", id);
        userDB.setErpEmployeeId(erpEmployeeId);
        this.userMapper.updateById(userDB);
    }

    @Override
    public void userIsUse(Long id, String isUse) {
        AssertExt.hasId(id, "id为空");
        AssertExt.notNull(isUse, "是否启用为空");
        User userDB = this.userMapper.selectById(id);
        AssertExt.notNull(userDB, "无效id[%s]", id);
        userDB.setIsUse(isUse);
        this.userMapper.updateById(userDB);
    }

    @Override
    public void addRole(RoleVo roleVo) {
        log.info("{}",roleVo.getRolePermissionList());
        AssertExt.notNull(roleVo.getRolePermissionList(), "权限数据为空");
        AssertExt.notNull(roleVo.getRoleName(), "角色名称为空");

        Role roleDB=this.roleMapper.selectOne(new QueryWrapper<Role>().eq("ROLE_NAME",roleVo.getRoleName()));
        AssertExt.isTrue(roleDB == null, "角色名称[%s] 已存在", roleVo.getRoleName());

        roleVo.setCreateTime(LocalDateTime.now());
        this.roleMapper.insert(roleVo);

        for (RolePermission rolePermission : roleVo.getRolePermissionList()) {
            rolePermission.setSysRoleId(roleVo.getId());
            rolePermission.setCreateUserId(roleVo.getCreateUserId());
            rolePermission.setCreateTime(LocalDateTime.now());
            this.rolePermissionMapper.insert(rolePermission);
        }

    }

    @Override
    public void updateRole(RoleVo roleVo) {
        AssertExt.hasId(roleVo.getId(), "id为空");
        AssertExt.notNull(roleVo.getRolePermissionList(), "权限数据为空");
        AssertExt.notNull(roleVo.getRoleName(), "角色名称为空");

        Role roleDB=this.roleMapper.selectOne(new QueryWrapper<Role>().ne("id",roleVo.getId()).eq("ROLE_NAME",roleVo.getRoleName()));
        AssertExt.isTrue(roleDB == null, "角色名称[%s] 已存在", roleVo.getRoleName());

        this.roleMapper.updateById(roleVo);

        this.rolePermissionMapper.delete(new QueryWrapper<RolePermission>().eq("SYS_ROLE_ID", roleVo.getId()));

        for (RolePermission rolePermission : roleVo.getRolePermissionList()) {
            rolePermission.setSysRoleId(roleVo.getId());
            rolePermission.setCreateUserId(roleVo.getCreateUserId());
            rolePermission.setCreateTime(LocalDateTime.now());
            this.rolePermissionMapper.insert(rolePermission);
        }

    }

    @Override
    public IPage<RoleVo> getRoleList(Page page, RoleVo roleVo) {
        IPage<RoleVo> roleVoIPage = this.roleMapper.getRoleList(page, roleVo);
        roleVoIPage.getRecords().forEach(roleVo1 -> {
            roleVo1.setRolePermissionList(this.rolePermissionMapper.selectList(new QueryWrapper<RolePermission>().eq("SYS_ROLE_ID", roleVo1.getId())));
        });
        return roleVoIPage;
    }

    @Override
    public List<RoleVo> getRoleAll(RoleVo roleVo) {
        return this.roleMapper.getRoleList(roleVo);
    }

    @Override
    public IPage<AdminLog> getAdminLogList(Page page) {
        return this.adminLogMapper.selectPage(page, new QueryWrapper<AdminLog>().orderByDesc("CREATE_TIME"));
    }

    @Override
    public List<PubTranslineDef> selectAllTransline() {
        return this.customService.selectAllTransline();
    }
}
