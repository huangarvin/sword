package com.huangsuip.framework.util;

import java.util.Date;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import com.huangsuip.common.constant.Constants;
import com.huangsuip.common.po.User;
import com.huangsuip.framework.error.BusinessErrors;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

/**
 * @author HuangSuip
 */
public class JwtUtils {
    private static String key = "huangsuip";

    public static String createJwtToken(User user, long ttlMillis) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts
                .builder()
                .setIssuedAt(now)
                //可以设置多个值 最好是String类型
                .claim(Constants.USER_ID, user.getUserId().toString())
                .claim(Constants.USER_NAME, user.getName())
                .signWith(signatureAlgorithm, getKey());
        long expMillis = nowMillis + ttlMillis;
        //builder.setExpiration(new Date(expMillis));
        return builder.compact();
    }

    public static User parserJwtToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null){
            throw BusinessErrors.LOGIN_ERROR.toException();
        }
        for (Cookie cookie : cookies) {
            if (Constants.TOKEN.equalsIgnoreCase(cookie.getName())) {
                return parserJwtToken(cookie.getValue());
            }
        }
        throw BusinessErrors.LOGIN_ERROR.toException();
    }

    public static User parserJwtToken(String token) {
        Claims body;
        try {
         body = Jwts.parser().setSigningKey(getKey()).parseClaimsJws(token).getBody();
        }catch (Exception e){
            LogUtils.error( "token 校验失败: " + token, e);
            throw BusinessErrors.LOGIN_ERROR.toException();
        }
        User u = new User();
        String userId = body.get(Constants.USER_ID, String.class);
        String userName = body.get(Constants.USER_NAME, String.class);
        u.setUserId(Long.valueOf(userId));
        u.setName(userName);
        return u;
    }

    private static SecretKey getKey() {
        byte[] encodedKey = Base64.decodeBase64(key);
        return new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
    }
}
