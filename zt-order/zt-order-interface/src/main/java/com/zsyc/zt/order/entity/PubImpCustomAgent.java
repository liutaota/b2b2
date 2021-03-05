package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("PUB_IMP_CUSTOM_AGENT")
public class PubImpCustomAgent extends Model<PubImpCustomAgent> {

    private static final long serialVersionUID = 1L;

    @TableField("DOCID")
    private Long docid;

    @TableId("DTLID")
    private Long dtlid;

    @TableField("IMPSTATUS")
    private Integer impstatus;

    @TableField("ERRORMESSAGE")
    private String errormessage;

    @TableField("NEWID")
    private Long newid;

    @TableField("CUSTOMNAME")
    private String customname;

    @TableField("ENTRYNAME")
    private String entryname;

    @TableField("AGENTNAME")
    private String agentname;

    @TableField("AGENTOPCODE")
    private String agentopcode;

    @TableField("PINYIN")
    private String pinyin;

    @TableField("SEX")
    private String sex;

    @TableField("BIRTHDATE")
    private String birthdate;

    @TableField("IDCARD")
    private String idcard;

    @TableField("EMAIL")
    private String email;

    @TableField("PHONE")
    private String phone;

    @TableField("MOBILENO")
    private String mobileno;

    @TableField("ADDRESS")
    private String address;

    @TableField("MEMO")
    private String memo;

    @TableField("USESTATUSNAME")
    private String usestatusname;

    @TableField("ZXCOLUMN01")
    private String zxcolumn01;

    @TableField("ZXCOLUMN02")
    private String zxcolumn02;

    @TableField("ZXCOLUMN03")
    private String zxcolumn03;

    @TableField("ZXCOLUMN04")
    private String zxcolumn04;

    @TableField("ZXCOLUMN05")
    private String zxcolumn05;

    @TableField("ZXCOLUMN06")
    private String zxcolumn06;

    @TableField("ZXCOLUMN07")
    private String zxcolumn07;

    @TableField("ZXCOLUMN08")
    private String zxcolumn08;

    @TableField("ZXCOLUMN09")
    private String zxcolumn09;

    @TableField("ZXCOLUMN10")
    private String zxcolumn10;

    @TableField("ZXCOLUMN11")
    private String zxcolumn11;

    @TableField("ZXCOLUMN12")
    private String zxcolumn12;

    @TableField("ZXCOLUMN13")
    private String zxcolumn13;

    @TableField("ZXCOLUMN14")
    private String zxcolumn14;

    @TableField("ZXCOLUMN15")
    private String zxcolumn15;

    @TableField("ZXCOLUMN16")
    private String zxcolumn16;

    @TableField("ZXCOLUMN17")
    private String zxcolumn17;

    @TableField("ZXCOLUMN18")
    private String zxcolumn18;

    @TableField("ZXCOLUMN19")
    private String zxcolumn19;

    @TableField("ZXCOLUMN20")
    private String zxcolumn20;

    @TableField("DATEFROM")
    private String datefrom;

    @TableField("DATETO")
    private String dateto;

    @TableField("CTRLTYPE")
    private String ctrltype;

    @TableField("CUSTOMID")
    private Long customid;

    @TableField("ENTRYID")
    private Long entryid;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("ENTRYCOMPANYID")
    private Long entrycompanyid;

    @TableField("CTRLTYPEDDLID")
    private Integer ctrltypeddlid;

    @TableField("FORBIDBUSINESS")
    private String forbidbusiness;

    @TableField("FORBIDBACK")
    private String forbidback;


    @Override
    protected Serializable pkVal() {
        return this.dtlid;
    }

}
