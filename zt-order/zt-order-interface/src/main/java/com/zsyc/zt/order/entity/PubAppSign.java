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
@TableName("PUB_APP_SIGN")
public class PubAppSign extends Model<PubAppSign> {

    private static final long serialVersionUID = 1L;

    @TableId("SIGNID")
    private Long signid;

    @TableField("EMPLOYEEID")
    private Long employeeid;

    @TableField("SIGNTIME")
    private LocalDateTime signtime;

    @TableField("PLACE")
    private String place;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.signid;
    }

}
