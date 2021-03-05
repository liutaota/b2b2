package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 专区客户集合
 * </p>
 *
 * @author MP
 * @since 2020-08-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("WEB_PAGE_CUSTOMER_SET")
@ApiModel(value="WebPageCustomerSet对象", description="专区客户集合")
@KeySequence(value = "SEQ_WEB_PAGE_CUSTOMER_SET")
public class WebPageCustomerSet extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId("ID")
    private Long id;

    /**
     * 专区ID
     */
    @ApiModelProperty(value = "专区ID")
    @TableField("WEB_PAGE_ID")
    private Long webPageId;

    /**
     * 客户集合ID
     */
    @ApiModelProperty(value = "客户集合ID")
    @TableField("CUSTOMER_SET_ID")
    private Long customerSetId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "可见类型")
    @TableField("TYPE")
    private String type;

    /**
     * @see #type
     */
    public enum EType implements IEnum {
        ALL_VISIBLE("全部可见"),
        PARTIALLY_VISIBLE("部分可见"),
        PARTIALLY_IN_VISIBLE("部分不可见"),
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
