package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-07-24
 */
@Data

@Accessors(chain = true)
@TableName("GSP_COMPANY_LICENSE")
@ApiModel(value="GspCompanyLicense对象", description="")
@KeySequence(value = "GSP_COMPANY_LICENSE_SEQ")
public class GspCompanyLicense extends Model<GspCompanyLicense> {

    private static final long serialVersionUID = 1L;

    @TableId("LICENSEID")
    private Long licenseid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private Date credate;

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
    private Date signdate;

    @TableField("SIGNDEPARTMENT")
    private String signdepartment;

    @TableField("VALIDONDATE")
    private LocalDate validondate;

    @TableField("VALIDENDDATE")
    private LocalDate validenddate;

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
    private Date sysModifydate;


}
