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
@TableName("GSP_LOT_CONSERVEDATE")
@ApiModel(value="GspLotConservedate对象", description="")
//@KeySequence(value = "GSP_LOT_CONSERVEDATE_SEQ")
public class GspLotConservedate extends Model<GspLotConservedate> {

    private static final long serialVersionUID = 1L;

    @TableId("LOTID")
    private Long lotid;

    @TableField("STHOUSEID")
    private Long sthouseid;

    @TableField("CONSERVEDATE")
    private LocalDateTime conservedate;


}
