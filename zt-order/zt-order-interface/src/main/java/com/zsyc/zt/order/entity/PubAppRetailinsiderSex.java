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
@TableName("PUB_APP_RETAILINSIDER_SEX")
public class PubAppRetailinsiderSex extends Model<PubAppRetailinsiderSex> {

    private static final long serialVersionUID = 1L;

    @TableId("SEQID")
    private Long seqid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("MENQTY")
    private Long menqty;

    @TableField("WOMENQTY")
    private Long womenqty;

    @TableField("OTHERQTY")
    private Long otherqty;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
