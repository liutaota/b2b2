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
@TableName("PUB_AUTO_FIRSTCAMP_LICENCE")
public class PubAutoFirstcampLicence extends Model<PubAutoFirstcampLicence> {

    private static final long serialVersionUID = 1L;

    @TableId("FIRSTCAMPLICENCEID")
    private Long firstcamplicenceid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("REENTRYID")
    private Long reentryid;

    @TableField("SUPPLYAUTOFIRSTCAMP")
    private Integer supplyautofirstcamp;

    @TableField("CUSTOMERAUTOFIRSTCAMP")
    private Integer customerautofirstcamp;

    @TableField("GOODSAUTOFIRSTCAMP")
    private Integer goodsautofirstcamp;

    @TableField("COMPANYAUTOCOPY")
    private Integer companyautocopy;

    @TableField("GOODSAUTOCOPY")
    private Integer goodsautocopy;

    @TableField("GOODSAPPROVALID1")
    private Long goodsapprovalid1;

    @TableField("GOODSAPPROVALID2")
    private Long goodsapprovalid2;

    @TableField("GOODSAPPROVALID3")
    private Long goodsapprovalid3;

    @TableField("GOODSAPPROVALID4")
    private Long goodsapprovalid4;

    @TableField("GOODSAPPROVALID5")
    private Long goodsapprovalid5;

    @TableField("GOODSAPPROVALID6")
    private Long goodsapprovalid6;

    @TableField("SUPPLYAPPROVALID1")
    private Long supplyapprovalid1;

    @TableField("SUPPLYAPPROVALID2")
    private Long supplyapprovalid2;

    @TableField("SUPPLYAPPROVALID3")
    private Long supplyapprovalid3;

    @TableField("SUPPLYAPPROVALID4")
    private Long supplyapprovalid4;

    @TableField("SUPPLYAPPROVALID5")
    private Long supplyapprovalid5;

    @TableField("SUPPLYAPPROVALID6")
    private Long supplyapprovalid6;

    @TableField("CUSTOMERAPPROVALID1")
    private Long customerapprovalid1;

    @TableField("CUSTOMERAPPROVALID2")
    private Long customerapprovalid2;

    @TableField("CUSTOMERAPPROVALID3")
    private Long customerapprovalid3;

    @TableField("CUSTOMERAPPROVALID4")
    private Long customerapprovalid4;

    @TableField("CUSTOMERAPPROVALID5")
    private Long customerapprovalid5;

    @TableField("CUSTOMERAPPROVALID6")
    private Long customerapprovalid6;

    @TableField("GOODSLICENCECREADEID")
    private Long goodslicencecreadeid;

    @TableField("GOODSLICENCEALTERID")
    private Long goodslicencealterid;

    @TableField("GOODSLICENCEALTERAPPROVELID")
    private Long goodslicencealterapprovelid;

    @TableField("COMPANYLICENCECREADEID")
    private Long companylicencecreadeid;

    @TableField("COMPANYLICENCEALTERID")
    private Long companylicencealterid;

    @TableField("COMPANYLICENCEALTERAPPROVELID")
    private Long companylicencealterapprovelid;


    @Override
    protected Serializable pkVal() {
        return this.firstcamplicenceid;
    }

}
