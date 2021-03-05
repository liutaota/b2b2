package com.zsyc.zt.report.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2021-01-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("PUB_MONTHLY_SALES")
@ApiModel(value="PubMonthlySales对象", description="")
@KeySequence(value = "sales_id_sequence")
@ExcelTarget(value = "PubMonthlySales")
@AllArgsConstructor
@NoArgsConstructor
public class PubMonthlySales extends BaseBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    @TableId("ID")
    private Long id;

    @Excel(name = "课程名称")
    @TableField("CUSTOMIDS")
    private Long customids;


    /*@Excel(name="日期",width=15)
    @ApiModelProperty(value = "日期")*/
    @Excel(name = "课程名称")
    @TableField("TOMONTHSS")
    private String tomonthss;


    /*@Excel(name="下单次数",width=15)
    @ApiModelProperty(value = "下单次数")*/
    @Excel(name = "课程名称")
    @TableField("SALES_LINES")
    private String salesLines;


    /*@Excel(name="中药材",width=15)
    @ApiModelProperty(value = "中药材")*/
    @Excel(name = "课程名称")
    @TableField("ZYC_LINES")
    private String zycLines;


    /*@Excel(name="处方药",width=15)
    @ApiModelProperty(value = "处方药")*/
    @Excel(name = "课程名称")
    @TableField("CFY_LINES")
    private String cfyLines;


   /* @Excel(name="非处方药",width=15)
    @ApiModelProperty(value = "非处方药")*/
   @Excel(name = "课程名称")
    @TableField("FCFY_LINES")
    private String fcfyLines;


   /* @Excel(name="保健食品",width=15)
    @ApiModelProperty(value = "保健食品")*/
   @Excel(name = "课程名称")
    @TableField("BJSP_LINES")
    private String bjspLines;


   /* @Excel(name="个人护理品",width=15)
    @ApiModelProperty(value = "个人护理品")*/
   @Excel(name = "课程名称")
    @TableField("GRFLP_LINES")
    private String grflpLines;


   /* @Excel(name="医疗器械",width=15)
    @ApiModelProperty(value = "医疗器械")*/
   @Excel(name = "课程名称")
    @TableField("YLQJ_LINES")
    private String ylqjLines;


    /*@Excel(name="食品饮料",width=15)
    @ApiModelProperty(value = "食品饮料")*/
    @Excel(name = "课程名称")
    @TableField("SPYL_LINES")
    private String spylLines;


   /* @Excel(name="总销售金额",width=15)
    @ApiModelProperty(value = "总销售金额")*/
   @Excel(name = "课程名称")
    @TableField("TOTAL")
    private Double total;


   /* @Excel(name="销售金额前十的商品",width=50)
    @ApiModelProperty(value = "销售金额前十的商品")*/
   @Excel(name = "课程名称")
    @TableField("SALES_AMOUNT")
    private String salesAmount;


   /* @Excel(name="销售数量前十的商品",width=50)
    @ApiModelProperty(value = "销售数量前十的商品")*/
   @Excel(name = "课程名称")
    @TableField("SALES_GOODSQTY")
    private String salesGoodsqty;

    @Excel(name = "课程名称")
    @TableField("CUSTOMNAME")
    private String customname;
    private LocalDateTime createTime;


}
