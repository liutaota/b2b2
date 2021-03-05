package com.zsyc.zt.inca.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class BmsSaSettorecVo {

    private Long sasettledtlid;

    private Long sarecdtlid;

    private Double goodsqty;

    private Double totalLine;

    private Long salesdtlid;
}
