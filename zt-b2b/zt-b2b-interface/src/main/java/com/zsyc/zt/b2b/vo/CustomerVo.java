package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.zt.b2b.entity.Member;
import com.zsyc.zt.inca.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by tang on 2020/07/27.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "CustomerVo对象", description = "客户信息")
public class CustomerVo extends Member {
    /**
     * pub_customer 客户表
     * bms_sa_inv_info 发票表
     * bms_tr_pos_def 运输出地点表
     * PUB_ENTRY_CUSTOMER  客户运营表
     * pub_custom_to_saler 业务员表
     * gsp_customer_first_operation 客户首营主表
     * gsp_customer_first_license 客户首营子表
     * bms_entry_goods_price 客户价格表
     * bms_pr_custom 客户协议价
     */

    /**
     * 发票表
     */
    List<BmsSaInvInfo> bmsSaInvInfoList;

    /**
     * 运输出地点表
     */
    List<BmsTrPosDefVo> bmsTrPosDefList;

    //pub_customer 客户表  --> 基本信息
    @ApiModelProperty(value = "客户ID")
    private Long customid;

    @ApiModelProperty(value = "b2b客户ID")
    private Long memberId;

    @ApiModelProperty(value = "客户操作码")
    private String customopcode;

    @ApiModelProperty(value = "客户分类编码")
    private String classno;

    @ApiModelProperty(value = "客户拼音")
    private String custompinyin;

    @ApiModelProperty(value = "组织代码")
    private String corpcode;

    /**
     * 客户基本信息 + 发票信息 共用
     */
    @ApiModelProperty(value = "客户名称")
    private String customname;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "建立日期")
    private LocalDateTime credate;

    @ApiModelProperty(value = "建立人ID")
    private Long inputmanid;

    @ApiModelProperty(value = "使用状态")
    private String usestatus;

    @ApiModelProperty(value = "市ID")
    private Long cityid;

    @ApiModelProperty(value = "县ID")
    private Long countryid;

    @ApiModelProperty(value = "客户属性")
    private String customertype;

    @ApiModelProperty(value = "注册地址")
    private String registadd;

    @ApiModelProperty(value = "生产或仓库地址")
    private String address;

    @ApiModelProperty(value = "医药行业标志")
    private String gspflag;

    @ApiModelProperty(value = "税号")
    private String taxnumber;

    @ApiModelProperty(value = "地区")
    private String zone;

    @ApiModelProperty(value = "法人代表")
    private String legalperson;

    @ApiModelProperty(value = "客户质量类别")
    private Long gspcategoryid;

    @ApiModelProperty(value = "药品监管码")
    private String medicode;

    @ApiModelProperty(value = "期初数据标志")
    private Integer initflag;

    @ApiModelProperty(value = "确认人id")
    private Long confirmmanid;

    @ApiModelProperty(value = "确认时间")
    private LocalDateTime confirmdate;

    @ApiModelProperty(value = "缺省外币ID")
    private Long fmid;

    @ApiModelProperty(value = "发票要求")
    private Integer invdemand;

    @ApiModelProperty(value = "开票策略")
    private Long invmethod;

    @ApiModelProperty(value = "月")
    private Integer invmonth;

    @ApiModelProperty(value = "日")
    private Integer invday;

    @ApiModelProperty(value = "缺省发票类型")
    private String invtype;

    //新增的字段信息--
    @ApiModelProperty(value = "开户行")
    private String bankname;

    @ApiModelProperty(value = "运输地点")
    private String transpsite;

    @ApiModelProperty(value = "地点序号")
    private Long siteid;

    @ApiModelProperty(value = "联系人")
    private String contacts;

    @ApiModelProperty(value = "建立人ID")
    private Long creid;

    @ApiModelProperty(value = "备注")
    private String remarks;

    //运营信息 + 首营信息
    @ApiModelProperty(value = "首映日期")
    private LocalDateTime firstdate;

    @ApiModelProperty(value = "结算方式")
    private Long clearform;

    @ApiModelProperty(value = "缺省价格类型")
    private Long pritype;

    @ApiModelProperty(value = "销售状态")
    private Long selltype;

    @ApiModelProperty(value = "质量状态")
    private Long masstype;

    @ApiModelProperty(value = "客户折扣")
    private Double customervip;


    @ApiModelProperty(value = "经营范围")
    private String goodsbusiscope;




    @ApiModelProperty(value = "手机号")
    private Long zxPhone;

    @ApiModelProperty(value = "是否可以登录/客户质量类别")
    private Integer gspusestatus;

    @ApiModelProperty(value = "联系人")
    private String employeename;

    @ApiModelProperty(value = "开票策略")
    private String invmethodName;

    @ApiModelProperty(value = "首营通过日期")
    private String  firstapprovedate;

    @ApiModelProperty(value = "结算方式")
    private String settletype;
    @ApiModelProperty(value = "结算方式")
    private Long settletypeid;

    @ApiModelProperty(value = "客户折扣")
    private double discount;

    @ApiModelProperty(value = "按商品厂商给客户折扣")
    private double factoryDiscount;

    @ApiModelProperty(value = "缺省价格类型客户折扣")
    private String pricename;

    @ApiModelProperty(value = "销售状态")
    private String sausestatus;

    @ApiModelProperty(value = "质量状态")
    private String gspusestatusname;

    @ApiModelProperty(value = "经营范围id")
    private Long scopedefid;


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
     * 区域名，省市区
     */
    @ApiModelProperty(value = "区域名，省市区")
    @TableField("AREA_NAME")
    private String areaName;

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

    @ApiModelProperty(value = "认证类型->企业类型")
    @TableField("COMPANY_TYPE")
    private String companyType;

    @ApiModelProperty(value = "处方_凭处方零售")
    private String zxOtcflag0;

    @ApiModelProperty(value = "非处方甲")
    private String zxOtcflag1;

    @ApiModelProperty(value = "非处方乙")
    private String zxOtcflag2;

    @ApiModelProperty(value = "审核结果（通过不通过原因）")
    private String zxOtcflag3;

    @ApiModelProperty(value = "我的收藏数")
    private Integer myFavoritesCount;

    @ApiModelProperty(value = "待付账单")
    private Integer toTotal;

    @ApiModelProperty(value = "未出账单")
    private Integer notTotal;
    @ApiModelProperty(value = "抽奖次数")
    private Integer lotterCount;

    @ApiModelProperty(value = "欠款")
    private Double notRecAmount;

    @ApiModelProperty(value = "剩余还款天数")
    private Integer remainRecDays;

    @ApiModelProperty(value = "信用天数")
    private Integer  creditDays;
}
