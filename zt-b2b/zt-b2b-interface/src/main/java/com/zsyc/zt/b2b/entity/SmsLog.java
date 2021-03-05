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
 * 短信日志
 * </p>
 *
 * @author MP
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("SMS_LOG")
@ApiModel(value="SmsLog对象", description="短信日志")
@KeySequence(value = "SEQ_SMS_LOG")
public class SmsLog extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @TableField("LOG_PHONE")
    private String logPhone;

    /**
     * 短信动态码
     */
    @ApiModelProperty(value = "短信动态码")
    @TableField("LOG_CAPTCHA")
    private String logCaptcha;

    /**
     * 请求IP
     */
    @ApiModelProperty(value = "请求IP")
    @TableField("LOG_IP")
    private String logIp;

    /**
     * 短信内容
     */
    @ApiModelProperty(value = "短信内容")
    @TableField("LOG_MSG")
    private String logMsg;

    /**
     * 短信类型:1为注册,2为登录,3为找回密码,默认为1
     */
    @ApiModelProperty(value = "短信类型:1为注册,2为登录,3为找回密码,默认为1")
    @TableField("LOG_TYPE")
    private String logType;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 会员 id，注册为0
     */
    @ApiModelProperty(value = "会员 id，注册为0")
    @TableField("MEMBER_ID")
    private Long memberId;

    /**
     * 会员名
     */
    @ApiModelProperty(value = "会员名")
    @TableField("MEMBER_NAME")
    private String memberName;

    /**
     * @see #logType
     */
    public enum ELogType implements IEnum {
        REGISTER("注册"),
        LOGIN("登录"),
        FIND_PASSWORD("找回密码"),
        ;
        private String desc;

        ELogType(String desc) {
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
