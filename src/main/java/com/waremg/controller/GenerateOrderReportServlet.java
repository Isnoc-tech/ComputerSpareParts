package com.waremg.controller;



import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.waremg.classes.Order;
import com.waremg.models.OrderDBUtil;
import com.waremg.models.SupplierDBUtil;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.BaseColor;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/GenerateOrderReportServlet")
public class GenerateOrderReportServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private SupplierDBUtil supplierDBUtil;
    private OrderDBUtil orderDBUtil;

    public void init() {
        supplierDBUtil = new SupplierDBUtil();
        orderDBUtil = new OrderDBUtil();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String supplierIdStr = request.getParameter("supplierId");
        int supplierId = Integer.parseInt(supplierIdStr);

        List<Order> orders = orderDBUtil.getAllOrders();

        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try {
            PdfWriter.getInstance(document, baos);
            document.open();

            // Add a title
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD, BaseColor.BLACK);
            Paragraph title = new Paragraph("Order Report", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add a table with order details
            PdfPTable table = new PdfPTable(5); // columns
            table.setWidthPercentage(100);
            
            Font cellFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL, BaseColor.BLACK);
            
            // Add table headers
            addTableHeader(table, cellFont);

            // Add order details
            addTableData(table, orders, cellFont);

            document.add(table);
            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }


        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=order_report.pdf");


        OutputStream out = response.getOutputStream();
        baos.writeTo(out);
        out.close();
    }




    private void addTableHeader(PdfPTable table, Font font) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setPhrase(new Phrase("Order ID", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Quantity", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Unit Price", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Total", font));
        table.addCell(cell);
    }

    private void addTableData(PdfPTable table, List<Order> orders, Font font) {
    	double itemtotal;
    	double total = 0;
        for (Order order : orders) {
        	itemtotal = 0;
            table.addCell(new Phrase(String.valueOf(order.getOrderID()), font));
            table.addCell(new Phrase(order.getDescription(), font));
            table.addCell(new Phrase(String.valueOf(order.getQuantity()), font));
            table.addCell(new Phrase(String.valueOf(order.getUnitPrice()), font));
            itemtotal += order.getQuantity() * order.getUnitPrice();
            total += order.getQuantity() * order.getUnitPrice();
            table.addCell(new Phrase(String.valueOf(itemtotal), font));
        }
        
        table.addCell(new Phrase());
        table.addCell(new Phrase());
        table.addCell(new Phrase());
        table.addCell(new Phrase("Total Price", font));
        table.addCell(new Phrase(String.valueOf(total), font));
    }
}