package com.zsyc.zt.order.entity;

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
@TableName("BMS_CERT_CLASS_DTL")
public class BmsCertClassDtl extends Model<BmsCertClassDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("CERTCLASSID")
    private Long certclassid;

    @TableField("BMSENTRYID")
    private Long bmsentryid;

    @TableField("CERTGENID")
    private Long certgenid;

    @TableField("QUONDAMID")
    private Long quondamid;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("QUBUFFID")
    private Long qubuffid;


    @Override
    protected Serializable pkVal() {
        return this.certclassid;
    }

}
