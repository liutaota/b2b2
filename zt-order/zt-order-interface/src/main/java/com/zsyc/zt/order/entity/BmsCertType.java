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
@TableName("BMS_CERT_TYPE")
public class BmsCertType extends Model<BmsCertType> {

    private static final long serialVersionUID = 1L;

    @TableId("CERTTYPEID")
    private Long certtypeid;

    @TableField("CERTTYPENAME")
    private String certtypename;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("BUSITYPE")
    private Integer busitype;

    @TableField("SERIALNOID")
    private Long serialnoid;

    @TableField("GENMETHODEMPDEPT")
    private Integer genmethodempdept;

    @TableField("GENMETHODBILL")
    private Integer genmethodbill;

    @TableField("GENMETHODDAYMONTH")
    private Integer genmethoddaymonth;


    @Override
    protected Serializable pkVal() {
        return this.certtypeid;
    }

}
