package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 账单-收款单中间表
 * </p>
 *
 * @author MP
 * @since 2020-11-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("STATEMENT_REC_DOC")
@ApiModel(value="StatementRecDoc对象", description="账单-收款单中间表")
@KeySequence(value = "SEQ_STATEMENT_REC_DOC")
public class StatementRecDoc extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 账单id
     */
    @ApiModelProperty(value = "账单id")
    @TableField("STATEMENT_ID")
    private Long statementId;

    /**
     * 收款单id
     */
    @ApiModelProperty(value = "收款单id")
    @TableField("REC_DOC_ID")
    private Long recDocId;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;


}
