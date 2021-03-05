package com.zsyc.zt.inca.vo;

import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * 销售报表统计
 *
 */
@Data
@ExcelTarget("reportVo")
public class ReportVo implements Serializable {

    //类型id
    private Long classid;

    //类型名称
    @Excel(name = "类别名称",width = 25)
    private String classname;

    //返回时间
    @Excel(name = "项目/时间", width = 25)
    private String datTime;

    //返回金额
    @Excel(name = "金额",width = 25)
    private BigDecimal amount;

    //返回总数
    @Excel(name = "总金额",width = 25)
    private  BigDecimal totalAmount;


}
