package com.zsyc.zt.order.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author peiqy
 * @version 1.0
 * @email peiqy@foxmail.com
 * @date 2020/8/10 17:06
 */
@Data
public class CustomerVo {


    private int entryid;
    private Long goodsid;
    private Long lastsadtlid;
    private double lastsaprice;

    private Long customid;

    private String customopcode;

    private String customno;

    private String custompinyin;

    private String corpcode;

    private String customname;

    private String memo;

    private Date credate;

    private Long inputmanid;

    private Integer usestatus;

    private Long cityid;

    private Long countryid;

    private Integer customertype;

    private String registadd;

    private String address;

    private Integer gspflag;

    private String taxnumber;

    private String zone;

    private String legalperson;

    private Long gspcategoryid;

    private String medicode;

    private Integer initflag;

    private Long confirmmanid;

    private Date confirmdate;

    private Long fmid;

    //    fmrate, double
    private Double fmrate;
    //    settletypeid int
    private Integer settletypeid;

    private Integer invdemand;


    private Integer invmethod;


    private Integer invmonth;


    private Integer invday;


    private Integer invtype;


    private Date sysModifydate;


    private Date towmsdate;


    private Integer monomerpharmacy;


    private Integer zxIsfix;


    private Integer zxIsprint;


    private Long zxDzDate;


    private String zxPhOrder1;


    private Long zxKpmanid;


    private String zxKpman;


    private Long zxAccountid;


    private String zxAccount;


    private Long zxQySalerid;


    private String zxQySaler;


    private Integer zxBhFlag;


    private String zxQy;


    private Integer zxPhOrder;


    private Integer zxOtcflag0;


    private Integer zxOtcflag1;


    private Integer zxOtcflag2;


    private Integer zxOtcflag3;


    private Integer xstranslineid;


    private String xstransname;


    private String zxMemo;


    private String zxEmail;


    private Integer zxWarnday;


    private Integer customertypeDoc;


    private Long zxPhone;
}
