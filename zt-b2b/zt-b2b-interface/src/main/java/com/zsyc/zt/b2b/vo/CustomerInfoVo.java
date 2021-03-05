package com.zsyc.zt.b2b.vo;

import com.zsyc.zt.b2b.entity.Member;
import com.zsyc.zt.inca.entity.PubCustomer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "CustomerInfoVo对象", description = "客户信息筛选")
public class CustomerInfoVo extends Member {
    /**
     * 客户列表筛选参数
     */
    @ApiModelProperty(value = "手机号")
    private String zxPhone;

    @ApiModelProperty(value = "平台账号")
    private String telephone;

    @ApiModelProperty(value = "客户ID")
    private Long id;

    @ApiModelProperty(value = "客户名称")
    private String name;

    @ApiModelProperty(value = "起始时间")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "b2b客户ID")
    private Long memberId;

    @ApiModelProperty(value = "结算方式")
    private String settletype;
}
