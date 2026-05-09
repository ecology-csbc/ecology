package com.college;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;

class ScheduleTest {

    @Test
    void toStringContainsAllImportantFields() throws Exception {
        Schedule schedule = new Schedule(
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

        Field idField = Schedule.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(schedule, "id-123");

        String value = schedule.toString();

        assertTrue(value.contains("id=\"id-123\""));
        assertTrue(value.contains("studentFirstName=\"Аліса\""));
        assertTrue(value.contains("teacherLastName=\"Петренко\""));
        assertTrue(value.contains("courseName=\"Вступ до програмування\""));
        assertTrue(value.contains("roomNumber=\"210\""));
        assertTrue(value.contains("endTime=\"10:30:00\""));
    }

    @Test
    void constructorStoresGivenValuesForSecondCsvRow() {
        Schedule schedule = new Schedule(
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

        String value = schedule.toString();
        assertTrue(value.contains("studentFirstName=\"Богдан\""));
        assertTrue(value.contains("courseName=\"Математичний аналіз I\""));
    }

    @Test
    void noArgConstructorCreatesEmptySchedule() {
        Schedule schedule = new Schedule();

        assertNull(schedule.getId());
        assertNull(schedule.getStudentFirstName());
        assertNull(schedule.getStudentLastName());
        assertNull(schedule.getTeacherFirstName());
        assertNull(schedule.getTeacherLastName());
        assertNull(schedule.getCourseName());
        assertNull(schedule.getDepartmentName());
        assertNull(schedule.getRoomNumber());
        assertNull(schedule.getSemester());
        assertNull(schedule.getYear());
        assertNull(schedule.getStartTime());
        assertNull(schedule.getEndTime());
    }

    @Test
    void settersAndGettersRoundTripAllFields() {
        Schedule schedule = new Schedule();

        schedule.setStudentFirstName("Аліса");
        schedule.setStudentLastName("Мельник");
        schedule.setTeacherFirstName("Іван");
        schedule.setTeacherLastName("Петренко");
        schedule.setCourseName("Програмування");
        schedule.setDepartmentName("Комп`ютерні науки");
        schedule.setRoomNumber("210");
        schedule.setSemester("Осінь");
        schedule.setYear("2024");
        schedule.setStartTime("09:00:00");
        schedule.setEndTime("10:30:00");

        assertEquals("Аліса", schedule.getStudentFirstName());
        assertEquals("Мельник", schedule.getStudentLastName());
        assertEquals("Іван", schedule.getTeacherFirstName());
        assertEquals("Петренко", schedule.getTeacherLastName());
        assertEquals("Програмування", schedule.getCourseName());
        assertEquals("Комп`ютерні науки", schedule.getDepartmentName());
        assertEquals("210", schedule.getRoomNumber());
        assertEquals("Осінь", schedule.getSemester());
        assertEquals("2024", schedule.getYear());
        assertEquals("09:00:00", schedule.getStartTime());
        assertEquals("10:30:00", schedule.getEndTime());
    }

    @Test
    void gettersReturnValuesFromParameterizedConstructor() throws Exception {
        Schedule schedule = new Schedule(
            "Богдан", "Іванов", "Оксана", "Коваль",
            "Математичний аналіз I", "Математика", "212",
            "Осінь", "2024", "11:00:00", "12:30:00"
        );

        Field idField = Schedule.class.getDeclaredField("id");
        idField.setAccessible(true);
        idField.set(schedule, "id-456");

        assertEquals("id-456", schedule.getId());
        assertEquals("Богдан", schedule.getStudentFirstName());
        assertEquals("Іванов", schedule.getStudentLastName());
        assertEquals("Оксана", schedule.getTeacherFirstName());
        assertEquals("Коваль", schedule.getTeacherLastName());
        assertEquals("Математичний аналіз I", schedule.getCourseName());
        assertEquals("Математика", schedule.getDepartmentName());
        assertEquals("212", schedule.getRoomNumber());
        assertEquals("Осінь", schedule.getSemester());
        assertEquals("2024", schedule.getYear());
        assertEquals("11:00:00", schedule.getStartTime());
        assertEquals("12:30:00", schedule.getEndTime());
    }
}
