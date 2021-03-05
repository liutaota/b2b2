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
 * 后台管理员操作日志
 * </p>
 *
 * @author MP
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ADMIN_LOG")
@ApiModel(value="AdminLog对象", description="后台管理员操作日志")
@KeySequence(value = "SEQ_ADMIN_LOG")
public class AdminLog extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 操作内容
     */
    @ApiModelProperty(value = "操作内容")
    @TableField("CONTENT")
    private String content;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 管理员
     */
    @ApiModelProperty(value = "管理员")
    @TableField("USER_NAME")
    private String userName;

    /**
     * 管理员Id
     */
    @ApiModelProperty(value = "管理员Id")
    @TableField("USER_ID")
    private Long userId;

    /**
     * 登录IP
     */
    @ApiModelProperty(value = "登录IP")
    @TableField("IP")
    private String ip;

    /**
     * （类名&方法名）
     */
    @ApiModelProperty(value = "（类名&方法名）")
    @TableField("URL")
    private String url;


}
