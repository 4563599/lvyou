package cn.lyy.servlet;

import cn.lyy.domain.Category;
import cn.lyy.service.CategoryService;
import cn.lyy.service.CategoryServiceImpl;
import com.sun.xml.internal.rngom.parse.host.Base;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {
    CategoryService categoryService = new CategoryServiceImpl();

    protected void findAll(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServletException {
        try {
            List<Category> list = categoryService.findAll();
            writeValue(list, httpServletResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
