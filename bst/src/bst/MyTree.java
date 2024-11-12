    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
package bst;

import java.util.ArrayList;
import java.util.List;

public class MyTree {
    Node root;

    public MyTree() {
        root = null;
    }

    // Insert a student into the tree
    void insert(Student student) {
        root = insertRecursive(root, student);
    }

    private Node insertRecursive(Node root, Student student) {
        if (root == null) {
            root = new Node(student);
            return root;
        }
        if (student.getId() > root.student.getId()) {
            root.left = insertRecursive(root.left, student);
        } else {
            root.right = insertRecursive(root.right, student);
        }
        return root;
    }

    // In-order traversal of the tree
    public void inorderTraversal(Node root) {
        if (root != null) {
            inorderTraversal(root.right);
            System.out.println(root.student + " ");
            inorderTraversal(root.left);
        }
    }

    public void inorderTraversal() {
        inorderTraversal(root);
    }

    // Edit student information by ID
    public boolean editStudent(int id, String newName, double newScore) {
        Node node = findNode(root, id);
        if (node != null) {
            node.student.name = newName;
            node.student.score = newScore;
            return true;
        }
        return false;
    }

    // Delete student by ID
    public Node deleteStudent(Node root, int id) {
        if (root == null) return root;

        if (id < root.student.getId()) {
            root.left = deleteStudent(root.left, id);
        } else if (id > root.student.getId()) {
            root.right = deleteStudent(root.right, id);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            Node temp = minValueNode(root.right);
            root.student = temp.student;
            root.right = deleteStudent(root.right, temp.student.getId());
        }
        return root;
    }

    public void deleteStudent(int id) {
        root = deleteStudent(root, id);
    }

    // Find a node with a specific ID
    private Node findNode(Node root, int id) {
        if (root == null || root.student.getId() == id) return root;
        if (id < root.student.getId()) return findNode(root.left, id);
        return findNode(root.right, id);
    }

    // Find node with minimum value (for deletion)
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    // Search for a student by ID
    public Student findStudent(int id) {
        Node node = findNode(root, id);
        return (node != null) ? node.student : null;
    }

    // Bubble Sort by ID
    public void bubbleSortById() {
        List<Student> students = new ArrayList<>();
        inorderTraversalToList(root, students);
        // Bubble sort based on ID
        for (int i = 0; i < students.size(); i++) {
            for (int j = 0; j < students.size() - 1 - i; j++) {
                if (students.get(j).getId() > students.get(j + 1).getId()) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        // Rebuild the tree with sorted students
        root = null;
        for (Student student : students) {
            insert(student);
        }
    }

    // Quick Sort by Score
    public void quickSortByScore() {
        List<Student> students = new ArrayList<>();
        inorderTraversalToList(root, students);
        quickSort(students, 0, students.size() - 1);
        // Rebuild the tree with sorted students
        root = null;
        for (Student student : students) {
            insert(student);
        }
    }

    private void quickSort(List<Student> students, int low, int high) {
        if (low < high) {
            int pi = partition(students, low, high);
            quickSort(students, low, pi - 1);
            quickSort(students, pi + 1, high);
        }
    }

    private int partition(List<Student> students, int low, int high) {
        Student pivot = students.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (students.get(j).getScore() < pivot.getScore()) {
                i++;
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }
        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);
        return i + 1;
    }

    private void inorderTraversalToList(Node root, List<Student> students) {
        if (root != null) {
            inorderTraversalToList(root.left, students);
            students.add(root.student);
            inorderTraversalToList(root.right, students);
        }
    }
}
