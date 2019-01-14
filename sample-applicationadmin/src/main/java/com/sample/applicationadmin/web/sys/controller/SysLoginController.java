package com.sample.applicationadmin.web.sys.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sample.applicationadmin.common.interceptor.ShiroUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登陆相关
 * @author murunsen
 *  * @email 465361524@qq.com
 *  * @date
 */
@Controller
public class SysLoginController {

    private static Logger logger = LoggerFactory.getLogger(SysLoginController.class);
    @Autowired
    private Producer producer;

    @Autowired
    DefaultKaptcha defaultKaptcha;

    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletResponse response) throws ServletException, IOException {
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");

        BufferedImage image = null;


        // 生产验证码字符串并保存到session中
        String createText = producer.createText();
        //生成图片验证码
        image = producer.createImage(createText);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, createText);


        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }
}
