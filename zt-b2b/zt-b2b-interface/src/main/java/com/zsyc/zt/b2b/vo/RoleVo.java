package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.zt.b2b.entity.Role;
import com.zsyc.zt.b2b.entity.RolePermission;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "RoleVo", description = "系统角色")
public class RoleVo extends Role {

    /**
     * 角色权限
     */
    @ApiModelProperty(value = "角色权限")
    List<RolePermission> rolePermissionList;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String permissionName;

    /**
     * 权限路径
     */
    @ApiModelProperty(value = "权限路径")
    private String permissionPath;

}
