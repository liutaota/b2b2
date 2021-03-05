package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel(value = "ErpB2bPayOrderFlowNoSyncVo", description = "ERP同步表")
public class ErpB2bPayOrderFlowNoSyncVo implements Serializable {
    private String tmpNo;
    /**
     * 支付流水号
     */
    @ApiModelProperty(value = "支付流水号")
    private String payFlowNo;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    private Long erpUserId;

    private List<Long> orderIds;
}
