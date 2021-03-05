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
 * @since 2020-12-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("BMS_FETCH_TO_SAIO")
@ApiModel(value="BmsFetchToSaio对象", description="")
//@KeySequence(value = "SEQ_BMS_FETCH_TO_SAIO")
//去掉序列 jie
public class BmsFetchToSaio extends BaseBean {

    private static final long serialVersionUID = 1L;

    @TableId("FETCHDTLID")
    private Long fetchdtlid;

    @TableField("IODTLID")
    private Long iodtlid;

    @TableField("GOODSQTY")
    private Double goodsqty;

    @TableField("SALESDTLID")
    private Long salesdtlid;


}
