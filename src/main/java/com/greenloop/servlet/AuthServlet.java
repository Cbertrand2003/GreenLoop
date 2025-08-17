package com.greenloop.servlet;

import com.greenloop.model.Role;
import com.greenloop.model.User;
import com.greenloop.service.AuthService;
import com.greenloop.util.ValidationUtil;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name="AuthServlet", urlPatterns="/auth")
public class AuthServlet extends HttpServlet {
    private final AuthService auth = new AuthService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if("login".equals(action)){
                String email = req.getParameter("email");
                String pass = req.getParameter("password");
                User u = auth.login(email, pass);
                if(u==null){
                    req.setAttribute("error", "Invalid credentials");
                    req.getRequestDispatcher("/login.jsp").forward(req, resp);
                    return;
                }
                req.getSession().setAttribute("user", u);
                resp.sendRedirect(req.getContextPath()+"/products");
            } else if("register".equals(action)){
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                String pass = req.getParameter("password");
                String role = req.getParameter("role");

                if(!ValidationUtil.isEmail(email) || pass.length()<6){
                    req.setAttribute("error","Invalid email or password too short.");
                    req.getRequestDispatcher("/register.jsp").forward(req, resp);
                    return;
                }
                Role r = Role.valueOf(role);
                auth.register(name, email, pass, r);
                resp.sendRedirect(req.getContextPath()+"/login.jsp?registered=1");
            } else {
                resp.sendError(400);
            }
        } catch (Exception e){
            throw new ServletException(e);
        }
    }
}
