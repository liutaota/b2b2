package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.BmsSaRecDoc;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-08-12
 */
public interface BmsSaRecDocMapper extends BaseMapper<BmsSaRecDoc> {
    /**
     * 获取客户信息
     * @param customId 客户id
     * @return
     */
    List<Map<String, Object>> getCustomInfo(Long customId);

    /**
     * 计算收款单的收款额跟细单金额
     * @param sarecid
     * @param total
     * @param premoney
     */
    void upBmsSaRecDocMoney(@Param("sarecid")Long sarecid, @Param("total")Double total, @Param("premoney") Double premoney);
}
