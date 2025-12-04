package com.haphazard.AlapOn.interceptor;

import java.io.IOException;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.haphazard.AlapOn.user.UserSynchronizer;

@Component
@RequiredArgsConstructor
public class UserSynchronizerFilter extends OncePerRequestFilter {
  private final UserSynchronizer userSynchronizer;
  @Override
  protected void doFilterInternal(@Nonnull HttpServletRequest request, @Nonnull HttpServletResponse response,
                                  @Nonnull FilterChain filterChain) throws ServletException, IOException
  {
    if(!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
      JwtAuthenticationToken token = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
      userSynchronizer.synchronizeWithIdp(token.getToken());
    }

  }
}
