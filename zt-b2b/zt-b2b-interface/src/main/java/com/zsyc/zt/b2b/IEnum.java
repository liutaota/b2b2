package com.zsyc.zt.b2b;

/**
 * Created by lcs on 2019-02-02.
 */
public interface IEnum/* extends com.baomidou.mybatisplus.core.enums.IEnum<String> */ {
    /**
     * 中文说明
     *
     * @return
     */
    String desc();

    /**
     * 枚举内容
     *
     * @return
     */
    String val();

//	default String getValue(){
//		return this.val();
//	}
}


