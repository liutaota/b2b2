package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@TableName("GSP_LICENSE_TYPE")
@ApiModel(value="GspLicenseType对象", description="")
@KeySequence(value = "GSP_LICENSE_TYPE_SEQ")
public class GspLicenseType extends Model<GspLicenseType> {

    private static final long serialVersionUID = 1L;

    /**
     * 证照类型ID
     */
    @TableId("LICENSETYPEID")
    private Long licensetypeid;

    /**
     * 证照类型名称
     */
    @TableField("LICENSENAME")
    private String licensename;

    /**
     *  是否客户证照
     */
    @TableField("CUSTOMFLAG")
    private Integer customflag;

    /**
     *  是否供应商证照
     */
    @TableField("SUPPLYFLAG")
    private Integer supplyflag;

    /**
     *  是否商品证照
     */
    @TableField("GOODSFLAG")
    private Integer goodsflag;

    /**
     *  是否包含经营范围
     */
    @TableField("RANGEFLAG")
    private Integer rangeflag;

    /**
     *  录入人
     */
    @TableField("INPUTMANID")
    private Long inputmanid;


    /**
     *  录入日期
     */
    @TableField("CREDATE")
    private Date credate;


    /**
     *  备注
     */
    @TableField("MEMO")
    private String memo;


    /**
     *  预警天数
     */
    @TableField("EARLYWARNDAYS")
    private Integer earlywarndays;


    /**
     *  可超天数
     */
    @TableField("SUPERDAYS")
    private Integer superdays;


    /**
     *  效期控制
     */
    @TableField("VALIDCTRL")
    private Integer validctrl;


    /**
     *  收货时控制批号生产日期
     */
    @TableField("CONTROLPRODDATE")
    private Integer controlproddate;

    /**
     * 是否先决条件  1 表示 一定需要不过期  2 表示可以
     */
    @ApiModelProperty(value = "是否先决条件  1 表示 一定需要不过期  2 表示可以")
    @TableField("IS_PREMISE")
    private Integer isPremise;


}
