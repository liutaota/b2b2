package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("PUB_BID_PRICE_DOC")
public class PubBidPriceDoc extends Model<PubBidPriceDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("BIDID")
    private Long bidid;

    @TableField("SETID")
    private Long setid;

    @TableField("STARTDATE")
    private LocalDateTime startdate;

    @TableField("ENDDATE")
    private LocalDateTime enddate;

    @TableField("MEMO")
    private String memo;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("ENTRYID")
    private Long entryid;


    @Override
    protected Serializable pkVal() {
        return this.bidid;
    }

}
