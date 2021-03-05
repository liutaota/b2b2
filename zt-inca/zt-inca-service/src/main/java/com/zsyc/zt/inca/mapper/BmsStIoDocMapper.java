package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsStIoDoc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-22
 */
@Mapper
public interface BmsStIoDocMapper extends BaseMapper<BmsStIoDoc> {


    BmsStIoDoc save(@Param("inoutId") Long inoutId);
    @Update("update bms_st_io_doc set usestatus = 2,keepmanid =#{inputmanid},keepdate = sysdate,preparestatus = 3 where inoutid = #{inoutId}")
    void updateUsestatus(@Param("inputmanid") Long inputmanid, @Param("inoutId") Long inoutId);

    /**
     * 获取门店的逻辑月
     * @param entryid
     * @return
     */
    @Select("select t.usemm from pub_settle_account t where t.entryid=#{entryid} and t.enddate>sysdate")
    List<Long> getUsemm(@Param("entryid")Long entryid);

    /**
     * 更新货品缺省货架
     *
     * @param posid
     * @param goodsId
     * @param customId
     */
    @Update("update resa_goodstopos set posid = #{posid} where goodsid = #{goodsId} and placepointid = #{customId}")
    void updateGoodstopos(@Param("posid")Long posid, @Param("goodsId")Long goodsId, @Param("customId")Long customId);
}
