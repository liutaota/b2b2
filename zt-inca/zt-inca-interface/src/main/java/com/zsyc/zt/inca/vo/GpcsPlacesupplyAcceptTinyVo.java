package com.zsyc.zt.inca.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GpcsPlacesupplyAcceptTinyVo {
    private Long placepointid;
    private Long goodsid;
    private Long goodsdtlid;
    private Double goodsqty;
    private Double unitprice;
    private Long lotid;
    private Long batchid;
    private String goodsunit;
    private Long storageid;
    private Long placecenterid;
    private Integer presstockflag;
    private Integer accflag;
    private Integer presendflag;
    private Long updatepos;
    private Long placesupplydtlid;
    private String memo;
    private Double postplaceprice;
    private Long placeentryid;
    private Long placesupplydtlstid;
    private Long placepriceid;
}
