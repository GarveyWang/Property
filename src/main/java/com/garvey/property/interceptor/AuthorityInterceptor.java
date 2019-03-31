package com.garvey.property.interceptor;

import com.garvey.property.annotation.NeededAuthority;
import com.garvey.property.constant.Authority;
import com.garvey.property.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
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
        NeededAuthority controllerAuthority = handlerMethod.getBeanType().getAnnotation(NeededAuthority.class);
        NeededAuthority methodAuthority = method.getAnnotation(NeededAuthority.class);

        if (controllerAuthority == null && methodAuthority == null) {
            return true;
        }

        HttpSession session =  request.getSession(false);
        if (session == null){
            response.sendRedirect("redirect:/login");
            return false;
        }
        User user = (User) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("redirect:/login");
            return false;
        } else if (controllerAuthority != null && !Authority.containArray(user.getAuthority(), controllerAuthority.authorities())) {
            return false;
        } else if (methodAuthority!=null && !Authority.containArray(user.getAuthority(), methodAuthority.authorities())) {
            return false;
        }
        return true;
    }
}
