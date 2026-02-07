package com.airtribe.learntrack.service;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;

public class CourseService {
    private CourseRepository repo = new CourseRepository();

    public void addCourse(String name, int duration) {
        int id = IdGenerator.nextCourseId();
        repo.save(new Course(id, name, duration));
    }
}