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
@TableName("BMS_RG_NOTICE_DOC")
public class BmsRgNoticeDoc extends Model<BmsRgNoticeDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("NOTICEDOCID")
    private Long noticedocid;

    @TableField("NOTICENO")
    private String noticeno;

    @TableField("CREDATE")
    private LocalDateTime credate;

    @TableField("COMEFROM")
    private Integer comefrom;

    @TableField("SOURCEID")
    private Long sourceid;

    @TableField("SALERID")
    private Long salerid;

    @TableField("SALESDEPTID")
    private Long salesdeptid;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("COMPANYID")
    private Long companyid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("STORAGEID")
    private Long storageid;

    @TableField("TRPOSID")
    private Long trposid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("MEMO")
    private String memo;


    @Override
    protected Serializable pkVal() {
        return this.noticedocid;
    }

}
