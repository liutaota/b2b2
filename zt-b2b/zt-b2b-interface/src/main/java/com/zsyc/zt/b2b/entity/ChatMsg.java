package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 聊天记录
 * </p>
 *
 * @author MP
 * @since 2020-08-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("CHAT_MSG")
@ApiModel(value="ChatMsg对象", description="聊天记录")
@KeySequence(value = "SEQ_CHAT_MSG")
public class ChatMsg extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    @TableId("ID")
    private Long id;

    /**
     * 客户id
     */
    @ApiModelProperty(value = "客户id")
    @TableField("MEMBER_ID")
    private Long memberId;

    /**
     * 客户名称
     */
    @ApiModelProperty(value = "客户名称")
    @TableField("MEMBER_NAME")
    private String memberName;

    /**
     * 客户ip
     */
    @ApiModelProperty(value = "客户ip")
    @TableField("MEMBER_IP")
    private String memberIp;

    /**
     * 接收人id
     */
    @ApiModelProperty(value = "接收人id")
    @TableField("ZT_ID")
    private Long ztId;

    /**
     * 接收人名称
     */
    @ApiModelProperty(value = "接收人名称")
    @TableField("ZT_NAME")
    private String ztName;

    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    @TableField("ZT_MSG")
    private String ztMsg;

    /**
     * 状态：1为已读，2为未读，默认2
     */
    @ApiModelProperty(value = "状态：1为已读，2为未读，默认2")
    @TableField("STATUS")
    private String status;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * @see #status
     */
    public enum EStatus implements IEnum {
        READ("已读"),
        NU_READ("未读"),
        ;
        private String desc;

        EStatus(String desc) {
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
