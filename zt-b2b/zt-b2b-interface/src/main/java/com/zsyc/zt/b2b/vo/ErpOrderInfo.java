package com.zsyc.zt.b2b.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel(value = "ErpOrderInfo", description = "ErpOrderInfo")
public class ErpOrderInfo implements Serializable {

    @ApiModelProperty(value = "erp订单id")
    private String salesid;

    @ApiModelProperty(value = "细单数")
    private String dtlLines;

    @ApiModelProperty(value = "b2b訂單id")
    private Long b2bOrderId;

    @ApiModelProperty(value = "销售单类型")
    private String satypeid;

    @ApiModelProperty(value = "客户id")
    private Long customid;

    @ApiModelProperty(value = "客户名称")
    private String customname;


    @ApiModelProperty(value = "")
    private String zxBhFlag;

    @ApiModelProperty(value = "未付款金额")
    private double payTotaline;

    @ApiModelProperty(value = "支付方式")
    private String paymentType;

    @ApiModelProperty(value = "时间")
    private String credate;

    @ApiModelProperty(value = "1是销售类，2是配送类,3是销配退类")
    private Integer sourcetype;

    @ApiModelProperty(value = "区分源单ID")
    private String sourcedocid;

    @ApiModelProperty("路线")
    private String tranposname;

    @ApiModelProperty("线序")
    private String transortno;

    @ApiModelProperty("是否选中")
    private Integer pitchon;

    @ApiModelProperty("客户id")
    private Long erpUserId;

    @ApiModelProperty("路单id")
    private Long transDocId;

    @ApiModelProperty("线路")
    private String[] translinenames;

    @ApiModelProperty("erp订单id")
    private List<Long> longList;

    @ApiModelProperty("erp订单id")
    private List<Long> transDocBySalesId;

    @ApiModelProperty(value = "1是销售类，2是配送类,3是销配退类")
    private String[] sourcetypes;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

}
