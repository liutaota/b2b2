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
@TableName("BMS_CONTACT_DEF")
public class BmsContactDef extends Model<BmsContactDef> {

    private static final long serialVersionUID = 1L;

    @TableId("CONTACTID")
    private Long contactid;

    @TableField("CONTACTMAN")
    private String contactman;

    @TableField("CONTACTOPCODE")
    private String contactopcode;

    @TableField("IDCARD")
    private String idcard;

    @TableField("SEX")
    private Integer sex;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("JOB")
    private String job;

    @TableField("TELEPHONE")
    private String telephone;

    @TableField("MOBILE")
    private String mobile;

    @TableField("EMAIL")
    private String email;

    @TableField("FAX")
    private String fax;

    @TableField("POSTCODE")
    private String postcode;

    @TableField("ADDRESS")
    private String address;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.contactid;
    }

}
