package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
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
@TableName("BMS_SA_CON_DTL_TMP")
@KeySequence("BMS_SA_CON_DTL_TMP_SEQ")
public class BmsSaConDtlTmp extends Model<BmsSaConDtlTmp> {

    private static final long serialVersionUID = 1L;

    @TableId("CONDTLID")
    private Long condtlid;


    @Override
    protected Serializable pkVal() {
        return this.condtlid;
    }

}
