package com.example.loandemo.model.auth;

import com.example.loandemo.service.BackendUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * create by zhangjun1 on 2017/12/21
 */
public class MyShiroRealm extends AuthorizingRealm {


    Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    @Autowired
    private BackendUserService backendUserService;


    @PostConstruct
    public void initParam(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        this.setCredentialsMatcher(hashedCredentialsMatcher);
    }

    /**
     * 认证信息.(身份验证)
     * :
     * Authentication 是用来验证用户身份
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();

        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        BackendUser userInfo = backendUserService.findByUid(username);

        if(userInfo == null){
            return null;
        }

        //4.若用户不行存在，可以抛出UnknownAccountException
        if(userInfo ==null){
            throw new UnknownAccountException("用户不存在");
        }

        //5.若用户被锁定，可以抛出LockedAccountException
        if(userInfo.getLocked() == 1){
            throw new LockedAccountException("用户被锁定");
        }

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo.getUid(), //用户名
                userInfo.getRealName(), //密码
                ByteSource.Util.bytes(""),
                getName()  //realm name
        );

        return authenticationInfo;
    }



    /**
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     *
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
       /*
        * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
        * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
        * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
        * 缓存过期之后会再次执行。
        */
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        BackendUser userInfo  = (BackendUser) principals.getPrimaryPrincipal();


        return authorizationInfo;
    }




}