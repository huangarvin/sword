package com.huangsuip.framework.email;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.huangsuip.framework.error.ServiceException;

/**
 * @author HuangSuip
 */
public class EmailManager {

    private static List<String> providerList = Arrays.asList("qq.com");

    public static void sendEmail(final String email, String content) {
        if (!isEmail(email)) {
            throw new ServiceException("邮箱格式不符合规范");
        }
        int start = email.indexOf('@') + 1;
        String emailProvider = email.substring(start, email.length()).toLowerCase();
        System.out.println(emailProvider);
        if (!providerList.contains(emailProvider)) {
            throw new ServiceException(String.format("当前邮箱: %s 不支持!", emailProvider));
        }


    }

    private static boolean isEmail(String email) {
        if (email == null) {
            return false;
        }
        String regEx1 = "^[a-zA-Z0-9][|\\-\\w.+]*[a-zA-Z0-9]@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(email);
        return m.matches();
    }


    public static void sendEmail(final String email, final int template, String... content) {


        return;
    }
}
