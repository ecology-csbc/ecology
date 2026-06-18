package com.retail;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;

class RetailOrderTest {

    @Test
    void toStringContainsAllImportantFields() throws Exception {
        RetailOrder order = new RetailOrder(
            "Магазин \"Сонях\"",
            "ТОВ \"АгроПоставка\"",
            "НоваПошта Логістика",
            "2024-09-14",
            "цукор:20; масло:10",
            "Продукти харчування",
            "7",
            "м. Київ, вул. Складська, 1",
            "380441110101",
            "Палети 120×80; терміново"
        );

        Field idField = RetailOrder.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(order, "id-123");

        String value = order.toString();

        assertTrue(value.contains("id=\"id-123\""));
        assertTrue(value.contains("storeName=\"Магазин \"Сонях\"\""));
        assertTrue(value.contains("supplier=\"ТОВ \"АгроПоставка\"\""));
        assertTrue(value.contains("mainProductCategory=\"Продукти харчування\""));
        assertTrue(value.contains("warehousePhone=\"380441110101\""));
        assertTrue(value.contains("deliveryConditions=\"Палети 120×80; терміново\""));
    }

    @Test
    void constructorStoresGivenValuesForSecondCsvRow() {
        RetailOrder order = new RetailOrder(
            "Магазин \"Економ\"",
            "ТОВ \"ЕлектроТрейд\"",
            "МістЕкспрес",
            "2024-09-17",
            "міксер:4; фен:6",
            "Побутова техніка",
            "5",
            "м. Київ, вул. Логістична, 7",
            "380443330303",
            "Упаковка від постач."
        );

        String value = order.toString();
        assertTrue(value.contains("storeName=\"Магазин \"Економ\"\""));
        assertTrue(value.contains("mainProductCategory=\"Побутова техніка\""));
    }

    @Test
    void gettersAndSettersWork() {
        RetailOrder order = new RetailOrder(
            "Test Store",
            "Test Supplier",
            "Test Logistics",
            "2024-01-01",
            "test:10",
            "Test Category",
            "5",
            "Test Address",
            "1234567890",
            "Test Conditions"
        );

        // Test setters and getters
        order.setId("test-id");
        assertEquals("test-id", order.getId());

        order.setStoreName("New Store");
        assertEquals("New Store", order.getStoreName());

        order.setSupplier("New Supplier");
        assertEquals("New Supplier", order.getSupplier());

        order.setLogisticsCompany("New Logistics");
        assertEquals("New Logistics", order.getLogisticsCompany());

        order.setOrderDate("2024-01-02");
        assertEquals("2024-01-02", order.getOrderDate());

        order.setProductBatch("new:20");
        assertEquals("new:20", order.getProductBatch());

        order.setMainProductCategory("New Category");
        assertEquals("New Category", order.getMainProductCategory());

        order.setWholesaleDiscount("10");
        assertEquals("10", order.getWholesaleDiscount());

        order.setWarehouseAddress("New Address");
        assertEquals("New Address", order.getWarehouseAddress());

        order.setWarehousePhone("9876543210");
        assertEquals("9876543210", order.getWarehousePhone());

        order.setDeliveryConditions("New Conditions");
        assertEquals("New Conditions", order.getDeliveryConditions());
    }
}
