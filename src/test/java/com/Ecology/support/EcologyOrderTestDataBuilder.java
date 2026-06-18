package com.retail.support;

import com.retail.RetailOrder;

public final class RetailOrderTestDataBuilder {
    private String storeName = "Магазин \"Сонях\"";
    private String supplier = "ТОВ \"АгроПоставка\"";
    private String logisticsCompany = "НоваПошта Логістика";
    private String orderDate = "2024-09-14";
    private String productBatch = "цукор:20; масло:10";
    private String mainProductCategory = "Продукти харчування";
    private String wholesaleDiscount = "7";
    private String warehouseAddress = "м. Київ, вул. Складська, 1";
    private String warehousePhone = "380441110101";
    private String deliveryConditions = "Палети 120×80; терміново";

    public static RetailOrderTestDataBuilder anOrder() {
        return new RetailOrderTestDataBuilder();
    }

    public RetailOrderTestDataBuilder withStoreName(String value) {
        this.storeName = value;
        return this;
    }

    public RetailOrderTestDataBuilder withMainProductCategory(String value) {
        this.mainProductCategory = value;
        return this;
    }

    public RetailOrder build() {
        return new RetailOrder(
            storeName,
            supplier,
            logisticsCompany,
            orderDate,
            productBatch,
            mainProductCategory,
            wholesaleDiscount,
            warehouseAddress,
            warehousePhone,
            deliveryConditions
        );
    }
}
