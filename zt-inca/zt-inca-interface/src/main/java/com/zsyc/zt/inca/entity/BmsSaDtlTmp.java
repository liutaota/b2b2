package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Param;

@Data

@Accessors(chain = true)
@TableName("BMS_SA_DTL_TMP")
@ApiModel(value="BmsSaDtlTmp对象", description="")
public class BmsSaDtlTmp {
    @TableField("salesdtlid")
    private Long salesdtlid;
    @TableField("zx_errmsg")
    private String zxErrmsg;
    @TableField("zx_makeinvflag")
    private Integer zxMakeInvflag;
}
