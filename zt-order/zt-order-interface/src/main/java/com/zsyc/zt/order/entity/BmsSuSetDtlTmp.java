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
@TableName("BMS_SU_SET_DTL_TMP")
public class BmsSuSetDtlTmp extends Model<BmsSuSetDtlTmp> {

    private static final long serialVersionUID = 1L;

    @TableId("SUSETDTLID")
    private Long susetdtlid;


    @Override
    protected Serializable pkVal() {
        return this.susetdtlid;
    }

}
