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
@TableName("BMS_GOODS_LIBRARY_INFO")
public class BmsGoodsLibraryInfo extends Model<BmsGoodsLibraryInfo> {

    private static final long serialVersionUID = 1L;

    @TableId("INFOID")
    private Long infoid;

    @TableField("APPROVEDOCNO")
    private String approvedocno;

    @TableField("OLDAPPROVEDOCNO")
    private String oldapprovedocno;

    @TableField("GOODSCODE")
    private String goodscode;

    @TableField("CODENOTE")
    private String codenote;

    @TableField("GOODSNAME")
    private String goodsname;

    @TableField("GOODSENGNAME")
    private String goodsengname;

    @TableField("CURRENCYNAME")
    private String currencyname;

    @TableField("FACTORYNAME")
    private String factoryname;

    @TableField("GOODSTYPE")
    private String goodstype;

    @TableField("MEDICINETYPE")
    private String medicinetype;

    @TableField("GOODSSORT")
    private Integer goodssort;

    @TableField("APPROVEDATE")
    private LocalDateTime approvedate;


    @Override
    protected Serializable pkVal() {
        return this.infoid;
    }

}
