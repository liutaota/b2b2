package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.DayBill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 每日报表 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-12-02
 */
public interface DayBillMapper extends BaseMapper<DayBill> {

    /**
     * 查询昨天订单数据
     */
    DayBill selectDayBill();

    /**
     * 每日报表列表
     */
    IPage<DayBill> getDayBillList(@Param("page") Page page, @Param("dayBill") DayBill dayBill);
}
