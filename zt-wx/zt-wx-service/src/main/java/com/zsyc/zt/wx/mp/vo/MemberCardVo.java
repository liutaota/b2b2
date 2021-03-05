package com.zsyc.zt.wx.mp.vo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
public class MemberCardVo {

    private String cardId;
    private String code;
    private String mobile;
    private String sex;
    private LocalDate birthday;
    private String name;

}
