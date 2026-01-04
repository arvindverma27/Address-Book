package com.address.book.export;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.address.book.dto.UserDTO;

import jakarta.servlet.ServletOutputStream;

public class ExcelExporter {

    public void exportToExcel(List<UserDTO> userList, ServletOutputStream outputStream) throws IOException {

        try (Workbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet("Contact List");

            Row headerRow = sheet.createRow(0);
            String[] columns = { "ID", "First Name", "Last Name", "Email", "Mobile Number", "Gender", "Address", "City" };

            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            int rowNum = 1;
            for (UserDTO userDTO : userList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(userDTO.getId());
                row.createCell(1).setCellValue(userDTO.getFirstName());
                row.createCell(2).setCellValue(userDTO.getLastName());
                row.createCell(3).setCellValue(userDTO.getEmail());
                row.createCell(4).setCellValue(userDTO.getMobileNumber());
                row.createCell(5).setCellValue(userDTO.getGender());
                row.createCell(6).setCellValue(userDTO.getAddress());
                row.createCell(7).setCellValue(userDTO.getCityName());
            }

            for (int i = 0; i < columns.length; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(outputStream);
        }
    }
}
