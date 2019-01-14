package com.sample.applicationadmin.util;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import com.google.code.kaptcha.util.Config;

import java.util.Properties;

/**
 * @description:
 * @author: murunsen
 * @email:465361524@qq.com
 * @date: 2019-01-11 16:22
 */
@Component
@Configuration
public class KaptchaUtils {

    @Bean
    public DefaultKaptcha getDefaultKaptcha(){

        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 图片边框
        properties.setProperty("kaptcha.border", "no");
        // 边框颜色
        //properties.setProperty("kaptcha.border.color", "105,179,90");
        // 字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        // 图片宽
        //properties.setProperty("kaptcha.image.width", "110");
        // 图片高
        //properties.setProperty("kaptcha.image.height", "40");
        // 字体大小
        //properties.setProperty("kaptcha.textproducer.font.size", "30");
        // session key
        //properties.setProperty("kaptcha.session.key", "code");
        // 验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //
        properties.setProperty("kaptcha.textproducer.char.space", "4");
        // 字体
        properties.setProperty("kaptcha.textproducer.font.names", "微软雅黑");

        properties.setProperty("kaptcha.textproducer.char.string", "123456789");

        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);

        return defaultKaptcha;
    }
}
