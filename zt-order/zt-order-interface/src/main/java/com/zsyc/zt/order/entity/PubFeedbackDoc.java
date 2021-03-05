package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("PUB_FEEDBACK_DOC")
public class PubFeedbackDoc extends Model<PubFeedbackDoc> {

    private static final long serialVersionUID = 1L;

    @TableField("GOODSID")
    private Long goodsid;

    @TableField("CURRENCYNAME")
    private String currencyname;

    @TableField("GOODSTYPE")
    private String goodstype;

    @TableField("FACTORYNAME")
    private String factoryname;

    @TableField("GOODSUNIT")
    private String goodsunit;

    @TableField("MEMO")
    private String memo;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("TRANPOSNAME")
    private String tranposname;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("INPUTMANNAME")
    private String inputmanname;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("ZX_MEMO")
    private String zxMemo;

    @TableId("FEEDBACKDOCID")
    private Long feedbackdocid;


    @Override
    protected Serializable pkVal() {
        return this.feedbackdocid;
    }

}
