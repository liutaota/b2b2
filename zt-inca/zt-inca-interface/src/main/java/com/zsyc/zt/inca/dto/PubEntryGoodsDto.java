package com.zsyc.zt.inca.dto;

import cn.hutool.core.date.DatePattern;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel(value  = "货品运营参数查询条件")
@Data
public class PubEntryGoodsDto implements Serializable {


    @ApiModelProperty(name  = "独立单元货品ID")
    private Long entrygoodsid;

    @ApiModelProperty(name  = "货品ID")
    private Long goodsid;

    @ApiModelProperty(name  = "独立单元ID")
    private Long entryid;

    @ApiModelProperty(name  = "质量状态")
    private Integer gspusestatus;

    @ApiModelProperty(name  = "首次进货日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime firstsudate;

    @ApiModelProperty(name  = "首营日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime firstapprovedate;

    @ApiModelProperty(name  = "采购状态")
    private Integer suuesstatus;

    @ApiModelProperty(name  = "销售状态")
    private Integer sausestatus;

    @ApiModelProperty(name  = "最小销售数量")
    private BigDecimal leastsaleqty;

    @ApiModelProperty(name  = "采购员ID")
    private Long supplyerid;

    @ApiModelProperty(name  = "发票名称")
    private String goodsinvname;

    @ApiModelProperty(name  = "销项税")
    private BigDecimal salestaxrate;

    @ApiModelProperty(name  = "进项税")
    private BigDecimal supplytaxrate;

    @ApiModelProperty(name  = "关税税率")
    private BigDecimal customstax;

    //    @ApiModelProperty(name  = "")
    private Integer bmsusestatus;

    //    @ApiModelProperty(name  = "")
    private Integer usestatus;

    @ApiModelProperty(name  = "数据来源")
    private Integer comefrom;

    @ApiModelProperty(name  = "期初标志")
    private Integer initflag;

    @ApiModelProperty(name  = "财务编码")
    private String financeno;

    //    @ApiModelProperty(name  = "")
    private Long subscribesetdtlid;

    @ApiModelProperty(name  = "生产属性")
    private Integer useflag;

    @ApiModelProperty(name  = "采购提前期")
    private Long purbefor;

    @ApiModelProperty(name  = "生产提前期")
    private Long probefor;

    @ApiModelProperty(name  = "累计提前期")
    private Long sumbefor;

    @ApiModelProperty(name  = "低阶码")
    private String levelnum;

    @ApiModelProperty(name  = "计划策略")
    private Integer planstra;

    @ApiModelProperty(name  = "均化类型")
    private Integer averragetype;

    @ApiModelProperty(name  = "均化取整")
    private Integer averrageint;

    @ApiModelProperty(name  = "采购批量")
    private BigDecimal purchasenum;

    @ApiModelProperty(name  = "生产批量")
    private BigDecimal producenum;

    //    @ApiModelProperty(name  = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime sysModifydate;

    //    @ApiModelProperty(name  = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime towmsdate;

    @ApiModelProperty(name  = "双人验收")
    private Integer doublerg;

    @ApiModelProperty(name  = "双人复核")
    private Integer doublecheck;

    @ApiModelProperty(name  = "双人拣货")
    private Integer doublepick;

    @ApiModelProperty(name  = "禁止现金交易")
    private Integer bancash;

    //    @ApiModelProperty(name  = "")
    private Integer zxBhFlag;

    //    @ApiModelProperty(name  = "")
    private Long oldSupplyerid;

    /**
     * 0不开票 1开票
     */
    @ApiModelProperty(name  = "是否开票")
    private Integer zxKpFlag;

    //    @ApiModelProperty(name  = "")
    private Integer zxFlagLin;

    /**
     * 京东最小销售数量
     */
    @ApiModelProperty(name  = "京东最小销售数量")
    private BigDecimal zxJdNumLimit;

    /**
     * B2B最小销售数量
     */
    @ApiModelProperty(name  = "B2B最小销售数量")
    private BigDecimal zxB2bNumLimit;

    //    @ApiModelProperty(name  = "")
    private String entryMemo;


}
