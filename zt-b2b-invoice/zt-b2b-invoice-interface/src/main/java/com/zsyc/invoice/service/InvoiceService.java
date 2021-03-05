package com.zsyc.invoice.service;

import com.zsyc.invoice.vo.ZtIssueVo;

/**
 * Created by lcs on 2020/10/9.
 */
public interface InvoiceService {

    /**
     * 电子发票开具
     * @param billCode 结算单号，多个以英文逗号隔开
     * @param sellerTaxCode 销方税号
     * @param username 票税云账户
     * @param password 票税云密码
     */
    String ztissue(String billCode,String sellerTaxCode,String username,String password);


}
