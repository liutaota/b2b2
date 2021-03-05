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
@TableName("PUB_CLASS")
public class PubClass extends Model<PubClass> {

    private static final long serialVersionUID = 1L;

    @TableField("CLASSID")
    private Long classid;

    @TableField("CLASSDESC")
    private String classdesc;

    @TableField("ENTRYFLAG")
    private Integer entryflag;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("MEMO")
    private String memo;

    @TableId("DOCSEQID")
    private Long docseqid;


    @Override
    protected Serializable pkVal() {
        return this.docseqid;
    }

}
