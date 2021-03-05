package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 缺货登记
 * </p>
 *
 * @author MP
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ARRIVAL_NOTICE")
@ApiModel(value="ArrivalNotice对象", description="缺货登记")
@KeySequence(value = "SEQ_ARRIVAL_NOTICE")
public class ArrivalNotice extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * erp商品id
     */
    @ApiModelProperty(value = "erp商品id")
    @TableField("ERP_GOODS_ID")
    private Long erpGoodsId;

    /**
     * 商品名称
     */
    @ApiModelProperty(value = "商品名称")
    @TableField("GOODS_NAME")
    private String goodsName;

    /**
     * 会员id
     */
    @ApiModelProperty(value = "会员id")
    @TableField("MEMBER_ID")
    private Long memberId;

    /**
     * 预计购买时间
     */
    @ApiModelProperty(value = "预计购买时间")
    @TableField("SELL_TIME")
    private String sellTime;

    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @TableField("AN_EMAIL")
    private String anEmail;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @TableField("AN_MOBILE")
    private String anMobile;

    /**
     * 类型：1到货通知
     */
    @ApiModelProperty(value = "类型：1到货通知")
    @TableField("AN_TYPE")
    private String anType;

    /**
     * 数量
     */
    @ApiModelProperty(value = "数量")
    @TableField("GOODS_NUM")
    private Integer goodsNum;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    /**
     * erp单位
     */
    @ApiModelProperty(value = "erp单位")
    @TableField("ERP_GOODS_UNIT")
    private String erpGoodsUnit;

    /**
     * erp规格
     */
    @ApiModelProperty(value = "erp规格")
    @TableField("ERP_GOODS_TYPE")
    private String erpGoodsType;

    /**
     * 厂家
     */
    @ApiModelProperty(value = "厂家")
    @TableField("ERP_FACTORY_NAME")
    private String  erpFactoryName;

    /**
     * 包装
     */
    @ApiModelProperty(value = "包装")
    @TableField("PACKING_BOX")
    private String  packingBox;

    /**
     * 商品id
     */
    @ApiModelProperty(value = "商品id")
    @TableField("GOODS_ID")
    private Long goodsId;

    /**
     * 状态：1未处理，2已发知道，3已加入购物车，4已删除
     */
    @ApiModelProperty(value = "状态：1未处理，2已发知道，3已加入购物车，4已删除")
    @TableField("AN_STATUS")
    private String anStatus;

    /**
     * 发通知的时间
     */
    @ApiModelProperty(value = "发通知的时间")
    @TableField("SEND_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime sendTime;

    @ApiModelProperty(value = "是否赠品标志")
    private Long accflag;

    @ApiModelProperty(value = "批号")
    private String lotNo;

    @ApiModelProperty(value = "批号id")
    private Long lotId;

    @ApiModelProperty(value = "保管帐ID")
    private Long  storageId;

    /**
     * @see #anType
     */
    public enum EAnType implements IEnum {
        NOTICE("到货通知"),
        ;
        private String desc;

        EAnType(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }

    /**
     * @see #anStatus 状态：1未处理，2已发知道，3已加入购物车，4已删除
     */
    public enum EAnStatus implements IEnum {
        UNTREATED("未处理"),
        SENT_KNOW("已发通知"),
        ADDED_SHOPPING_CART("已加入购物车"),
        HAVE_DELETED("已删除")
        ;
        private String desc;

        EAnStatus(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }
}
