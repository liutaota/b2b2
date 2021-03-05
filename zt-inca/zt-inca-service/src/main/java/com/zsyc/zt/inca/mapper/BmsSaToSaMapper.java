package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.entity.BmsSaToSa;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 * 新增 jie
 * @author peiqy
 * @since 2020-12-03
 */
@Mapper
public interface BmsSaToSaMapper extends BaseMapper<BmsSaToSa> {

    //获取销售退货细单
    @Select("select sadtlid2 from bms_sa_to_sa where sadtlid1 = #{saDtlId}")
    Long getsadtlid2(@Param("saDtlId") Long saDtlId);

}
