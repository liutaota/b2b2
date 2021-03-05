package com.zsyc.zt.b2b.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("B2B_ERP_SYNC_CONFIG")
@ApiModel(value="B2b同步ERP数据配置表", description="B2b同步ERP数据配置表")
public class B2bErpSyncConfig extends Model<B2bErpSyncConfig> {
    @TableId("id")
    private Long id;
    private String varName;
    private String varValue;
    private String varDesc;
}
