package com.airtribe.learntrack.service;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.List;

public class StudentService {
    private StudentRepository repo = new StudentRepository();

    public void addStudent(String fn, String ln, String batch) {
        int id = IdGenerator.nextStudentId();
        repo.save(new Student(id, fn, ln, batch));
    }

    public Student getStudent(int id) {
        Student s = repo.findById(id);
        if (s == null) throw new EntityNotFoundException("Student not found");
        return s;
    }

    public void deactivateStudent(int id) {
        Student student = repo.findById(id);

        if (student == null) {
            throw new EntityNotFoundException("Student not found with ID: " + id);
        }

        student.setActive(false);
    }
    public boolean updateStudent(int id, String firstName, String lastName, String batch) {

        Student student = repo.findById(id);

        if (student == null) {
            throw new EntityNotFoundException("Student not found with ID: " + id);
        }

        boolean updated = false;

        if (firstName != null && !firstName.trim().isEmpty()) {
            student.setFirstName(firstName);
            updated = true;
        }

        if (lastName != null && !lastName.trim().isEmpty()) {
            student.setLastName(lastName);
            updated = true;
        }

        if (batch != null && !batch.trim().isEmpty()) {
            student.setBatch(batch);
            updated = true;
        }

        return updated; // 👈 tells Main whether anything changed
    }

    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    // Get active students only
    public List<Student> getActiveStudents() {
        return repo.findAllActive();
    }
}