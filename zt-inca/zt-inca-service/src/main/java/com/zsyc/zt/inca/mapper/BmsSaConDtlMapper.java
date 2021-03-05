package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.entity.BmsSaConDoc;
import com.zsyc.zt.inca.entity.BmsSaConDtl;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BmsSaConDtlMapper extends BaseMapper<BmsSaConDtl> {
    List<BmsSaConDtl> selectListBy(@Param("salesId") Long salesId);
}
