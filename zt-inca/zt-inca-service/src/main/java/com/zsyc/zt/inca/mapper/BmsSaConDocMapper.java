package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.zsyc.zt.inca.entity.BmsSaConDoc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BmsSaConDocMapper extends BaseMapper<BmsSaConDoc> {
    @Update("update bms_sa_con_doc set usestatus = 3,approveflag = 1 where conid =#{conId}")
    void updateBy(@Param("conId") Long conId);
}
