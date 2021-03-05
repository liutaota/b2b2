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
@TableName("PUB_CITY")
public class PubCity extends Model<PubCity> {

    private static final long serialVersionUID = 1L;

    @TableId("CITYID")
    private Long cityid;

    @TableField("CITYNAME")
    private String cityname;

    @TableField("CITYPINYIN")
    private String citypinyin;

    @TableField("CITYOPCODE")
    private String cityopcode;

    @TableField("PROVINCEID")
    private Long provinceid;


    @Override
    protected Serializable pkVal() {
        return this.cityid;
    }

}
