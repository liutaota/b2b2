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
 * @since 2020-08-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("GSP_COMPANY_LICENSE")
public class GspCompanyLicense extends Model<GspCompanyLicense> {

    private static final long serialVersionUID = 1L;

    @TableId("LICENSEID")
    private Long licenseid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("RELATCOMPANY")
    private String relatcompany;

    @TableField("LICENSETYPEID")
    private Long licensetypeid;

    @TableField("LICENSECODE")
    private String licensecode;

    @TableField("RELATGOODS")
    private String relatgoods;

    @TableField("MAINCONTENT")
    private String maincontent;

    @TableField("SIGNDATE")
    private LocalDateTime signdate;

    @TableField("SIGNDEPARTMENT")
    private String signdepartment;

    @TableField("VALIDONDATE")
    private LocalDateTime validondate;

    @TableField("VALIDENDDATE")
    private LocalDateTime validenddate;

    @TableField("FILESOPCODE")
    private String filesopcode;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("GOODSBUSISCOPE")
    private String goodsbusiscope;

    @TableField("GOODSCLASSCOPE")
    private String goodsclasscope;

    @TableField("MEMO")
    private String memo;

    @TableField("GOODSBUSISCOPEIDS")
    private String goodsbusiscopeids;

    @TableField("GOODSCLASSCOPEIDS")
    private String goodsclasscopeids;

    @TableField("STOPREASON")
    private Integer stopreason;

    @TableField("MEMO2")
    private String memo2;

    @TableField("OLD_LICENSEID")
    private Long oldLicenseid;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;


    @Override
    protected Serializable pkVal() {
        return this.licenseid;
    }

}
