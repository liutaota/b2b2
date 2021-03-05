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
@TableName("PUB_COMPANY_TO_AGENT")
public class PubCompanyToAgent extends Model<PubCompanyToAgent> {

    private static final long serialVersionUID = 1L;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("TYPE")
    private Integer type;

    @TableId("SEQID")
    private Long seqid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("CTRLTYPE")
    private Integer ctrltype;

    @TableField("DATEFROM")
    private LocalDateTime datefrom;

    @TableField("DATETO")
    private LocalDateTime dateto;

    @TableField("MEMO")
    private String memo;

    @TableField("FILECOUNT")
    private Long filecount;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("FORBIDBACK")
    private Integer forbidback;

    @TableField("FORBIDBUSINESS")
    private Integer forbidbusiness;


    @Override
    protected Serializable pkVal() {
        return this.seqid;
    }

}
