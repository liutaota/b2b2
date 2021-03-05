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
@TableName("PUB_COMPANY_INFO")
public class PubCompanyInfo extends Model<PubCompanyInfo> {

    private static final long serialVersionUID = 1L;

    @TableId("COMPANYINFOID")
    private Long companyinfoid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("COMPANYFUNCTION")
    private Integer companyfunction;

    @TableField("CONTACTMAN")
    private String contactman;

    @TableField("TELEPHONE")
    private String telephone;

    @TableField("MOBILE")
    private String mobile;

    @TableField("EMAIL")
    private String email;

    @TableField("FAX")
    private String fax;

    @TableField("ADDR")
    private String addr;

    @TableField("POSTCODE")
    private String postcode;

    @TableField("ZONECODE")
    private String zonecode;

    @TableField("MEMO")
    private String memo;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("PERSONNO")
    private String personno;


    @Override
    protected Serializable pkVal() {
        return this.companyinfoid;
    }

}
