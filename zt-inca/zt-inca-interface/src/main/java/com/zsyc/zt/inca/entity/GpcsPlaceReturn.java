package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/*配送退货总单*/

@Data
@TableName("GPCS_PLACERETURN")
@KeySequence("GPCS_PLACERETURN_SEQ")
public class GpcsPlaceReturn  {
    private static final long serialVersionUID = 1L;
    /*配送退货单ID */
    @TableId("PLACERETURNID")
    private Long placereturnid;
    /*调配中心ID*/
    @TableField("PLACECENTERID")
    private int placecenterid;
    /*门店ID*/
    @TableField("PLACEPOINTID")
    private Long placepointid;
    /*退货人ID*/
    @TableField("PLACEMANDID")
    private Long placemandid;
    /*退货日期*/
    @TableField("PLACEDATE")
    private LocalDateTime PLACEDATE;
    /*单据类型*/
    @TableField("INPUTFLAG")
    private int inputflag;
    /*审批状态*/
    @TableField("APPROVESTATUSDOC")
    private int approvestatusdoc;

    @TableField("B2B_NOT_WRITE_BACK")
    private int b2bnotwriteback;


    @TableField("ZX_BH_FLAG")
    private Integer zxBhFlag;

    @TableField("MEMO")
    private String memo;


    /*商城单号*/
    @TableField("B2B_ORDER_ID")
    private Long b2bOrderId;

    /*商城单号*/
    @TableField("B2B_ORDER_NO")
    private String b2bOrderNo;


    /*商城单号*/
    @TableField("B2B_SRC_ORDER_ID")
    private Long b2bSrcOrderId;

    /*商城单号*/
    @TableField("B2B_SRC_ORDER_NO")
    private String b2bSrcOrderNo;

    private String placepointname;
    private Integer totallines;
}
