package com.example.inventorymanagement.Service;

import com.example.inventorymanagement.model.InventoryItem;
import com.example.inventorymanagement.model.Outlet;
import com.example.inventorymanagement.model.OutletInventoryItem;
import com.example.inventorymanagement.repository.ItemRepository;
import com.example.inventorymanagement.repository.OutletInventoryItemRepository;
import com.example.inventorymanagement.repository.OutletRepository;
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
import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private OutletRepository outletRepository;

    @Autowired
    private OutletInventoryItemRepository outletInventoryItemRepository;

    @Autowired
    private ItemRepository inventoryItemRepository;

    public byte[] generateInvoiceOutlet(Long outletId) throws Exception {
        Outlet outlet = outletRepository.findById(outletId)
                .orElseThrow(() -> new RuntimeException("Outlet not found"));

        List<OutletInventoryItem> items = outletInventoryItemRepository.findByOutletId(outletId);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4, 36, 36, 36, 36);
        PdfWriter.getInstance(document, out);
        document.open();

        // Invoice header
        Paragraph header = new Paragraph("INVOICE - OUTLET", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18));
        header.setAlignment(Element.ALIGN_CENTER);
        document.add(header);
        document.add(new Paragraph(" "));

        // Invoice metadata + company info
        PdfPTable metaTable = new PdfPTable(2);
        metaTable.setWidthPercentage(100);
        metaTable.setWidths(new int[]{50, 50});

        PdfPTable left = new PdfPTable(1);
        left.addCell(getNoBorderCell("Invoice #: INV-" + System.currentTimeMillis()));
        left.addCell(getNoBorderCell("Invoice Date: " + LocalDate.now()));
        left.addCell(getNoBorderCell("Due Date: " + LocalDate.now().plusMonths(1)));

        PdfPTable right = new PdfPTable(1);
        try {
            Image logo = Image.getInstance(getClass().getClassLoader().getResource("static/logo.jpg"));
            logo.scaleToFit(100, 100);
            logo.setAlignment(Image.ALIGN_RIGHT);
            document.add(logo);
        } catch (Exception e) {
            // optional
        }
        right.addCell(getNoBorderCell("E - Mart Inventory Management Pvt. Ltd."));
        right.addCell(getNoBorderCell("27, Anna Nagar, Chennai, Tamil Nadu - 600001"));
        right.addCell(getNoBorderCell("Phone: +91 9999999999"));

        metaTable.addCell(left);
        metaTable.addCell(right);
        document.add(metaTable);
        document.add(new Paragraph(" "));

        // Outlet information
        PdfPTable outletTable = new PdfPTable(1);
        outletTable.setWidthPercentage(100);
        PdfPCell outletCell = new PdfPCell();
        outletCell.addElement(new Paragraph("Outlet: " + outlet.getName()));
        outletCell.addElement(new Paragraph("Manager: " + outlet.getManagerName()));
        outletCell.addElement(new Paragraph("Phone: " + outlet.getContactNumber()));
        outletCell.addElement(new Paragraph("Location: " + outlet.getLocation()));
        outletCell.setBorder(Rectangle.BOX);
        outletTable.addCell(outletCell);
        document.add(outletTable);
        document.add(new Paragraph(" "));

        // Item list table
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{10, 30, 10, 20, 20});
        table.addCell("S.No");
        table.addCell("Item Name");
        table.addCell("Qty");
        table.addCell("Item ID");
        table.addCell("Total");

        double subtotal = 0;
        int i = 1;
        for (OutletInventoryItem itemEntry : items) {
            InventoryItem item = inventoryItemRepository.findById(itemEntry.getInventoryItem().getId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));

            double price = item.getCost() * itemEntry.getQuantity();
            subtotal += price;

            table.addCell(String.valueOf(i++));
            table.addCell(item.getName());
            table.addCell(String.valueOf(itemEntry.getQuantity()));
            table.addCell(String.valueOf(item.getId()));
            table.addCell("₹" + price);
        }
        document.add(table);
        document.add(new Paragraph(" "));

        // Totals with tax
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

        // Barcode generation
        String barcodeData = "OUTLET-" + outletId + "-" + System.currentTimeMillis();
        Image barcodeImage = generateBarcodeImage(barcodeData);
        barcodeImage.setAlignment(Element.ALIGN_CENTER);
        barcodeImage.scalePercent(150);
        document.add(barcodeImage);

        // Digital signature text
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
