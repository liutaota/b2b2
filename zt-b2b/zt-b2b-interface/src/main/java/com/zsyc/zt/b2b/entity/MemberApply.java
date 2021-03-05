package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.zsyc.framework.base.BaseBean;
import com.zsyc.zt.b2b.IEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户注册申请
 * </p>
 *
 * @author MP
 * @since 2020-09-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MEMBER_APPLY")
@ApiModel(value="MemberApply对象", description="用户注册申请")
@KeySequence(value = "SEQ_MEMBER_APPLY")
public class MemberApply extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId("ID")
    private Long id;

    /**
     * 公司名称
     */
    @ApiModelProperty(value = "公司名称")
    @TableField("COMPANY_NAME")
    private String companyName;

    /**
     * 证照经营范围IDs
     */
    @ApiModelProperty(value = "证照经营范围IDs")
    @TableField("GOODSBUSISCOPEIDS")
    private String goodsbusiscopeids;

    /**
     * 经营范围
     */
    @ApiModelProperty(value = "经营范围")
    @TableField("GOODSBUSISCOPE")
    private String goodsbusiscope;

    /**
     * 区域名，省市区
     */
    @ApiModelProperty(value = "区域名，省市区")
    @TableField("AREA_NAME")
    private String areaName;

    /**
     * 详细地址
     */
    @ApiModelProperty(value = "详细地址")
    @TableField("ADDRESS")
    private String address;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人")
    @TableField("CONTACT_NAME")
    private String contactName;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    @TableField("CONTACT_PHONE")
    private String contactPhone;

    /**
     * 状态
     * APPLYING 申请中
     * IN_REVIEW 审核中
     * APPROVE 审核通过
     * NOT_APPROVED 不通过
     */
    @ApiModelProperty(value = "状态 APPLYING 申请中 IN_REVIEW 审核中 APPROVE 通过 NOT_APPROVED 不通过")
    @TableField("STATUS")
    private String status;

    /**
     * 审核结果（通过不通过原因）
     */
    @ApiModelProperty(value = "审核结果（通过不通过原因）")
    @TableField("REASON")
    private String reason;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @TableField("REMARK")
    private String remark;

    /**
     * 添加时间
     */
    @ApiModelProperty(value = "添加时间")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 认证类型->企业类型
     * RETAIL_MONOMER_DRUGSTORE 零售-单体药店
     * RETAIL_CHAIN_HEADQUARTERS 零售-连锁总部
     * PROFITABILITY_MEDICAL_ESTABLISHMENT 盈利性医疗机构
     * NOT_PROFITABILITY_MEDICAL_ESTABLISHMENT 非盈利性医疗机构
     * WHOLESALE_BUSINESS_COMPANY 批发-商业公司
     */
    @ApiModelProperty(value = "认证类型->企业类型")
    @TableField("COMPANY_TYPE")
    private String companyType;

    /**
     * @see #companyType
     */
    public enum ECompanyType implements IEnum {
        RETAIL_MONOMER_DRUGSTORE("零售-单体药店"),
        RETAIL_CHAIN_HEADQUARTERS("零售-连锁总部"),
        PROFITABILITY_MEDICAL_ESTABLISHMENT("盈利性医疗机构"),
        NOT_PROFITABILITY_MEDICAL_ESTABLISHMENT("非盈利性医疗机构"),
        WHOLESALE_BUSINESS_COMPANY("批发-商业公司"),
        ;
        private String desc;

        ECompanyType(String desc) {
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
     * @see #status
     */
    public enum EStatus implements IEnum {
        SAVE("保存资料"),
        APPLYING("申请中"),
        IN_REVIEW("审核中"),
        APPROVE("通过"),
        NOT_APPROVED("不通过"),
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
