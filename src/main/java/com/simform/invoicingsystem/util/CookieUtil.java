package com.simform.invoicingsystem.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class CookieUtil {

    public static String getCookieValueByName(HttpServletRequest request, String cookieName) {
        if (request.getCookies() != null) {
            Cookie ck = Arrays.stream(request.getCookies()).filter(c -> c.getName().equals(cookieName)).findFirst().orElse(new Cookie("token", " "));
            return ck.getValue();
        }
        return " ";
    }
}
