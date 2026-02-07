package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.service.StudentService;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        StudentService studentService = new StudentService();
        boolean running = true;

        while (running) {

            printMenu();
            System.out.print(AppConstants.ENTER_CHOICE);

            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println(AppConstants.INVALID_OPTION_MSG);
                scanner.nextLine(); // clear buffer
                continue;
            }

            try {
                switch (choice) {

                    // ===== ADD STUDENT =====
                    case MenuOptions.ADD_STUDENT: {
                        System.out.print(AppConstants.ENTER_FIRST_NAME);
                        String fn = scanner.next();

                        System.out.print(AppConstants.ENTER_LAST_NAME);
                        String ln = scanner.next();

                        System.out.print(AppConstants.ENTER_BATCH);
                        String batch = scanner.next();

                        studentService.addStudent(fn, ln, batch);
                        System.out.println(AppConstants.STUDENT_ADDED_SUCCESS);
                        break;
                    }

                    // ===== UPDATE STUDENT =====
                    case MenuOptions.UPDATE_STUDENT: {
                        System.out.print(AppConstants.ENTER_STUDENT_ID);
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print(AppConstants.ENTER_FIRST_NAME);
                        String fn = scanner.nextLine();

                        System.out.print(AppConstants.ENTER_LAST_NAME);
                        String ln = scanner.nextLine();

                        System.out.print(AppConstants.ENTER_BATCH);
                        String batch = scanner.nextLine();

                        boolean updated = studentService.updateStudent(id, fn, ln, batch);

                        if (updated) {
                            System.out.println(AppConstants.STUDENT_UPDATED_SUCCESS);
                        } else {
                            System.out.println(AppConstants.STUDENT_NOT_UPDATED);
                        }
                        break;
                    }

                    // ===== DEACTIVATE STUDENT =====
                    case MenuOptions.DEACTIVATE_STUDENT: {
                        System.out.print(AppConstants.ENTER_STUDENT_ID);
                        int id = scanner.nextInt();

                        studentService.deactivateStudent(id);
                        System.out.println(AppConstants.STUDENT_DEACTIVATED_SUCCESS);
                        break;
                    }

                    // ===== VIEW ACTIVE STUDENTS =====
                    case MenuOptions.VIEW_ACTIVE_STUDENTS: {
                        List<Student> students = studentService.getActiveStudents();

                        if (students.isEmpty()) {
                            System.out.println(AppConstants.NO_RECORDS_FOUND);
                        } else {
                            for (Student s : students) {
                                System.out.println(
                                        "ID: " + s.getId() +
                                                ", Name: " + s.getFirstName() + " " + s.getLastName() +
                                                ", Batch: " + s.getBatch() +
                                                ", Status: ACTIVE"
                                );
                            }
                        }
                        break;
                    }

                    // ===== VIEW ALL STUDENTS =====
                    case MenuOptions.VIEW_ALL_STUDENTS: {
                        List<Student> students = studentService.getAllStudents();

                        if (students.isEmpty()) {
                            System.out.println(AppConstants.NO_RECORDS_FOUND);
                        } else {
                            for (Student s : students) {
                                String status = s.isActive() ? "Active" : "In Active";
                                System.out.println(
                                        "ID: " + s.getId() +
                                                ", Name: " + s.getFirstName() + " " + s.getLastName() +
                                                ", Batch: " + s.getBatch() +
                                                ", Status: " + status
                                );
                            }
                        }
                        break;
                    }

                    // ===== EXIT =====
                    case MenuOptions.EXIT:
                        System.out.println(AppConstants.EXIT_MSG);
                        running = false;
                        break;

                    default:
                        System.out.println(AppConstants.INVALID_OPTION_MSG);
                }

            } catch (EntityNotFoundException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println(AppConstants.INVALID_OPTION_MSG);
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== LearnTrack Menu =====");
        System.out.println(MenuOptions.ADD_STUDENT + ". Add Student");
        System.out.println(MenuOptions.UPDATE_STUDENT + ". Update Student");
        System.out.println(MenuOptions.DEACTIVATE_STUDENT + ". Deactivate Student");
        System.out.println(MenuOptions.VIEW_ACTIVE_STUDENTS + ". View Active Students");
        System.out.println(MenuOptions.VIEW_ALL_STUDENTS + ". View All Students");
        System.out.println(MenuOptions.EXIT + ". Exit");
    }
}