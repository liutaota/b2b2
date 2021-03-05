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
@TableName("BMS_SA_INVHANG_SET_TMP")
public class BmsSaInvhangSetTmp extends Model<BmsSaInvhangSetTmp> {

    private static final long serialVersionUID = 1L;

    @TableId("INVSETTMPID")
    private Long invsettmpid;

    @TableField("INVSETID")
    private Long invsetid;

    @TableField("INVRULEID")
    private String invruleid;

    @TableField("INVRULENAME")
    private String invrulename;


    @Override
    protected Serializable pkVal() {
        return this.invsettmpid;
    }

}
