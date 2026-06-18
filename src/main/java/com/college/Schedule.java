package com.college;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/* 
  Колекція, в якій зберігається документ баз даних MongoDB, що представляє сутність рядку з таблиці з розкладом коледжу.
*/
@Document(collection = "college-schedule")
public class Schedule {
    @Id
    private String id;
    private String studentFirstName;
    private String studentLastName;
    private String teacherFirstName;
    private String teacherLastName;
    private String courseName;
    private String departmentName;
    private String roomNumber;
    private String semester;
    private String year;
    private String startTime;
    private String endTime;

    public Schedule(
        String studentFirstName,
        String studentLastName,
        String teacherFirstName,
        String teacherLastName,
        String courseName,
        String departmentName,
        String roomNumber,
        String semester,
        String year,
        String startTime,
        String endTime
    ) {
        this.studentFirstName = studentFirstName;
        this.studentLastName = studentLastName;
        this.teacherFirstName = teacherFirstName;
        this.teacherLastName = teacherLastName;
        this.courseName = courseName;
        this.departmentName = departmentName;
        this.roomNumber = roomNumber;
        this.semester = semester;
        this.year = year;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Schedule() {
    }

    public String getId() {
        return id;
    }

    public String getStudentFirstName() {
        return studentFirstName;
    }

    public void setStudentFirstName(String studentFirstName) {
        this.studentFirstName = studentFirstName;
    }

    public String getStudentLastName() {
        return studentLastName;
    }

    public void setStudentLastName(String studentLastName) {
        this.studentLastName = studentLastName;
    }

    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String toString() {
        return "Schedule {" +
                " id=\"" + id + "\"\n" +
                " studentFirstName=\"" + studentFirstName + "\"\n" +
                " studentLastName=\"" + studentLastName + "\"\n" +
                " teacherFirstName=\"" + teacherFirstName + "\"\n" +
                " teacherLastName=\"" + teacherLastName + "\"\n" +
                " courseName=\"" + courseName + "\"\n" +
                " departmentName=\"" + departmentName + "\"\n" +
                " roomNumber=\"" + roomNumber + "\"\n" +
                " semester=\"" + semester + "\"\n" +
                " year=\"" + year + "\"\n" +
                " startTime=\"" + startTime + "\"\n" +
                " endTime=\"" + endTime + "\"\n" +
                "}";
    }
}