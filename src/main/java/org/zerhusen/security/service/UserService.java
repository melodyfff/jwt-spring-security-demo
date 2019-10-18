package org.zerhusen.security.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerhusen.security.SecurityUtils;
import org.zerhusen.security.model.User;
import org.zerhusen.security.repository.UserRepository;

import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {

   private final UserRepository userRepository;

   public UserService(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Transactional(readOnly = true)
   public Optional<User> getUserWithAuthorities() {
      // 查询当前安全上下文中是否存在用户名,如果有则传入.flatMap(),去数据库查询GrantedAuthority信息,构建返回一个User对象
      return SecurityUtils.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
   }

}
