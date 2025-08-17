package com.greenloop.servlet;

import com.greenloop.model.Role;
import com.greenloop.model.User;
import com.greenloop.service.ProductService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name="VendorServlet", urlPatterns="/vendor")
public class VendorServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        if(u==null || (u.getRole()!=Role.VENDOR && u.getRole()!=Role.ADMIN)){ resp.sendError(403); return; }
        try{
            req.setAttribute("vendorProducts", productService.byVendor(u.getId()));
            req.getRequestDispatcher("/vendor.jsp").forward(req, resp);
        } catch(Exception e){ throw new ServletException(e); }
    }
}
