package com.zsyc.zt.inca.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2021-01-13
 */
@ApiModel("PDA版本-前端")
@Data
public class PdaVersionUpdateVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 版本号 
     */
    @ApiModelProperty(value = "版本号 ")
    private String version;

    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    private String filePath;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    /**
     * 创建用户id
     */
    @ApiModelProperty(value = "创建用户id")
    @TableField("CREATE_USER_ID")
    private Long createUserId;

    /**
     * 修改用户id
     */
    @ApiModelProperty(value = "修改用户id")
    private Long updateUserId;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    private String remark;

    /**
     * 是否删除：0为否 1为是
     */
    @ApiModelProperty(value = "是否删除：0为否 1为是")
    private Long isDel;

    @ApiModelProperty(value = "开始查询日期" , name = "startDate")
    private LocalDateTime startDate;

    @ApiModelProperty(value = "结束查询日期" , name = "endDate")
    private LocalDateTime endDate;
}
