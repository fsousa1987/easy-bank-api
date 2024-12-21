package com.francisco.easybankapi.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenAuthenticationConverter;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class KeycloakOpaqueRoleConverter implements OpaqueTokenAuthenticationConverter {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Authentication convert(String introspectedToken, OAuth2AuthenticatedPrincipal authenticatedPrincipal) {
        Map<String, Object> realmAccess = authenticatedPrincipal.getAttribute("realm_access");

        Objects.requireNonNull(realmAccess, "'realmAccess' não pode ser nulo.");

        List<String> rolesList = objectMapper.convertValue(realmAccess.get("roles"), new TypeReference<>() {
        });
        Collection<GrantedAuthority> roles = rolesList.stream()
                .map(roleName -> "ROLE_" + roleName)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(authenticatedPrincipal.getName(), null, roles);
    }
}
