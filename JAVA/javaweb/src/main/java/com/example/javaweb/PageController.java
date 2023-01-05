package com.example.javaweb;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "pageController", value = "/page-controller")
public class PageController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // 서비스에 대한 참조 변수
    private PageService pageService;

    public PageController() {
        pageService = new PageServiceImpl();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // parameter 받아오기
        String first = request.getParameter("first");
        String second = request.getParameter("second");

        // Spring은 일반적으로 Controller에서 한다.
        int result = pageService.add(Integer.parseInt(first), Integer.parseInt(second));

        // 결과 저장
        request.setAttribute("result", result);
        // 세션 직접 만들어
        request.getSession().setAttribute("result", result);
        request.getServletContext().setAttribute("result", result);

        // 뷰 페이지 결정하고 데이터 전달
        response.sendRedirect("output.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
