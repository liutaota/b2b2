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
@TableName("PUB_GOODS_CLASS_CHECK")
public class PubGoodsClassCheck extends Model<PubGoodsClassCheck> {

    private static final long serialVersionUID = 1L;

    @TableId("CHEID")
    private Long cheid;

    @TableField("CLASSTYPEID")
    private Long classtypeid;

    @TableField("GOODSISABLEREQ")
    private Integer goodsisablereq;

    @TableField("CONMODE")
    private Integer conmode;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;


    @Override
    protected Serializable pkVal() {
        return this.cheid;
    }

}
