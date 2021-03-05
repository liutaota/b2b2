package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.sql.Clob;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 协议详情
 * </p>
 *
 * @author MP
 * @since 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("DOCUMENT")
@ApiModel(value="Document对象", description="协议详情")
@KeySequence(value = "SEQ_DOCUMENT")
public class Document extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 调用标识码，REG
     */
    @ApiModelProperty(value = "调用标识码，REG")
    @TableField("DOC_CODE")
    private String docCode;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题")
    @TableField("DOC_TITLE")
    private String docTitle;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    @TableField("DOC_CONTENT")
    private Clob docContent;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField("DOC_MOFIDY_TIME")
    private LocalDateTime docMofidyTime;

    /**
     * @see #docCode
     */
    public enum EDocCode implements IEnum {
        REGISTER("注册"),
        OTHER("其他"),
        ;
        private String desc;

        EDocCode(String desc) {
            this.desc = desc;
        }

        @Override
        public String desc() {
            return this.desc;
        }

        @Override
        public String val() {
            return this.name();
        }
    }
}
