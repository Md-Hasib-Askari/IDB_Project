import Database.Search.SearchData;
import Database.View.ShowTableData;
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
                            // Doctor
                            ShowTableData.doctorView();
                            break;
                        case "2":
                            // Patient
                            ShowTableData.patientView();
                            break;
                        case "3":
                            // Employee
                            ShowTableData.employeeView();
                            break;
                        case "4":
                            dataView = "appointments";
                            // Appointment
                            ShowTableData.appointmentView();
                            break;
                        case "5":
                            // Medical Record
                            ShowTableData.recordView();
                            break;
                        default:
                            System.out.println("Invalid input");
                            operationMenu("View");
                            break;
                    }
                    break;
                case "6":
                    // Search Operation
                    operationMenu("Search");
                    String dataSearch = scanner.nextLine();
                    String name = "";
                    switch (dataSearch) {
                        case "1":
                            // Doctor
                            System.out.println("Enter Doctor's Name");
                            name = scanner.nextLine();
                            SearchData.doctorSearch(name);
                            break;
                        case "2":
                            // Patient
                            System.out.println("Enter Patient's Name");
                            name = scanner.nextLine();
                            SearchData.patientSearch(name);
                            break;
                        case "3":
                            // Employee
                            System.out.println("Enter Employee's Name");
                            name = scanner.nextLine();
                            SearchData.employeeSearch(name);
                            break;
                        case "4":
                            // Appointment
                            System.out.println("Enter Patient's Name");
                            name = scanner.nextLine();
                            SearchData.appointmentSearch(name);
                            break;
                        case "5":
                            // Medical Record
                            System.out.println("Enter Patient's Name");
                            name = scanner.nextLine();
                            SearchData.recordSearch(name);
                            break;
                        default:
                            System.out.println("Invalid input");
                            operationMenu("Search");
                            break;
                    }
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
        System.out.println("5. View Data");
        System.out.println("6. Search Data");
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
