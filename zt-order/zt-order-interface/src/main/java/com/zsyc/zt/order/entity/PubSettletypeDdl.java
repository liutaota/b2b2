package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("PUB_SETTLETYPE_DDL")
public class PubSettletypeDdl extends Model<PubSettletypeDdl> {

    private static final long serialVersionUID = 1L;

    @TableId("SETTLETYPEID")
    private Integer settletypeid;

    @TableField("SETTLETYPE")
    private String settletype;

    @TableField("SETTLEFLAG")
    private Integer settleflag;

    @TableField("CASHFLAG")
    private Integer cashflag;

    @TableField("USEFORSU")
    private Integer useforsu;

    @TableField("USEFORSAL")
    private Integer useforsal;


    @Override
    protected Serializable pkVal() {
        return this.settletypeid;
    }

}
