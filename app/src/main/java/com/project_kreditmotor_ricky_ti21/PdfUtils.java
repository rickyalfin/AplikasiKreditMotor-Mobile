package com.project_kreditmotor_ricky_ti21;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class PdfUtils {
    public static void createPdf(String filePath, String content) {
        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(filePath));
            document.open();

            Paragraph paragraph = new Paragraph(content);
            document.add(paragraph);

            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
