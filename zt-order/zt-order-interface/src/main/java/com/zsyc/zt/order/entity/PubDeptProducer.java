package com.zsyc.zt.order.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("PUB_DEPT_PRODUCER")
public class PubDeptProducer extends Model<PubDeptProducer> {

    private static final long serialVersionUID = 1L;

    @TableId("PRODUCERID")
    private Long producerid;

    @TableField("PRODUCERNO")
    private String producerno;

    @TableField("USESTATUS")
    private Integer usestatus;

    @TableField("MASTERID")
    private Long masterid;

    @TableField("PRODUCEROPCODE")
    private String produceropcode;

    @TableField("INPUTMANID")
    private Long inputmanid;

    @TableField("CRETDATE")
    private LocalDateTime cretdate;

    @TableField("DEPTMEMO")
    private String deptmemo;

    @TableField("PRODUCERTYPE")
    private Long producertype;


    @Override
    protected Serializable pkVal() {
        return this.producerid;
    }

}
