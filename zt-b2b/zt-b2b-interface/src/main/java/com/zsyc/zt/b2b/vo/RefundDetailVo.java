package com.zsyc.zt.b2b.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.zt.b2b.entity.RefundDetail;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "RefundDetailVo对象", description = "退单详情")
public class RefundDetailVo extends RefundDetail {


    @ApiModelProperty("批准文号")
    private String approvedocno;

    @ApiModelProperty("厂家名称")
    private String factoryname;

    @ApiModelProperty("货品分类名")
    private String classname;

    @ApiModelProperty("商品定位， X为新品")
    private String goodsidGps;

    @ApiModelProperty("剂型")
    private String medicinetypename;

    @ApiModelProperty("商品名称")
    private String currencyname;

    @ApiModelProperty("基本单位")
    private String goodsunit;

    @ApiModelProperty("批发二价")
    private double price2;

    @ApiModelProperty("商品最近的一个效期")
    private String invaliddate;

    @ApiModelProperty("OTC标志")
    private String otcflag;

    @ApiModelProperty("运输条件")
    private String storagecondition;

    @ApiModelProperty("产地")
    private String prodarea;

    @ApiModelProperty("运输条件")
    private String transcondition;

    @ApiModelProperty("用法用量")
    private String dosage;

    @ApiModelProperty("发货单类型")
    private String fromtype;

    @ApiModelProperty("客户id")
    private Long companyid;

    @ApiModelProperty("客户名称")
    private String companyname;

    @ApiModelProperty("商品id")
    private Long goodsid;

    @ApiModelProperty("数量")
    private double dtlgoodsqty;

    @ApiModelProperty("单价")
    private double unitprice;

    @ApiModelProperty("小计")
    private double total;

    @ApiModelProperty("生产日期")
    private String proddate;

    @ApiModelProperty("规格")
    private String goodstype;

    @ApiModelProperty("可退数量")
    private Integer sellNum;

    @ApiModelProperty("已退数量")
    private Integer refundNum;

    /**
     * erp最小销售数量
     */
    @ApiModelProperty(value = "erp最小销售数量")
    private Integer erpLeastsaleqty;

    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片")
    @TableField("GOODS_IMAGE")
    private String goodsImage;

    @ApiModelProperty(value = "[{img/video:xxx,code:xxx}]")
    public List<GoodsImagesVo> getGoodsImgList() {
        if (StringUtils.isBlank(this.goodsImage)) {
            return Collections.emptyList();
        }
        return JSONArray.parseArray((this.goodsImage), GoodsImagesVo.class);
    }

    @ApiModelProperty(value = "[{img/video:xxx,code:xxx}]")
    public void setGoodsImgList(List imageList) {
        if (CollectionUtils.isEmpty(imageList)) {
            this.goodsImage = null;
        } else {
            this.goodsImage = JSON.toJSONString(imageList);
        }

    }
}
