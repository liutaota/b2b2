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
@TableName("PUB_COUNTRY")
public class PubCountry extends Model<PubCountry> {

    private static final long serialVersionUID = 1L;

    @TableId("COUNTRYID")
    private Long countryid;

    @TableField("COUNTRYNAME")
    private String countryname;

    @TableField("COUNTRYPINYIN")
    private String countrypinyin;

    @TableField("COUNTRYOPCODE")
    private String countryopcode;

    @TableField("CITYID")
    private Long cityid;

    @TableField("HOSGRADE")
    private Integer hosgrade;

    @TableField("OTCGRADE")
    private Integer otcgrade;


    @Override
    protected Serializable pkVal() {
        return this.countryid;
    }

}
