package com.zsyc.zt.inca.vo.others;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelEntity;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ApiModel(value =  "b2b财务报表")
@Data
@EqualsAndHashCode(callSuper = false)
@ExcelTarget("b2bFinancialStatementVo")
public class B2bFinancialStatementVo implements Serializable {

    @ApiModelProperty(value = "ERP销售发货单ID" , name = "salesId")
    @Excel(name = "ERP销售发货单ID",width = 15)
    private String source_id_list;

    @ApiModelProperty(value = "B2B收款单ID" , name = "recId")
    @Excel(name = "B2B收款单ID",width = 15)
    private Long recId;

    @ApiModelProperty(value = "B2B订单ID" , name = "OrderId")
    @Excel(name = "B2B订单ID",width = 15)
    private Long OrderId;

    @ApiModelProperty(value = "B2B订单编号" , name = "orderNo")
    @Excel(name = "B2B订单编号",width = 15)
    private String orderNo;

    @ApiModelProperty(value = "费用发生时间" , name = "expenseDate")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "费用发生时间",width = 26, format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime expenseDate;

    @ApiModelProperty(value = "出库时间" , name = "wmsBackDate")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "出库时间",width = 26)
    private LocalDateTime wmsBackDate;

    @ApiModelProperty(value = "账单日期" , name = "billDate")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "账单日期",width = 20, format = "yyyy-MM-dd")
    private LocalDate billDate;

    @ApiModelProperty(value = "结算时间" , name = "balanceDate")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "结算时间",width = 26, format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime balanceDate;

    @ApiModelProperty(value = "买家名称" , name = "userName")
    @Excel(name = "买家名称",width = 48)
    private String userName;

    @ApiModelProperty(value = "ERP客户编号" , name = "salesId")
    @Excel(name = "ERP客户编号",width = 15)
    private Long erpCustomerId;

    @ApiModelProperty(value = "收款单金额" , name = "recTotal")
    @Excel(name = "收款单金额",width = 15)
    private Double recTotal;

    @ApiModelProperty(value = "订单应付金额" , name = "goodsAmount")
    @Excel(name = "订单应付金额",width = 15)
    private Double goodsAmount;

    @ApiModelProperty(value = "订单实付金额" , name = "actuallyMoney")
    @Excel(name = "订单实付金额",width = 15)
    private Double actuallyMoney;

    @ApiModelProperty(value = "订单总额" , name = "erpOrderTotal")
    @Excel(name = "订单总额",width = 15)
    private Double erpOrderTotal;

    @ApiModelProperty(value = "订单优惠金额" , name = "discount")
    @Excel(name = "订单优惠金额",width = 15)
    private Double discount;

//    @ApiModelProperty(value = "订单优惠金额" , name = "salesId")
//    @Excel(name = "订单优惠金额",width = 15)
//    private Long rptAmount;

    @ApiModelProperty(value = "ERP商品ID" , name = "erpGoodsId")
    @Excel(name = "ERP商品ID",width = 15)
    private Long erpGoodsId;

    @ApiModelProperty(value = "商品名称" , name = "goodsName")
    @Excel(name = "商品名称",width = 15)
    private String goodsName;

    @ApiModelProperty(value = "规格" , name = "goodsSpec")
    @Excel(name = "规格",width = 15)
    private String goodsSpec;

    @ApiModelProperty(value = "商品数量" , name = "goodsNum")
    @Excel(name = "商品数量",width = 15)
    private Double goodsNum;

    @ApiModelProperty(value = "ERP实际发货数量" , name = "sellNum")
    @Excel(name = "ERP实际发货数量",width = 15)
    private Long sellNum;

//    @ApiModelProperty(value = "小计" , name = "amountNum")
//    @Excel(name = "小计",width = 15)
//    private Double amountNum;

    @ApiModelProperty(value = "小计后价格" , name = "amountPay")
    @Excel(name = "小计后价格",width = 15)
    private Double amountPay;

    @ApiModelProperty(value = "独立单元Id" , name = "entryId")
    private Long entryId;

    @ApiModelProperty(value = "独立单元" , name = "entryName")
    @Excel(name = "独立单元",width = 15)
    private String entryName;

//    @ApiModelProperty(value = "B2B结算方式" , name = "recType")
//    @Excel(name = "B2B结算方式",width = 15)
//    private String recType;

    @ApiModelProperty(value = "ERP结算方式" , name = "settleTypeId")
    @Excel(name = "ERP结算方式",width = 15)
    private Integer settleTypeId;

    @ApiModelProperty(value = "B2B订单来源" , name = "orderFrom")
    @Excel(name = "B2B订单来源",width = 15,replace = {"web_1","mobile_2"})
    private Integer orderFrom;

    @ApiModelProperty(value = "开始查询日期" , name = "startDate")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;

    @ApiModelProperty(value = "截止查询日期" , name = "endDate")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;

}
