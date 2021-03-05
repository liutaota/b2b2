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
@TableName("BMS_ENTRYGDSPRICE_CANCEL_REC")
public class BmsEntrygdspriceCancelRec extends Model<BmsEntrygdspriceCancelRec> {

    private static final long serialVersionUID = 1L;

    @TableId("CANCELRECID")
    private Long cancelrecid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("PRICEID")
    private Long priceid;

    @TableField("OLDPRICE")
    private BigDecimal oldprice;

    @TableField("NEWPRICE")
    private BigDecimal newprice;

    @TableField("REASON")
    private String reason;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("ENTRYID")
    private Long entryid;


    @Override
    protected Serializable pkVal() {
        return this.cancelrecid;
    }

}
