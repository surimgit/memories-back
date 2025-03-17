package com.sr.memoriesback.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.sr.memoriesback.filter.JwtAuthenticationFilter;
import com.sr.memoriesback.handler.OAuth2SuccessHandler;
import com.sr.memoriesback.service.implement.OAuth2UserServiceImplement;

import lombok.RequiredArgsConstructor;

// class: Spring Web 보안 설정 클래스 //
// description: Bearer 인증 방식을 사용하기 위해 Basic 인증 미사용 //
// description: REST API 서버로 Session 유지하지 않음 //
// description: CORS 정책은 모든 출처 및 리소스에 대해서 허용 //

@EnableWebSecurity
@Configurable
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final OAuth2UserServiceImplement oauth2UserService;
  private final OAuth2SuccessHandler oAuth2SuccessHandler;

  // function: Web Security 설정 메서드 //
  @Bean
  protected SecurityFilterChain configure(HttpSecurity security) throws Exception{
    // description: Basic 인증 미사용 지정 //
    security.httpBasic(HttpBasicConfigurer::disable)
    // description: Session 유지 유지X 지정 //
    .sessionManagement(management -> management
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    )
    // description: csrf 취약점 대비 미사용지정 //
    .csrf(CsrfConfigurer::disable)
    // description: CORS 정책 설정 //
    .cors(cors -> cors.configurationSource(corsconfigurationSource()))
    // description: 인가 설정 //
    .authorizeHttpRequests(request -> request
      .requestMatchers("/api/v1/auth", "/api/v1/auth/**", "/oauth2/** ").permitAll()
      .anyRequest().authenticated()
    )  

    // description: Oauth 로그인 적용 //
    .oauth2Login(oauth2 -> oauth2
      .redirectionEndpoint(endpoint -> endpoint.baseUri("/oauth2/callback/*"))
      .authorizationEndpoint(endpoint -> endpoint.baseUri("/api/v1/auth/sns"))
      .userInfoEndpoint(endpoint -> endpoint.userService(oauth2UserService))
      .successHandler(oAuth2SuccessHandler)
    )
    // description: JwtAuthentication 필터 등록 //
    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return security.build();
  }

  // function: CORS 정책 설정 객체를 반환하는 메서드 //
  protected CorsConfigurationSource corsconfigurationSource(){
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");
    configuration.addAllowedOrigin("*");

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}
