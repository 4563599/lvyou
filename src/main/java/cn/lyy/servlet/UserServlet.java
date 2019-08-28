package cn.lyy.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

    public void add(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException {
        System.out.println("userServlet的add方法..");
    }

    public void find(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException {
        System.out.println("userServlet的find方法..");
    }

}
