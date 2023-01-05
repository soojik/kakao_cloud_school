package com.example.javaweb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebListener
public class SessionListener implements HttpSessionListener {

    // 접속자 수 저장
    private static int count = 0;

    public static int getCount() {
        return count;
    }

    public SessionListener() {
    }

    // 세션 만들어 질 때 - 새로운 접속이 온 경우
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
        count++;
        System.out.println("접속자 수:" + getCount());

        HttpSession httpSession = se.getSession();
        System.out.println("세션 아이디: " + httpSession.getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

}
