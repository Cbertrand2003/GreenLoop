package servlets;

import dao.UserDAO;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username").trim().toLowerCase();
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername(username);

        if (user != null && user.verifyPassword(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            session.setAttribute("email", user.getEmail()); //
            
            String redirectPage = getRedirectPage(user.getRole());
            response.sendRedirect(redirectPage);
        } else {
            response.sendRedirect("login.jsp?error=invalid");
        }
    }

    private String getRedirectPage(String role) {
        switch (role.toUpperCase()) {
            case "ADMIN": return "admin/dashboard.jsp";
            case "VENDOR": return "vendor/portal.jsp";
            default: return "customer/dashboard.jsp";
        }
    }
}
