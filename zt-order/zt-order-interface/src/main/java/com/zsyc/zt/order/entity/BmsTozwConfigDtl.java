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
@TableName("BMS_TOZW_CONFIG_DTL")
public class BmsTozwConfigDtl extends Model<BmsTozwConfigDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("DTLID")
    private Long dtlid;

    @TableField("DOCID")
    private Long docid;

    @TableField("CERTTYPEID")
    private Long certtypeid;

    @TableField("CSIGN")
    private String csign;


    @Override
    protected Serializable pkVal() {
        return this.dtlid;
    }

}
