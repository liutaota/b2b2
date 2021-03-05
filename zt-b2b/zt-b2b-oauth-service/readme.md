# zt-b2b-oauth-service

## 配置说明 

| Key  | Default Value | Description | Type | Source Type |
| :--- | :------------ | :---------- | :--- | :---------- |
| zsyc.oauth2.clients |   | 所有客户端配置  | java.util.Map<java.lang.String,org.springframework.security.oauth2.provider.client.BaseClientDetails> | com.zsyc.webapp.config.OauthClientProperties  |
| zsyc.oauth2.store-redis-key | spring_oauth:  | 存储 token 前缀  | java.lang.String | com.zsyc.webapp.config.OauthClientProperties  |
| zsyc.oauth2.request.white-list |   | 请求白名单  | java.lang.String | com.zsyc.webapp.config.OauthClientProperties$Request  |
| zsyc.oauth2.request.cors.headers |   | 请求头  | java.lang.String | com.zsyc.webapp.config.OauthClientProperties$RequestOrs  |
| zsyc.oauth2.request.cors.origin |   | 请求来源  | java.lang.String | com.zsyc.webapp.config.OauthClientProperties$RequestOrs  |

