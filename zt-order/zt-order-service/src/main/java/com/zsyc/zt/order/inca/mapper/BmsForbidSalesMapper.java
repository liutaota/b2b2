package com.zsyc.zt.order.inca.mapper;

import com.zsyc.zt.order.entity.BmsForbidSales;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author peiqy
 * @since 2020-08-18
 */
public interface BmsForbidSalesMapper extends BaseMapper<BmsForbidSales> {

    /**
     *
     * 判断禁销
     * @param customId
     * @param goodsId
     * @param entryId
     * @param useStatus
     * @return
     */
    List<BmsForbidSales> selectForbidBy(@Param("customId")Long customId, @Param("goodsId")Long goodsId, @Param("entryId")int entryId, @Param("useStatus")int useStatus);





    /**
     * 判断仅销
     * @param customId
     * @param goodsId
     * @param entryId
     * @param useStatus
     * @return
     */
    List<BmsForbidSales> selectRestrictBy(@Param("customId")Long customId, @Param("goodsId")Long goodsId, @Param("entryId")int entryId, @Param("useStatus")int useStatus);

    List<Long> selectForbidGoods(@Param("customId")Long customId, @Param("entryId")Integer entryId,  @Param("useStatus")int useStatus);

    /**
     * 非本客户的仅销商品
     * @param customId
     * @param entryId
     * @param useStatus
     * @return
     */
    List<Long> selectRestrictGoodsForOther(@Param("customId")Long customId, @Param("entryId")Integer entryId,  @Param("useStatus")int useStatus);

    /**
     * 本客户的仅销商品
     * @param customId
     * @param entryId
     * @param useStatus
     * @return
     */
    List<Long> selectRestrictGoods(@Param("customId")Long customId, @Param("entryId")Integer entryId,  @Param("useStatus")int useStatus);

}
