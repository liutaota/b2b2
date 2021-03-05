package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
@TableName("BMS_SA_INV_INFO")
@ApiModel(value="BmsSaInvInfo对象", description="")
@KeySequence(value = "BMS_SA_INV_INFO_SEQ")
public class BmsSaInvInfo implements Serializable {

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


}
