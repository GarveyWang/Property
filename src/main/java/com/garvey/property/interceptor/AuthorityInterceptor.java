package com.garvey.property.interceptor;

import com.garvey.property.annotation.Authority;
import com.garvey.property.constant.Role;
import com.garvey.property.model.db.User;
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
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Authority authority = method.getAnnotation(Authority.class);
        if (authority == null) {
            Class clazz = handlerMethod.getBeanType();
            authority = (Authority) clazz.getAnnotation(Authority.class);
        }
        if (authority == null){
            return true;
        }
        HttpSession session = request.getSession(false);

        boolean auth = false;
        if (session == null || session.getAttribute("user") == null){
            auth = authority.anomynous();
        }else{
            User user = (User)session.getAttribute("user");
            if (user.getRole() == Role.Proprietor.getValue()){
                auth = authority.proprietor();
            } else if (user.getRole() == Role.Property.getValue()){
                auth = authority.property();
            }else if (user.getRole() == Role.Manager.getValue()){
                auth = authority.manager();
            }
        }
        if (!auth){
            response.sendRedirect("/login");
        }
        return auth;
    }
}
