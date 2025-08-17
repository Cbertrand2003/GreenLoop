package com.greenloop.servlet;

import com.greenloop.model.*;
import com.greenloop.service.ProductService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name="CartServlet", urlPatterns="/cart")
public class CartServlet extends HttpServlet {
    private final ProductService productService = new ProductService();

    private ShoppingCart cart(HttpSession session){
        ShoppingCart c = (ShoppingCart) session.getAttribute("cart");
        if(c==null){ c = new ShoppingCart(); session.setAttribute("cart", c); }
        return c;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try{
            if("add".equals(action)){
                int pid = Integer.parseInt(req.getParameter("productId"));
                int qty = Integer.parseInt(req.getParameter("qty"));
                Product p = productService.get(pid);
                cart(req.getSession()).add(p, qty);
            } else if("update".equals(action)){
                int pid = Integer.parseInt(req.getParameter("productId"));
                int qty = Integer.parseInt(req.getParameter("qty"));
                cart(req.getSession()).update(pid, qty);
            } else if("remove".equals(action)){
                int pid = Integer.parseInt(req.getParameter("productId"));
                cart(req.getSession()).remove(pid);
            } else if("clear".equals(action)){
                cart(req.getSession()).clear();
            }
            resp.sendRedirect(req.getContextPath()+"/cart");
        } catch(Exception e){ throw new ServletException(e); }
    }
}
