package com.zsyc.zt.order.entity;

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
@TableName("BMS_ACC_DEF")
public class BmsAccDef extends Model<BmsAccDef> {

    private static final long serialVersionUID = 1L;

    @TableId("ACCID")
    private Long accid;

    @TableField("ACCNO")
    private String accno;

    @TableField("ACCNAME")
    private String accname;

    @TableField("ACCTYPE")
    private Integer acctype;

    @TableField("COMPANYFLAG")
    private Integer companyflag;

    @TableField("GOODSFLAG")
    private Integer goodsflag;

    @TableField("DEPTFLAG")
    private Integer deptflag;

    @TableField("EMPLOYEEFLAG")
    private Integer employeeflag;

    @TableField("COND")
    private String cond;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("FMID")
    private Long fmid;

    @TableField("AGENTID")
    private Long agentid;

    @TableField("AGENTFLAG")
    private Integer agentflag;

    @TableField("CONTACTFLAG")
    private Integer contactflag;


    @Override
    protected Serializable pkVal() {
        return this.accid;
    }

}
