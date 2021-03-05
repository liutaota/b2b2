package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色权限
 * </p>
 *
 * @author MP
 * @since 2020-09-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ROLE_PERMISSION")
@ApiModel(value="RolePermission对象", description="角色权限")
@KeySequence(value = "SEQ_ROLE_PERMISSION")
public class RolePermission extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    @TableField("SYS_ROLE_ID")
    private Long sysRoleId;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    @TableField("PERMISSION_NAME")
    private String permissionName;

    /**
     * 权限路径
     */
    @ApiModelProperty(value = "权限路径")
    @TableField("PERMISSION_PATH")
    private String permissionPath;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    @TableField("CREATE_USER_ID")
    private Long createUserId;


}
