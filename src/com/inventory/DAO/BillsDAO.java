package com.inventory.DAO;

import com.inventory.DTO.BillItemDTO;
import com.inventory.DTO.BillsDTO;
import com.inventory.Database.ConnectionFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Locale;
import java.util.Vector;

public class BillsDAO {
    Connection conn = null;
    PreparedStatement prepStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    public BillsDAO() {
        try {
            conn = new ConnectionFactory().getConn();
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Add Bill
    public String createNewBill(String customerId, int totalPrice) {
        String newBillId = "";
        try {
            String query = "INSERT INTO bills(cid, total_amount) VALUES(?,?)";
            prepStatement = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prepStatement.setString(1, customerId);
            prepStatement.setInt(2, totalPrice);
            prepStatement.executeUpdate();
            ResultSet rs = prepStatement.getGeneratedKeys();
            if (rs.next()) {
                newBillId = String.valueOf(rs.getInt(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding bill: " + e.getMessage());
            e.printStackTrace();
        }
        return newBillId;
    }

    //Add Bill Items in bill_items
    public void addBillItems(String billId, BillsDTO itemDetailsDTO) {
        try {
            String query = "INSERT INTO bill_items(bill_id, pid, quantity, price) VALUES(?,?,?,?)";
            prepStatement = conn.prepareStatement(query);
            prepStatement.setInt(1, Integer.parseInt(billId));
            prepStatement.setInt(2, Integer.parseInt(itemDetailsDTO.getProductId()));
            prepStatement.setInt(3, itemDetailsDTO.getQuantity());
            prepStatement.setInt(4, itemDetailsDTO.getUnitPrice());
            System.out.println("Query: " + prepStatement.toString());
            prepStatement.executeUpdate();
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error adding bill items: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Delete Bill
    public void deleteBillDAO(String billCode) {
        try {
            String query = "DELETE FROM bills WHERE billcode='" + billCode + "'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Bill removed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get all bills
    public ResultSet getQueryResult() {
        try {
            String query = "SELECT bill_id as `Bill ID`, fullname as `Customer Name`, bill_date as `Date`, total_amount as `Amount` FROM bills natural join customers";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    //Get bill items
    public ResultSet getBillItemsByBillId(int billId) {
        try {
            String query = """
                           SELECT b.cid, b.total_amount, bi.pid, p.productname, quantity, bi.price, c.fullname, bill_date
                           FROM bills b 
                           NATURAL JOIN bill_items bi
                           NATURAL JOIN products p
                           NATURAL JOIN customers c
                           where b.bill_id=?
                           """;
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, billId);
            resultSet = preparedStatement.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    // Search bills
    public ResultSet getBillSearch(String text) {
        try {
            String query ="SELECT bill_id as `Bill ID`, fullname as `Customer Name`, bill_date as `Date`, total_amount as `Amount` "+
                            "FROM bills "+
                            "natural join customers "+
                            "where bill_id like '%"+text+"%' OR fullname like '%" + text + "%' or total_amount like '%" + text + "%' or bill_date like '%" + text + "%'";
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        Vector<String> columnNames = new Vector<>();
        int colCount = metaData.getColumnCount();

        for (int col = 1; col <= colCount; col++) {
            columnNames.add(metaData.getColumnLabel(col).toUpperCase(Locale.ROOT));
        }
        columnNames.add("ACTION"); // extra column for View button

        Vector<Vector<Object>> data = new Vector<>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int col = 1; col <= colCount; col++) {
                vector.add(rs.getObject(col));
            }
            vector.add("View"); // placeholder for button
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }
}
