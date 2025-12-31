package com.inventory.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class PrintHelper {

    public static void print(JTable table, String title) {
        try {
            boolean complete = table.print(
                    JTable.PrintMode.FIT_WIDTH, // Fits the table to page width
                    new java.text.MessageFormat(title), // Header text
                    new java.text.MessageFormat("Page - {0}") // Footer text
            );
            if (!complete) {
                System.out.println("User canceled the printing.");
            }
        } catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }
    public static void printPanel(JPanel panel) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setJobName("Print Bill");

        job.setPrintable(new Printable() {
            @Override
            public int print(Graphics g, PageFormat pf, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return NO_SUCH_PAGE;
                }

                Graphics2D g2d = (Graphics2D) g;
                g2d.translate(pf.getImageableX(), pf.getImageableY());

                // Scale to fit page width (optional)
                double scaleX = pf.getImageableWidth() / panel.getWidth();
                double scaleY = pf.getImageableHeight() / panel.getHeight();
                double scale = Math.min(scaleX, scaleY);
                g2d.scale(scale, scale);

                panel.printAll(g2d); // render the panel
                return PAGE_EXISTS;
            }
        });

        boolean ok = job.printDialog();
        if (ok) {
            try {
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }
}
