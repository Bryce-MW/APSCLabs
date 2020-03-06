// Lab18av110.java
// This is the 110 point version.
// Bryce Wilson
// May 26, 2019
// APCS, Mr. Robinson

package ca.thenetworknerds.APCS;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DecimalFormat;
import java.util.*;

public class Lab18av110 {
    public static void main(String[] args) throws IOException {
        List studentArray = new List(100);
        studentArray.getList();
        studentArray.display("UNSORTED LIST OF STUDENTS");
        studentArray.pause();

        studentArray.gpaSort();
        studentArray.display("STUDENTS SORTED IN DESCENDING ORDER BY GPA");
        studentArray.pause();

        studentArray.ageSort();
        studentArray.display("STUDENTS SORTED IN ASCENDING ORDER BY AGE");
        studentArray.pause();

        studentArray.idSort();
        studentArray.display("STUDENTS SORTED IN ASCENDING ORDER BY ID#");
        studentArray.pause();

		int studentID = getID();
		int index = studentArray.search(studentID);

		if (index == -1)
		    System.out.println("There is no students with an ID# of "+studentID+".\n");
		else
		{
			studentArray.displayStudent(index);   // displays the information for the found students
			studentArray.delete(index);           // remove the same students from the array
			studentArray.display("STUDENTS SORTED IN ASCENDING ORDER BY ID# WITHOUT STUDENT# "+studentID);
			studentArray.pause();
		}
    }

    public static int getID() {
        Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.print("\nEnter the 6-digit ID of the students.  { 100000 - 999999 }  -->  ");
        return input.nextInt();
    }
}


class Person {
    public String name;

    public int getID() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public double getGpa() {
        return gpa;
    }

    public int id;
    public int age;
    public double gpa;

    Person(String n, int id, int a, double g) {
        name = n;
        this.id = id;
        age = a;
        gpa = g;
    }
}


class List {
    private HashMap<Integer, Person> students;
    private ArrayList<Person> studentsList;

    List(int c) {
        students = new HashMap<>(c);
        studentsList = new ArrayList<>(students.size());
    }

    public void getList() throws IOException {
        FileReader inFile = new FileReader("students.dat", StandardCharsets.UTF_8);
        BufferedReader inStream = new BufferedReader(inFile);
        String s1, s2, s3, s4;
        int age, id;
        double gpa;
        while (((s1 = inStream.readLine()) != null) &&
               ((s2 = inStream.readLine()) != null) &&
               ((s3 = inStream.readLine()) != null) &&
               ((s4 = inStream.readLine()) != null)) {
            id = Integer.parseInt(s2);
            age = Integer.parseInt(s3);
            gpa = Double.parseDouble(s4);

            students.put(id, new Person(s1, id, age, gpa));
        }
        studentsList.addAll(students.values());
        inStream.close();
    }

    private String spaces(String name) {
        int tab = 24 - name.length();
        return " ".repeat(tab + 1);
    }

    public void display(String listInfo) {
        DecimalFormat output = new DecimalFormat("0.000");
        System.out.println("\nDISPLAYING " + listInfo);
        System.out.println("\nStudent ID#     Student Name            Age         GPA");
        System.out.println("============================================================");

        for (Person item :
                studentsList) {
            System.out.println(item.id + "          " + item.name + spaces(item.name) + item.age + "          " + output.format(item.gpa));
        }
    }

    public void pause() {
        Scanner input = new Scanner(System.in, StandardCharsets.UTF_8);
        System.out.println("\nPress <Enter> to continue.");
        input.nextLine();
    }

    public void displayStudent(int index) {
        System.out.println("Student record for students ID: " + index + "\n");
        System.out.println("Name: " + students.get(index).name);
        System.out.println("Age:  " + students.get(index).age);
        System.out.println("GPA:  " + students.get(index).gpa);
    }

    public void gpaSort() {
        studentsList.sort(Comparator.comparingDouble(Person::getGpa).reversed());
    }

    public void ageSort() {
        studentsList.sort(Comparator.comparingDouble(Person::getAge));
    }

    public void idSort() {
        studentsList.sort(Comparator.comparingDouble(Person::getID));
    }

    public int search(int studentID) {
        return studentID;
    }

    public void delete(int index) {
        students.remove(index);
        int start = 0;
        int end = studentsList.size() - 1;
        int actualIndex = 0;
        for (int i = 0; i < 31 - Integer.numberOfLeadingZeros(index); i++) {
            int middle = (start + end) / 2;
            if (studentsList.get(middle).id == index) {
                actualIndex = middle;
                break;
            }
            if (studentsList.get(middle).id < index) {
                start = middle;
            } else {
                end = middle;
            }
        }
        studentsList.remove(actualIndex);
    }
}

