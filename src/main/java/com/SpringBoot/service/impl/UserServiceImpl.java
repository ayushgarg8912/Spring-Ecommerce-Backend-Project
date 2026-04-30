package com.SpringBoot.service.impl;

import com.SpringBoot.config.JwtProvider;
import com.SpringBoot.repository.UserRepository;
import com.SpringBoot.service.UserService;
import lombok.RequiredArgsConstructor;
import com.SpringBoot.model.User;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public com.SpringBoot.model.User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFrom_JwtToken(jwt);
        return   this.findUserByEmail(email);




    }

    @Override
    public com.SpringBoot.model.User findUserByEmail(String email) throws Exception {
        User user = (User) userRepository.findByEmail(email);
        if(user==null){
            throw new Exception("user not found with email-" +email);
        }
        return user;
    }
}
