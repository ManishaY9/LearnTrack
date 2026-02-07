package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {
    private List<Student> students = new ArrayList<>();

    public void save(Student student) {
        students.add(student);
    }


    public Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public List<Student> findAll() {
        return students;
    }

    public List<Student> findAllActive() {
        List<Student> activeStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.isActive()) {
                activeStudents.add(student);
            }
        }
        return activeStudents;
    }

}