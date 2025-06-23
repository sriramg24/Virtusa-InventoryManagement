package com.example.inventorymanagement.util;

import com.example.inventorymanagement.DTO.InventoryCSVDTO;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.List;

public class CSVHelper {
    public static List<InventoryCSVDTO> convert(InputStream fileInputStream) throws Exception {
        return new CsvToBeanBuilder<InventoryCSVDTO>(new InputStreamReader(fileInputStream))
                .withType(InventoryCSVDTO.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build()
                .parse();
    }
}
