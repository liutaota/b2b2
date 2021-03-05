package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.sql.Clob;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2020-12-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("B2B_ORDER_BACK_LIST")
@ApiModel(value="B2bOrderBackList对象", description="")
@KeySequence(value = "B2B_ORDER_BACK_LIST_SEQ")
public class B2bOrderBackList extends Model<B2bOrderBackList> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private Double id;

    @TableField("B2B_ORDER_NO")
    private String b2bOrderNo;

    @TableField("CREATE_DATE")
    private LocalDateTime createDate;

    @TableField("B2B_ORDER_ID")
    private Long b2bOrderId;

    @TableField("B2B_SRC_ORDER_ID")
    private Long b2bSrcOrderId;

    @TableField("B2B_SRC_ORDER_NO")
    private String b2bSrcOrderNo;

    @TableField("VERSION")
    private String version;

    @TableField("SRC_DATA")
    private String srcData;


}
