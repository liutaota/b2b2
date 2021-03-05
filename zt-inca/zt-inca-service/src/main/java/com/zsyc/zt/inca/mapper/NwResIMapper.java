package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zsyc.zt.inca.dto.NwResIDto;
import com.zsyc.zt.inca.entity.NwResI;
import com.zsyc.zt.inca.vo.NwResIVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-11-25
 */
@Mapper
public interface NwResIMapper extends BaseMapper<NwResI> {

       List<NwResIVo> getByErpId(@Param("erpId") String erpId);

       List<NwResIVo> getByPackId(@Param("packId") String packId);

       IPage<NwResIVo> selectNwResI(IPage<NwResIVo> page, NwResIDto nwResIDto);

       /**
        * 查询packId对应的客户集
        * @param packId
        * @return
        */
       NwResIVo getCustomByPackId(@Param("packId") String packId);

       /**
        * 根据销售单号和箱号 查询记录
        * @param erpId
        * @param packId
        * @return
        */
       List<NwResIVo> selectListByErpIdPackId(@Param("erpId") String erpId, @Param("packId") String packId);

       /**
        * 查询当前用户剩余未录入箱号
        * @param customId
        * @return
        */
       List<String> querySurplusPackId(@Param("customId") Long customId);
}
