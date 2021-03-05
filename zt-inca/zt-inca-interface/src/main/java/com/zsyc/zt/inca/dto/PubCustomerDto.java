package com.zsyc.zt.inca.dto;

import cn.hutool.core.date.DatePattern;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


@ApiModel(value =   "客户主档信息返回查询条件")
@Data
public class PubCustomerDto implements Serializable {

    @ApiModelProperty(name  = "客户id")
    private Long customid;

    @ApiModelProperty(name  = "操作码")
    private String customopcode;

    @ApiModelProperty(name  = "旧编码")
    private String customno;

    @ApiModelProperty(name  = "拼音")
    private String custompinyin;

    @ApiModelProperty(name  = "组织代码")
    private String corpcode;

    @ApiModelProperty(name  = "客户名称")
    private String customname;

    @ApiModelProperty(name  = "备注")
    private String memo;

    @ApiModelProperty(name  = "建立日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime credate;

    @ApiModelProperty(name  = "建立人id")
    private Long inputmanid;

    @ApiModelProperty(name  = "使用状态")
    private Integer usestatus;

    @ApiModelProperty(name  = "市id")
    private Long cityid;

    @ApiModelProperty(name  = "县id")
    private Long countryid;

    @ApiModelProperty(name  = "客户性质")
    private Integer customertype;

    @ApiModelProperty(name  = "注册地址")
    private String registadd;

    @ApiModelProperty(name  = "生产或仓库地址")
    private String address;

    @ApiModelProperty(name  = "医药行业标志")
    private Integer gspflag;

    @ApiModelProperty(name  = "税号")
    private String taxnumber;

    @ApiModelProperty(name  = "地区")
    private String zone;

    @ApiModelProperty(name  = "法人代表")
    private String legalperson;

    @ApiModelProperty(name  = "客户证照管控分类")
    private Long gspcategoryid;

    @ApiModelProperty(name  = "药监编码")
    private String medicode;

    @ApiModelProperty(name  = "initflag")
    private Integer initflag;

    //    @ApiModelProperty(name  = "")
    private Long confirmmanid;

    //    @ApiModelProperty(name  = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime confirmdate;

    @ApiModelProperty(name  = "缺省外币ID")
    private Long fmid;

    @ApiModelProperty(name  = "发票要求")
    private Integer invdemand;

    @ApiModelProperty(name  = "开票策略")
    private Integer invmethod;

    @ApiModelProperty(name  = "月")
    private Integer invmonth;

    @ApiModelProperty(name  = "日")
    private Integer invday;

    @ApiModelProperty(name  = "缺省发票类型")
    private Integer invtype;

    @ApiModelProperty(name  = "最后修改日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime sysModifydate;

    //    @ApiModelProperty(name  = "")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime towmsdate;

    @ApiModelProperty(name  = "单体药店")
    private Integer monomerpharmacy;

    @ApiModelProperty(name  = "是否波次发货")
    private Integer zxIsfix;

    @ApiModelProperty(name  = "打印单据")
    private Integer zxIsprint;

    @ApiModelProperty(name  = "对账日期")
    private Long zxDzDate;

    //    @ApiModelProperty(name  = "")
    private String zxPhOrder1;

    //    @ApiModelProperty(name  = "")
    private Long zxKpmanid;

    @ApiModelProperty(name  = "区域开票员")
    private String zxKpman;

    //    @ApiModelProperty(name  = "")
    private Long zxAccountid;

    @ApiModelProperty(name  = "会计员")
    private String zxAccount;

    @ApiModelProperty(name  = "区域业务员ID")
    private Long zxQySalerid;

    @ApiModelProperty(name  = "区域业务员")
    private String zxQySaler;

    //    @ApiModelProperty(name  = "")
    private Integer zxBhFlag;

    @ApiModelProperty(name  = "区域")
    private String zxQy;

    @ApiModelProperty(name  = "排货顺序")
    private Integer zxPhOrder;

    @ApiModelProperty(name  = "处方_凭处方零售")
    private Integer zxOtcflag0;

    @ApiModelProperty(name  = "非处方甲")
    private Integer zxOtcflag1;

    @ApiModelProperty(name  = "非处方乙")
    private Integer zxOtcflag2;

    @ApiModelProperty(name  = "处方_处方零售")
    private Integer zxOtcflag3;

    //    @ApiModelProperty(name  = "")
    private Integer xstranslineid;

    //    @ApiModelProperty(name  = "")
    private String xstransname;

    //    @ApiModelProperty(name  = "")
    private String zxMemo;

    @ApiModelProperty(name  = "邮箱")
    private String zxEmail;

    //    @ApiModelProperty(name  = "")
    @TableField("ZX_WARNDAY")
    private Integer zxWarnday;

    //    @ApiModelProperty(name  = "")
    private Integer customertypeDoc;

    @ApiModelProperty(name  = "商城账号")
    private Long zxPhone;

    /**
     * 标志客户经营分类，一类，二类，三类等
     */
    @ApiModelProperty(name  = "标志客户经营分类")
    private String zxBusiness;

    /**
     * 维护客户销售合同启始日期
     */
    @ApiModelProperty(name  = "维护客户销售合同启始日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime beginDate;

    /**
     * 维护客户销售合同结束日期
     */
    @ApiModelProperty(name  = "维护客户销售合同结束日期")
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
    private LocalDateTime endDate;


}
