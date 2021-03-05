package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
@TableName("BMS_CONTAINER_DEF")
public class BmsContainerDef extends Model<BmsContainerDef> {

    private static final long serialVersionUID = 1L;

    @TableId("CONTAINERID")
    private Long containerid;

    @TableField("CONTAINERNO")
    private String containerno;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("STORERID")
    private Long storerid;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.containerid;
    }

}
