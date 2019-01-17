package com.sample.applicationadmin.web.sys.controller;

import com.sample.applicationadmin.util.Code;
import com.sample.applicationadmin.util.R;
import com.sample.applicationadmin.util.SysMessage;
import com.sample.applicationadmin.web.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: murunsen
 * @email:465361524@qq.com
 * @date: 2019-01-14 16:24
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController {

    /**
     * 获取登录的用户信息
     */
    @RequestMapping("/info")
    public Object info() {
        return R.ok().put("user", getUser());
    }
}
