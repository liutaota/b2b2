package com.zsyc.zt.inca.vo;

import lombok.Data;

@Data
public class IncaAddressVo {

    private Long tranposid;

    private Integer translineid;

    private int entryid;

    private String address;


    private Integer presoutid;

    private String credate;
    private Integer comefrom;
    private Integer sourcetable;
    private String sourceid;
    private Integer inputmanid;

    private String targetposid;

}
