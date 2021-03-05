package com.zsyc.zt.b2b.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel(value = "CouponGiveRecordVo对象", description = "优惠券赠送记录")
public class CouponGiveRecordVo implements Serializable {
    @ApiModelProperty(value = "客户id集合")
    private List<Member> memberList;

    @ApiModelProperty(value = "操作信息")
    private String message;
    @Data
    @Accessors(chain = true)
    @ApiModel(value = "Member对象", description = "优惠券赠送记录客户信息")
    public static class Member implements Serializable{
        @ApiModelProperty(value = "id")
        private Long id;

        @ApiModelProperty(value = "名称")
        private String name;
    }
}
