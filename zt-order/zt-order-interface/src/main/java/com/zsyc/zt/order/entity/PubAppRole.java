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
@TableName("PUB_APP_ROLE")
public class PubAppRole extends Model<PubAppRole> {

    private static final long serialVersionUID = 1L;

    @TableId("GROUPID")
    private Long groupid;

    @TableField("USERGROUP")
    private String usergroup;

    @TableField("MESSAGE")
    private Integer message;

    @TableField("REVENUE")
    private Integer revenue;

    @TableField("STRUCT")
    private Integer struct;

    @TableField("STORERANK")
    private Integer storerank;

    @TableField("STOCK")
    private Integer stock;

    @TableField("GOODSAGE")
    private Integer goodsage;

    @TableField("INVALID")
    private Integer invalid;

    @TableField("PROMOTION")
    private Integer promotion;

    @TableField("PRESCRIPTION")
    private Integer prescription;

    @TableField("STORERPOS")
    private Integer storerpos;

    @TableField("REQSUPPLY")
    private Integer reqsupply;

    @TableField("INSIDER")
    private Integer insider;

    @TableField("ADDRESSBOOK")
    private Integer addressbook;

    @TableField("SIGNIN")
    private Integer signin;

    @TableField("SALERANK")
    private Integer salerank;

    @TableField("INSHOPRANK")
    private Integer inshoprank;

    @TableField("GOODSINFO")
    private Integer goodsinfo;


    @Override
    protected Serializable pkVal() {
        return this.groupid;
    }

}
