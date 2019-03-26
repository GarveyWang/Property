package com.garvey.property.interceptor;

import com.garvey.property.annotation.Login;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;

/**
 * @author GarveyWong
 * @date 2019/3/26
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        if (containLoginAnnotation(handlerMethod)) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("user") == null) {
                response.sendRedirect("/login");
                return false;
            }
        }
        return true;
    }

    private boolean containLoginAnnotation(HandlerMethod handlerMethod) {
        Class clazz = handlerMethod.getBeanType();
        Login login = (Login) clazz.getAnnotation(Login.class);
        if (login == null) {
            Method method = handlerMethod.getMethod();
            login = method.getAnnotation(Login.class);
        }
        return login != null;
    }
}
