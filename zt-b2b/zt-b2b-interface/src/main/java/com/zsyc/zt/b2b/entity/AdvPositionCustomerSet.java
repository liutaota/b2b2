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
 * 广告客户集合
 * </p>
 *
 * @author MP
 * @since 2020-09-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ADV_POSITION_CUSTOMER_SET")
@ApiModel(value="AdvPositionCustomerSet对象", description="广告客户集合")
@KeySequence(value = "SEQ_ADV_POSITION_CUSTOMER_SET")
public class AdvPositionCustomerSet extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id

     */
    @ApiModelProperty(value = "主键id")
    @TableId("ID")
    private Long id;

    /**
     * 广告id
     */
    @ApiModelProperty(value = "广告id")
    @TableField("ADV_ID")
    private Long advId;

    /**
     * 客户集合id
     */
    @ApiModelProperty(value = "客户集合id")
    @TableField("CUSTOMER_SET_ID")
    private Long customerSetId;

    /**
     * 可见类型：ALL_VISIBLE 全部可见 PARTIALLY_VISIBLE 部分可见
     */
    @ApiModelProperty(value = "可见类型：ALL_VISIBLE 全部可见 PARTIALLY_VISIBLE 部分可见 UN_VISIBLE 部分不可见")
    @TableField("TYPE")
    private String type;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * @see #type
     */
    public enum EType implements IEnum {
        ALL_VISIBLE("全部可见"),
        PARTIALLY_VISIBLE("部分可见"),
        UN_VISIBLE("部分不可见"),
        ;
        private String desc;

        EType(String desc) {
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
