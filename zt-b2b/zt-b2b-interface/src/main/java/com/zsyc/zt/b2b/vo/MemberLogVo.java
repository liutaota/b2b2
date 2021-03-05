package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.zt.b2b.entity.MemberLog;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "MemberLogVo对象", description = "日志信息")
public class MemberLogVo extends MemberLog {
    public static enum EMetaData {type, content}

    /**
     * type
     */
    @ApiModelProperty(value = "type")
    public Object getType() {
        return super.getMetaData(EMetaData.type.name(), String.class);
    }

    /**
     * type
     */
    @ApiModelProperty(value = "type")
    public void setType(String type) {
        super.setMetaData(EMetaData.type.name(), type);
    }

    /**
     * content
     */
    @ApiModelProperty(value = "content")
    public Object getContent() {
        return super.getMetaData(EMetaData.content.name(), String.class);
    }

    /**
     * content
     */
    @ApiModelProperty(value = "content")
    public void setContent(String content) {
        super.setMetaData(EMetaData.content.name(), content);
    }

    @ApiModelProperty(value = "erpUserId")
    private Long erpUserId;

    @ApiModelProperty(value = "客户名称")
    private String userName;
}
