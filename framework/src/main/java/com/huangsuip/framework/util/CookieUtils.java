package com.huangsuip.framework.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import com.huangsuip.common.constant.Constants;

/**
 * @author HuangSuip
 */
public class CookieUtils {

    public static void setToken(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie(Constants.TOKEN, token);
        cookie.setDomain(Constants.COOKIE_DOMAIN);
        cookie.setMaxAge(86400);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void cleanToken(HttpServletResponse response) {
        Cookie cookie = new Cookie(Constants.TOKEN, null);
        cookie.setDomain(Constants.COOKIE_DOMAIN);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
