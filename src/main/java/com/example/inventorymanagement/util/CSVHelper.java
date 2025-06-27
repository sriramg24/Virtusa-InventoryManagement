package com.example.inventorymanagement.util;

import com.example.inventorymanagement.DTO.InventoryCSVDTO;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class CSVHelper {

    public static List<InventoryCSVDTO> convert(InputStream inputStream) {
        List<InventoryCSVDTO> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                     .withFirstRecordAsHeader()
                     .withIgnoreHeaderCase()
                     .withTrim())) {

            for (CSVRecord record : csvParser) {
                InventoryCSVDTO dto = new InventoryCSVDTO();
                dto.setName(record.get("name"));
                dto.setCategoryName(record.get("categoryName"));
                dto.setQuantity(Integer.parseInt(record.get("quantity")));
                dto.setExpiration(record.get("expiration"));
                dto.setCost(Integer.parseInt(record.get("cost")));
                dto.setCurrency(record.get("currency"));
                dto.setProductdescription(record.get("productdescription"));
                dto.setStatus(record.get("status"));
                dto.setDistributorCode(record.get("distributorCode"));
                list.add(dto);
            }

        } catch (IOException e) {
            throw new RuntimeException("CSV parse error: " + e.getMessage());
        }
        return list;
    }
}
