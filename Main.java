import Operations.Add.AddData;
import essentials.DatabaseConnector;

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
                    operationMenu("Add");
                    String dataAdd = scanner.nextLine();
                    while (!dataAdd.equals("#")) {
                        switch (dataAdd) {
                            case "1":
                                AddData.addDoctor();
                                break;
                            case "2":
                                AddData.addPatient();
                                break;
                            case "3":
                                AddData.addEmployee();
                                break;
                            case "4":
                                // "appointments"
                                break;
                            case "5":
                                // "medical_records"
                                break;
                            default:
                                System.out.println("Invalid input");
                                operationMenu("Add");
                                break;
                        }
                        dataAdd = scanner.nextLine();
                    }
                    break;
                case "3":
                    operationMenu("Update");
                    String dataUpdate = scanner.nextLine();
                    switch (dataUpdate) {
                        case "1":
                            dataUpdate = "doctors";
                            break;
                        case "2":
                            dataUpdate = "patients";
                            break;
                        case "3":
                            dataUpdate = "employees";
                            break;
                        case "4":
                            dataUpdate = "appointments";
                            break;
                        case "5":
                            dataUpdate = "medical_records";
                            break;
                        default:
                            System.out.println("Invalid input");
                            operationMenu("Update");
                            break;
                    }
                    break;
                case "4":
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
                    operationMenu("Search");
                    String dataSearch = scanner.nextLine();
                    switch (dataSearch) {
                        case "1":
                            dataSearch = "doctors";
                            break;
                        case "2":
                            dataSearch = "patients";
                            break;
                        case "3":
                            dataSearch = "employees";
                            break;
                        case "4":
                            dataSearch = "appointments";
                            break;
                        case "5":
                            dataSearch = "medical_records";
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
