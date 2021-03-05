package com.zsyc.zt.b2b.common;

import cn.hutool.core.util.NumberUtil;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by lcs on 2020/8/11.
 */
public class IncaUtils {
    /**
     * 转成ERP金额单位
     * 分 => 元
     *
     * @param price
     * @return
     */
    public static double toErpPrice(double price) {
        return NumberUtil.round(price,0).doubleValue();
    }

    /**
     * 转成ERP金额单位
     * 分 => 元
     * 四舍五入 保留两位
     *
     * @param price
     * @return
     */
    public static double toErpPriceDouble(double price) {

       /* double d = new BigDecimal((double) price / 100).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();

        int i = (int) (d * 100);
        return i;*/

      /*  double al = price % 100;
        if (price.toString().length() > 2) {
            Long newPrice = Long.valueOf(price.toString().substring(0, price.toString().length() - 2));

            if (al >= 50) {
                newPrice += 1;
            }

            return newPrice * 100;
        }*/
        //return new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
        return NumberUtil.round(price,2).doubleValue();
    }

    /**
     * 转成ERP金额单位
     * 分 => 元
     * 四舍五入 保留两位
     *
     * @param price
     * @return
     */
    public static int toErpPriceDouble2(double price) {
        // String s = String.format("%.2f", (double) price / 10000);
        // return Double.parseDouble(s);
       // return new BigDecimal(price*100).setScale(2, BigDecimal.ROUND_HALF_UP).intValue();
        return NumberUtil.round(price*100,2).intValue();
    }


    /**
     * 转成ERP金额单位
     * 分 => 元
     * 四舍五入 保留4位
     *
     * @param price
     * @return
     */
    public static double toErpPriceDouble4(double price) {
        // String s = String.format("%.2f", (double) price / 10000);
        // return Double.parseDouble(s);
      //  return new BigDecimal(price).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
        return NumberUtil.round(price,4).doubleValue();
    }
}
