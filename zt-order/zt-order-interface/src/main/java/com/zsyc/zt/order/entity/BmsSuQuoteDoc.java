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
@TableName("BMS_SU_QUOTE_DOC")
public class BmsSuQuoteDoc extends Model<BmsSuQuoteDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("QUOTEDOCID")
    private Long quotedocid;

    @TableField("PLANDOCID")
    private Long plandocid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("RELEASETYPE")
    private Integer releasetype;

    @TableField("RELEASEID")
    private Long releaseid;

    @TableField("RELEASEDATE")
    private LocalDateTime releasedate;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("QUOTEENDDATE")
    private LocalDateTime quoteenddate;


    @Override
    protected Serializable pkVal() {
        return this.quotedocid;
    }

}
