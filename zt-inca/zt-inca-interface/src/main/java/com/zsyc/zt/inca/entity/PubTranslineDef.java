package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2021-01-05
 */
@Data
@Accessors(chain = true)
@TableName("PUB_TRANSLINE_DEF")
@ApiModel(value="PubTranslineDef对象", description="")
@KeySequence(value = "PUB_TRANSLINE_DEF_SEQ")
public class PubTranslineDef implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("TRANSLINEID")
    private Long translineid;

    @TableField("TRANSLINEOPCODE")
    private String translineopcode;

    @TableField("TRANSLINENAME")
    private String translinename;

    @TableField("MEMO")
    private String memo;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;

    @TableField("TOWMSDATE")
    private LocalDateTime towmsdate;

    @TableField("ZX_A_PE")
    private Double zxAPe;

    @TableField("ZX_C_PE")
    private Double zxCPe;

    @TableField("ZX_D_PE")
    private Double zxDPe;

}
