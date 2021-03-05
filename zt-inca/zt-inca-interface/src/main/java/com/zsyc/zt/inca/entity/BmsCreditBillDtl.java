package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@TableName("BMS_CREDIT_BILL_DTL")
@Data
@KeySequence("BMS_CREDIT_BILL_DTL_SEQ")
public class BmsCreditBillDtl {
    private static final long serialVersionUID = 1L;
    @TableId(value = "billdtlid",type = IdType.INPUT)
    private Long billdtlid;
    @TableField("billid")
    private Long billid;
    @TableField("busitype")
    private Integer busitype;
    @TableField("sourcetable")
    private String sourcetable;
    @TableField("sourceid")
    private Long sourceid;
    @TableField(value = "busicredate")
    private LocalDateTime busicredate;
    @TableField(value = "busiconfirmdate")
    private LocalDateTime busiconfirmdate;
    @TableField("busimoney")
    private Double busimoney;
}
