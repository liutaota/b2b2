package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName("BMS_CREDIT_BILL_DOC")
@ApiModel(value="BmsCreditBillDoc对象", description="")
@KeySequence(value = "BMS_CREDIT_BILL_DOC_SEQ")
public class BmsCreditBillDoc extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableField("BILLID")
    private Long billid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALERDEPTID")
    private Long salerdeptid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("OWEMONEY")
    private Double owemoney;

    @TableField("OWEDATE")
    private LocalDateTime owedate;


}
