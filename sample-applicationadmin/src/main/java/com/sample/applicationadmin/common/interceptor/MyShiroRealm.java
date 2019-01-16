package com.sample.applicationadmin.common.interceptor;

import com.sample.applicationadmin.common.cache.J2CacheUtils;
import com.sample.applicationadmin.util.Constant;
import com.sample.applicationadmin.web.sys.dao.SysMenuDao;
import com.sample.applicationadmin.web.sys.dao.SysUserDao;
import com.sample.applicationadmin.web.sys.entity.SysMenuEntity;
import com.sample.applicationadmin.web.sys.entity.SysUserEntity;
import com.sample.applicationadmin.web.sys.service.SysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: murunsen
 * @email:465361524@qq.com
 * @date: 2019-01-11 17:39
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysMenuDao sysMenuDao;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 验证用户身份,如果验证失败，返回null或者异常（带返回message），跳转到login的post链接
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {

        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        //查询用户信息
        SysUserEntity user = null;
        try {
            user = sysUserDao.queryByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 把当前用户放入到session中
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession(true);
        session.setAttribute(Constant.CURRENT_USER, user);

        List<String> permsList;
        //系统管理员，拥有最高权限
        if (Constant.SUPER_ADMIN == user.getUserId()) {
            List<SysMenuEntity> menuList = sysMenuDao.queryList(new HashMap<String, Object>());
            permsList = new ArrayList<>(menuList.size());
            for (SysMenuEntity menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = sysUserDao.queryAllPerms(user.getUserId());
        }
        J2CacheUtils.put(Constant.PERMS_LIST + user.getUserId(), permsList);


        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }
}
