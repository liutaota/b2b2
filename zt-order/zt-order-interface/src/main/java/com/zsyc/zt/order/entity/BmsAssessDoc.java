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
@TableName("BMS_ASSESS_DOC")
public class BmsAssessDoc extends Model<BmsAssessDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("DOCID")
    private Long docid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("CUSTOMSETID")
    private Long customsetid;

    @TableField("GOODSSETID")
    private Long goodssetid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.docid;
    }

}
