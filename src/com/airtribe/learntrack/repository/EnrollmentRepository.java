package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository {

    private List<Enrollment> enrollments = new ArrayList<>();

    // Save enrollment
    public void save(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    // 🔧 FIXED: Find enrollment by ID
    public Enrollment findById(int id) {
        for (Enrollment e : enrollments) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    // Find enrollments by student ID
    public List<Enrollment> findByStudentId(int studentId) {
        List<Enrollment> result = new ArrayList<>();

        for (Enrollment e : enrollments) {
            if (e.getStudentId() == studentId) {
                result.add(e);
            }
        }
        return result;
    }

    // Optional: return all enrollments
    public List<Enrollment> findAll() {
        return enrollments;
    }
}