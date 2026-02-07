package com.airtribe.learntrack.util;

public class IdGenerator {
    private static int studentId = 1;
    private static int courseId = 1;
    private static int enrollmentId = 1;

    public static int nextStudentId() {
        return studentId++;
    }

    public static int nextCourseId() {
        return courseId++;
    }

    public static int nextEnrollmentId() {
        return enrollmentId++;
    }
}