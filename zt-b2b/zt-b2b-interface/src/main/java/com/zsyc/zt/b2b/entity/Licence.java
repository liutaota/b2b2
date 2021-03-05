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
 * 用户证照
 * </p>
 *
 * @author MP
 * @since 2020-09-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("LICENCE")
@ApiModel(value="Licence对象", description="用户证照")
@KeySequence(value = "SEQ_LICENCE")
public class Licence extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "主键ID")
    @TableId("ID")
    private Long id;

    /**
     * erp证照ID
     */
    @ApiModelProperty(value = "erp证照ID")
    @TableField("ERP_LICENCE_ID")
    private Long erpLicenceId;

    /**
     * 证照图片，多个逗号隔开
     */
    @ApiModelProperty(value = "证照图片，多个逗号隔开")
    @TableField("LICENCE_IMAGES")
    private String licenceImages;

    /**
     * 证照名
     */
    @ApiModelProperty(value = "证照名")
    @TableField("LICENCE_NAME")
    private String licenceName;

    /**
     * 证照类型ID
     */
    @ApiModelProperty(value = "证照类型ID")
    @TableField("LICENCE_TYPE_ID")
    private Long licenceTypeId;

    /**
     * erp客户ID
     */
    @ApiModelProperty(value = "erp客户ID")
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
     * NORMAL 正常
     * DISABLE 停用
     * EXPIRES 过期
     */
    @ApiModelProperty(value = "状态 NORMAL 正常 DISABLE 停用 EXPIRES 过期")
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
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @TableField("VALID_ON_TIME")
    //@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private String validOnTime;

    /**
     * 有效期至
     */
    @ApiModelProperty(value = "有效期至")
    @TableField("VALID_END_TIME")
    //@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private String validEndTime;

    /**
     * 证照申请ID
     */
    @ApiModelProperty(value = "证照申请ID")
    @TableField("APPLY_ID")
    private Long applyId;

    /**
     * 创建日期
     */
    @ApiModelProperty(value = "创建日期")
    @TableField("CREATE_TIME")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "证照id")
    @TableField("LICENCE_ID")
    private Long licenceId;

    /**
     * @see #status 状态 NORMAL 正常 DISABLE 停用 EXPIRES 过期
     */
    public enum EStatus implements IEnum {
        NORMAL("正常"),
        DISABLE("停用"),
        EXPIRES("过期"),
        BE_ABOUT_TO_EXPIRES("即将过期"),
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
