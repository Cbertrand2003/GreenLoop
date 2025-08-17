package com.greenloop.service;

import com.greenloop.model.Order;
import com.greenloop.model.OrderItem;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.math.BigDecimal;

public class InvoiceService {
    public String generateInvoice(Order order, String dir) throws Exception {
        String path = dir + "/invoice_order_" + order.getId() + ".pdf";
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(path));
        doc.open();

        doc.add(new Paragraph("GreenLoop - Invoice"));
        doc.add(new Paragraph("Order ID: " + order.getId()));
        doc.add(new Paragraph("Customer ID: " + order.getCustomerId()));
        doc.add(new Paragraph("Created: " + order.getCreatedAt()));
        doc.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(4);
        table.addCell("Product");
        table.addCell("Price");
        table.addCell("Qty");
        table.addCell("Subtotal");

        BigDecimal total = BigDecimal.ZERO;
        for(OrderItem item: order.getItems()){
            table.addCell(item.getProductName());
            table.addCell(item.getPrice().toPlainString());
            table.addCell(String.valueOf(item.getQuantity()));
            BigDecimal sub = item.getPrice().multiply(new BigDecimal(item.getQuantity()));
            table.addCell(sub.toPlainString());
            total = total.add(sub);
        }
        doc.add(table);
        doc.add(Chunk.NEWLINE);
        doc.add(new Paragraph("TOTAL: " + total.toPlainString()));
        doc.close();
        return path;
    }
}
