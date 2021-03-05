package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsSaSettorec;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.vo.BmsSaSettorecVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-12
 */
public interface BmsSaSettorecMapper extends BaseMapper<BmsSaSettorec> {

    List<BmsSaSettorecVo> selectBySaRecId(@Param("sarecid") Long sarecid);
}
