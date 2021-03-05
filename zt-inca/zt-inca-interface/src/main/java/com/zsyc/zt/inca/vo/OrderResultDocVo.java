package com.zsyc.zt.inca.vo;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import com.zsyc.framework.util.AssertExt;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author peiqy
 */
@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class OrderResultDocVo extends ResultVo {

    private static final long serialVersionUID = 4906907455350015655L;
    private String orderId;
    private Long customId;

    /**
     * 出入库IDS
     */
    private List<Long> stdDocIds;

    /**
     * 下发到ERP时，无法出库的货品以及数量
     */
    private List<OrderResultDtlVo> undoneDtlList;

    public OrderResultDocVo(String orderId,Long customId) {
        this.orderId = orderId;
        this.customId = customId;
        this.setErrorCode(0);
    }
    public OrderResultDocVo(String orderId,Long customId,String errorMessage) {
        this.orderId = orderId;
        this.customId = customId;
        this.errorMessage = errorMessage;
        this.setErrorCode(1);
    }

    /**
     * 校验输出
     *
     * @param result
     * @return
     */

    public static OrderResultDocVo validReturn(OrderResultDocVo result,String orderId,Long customId) {
        if (ObjectUtil.isNull(result)) {
            return new OrderResultDocVo(orderId,customId);
        }
        return result;
    }

    public static OrderResultDocVo failAppend(OrderResultDocVo result1,OrderResultDocVo result2,String orderId,Long customId) {
        if (ObjectUtil.isNull(result1) && ObjectUtil.isNull(result2)) {
            return new OrderResultDocVo(orderId,customId);
        }

        if (ObjectUtil.isNull(result1) || ObjectUtil.isEmpty(result1.getUndoneDtlList())){
            return  result2;
        }
        if (ObjectUtil.isNull(result2) || ObjectUtil.isEmpty(result2.getUndoneDtlList())){
            return  result1;
        }
        return new OrderResultDocVo(orderId,customId,result1.getErrorMessage() + "\n" + result2.getErrorMessage());
    }


    /**
     * 没有出库的 拼接起来
     *
     * @param resultDocVo
     * @param goodsId
     * @param num
     * @return
     */
    public static OrderResultDocVo undoneAppend(OrderResultDocVo resultDocVo,String orderId,String orderDtlId, Long goodsId, Double num) {


        AssertExt.notNull(resultDocVo, "不能为空");

        if(ObjectUtil.isNull(resultDocVo.getUndoneDtlList())){
            resultDocVo.setUndoneDtlList(new ArrayList<>());
        }
        resultDocVo.getUndoneDtlList().add(new OrderResultDtlVo(orderId,orderDtlId,goodsId,num));

        return resultDocVo;
    }


    /**
     * 没有出库的 拼接起来
     * @return
     */
    public static OrderResultDocVo undoneAppend(OrderResultDocVo result1,OrderResultDocVo result2,String orderId,Long customId) {

        if (ObjectUtil.isNull(result1) && ObjectUtil.isNull(result2)) {
            return new OrderResultDocVo(orderId ,customId);
        }

        if (ObjectUtil.isNull(result1)){
            return  result2;
        }
        if (ObjectUtil.isNull(result2)){
            return  result1;
        }
        List<OrderResultDtlVo> undoneDtlList = ListUtil.toList(CollectionUtil.union(result1.getUndoneDtlList(),result2.getUndoneDtlList()));
        List<Long> stdDocIds = ListUtil.toList(CollectionUtil.union(result1.getStdDocIds(),result2.getStdDocIds()));

        result1.setStdDocIds(stdDocIds);
        result1.setUndoneDtlList(undoneDtlList);

        return result1;
    }

    /**
     * 校验输出
     * @param result
     * @return
     */
    public static OrderResultDocVo validReturn(ResultVo result,String orderId,Long customId){
        OrderResultDocVo orderResult = new OrderResultDocVo(orderId, customId);
        if(ObjectUtil.isNull(result)){
            return orderResult;
        }

        orderResult.setErrorCode(result.getErrorCode());
        orderResult.setErrorMessage(result.getErrorMessage());
        return orderResult;
    }

    public static OrderResultDocVo failErrorMessageAppend(OrderResultDocVo result, String errorMessage,String orderId,Long customId) {
        if (ObjectUtil.isNull(result)) {
            return new OrderResultDocVo(orderId, customId,errorMessage);
        }
        result.setErrorCode(result.getErrorCode()+1);
        result.setErrorMessage(result.getErrorMessage() + "\n" + errorMessage);
        return result;
    }

}
