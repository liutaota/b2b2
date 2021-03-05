package com.zsyc.zt.inca.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.zt.inca.commom.FaPiaoConstant;
import com.zsyc.zt.inca.entity.B2bFaPiaoConfig;
import com.zsyc.zt.inca.entity.BmsSaDoc;
import com.zsyc.zt.inca.mapper.B2bFaPiaoConfigMapper;
import com.zsyc.zt.inca.mapper.BmsSaDocMapper;
import com.zsyc.zt.inca.vo.BmsSaByFaPiaoVo;
import com.zsyc.zt.inca.vo.FapiaoAddressVo;
import com.zsyc.zt.inca.vo.FapiaoOrderVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@Slf4j
public class FapiaoServiceImpl implements FapiaoService {

    @Autowired
    private BmsSaDocMapper bmsSaDocMapper;

    @Autowired
    private B2bFaPiaoConfigMapper faPiaoConfigMapper;

    //查询公司税号配置
    public  B2bFaPiaoConfig getB2bFaPiaoConfigByKey(String key){
        QueryWrapper<B2bFaPiaoConfig> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(B2bFaPiaoConfig::getTaxKey,key);
        return  faPiaoConfigMapper.selectOne(queryWrapper);
    }

    @Override
    public FapiaoOrderVo getKpInfo(Long b2bOrderId) {
        //查询发货单号
        BmsSaByFaPiaoVo bmsSaByFaPiaoVo =  bmsSaDocMapper.getBmsSaDocListByB2bOrderId(b2bOrderId);
        AssertExt.notNull(bmsSaByFaPiaoVo,"根据订单id查询到的发货单为空");
        FapiaoOrderVo rs = new FapiaoOrderVo();
        rs.setErpOrderIdList(bmsSaByFaPiaoVo.getErpOrderIdList());;
        rs.setB2bOrderId(bmsSaByFaPiaoVo.getB2bOrderId());
        rs.setB2bOrderNo(bmsSaByFaPiaoVo.getB2bOrderNo());
        //获取税号配置
        B2bFaPiaoConfig b2bFaPiaoConfig = getB2bFaPiaoConfigByKey(FaPiaoConstant.GDZTYY_FLAY);
        BeanUtils.copyProperties(b2bFaPiaoConfig,rs);
        return rs;
    }

    @Override
    public List<FapiaoOrderVo> getKpInfo(List<Long> b2bOrderIdList) {
        //查询发货单号
        List<BmsSaByFaPiaoVo> bmsSaByFaPiaoVos =  bmsSaDocMapper.getBmsSaDocListByB2bOrderIdList(b2bOrderIdList);
        if(bmsSaByFaPiaoVos.size()==0){
            AssertExt.fail("根据订单id集合查询到的发货单为空",b2bOrderIdList);
        }
        //获取税号配置
        B2bFaPiaoConfig b2bFaPiaoConfig =getB2bFaPiaoConfigByKey(FaPiaoConstant.GDZTYY_FLAY);

        //拼接返回数据
        List<FapiaoOrderVo> rs = new ArrayList<>(bmsSaByFaPiaoVos.size());

        b2bOrderIdList.forEach(item->{
            rs.add(getKpInfo(item));
        });

        return rs;
    }

    @Override
    public FapiaoAddressVo getKpAddressInfo(Long b2bOrderId) {
        //查询发货单号
        List<Long> erpOrderIdList =  bmsSaDocMapper.erpOrderIdListByOrderId(b2bOrderId);
        if(erpOrderIdList.size()==0){
            AssertExt.fail("根据订单id查询到的发货单为空",b2bOrderId);
        }
        FapiaoAddressVo rs = new FapiaoAddressVo();

        //获取税号配置 进行数据拼接
        B2bFaPiaoConfig b2bFaPiaoConfig =getB2bFaPiaoConfigByKey(FaPiaoConstant.GDZTYY_FLAY);
        rs.setB2bOrderId(b2bOrderId);
        rs.setErpOrderIdList(erpOrderIdList);
        StringBuilder address = new StringBuilder(b2bFaPiaoConfig.getTaxUrl());
        for (long  temp : erpOrderIdList){
            address.append(temp+",");
        }
        rs.setAddress(address.toString());
        return rs;
    }

    @Override
    public List<FapiaoAddressVo> getKpAddressInfo(List<Long> b2bOrderIdList) {
        List<FapiaoAddressVo> rs = new ArrayList<>();
        for (Long aLong : b2bOrderIdList) {
            rs.add(this.getKpAddressInfo(aLong));
        }
        return rs;
    }
}
