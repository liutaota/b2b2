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
 * 客户
 * </p>
 *
 * @author MP
 * @since 2020-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MEMBER")
@ApiModel(value="Member对象", description="客户")
@KeySequence(value = "SEQ_MEMBER")
public class Member extends BaseBean {

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
    @ApiModelProperty(value = "用户名")
    @TableField("USER_NAME")
    private String userName;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    @TableField("NAME")
    private String name;

    /**
     * openid
     */
    @ApiModelProperty(value = "openid")
    @TableField("OPENID")
    private String openid;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    @TableField("SEX")
    private Integer sex;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    @TableField("HEAD_IMG")
    private String headImg;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @TableField("PASSWORD")
    private String password;

    /**
     * salt
     */
    @ApiModelProperty(value = "salt")
    @TableField("SALT")
    private String salt;

    /**
     * erp用户id
     */
    @ApiModelProperty(value = "erp用户id")
    @TableField("ERP_USER_ID")
    private Long erpUserId;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @TableField("TELEPHONE")
    private String telephone;

    /**
     * 登录次数
     */
    @ApiModelProperty(value = "登录次数")
    @TableField("MEMBER_LOGIN_NUM")
    private Integer memberLoginNum;

    /**
     * 修改密码时间
     */
    @ApiModelProperty(value = "修改密码时间")
    @TableField("AUTH_MODIFY_PWD_TIME")
    private LocalDateTime authModifyPwdTime;

    /**
     * 发送验证码次数
     */
    @ApiModelProperty(value = "发送验证码次数")
    @TableField("SEND_ACODE_TIMES")
    private Integer sendAcodeTimes;

    /**
     * 锁定时间
     */
    @ApiModelProperty(value = "锁定时间")
    @TableField("LOCK_TIME")
    private LocalDateTime lockTime;

    /**
     * 是否ERP旧用户
     */
    @ApiModelProperty(value = "是否ERP旧用户")
    @TableField("IS_ERP")
    private Integer isErp;

    /**
     * 用户申请ID
     */
    @ApiModelProperty(value = "用户申请ID")
    @TableField("APPLY")
    private Long apply;

    /**
     * 积分
     */
    @ApiModelProperty(value = "积分")
    @TableField("INTEGRAL")
    private Long integral;


}
