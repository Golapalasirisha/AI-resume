
package com.resume_analyzer.service;

import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PdfService {

    public String extractText(MultipartFile file) throws IOException {

        // Read PDF file
        byte[] pdfBytes = file.getBytes();

        // Load PDF document
        try (PDDocument document = Loader.loadPDF(pdfBytes)) {

            // Extract text from PDF
            PDFTextStripper pdfStripper = new PDFTextStripper();

            String extractedText = pdfStripper.getText(document);

            return extractedText;
        }
    }
}
