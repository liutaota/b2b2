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
@TableName("PUB_CA_APPROVE_DTL_TMP")
public class PubCaApproveDtlTmp extends Model<PubCaApproveDtlTmp> {

    private static final long serialVersionUID = 1L;

    @TableField("APPROVEID")
    private Long approveid;

    @TableId("APPROVEDTLID")
    private Long approvedtlid;

    @TableField("DTLCONTENT")
    private String dtlcontent;


    @Override
    protected Serializable pkVal() {
        return this.approvedtlid;
    }

}
