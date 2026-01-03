package com.inventory.Util;

import com.inventory.DAO.BillsDAO;
import com.inventory.DTO.BillItemDTO;
import com.inventory.UI.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Locale;
import javax.swing.border.MatteBorder;
import javax.swing.table.JTableHeader;


public class BillTable extends JFrame {
    private final String[] columnNames = {"Product", "Quantity", "Sell Price", "Net Price"};
    private List<BillItemDTO> billItems;

    public BillTable(String _businessName, int billId){
        billItems = new ArrayList<>();
        getBillDetails(billId);

        //JTable for bill pop-up
        setTitle("Bill " + billId);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 450);
        setLocationRelativeTo(null);

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setForeground(Color.BLACK);

        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        add(mainPanel);

        // ---------- TOP PANEL ----------
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        // Title
        JLabel titleLabel = new JLabel(_businessName, SwingConstants.LEFT);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBackground(Color.WHITE);

        // Invoice info (right aligned)
        JLabel invoiceLabel = new JLabel("<html><b>Invoice No.:</b> " + billId + " &nbsp;&nbsp; <b>Invoice Date:</b> " + billItems.get(0).getBillDate() + "</html>",SwingConstants.RIGHT);
        invoiceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        invoiceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        invoiceLabel.setForeground(Color.BLACK);

        // "To" + Customer Info
        JLabel toLabel = new JLabel("<html><b>To:</b> " + billItems.get(0).getCustomerName() + "</html>", SwingConstants.LEFT);
        toLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        toLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        toLabel.setForeground(Color.BLACK);


        // Thick Border line
        JPanel separatorPanel = new JPanel();
        separatorPanel.setBorder(new MatteBorder(2, 0, 0, 0, Color.GRAY)); // top and bottom thick border


        // Add to topPanel
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(invoiceLabel);
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(toLabel);
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(separatorPanel);
        topPanel.setBackground(Color.WHITE);
        topPanel.setForeground(Color.BLACK);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Convert list → Object[][] for DefaultTableModel
        Object[][] data = new Object[billItems.size()][5];
        for (int i = 0; i < billItems.size(); i++) {
            BillItemDTO item = billItems.get(i);
            data[i][0] = item.getProductName();
            data[i][1] = item.getQuantity();
            data[i][2] = item.getPrice();
            data[i][3] = item.getTotal();
        }

        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // prevent editing
            }
        };

        JTable billTable = new JTable(tableModel);

        billTable.setBackground(Color.WHITE);
        billTable.setForeground(Color.BLACK);
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
        billTable.getTableHeader().setBackground(Color.CYAN);
        billTable.getTableHeader().setForeground(Color.BLACK);

        // Right-align numeric columns
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
        billTable.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        billTable.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
        billTable.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);

//        JScrollPane scrollPane = new JScrollPane(billTable);
//        scrollPane.setBackground(Color.WHITE);
//        scrollPane.setForeground(Color.BLACK);
//        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // ---------- BOTTOM PANEL ----------
        JPanel bottomPanel = new JPanel(new BorderLayout());


        String amount = CurrencyFormatter.format(String.valueOf(billItems.get(0).getNetTotal()));

        System.out.println(billItems.get(0).getNetTotal());
        System.out.println(amount);

        JLabel totalLabel = new JLabel("Gross Total: " + amount);
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        totalLabel.setForeground(Color.RED);
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.setForeground(Color.BLACK);
        bottomPanel.add(totalLabel, BorderLayout.EAST);

        // Panel to hold Print button above the table
        JPanel tablePanel = new JPanel(new BorderLayout(5, 5));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton printButton = new JButton();
        printButton.setFont(new java.awt.Font("Segoe UI", 1, 12));
        printButton.setText("Print");
        printButton.setForeground(Color.RED);
        printButton.setBackground(Color.WHITE);
        printButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintHelper.printPanel(mainPanel);
            }
        });

        buttonPanel.add(printButton);
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setForeground(Color.BLACK);

        topPanel.add(buttonPanel, BorderLayout.EAST);

        tablePanel.add(billTable.getTableHeader(), BorderLayout.NORTH);
        tablePanel.add(billTable, BorderLayout.CENTER);

        tablePanel.setBackground(Color.WHITE);
        tablePanel.setForeground(Color.BLACK);

        mainPanel.add(tablePanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    void getBillDetails(int billId){
        BillsDAO billsDAO = new BillsDAO();

        try {
            ResultSet resultSet = billsDAO.getBillItemsByBillId(billId);

            while (resultSet.next()) {
                BillItemDTO billItem = new BillItemDTO(billId, resultSet.getString("productname"), resultSet.getInt("quantity"), resultSet.getInt("price"), resultSet.getInt("total_amount"), resultSet.getString("fullname"), resultSet.getString("bill_date"));
                billItems.add(billItem);
            }

        }catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}
