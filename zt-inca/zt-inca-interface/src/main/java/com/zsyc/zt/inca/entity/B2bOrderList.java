package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.bouncycastle.math.raw.Mod;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author MP
 * @since 2020-08-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("B2B_ORDER_LIST")
@ApiModel(value="B2bOrderList对象", description="订单表")
@KeySequence(value = "B2B_ORDER_LIST_SEQ")
public class B2bOrderList extends Model<B2bOrderList> {

    private static final long serialVersionUID = 1L;

    @TableField("ID")
    private Long id;

    @TableField("B2B_ORDER_NO")
    private String b2bOrderNo;

    @TableField("B2B_ORDER_ID")
    private Long b2bOrderId;

    @TableField("SRC_DATA")
    private String srdData;

    /**
     * 1 普通订单 2 补货单
     */
    @ApiModelProperty(value = "1 普通订单 2 补货单")
    @TableField("B2B_ORDER_TYPE")
    private String b2bOrderType;

    @TableField("CREATE_DATE")
    private LocalDateTime createDate;

    @TableField("HINT_COUNT")
    private  Integer hintCount;


    /**
     * 下发版本号
     */
    private String version;


}
