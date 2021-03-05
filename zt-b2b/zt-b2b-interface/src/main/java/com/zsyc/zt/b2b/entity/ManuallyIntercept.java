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
 * 手工拦截
 * </p>
 *
 * @author MP
 * @since 2020-11-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MANUALLY_INTERCEPT")
@ApiModel(value="ManuallyIntercept对象", description="手工拦截")
@KeySequence(value = "SEQ_MANUALLY_INTERCEPT")
public class ManuallyIntercept extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * b2b订单号
     */
    @ApiModelProperty(value = "b2b订单号")
    @TableField("B2B_ORDER_NO")
    private String b2bOrderNo;

    /**
     * erp订单号
     */
    @ApiModelProperty(value = "erp订单号")
    @TableField("ERP_ORDER_NO")
    private String erpOrderNo;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    @TableField("STATUS")
    private String status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 处理时间
     */
    @ApiModelProperty(value = "处理时间")
    @TableField("DEAL_WITH_TIME")
    private LocalDateTime dealWithTime;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    /**
     * @see #status
     */
    public enum EStatus implements IEnum {
        TO_INTERCEPT("待拦截"),
        INTERCEPT_SUCCESS("拦截成功"),
        INTERCEPT_FAIL("拦截失败"),
        ;
        private String desc;

        EStatus(String desc) {
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
