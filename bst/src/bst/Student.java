/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */

package bst;

public class Student {
    int id;
    String name;
    double score;

    public Student(int id, String name, double score) {
        this.id = id;
        this.name = name;
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public double getScore() {
        return score;
    }

    // Determines rank based on score
    public String getRank() {
        if (score >= 9.0 && score <= 10.0) return "Excellent";
        else if (score >= 7.5 && score < 9.0) return "Very Good";
        else if (score >= 6.5 && score < 7.5) return "Good";
        else if (score >= 5.0 && score < 6.5) return "Medium";
        else return "Fail";
    }

    @Override
    public String toString() {
        return "Student{" + "id= " + id + ", name='" + name + '\'' +
                ", score= " + score + ", rank= " + getRank() + '}';
    }
}
