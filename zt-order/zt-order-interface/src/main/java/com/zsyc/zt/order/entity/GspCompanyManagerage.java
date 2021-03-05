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
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("GSP_COMPANY_MANAGERAGE")
public class GspCompanyManagerage extends Model<GspCompanyManagerage> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("SCOPEDEFID")
    private Long scopedefid;

    @TableField("LICENSEID")
    private Long licenseid;

    @TableField("MEDICINETYPE")
    private Long medicinetype;

    /**
     * ????????????????? ??????? 1 ??????
     */
    @TableField("ZX_FLAG")
    private Integer zxFlag;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
