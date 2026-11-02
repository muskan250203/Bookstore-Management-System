package com.bookstore.servlet;

import com.bookstore.dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username != null && password != null && userDAO.validate(username.trim(), password)) {
            HttpSession session = req.getSession(true);
            session.setAttribute("user", username.trim());
            resp.sendRedirect(req.getContextPath() + "/books");
        } else {
            req.setAttribute("error", "Invalid username or password");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
