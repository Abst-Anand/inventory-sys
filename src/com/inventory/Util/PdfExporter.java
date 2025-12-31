package com.inventory.Util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.File;
import java.io.FileOutputStream;

public class PdfExporter {
    public static void exportToPDF(JTable table, String tableHeading) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save PDF");

        // Set default file name
        fileChooser.setSelectedFile(new File("report.pdf"));

        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String fullPath = fileToSave.getAbsolutePath();

            // Ensure .pdf extension
            if (!fullPath.toLowerCase().endsWith(".pdf")) {
                fullPath += ".pdf";
            }

            try {
                Document document = new Document(PageSize.A4.rotate());
                PdfWriter.getInstance(document, new FileOutputStream(fullPath));
                document.open();

                PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
                pdfTable.setWidthPercentage(100);

                TableModel model = table.getModel();

                // Add table headers
                for (int i = 0; i < model.getColumnCount(); i++) {
                    PdfPCell header = new PdfPCell(new Phrase(model.getColumnName(i)));
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    pdfTable.addCell(header);
                }

                // Add table rows
                for (int row = 0; row < model.getRowCount(); row++) {
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        Object value = model.getValueAt(row, col);
                        pdfTable.addCell(value != null ? value.toString() : "");
                    }
                }

                document.add(new Paragraph(tableHeading));
                document.add(Chunk.NEWLINE);
                document.add(pdfTable);
                document.close();

                JOptionPane.showMessageDialog(null, "PDF saved to:\n" + fullPath);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }
}
