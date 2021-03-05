# framework

## TODO

1. error code,拓展assert


## maven update version

```shell
 mvn versions:set -DnewVersion=1.0.0
```

## update change log

```
conventional-changelog -p angular -i CHANGELOG.md -s -r 0
```

## web

### http code 处理

1. 400 请求报文格式不正确
2. 401 无效授权，建议重新获取 【access_token】
3. 200 请求正常，再判断返回报文里的 【errorCode】
   1. 0 业务正常返回
   2. 500 系统内未知错误，正式环境建议直接提示【系统出错】，开发环境建议直接打印【errorMessage】
   3. 600 业务处理错误,建议直接提示【errorMessage】
   4. 6xx 业务处理错误,需要与相应与接口协商处理方式

## oauth

### http code 处理

1. 400 请求报文格式不正确
1. 401 无效授权，一般basic认证出错
1. 403 授权失败，建议提示【error_description】，判断【error_code】，结合相应情况进行处理
1. 200 请求正常，保存【access_token】
