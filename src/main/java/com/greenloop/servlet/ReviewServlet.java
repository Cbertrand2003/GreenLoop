package com.greenloop.servlet;

import com.greenloop.model.Review;
import com.greenloop.model.Role;
import com.greenloop.model.User;
import com.greenloop.service.ReviewService;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name="ReviewServlet", urlPatterns="/reviews")
public class ReviewServlet extends HttpServlet {
    private final ReviewService service = new ReviewService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        if(u==null || u.getRole()!=Role.CUSTOMER){ resp.sendError(403); return; }
        try{
            Review r = new Review();
            r.setProductId(Integer.parseInt(req.getParameter("productId")));
            r.setCustomerId(u.getId());
            r.setRating(Integer.parseInt(req.getParameter("rating")));
            r.setComment(req.getParameter("comment"));
            r.setDate(LocalDate.now());
            service.add(r);
            resp.sendRedirect(req.getContextPath()+"/products?reviewed=1");
        } catch(Exception e){ throw new ServletException(e); }
    }
}
