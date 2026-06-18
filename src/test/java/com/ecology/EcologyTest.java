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
            "Аліса",
            "Мельник",
            "Іван",
            "Петренко",
            "Вступ до програмування",
            "Комп`ютерні науки",
            "210",
            "Осінь",
            "2024",
            "09:00:00",
            "10:30:00"
        );

        Field idField = Ecology.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(ecology, "id-123");

        String value = ecology.toString();

        assertTrue(value.contains("id=\"id-123\""));
        assertTrue(value.contains("studentFirstName=\"Аліса\""));
        assertTrue(value.contains("teacherLastName=\"Петренко\""));
        assertTrue(value.contains("courseName=\"Вступ до програмування\""));
        assertTrue(value.contains("roomNumber=\"210\""));
        assertTrue(value.contains("endTime=\"10:30:00\""));
    }

    @Test
    void constructorStoresGivenValuesForSecondCsvRow() {
        Ecology ecology = new Ecology(
            "Богдан",
            "Іванов",
            "Оксана",
            "Коваль",
            "Математичний аналіз I",
            "Математика",
            "212",
            "Осінь",
            "2024",
            "11:00:00",
            "12:30:00"
        );

        String value = ecology.toString();
        assertTrue(value.contains("studentFirstName=\"Богдан\""));
        assertTrue(value.contains("courseName=\"Математичний аналіз I\""));
    }


    @Test
    void settersAndGettersWork() {
        Ecology ecology = new Ecology();
        ecology.setStudentFirstName("Олексій");
        ecology.setStudentLastName("Коваленко");
        ecology.setTeacherFirstName("Марія");
        ecology.setTeacherLastName("Шевченко");
        ecology.setCourseName("Біологія");
        ecology.setDepartmentName("Екологія");
        ecology.setRoomNumber("101");
        ecology.setSemester("Весна");
        ecology.setYear("2025");
        ecology.setStartTime("08:00:00");
        ecology.setEndTime("09:30:00");

        assertEquals("Олексій", ecology.getStudentFirstName());
        assertEquals("Коваленко", ecology.getStudentLastName());
        assertEquals("Марія", ecology.getTeacherFirstName());
        assertEquals("Шевченко", ecology.getTeacherLastName());
        assertEquals("Біологія", ecology.getCourseName());
        assertEquals("Екологія", ecology.getDepartmentName());
        assertEquals("101", ecology.getRoomNumber());
        assertEquals("Весна", ecology.getSemester());
        assertEquals("2025", ecology.getYear());
        assertEquals("08:00:00", ecology.getStartTime());
        assertEquals("09:30:00", ecology.getEndTime());
    }
    @Test
    void noArgConstructorCreatesEmptyEcology() {
        Ecology ecology = new Ecology();

        assertNull(ecology.getId());
        assertNull(ecology.getStudentFirstName());
        assertNull(ecology.getStudentLastName());
        assertNull(ecology.getTeacherFirstName());
        assertNull(ecology.getTeacherLastName());
        assertNull(ecology.getCourseName());
        assertNull(ecology.getDepartmentName());
        assertNull(ecology.getRoomNumber());
        assertNull(ecology.getSemester());
        assertNull(ecology.getYear());
        assertNull(ecology.getStartTime());
        assertNull(ecology.getEndTime());
    }
}
