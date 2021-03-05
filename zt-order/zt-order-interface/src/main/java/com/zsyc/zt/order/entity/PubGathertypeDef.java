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
@TableName("PUB_GATHERTYPE_DEF")
public class PubGathertypeDef extends Model<PubGathertypeDef> {

    private static final long serialVersionUID = 1L;

    @TableId("GATHERTYPEID")
    private Long gathertypeid;

    @TableField("GATHERTYPENAME")
    private String gathertypename;

    @TableField("ISMODIFY")
    private Integer ismodify;

    @TableField("INTYPE")
    private Integer intype;

    @TableField("RETAVAILABLE")
    private Integer retavailable;

    @TableField("ISCHANGEFLAG")
    private Integer ischangeflag;

    @TableField("YBID")
    private Long ybid;


    @Override
    protected Serializable pkVal() {
        return this.gathertypeid;
    }

}
