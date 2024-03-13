//package com.incallup.backend.configuration;
//
//import com.incallup.backend.utility.RSAKeyProperties;
//import com.nimbusds.jose.jwk.JWK;
//import com.nimbusds.jose.jwk.JWKSet;
//import com.nimbusds.jose.jwk.RSAKey;
//import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
//import com.nimbusds.jose.jwk.source.JWKSource;
//import com.nimbusds.jose.proc.SecurityContext;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@RequiredArgsConstructor
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public PasswordEncoder encoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(UserDetailsService service){
//
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(encoder());
//        provider.setUserDetailsService(service);
//
//        return new ProviderManager(provider);
//    }
////    @Autowired
////    private final AuthenticationEntryPoint authenticationEntryPoint;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
//
//        return httpSecurity
////                .exceptionHandling(http -> {
////                    http.authenticationEntryPoint(authenticationEntryPoint);
////                })
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth->{
//
////                    auth.requestMatchers("/admin/**").authenticated();
//                    auth.requestMatchers("/seller/**").authenticated();
//
//                    auth.anyRequest().permitAll();
//
//
//                })
//                .rememberMe(http -> {
//                    http.alwaysRemember(true)
//                            .tokenValiditySeconds(30*5)
//                            .rememberMeCookieDomain("incallup")
//                            .key("secrete-key");
//
//                })
//                .formLogin(configurer -> {
//                    configurer.loginPage("/auth/create");
//                })
////                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
//                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
//                .build();
//    }
//
//    private final RSAKeyProperties rsaKeyProperties;
//
//    @Bean
//    JwtDecoder jwtDecoder(){
//        return NimbusJwtDecoder.withPublicKey(rsaKeyProperties.getRsaPublicKey()).build();
//    }
//
//    @Bean
//    JwtEncoder jwtEncoder(){
//        JWK jwk = new RSAKey.Builder(rsaKeyProperties.getRsaPublicKey()).privateKey(rsaKeyProperties.getRsaPrivateKey()).build();
//        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
//        return new NimbusJwtEncoder(jwks);
//    }
//
//}
