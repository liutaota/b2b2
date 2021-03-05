package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("BMS_TR_FETCH_DTL_ST")
public class BmsTrFetchDtlSt extends Model<BmsTrFetchDtlSt> {

    private static final long serialVersionUID = 1L;

    @TableField("FETCHDTLID")
    private Long fetchdtlid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("STORERID")
    private Long storerid;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
