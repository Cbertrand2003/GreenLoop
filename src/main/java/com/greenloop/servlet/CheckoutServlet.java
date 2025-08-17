package com.greenloop.servlet;

import com.greenloop.model.*;
import com.greenloop.notify.ConsoleEmailService;
import com.greenloop.notify.NotificationService;
import com.greenloop.service.*;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name="CheckoutServlet", urlPatterns="/checkout")
public class CheckoutServlet extends HttpServlet {
    private final OrderService orderService = new OrderService();
    private final ProductService productService = new ProductService();
    private final InvoiceService invoiceService = new InvoiceService();
    private final NotificationService notifications = new NotificationService();

    @Override
    public void init() throws ServletException {
        notifications.subscribe(new ConsoleEmailService());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/checkout.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        if(u==null || u.getRole()!=Role.CUSTOMER){ resp.sendError(403); return; }
        ShoppingCart cart = (ShoppingCart) req.getSession().getAttribute("cart");
        if(cart==null || cart.isEmpty()){ resp.sendRedirect(req.getContextPath()+"/cart"); return; }

        try{
            // Place order
            int orderId = orderService.createFromCart(u.getId(), cart);
            // decrement stock
            for(CartItem ci : cart.getItems()){
                productService.decrementStock(ci.getProduct().getId(), ci.getQuantity());
                if(ci.getProduct().getStock() - ci.getQuantity() <= 2){
                    notifications.lowStock("Product "+ci.getProduct().getName()+" is low on stock.");
                }
            }
            Order order = orderService.get(orderId);

            // Generate invoice PDF into temp folder inside the webapp
            String dir = getServletContext().getRealPath("/invoices");
            new File(dir).mkdirs();
            String path = invoiceService.generateInvoice(order, dir);
            orderService.setInvoice(orderId, "invoices/" + new File(path).getName());

            // Notify
            notifications.orderPlaced("Order #" + orderId + " placed by user " + u.getEmail());

            cart.clear(); // empty cart
            resp.sendRedirect(req.getContextPath()+"/orders.jsp?success=1&orderId="+orderId);
        } catch(Exception e){ throw new ServletException(e); }
    }
}
