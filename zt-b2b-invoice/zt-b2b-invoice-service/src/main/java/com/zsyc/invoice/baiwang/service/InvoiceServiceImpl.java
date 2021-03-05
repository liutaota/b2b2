package com.zsyc.invoice.baiwang.service;

import com.alibaba.fastjson.JSONObject;
import com.zsyc.framework.util.AssertExt;
import com.zsyc.framework.util.HttpUtil;
import com.zsyc.invoice.baiwang.config.BaiWangProperties;
import com.zsyc.invoice.service.InvoiceService;
import com.zsyc.invoice.vo.ZtIssueVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lcs on 2020/10/9.
 */
@Service
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {

    private static final Integer SUCCESS_CODE = 0;
    @Autowired
    private BaiWangProperties baiWangProperties;

    @Override
    public String ztissue(String billCode,String sellerTaxCode,String username,String password ) {
        AssertExt.notBlank(billCode,"no bill code");
        AssertExt.notBlank(sellerTaxCode,"no seller tax code");
        AssertExt.notBlank(username,"no username");
        AssertExt.notBlank(password,"no password");

        JSONObject param = new JSONObject();
        param.put("BILL_CODE", billCode);
        param.put("SELLER_TAX_CODE", sellerTaxCode);
        param.put("USERNAME", username);
        param.put("PASSWORD", password);

        String api = String.format("%s/ztissue", this.baiWangProperties.getApi());
        JSONObject result = HttpUtil.httpPost(api, param.toJSONString());

        AssertExt.isTrue(SUCCESS_CODE.equals(result.getInteger("code")), result.getString("msg"));
        return result.toString();
    }
}
