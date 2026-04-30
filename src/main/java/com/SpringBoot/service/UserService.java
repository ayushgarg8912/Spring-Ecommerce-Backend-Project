package com.SpringBoot.service;

import com.SpringBoot.model.User;

public interface UserService {

    com.SpringBoot.model.User findUserByJwtToken(String jwt) throws Exception;
    User findUserByEmail(String email) throws Exception;

}
