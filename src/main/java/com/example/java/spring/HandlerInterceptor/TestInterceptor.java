package com.example.java.spring.HandlerInterceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import sun.tools.jstat.Token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.sun.xml.internal.ws.api.message.Packet.State.ServerResponse;

@Component
class TestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 请求处理完成之后
        System.out.println("请求处理完成啦！");
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 处理器执行完毕之后
        System.out.println("处理器执行完毕啦！");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 处理器实际执行之前
        System.out.println("处理器执行之前！");
        System.out.println();
        if ("/index.html".equals(request.getRequestURI())) {
            System.out.println("欢迎登录首页，pass");
            return super.preHandle(request, response, handler);
        }
        if ("/login.html".equals(request.getRequestURI())) {
            String token2 = request.getParameter("token");
            if ("czbcxy".equals(token2)) {
                System.out.println("欢迎登录首页，pass");
                return super.preHandle(request, response, handler);
            }
        }
        errorPage(response, request.getRemoteAddr());
        return false;
    }

    public void errorPage(HttpServletResponse response, String remoteAddr) {
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter pw = null;
        try {
            pw = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (pw != null) {
            pw.write("拦截器拦截，token错误: " + remoteAddr);
            pw.flush();
            pw.close();
        }
    }
}