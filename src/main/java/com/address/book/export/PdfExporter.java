package com.address.book.export;

import java.io.IOException;
import java.util.List;

import com.address.book.dto.UserDTO;
import jakarta.servlet.ServletOutputStream;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;


public class PdfExporter {

    public void exportToPdf(List<UserDTO> userDTOList, ServletOutputStream outputStream) throws IOException {
       
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc);

        
        document.add(new Paragraph("User List").setBold().setFontSize(16));


       
        Table table = new Table(8);

        // Header cells
        table.addHeaderCell("ID");
        table.addHeaderCell("First Name");
        table.addHeaderCell("Last Name");
        table.addHeaderCell("Email");
        table.addHeaderCell("Mobile Number");
        table.addHeaderCell("Gender");
        table.addHeaderCell("Address");
        table.addHeaderCell("City");

        // Data rows
        for (UserDTO userDTO : userDTOList) {
            table.addCell(String.valueOf(userDTO.getId()));
            table.addCell(userDTO.getFirstName());
            table.addCell(userDTO.getLastName());
            table.addCell(userDTO.getEmail());
            table.addCell(userDTO.getMobileNumber());
            table.addCell(userDTO.getGender());
            table.addCell(userDTO.getAddress());
            table.addCell(userDTO.getCityName());
        }

        document.add(table);
        document.close(); // Close the document
    }
}
