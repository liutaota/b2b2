package com.zsyc.zt.inca.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomBusinessScopeVo implements Serializable {

    private Long customId;
    private String customName;
    private Long  scopeId;
    private String  scopeName;
    private Long licenseId;
    private String licenseName;


}
