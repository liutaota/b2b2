package com.zsyc.zt.inca.vo;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zsyc.framework.util.AssertExt;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author peiqy
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OrderBackResultDocVo extends ResultVo {

    private static final long serialVersionUID = 4906907455350015655L;
    private String orderId;
    private Long customId;

    /**
     * 出入库IDS
     */
    private List<Long> stdDocIds;

    /**
     * 未完成的订单详情
     */
    private List<OrderBackResultDtlVo> undoneDtlList;

    public OrderBackResultDocVo(String orderId, Long customId) {
        this.orderId = orderId;
        this.customId = customId;
        this.setErrorCode(0);
    }

    /**
     * 校验输出
     *
     * @param result
     * @return
     */

    public static OrderBackResultDocVo validReturn(OrderBackResultDocVo result, String orderId, Long customId) {
        if (ObjectUtil.isNull(result)) {
            return new OrderBackResultDocVo(orderId,customId);
        }
        return result;
    }


    /**
     * 没有出库的 拼接起来
     *
     * @param resultDocVo
     * @param goodsId
     * @param num
     * @return
     */
    public static OrderBackResultDocVo undoneAppend(OrderBackResultDocVo resultDocVo, String orderId, String orderDtlId, Long goodsId, Double num) {


        AssertExt.notNull(resultDocVo, "不能为空");

        if(ObjectUtil.isNull(resultDocVo.getUndoneDtlList())){
            resultDocVo.setUndoneDtlList(new ArrayList<>());
        }
        resultDocVo.getUndoneDtlList().add(new OrderBackResultDtlVo(orderId,orderDtlId,goodsId,num));

        return resultDocVo;
    }


    /**
     * 没有出库的 拼接起来
     * @return
     */
    public static OrderBackResultDocVo undoneAppend(OrderBackResultDocVo result1, OrderBackResultDocVo result2, String orderId, Long customId) {

        if (ObjectUtil.isNull(result1) && ObjectUtil.isNull(result2)) {
            return new OrderBackResultDocVo(orderId ,customId);
        }

        if (ObjectUtil.isNull(result1)){
            return  result2;
        }
        if (ObjectUtil.isNull(result2)){
            return  result1;
        }
        List<OrderBackResultDtlVo> undoneDtlList = ListUtil.toList(CollectionUtil.union(result1.getUndoneDtlList(),result2.getUndoneDtlList()));
        List<Long> stdDocIds = ListUtil.toList(CollectionUtil.union(result1.getStdDocIds(),result2.getStdDocIds()));

        result1.setStdDocIds(stdDocIds);
        result1.setUndoneDtlList(undoneDtlList);

        return result1;
    }


}
