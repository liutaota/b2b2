package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
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
@TableName("PUB_APP_RETAILSALES_DTL")
public class PubAppRetailsalesDtl extends Model<PubAppRetailsalesDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("SALESDTLID")
    private Long salesdtlid;

    @TableField("SALESID")
    private Long salesid;

    @TableField("INSIDERSALES")
    private BigDecimal insidersales;

    @TableField("UNINSIDERSALES")
    private BigDecimal uninsidersales;


    @Override
    protected Serializable pkVal() {
        return this.salesdtlid;
    }

}
