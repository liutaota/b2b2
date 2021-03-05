package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-08-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("BMS_CERT_DTL_TMP")
@ApiModel(value="BmsCertDtlTmp对象", description="")
@KeySequence(value = "BMS_CERT_DTL_TMP_SEQ")
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


}
