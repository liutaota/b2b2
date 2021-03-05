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
@TableName("BMS_SA_DOCTYPE")
public class BmsSaDoctype extends Model<BmsSaDoctype> {

    private static final long serialVersionUID = 1L;

    @TableId("SATYPEID")
    private Integer satypeid;

    @TableField("SATYPENAME")
    private String satypename;


    @Override
    protected Serializable pkVal() {
        return this.satypeid;
    }

}
