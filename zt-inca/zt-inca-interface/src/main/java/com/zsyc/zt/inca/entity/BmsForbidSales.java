package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

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
@TableName("BMS_FORBID_SALES")
@ApiModel(value="BmsForbidSales对象", description="")
@KeySequence(value = "BMS_FORBID_SALES_SEQ")
public class BmsForbidSales {

    private static final long serialVersionUID = 1L;

    @TableId("FORBIDID")
    private Long forbidid;

    @TableField("CUSTOMSETID")
    private Long customsetid;

    @TableField("GOODSSETID")
    private Long goodssetid;

    @TableField("FORBIDFLAG")
    private Integer forbidflag;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("MEMO")
    private String memo;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private Date credate;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;


}
