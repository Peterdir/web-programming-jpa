package project.Controller;

import java.io.File;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import project.model.UserModel;
import project.service.UserService;
import project.service.impl.UserServiceImpl;

@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024 * 2,  // 2MB
	    maxFileSize = 1024 * 1024 * 10,       // 10MB
	    maxRequestSize = 1024 * 1024 * 50     // 50MB
	)
@WebServlet("/profile")
public class ProfileController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Lấy user từ session
        UserModel account = (UserModel) req.getSession().getAttribute("account");

        if (account == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setAttribute("user", account);
        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        UserModel account = (UserModel) req.getSession().getAttribute("account");

        if (account == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Lấy dữ liệu form
        String fullname = req.getParameter("fullname");
        String phone = req.getParameter("phone");

        account.setFullname(fullname);
        account.setPhone(phone);

        // Xử lý upload ảnh
        Part filePart = req.getPart("images");
        String filename = filePart.getSubmittedFileName();

        if (filename != null && !filename.isBlank()) {
            String uploadPath = req.getServletContext().getRealPath("/uploads");
            File dir = new File(uploadPath);
            if (!dir.exists()) dir.mkdirs();

            // Xóa ảnh cũ
            String oldImg = account.getImages();
            if (oldImg != null && !oldImg.isBlank()) {
                File oldFile = new File(uploadPath + File.separator + oldImg);
                if (oldFile.exists()) oldFile.delete();
            }

            // Lưu ảnh mới
            filePart.write(uploadPath + File.separator + filename);
            account.setImages(filename);
        }

        // Cập nhật DB
        userService.update(account);

        // Cập nhật lại session để hiển thị trên header
        req.getSession().setAttribute("account", account);

        // Gửi thông báo
        req.setAttribute("message", "Cập nhật thông tin thành công!");
        req.setAttribute("user", account);
        req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
	}
}
