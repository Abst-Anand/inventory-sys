package com.inventory.DTO;

public class BillItemDTO {
    private int productId;
    private static int billId;
    private String productName;
    private int quantity;
    private double price;
    private double total;
    private static String customerName;
    private double netTotal = 0.0;
    private static String billDate;

    public BillItemDTO(){}

    public BillItemDTO(int _billId, String _productName, int _quantity, double _price, double _netTotal, String _customerName, String _billDate) {
        billId = _billId;
        productName = _productName;
        quantity = _quantity;
        price = _price;
        total = quantity * price;
        netTotal = _netTotal;
        customerName = _customerName;
        billDate = _billDate;
    }

    public BillItemDTO(int _productId, double _price, int _customerId, int _quantity,String _billDate) {
        productId = _productId;
        price = _price;
        quantity = _quantity;
        billDate = _billDate;
               

    }

    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        BillItemDTO.billDate = billDate;
    }

    public int getBillId() { return billId; }
    public void setBillId(int billId) { this.billId = billId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(double netTotal) {
        this.netTotal = netTotal;
    }

    @Override
    public String toString() {
        return "BillItemDTO{" +
                "billId=" + billId +
                ", productName='" + productName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + total +
                ", customerName='" + customerName + '\'' +
                ", netTotal=" + netTotal +
                ", billDate='" + billDate + '\'' +
                '}' + "\n";
    }
}
