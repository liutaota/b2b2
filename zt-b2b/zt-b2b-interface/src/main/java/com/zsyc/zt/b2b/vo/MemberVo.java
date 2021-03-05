package com.zsyc.zt.b2b.vo;

import com.zsyc.zt.b2b.entity.Member;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "MemberVo对象", description = "用户数据")
public class MemberVo extends Member {
    @ApiModelProperty(value = "客户ID")
    private Long memberId;
    @ApiModelProperty(value = "重置密码")
    private String rePassword;
    @ApiModelProperty(value = "erp用户id")
    private Long erpUserId;
    @ApiModelProperty(value = "手机号")
    private String telephone;
    @ApiModelProperty(value = "登录密码")
    private String password;
}
