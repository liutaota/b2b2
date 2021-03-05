# zt-b2b-service

## 配置说明 

| Key  | Default Value | Description | Type | Source Type |
| :--- | :------------ | :---------- | :--- | :---------- |
| zsyc.invoice.baiwang.api |   | api 接口地址  | java.lang.String | com.zsyc.invoice.baiwang.config.BaiWangProperties  |
| zsyc.invoice.baiwang.invoice-api |   | 电子发票接口地址  | java.lang.String | com.zsyc.invoice.baiwang.config.BaiWangProperties  |
| zsyc.invoice.baiwang.sn |   | sn  | java.lang.String | com.zsyc.invoice.baiwang.config.BaiWangProperties  |
| zsyc.invoice.baiwang.xsf-mc |   | 销售方名称  | java.lang.String | com.zsyc.invoice.baiwang.config.BaiWangProperties  |
| zsyc.invoice.baiwang.xsf-nsrsbh |   | 销售方纳税人识别号  | java.lang.String | com.zsyc.invoice.baiwang.config.BaiWangProperties  |
| zsyc.pay.abc.app-id |   | 微信小程序 appid  | java.lang.String | com.zsyc.pay.abc.config.AbcPayProperties  |
| zsyc.pay.abc.pay-callback |   | 支付回调  | java.lang.String | com.zsyc.pay.abc.config.AbcPayProperties  |
| zsyc.aliyun.sms.access-key-id | LTAI4GJ12GtvfzZyLjKpx2yN  | accessKeyId  | java.lang.String | com.zsyc.zt.b2b.config.AliyunSmsProperties  |
| zsyc.aliyun.sms.access-key-secret | S5B2hcXni2qjRzahvdwEVcZjdPrMem  | accessKeySecret  | java.lang.String | com.zsyc.zt.b2b.config.AliyunSmsProperties  |
| zsyc.aliyun.sms.sign-name |   | signName 可选 1. 广东中天药业 2. 广东中天医药  | com.zsyc.zt.b2b.config.AliyunSmsProperties$SmsSignName | com.zsyc.zt.b2b.config.AliyunSmsProperties  |
| zsyc.aliyun.sms.template-code | SMS_176555154  | templateCode  | java.lang.String | com.zsyc.zt.b2b.config.AliyunSmsProperties  |
| zsyc.zt.b2b.id-worker.data-center-id | 0  | data center id  | java.lang.Long | com.zsyc.zt.b2b.config.B2bConfig  |
| zsyc.zt.b2b.id-worker.machine-id | 0  | machine id  | java.lang.Long | com.zsyc.zt.b2b.config.B2bConfig  |
| zsyc.dubbo.configs |   | dubbo自定义配置  | java.util.Map<java.lang.String,com.zsyc.zt.b2b.config.DubboCustomerVersionConfig$DubboVersion> | com.zsyc.zt.b2b.config.DubboCustomerVersionConfig  |
| zsyc.wechat.zhong-tian.app-id |   | appId  | java.lang.String | com.zsyc.zt.b2b.config.WechatConfigProperties  |
| zsyc.wechat.zhong-tian.app-secret |   | appSecret  | java.lang.String | com.zsyc.zt.b2b.config.WechatConfigProperties  |
| zsyc.xxl-job.admin-addresses |   | adminAddresses  | java.lang.String | com.zsyc.zt.b2b.config.XxlJobConfig  |
| zsyc.xxl-job.app-name |   | appname  | java.lang.String | com.zsyc.zt.b2b.config.XxlJobConfig  |
| zsyc.xxl-job.enable |   | enable 是否启用  | java.lang.Boolean | com.zsyc.zt.b2b.config.XxlJobConfig  |
| zsyc.xxl-job.ip |   | ip  | java.lang.String | com.zsyc.zt.b2b.config.XxlJobConfig  |
| zsyc.xxl-job.logpath |   | logpath  | java.lang.String | com.zsyc.zt.b2b.config.XxlJobConfig  |
| zsyc.xxl-job.port |   | port  | java.lang.Integer | com.zsyc.zt.b2b.config.XxlJobConfig  |
| zsyc.aliyun.oss.access-key-id | LTAI4GJ12GtvfzZyLjKpx2yN  | accessKeyId  | java.lang.String | com.zsyc.zt.fs.config.AliyunOssProperties  |
| zsyc.aliyun.oss.access-key-secret | S5B2hcXni2qjRzahvdwEVcZjdPrMem  | accessKeySecret  | java.lang.String | com.zsyc.zt.fs.config.AliyunOssProperties  |
| zsyc.aliyun.oss.bucket-name | gdztyy-b2b  | bucketName gdztyy-b2b.oss-cn-shenzhen.aliyuncs.com  | java.lang.String | com.zsyc.zt.fs.config.AliyunOssProperties  |
| zsyc.aliyun.oss.endpoint | https://oss-cn-shenzhen.aliyuncs.com  | endpoint oss-cn-shenzhen.aliyuncs.com  | java.lang.String | com.zsyc.zt.fs.config.AliyunOssProperties  |
