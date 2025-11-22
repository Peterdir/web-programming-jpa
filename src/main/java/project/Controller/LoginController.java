package project.Controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.model.UserModel;
import project.service.UserService;
import project.service.impl.UserServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.getRequestDispatcher("/views/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String userName = req.getParameter("username");
        String password = req.getParameter("password");

        UserModel user = userService.login(userName, password);

        if (user == null) {
            req.setAttribute("error", "Tên dăng nhập hoặc mật khẩu không đúng!");
            req.getRequestDispatcher("/views/Login.jsp").forward(req, resp);
            return;
        }

        if (!user.isActive()) {
            req.setAttribute("error", "Tài khoản đã bị khóa!");
            req.getRequestDispatcher("/views/Login.jsp").forward(req, resp);
            return;
        }

        // Lưu session
        req.getSession().setAttribute("account", user);

        // Nếu là admin -> chuyển vào trang admin
        if ("admin".equals(user.getRole())) {
            resp.sendRedirect(req.getContextPath() + "/admin/home");
        } else {
            // user bình thường
            resp.sendRedirect(req.getContextPath() + "/profile");
        }
    }
}
