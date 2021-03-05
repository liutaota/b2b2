package com.zsyc.zt.b2b.vo;

import com.zsyc.zt.b2b.entity.Role;
import com.zsyc.zt.b2b.entity.RolePermission;
import com.zsyc.zt.b2b.entity.User;
import com.zsyc.zt.b2b.entity.UserRole;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by lcs on 2020/7/15.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "UserVo对象", description = "后台用户")
public class UserVo extends User {
    /**
     * 二次密码
     */
    @ApiModelProperty(value = "二次密码")
    private String rePassword;

    /**
     * 角色
     */
    @ApiModelProperty(value = "角色")
    private List<RoleVo> roleList;

    private  Long userId;

    /**
     * 所有权限
     */
    @ApiModelProperty(value = "所有权限")
    List<RolePermission> rolePermissionList;

    @ApiModelProperty(value = "erp账号名称")
    private String  employeeName;
}
