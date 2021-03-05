package com.zsyc.zt.report.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.zsyc.zt.report.entity.PubMonthlySales;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2021-01-08
 */
@Mapper
public interface PubMonthlySalesMapper extends BaseMapper<PubMonthlySales> {


    IPage<PubMonthlySales> selectPageList(Page page, @Param("salesIdList") List<Long> salesIdList, @Param("customids") String customids, @Param("tomonthss") String tomonthss,@Param("startTime") String startTime, @Param("endTime") String endTime,@Param("customname") String customname);

    List<PubMonthlySales> selectListByTime(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
