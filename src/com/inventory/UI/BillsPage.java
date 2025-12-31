package com.inventory.UI;

import com.inventory.DAO.BillsDAO;
import com.inventory.DAO.ProductDAO;
import com.inventory.Util.BillTable;
import com.inventory.Util.PdfExporter;
import com.inventory.Util.PrintHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.sql.SQLException;

/**
 * @author anand
 */
public class BillsPage extends javax.swing.JPanel {

    Dashboard dashboard;

    public BillsPage(Dashboard dashboard) {
        initComponents();
        this.dashboard = dashboard;
        loadDataSet();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        jScrollPane1 = new javax.swing.JScrollPane();
        billTable = new javax.swing.JTable();

        refreshButton = new javax.swing.JButton();
        downloadButton = new javax.swing.JButton();
        printButton = new javax.swing.JButton();
        searchText = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Impact", 0, 24)); // NOI18N
        jLabel1.setText("Billings");

        // -------- TABLE --------
        billTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null},
                        {null, null, null, null}
                },
                new String[]{
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }
        ));
        billTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                purchaseTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(billTable);

        // -------- TOP ACTIONS --------
        refreshButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        refreshButton.setText("REFRESH");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        downloadButton.setFont(new java.awt.Font("Segoe UI", 1, 12));
        downloadButton.setText("Save as Pdf");
        downloadButton.setForeground(Color.RED);
        downloadButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        downloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadButtonActionPerformed(evt);
            }
        });

        printButton.setFont(new java.awt.Font("Segoe UI", 1, 12));
        printButton.setText("Print");
        printButton.setForeground(Color.RED);
        printButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        printButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printButtonActionPerformed(evt);
            }
        });

        searchText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchTextKeyReleased(evt);
            }
        });

        jLabel10.setText("Search:");

        // -------- LAYOUT (RIGHT PANEL REMOVED) --------
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator1)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(refreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(downloadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(printButton, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(refreshButton)
                                        .addComponent(downloadButton)
                                        .addComponent(printButton)
                                        .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                                .addContainerGap())
        );
    } // </editor-fold>

    // ---------- Actions ----------
    private void downloadButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String tableHeading = "business";
        PdfExporter.exportToPDF(billTable, tableHeading);
    }

    private void printButtonActionPerformed(java.awt.event.ActionEvent evt) {
        String printHeading = "Billings Report";
        PrintHelper.print(billTable, printHeading);
    }

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {
        loadDataSet();
    }

    private void searchTextKeyReleased(java.awt.event.KeyEvent evt) {
        loadSearchData(searchText.getText());
    }

    private void purchaseTableMouseClicked(java.awt.event.MouseEvent evt) {
        // kept in case you still need row-click behavior; currently no-op for create-bill removal
    }

    // ---------- Utilities ----------
    private void addViewButtonToTable(DefaultTableModel model) {
        TableColumn viewColumn = billTable.getColumnModel().getColumn(billTable.getColumnCount() - 1); // last column
        viewColumn.setCellRenderer(new ButtonRenderer());
        viewColumn.setCellEditor(new ButtonEditor(new JCheckBox(), model));
    }

    public void loadDataSet() {
        try {
            BillsDAO billsDAO = new BillsDAO();
            DefaultTableModel model = billsDAO.buildTableModel(billsDAO.getQueryResult());
            billTable.setModel(model);
            addViewButtonToTable(model);

            // sorting in table
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            // numeric comparator example (adjust indexes to your columns)
            sorter.setComparator(3, Comparator.comparingDouble(o -> {
                try { return Double.parseDouble(o.toString()); } catch (Exception e) { return 0d; }
            }));
            // datetime comparator example (adjust index)
            sorter.setComparator(2, (o1, o2) -> {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
                try {
                    Date d1 = format.parse(o1.toString());
                    Date d2 = format.parse(o2.toString());
                    return d1.compareTo(d2);
                } catch (ParseException e) {
                    return 0;
                }
            });
            billTable.setRowSorter(sorter);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void loadSearchData(String text) {
        try {
            // If your search should be on bills, point to BillsDAO instead.
            BillsDAO billsDAO = new BillsDAO();
            DefaultTableModel model = billsDAO.buildTableModel(billsDAO.getBillSearch(text));
            billTable.setModel(model);
            addViewButtonToTable(model);

            // sorting in table
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
            // numeric comparator example (adjust indexes to your columns)
            sorter.setComparator(3, Comparator.comparingDouble(o -> {
                try { return Double.parseDouble(o.toString()); } catch (Exception e) { return 0d; }
            }));
            // datetime comparator example (adjust index)
            sorter.setComparator(2, (o1, o2) -> {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
                try {
                    Date d1 = format.parse(o1.toString());
                    Date d2 = format.parse(o2.toString());
                    return d1.compareTo(d2);
                } catch (ParseException e) {
                    return 0;
                }
            });
            billTable.setRowSorter(sorter);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // -------- Variables (right panel removed) --------
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable billTable;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton downloadButton;
    private javax.swing.JButton printButton;
    private javax.swing.JTextField searchText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JSeparator jSeparator1;
}

// Renderer for JButton
class ButtonRenderer extends JButton implements TableCellRenderer {
    public ButtonRenderer() { setOpaque(true); }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setText("View");
        setForeground(Color.RED);
        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setMargin(new Insets(2, 4, 2, 4));
        return this;
    }
}

// Editor for JButton
class ButtonEditor extends DefaultCellEditor {
    private final JButton button;
    private String billId;
    DefaultTableModel model;

    public ButtonEditor(JCheckBox checkBox, DefaultTableModel model) {
        super(checkBox);
        this.model = model;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(e -> {
            if (billId != null) {
                SwingUtilities.invokeLater(() -> new BillTable("business", Integer.parseInt(billId)));
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        billId = table.getValueAt(row, 0).toString(); // assuming first column is Bill ID
        button.setText("View");
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return "View";
    }
}
