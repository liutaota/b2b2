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
@TableName("PUB_APP_MESSAGE")
public class PubAppMessage extends Model<PubAppMessage> {

    private static final long serialVersionUID = 1L;

    @TableId("MESSAGEID")
    private Long messageid;

    @TableField("TXT")
    private String txt;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("SENDMANID")
    private Long sendmanid;

    @TableField("SENDDATE")
    private LocalDateTime senddate;

    @TableField("TITLE")
    private String title;


    @Override
    protected Serializable pkVal() {
        return this.messageid;
    }

}
