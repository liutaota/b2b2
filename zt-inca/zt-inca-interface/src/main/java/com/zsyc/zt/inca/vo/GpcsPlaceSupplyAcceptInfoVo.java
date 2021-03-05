package com.zsyc.zt.inca.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class GpcsPlaceSupplyAcceptInfoVo {
    private Long transportid;
    private Integer tranmethod;
    private Integer coldequip;
    private String startplace;
    private Double accqty;
    private Double unqualifiedqty;
    private Double shqty;
    private LocalDateTime startdate;
    private LocalDateTime reachdate;
    private Integer days;
    private Double starttemperature;
    private Double reachtemperature;
    private Integer isreach;
    private String accmemo;
    private Integer pending;
}
