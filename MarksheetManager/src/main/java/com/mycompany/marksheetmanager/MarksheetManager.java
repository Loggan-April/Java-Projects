/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.marksheetmanager;

/**
 *
 * @author Loggan April
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

// Class to represent a student
class Student {
    String name;
    ArrayList<Integer> marks;
    int totalMarks;
    double average;
    char grade;

    // constructor to initialize student name and marks
    public Student(String name, ArrayList<Integer> marks) {
        this.name = name;
        this.marks = marks;
        calculateTotalAndAverage();
        calculateGrade();
    }

    // Method to calculate marks and average
    private void calculateTotalAndAverage() {
        this.totalMarks = 0;
        for (int mark : marks) {
            this.totalMarks += mark;
        }
        this.average = Math.round((totalMarks / (double) marks.size()) * 100.0) / 100.0;
    }

    // Method to calculate grade based on average
    private void calculateGrade() {
        if (average >= 90) {
            grade = 'A';
        } else if (average >= 80) {
            grade = 'B';
        } else if (average >= 70) {
            grade = 'C';
        } else if (average >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
    }

    // Method to update marks and recalculate total, average, and grade
    public void updateMarks(ArrayList<Integer> newMarks) {
        this.marks = newMarks;
        calculateTotalAndAverage();
        calculateGrade();
    }
}

// Class to manage the marksheet
public class MarksheetManager {
    private ArrayList<Student> students = new ArrayList<>();

    // Method to add a student to the list
    public void addStudent(String name, ArrayList<Integer> marks) {
        Student student = new Student(name, marks);
        students.add(student);
    }

    // Method to display the marksheet of all students
    public void displayMarksheet() {
        for (Student student : students) {
            System.out.println("-----------------------");
            System.out.println("Name: " + student.name);
            System.out.println("Marks: " + student.marks);
            System.out.println("Total Marks: " + student.totalMarks);
            System.out.println("Average: " + student.average);
            System.out.println("Grade: " + student.grade);
            System.out.println("-----------------------");
            System.out.println("");
        }
    }

    // Method to search for a student by name (name is case sensitive)
    public void searchStudent(String name) {
        for (Student student : students) {
            if (student.name.equalsIgnoreCase(name)) {
                System.out.println("Name: " + student.name);
                System.out.println("Marks: " + student.marks);
                System.out.println("Total Marks: " + student.totalMarks);
                System.out.println("Average: " + student.average);
                System.out.println("Grade: " + student.grade);
                System.out.println("");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Method to sort students by total marks in descending order
    public void sortStudentsByTotalMarks() {
        Collections.sort(students, (s1, s2) -> s2.totalMarks - s1.totalMarks);
    }

    // Method to update marks of a student by name (name is case sensitive)
    public void updateMarks(String name, ArrayList<Integer> newMarks) {
        for (Student student : students) {
            if (student.name.equalsIgnoreCase(name)) {
                student.updateMarks(newMarks);
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // Method to delete a student by name
    public void deleteStudent(String name) {
        students.removeIf(student -> student.name.equalsIgnoreCase(name));
    }

    //Method to run the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MarksheetManager manager = new MarksheetManager();
        
        while (true) {
            System.out.println("\n--- Student Marksheet Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Display Marksheet");
            System.out.println("3. Search Student");
            System.out.println("4. Sort Students");
            System.out.println("5. Update Marks");
            System.out.println("6. Delete Student");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    // Add a new Student
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter number of subjects: ");
                    int numSubjects = scanner.nextInt();
                    ArrayList<Integer> marks = new ArrayList<>();
                    for (int i = 0; i < numSubjects; i++) {
                        System.out.print("Enter mark for subject " + (i + 1) + ": ");
                        marks.add(scanner.nextInt());
                    }
                    manager.addStudent(name, marks);
                    break;
                case 2:
                    // Display the marksheet
                    manager.displayMarksheet();
                    break;
                case 3:
                    // Search for a student by name
                    System.out.print("Enter student name to search: ");
                    name = scanner.nextLine();
                    manager.searchStudent(name);
                    break;
                case 4:
                    // Sort the student by marks in descending order
                    manager.sortStudentsByTotalMarks();
                    System.out.println("Students sorted by total marks in descending order.");
                    break;
                case 5:
                    // Update the marks of a student
                    System.out.print("Enter student name to update marks: ");
                    name = scanner.nextLine();
                    System.out.print("Enter number of subjects: ");
                    numSubjects = scanner.nextInt();
                    marks = new ArrayList<>();
                    for (int i = 0; i < numSubjects; i++) {
                        System.out.print("Enter new mark for subject " + (i + 1) + ": ");
                        marks.add(scanner.nextInt());
                    }
                    manager.updateMarks(name, marks);
                    break;
                case 6:
                    // Delete a student
                    System.out.print("Enter student name to delete: ");
                    name = scanner.nextLine();
                    manager.deleteStudent(name);
                    break;
                case 7:
                    // Exit the program
                    System.out.println("Exiting...");
                    return;
                default:
                    //Output if the user enters a number not on the list
                    System.out.println("Invalid choice. Please try again.");
            }scanner.close();
        }
        
    }
}
