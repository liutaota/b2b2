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
@TableName("BMS_PICK_AREAS_DOC")
public class BmsPickAreasDoc extends Model<BmsPickAreasDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("PICKAREASID")
    private Long pickareasid;

    @TableField("PICKAREASNO")
    private String pickareasno;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("STORERID")
    private Long storerid;

    @TableField("GOODSUNITFLAG")
    private Integer goodsunitflag;

    @TableField("PICKTYPE")
    private Integer picktype;


    @Override
    protected Serializable pkVal() {
        return this.pickareasid;
    }

}
