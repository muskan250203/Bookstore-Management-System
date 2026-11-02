package com.bookstore.servlet;

import com.bookstore.dao.BookDAO;
import com.bookstore.dao.ReportDAO;
import com.bookstore.model.Book;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

/** Front controller for all book operations. Uses ?action=... */
@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private final BookDAO bookDAO = new BookDAO();
    private final ReportDAO reportDAO = new ReportDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) action = "list";

        try {
            switch (action) {
                case "new":
                    req.getRequestDispatcher("/book-form.jsp").forward(req, resp);
                    break;
                case "edit":
                    int id = Integer.parseInt(req.getParameter("id"));
                    req.setAttribute("book", bookDAO.getById(id));
                    req.getRequestDispatcher("/book-form.jsp").forward(req, resp);
                    break;
                case "insert":
                    bookDAO.save(readBook(req));
                    resp.sendRedirect(req.getContextPath() + "/books?msg=Book+added+successfully");
                    break;
                case "update":
                    Book b = readBook(req);
                    b.setId(Integer.parseInt(req.getParameter("id")));
                    bookDAO.update(b);
                    resp.sendRedirect(req.getContextPath() + "/books?msg=Book+updated+successfully");
                    break;
                case "delete":
                    bookDAO.delete(Integer.parseInt(req.getParameter("id")));
                    resp.sendRedirect(req.getContextPath() + "/books?msg=Book+deleted");
                    break;
                case "search":
                    String kw = req.getParameter("keyword");
                    List<Book> found = (kw == null || kw.trim().isEmpty())
                            ? bookDAO.getAll() : bookDAO.search(kw.trim());
                    showList(req, resp, found);
                    break;
                default:
                    showList(req, resp, bookDAO.getAll());
            }
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Please enter valid numeric values for price and quantity.");
            req.getRequestDispatcher("/book-form.jsp").forward(req, resp);
        }
    }

    private void showList(HttpServletRequest req, HttpServletResponse resp, List<Book> books)
            throws ServletException, IOException {
        req.setAttribute("books", books);
        req.setAttribute("stats", reportDAO.getStats());   // JDBC aggregate query
        req.setAttribute("msg", req.getParameter("msg"));
        req.getRequestDispatcher("/books.jsp").forward(req, resp);
    }

    private Book readBook(HttpServletRequest req) {
        Book b = new Book();
        b.setTitle(req.getParameter("title").trim());
        b.setAuthor(req.getParameter("author").trim());
        b.setCategory(req.getParameter("category").trim());
        b.setPrice(Double.parseDouble(req.getParameter("price")));
        b.setQuantity(Integer.parseInt(req.getParameter("quantity")));
        return b;
    }
}
