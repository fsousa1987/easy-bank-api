package com.francisco.easybankapi.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    public Collection<GrantedAuthority> convert(Jwt source) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> realmAccess = objectMapper
                .convertValue(source.getClaims().get("realm_access"), new TypeReference<>() {
                });

        if (realmAccess == null || realmAccess.isEmpty()) {
            return new ArrayList<>();
        }

        List<String> roles = objectMapper.convertValue(realmAccess.get("roles"), new TypeReference<>() {
        });

        return roles.stream()
                .map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

}
