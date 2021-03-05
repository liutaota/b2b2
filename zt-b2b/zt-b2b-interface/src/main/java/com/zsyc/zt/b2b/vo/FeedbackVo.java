package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zsyc.zt.b2b.entity.Feedback;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("FEEDBACK")
@ApiModel(value = "FeedbackVo", description = "用户反馈/产品咨询")
public class FeedbackVo extends Feedback {

    /**
     * code
     */
    @ApiModelProperty(value = "code")
    private String code;

}
