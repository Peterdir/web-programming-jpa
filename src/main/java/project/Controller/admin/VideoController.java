package project.Controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.entity.Video;
import project.entity.Category;
import project.service.VideoService;
import project.service.CategoryService;
import project.service.impl.VideoServiceImpl;
import project.service.impl.CategoryServiceImpl;

@WebServlet({"/admin/video", "/admin/video/create", "/admin/video/edit", "/admin/video/delete"})
public class VideoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private VideoService videoService = new VideoServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getServletPath();

        switch (path) {

            // Danh sách video hoặc tìm kiếm
            case "/admin/video":
                String keyword = req.getParameter("q");
                List<Video> list;
                if (keyword != null && !keyword.isBlank()) {
                    list = videoService.search(keyword);
                    req.setAttribute("keyword", keyword);
                } else {
                    list = videoService.findAll();
                }
                req.setAttribute("videos", list);
                req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
                break;

            // Mở form tạo mới
            case "/admin/video/create":
                req.setAttribute("categories", categoryService.findAll());
                req.getRequestDispatcher("/views/admin/video-form.jsp").forward(req, resp);
                break;

            // Mở form chỉnh sửa
            case "/admin/video/edit":
                int id = Integer.parseInt(req.getParameter("id"));
                Video video = videoService.findById(id);
                req.setAttribute("video", video);
                req.setAttribute("categories", categoryService.findAll());
                req.getRequestDispatcher("/views/admin/video-form.jsp").forward(req, resp);
                break;

            // Xóa video
            case "/admin/video/delete":
                int delId = Integer.parseInt(req.getParameter("id"));
                videoService.delete(delId);
                resp.sendRedirect(req.getContextPath() + "/admin/video");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String id = req.getParameter("videoId");
        String title = req.getParameter("title");
        String poster = req.getParameter("poster");
        String description = req.getParameter("description");
        long views = Long.parseLong(req.getParameter("views"));
        boolean active = req.getParameter("active") != null;
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        Category category = categoryService.findById(categoryId);

        Video v = new Video();
        v.setTitle(title);
        v.setPoster(poster);
        v.setDescription(description);
        v.setViews(views);
        v.setActive(active);
        v.setCategory(category);

        // nếu có id → update, không có → create
        if (id != null && !id.isBlank()) {
            v.setVideoId(Integer.parseInt(id));
            videoService.update(v);
        } else {
            videoService.insert(v);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/video");
    }
}
