package servlet;

import model.User;
import service.UserService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/edit")
public class EditServlet  extends HttpServlet {
    private static UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = UserService.getINSTANCE();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            String idStr = req.getParameter("id");
            int id = Integer.parseInt(idStr);
            User user = userService.getUserById(id);
            if (user == null) {
                resp.setStatus(500);
            }
            req.setAttribute("userForEdit", user);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("editUsers.jsp");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("Unknown error while edit user!");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            String name = req.getParameter("name");
            int age = Integer.parseInt(req.getParameter("age"));
            int id = Integer.parseInt(req.getParameter("id"));
            if (!userService.updateUser(id, name, age)) {
                resp.setStatus(500);
            }
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("/");
            requestDispatcher.forward(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("Incorrect data!");
        }
    }
}
