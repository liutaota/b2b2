package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("BMS_SA_DTL_TMP_HIS")
public class BmsSaDtlTmpHis extends Model<BmsSaDtlTmpHis> {

    private static final long serialVersionUID = 1L;

    @TableField("SALESDTLID")
    private Long salesdtlid;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
