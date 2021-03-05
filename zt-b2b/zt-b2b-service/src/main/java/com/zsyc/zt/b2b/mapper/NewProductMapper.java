package com.zsyc.zt.b2b.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsyc.zt.b2b.entity.NewProduct;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.b2b.vo.NewProductVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 新品登记 Mapper 接口
 * </p>
 *
 * @author MP
 * @since 2020-09-16
 */
public interface NewProductMapper extends BaseMapper<NewProduct> {
    /**
     * 新品登记列表
     * @param newProductVo
     * @return
     */
    IPage<NewProductVo> getNewProductList(@Param("page") Page page, @Param("newProductVo") NewProductVo newProductVo);

    /**
     * 前台新品登记列表
     * @param page
     * @param newProductVo
     * @return
     */
    IPage<NewProductVo> getNewProducts(@Param("page") Page page,@Param("newProductVo") NewProductVo newProductVo);

    /**
     * 新品列表导出
     * @param newProductVo
     * @return
     */
    List<NewProductVo> getNewProductExcel(@Param("newProductVo") NewProductVo newProductVo);
}
