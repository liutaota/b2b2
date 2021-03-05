package com.zsyc.zt.inca.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CustomLicenseVo implements Serializable {
    private Long licenseId;
    private Long customId;
    private String customName;
    private String licenseName;
    private LocalDateTime signDate;
    private LocalDateTime validBeginDate;
    private LocalDateTime validEndDate;

}
