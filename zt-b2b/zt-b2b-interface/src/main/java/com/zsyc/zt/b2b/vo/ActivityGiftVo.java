package com.zsyc.zt.b2b.vo;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zsyc.zt.b2b.entity.ActivityGift;
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
@ApiModel(value="ActivityGiftVo对象", description="活动赠品")
public class ActivityGiftVo extends ActivityGift {


    @ApiModelProperty(value = "剂型")
    private String medicinetypename;

    @ApiModelProperty(value = "基本单位")
    private String goodsunit;

    @ApiModelProperty(value = "批准文号")
    private String approvedocno;

    @ApiModelProperty(value = "规格")
    private String  goodstype;

    @ApiModelProperty(value = "商品定位， X为新品")
    private String   goodsidGps;

    @ApiModelProperty(value = "条形码")
    private String  barcode;

    @ApiModelProperty(value = "通用名")
    private String  currencyname;

    @ApiModelProperty(value = "创建时间")
    private String credate;

    @ApiModelProperty(value = "otc标志")
    private String  otcflag;

    @ApiModelProperty(value = "产地")
    private String  prodarea;

    @ApiModelProperty(value = "用法用量")
    private String   dosage;

    @ApiModelProperty(value = "用药时间")
    private String usegoodstime;

    @ApiModelProperty(value = "诊断信息")
    private String  diagnosticinfo;

    @ApiModelProperty(value = "疗程说明")
    private String  treatment;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "图片")
    private String goodsImg;

    @ApiModelProperty(value = "[{img/video:xxx,code:xxx}]")
    public List<GoodsImagesVo> getGoodsImgList() {
        if (StringUtils.isBlank(this.goodsImg)) {
            return Collections.emptyList();
        }
        return JSONArray.parseArray((this.goodsImg), GoodsImagesVo.class);
    }

    @ApiModelProperty(value = "[{img/video:xxx,code:xxx}]")
    public void setGoodsImgList(List imageList) {
        if (CollectionUtils.isEmpty(imageList)) {
            this.goodsImg = null;
        } else {
            this.goodsImg = JSON.toJSONString(imageList);
        }

    }

    @ApiModelProperty("库存")
    private Integer stqty;

    @ApiModelProperty("厂家名称")
    private String factoryname;


    @ApiModelProperty("策略赠送数量")
    private double asGiftNum;

    @ApiModelProperty("赠送策略")
    private Long giftStrategy;

    @ApiModelProperty("accflag")
    private Long  accflag;

    @ApiModelProperty("最小销售数量")
    private double zxB2bNumLimit;

    @ApiModelProperty("赠品经营范围")
    private String giftRemark;

    /**
     * 优惠后小计
     */
    @ApiModelProperty(value = "优惠后小计")
    private double amountPay;
}
