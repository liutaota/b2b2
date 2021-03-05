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
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("PUB_DIREC_AGENT_PRICE")
@ApiModel(value="PubDirecAgentPrice对象", description="")
@KeySequence(value = "PUB_DIREC_AGENT_PRICE_SEQ")
public class PubDirecAgentPrice extends Model<PubDirecAgentPrice> {

    private static final long serialVersionUID = 1L;

    @TableId("AGENTID")
    private Long agentid;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("PRICE")
    private Double price;

    @TableField("DISCOUNT")
    private Double discount;

    @TableField("MEMO")
    private String memo;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;


}
