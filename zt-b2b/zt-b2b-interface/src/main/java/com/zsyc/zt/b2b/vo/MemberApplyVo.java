package com.zsyc.zt.b2b.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zsyc.zt.b2b.entity.LicenceApply;
import com.zsyc.zt.b2b.entity.MemberApply;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.bytebuddy.implementation.bind.annotation.Pipe;

import java.time.LocalDateTime;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "MemberApplyVo对象", description = "企业认证数据信息")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberApplyVo extends MemberApply {

    /**
     * 后台审核用户注册申请参数：
     * companyName 企业名称
     * companyType 企业类型
     * contactName 联系人
     * contactPhone 联系人手机
     * startTime 开始时间
     * endTime 结束时间
     */
    @ApiModelProperty(value = "企业名称")
    private String companyName;

    @ApiModelProperty(value = "企业类型")
    private String companyType;

    @ApiModelProperty(value = "联系人")
    private String contactName;

    @ApiModelProperty(value = "联系人手机")
    private String contactPhone;

    @ApiModelProperty(value = "开始时间")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @ApiModelProperty(value = "结束时间")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @ApiModelProperty(value = "认证审核状态")
    private String status;

    @ApiModelProperty(value = "erp证照id")
    private Long erpLicenceId;

    @ApiModelProperty(value = "erp客户id")
    private Long erpCustomId;

    @ApiModelProperty(value = "客户id")
    private Long memberId;

    @ApiModelProperty(value = "证照申请信息")
    private List<LicenceApply> licenceApplyList;

    @ApiModelProperty(value = "客户申请信息")
    private MemberApply memberApply;

    @ApiModelProperty(value = "注册手机")
    private String telephone;
}
