package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
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
@TableName("BMS_SU_PROTOCAL_DOC")
public class BmsSuProtocalDoc extends Model<BmsSuProtocalDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("PROTOCALDOCID")
    private Long protocaldocid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("PROTOCALNO")
    private String protocalno;

    @TableField("INVALIDDATE")
    private LocalDateTime invaliddate;

    @TableField("INVALIDENDDATE")
    private LocalDateTime invalidenddate;

    @TableField("SUPPLYID")
    private Long supplyid;

    @TableField("GOODSQTY")
    private BigDecimal goodsqty;

    @TableField("TOTAL")
    private BigDecimal total;

    @TableField("SETTLETYPEID")
    private Long settletypeid;

    @TableField("REBATEMEMO")
    private String rebatememo;

    @TableField("PAYMETHOD")
    private Integer paymethod;

    @TableField("MAINTERMS")
    private String mainterms;

    @TableField("RELATEDTERMS")
    private String relatedterms;

    @TableField("DESTRIMEDTERMS")
    private String destrimedterms;

    @TableField("SECDESTRIMEDTERMS")
    private String secdestrimedterms;

    @TableField("TERMINALTERMS")
    private String terminalterms;

    @TableField("MEMO")
    private String memo;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("ISALLOWPREPAID")
    private Integer isallowprepaid;

    @TableField("SUPPLYERID")
    private Long supplyerid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("CONTACTID")
    private Long contactid;

    @TableField("PAYLIMIT")
    private String paylimit;


    @Override
    protected Serializable pkVal() {
        return this.protocaldocid;
    }

}
