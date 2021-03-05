package com.zsyc.zt.b2b.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@ApiModel(value = "StatementVo", description = "账单")
public class TVDates implements Serializable {

    @ApiModelProperty(value = "收货日期/赠品业务日期")
    private String recredate;

    @ApiModelProperty(value = "验收日期/赠品确定日期")
    private String checkdate;

    @ApiModelProperty(value = "剩余天数")
    private String remaining;

    @ApiModelProperty(value = "剩余时间")
    private String remainingTime;

    @ApiModelProperty(value = "剩余时间状态")
    private String remainingStatus;

    @ApiModelProperty(value = "进货单号/赠品入库单ID")
    private String sudocid;

    @ApiModelProperty(value = "商品id")
    private Long  goodsid;

    @ApiModelProperty(value = "商品名称")
    private String    goodsname;

    @ApiModelProperty(value = "规格")
    private String   goodstype;

    @ApiModelProperty(value = "基本单位")
    private String   goodsunit;

    @ApiModelProperty(value = "厂家")
    private String   factoryname;

    @ApiModelProperty(value = "来货数量")
    private String goodsqty;

    @ApiModelProperty(value = "上架数量")
    private Long   inqty;

    @ApiModelProperty(value = "客户id")
    private Long companyid;

    @ApiModelProperty(value = "客户名称")
    private String companyname;

    @ApiModelProperty(value = "平台类型")
    private String doctype;
    @ApiModelProperty(value = "业务日期")
    private String credate;
    @ApiModelProperty(value = "平台订单号")
    private String docno;

    @ApiModelProperty(value = "发货状态")
    private String zx_allstioflag;

    @ApiModelProperty(value = "ERP订单数量")
    private Long counterp;

    @ApiModelProperty(value = "京东订单数量")
    private Long countjd;

    @ApiModelProperty(value = "B2B订单数量")
    private Long countb2b;

    @ApiModelProperty(value = "总单量")
    private Long countall;

    @ApiModelProperty(value = "总待上架量")
    private Integer goodTotal;

    @ApiModelProperty(value = "剩余待上架量")
    private Integer goodRemainingTotal;
}
