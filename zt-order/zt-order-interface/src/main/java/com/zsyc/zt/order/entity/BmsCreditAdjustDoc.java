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
@TableName("BMS_CREDIT_ADJUST_DOC")
public class BmsCreditAdjustDoc extends Model<BmsCreditAdjustDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("CREDIT_ADJUST_DOC_ID")
    private Long creditAdjustDocId;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("INPUTDATE")
    private LocalDateTime inputdate;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("DOCMEMO")
    private String docmemo;

    @TableField("BEGINDATE")
    private LocalDateTime begindate;

    @TableField("ENDDATE")
    private LocalDateTime enddate;

    @TableField("ADJUSTTYPE")
    private Integer adjusttype;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("IMPORTID")
    private Long importid;

    @TableField("EXECUTEID")
    private Long executeid;

    @TableField("EXECUTEDATE")
    private LocalDateTime executedate;


    @Override
    protected Serializable pkVal() {
        return this.creditAdjustDocId;
    }

}
