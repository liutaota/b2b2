package com.zsyc.zt.order.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("PUB_EMPLOYEE_IMP")
public class PubEmployeeImp extends Model<PubEmployeeImp> {

    private static final long serialVersionUID = 1L;

    @TableField("EMPLOYEEID")
    private Long employeeid;

    @TableField("OPCODE")
    private String opcode;

    @TableField("EMPLOYEENAME")
    private String employeename;

    @TableField("PINYIN")
    private String pinyin;

    @TableField("WEBPASS")
    private String webpass;

    @TableField("SEX")
    private String sex;

    @TableField("BIRTHDATE")
    private LocalDateTime birthdate;

    @TableField("IDCARD")
    private String idcard;

    @TableField("DEPTID")
    private Long deptid;

    @TableField("MEMO")
    private String memo;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("EMAIL")
    private String email;

    @TableField("PHONE")
    private String phone;

    @TableField("MOBILENO")
    private String mobileno;

    @TableField("ADDRESS")
    private String address;

    @TableField("EDULEVEL")
    private Integer edulevel;

    @TableField("SPECIALTY")
    private String specialty;

    @TableField("JOBEDU")
    private String jobedu;

    @TableField("STATION")
    private String station;

    @TableField("SELFFLAG")
    private Integer selfflag;

    @TableField("FINANCENO")
    private String financeno;

    @TableField("GOODSOWNERID")
    private Long goodsownerid;

    @TableField("WAREHID")
    private Long warehid;

    @TableField("HISCOMPANYID")
    private Long hiscompanyid;

    @TableField("BP")
    private String bp;

    @TableField("PDAPASS")
    private String pdapass;

    @TableField("WAREHIDS")
    private String warehids;

    @TableField("POSTID")
    private Long postid;

    @TableField("EMPLOYEENO")
    private String employeeno;

    @TableField("ENTRYTIME")
    private LocalDateTime entrytime;

    @TableField("LEAVEJOBDATE")
    private LocalDateTime leavejobdate;

    @TableField("LEAVEJOBSTATUS")
    private Long leavejobstatus;

    @TableField("MD5COUNT")
    private Long md5count;

    @TableField("USERNAME")
    private String username;

    @TableField("LOGINDATE")
    private LocalDateTime logindate;

    @TableField("LOGINTIMES")
    private Long logintimes;

    @TableField("ERRORNUM")
    private Long errornum;

    @TableField("INITPWD")
    private String initpwd;

    @TableField("EMAILFLAG")
    private Integer emailflag;

    @TableField("EMAILDATE")
    private LocalDateTime emaildate;

    @TableField("EMAILMANID")
    private Long emailmanid;

    @TableField("ENTRYID")
    private Integer entryid;

    @TableField("SYS_MODIFYDATE")
    private LocalDateTime sysModifydate;

    @TableField("TOWMSDATE")
    private LocalDateTime towmsdate;

    @TableField("ZX_FLEID")
    private Integer zxFleid;

    @TableField("ZX_FLNAME")
    private String zxFlname;

    @TableField("ZX_ATYPE")
    private BigDecimal zxAtype;

    @TableField("ZX_CTYPE")
    private BigDecimal zxCtype;

    @TableField("ZX_DTYPE")
    private BigDecimal zxDtype;

    @TableField("ZX_ROLENAME")
    private Long zxRolename;


    @Override
    protected Serializable pkVal() {
        return null;
    }

}
