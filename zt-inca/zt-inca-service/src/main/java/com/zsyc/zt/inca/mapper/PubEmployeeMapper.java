package com.zsyc.zt.inca.mapper;

import com.zsyc.zt.inca.entity.PubEmployee;
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
public interface PubEmployeeMapper extends BaseMapper<PubEmployee> {

    /**
     * 获取员工信息
     * pub_employee
     * @param inputManId
     * @return
     */
    List<Map<String, Object>> getInputManInfo(Long inputManId);

    //获取员工信息 jie
    PubEmployee getPubEmployee(@Param("inputManId") Long inputManId);

    String getEmployeename(@Param("salerid") Long salerid);
}
