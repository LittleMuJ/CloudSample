package com.sample.applicationadmin.web.sys.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;


import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.sample.applicationadmin.common.interceptor.ShiroUtils;
import com.sample.applicationadmin.util.Code;
import com.sample.applicationadmin.util.SysMessage;
import com.sample.applicationadmin.web.base.BaseController;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class SysLoginController extends BaseController {

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

    /**
     * 登录
     */
    @ResponseBody
    @RequestMapping(value = "/sys/login", method = RequestMethod.POST)
    public Object login(String username,String password,String captcha){
        String kaptcha = ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(null == kaptcha){
            return actionResult(Code.BAD_REQUEST,SysMessage.VERIFICATION_CODE_ERROR);
        }
        if (!captcha.equalsIgnoreCase(kaptcha)) {
            return actionResult(Code.BAD_REQUEST,SysMessage.VERIFICATION_CODE_ERROR);
        }

        try {
            Subject subject = ShiroUtils.getSubject();
            //sha256加密
            password = new Sha256Hash(password).toHex();
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return actionResult(Code.LOGIN_FAIL,e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return actionResult(Code.LOGIN_FAIL,e.getMessage());
        } catch (LockedAccountException e) {
            return actionResult(Code.LOGIN_FAIL,e.getMessage());
        } catch (AuthenticationException e) {
            return actionResult(Code.LOGIN_FAIL,"账户验证失败");
        }

        return actionResult(Code.OK,SysMessage.LOGIN_SUCCESS);
    }
}
