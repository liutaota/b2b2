package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("BMS_SA_SETTLE_DTL_TMP")
public class BmsSaSettleDtlTmp extends Model<BmsSaSettleDtlTmp> {

    private static final long serialVersionUID = 1L;

    @TableId("SASETTLEDTLID")
    private Long sasettledtlid;


    @Override
    protected Serializable pkVal() {
        return this.sasettledtlid;
    }

}
