package Operations.Add;

import essentials.DataStructure;
import essentials.DatabaseConnector;
import essentials.View.ShowData;

import java.util.Scanner;

public class AddData {
    public static void addDoctor() {
        // Taking information
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add Doctor");
        System.out.println("=====================================");
        System.out.println("$ Enter Doctor's Name");
        String name = scanner.nextLine();
        System.out.println("$ Choose Doctor's Gender\n1. Male\n2. Female");
        int genderInt = scanner.nextInt();
        String gender = "m";
        while (genderInt != 1 && genderInt != 2) {
            System.out.println("Invalid input");
            genderInt = scanner.nextInt();
        }
        if (genderInt == 1) gender = "m";
        else if (genderInt == 2) gender = "f";
        scanner.nextLine();
        System.out.println("$ Enter Doctor's Phone Number");
        String phoneNumber = scanner.nextLine();
        System.out.println("$ Enter Doctor's Specialization");
        String specialization = scanner.nextLine();
        System.out.println("$ Enter Doctor's Experience");
        int experience = scanner.nextInt();

        // Creating Column Name, Value Pair
        DataStructure[] data = new DataStructure[6];
        int id = DatabaseConnector.getID("doctor", "d_id");
        if (++id == 0) {
            id = 1;
        }
        data[0] = new DataStructure("d_id", id);
        data[1] = new DataStructure("d_name", name);
        data[2] = new DataStructure("d_gender", gender);
        data[3] = new DataStructure("specialist", specialization);
        data[4] = new DataStructure("experience", experience);
        data[5] = new DataStructure("d_phone", phoneNumber);

        // Database Insertion
        DatabaseConnector.insert(data, "doctor");
    }

    public static void addPatient() {
        // Taking information
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add Patient");
        System.out.println("=====================================");
        System.out.println("$ Enter Patient's Name");
        String name = scanner.nextLine();
        System.out.println("$ Enter Patient's Age");
        int age = scanner.nextInt();
        System.out.println("$ Choose Patient's Gender\n1. Male\n2. Female");
        int genderInt = scanner.nextInt();
        String gender = "m";
        while (genderInt != 1 && genderInt != 2) {
            System.out.println("Invalid input");
            genderInt = scanner.nextInt();
        }
        if (genderInt == 2) gender = "f";
        scanner.nextLine();
        System.out.println("$ Enter Patient's Address (City)");
        String city = scanner.nextLine();
        System.out.println("$ Enter Patient's Address (Country)");
        String country = scanner.nextLine();
        System.out.println("$ Enter Patient's Phone Number");
        String phoneNumber = scanner.nextLine();
        System.out.println("$ Select Doctor");
        ShowData.showDoctors();
        int doctorID = scanner.nextInt();

        // Patient ID
        int id = DatabaseConnector.getID("patient", "p_id");
        if (++id == 0) {
            id = 1;
        }

        // Creating Column Name, Value Pair
        DataStructure[] data = new DataStructure[8];
        data[0] = new DataStructure("p_id", id);
        data[1] = new DataStructure("p_name", name);
        data[2] = new DataStructure("p_age", age);
        data[3] = new DataStructure("p_gender", gender);
        data[4] = new DataStructure("city", city);
        data[5] = new DataStructure("country", country);
        data[6] = new DataStructure("p_phone", phoneNumber);
        data[7] = new DataStructure("d_id", doctorID);

        // Database Insertion
        DatabaseConnector.insert(data, "patient");

    }

    public static void addEmployee() {
        // Taking information
        Scanner scanner = new Scanner(System.in);
        System.out.println("Add Employee");
        System.out.println("=====================================");
        System.out.println("$ Enter Employee's Name");
        String name = scanner.nextLine();
        System.out.println("$ Choose Employee's Gender\n1. Male\n2. Female");
        int genderInt = scanner.nextInt();
        String gender = "m";
        while (genderInt != 1 && genderInt != 2) {
            System.out.println("Invalid input");
            genderInt = scanner.nextInt();
        }
        if (genderInt == 2) gender = "f";
        scanner.nextLine();
        System.out.println("$ Enter Employee's designation");
        String job = scanner.nextLine();
        System.out.println("$ Enter Employee's Phone Number");
        String phoneNumber = scanner.nextLine();
        System.out.println("$ Select Doctor");
        ShowData.showDoctors();
        int doctorID = scanner.nextInt();

        // Employee ID
        int id = DatabaseConnector.getID("employee", "e_id");
        if (++id == 0) {
            id = 1;
        }

        // Creating Column Name, Value Pair
        DataStructure[] data = new DataStructure[6];
        data[0] = new DataStructure("e_id", id);
        data[1] = new DataStructure("e_name", name);
        data[2] = new DataStructure("e_gender", gender);
        data[3] = new DataStructure("job", job);
        data[4] = new DataStructure("d_id", doctorID);
        data[5] = new DataStructure("p_phone", phoneNumber);

        // Database Insertion
        DatabaseConnector.insert(data, "employee");

    }
}
