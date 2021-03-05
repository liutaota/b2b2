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
@TableName("PUB_WMS_FEEDBACK_MSG")
public class PubWmsFeedbackMsg extends Model<PubWmsFeedbackMsg> {

    private static final long serialVersionUID = 1L;

    @TableId("ID")
    private Long id;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("FEEDBACKDATE")
    private LocalDateTime feedbackdate;

    @TableField("MSG")
    private String msg;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
