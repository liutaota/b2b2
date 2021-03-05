package com.zsyc.invoice.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by lcs on 2020/12/10.
 */
@Deprecated
@Data
@Accessors(chain = true)
public class ZtIssueVo implements Serializable {
    /**
     * 结算单号，多个以英文逗号隔开
     */
    private String billCode;
    /**
     * 销方税号
     */
    private String sellerTaxCode;
    /**
     * 票税云账户
     */
    private String username;
    /**
     * 票税云密码
     */
    private String password;
}
