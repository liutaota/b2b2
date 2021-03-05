package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("API_USER_INFO")
@KeySequence("API_USER_INFO_SEQ")
public class ApiUserInfo extends Model<ApiUserInfo> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private Long id;

    @TableField("USER_NAME")
    private String userName;

    @TableField("NAME")
    private String name;

    @TableField("PASSWORD")
    private String password;

    @TableField("CUSTOM_ID")
    private Long customId;

    @TableField("B2B_CUSTOM_ID")
    private Long b2bCustomId;

    @TableField("CREATE_TIME")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

    @TableField("IS_DEL")
    @JsonIgnore
    private Integer isDel;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
