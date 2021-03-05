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
@TableName("BMS_SACONDTL_PLACE_HIS")
public class BmsSacondtlPlaceHis extends Model<BmsSacondtlPlaceHis> {

    private static final long serialVersionUID = 1L;

    @TableId("HISTORYID")
    private Long historyid;

    @TableField("CONDTLID")
    private Long condtlid;

    @TableField("PLANSAPLACEQTY")
    private BigDecimal plansaplaceqty;

    @TableField("SAPLACEQTY")
    private BigDecimal saplaceqty;

    @TableField("PLACEDATE")
    private LocalDateTime placedate;


    @Override
    protected Serializable pkVal() {
        return this.historyid;
    }

}
