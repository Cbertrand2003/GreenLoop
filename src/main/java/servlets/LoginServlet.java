package servlets;

import dao.UserDAO;

import model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int MAX_ATTEMPTS = 5;
    private static final long LOCK_TIME_MS = 15 * 60 * 1000; // 15 minutes

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username").trim().toLowerCase();
        String password = request.getParameter("password");
        HttpSession session = request.getSession();

        // Check if account is locked
        Long lockTime = (Long) session.getAttribute("lockTime");
        if (lockTime != null && System.currentTimeMillis() - lockTime < LOCK_TIME_MS) {
            response.sendRedirect("login.jsp?error=locked");
            return;
        }

        // Process login attempt
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUserByUsername(username);

        if (user != null && user.isActive() && user.verifyPassword(password)) {
            handleSuccessfulLogin(session, user, response);
        } else {
            handleFailedLogin(session, response);
        }
    }

    private void handleSuccessfulLogin(HttpSession session, User user, HttpServletResponse response) 
            throws IOException {
        session.removeAttribute("loginAttempts");
        session.removeAttribute("lockTime");
        session.setAttribute("user", user);
        session.setMaxInactiveInterval(30 * 60); // 30 minute session
        
        response.sendRedirect(getRedirectPage(user.getRole()));
    }

    private void handleFailedLogin(HttpSession session, HttpServletResponse response) 
            throws IOException {
        Integer attempts = (Integer) session.getAttribute("loginAttempts");
        attempts = attempts == null ? 1 : attempts + 1;
        session.setAttribute("loginAttempts", attempts);

        if (attempts >= MAX_ATTEMPTS) {
            session.setAttribute("lockTime", System.currentTimeMillis());
            response.sendRedirect("login.jsp?error=locked");
        } else {
            response.sendRedirect("login.jsp?error=invalid&attempts=" + attempts);
        }
    }

    private String getRedirectPage(String role) {
        switch (role.toUpperCase()) {
            case "ADMIN": return "/admin/dashboard.jsp";
            case "VENDOR": return "/vendor/portal.jsp";
            default: return "/customer/dashboard.jsp";
        }
    }
}