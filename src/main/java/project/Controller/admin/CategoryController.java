package project.Controller.admin;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import project.entity.Category;
import project.service.CategoryService;
import project.service.impl.CategoryServiceImpl;

@WebServlet({"/admin/category", "/admin/category/create", "/admin/category/edit", "/admin/category/delete"})
public class CategoryController extends HttpServlet{
	private static final long serialVersionUID = 1L;
    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path = req.getServletPath();
        switch (path) {
            case "/admin/category":
                List<Category> list = categoryService.findAll();
                req.setAttribute("listCategory", list);
                req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
                break;

            case "/admin/category/edit":
                int idEdit = Integer.parseInt(req.getParameter("id"));
                Category cat = categoryService.findById(idEdit);
                req.setAttribute("category", cat);
                req.getRequestDispatcher("/views/admin/category-form.jsp").forward(req, resp);
                break;

            case "/admin/category/delete":
                int idDelete = Integer.parseInt(req.getParameter("id"));
                categoryService.delete(idDelete);
                resp.sendRedirect(req.getContextPath() + "/admin/category");
                break;

            case "/admin/category/create":
            default:
                req.getRequestDispatcher("/views/admin/category-form.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String id = req.getParameter("id");
        String name = req.getParameter("categoryName");
        String code = req.getParameter("categoryCode");
        String images = req.getParameter("images");

        Category c = new Category();
        if (id != null && !id.isBlank()) {
            c.setCategoryId(Integer.parseInt(id));
        }
        c.setCategoryName(name);
        c.setCategoryCode(code);
        c.setImages(images);
        c.setStatus(true);

        if (id == null || id.isBlank()) {
            categoryService.insert(c);
        } else {
            categoryService.update(c);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/category");
    }
}
