# 认证服务
微服务生态的一部分，用于服务的授权与认证。

# 不同的授权类型测试
# 简化模式(implicit grant type)
  * 该模式适用于基于浏览器认证的应用程序
  
# password模式
  * 使用用户名和密码直接获取访问令牌
  * 通常在移动/桌面应用程式使用，它们使用服务来获取访问令牌并隐式访问用户的凭据
  * 使用curl命令发送请求
  
  
    $ curl -XPOST -u android:admin localhost:9999/oauth/token -d grant_type=password -d username=admin -d password=123456
  
  * 响应信息
 
 
    {"access_token":"5848092b-8361-4e48-a2f0-113d36e62bff","token_type":"bearer","refresh_token":"4a97c057-e1f8-4a4d-9edf-daa7a5314520","expires_in":30103,"scope":"xx"}

# client模式
  * 客户端模式（client credentials）(主要用于api认证，跟用户无关)，例如微信公众平台就是使用该模式
  * 使用curl命令发送请求
  
  
    $ curl -XPOST -u admin:admin localhost:9999/oauth/token -d grant_type=client_credentials

   * 响应信息
   
   
    {"access_token":"edff0f21-21e0-4362-a3e2-3dd4322dd51c","token_type":"bearer","expires_in":24696,"scope":"read"}
    
    
  
 