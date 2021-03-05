package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.ArrivalNotice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.ArrivalNoticeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 缺货登记 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-07-29
 */
public interface ArrivalNoticeMapper extends BaseMapper<ArrivalNotice> {
    /**
     * 缺货列表
     * @param page
     * @param arrivalNoticeVo
     * @return
     */
    IPage<ArrivalNoticeVo> getArrivalNoticeList(@Param("page") Page page, @Param("arrivalNoticeVo") ArrivalNoticeVo arrivalNoticeVo);


    /**
     * 到货通知列表
     *
     * @param memberId
     * @return
     */
    List<ArrivalNoticeVo> getMemberArrivalNoticeList(@Param("memberId") Long memberId);

    /**
     * 前台缺货列表
     * @param page
     * @param arrivalNoticeVo
     * @return
     */
    IPage<ArrivalNoticeVo> getPcArrivalNoticeList(@Param("page") Page page, @Param("arrivalNoticeVo") ArrivalNoticeVo arrivalNoticeVo);

    /**
     * 缺货列表导出
     * @param arrivalNoticeVo
     * @return
     */
    List<ArrivalNoticeVo> getArrivalNoticeListExcel(@Param("arrivalNoticeVo") ArrivalNoticeVo arrivalNoticeVo);
}
