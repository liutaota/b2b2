package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
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
@TableName("BMS_CERT_DTL_TMP")
@KeySequence("BMS_CERT_DTL_TMP_SEQ")
public class BmsCertDtlTmp extends Model<BmsCertDtlTmp> {

    private static final long serialVersionUID = 1L;

    @TableId("TMPID")
    private Long tmpid;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("SOURCETABLE")
    private String sourcetable;

    @TableField("CERTTYPE")
    private Integer certtype;

    @TableField("ACCTYPE")
    private Integer acctype;

    @TableField("TRANSACTIONID")
    private Long transactionid;


    @Override
    protected Serializable pkVal() {
        return this.tmpid;
    }

}
