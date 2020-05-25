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

@WebServlet(urlPatterns = "/add")
public class AddServlet  extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = UserService.getINSTANCE();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("addUsers.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        try {
            String name = req.getParameter("name");
            String surname = req.getParameter("surname");
            int age = Integer.parseInt(req.getParameter("age"));

            User user = new User(name, age);
            if (!userService.addUser(user)) {
                resp.setStatus(500);
            }
            req.setAttribute("userAdded", user);
            doGet(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("Incorrect data!");
        }
    }
}

