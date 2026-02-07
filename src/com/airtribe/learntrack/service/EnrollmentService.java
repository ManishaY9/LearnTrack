package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;

public class EnrollmentService {

    private EnrollmentRepository enrollmentRepo = new EnrollmentRepository();
    private StudentRepository studentRepo = new StudentRepository();
    private CourseRepository courseRepo = new CourseRepository();

    // 1️⃣ Enroll a student in a course
    public void enrollStudent(int studentId, int courseId) {

        // Validate student
        if (studentRepo.findById(studentId) == null) {
            throw new EntityNotFoundException("Student not found with ID: " + studentId);
        }

        // Validate course
        if (courseRepo.findById(courseId) == null) {
            throw new EntityNotFoundException("Course not found with ID: " + courseId);
        }

        int enrollmentId = IdGenerator.nextEnrollmentId();
        Enrollment enrollment = new Enrollment(enrollmentId, studentId, courseId);

        enrollmentRepo.save(enrollment);
    }

    // 2️⃣ View enrollments for a student
    public List<Enrollment> getEnrollmentsByStudent(int studentId) {

        if (studentRepo.findById(studentId) == null) {
            throw new EntityNotFoundException("Student not found with ID: " + studentId);
        }

        return enrollmentRepo.findByStudentId(studentId);
    }

    // 3️⃣ Update enrollment status
    public void updateEnrollmentStatus(int enrollmentId, EnrollmentStatus status) {

        Enrollment enrollment = enrollmentRepo.findById(enrollmentId);

        if (enrollment == null) {
            throw new EntityNotFoundException("Enrollment not found with ID: " + enrollmentId);
        }

        enrollment.setStatus(status);
    }
}