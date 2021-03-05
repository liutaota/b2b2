package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsStorerPos;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-20
 */
@Mapper
public interface BmsStorerPosMapper extends BaseMapper<BmsStorerPos> {

    /**
     * 获取商品货架信息
     * @param goodsid
     * @param customId
     * @return
     */
    @Select("select pos.* from bms_storer_pos pos," +
            "resa_goodstopos goodstopos " +
            "where pos.posid=goodstopos.posid(+) " +
            "and goodstopos.goodsid=#{goodsid} and goodstopos.placepointid=#{customId}")
    List<BmsStorerPos> getBmsStorerPos(@Param("goodsid")Long goodsid, @Param("customId")Long customId);
}
