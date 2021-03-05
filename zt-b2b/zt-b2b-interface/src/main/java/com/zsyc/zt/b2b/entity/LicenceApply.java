package com.zsyc.zt.b2b.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.b2b.IEnum;
import com.zsyc.zt.b2b.vo.LicenceVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * <p>
 * 用户证照申请
 * </p>
 *
 * @author MP
 * @since 2020-09-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("LICENCE_APPLY")
@ApiModel(value="LicenceApply对象", description="用户证照申请")
@KeySequence(value = "SEQ_LICENCE_APPLY")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LicenceApply extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId("ID")
    private Long id;

    /**
     * 证照图片,多个逗号隔开
     */
    @ApiModelProperty(value = "证照图片,多个逗号隔开")
    @TableField("LICENCE_IMAGES")
    private String licenceImages;

    /**
     * 证照名
     */
    @ApiModelProperty(value = "证照名")
    @TableField("LICENCE_NAME")
    private String licenceName;

    /**
     * 证照类型id
     */
    @ApiModelProperty(value = "证照类型id")
    @TableField("LICENCE_TYPE_ID")
    private Long licenceTypeId;

    /**
     * erp客户id
     */
    @ApiModelProperty(value = "erp客户id")
    @TableField("ERP_CUSTOMER_ID")
    private Long erpCustomerId;

    /**
     * 证照号
     */
    @ApiModelProperty(value = "证照号")
    @TableField("LICENCE_CODE")
    private String licenceCode;

    /**
     * 状态
     * IN_REVIEW 审核中
     * APPROVE 审核通过
     * REJECT 驳回
     */
    @ApiModelProperty(value = "状态 IN_REVIEW 审核中 APPROVE 通过 REJECT 驳回")
    @TableField("STATUS")
    private String status;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    /**
     * 签发日期
     */
    @ApiModelProperty(value = "签发日期")
    @TableField("SIGN_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime signTime;

    /**
     * 发证日期->开始时间
     */
    @ApiModelProperty(value = "发证日期")
    @TableField("VALID_ON_TIME")
   // @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private String validOnTime;

    /**
     * 有效期至
     */
    @ApiModelProperty(value = "有效期至")
    @TableField("VALID_END_TIME")
    //@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private String validEndTime;

    /**
     * 申请时间
     */
    @ApiModelProperty(value = "申请时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 审核结果：通过/不通过原因
     */
    @ApiModelProperty(value = "审核结果：通过/不通过原因")
    @TableField("REASON")
    private String reason;

    /**
     * 审核人
     */
    @ApiModelProperty("审核人")
    @TableField("AUDIT_USER_ID")
    private Long auditUserId;

    /**
     * 审核时间
     */
    @ApiModelProperty(value = "审核时间")
    @TableField("AUDIT_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime auditTime;

    /**
     * 客户ID
     */
    @ApiModelProperty(value = "客户ID")
    @TableField("MEMBER_ID")
    private Long memberId;

    @ApiModelProperty(value = "认证类型->企业类型")
    @TableField("CERTIFICATION_TYPE")
    private String certificationType;


    @ApiModelProperty(value = "证照id")
    @TableField("LICENCE_ID")
    private Long licenceId;

    /**
     * @see #status
     */
    public enum EStatus implements IEnum {
        SAVE("保存资料"),
        IN_REVIEW("审核中"),
        APPROVE("通过"),
        NOT_APPROVED("驳回"),
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

    /**
     * @see #certificationType
     */
    public enum ECertificationType implements IEnum {
        LEGAL_PERSON("法人"),
        CERTIGIER("授权人"),
        ;
        private String desc;

        ECertificationType(String desc) {
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
