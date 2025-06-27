package com.example.inventorymanagement.Service;

import com.example.inventorymanagement.model.Distributor;
import com.example.inventorymanagement.model.InventoryItem;
import com.example.inventorymanagement.repository.DistributorRepository;
import com.example.inventorymanagement.repository.ItemRepository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class InvoicePDFService {

    @Autowired
    private DistributorRepository distributorRepository;

    @Autowired
    private ItemRepository inventoryItemRepository;

    public byte[] generateInvoicePDF(String distributorCode) throws Exception {
        Distributor distributor = distributorRepository.findByDistributorCode(distributorCode)
                .orElseThrow(() -> new RuntimeException("Distributor not found"));

        List<InventoryItem> items = inventoryItemRepository.findByDistributor(distributor);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 36, 36, 36, 36);
        PdfWriter.getInstance(document, out);
        document.open();

        Paragraph header = new Paragraph("INVOICE - DISTRIBUTOR", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);
        document.add(new Paragraph(" "));

        PdfPTable metaTable = new PdfPTable(2);
        metaTable.setWidthPercentage(100);
        metaTable.setWidths(new int[]{50, 50});

        PdfPTable left = new PdfPTable(1);
        left.addCell(getNoBorderCell("Invoice #: INV-" + System.currentTimeMillis()));
        PdfPTable left1 = new PdfPTable(1);
        left.addCell(left1);
        left.addCell(getNoBorderCell("Invoice Date: " + LocalDate.now()));
        PdfPTable left2 = new PdfPTable(1);
        left.addCell(left2);
        LocalDate nextMonthDate = LocalDate.now().plusMonths(1);
        left.addCell(getNoBorderCell("Due Date: " + nextMonthDate.toString()));

        PdfPTable right = new PdfPTable(1);
        Image logo = Image.getInstance(getClass().getClassLoader().getResource("static/logo.jpg"));
        logo.scaleToFit(100, 100);
        logo.setAlignment(Image.ALIGN_RIGHT);
        document.add(logo);
        right.addCell(getNoBorderCell("E - Mart Inventory Management Pvt. Ltd."));
        right.addCell(getNoBorderCell("27,Anna Nagar,Chennai ,Tamilnadu,600001"));
        right.addCell(getNoBorderCell("Phone: +91 9999999999"));

        metaTable.addCell(left);
        metaTable.addCell(right);
        document.add(metaTable);
        document.add(new Paragraph(" "));

        PdfPTable distributorTable = new PdfPTable(1);
        distributorTable.setWidthPercentage(100);
        PdfPCell cell = new PdfPCell();
        cell.addElement(new Paragraph("Distributor: " + distributor.getName()));
        cell.addElement(new Paragraph("Email: " + distributor.getEmail()));
        cell.addElement(new Paragraph("Phone: " + distributor.getPhone()));
        cell.addElement(new Paragraph("Address: " + distributor.getAddress()));
        cell.setBorder(Rectangle.BOX);
        distributorTable.addCell(cell);
        document.add(distributorTable);
        document.add(new Paragraph(" "));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{10, 30, 10, 20, 20});
        table.addCell("S.No");
        table.addCell("Item Name");
        table.addCell("Qty");
        table.addCell("Item ID");
        table.addCell("Price");

        double subtotal = 0;
        int i = 1;
        for (InventoryItem item : items) {
            table.addCell(String.valueOf(i++));
            table.addCell(item.getName());
            table.addCell(String.valueOf(item.getQuantity()));
            table.addCell(String.valueOf(item.getId()));
            double price = item.getCost() * item.getQuantity();
            table.addCell("₹" + price);
            subtotal += price;
        }

        document.add(table);
        document.add(new Paragraph(" "));

        double tax = subtotal * 0.18;
        double total = subtotal + tax;

        PdfPTable totalTable = new PdfPTable(2);
        totalTable.setWidthPercentage(50);
        totalTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
        totalTable.setWidths(new int[]{60, 40});

        totalTable.addCell(getNoBorderCell("Subtotal:"));
        totalTable.addCell(getNoBorderCell("₹" + subtotal));
        totalTable.addCell(getNoBorderCell("Tax (18%):"));
        totalTable.addCell(getNoBorderCell("₹" + tax));
        totalTable.addCell(getNoBorderCell("Total Amount:"));
        totalTable.addCell(getNoBorderCell("₹" + total));

        document.add(totalTable);
        document.add(new Paragraph(" "));

        String barcodeData = distributorCode + "-" + System.currentTimeMillis();
        Image barcodeImage = generateBarcodeImage(barcodeData);
        barcodeImage.setAlignment(Element.ALIGN_CENTER);
        barcodeImage.scalePercent(150);
        document.add(barcodeImage);

        document.add(new Paragraph("Digitally Signed by Inventory System", FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10)));
        document.close();
        return out.toByteArray();
    }

    private PdfPCell getNoBorderCell(String content) {
        PdfPCell cell = new PdfPCell(new Phrase(content));
        cell.setBorder(Rectangle.NO_BORDER);
        return cell;
    }

    private Image generateBarcodeImage(String data) throws Exception {
        BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.CODE_128, 250, 50);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "png", out);
        return Image.getInstance(out.toByteArray());
    }
}
