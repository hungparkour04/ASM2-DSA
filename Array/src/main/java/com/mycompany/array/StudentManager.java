/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.array;

import java.util.Scanner;

public class StudentManager {
    private static Student[] students = new Student[0];
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Student Manager ---");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display Students");
            System.out.println("5. Search Student by ID");
            System.out.println("6. Search Student by Score");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            try {
                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        updateStudent();
                        break;
                    case 3:
                        deleteStudent();
                        break;
                    case 4:
                        displayStudents();
                        break;
                    case 5:
                        searchStudentById();
                        break;
                    case 6:
                        searchStudentByScore();  
                        break;
                    case 9:
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private static void addStudent() {
        int id = 0;
        while (true) {
            try {
                System.out.print("Enter Student ID: ");
                id = Integer.parseInt(scanner.nextLine());
                if (id <= 0) {
                    throw new IllegalArgumentException("ID must be a positive integer.");
                }
                // Kiểm tra trùng ID
                boolean idExists = false;
                for (Student student : students) {
                    if (student.id == id) {
                        idExists = true;
                        break;
                    }
                }
                if (idExists) {
                    System.out.println("ID already exists. Please enter a different ID.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for Student ID.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred while entering the Student ID: " + e.getMessage());
            }
        }

        String name = "";
        while (true) {
            try {
                System.out.print("Enter Student Name: ");
                name = scanner.nextLine();
                if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
                    throw new IllegalArgumentException("Name must only contain letters and cannot be empty.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred while entering the Student Name: " + e.getMessage());
            }
        }

        double score = 0.0;
        while (true) {
            try {
                System.out.print("Enter Student Score: ");
                score = Double.parseDouble(scanner.nextLine());
                if (score < 0 || score > 10) {
                    throw new IllegalArgumentException("Score must be between 0 and 10.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for Student Score.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An error occurred while entering the Student Score: " + e.getMessage());
            }
        }

        // Tạo sinh viên mới và thêm vào mảng
        Student newStudent = new Student(id, name, score);
        Student[] newStudentsArray = new Student[students.length + 1];
        for (int i = 0; i < students.length; i++) {
            newStudentsArray[i] = students[i];
        }
        newStudentsArray[students.length] = newStudent;
        students = newStudentsArray;

        // Hiển thị thông báo
        System.out.println("Student added successfully with ID: " + id);
    }

    private static void updateStudent() {
        int id = 0;
        while (true) {
            try {
                System.out.print("Enter Student ID to update: ");
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for Student ID.");
            } catch (Exception e) {
                System.out.println("An error occurred while entering the Student ID: " + e.getMessage());
            }
        }

        for (int i = 0; i < students.length; i++) {
            if (students[i].id == id) {
                String name = "";
                while (true) {
                    try {
                        System.out.print("Enter new name: ");
                        name = scanner.nextLine();
                        if (name.isEmpty() || !name.matches("[a-zA-Z ]+")) {
                            throw new IllegalArgumentException("Name must only contain letters and cannot be empty.");
                        }
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid input. " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error occurred while entering the new name: " + e.getMessage());
                    }
                }

                double score = 0.0;
                while (true) {
                    try {
                        System.out.print("Enter new score: ");
                        score = Double.parseDouble(scanner.nextLine());
                        if (score < 0 || score > 10) {
                            throw new IllegalArgumentException("Score must be between 0 and 10.");
                        }
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number for Student Score.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid input. " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("An error occurred while entering the new score: " + e.getMessage());
                    }
                }

                students[i].name = name;
                students[i].score = score;
                students[i].rank = students[i].getRank(); // Update rank

                System.out.println("Student updated successfully.");
                return;
            }
        }

        System.out.println("Student ID not found.");
    }

    private static void deleteStudent() {
        try {
            System.out.print("Enter Student ID to delete: ");
            int id = Integer.parseInt(scanner.nextLine());

            int index = -1;
            for (int i = 0; i < students.length; i++) {
                if (students[i].id == id) {
                    index = i;
                    break;
                }
            }

            if (index != -1) {
                Student[] newStudentsArray = new Student[students.length - 1];
                for (int i = 0, j = 0; i < students.length; i++) {
                    if (i != index) {
                        newStudentsArray[j++] = students[i];
                    }
                }
                students = newStudentsArray;

                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("Student ID not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid Student ID.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static void displayStudents() {
        if (students.length == 0) {
            System.out.println("No students to display.");
            return;
        }

        System.out.println("\nStudent List:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void searchStudentById() {
        int id = 0;
        while (true) {
            try {
                System.out.print("Enter Student ID to search: ");
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for Student ID.");
            } catch (Exception e) {
                System.out.println("An error occurred while entering the Student ID: " + e.getMessage());
            }
        }

        for (Student student : students) {
            if (student.id == id) {
                System.out.println("\nSearch Result:");
                System.out.println(student);
                return;
            }
        }

        System.out.println("No student found with ID: " + id);
    }

    private static void searchStudentByScore() {
        double score = 0.0;

        while (true) {
            try {
                System.out.print("Enter score to search for: ");
                score = Double.parseDouble(scanner.nextLine());

                if (score < 0 || score > 10) {
                    throw new IllegalArgumentException("Score must be between 0 and 10.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for the score.");
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input. " + e.getMessage());
            }
        }

        boolean found = false;
        for (Student student : students) {
            if (student.score == score) {
                System.out.println("\nSearch Result:");
                System.out.println(student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students found with the score: " + score);
        }
    }
}

