package org.zerhusen.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.zerhusen.security.jwt.JWTFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义认证入口,由于是基于jwt的认证,没有跳转的登录界面
 *
 * 所有认证都会在{@link JWTFilter}中完成,到这里统一返回401未认证
 *
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

   @Override
   public void commence(HttpServletRequest request,
                        HttpServletResponse response,
                        AuthenticationException authException) throws IOException {


      // 当用户尝试在不提供任何凭据的情况下访问安全的REST资源时调用此方法
      // 我们应该只发送401未经授权的响应，因为没有“登录页面”可重定向到
      // 在这里您可以放置任何想要的消息

      // This is invoked when user tries to access a secured REST resource without supplying any credentials
      // We should just send a 401 Unauthorized response because there is no 'login page' to redirect to
      // Here you can place any message you want
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
   }
}
