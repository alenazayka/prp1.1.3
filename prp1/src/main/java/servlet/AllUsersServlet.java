package servlet;

import model.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/")
public class AllUsersServlet extends HttpServlet {
    private static UserService userService;

    @Override
    public void init() throws ServletException {
        super.init();
        userService = UserService.getINSTANCE();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = userService.getAllUsers();
        req.setAttribute("users", users);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("allUsers.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}

