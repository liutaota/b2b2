package com.zsyc.test;

import com.abc.pay.client.TrxException;
import com.abc.pay.client.ebus.PaymentResult;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

/**
 * Created by lcs on 2019-01-02.
 */
@Slf4j
public class TT {

	@Test
	public void testChart(){
		Assert.isTrue(Pattern.matches("^[\\d\\w\\-]{2,4}$", "18996"));
	}

	@Test
	public void testRandomStringUtils(){
		System.out.println("--------------------------");
		System.out.println(RandomStringUtils.random(10));
		System.out.println(RandomStringUtils.random(10, false, true));
		System.out.println(RandomStringUtils.random(10, true, false));
		System.out.println(RandomStringUtils.random(10, true, true));

		System.out.println("--------------------------");
		System.out.println(RandomStringUtils.random(10));
		System.out.println(RandomStringUtils.random(10, false, true));
		System.out.println(RandomStringUtils.random(10, true, false));
		System.out.println(RandomStringUtils.random(10, true, true));

		System.out.println("--------------------------");
		System.out.println(RandomStringUtils.random(10));
		System.out.println(RandomStringUtils.random(10, false, true));
		System.out.println(RandomStringUtils.random(10, true, false));
		System.out.println(RandomStringUtils.random(10, true, true));
	}

	@Test
	public void testLocalDate(){
		System.out.println(LocalDateTime.now());
	}


	@Test
	public void test() throws TrxException {
		String msg = "PE1TRz48TWVzc2FnZT48VHJ4UmVzcG9uc2U+PFJldHVybkNvZGU+MDAwMDwvUmV0dXJuQ29kZT48RXJyb3JNZXNzYWdlPr270tezybmmPC9FcnJvck1lc3NhZ2U+PEVDTWVyY2hhbnRUeXBlPkVCVVM8L0VDTWVyY2hhbnRUeXBlPjxNZXJjaGFudElEPjEwMzg4NDQ1MDAwMDE4NjwvTWVyY2hhbnRJRD48VHJ4VHlwZT5BQkNTb2Z0VG9rZW5QYXk8L1RyeFR5cGU+PE9yZGVyTm8+enNfMTYwNTE1MjY0MDA1ODwvT3JkZXJObz48QW1vdW50PjEuMDA8L0Ftb3VudD48QmF0Y2hObz4wMDA0MzA8L0JhdGNoTm8+PFZvdWNoZXJObz4wMDA5NTI8L1ZvdWNoZXJObz48SG9zdERhdGU+MjAyMC8xMS8xMjwvSG9zdERhdGU+PEhvc3RUaW1lPjExOjUzOjQ0PC9Ib3N0VGltZT48UGF5VHlwZT5FUDA3MjwvUGF5VHlwZT48Tm90aWZ5VHlwZT4xPC9Ob3RpZnlUeXBlPjxpUnNwUmVmPkJDRUNFUDAxMTE1MTM5NDI2NTU4PC9pUnNwUmVmPjxBY2NEYXRlPjIwMjAxMTEyPC9BY2NEYXRlPjxBY3FGZWU+MC4wMDwvQWNxRmVlPjxJc3NGZWU+MC4wMDwvSXNzRmVlPjxKcm5Obz4gMzMyOTUxMzY5PC9Kcm5Obz48L1RyeFJlc3BvbnNlPjwvTWVzc2FnZT48U2lnbmF0dXJlLUFsZ29yaXRobT5TSEExd2l0aFJTQTwvU2lnbmF0dXJlLUFsZ29yaXRobT48U2lnbmF0dXJlPjk3QUJnSGUwWXlCd0lsdTBwQno5SndZZlY3VTBDNG52MHRqSlpQLzd1L1JLamcrUjlVb1BXQ0VoczNHQVVGOGgxQlROYVZoazYyMUw2V3E1Z3pIZHFzV3g1SHpYNXlQd3NBOExqY2VDK0x4VFJ2bndBOW10am8wQ2N0U3VnRmNab1J5czN0NzFYRndTTVhzcG1VaEhsS3oxZ3NNM3NBUU42bzF1b2Z1UVNCWT08L1NpZ25hdHVyZT48L01TRz4=";
		log.info("{}", JSONObject.toJSONString(new PaymentResult(msg)), true);
		PaymentResult tResult = new PaymentResult(msg);
		log.info("TrxType {}", tResult.getValue("TrxType"));;
		log.info("OrderNo {}", tResult.getValue("OrderNo"));;
		log.info("Amount {}", tResult.getValue("Amount"));;
		log.info("BatchNo {}", tResult.getValue("BatchNo"));;
		log.info("VoucherNo {}", tResult.getValue("VoucherNo"));;
		log.info("HostDate {}", tResult.getValue("HostDate"));;
		log.info("HostTime {}", tResult.getValue("HostTime"));;
		log.info("MerchantRemarks {}", tResult.getValue("MerchantRemarks"));;
		log.info("PayType {}", tResult.getValue("PayType"));;
		log.info("NotifyType {}", tResult.getValue("NotifyType"));;
		log.info("iRspRef {}", tResult.getValue("iRspRef"));;
		log.info("bank_type {}", tResult.getValue("bank_type"));;
		log.info("ThirdOrderNo {}", tResult.getValue("ThirdOrderNo"));;
	}

}
