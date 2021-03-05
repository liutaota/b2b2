package com.zsyc.zt.b2b.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.zt.inca.entity.BmsTrPosDef;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "BmsTrPosDefVo对象", description = "运输地址")
public class BmsTrPosDefVo  extends BmsTrPosDef {

    @TableField("运输路线")
    private String translinename;

    @TableField("线序")
    private Long  zxPhOrder;

    @TableField("客户名称")
    private String companyname;
}
