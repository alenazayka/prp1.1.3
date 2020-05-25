package servlet;

import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/delete")
public class DeleteServlet  extends HttpServlet {
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
            if (!userService.deleteUserById(id)) {
                resp.setStatus(500);
            }
        } catch (Exception e) {
            resp.getWriter().write("Incorrect data!");
        }

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/");
        requestDispatcher.forward(req, resp);
    }
}
