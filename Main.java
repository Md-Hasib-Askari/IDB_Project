import Operations.Add.AddData;
import Database.DatabaseConnector;
import Operations.Update.UpdateData;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Hospital Management System");
        mainMenu();

        String input = scanner.nextLine();
        while (!input.equals("q")) {
            DatabaseConnector.init();
            switch (input) {
                case "1":
                    DatabaseConnector.getTables();
                    break;
                case "2":
                    // Insert Operation
                    operationMenu("Add");
                    String dataAdd = scanner.nextLine();
                    while (!dataAdd.equals("#")) {
                        switch (dataAdd) {
                            case "1":
                                // Doctor
                                AddData.addDoctor();
                                break;
                            case "2":
                                // Patient
                                AddData.addPatient();
                                break;
                            case "3":
                                // Employee
                                AddData.addEmployee();
                                break;
                            case "4":
                                // Appointment
                                AddData.addAppointment();
                                break;
                            case "5":
                                // Medical Record
                                AddData.addMedicalRecord();
                                break;
                            default:
                                System.out.println("Invalid input, Try again.");
                                break;
                        }
                        operationMenu("Add");
                        dataAdd = scanner.nextLine();
                    }
                    break;
                case "3":
                    // Update Operation
                    operationMenu("Update");
                    String dataUpdate = scanner.nextLine();
                    switch (dataUpdate) {
                        case "1":
                            // Doctor
                            UpdateData.updateDoctor();
                            break;
                        case "2":
                            // Patient
                            UpdateData.updatePatient();
                            break;
                        case "3":
                            // Employee
                            UpdateData.updateEmployee();
                            break;
                        case "4":
                            // Appointment
                            UpdateData.updateAppointment();
                            break;
                        case "5":
                            // Medical Record
                            UpdateData.updateMedicalRecord();
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                    operationMenu("Update");
                    break;
                case "4":
                    // Delete Operation
                    operationMenu("Delete");
                    String dataDelete = scanner.nextLine();
                    switch (dataDelete) {
                        case "1":
                            dataDelete = "doctors";
                            break;
                        case "2":
                            dataDelete = "patients";
                            break;
                        case "3":
                            dataDelete = "employees";
                            break;
                        case "4":
                            dataDelete = "appointments";
                            break;
                        case "5":
                            dataDelete = "medical_records";
                            break;
                        default:
                            System.out.println("Invalid input");
                            operationMenu("Delete");
                            break;
                    }
                    break;
                case "5":
                    // View Operation
                    operationMenu("View");
                    String dataView = scanner.nextLine();
                    switch (dataView) {
                        case "1":
                            dataView = "doctors";
                            break;
                        case "2":
                            dataView = "patients";
                            break;
                        case "3":
                            dataView = "employees";
                            break;
                        case "4":
                            dataView = "appointments";
                            break;
                        case "5":
                            dataView = "medical_records";
                            break;
                        default:
                            System.out.println("Invalid input");
                            break;
                    }
                    operationMenu("View");
                    break;
                default:
                    System.out.println("Invalid input");
            }
            DatabaseConnector.close();
            mainMenu();
            input = scanner.nextLine();
        }
    }

    private static void mainMenu() {
        System.out.println("=====================================");
        System.out.println("Main Menu");
        System.out.println("=====================================");
        System.out.println("$ Select options below");
        System.out.println("1. show tables");
        System.out.println("2. Add Data");
        System.out.println("3. Update Data");
        System.out.println("4. Delete Data");
        System.out.println("5. Search Data");
        System.out.println("q. quit");
    }

    private static void operationMenu(String ops) {
        System.out.println("=====================================");
        System.out.println("$ Select options below");
        System.out.println("1. " + ops + " Doctor");
        System.out.println("2. " + ops + " Patient");
        System.out.println("3. " + ops + " Employee");
        System.out.println("4. " + ops + " Appointment");
        System.out.println("5. " + ops + " Medical Record");
        System.out.println("#. Main Menu");
        System.out.println("q. quit");
    }

}
