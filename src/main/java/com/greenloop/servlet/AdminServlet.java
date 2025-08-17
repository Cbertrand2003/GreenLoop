package com.greenloop.servlet;

import com.greenloop.model.Role;
import com.greenloop.model.User;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name="AdminServlet", urlPatterns="/admin")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        if(u==null || u.getRole()!=Role.ADMIN){ resp.sendError(403); return; }
        req.getRequestDispatcher("/admin.jsp").forward(req, resp);
    }
}
