package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.inca.entity.PubGoodsPrice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "PubGoodsPriceVo对象", description = "协议价信息")
public class PubGoodsPriceVo extends BaseBean {

    /**
     * 协议价
     */
    @ApiModelProperty("客户名称")
    private String customname;

    @ApiModelProperty("客户id")
    private Long customid;

    @ApiModelProperty("协议价ID")
    private Long prid;

    @ApiModelProperty("商品ID")
    private Long goodsid;

    @ApiModelProperty("货品名称")
    private String goodsname;

    @ApiModelProperty("商品名")
    private String currencyname;

    @ApiModelProperty("规格")
    private String goodstype;

    @ApiModelProperty("基本单位")
    private String goodsunit;

    @ApiModelProperty("厂家ID")
    private Long factoryid;

    @ApiModelProperty("厂家名称")
    private String factoryname;

    @ApiModelProperty("产地")
    private String prodarea;

    @ApiModelProperty("协议价")
    private double price;

    @ApiModelProperty("生效开始日期")
    private String startdate;

    @ApiModelProperty("失效结束日期")
    private String enddate;

    @ApiModelProperty("状态")
    private String usestatus;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;
}
