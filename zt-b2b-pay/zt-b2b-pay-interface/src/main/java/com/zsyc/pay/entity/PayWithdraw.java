package com.zsyc.pay.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zsyc.framework.base.BaseMeta;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 提现
 * </p>
 *
 * @author MP
 * @since 2019-06-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class PayWithdraw extends BaseMeta {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商户号
     */
    private String merchantId;

    /**
     * 商户key(appid)
     */
    private String merchantKey;

    /**
     * 业务订单号
     */
    private String orderNo;

    /**
     * 支付订单号
     */
    private String withdrawFlowNo;

    /**
     * 币种
     */
    private String feeType;

    /**
     * 金额
     */
    private Integer totalFee;

    /**
     * 交易起始时间    
     */
    private LocalDateTime timeStart;

    /**
     * 交易完成时间  
     */
    private LocalDateTime timeEnd;

    /**
     * 状态
     */
    private String status;

    /**
     * 数据来源
     */
    private String dataSource;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 账号
     */
    private String accountNo;

    /**
     * 账号名
     */
    private String accountName;

    /**
     * 账号属性:0私人，1公司
     */
    private Integer accountProp;

    /**
     * 返回结果
     */
    private String result;


    /**
     * @see #status
     */
    public enum EStatus {
        SUCCESS("SUCCESS"),
        PENDING("PENDING"),
        FAIL("FAIL"),
        ;
        private String desc;

        EStatus(String desc) {
            this.desc = desc;
        }

        public String desc() {
            return this.desc;
        }

        public String val() {
            return this.name();
        }
    }

}
