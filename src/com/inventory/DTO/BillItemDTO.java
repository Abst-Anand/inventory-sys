package com.inventory.DTO;

public class BillItemDTO {
    private int productId;
    private static int billId;
    private String productName;
    private int quantity;
    private int price;
    private int total;
    private static String customerName;
    private int netTotal = 0;
    private static String billDate;

    public BillItemDTO(){}

    public BillItemDTO(int _billId, String _productName, int _quantity, int _price, int _netTotal, String _customerName, String _billDate) {
        billId = _billId;
        productName = _productName;
        quantity = _quantity;
        price = _price;
        total = quantity * price;
        netTotal = _netTotal;
        customerName = _customerName;
        billDate = _billDate;
    }

    public BillItemDTO(int _productId, int _price, int _customerId, int _quantity,String _billDate) {
        productId = _productId;
        price = _price;
        quantity = _quantity;
        billDate = _billDate;
               

    }

    // Getters and Setters


    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public static int getBillId() {
        return billId;
    }

    public static void setBillId(int billId) {
        BillItemDTO.billId = billId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static String getCustomerName() {
        return customerName;
    }

    public static void setCustomerName(String customerName) {
        BillItemDTO.customerName = customerName;
    }

    public int getNetTotal() {
        return netTotal;
    }

    public void setNetTotal(int netTotal) {
        this.netTotal = netTotal;
    }

    public static String getBillDate() {
        return billDate;
    }

    public static void setBillDate(String billDate) {
        BillItemDTO.billDate = billDate;
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
