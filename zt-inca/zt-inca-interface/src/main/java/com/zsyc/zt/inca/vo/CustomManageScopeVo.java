package com.zsyc.zt.inca.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 公司证照  经营范围
 * @author peiqy
 */
@Data
@Accessors(chain = true)
public class CustomManageScopeVo{

    private Long licenseid;

    private Long companyid;

    private Long inputmanid;

    private LocalDateTime credate;

    private Long entryid;

    private String relatcompany;

    private Long licensetypeid;

    private String licensecode;

    private String relatgoods;

    private String maincontent;

    private LocalDateTime signdate;

    private String signdepartment;

    private LocalDate validondate;

    private LocalDate validenddate;

    private String filesopcode;

    private Integer usestatus;

    private String goodsbusiscope;

    private String goodsclasscope;

    private String memo;

    private String goodsbusiscopeids;

    private String goodsclasscopeids;

    private Integer stopreason;

    private String memo2;

    private Long oldLicenseid;

    private Date sysModifydate;

    private Long classid;

    private Long classtypeid;

    private String classno;

    private String classname;

    private Integer leafflag;

    private Long parentclassid;


    private Long classnRow;

}
