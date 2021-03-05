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
@TableName("BMS_CREDIT_CANCEL_DOC")
public class BmsCreditCancelDoc extends Model<BmsCreditCancelDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("CREDITCANCELDOCID")
    private Long creditcanceldocid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("INPUTDATE")
    private LocalDateTime inputdate;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("DOCMEMO")
    private String docmemo;

    @TableField("ADJUSTTYPE")
    private Integer adjusttype;


    @Override
    protected Serializable pkVal() {
        return this.creditcanceldocid;
    }

}
