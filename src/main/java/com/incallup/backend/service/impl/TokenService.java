//package com.incallup.backend.service.impl;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.oauth2.jwt.JwtClaimsSet;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
//import org.springframework.stereotype.Service;
//
//import java.time.Instant;
//import java.util.stream.Collectors;
//
//@Service
//public class TokenService {
//
//    @Autowired
//    private JwtEncoder jwtEncoder;
//
//    @Autowired
//    private JwtDecoder jwtDecoder;
//
//    public String generaJwt(Authentication authentication){
//        Instant instant = Instant.now();
//        String scope = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(" "));
//        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
//                .issuer("self")
//                .issuedAt(instant)
//                .subject(authentication.getName())
//                .claim("roles",scope)
//                .build();
//        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
//    }
//}
