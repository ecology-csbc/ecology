package com.college.support;

import com.college.Schedule;

public final class ScheduleTestDataBuilder {
    private String studentFirstName = "Аліса";
    private String studentLastName = "Мельник";
    private String teacherFirstName = "Іван";
    private String teacherLastName = "Петренко";
    private String courseName = "Вступ до програмування";
    private String departmentName = "Комп`ютерні науки";
    private String roomNumber = "210";
    private String semester = "Осінь";
    private String year = "2024";
    private String startTime = "09:00:00";
    private String endTime = "10:30:00";

    public static ScheduleTestDataBuilder aSchedule() {
        return new ScheduleTestDataBuilder();
    }

    public ScheduleTestDataBuilder withStudentFirstName(String value) {
        this.studentFirstName = value;
        return this;
    }

    public ScheduleTestDataBuilder withCourseName(String value) {
        this.courseName = value;
        return this;
    }

    public Schedule build() {
        return new Schedule(
            studentFirstName,
            studentLastName,
            teacherFirstName,
            teacherLastName,
            courseName,
            departmentName,
            roomNumber,
            semester,
            year,
            startTime,
            endTime
        );
    }
}
