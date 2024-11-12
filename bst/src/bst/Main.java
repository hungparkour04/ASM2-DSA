/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bst;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MyTree tree = new MyTree();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Add student");
            System.out.println("2. Edit student");
            System.out.println("3. Delete student");
            System.out.println("4. Search for student");
            System.out.println("5. Sort and display students");
            System.out.println("6. Sort by ID using Bubble Sort");
            System.out.println("7. Sort by Score using Quick Sort");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> { // Add a student
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Score: ");
                    double score = scanner.nextDouble();
                    scanner.nextLine();
                    Student student = new Student(id, name, score);
                    tree.insert(student);
                    System.out.println("Student added successfully.");
                }
                case 2 -> { // Edit a student
                    System.out.print("Enter student ID to edit: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("New name: ");
                    String newName = scanner.nextLine();
                    System.out.print("New score: ");
                    double newScore = scanner.nextDouble();
                    if (tree.editStudent(id, newName, newScore)) {
                        System.out.println("Student information updated successfully.");
                    } else {
                        System.out.println("Student not found.");
                    }
                }
                case 3 -> { // Delete a student
                    System.out.print("Enter student ID to delete: ");
                    int id = scanner.nextInt();
                    tree.deleteStudent(id);
                    System.out.println("Student deleted successfully.");
                }
                case 4 -> { // Search for a student
                    System.out.print("Enter student ID to search: ");
                    int id = scanner.nextInt();
                    Student student = tree.findStudent(id);
                    System.out.println(student != null ? student : "Student not found.");
                }
                case 5 -> { // Sort and display students
                    System.out.println("List of students:");
                    tree.inorderTraversal();
                }
                case 6 -> { // Sort by ID using Bubble Sort
                    tree.bubbleSortById();
                    System.out.println("Students sorted by ID:");
                    tree.inorderTraversal();
                }
                case 7 -> { // Sort by Score using Quick Sort
                    tree.quickSortByScore();
                    System.out.println("Students sorted by Score:");
                    tree.inorderTraversal();
                }
                case 0 -> { // Exit
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}
