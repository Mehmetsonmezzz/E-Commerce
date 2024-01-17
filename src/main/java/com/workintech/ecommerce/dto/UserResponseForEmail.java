package com.workintech.ecommerce.dto;

import com.workintech.ecommerce.entity.User.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public record UserResponseForEmail(String fullName, Collection<? extends GrantedAuthority> role) {
}
