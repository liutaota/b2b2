package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-07-24
 */
@Data

@Accessors(chain = true)
@TableName("PUB_CUSTOM_SET")
@ApiModel(value="PubCustomSet对象", description="")
@KeySequence(value = "PUB_CUSTOM_SET_SEQ")
public class PubCustomSet {

    private static final long serialVersionUID = 1L;

    @TableId("SETID")
    private Long setid;

    @TableField("SETOPCODE")
    private String setopcode;

    @TableField("SETNAME")
    private String setname;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CREDATE")
    private Date credate;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("ENTRYID")
    private Long entryid;


}
