package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-07-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("GSP_CUSTOMER_FIRST_OPERATION")
@ApiModel(value="GspCustomerFirstOperation对象", description="")
@KeySequence(value = "GSP_CUSTOMER_FIRST_OPERATION_SEQ")
public class GspCustomerFirstOperation extends Model<GspCustomerFirstOperation> {

    private static final long serialVersionUID = 1L;

    @TableId("OPERATIONID")
    private Long operationid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("CUSTOMNO")
    private String customno;

    @TableField("TAXNUMBER")
    private String taxnumber;

    @TableField("LEGALPERSON")
    private String legalperson;

    @TableField("ZONE")
    private String zone;

    @TableField("BANKACCOUNT")
    private String bankaccount;

    @TableField("REGISTADD")
    private String registadd;

    @TableField("ADDRESS")
    private String address;

    @TableField("INSPECTCONCLUSION")
    private String inspectconclusion;

    @TableField("QUALITYDEPARTMENTADVICE")
    private String qualitydepartmentadvice;

    @TableField("OWNERSOFCOMPADVICE")
    private String ownersofcompadvice;

    @TableField("FIRSTAPPROVEDATE")
    private LocalDateTime firstapprovedate;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("INITFLAG")
    private Integer initflag;

    @TableField("MEMO")
    private String memo;

    @TableField("RECORDNO")
    private String recordno;

    @TableField("QFUSESTATUS")
    private Integer qfusestatus;

    @TableField("QFMEMO")
    private String qfmemo;

    @TableField("QFLEVELID")
    private Integer qflevelid;

    @TableField("QUALITYCONTROLSTATUS")
    private Integer qualitycontrolstatus;

    @TableField("QUALITYCONTROLMANID")
    private Long qualitycontrolmanid;

    @TableField("QUALITYCONTROLMEMO")
    private String qualitycontrolmemo;

    @TableField("QUALITYCONTROLDATE")
    private LocalDateTime qualitycontroldate;

    @TableField("QUALITYMANAGERSTATUS")
    private Integer qualitymanagerstatus;

    @TableField("QUALITYMANAGERMANID")
    private Long qualitymanagermanid;

    @TableField("QUALITYMANAGERMEMO")
    private String qualitymanagermemo;

    @TableField("QUALITYMANAGERDATE")
    private LocalDateTime qualitymanagerdate;

    @TableField("PRINTCOUNT")
    private Long printcount;

    @TableField("PRINTMANID")
    private Long printmanid;

    @TableField("PRINTDATE")
    private LocalDateTime printdate;

    @TableField("APPROVEDATE3")
    private LocalDateTime approvedate3;

    @TableField("APPROVEDATE4")
    private LocalDateTime approvedate4;

    @TableField("APPROVEDATE5")
    private LocalDateTime approvedate5;

    @TableField("APPROVEDATE6")
    private LocalDateTime approvedate6;

    @TableField("APPROVEMANID3")
    private Long approvemanid3;

    @TableField("APPROVEMANID4")
    private Long approvemanid4;

    @TableField("APPROVEMANID5")
    private Long approvemanid5;

    @TableField("APPROVEMANID6")
    private Long approvemanid6;

    @TableField("APPROVEMEMO3")
    private String approvememo3;

    @TableField("APPROVEMEMO4")
    private String approvememo4;

    @TableField("APPROVEMEMO5")
    private String approvememo5;

    @TableField("APPROVEMEMO6")
    private String approvememo6;

    @TableField("APPROVESTATUS3")
    private Integer approvestatus3;

    @TableField("APPROVESTATUS4")
    private Integer approvestatus4;

    @TableField("APPROVESTATUS5")
    private Integer approvestatus5;

    @TableField("APPROVESTATUS6")
    private Integer approvestatus6;

    @TableField("FIRSTAPPROVESTATUS")
    private Integer firstapprovestatus;


}
