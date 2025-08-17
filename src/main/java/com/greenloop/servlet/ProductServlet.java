package com.greenloop.servlet;

import com.greenloop.model.*;
import com.greenloop.service.ProductService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name="ProductServlet", urlPatterns="/products")
public class ProductServlet extends HttpServlet {
    private final ProductService service = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String q = req.getParameter("q");
        try {
            List<Product> products = service.list(q);
            req.setAttribute("products", products);
            req.getRequestDispatcher("/products.jsp").forward(req, resp);
        } catch (Exception e){
            throw new ServletException(e);
        }
    }

    // Vendor actions (create/update/delete)
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        if(u==null || (u.getRole()!=Role.VENDOR && u.getRole()!=Role.ADMIN)){ resp.sendError(403); return; }

        String action = req.getParameter("action");
        try{
            if("create".equals(action)){
                Product p = new Product();
                p.setVendorId(u.getId());
                p.setName(req.getParameter("name"));
                p.setDescription(req.getParameter("description"));
                p.setPrice(new BigDecimal(req.getParameter("price")));
                p.setStock(Integer.parseInt(req.getParameter("stock")));
                p.setSustainabilityCert(req.getParameter("cert"));
                service.create(p);
            } else if("update".equals(action)){
                Product p = service.get(Integer.parseInt(req.getParameter("id")));
                p.setName(req.getParameter("name"));
                p.setDescription(req.getParameter("description"));
                p.setPrice(new BigDecimal(req.getParameter("price")));
                p.setStock(Integer.parseInt(req.getParameter("stock")));
                p.setSustainabilityCert(req.getParameter("cert"));
                service.update(p);
            } else if("delete".equals(action)){
                service.delete(Integer.parseInt(req.getParameter("id")));
            }
            resp.sendRedirect(req.getContextPath()+"/vendor");
        } catch (Exception e){ throw new ServletException(e); }
    }
}
