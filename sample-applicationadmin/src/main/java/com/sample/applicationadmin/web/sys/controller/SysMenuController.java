package com.sample.applicationadmin.web.sys.controller;

import com.sample.applicationadmin.util.Code;
import com.sample.applicationadmin.util.SysMessage;
import com.sample.applicationadmin.web.base.BaseController;
import com.sample.applicationadmin.web.sys.entity.SysMenuEntity;
import com.sample.applicationadmin.web.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: murunsen
 * @email:465361524@qq.com
 * @date: 2019-01-14 16:59
 */
@RestController
@RequestMapping("/sys/menu")
public class SysMenuController extends BaseController {

    @Autowired
    private SysMenuService sysMenuService;
    /**
     * 用户菜单列表
     */
    @RequestMapping("/user")
    public Object user() {
        List<SysMenuEntity> menuList = sysMenuService.getUserMenuList(getUserId());

        Map<String,Object> map = new HashMap<>();
        map.put("menuList",menuList);
        return actionResult(Code.OK,SysMessage.GET_SUCCESS, map);
        //return R.ok().put("menuList", menuList);
    }
}
