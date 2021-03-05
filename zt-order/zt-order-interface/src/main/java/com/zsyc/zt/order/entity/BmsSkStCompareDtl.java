package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
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
@TableName("BMS_SK_ST_COMPARE_DTL")
public class BmsSkStCompareDtl extends Model<BmsSkStCompareDtl> {

    private static final long serialVersionUID = 1L;

    @TableId("DTLID")
    private Long dtlid;

    @TableField("DOCID")
    private Long docid;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("SOURCETABLE")
    private Integer sourcetable;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("STADDQTY")
    private BigDecimal staddqty;

    @TableField("STSUBQTY")
    private BigDecimal stsubqty;

    @TableField("SKADDQTY")
    private BigDecimal skaddqty;

    @TableField("SKSUBQTY")
    private BigDecimal sksubqty;

    @TableField("BALANCE")
    private BigDecimal balance;

    @TableField("COMEFROMSOURCEID")
    private Long comefromsourceid;

    @TableField("MEMO")
    private String memo;

    @TableField("FLAG")
    private Integer flag;

    @TableField("INOUTID")
    private Long inoutid;

    @TableField("SKSEQID")
    private Long skseqid;


    @Override
    protected Serializable pkVal() {
        return this.dtlid;
    }

}
