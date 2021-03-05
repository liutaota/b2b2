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
@TableName("BMS_TOZW_CONFIG_DOC")
public class BmsTozwConfigDoc extends Model<BmsTozwConfigDoc> {

    private static final long serialVersionUID = 1L;

    @TableId("DOCID")
    private Long docid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("ACCCODE")
    private String acccode;

    @TableField("ACCNAME")
    private String accname;

    @TableField("ACCYEAR")
    private Integer accyear;

    @TableField("ENTRYNAME")
    private String entryname;


    @Override
    protected Serializable pkVal() {
        return this.docid;
    }

}
