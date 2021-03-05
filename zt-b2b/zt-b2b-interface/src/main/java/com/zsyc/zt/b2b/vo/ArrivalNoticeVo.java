package com.zsyc.zt.b2b.vo;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.zt.b2b.entity.ArrivalNotice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "ArrivalNoticeVo对象", description = "缺货信息")
public class ArrivalNoticeVo  extends ArrivalNotice {
    @ApiModelProperty("是否销售，1联系客服")
    private Integer b2bNotSaleFlag;

    @ApiModelProperty(value = "商品编号")
    private Long goodsId;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "状态")
    private String anStatus;

    @ApiModelProperty(value = "商品编号/名称查询")
    private String goodsPinYin;

    @ApiModelProperty("客户ID")
    private Long memberId;

    @ApiModelProperty("erpUserId")
    private Long erpUserId;

    @ApiModelProperty("客户名称")
    private String userName;

    @ApiModelProperty("客户名称")
    private String memberName;

    /**
     * 厂家简称
     */
    @ApiModelProperty(value = "厂家简称")
    private String factoryShort;

    @ApiModelProperty("库存")
    private Integer stqty;

    @TableField("CURRENCYNAME")
    private String currencyname;

    @TableField("规格")
    private String goodstype;

    @TableField("基本单位")
    private String goodsunit;

    @ApiModelProperty("厂家名称")
    private String factoryname;

    @ApiModelProperty("批准文号")
    private String approvedocno;

    @ApiModelProperty("剂型")
    private String medicinetypename;

    @ApiModelProperty("条形码")
    private String barcode;

    @ApiModelProperty(value = "B2B最小销售数量")
    private Integer zxB2bNumLimit;

    @ApiModelProperty("批发二价")
    private Integer price2;

    @ApiModelProperty("价格id")
    private Long priceid;

    @ApiModelProperty("下单价")
    private Integer price;

    @ApiModelProperty(value = "分类号")
    private Long classnRow3;

    @ApiModelProperty(value = "保管帐ID")
    private Integer  storageid;

    /**
     * 商品图片
     */
    @ApiModelProperty(value = "商品图片")
    @TableField("GOODS_IMAGE")
    private String goodsImage;

    /**
     * [{img:xxx},{video:xxx}]
     * @return
     */
    @ApiModelProperty(value = "[{img:xxx},{video:xxx}]")
    public List getGoodsImgList() {
        if (StringUtils.isBlank(this.goodsImage)) {

            return Collections.emptyList();
        }
        return JSON.parseArray(this.goodsImage);
    }

    @ApiModelProperty(value = "活动")
    List<ActivityVo> activityVoList;

    @ApiModelProperty(value = "全场折扣信息")
    private ActivityVo activityVo;

    @ApiModelProperty(value = "价格信息")
    List<PubGoodsPriceVo> pubGoodsPriceList;

}
