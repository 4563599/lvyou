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
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Map<String, String[]> maps = req.getParameterMap();
            User user = new User();
            BeanUtils.populate(user, maps);
            UserService userService = new UserServiceImpl();
            User loginUser = userService.login(user);
            ResultInfo resultInfo = new ResultInfo();
            if (loginUser == null) {
                resultInfo.setErrorMsg("用户或密码错误");
                resultInfo.setFlag(false);
            } else if (loginUser != null && (loginUser.getStatus() == null || !loginUser.getStatus().equalsIgnoreCase("Y"))) {
                resultInfo.setErrorMsg("您尚未激活，请激活");
                resultInfo.setFlag(false);
            } else if (loginUser != null && loginUser.getStatus().equalsIgnoreCase("Y")) {
                resultInfo.setFlag(true);
            }
            //响应数据
            ObjectMapper mapper = new ObjectMapper();

            resp.setContentType("application/json;charset=utf-8");
            mapper.writeValue(resp.getOutputStream(), resultInfo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
