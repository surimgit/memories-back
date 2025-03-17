package com.sr.memoriesback.common.entity;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;

@Getter
public class CustomOAuth2User implements OAuth2User {

  private String name;
  private Map<String, Object> attributes;
  private Collection<? extends GrantedAuthority> authorities;
  // description: 회원가입 여부 //
  private boolean existed; 

  public CustomOAuth2User(String name, Map<String, Object> atrributes, boolean existed){
    this.name = name;
    this.attributes = atrributes;
    this.authorities = AuthorityUtils.NO_AUTHORITIES;
    this.existed = existed;
  }

}
