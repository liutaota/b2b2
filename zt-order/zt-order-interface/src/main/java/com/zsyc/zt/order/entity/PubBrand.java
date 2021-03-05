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
@TableName("PUB_BRAND")
public class PubBrand extends Model<PubBrand> {

    private static final long serialVersionUID = 1L;

    @TableId("BRANDID")
    private Long brandid;

    @TableField("OPCODE")
    private String opcode;

    @TableField("BRANDNAME")
    private String brandname;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;


    @Override
    protected Serializable pkVal() {
        return this.brandid;
    }

}
