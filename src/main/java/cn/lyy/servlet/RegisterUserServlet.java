package cn.lyy.servlet;

import cn.lyy.domain.ResultInfo;
import cn.lyy.domain.User;
import cn.lyy.service.UserService;
import cn.lyy.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@WebServlet("/registUserServlet")
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //验证码校验
        String check = req.getParameter("check");

        String sessionCheck = (String) req.getSession().getAttribute("CHECKCODE_SERVER");
        req.getSession().removeAttribute("CHECKCODE_SERVER");
        if (!check.equalsIgnoreCase(sessionCheck)) {
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            //将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(resultInfo);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return;
        }
        Map<String, String[]> maps = req.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user, maps);
            UserService userService = new UserServiceImpl();
            boolean success = userService.register(user);
            ResultInfo resultInfo = new ResultInfo();
            if (success) {
                resultInfo.setFlag(true);
            } else {
                resultInfo.setFlag(false);
                resultInfo.setErrorMsg("注册失败");
            }

            //将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(resultInfo);
//            resp.setContentType("application/json;charset=utf-8");
//            resp.getWriter().write(json);
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(json.toString());

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
