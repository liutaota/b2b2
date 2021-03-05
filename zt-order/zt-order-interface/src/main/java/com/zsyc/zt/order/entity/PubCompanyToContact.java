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
@TableName("PUB_COMPANY_TO_CONTACT")
public class PubCompanyToContact extends Model<PubCompanyToContact> {

    private static final long serialVersionUID = 1L;

    @TableId("CONTACTPANYID")
    private Long contactpanyid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("TYPE")
    private Integer type;

    @TableField("CTRLTYPE")
    private Integer ctrltype;

    @TableField("FORBIDBUSINESS")
    private Integer forbidbusiness;

    @TableField("FORBIDRETURN")
    private Integer forbidreturn;

    @TableField("MEMO")
    private String memo;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private LocalDateTime credate;


    @Override
    protected Serializable pkVal() {
        return this.contactpanyid;
    }

}
