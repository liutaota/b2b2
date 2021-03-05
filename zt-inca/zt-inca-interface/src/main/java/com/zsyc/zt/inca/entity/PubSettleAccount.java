package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2021-01-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("PUB_SETTLE_ACCOUNT")
@ApiModel(value="PubSettleAccount对象", description="")
@KeySequence(value = "UB_SETTLE_ACCOUNT_SEQ")
public class PubSettleAccount extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableId("USEMM")
    private Long usemm;

    @TableField("USEYEAR")
    private Integer useyear;

    @TableField("USEMONTH")
    private Integer usemonth;

    @TableField("ENDDATE")
    private LocalDateTime enddate;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("MEMO")
    private String memo;

    @TableField("ALLOWINIT")
    private Integer allowinit;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("LOGICMM")
    private Long logicmm;


}
