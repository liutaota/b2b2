package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2020-08-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("BMS_SA_SETTOREC")
@ApiModel(value="BmsSaSettorec对象", description="")
/**
 * 去掉序列  jie
 */
public class BmsSaSettorec extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableId("SASETTLEDTLID")
    private Long sasettledtlid;

    @TableField("SARECDTLID")
    private Long sarecdtlid;

    @TableField("GOODSQTY")
    private Double goodsqty;

    @TableField("TOTAL_LINE")
    private Double totalLine;


}
