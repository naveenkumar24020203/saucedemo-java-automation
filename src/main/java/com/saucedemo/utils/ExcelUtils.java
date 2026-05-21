package com.saucedemo.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ExcelUtils {
    private ExcelUtils() {
    }

    public static List<Map<String, String>> readSheet(String sheetName) {
        try (InputStream inputStream = ExcelUtils.class.getClassLoader().getResourceAsStream("testData/SauceDemoData.xlsx");
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }

            List<String> headers = new ArrayList<>();
            Row headerRow = sheet.getRow(0);
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue());
            }

            List<Map<String, String>> rows = new ArrayList<>();
            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null) continue;
                Map<String, String> rowData = new HashMap<>();
                for (int colIndex = 0; colIndex < headers.size(); colIndex++) {
                    Cell cell = row.getCell(colIndex);
                    rowData.put(headers.get(colIndex), cell == null ? "" : cell.toString());
                }
                rows.add(rowData);
            }
            return rows;
        } catch (IOException e) {
            throw new RuntimeException("Unable to read Excel test data: " + e.getMessage(), e);
        }
    }
}
