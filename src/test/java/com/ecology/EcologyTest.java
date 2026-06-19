package com.ecology;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;

class EcologyTest {

    @Test
    void toStringContainsAllImportantFields() throws Exception {
        Ecology ecology = new Ecology(
            "Зелена Оксана Вікторівна",
            "S-101",
            "2024-09-14",
            18.0,
            32.0,
            7.1,
            "Низький",
            50.4501,
            30.5234,
            "Київська МДА",
            "м. Київ, вул. Хрещатик, 36",
            "380444670001",
            6
        );

        Field idField = Ecology.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(ecology, "id-123");

        String value = ecology.toString();

        assertTrue(value.contains("id=\"id-123\""));
        assertTrue(value.contains("ecologistName=\"Зелена Оксана Вікторівна\""));
        assertTrue(value.contains("sensorId=\"S-101\""));
        assertTrue(value.contains("dangerLevel=\"Низький\""));
        assertTrue(value.contains("authorityPhone=\"380444670001\""));
        assertTrue(value.contains("ecologistExperience=6"));
    }

    @Test
    void constructorStoresGivenValuesForSecondCsvRow() {
        Ecology ecology = new Ecology(
            "Чистий Дмитро Олегович",
            "S-202",
            "2024-09-16",
            38.0,
            48.0,
            null,
            "Середній",
            50.4547,
            30.5166,
            "Шевченківська РДА",
            "м. Київ, вул. Б. Хмельницького, 24",
            "380442001002",
            9
        );

        String value = ecology.toString();
        assertTrue(value.contains("ecologistName=\"Чистий Дмитро Олегович\""));
        assertTrue(value.contains("sensorId=\"S-202\""));
        assertTrue(value.contains("dangerLevel=\"Середній\""));
    }


    @Test
    void settersAndGettersWork() {
        Ecology ecology = new Ecology();
        ecology.setEcologistName("Лісова Лідія Петрівна");
        ecology.setSensorId("S-303");
        ecology.setMeasurementDate("2024-09-18");
        ecology.setParamPm25(70.0);
        ecology.setParamNo2(90.0);
        ecology.setParamPh(6.7);
        ecology.setDangerLevel("Високий");
        ecology.setLatitude(50.4813);
        ecology.setLongitude(30.6022);
        ecology.setAuthorityName("Дарницька РДА");
        ecology.setAuthorityAddress("м. Київ, просп. Бажана, 1А");
        ecology.setAuthorityPhone("380444440003");
        ecology.setEcologistExperience(12);

        assertEquals("Лісова Лідія Петрівна", ecology.getEcologistName());
        assertEquals("S-303", ecology.getSensorId());
        assertEquals("2024-09-18", ecology.getMeasurementDate());
        assertEquals(70.0, ecology.getParamPm25());
        assertEquals(90.0, ecology.getParamNo2());
        assertEquals(6.7, ecology.getParamPh());
        assertEquals("Високий", ecology.getDangerLevel());
        assertEquals(50.4813, ecology.getLatitude());
        assertEquals(30.6022, ecology.getLongitude());
        assertEquals("Дарницька РДА", ecology.getAuthorityName());
        assertEquals("м. Київ, просп. Бажана, 1А", ecology.getAuthorityAddress());
        assertEquals("380444440003", ecology.getAuthorityPhone());
        assertEquals(12, ecology.getEcologistExperience());
    }
    @Test
    void noArgConstructorCreatesEmptyEcology() {
        Ecology ecology = new Ecology();

        assertNull(ecology.getId());
        assertNull(ecology.getEcologistName());
        assertNull(ecology.getSensorId());
        assertNull(ecology.getMeasurementDate());
        assertNull(ecology.getParamPm25());
        assertNull(ecology.getParamNo2());
        assertNull(ecology.getParamPh());
        assertNull(ecology.getDangerLevel());
        assertNull(ecology.getLatitude());
        assertNull(ecology.getLongitude());
        assertNull(ecology.getAuthorityName());
        assertNull(ecology.getAuthorityAddress());
        assertNull(ecology.getAuthorityPhone());
        assertNull(ecology.getEcologistExperience());
    }
}
