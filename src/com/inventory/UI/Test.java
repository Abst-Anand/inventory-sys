package com.inventory.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.border.MatteBorder;


public class Test extends JFrame {


        public Test() {
            setTitle("Bill: 1234");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(700, 450);
            setLocationRelativeTo(null);

            // Main panel with padding
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BorderLayout(10, 10));
            mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
            add(mainPanel);

            // ---------- TOP PANEL ----------
            JPanel topPanel = new JPanel();
            topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

            // Title
            JLabel titleLabel = new JLabel("business", SwingConstants.LEFT);
            titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
            titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Invoice info (right aligned)
            JLabel invoiceLabel = new JLabel("<html><b>Invoice No.:</b> " + "1234" + " &nbsp;&nbsp; <b>Invoice Date:</b> " + "Fri Jan 16 23:12:40 IST 2021" + "</html>",SwingConstants.RIGHT);
            invoiceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            invoiceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

            // "To" + Customer Info
            JLabel toLabel = new JLabel("<html><b>To:</b> " + "cid-03: Jon Doe" + "</html>", SwingConstants.LEFT);
            toLabel.setFont(new Font("Arial", Font.PLAIN, 14));


            toLabel.setAlignmentX(Component.LEFT_ALIGNMENT);


            // Thick Border line
            JPanel separatorPanel = new JPanel();
            separatorPanel.setBorder(new MatteBorder(2, 0, 0, 0, Color.BLACK)); // top and bottom thick border

            // Add to topPanel
            topPanel.add(titleLabel);
            topPanel.add(Box.createVerticalStrut(10));
            topPanel.add(invoiceLabel);
            topPanel.add(Box.createVerticalStrut(10));
            topPanel.add(toLabel);
            topPanel.add(Box.createVerticalStrut(10));
            topPanel.add(separatorPanel);

            mainPanel.add(topPanel, BorderLayout.NORTH);

            // ---------- TABLE ----------
            String[] columnNames = {"Product Id","Product Name", "Quantity", "Sell Price", "Net Price"};
            Object[][] data = {
                    {"pid-1", "Laptop", 2, 50000, 100000},
                    {"pid-2", "Mouse", 3, 500, 1500},
                    {"pid-3", "Keyboard", 1, 1500, 1500},
            };

            DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false; // prevent editing
                }
            };

            JTable billTable = new JTable(tableModel);

            // Center-align all columns
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < billTable.getColumnCount(); i++) {
                billTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            // Make table look nicer
            billTable.setRowHeight(28);
            billTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            billTable.setGridColor(Color.LIGHT_GRAY);

            // Header styling
            billTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
            billTable.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.GRAY));

            // Right-align numeric columns
            DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
            rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
            billTable.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
            billTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
            billTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

            JScrollPane scrollPane = new JScrollPane(billTable);
            mainPanel.add(scrollPane, BorderLayout.CENTER);

            // ---------- BOTTOM PANEL ----------
            JPanel bottomPanel = new JPanel(new BorderLayout());
            int netTotal = 103000;
            JLabel totalLabel = new JLabel("Net Total: " + netTotal);
            totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
            totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
            bottomPanel.add(totalLabel, BorderLayout.EAST);

            // Panel to hold Print button above the table
            JPanel tablePanel = new JPanel(new BorderLayout(5, 5));

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton printButton = new JButton();
            printButton.setFont(new java.awt.Font("Segoe UI", 1, 12));
            printButton.setText("Print");
            printButton.setForeground(Color.RED);
            printButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            buttonPanel.add(printButton);

            tablePanel.add(buttonPanel, BorderLayout.NORTH);
            tablePanel.add(scrollPane, BorderLayout.CENTER);

            mainPanel.add(tablePanel, BorderLayout.CENTER);
            mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Test().setVisible(true));
    }
}
