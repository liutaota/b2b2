package com.zsyc.zt.b2b.vo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zsyc.zt.b2b.entity.LicenceApply;
import com.zsyc.zt.inca.vo.CustomBusinessScopeVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@Accessors(chain = true)
@ApiModel(value = "MemberLicenceVo对象", description = "用户证照信息")
public class MemberLicenceVo extends LicenceApply implements Serializable {
    @ApiModelProperty(value = "客户id")
    private Long memberId;

    @ApiModelProperty(value = "客户名称")
    private String memberName;

    @ApiModelProperty(value = "签发日期")
    private LocalDateTime signTime;

    @ApiModelProperty(value = "发证日期")
    private String validBeginTime;

    @ApiModelProperty(value = "有效期至")
    private String validEndTime;

    @ApiModelProperty(value = "证照id")
    private Long licenseId;

    @ApiModelProperty(value = "证照名称")
    private String licenseName;

    @ApiModelProperty(value = "erp证照编码")
    private String erpLicenceCode;

    @ApiModelProperty(value = "b2b证照编码")
    private String licenceCode;

    @ApiModelProperty(value = "审核状态")
    private String auditStatus;

    @ApiModelProperty(value = "证照状态")
    private String licenceStatus;

    @ApiModelProperty(value = "证照图片")
    private String licenceImages;

    @ApiModelProperty(value = "经营范围")
    List<CustomBusinessScopeVo> customBusinessScopeVoList;

    @ApiModelProperty(value = "是否过期")
    private String times;

    @ApiModelProperty(value = "过期状态")
    private String statusTime;
}
