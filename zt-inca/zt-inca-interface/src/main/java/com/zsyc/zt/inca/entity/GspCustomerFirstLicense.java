package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
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
 * @since 2020-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("GSP_CUSTOMER_FIRST_LICENSE")
@ApiModel(value="GspCustomerFirstLicense对象", description="")
@KeySequence(value = "GSP_CUSTOMER_FIRST_LICENSE_SEQ")
public class GspCustomerFirstLicense extends Model<GspCustomerFirstLicense> {

    private static final long serialVersionUID = 1L;

    @TableField("OPERATIONID")
    private Long operationid;

    @TableId("OPERATIONDTLID")
    private Long operationdtlid;

    @TableField("LICENSEID")
    private Long licenseid;

    @TableField("CHECKEDFLAG")
    private Integer checkedflag;

    @TableField("CHECKMANID")
    private Long checkmanid;

    @TableField("CHECKDATE")
    private LocalDateTime checkdate;


}
