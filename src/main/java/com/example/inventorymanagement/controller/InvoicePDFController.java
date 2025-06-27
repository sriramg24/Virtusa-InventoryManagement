package com.example.inventorymanagement.controller;

import com.example.inventorymanagement.Service.InvoicePDFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/invoices")
public class InvoicePDFController {

    @Autowired
    private InvoicePDFService invoicePDFService;

    @GetMapping("/generate/{distributorCode}")
    public ResponseEntity<byte[]> generatePDF(@PathVariable String distributorCode) {
        try {
            byte[] pdf = invoicePDFService.generateInvoicePDF(distributorCode);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename("invoice_" + distributorCode + ".pdf").build());
            return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Failed to generate invoice: " + e.getMessage()).getBytes());
        }
    }
}
