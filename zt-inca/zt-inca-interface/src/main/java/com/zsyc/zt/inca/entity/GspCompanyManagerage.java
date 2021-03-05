package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-07-24
 */
@Data

@Accessors(chain = true)
@TableName("GSP_COMPANY_MANAGERAGE")
@ApiModel(value="GspCompanyManagerage对象", description="")
@KeySequence(value = "GSP_COMPANY_MANAGERAGE_SEQ")
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


}
