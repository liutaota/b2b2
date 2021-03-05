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
@TableName("BMS_SA_INV_INFO")
public class BmsSaInvInfo extends Model<BmsSaInvInfo> {

    private static final long serialVersionUID = 1L;

    @TableId("INFOID")
    private Long infoid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("INVCOMPANYNAME")
    private String invcompanyname;

    @TableField("TAXNO")
    private String taxno;

    @TableField("TELEPHONE")
    private String telephone;

    @TableField("ADDRESS")
    private String address;

    @TableField("BANKNAME")
    private String bankname;

    @TableField("ACCNO")
    private String accno;

    @TableField("MEMO")
    private String memo;

    @TableField("COMPANYFUNCTION")
    private Integer companyfunction;

    @TableField("MAIL")
    private String mail;


    @Override
    protected Serializable pkVal() {
        return this.infoid;
    }

}
