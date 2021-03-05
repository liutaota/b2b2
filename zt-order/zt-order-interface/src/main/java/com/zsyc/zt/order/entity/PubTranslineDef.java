package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
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
@TableName("PUB_TRANSLINE_DEF")
public class PubTranslineDef extends Model<PubTranslineDef> {

    private static final long serialVersionUID = 1L;

    @TableId("TRANSLINEID")
    private Long translineid;

    @TableField("TRANSLINEOPCODE")
    private String translineopcode;

    @TableField("TRANSLINENAME")
    private String translinename;

    @TableField("MEMO")
    private String memo;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;

    @TableField("TOWMSDATE")
    private LocalDateTime towmsdate;

    @TableField("ZX_A_PE")
    private BigDecimal zxAPe;

    @TableField("ZX_C_PE")
    private BigDecimal zxCPe;

    @TableField("ZX_D_PE")
    private BigDecimal zxDPe;


    @Override
    protected Serializable pkVal() {
        return this.translineid;
    }

}
