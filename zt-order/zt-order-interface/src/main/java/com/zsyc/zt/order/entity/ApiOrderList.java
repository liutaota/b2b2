package com.zsyc.zt.order.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author peiqy
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("API_ORDER_LIST")
@KeySequence("API_ORDER_LIST_SEQ")
public class ApiOrderList extends Model<ApiOrderList> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private Long id;

    @TableField("API_ORDER_NO")
    private String apiOrderNo;

    /**
     * 1 普通订单 2 补货单
     */
    @TableField("API_ORDER_TYPE")
    private String apiOrderType;

    @TableField("CREATE_DATE")
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createDate;

    @TableField("API_ORDER_ID")
    private Long apiOrderId;

    @TableField("API_STORE_ID")
    private Long apiStoreId;

    @TableField("VERSION")
    private String version;


    @TableField("SRC_DATA")
    private String srdData;

    @TableField("HINT_COUNT")
    private  Integer hintCount;

    @TableField("MESSAGE")
    private  String MESSAGE;

    @TableField("STATUS")
    private  Integer status;
    @Override
    protected Serializable pkVal() {
        return null;
    }

}
