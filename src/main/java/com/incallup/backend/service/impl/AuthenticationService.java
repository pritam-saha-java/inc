package com.incallup.backend.service.impl;

import com.incallup.backend.domain.Admin;
import com.incallup.backend.domain.LoginResponse;
import com.incallup.backend.domain.Role;
import com.incallup.backend.repository.AdminRepository;
import com.incallup.backend.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final AdminRepository adminRepository;

    @Autowired
    private final TokenService tokenService;
    @Autowired
    private final AuthenticationManager authenticationManager;


    @Autowired
    private final RoleRepository roleRepository;
    @Autowired
    private final PasswordEncoder encoder;

    public Admin register(String username,String password){
        String encodedPass = encoder.encode(password);
        Role userRole = roleRepository.findRoleByAuthority("ADMIN").get();
        Set<Role>  authorities = new HashSet<>();
        authorities.add(userRole);
        return adminRepository.save(Admin.builder()
                        .username(username)
                        .password(encodedPass)
                        .authorities(authorities)
                .build());

    }

    public LoginResponse loginResponse(String username,String password){
        try {


            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)

            );

            String token = tokenService.generaJwt(authentication);
            return new LoginResponse(adminRepository.findUserByUsername(username).get(),token);
        }catch (AuthenticationException e){
            return new LoginResponse(null,"");
        }


    }



}
