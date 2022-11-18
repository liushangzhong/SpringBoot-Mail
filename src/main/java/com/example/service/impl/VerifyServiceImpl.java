package com.example.service.impl;

import com.example.service.VerifyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author 刘上忠
 * @data studying
 */
@Service
public class VerifyServiceImpl implements VerifyService {
    @Resource
    JavaMailSender sender;
    @Resource
    RedisTemplate<String, String> template;
    @Value("${spring.mail.username}")
    String from;
    @Override
    public void sendVerifyCode(String mail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("【xx网站】您的注册验证码");
        Random random = new Random();
        int code = random.nextInt(899999) + 10000;
        template.opsForValue().set("verify:code"+mail,code+"",3, TimeUnit.MINUTES);
        message.setText("您的注册验证码为:"+code+"三分钟内有效，请及时完成注册！如果不是本人操作，请忽略!");
        message.setTo(mail);
        message.setFrom(from);
        sender.send(message);
    }

    @Override
    public boolean doVerify(String mail, String code) {
        String string = template.opsForValue().get("verify:code" + mail);
        if (string == null){
            return false;
        }
        if (!string.equals(code)){
            return false;
        }
        template.delete("verify:code" + mail);
        return true;
    }
}
