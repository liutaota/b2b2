# 中天B2B商城

## 目录模块说明

| -                 | -               |
| :---------------- | :-------------- |
| zt-b2b            | b2b报货平台     |
| zt-b2b-fs         | oss文件服务封装 |  |
| zt-b2b-invoice    | 电子发票对接(百望)    |
| zt-b2b-pay        | 在线支付对接(农行)    |
| zt-base           | 公共代码模块    |
| zt-code-generator | 代码生成工具    |
| zt-inca           | inca相关服务    |

## 最新的报货平台

后台api接口

## api调用说明

1. 首先获取 access_token

post请求

```shell

curl -X POST \
  http://172.16.20.221:9120/oauth/token \
  -H 'Authorization: Basic aGFpbHVvOmhhaWx1bw==' \
  -H 'cache-control: no-cache' \
  -d 'grant_type=user_token&loginName=admin124&password=test1234'
```

返回access_token

```json
{
    "access_token": "4fb03d43-9922-4715-9b8a-17bcfbf88edd",
    "token_type": "bearer",
    "refresh_token": "cc2284a9-50fb-4a53-b38a-f5f983d8d44b",
    "expires_in": 99698,
    "scope": "user"
}
```

1. 通过access_token调用api接口

```shell

curl -X POST \
  http://172.16.20.221:9120/api/rep/monthCountRep \
  -H 'Authorization: Bearer 4fb03d43-9922-4715-9b8a-17bcfbf88edd' \
  -d 'grant_type=user_token&loginName=admin&password=admin123&undefined='

```

返回

```json
{
    "errorCode": "0",
    "errorMessage": null,
    "bizContent": [
        {
            "officeId": null
        },
        {
            "officeId": null
        }
    ]
}
```

## postman demo

https://www.getpostman.com/collections/d61d11b566261312a0b0



### 后台测试账户

- admin1 / test1234
- admin2 / test1234
- admin3 / test1234
- admin4 / test1234
- admin5 / test1234
- admin6 / test1234
- admin7 / test1234
- admin8 / test1234
- admin9 / test1234


报表框架使用
report.gdztyy.com/ureport/designer地址设计