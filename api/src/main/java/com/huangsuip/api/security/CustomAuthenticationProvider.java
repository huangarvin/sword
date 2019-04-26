package com.huangsuip.api.security;

import java.util.ArrayList;
import java.util.List;
import com.huangsuip.common.po.User;
import com.huangsuip.framework.util.JSONUtils;
import com.huangsuip.framework.util.LogUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * @author HuangSuip
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object credentials = authentication.getCredentials();
        Object details = authentication.getDetails();
        Object principal = authentication.getPrincipal();
        try {
            LogUtils.info(JSONUtils.toJSONString(credentials));
            LogUtils.info(JSONUtils.toJSONString(details));
            LogUtils.info(JSONUtils.toJSONString(principal));
        } catch (Exception e) {
            LogUtils.error("CustomAuthenticationProvider authenticate", e);
        }

        User u = new User();
        u.setUserId(119L);
        u.setName("mq");
        GrantedAuthority authority = new SimpleGrantedAuthority("admin");
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(authority);
        AuthenticationToken token = new AuthenticationToken("admin", "8023", list);
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
