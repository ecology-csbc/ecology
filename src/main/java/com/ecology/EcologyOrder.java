package com.retail;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
  Колекція, в якій зберігається документ баз даних MongoDB, що представляє сутність рядку з таблиці замовлень Retail.
*/
@Document(collection = "retail-orders")
public class RetailOrder {
    @Id
    private String id;
    private String storeName;
    private String supplier;
    private String logisticsCompany;
    private String orderDate;
    private String productBatch;
    private String mainProductCategory;
    private String wholesaleDiscount;
    private String warehouseAddress;
    private String warehousePhone;
    private String deliveryConditions;

    public RetailOrder(
        String storeName,
        String supplier,
        String logisticsCompany,
        String orderDate,
        String productBatch,
        String mainProductCategory,
        String wholesaleDiscount,
        String warehouseAddress,
        String warehousePhone,
        String deliveryConditions
    ) {
        this.storeName = storeName;
        this.supplier = supplier;
        this.logisticsCompany = logisticsCompany;
        this.orderDate = orderDate;
        this.productBatch = productBatch;
        this.mainProductCategory = mainProductCategory;
        this.wholesaleDiscount = wholesaleDiscount;
        this.warehouseAddress = warehouseAddress;
        this.warehousePhone = warehousePhone;
        this.deliveryConditions = deliveryConditions;
    }

    public String toString() {
        return "RetailOrder {" +
                " id=\"" + id + "\"\n" +
                " storeName=\"" + storeName + "\"\n" +
                " supplier=\"" + supplier + "\"\n" +
                " logisticsCompany=\"" + logisticsCompany + "\"\n" +
                " orderDate=\"" + orderDate + "\"\n" +
                " productBatch=\"" + productBatch + "\"\n" +
                " mainProductCategory=\"" + mainProductCategory + "\"\n" +
                " wholesaleDiscount=\"" + wholesaleDiscount + "\"\n" +
                " warehouseAddress=\"" + warehouseAddress + "\"\n" +
                " warehousePhone=\"" + warehousePhone + "\"\n" +
                " deliveryConditions=\"" + deliveryConditions + "\"\n" +
                "}";
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getProductBatch() {
        return productBatch;
    }

    public void setProductBatch(String productBatch) {
        this.productBatch = productBatch;
    }

    public String getMainProductCategory() {
        return mainProductCategory;
    }

    public void setMainProductCategory(String mainProductCategory) {
        this.mainProductCategory = mainProductCategory;
    }

    public String getWholesaleDiscount() {
        return wholesaleDiscount;
    }

    public void setWholesaleDiscount(String wholesaleDiscount) {
        this.wholesaleDiscount = wholesaleDiscount;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

    public String getWarehousePhone() {
        return warehousePhone;
    }

    public void setWarehousePhone(String warehousePhone) {
        this.warehousePhone = warehousePhone;
    }

    public String getDeliveryConditions() {
        return deliveryConditions;
    }

    public void setDeliveryConditions(String deliveryConditions) {
        this.deliveryConditions = deliveryConditions;
    }
}
