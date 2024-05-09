package com.incallup.backend.service.impl;

import com.incallup.backend.domain.Admin;
import com.incallup.backend.domain.Seller;
import com.incallup.backend.exception.ApplicationException;
import com.incallup.backend.helper.LoginResponse;
import com.incallup.backend.repository.AdminRepository;
import com.incallup.backend.repository.SellerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

    @Autowired
    private final AdminRepository adminRepository;
    private final SellerRepository sellerRepository;

//    @Autowired
//    private final TokenService tokenService;
//    @Autowired
//    private final AuthenticationManager authenticationManager;
//
//
//    @Autowired
//    private final RoleRepository roleRepository;
//    @Autowired
//    private final PasswordEncoder encoder;

    public Admin register(String username,String password){
//        String encodedPass = encoder.encode(password);
//        Role userRole = roleRepository.findRoleByAuthority("ADMIN").get();
//        Set<Role>  authorities = new HashSet<>();
//        authorities.add(userRole);
        return adminRepository.save(Admin.builder()
                        .username(username)
                        .password(password)
//                        .authorities(password)
                .build());

    }
    public LoginResponse adminLoginResponse(String username,String password){
try {

    if (!"root".equals(username))
        throw ApplicationException.builder().build();

    if ("root1234".equals(password))
        return new LoginResponse(username);

    throw ApplicationException.builder().title("wrong password").build();

}catch (Exception e){
    return new LoginResponse(null);

}

    }
    public LoginResponse loginResponse(String username,String password){
        try {

            var user = sellerRepository.findSellerByUsername(username);
            if(user.isEmpty())
                throw ApplicationException.builder().build();
             Seller seller = user.get();
            if(seller.getPassword().equals(password))
                return new LoginResponse(username);

            throw ApplicationException.builder().title("wrong password").build();
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username,password)
//
//            );



//            String token = tokenService.generaJwt(authentication);
//            return new LoginResponse(adminRepository.findUserByUsername(username).get()
//                    ,token
//            );
//        }catch (AuthenticationException e){
        }catch (Exception e){
            return new LoginResponse(null);
        }


    }



}
