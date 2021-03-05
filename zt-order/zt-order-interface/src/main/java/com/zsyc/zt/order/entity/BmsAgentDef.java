package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("BMS_AGENT_DEF")
public class BmsAgentDef extends Model<BmsAgentDef> {

    private static final long serialVersionUID = 1L;

    @TableId("AGENTID")
    private Long agentid;

    @TableField("AGENTOPCODE")
    private String agentopcode;

    @TableField("AGENTNAME")
    private String agentname;

    @TableField("PINYIN")
    private String pinyin;

    @TableField("SEX")
    private String sex;

    @TableField("BIRTHDATE")
    private LocalDateTime birthdate;

    @TableField("IDCARD")
    private String idcard;

    @TableField("MEMO")
    private String memo;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("EMAIL")
    private String email;

    @TableField("PHONE")
    private String phone;

    @TableField("MOBILENO")
    private String mobileno;

    @TableField("ADDRESS")
    private String address;


    @Override
    protected Serializable pkVal() {
        return this.agentid;
    }

}
