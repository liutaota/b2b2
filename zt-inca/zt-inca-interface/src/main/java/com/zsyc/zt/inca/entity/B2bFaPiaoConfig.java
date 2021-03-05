package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("B2B_FAPIAO_CONFIG")
public class B2bFaPiaoConfig {

    @TableField("TAX_NO")
    private String taxNo;

    /**
     * 连接fapiao项目的用户名
     */
    @TableField("USERNAME")
    private String username;
    /**
     * 连接fapiao项目的密码
     */
    @TableField("PASSWORD")
    private String password;

    @TableField("TAX_KEY")
    private String taxKey;

    @TableField("TAX_URL")
    private String taxUrl;

}
