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
 * @since 2020-08-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("GPCS_PLACE_ACCEPT_DOC")
@ApiModel(value="GpcsPlaceAcceptDoc对象", description="")
@KeySequence(value = "GPCS_PLACE_ACCEPT_DOC_SEQ")
public class GpcsPlaceAcceptDoc extends Model<GpcsPlaceAcceptDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("ACCEPTDOCID")
    private Long acceptdocid;

    @TableField("PLACEPOINTID")
    private Long placepointid;

    @TableField("OLDPLACEID")
    private Long oldplaceid;

    @TableField("ACCEPTDATE")
    private LocalDateTime acceptdate;

    @TableField("ACCEPTMANID")
    private Long acceptmanid;

    @TableField("PLACEENTRYID")
    private Long placeentryid;

    @TableField("PLACESTORAGEID")
    private Long placestorageid;

    @TableField("MEMO")
    private String memo;

    @TableField("GENBILLFLAG")
    private Integer genbillflag;

    @TableField("TOFSYJDATE")
    private LocalDateTime tofsyjdate;

    @TableField("SOURCE")
    private String source;//订单来源
}
