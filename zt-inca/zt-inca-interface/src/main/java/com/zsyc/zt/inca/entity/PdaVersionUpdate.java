package com.zsyc.zt.inca.entity;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import com.zsyc.framework.base.BaseBean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author MP
 * @since 2021-01-13
 */
@ApiModel("PDA版本信息")
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("B2B.PDA_VERSION_UPDATE")
@KeySequence(value = "B2B.SEQ_PDA_VERSION_UPDATE")
public class PdaVersionUpdate extends BaseBean {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @TableId("ID")
    private Long id;

    /**
     * 版本号 
     */
    @ApiModelProperty(value = "版本号 ")
    @TableField("VERSION")
    private String version;

    /**
     * 文件路径
     */
    @ApiModelProperty(value = "文件路径")
    @TableField("FILE_PATH")
    private String filePath;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("CREATE_TIME")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField("UPDATE_TIME")
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
    @TableField("UPDATE_USER_ID")
    private Long updateUserId;

    /**
     * 备注信息
     */
    @ApiModelProperty(value = "备注信息")
    @TableField("REMARK")
    private String remark;

    /**
     * 是否删除：0为否 1为是
     */
    @ApiModelProperty(value = "是否删除：0为否 1为是")
    @TableField("IS_DEL")
    private Long isDel;


}
