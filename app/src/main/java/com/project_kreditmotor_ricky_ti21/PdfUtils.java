package com.project_kreditmotor_ricky_ti21;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.FileOutputStream;
import java.io.IOException;

public class PdfUtils {
    public static void createPdf(String filePath, String[] headers, String[][] data) {
        Document document = new Document(PageSize.A4);

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            // Create and format table
            PdfPTable table = new PdfPTable(headers.length);
            table.setWidthPercentage(100);

            // Add headers to the table
            for (String header : headers) {
                PdfPCell cell = new PdfPCell(new Paragraph(header));
                table.addCell(cell);
            }

            // Add data rows to the table
            for (String[] row : data) {
                for (String cellValue : row) {
                    PdfPCell cell = new PdfPCell(new Paragraph(cellValue));
                    table.addCell(cell);
                }
            }

            // Add table to the document
            document.add(table);

            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
