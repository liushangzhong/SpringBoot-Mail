package com.example.service;

import org.springframework.stereotype.Service;

/**
 * @author 刘上忠
 * @data studying
 */

public interface VerifyService {
    void sendVerifyCode(String mail);
    boolean doVerify(String mail,String code);
}
