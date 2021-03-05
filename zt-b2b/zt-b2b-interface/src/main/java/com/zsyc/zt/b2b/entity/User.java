package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 后台用户
 * </p>
 *
 * @author MP
 * @since 2020-07-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("USER_INFO")
@ApiModel(value = "User对象", description = "后台用户")
@KeySequence(value = "SEQ_USER_INFO")
public class User extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "账号")
    @TableField("USER_NAME")
    private String userName;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @TableField("NAME")
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @TableField("PASSWORD")
    private String password;

    /**
     * salt值
     */
    @ApiModelProperty(value = "salt值")
    @TableField("SALT")
    private String salt;

    /**
     * 角色
     */
    @ApiModelProperty(value = "角色")
    @TableField("ROLE")
    private String role;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    @TableField("IS_DEL")
    private Integer isDel;

    /**
     * erp账号id
     */
    @ApiModelProperty(value = "erp账号id")
    @TableField("ERP_EMPLOYEE_ID")
    private Long erpEmployeeId;

    /**
     * 工号
     */
    @ApiModelProperty(value = "工号")
    @TableField("EMPLOYEE_NO")
    private String employeeNo;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @TableField("EMAIL")
    private String email;

    /**
     * 最近一次登录时间
     */
    @ApiModelProperty(value = "最近一次登录时间")
    @TableField("END_TIME")
    private LocalDateTime endTime;

    /**
     * 是否启用
     */
    @ApiModelProperty(value = "是否启用")
    @TableField("IS_USE")
    private String isUse;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @TableField("TELEPHONE")
    private String telephone;

    @ApiModelProperty(value = "运输路线")
    @TableField("TRANSLINENAME")
    private String translinename;

    /**
     * @see #isUse
     */
    public enum EIsUse implements IEnum {
        OFF("关闭"),
        ON("启动"),
        ;
        private String desc;

        EIsUse(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }
}
