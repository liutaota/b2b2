package com.zsyc.zt.inca.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsyc.zt.inca.entity.BmsCertDtlTmp;
import com.zsyc.zt.inca.vo.BmsCreditBillVo;
import com.zsyc.zt.inca.vo.IncaGoodsCustomVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BmsCertDtlTmpMapper extends BaseMapper<BmsCertDtlTmp> {
    @Select("select billid,owemoney from bms_credit_bill_doc where 1=1  and customid = #{customid} and salerid = #{salerid} and salerdeptid = #{salesdeptid} and entryid = 1")
    BmsCreditBillVo queryByid(@Param("customid") String customid, @Param("salerid") String salerid, @Param("salesdeptid") String salesdeptid);
    @Update("update bms_credit_bill_doc set owemoney =#{owemoney} where billid = #{billid}")
    void updateByBillid(@Param("billid") String billid, @Param("owemoney") Double owemoney);
    @Select("select entryid, customid,goodsid,lastsadtlid,lastsaprice from bms_goods_saprice_custom_ref where entryid=1 and customid=#{customid} and goodsid=#{goodsid}")
    IncaGoodsCustomVo queryByCust(@Param("customid") String customid, @Param("goodsid") String goodsid);
    @Update("update bms_goods_saprice_custom_ref set lastsadtlid=#{lastsadtlid},lastsaprice=#{lastsaprice} where entryid=1 and customid=#{customid} and goodsid=#{goodsid}")
    void updateByPrice(@Param("lastsadtlid") String lastsadtlid, @Param("lastsaprice") Double lastsaprice, @Param("customid") String customid, @Param("goodsid") String goodsid);
    @Update("update bms_sa_doc set zx_kp_flag=1  WHERE salesid = #{salesid}")
    void updateByFlag(@Param("salesid") String salesid);
    @Select("select bms_cert_dtl_tmp_trans_seq.nextval TraId from dual")
    Long getTraId();
    /**
     * 获取transId ：bms_cert_dtl_tmp_trans_seq.nextval
     * @return
     */
    Long getTransactionId();
    /**
     * 获取欠款流水细表seq号
     * @return
     */
   // Long getBmsCertBillDtlSeq();
}
