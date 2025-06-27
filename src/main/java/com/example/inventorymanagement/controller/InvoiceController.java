package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.Service.InvoicePDFService;
import com.example.inventorymanagement.Service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoice-outlet")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{outletId}")
    public ResponseEntity<byte[]> generateOutletInvoice(@PathVariable Long outletId) {
        try {
            byte[] pdfBytes = invoiceService.generateInvoiceOutlet(outletId);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename("outlet-invoice-" + outletId + ".pdf")
                    .build());

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
