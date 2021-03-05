package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.zt.b2b.entity.ErpB2bOrderRecDoc;
import com.zsyc.zt.b2b.entity.TmpOrderRecDoc;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "ErpB2bOrderRecDocVo", description = "erp收款单")
public class ErpB2bOrderRecDocVo extends ErpB2bOrderRecDoc {

    @ApiModelProperty(value = "用户手机号")
    private String telephone;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    List<TmpOrderRecDoc> tmpErpOrderRecDocList;

    @ApiModelProperty(value = "核销人id")
    private Long financeErpId;

    @ApiModelProperty(value = "核销人")
    private String financeName;

    @ApiModelProperty(value = "确认人id")
    private Long confirmErpId;

    @ApiModelProperty(value = "确认人")
    private String   confirmName;

    @ApiModelProperty(value = "erp订单")
    private String  salesids;

    @ApiModelProperty(value = "核销状态")
    private String  financeStatus;

    @ApiModelProperty(value = "收款人id")
    private Long sErpId;

    @ApiModelProperty(value = "收款人")
    private String sName;

    @ApiModelProperty(value = "待确认")
    private String confirm;

    @ApiModelProperty(value = "多个线路")
    private String[] tranposnames;

    /**
     * 1,已核销，0未核销,2核销中，3核销失败
     */
    @ApiModelProperty(value = "核销是否通过")
    private String financeTrues;
}
