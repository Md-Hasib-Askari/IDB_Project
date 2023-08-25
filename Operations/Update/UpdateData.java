package Operations.Update;

import Database.DataStructure;
import Database.DatabaseConnector;
import Database.View.ShowData;

import java.util.ArrayList;
import java.util.Scanner;

public class UpdateData {
    public static void updateDoctor() {
        // Taking Information
        Scanner scanner = new Scanner(System.in);
        System.out.println("Update Doctor");
        System.out.println("=====================================");
        System.out.println("$ Enter Doctor's phone number");
        String phoneNumber = scanner.nextLine();

        // Select Doctor
        ShowData.showDoctorByPhone(phoneNumber);
        System.out.println("$ Enter Doctor's ID");
        int id = scanner.nextInt();

        // Existing Data Fetch
        ArrayList<Object> existingData = DatabaseConnector.getData("doctor", id, "d_id");

        // Data Change
        System.out.println("$ Enter Doctor's Name (Current: " + existingData.get(1) + ")");
        scanner.nextLine();
        String d_name = scanner.nextLine();

        System.out.println("$ Enter Doctor's Gender (Current: " + existingData.get(2) + ")");
        String d_gender = scanner.nextLine();


        System.out.println("$ Enter Doctor's Specialization (Current: " + existingData.get(3) + ")");
        String specialist = scanner.nextLine();

        System.out.println("$ Enter Doctor's Experience (Current: " + existingData.get(4) + ")");
        int experience = scanner.nextInt();
        System.out.println();

        System.out.println("$ Enter Doctor's Phone");
        scanner.nextLine();
        String d_phone = scanner.nextLine();

        // Creating Column Name, Value Pair
        ArrayList<DataStructure> data = new ArrayList<>();
        data.add(new DataStructure("d_name", d_name));
        data.add(new DataStructure("d_gender", d_gender));
        data.add(new DataStructure("specialist", specialist));
        data.add(new DataStructure("experience", experience));
        data.add(new DataStructure("d_phone", d_phone));

        // Database Update
        DatabaseConnector.update("doctor", id, data);

    }

    public static void updatePatient() {
        // Taking Information
        Scanner scanner = new Scanner(System.in);
        System.out.println("Update Patient");
        System.out.println("=====================================");
        System.out.println("$ Enter Patient's phone number");
        String phoneNumber = scanner.nextLine();

        // Select Doctor
        ShowData.getPatient(phoneNumber);
        System.out.println("$ Enter Patient's ID");
        int id = scanner.nextInt();

        // Existing Data Fetch
        ArrayList<Object> existingData = DatabaseConnector.getData("patient", id, "p_id");

        // Data Change
        System.out.println("$ Enter Patient's Name (Current: " + existingData.get(1) + ")");
        scanner.nextLine();
        String p_name = scanner.nextLine();

        System.out.println("$ Enter Patient's Age (Current: " + existingData.get(2) + ")");
        int p_age = scanner.nextInt();

        System.out.println("$ Enter Patient's Gender (Current: " + existingData.get(3) + ")\n1. Male\n2. Female");
        int gender = scanner.nextInt();
        String p_gender = "m";
        while (gender != 1 && gender != 2) {
            System.out.println("Invalid Input");
            gender = scanner.nextInt();
        }
        if (gender == 2)
            p_gender = "f";

        ShowData.showCity();
        System.out.println("$ Enter Patient's City");
        scanner.nextLine();
        String city = scanner.nextLine();

        System.out.println("$ Enter Patient's Country");
        String country = scanner.nextLine();

        System.out.println("$ Enter Patient's Phone");
        String p_phone = scanner.nextLine();
        System.out.println();

        // Creating Column Name, Value Pair
        ArrayList<DataStructure> data = new ArrayList<>();
        data.add(new DataStructure("p_name", p_name));
        data.add(new DataStructure("p_age", p_age));
        data.add(new DataStructure("p_gender", p_gender));
        data.add(new DataStructure("city", city));
        data.add(new DataStructure("country", country));
        data.add(new DataStructure("p_phone", p_phone));

        // Database Update
        DatabaseConnector.update("patient", id, data);
    }

    public static void updateEmployee() {
        // Taking Information
        Scanner scanner = new Scanner(System.in);
        System.out.println("Update Employee");
        System.out.println("=====================================");
        System.out.println("$ Enter Employee's ID");
        int e_id = scanner.nextInt();

        // Existing Data Fetch
        ArrayList<Object> existingData = DatabaseConnector.getData("employee", e_id, "e_id");

        // Data Change
        System.out.println("$ Enter Employee's Name (Current: " + existingData.get(1) + ")");
        scanner.nextLine();
        String e_name = scanner.nextLine();

        System.out.println("$ Enter Employee's Gender (Current: " + existingData.get(2) + ")");
        String e_gender = scanner.nextLine();

        System.out.println("$ Enter Employee's Role (Current: " + existingData.get(3) + ")");
        String job = scanner.nextLine();

        ShowData.showDoctors();
        System.out.println("$ Select Doctor (Current: " + existingData.get(4) + ")");
        int d_id = scanner.nextInt();

        System.out.println("$ Enter Employee's phone");
        scanner.nextLine();
        String e_phone = scanner.nextLine();
        System.out.println();

        // Creating Column Name, Value Pair
        ArrayList<DataStructure> data = new ArrayList<>();
        data.add(new DataStructure("e_name", e_name));
        data.add(new DataStructure("e_gender", e_gender));
        data.add(new DataStructure("job", job));
        data.add(new DataStructure("d_id", d_id));
        data.add(new DataStructure("e_phone", e_phone));

        // Database Update
        DatabaseConnector.update("doctor", e_id, data);
    }

    public static void updateMedicalRecord() {
        // Taking Information
        Scanner scanner = new Scanner(System.in);
        System.out.println("Update Medical Record");
        System.out.println("=====================================");
        System.out.println("$ Enter Medical Record's ID");
        int r_id = scanner.nextInt();

        // Existing Data Fetch
        ArrayList<Object> existingData = DatabaseConnector.getData("record", r_id, "r_id");
        ArrayList<Object> existingPrescription = DatabaseConnector.getData("record_drugs", r_id, "r_id");

        // Data Change
        System.out.println("$ Enter Diagnosis (Current: " + existingData.get(1) + ")");
        scanner.nextLine();
        String diagnosis = scanner.nextLine();

        System.out.println("$ Enter Date (Current: " + existingData.get(2) + ")");
        String r_date = scanner.nextLine();

        ShowData.showDoctors();
        System.out.println("$ Select Reference (Current: " + existingData.get(3) + ")");
        int reference = scanner.nextInt();

        System.out.println("$ Enter Appointment ID (Current: " + existingData.get(4) + ")");
        int a_id = scanner.nextInt();

        // Drugs Info Update
        ArrayList<String> drugs = new ArrayList<>();
        System.out.println("=====================================");
        for (int j = 0; j < existingPrescription.size(); j++) {
            System.out.println("$ Enter Drug Name (Current: " + existingPrescription.get(j++) + ")");
            String drugName = scanner.nextLine();
            drugs.add(drugName);
        }

        // Creating Column Name, Value Pair
        ArrayList<DataStructure> data = new ArrayList<>();
        data.add(new DataStructure("diagnosis", diagnosis));
        data.add(new DataStructure("r_date", r_date));
        data.add(new DataStructure("reference", reference));
        data.add(new DataStructure("a_id", a_id));
        data.add(new DataStructure("drugs", drugs));

        // Database Update
        DatabaseConnector.update("doctor", r_id, data);
    }

    public static void updateAppointment() {
        // Taking Information
        Scanner scanner = new Scanner(System.in);
        System.out.println("Update Appointment");
        System.out.println("=====================================");
        System.out.println("$ Enter Appointment's ID");
        int a_id = scanner.nextInt();

        // Existing Data Fetch
        ArrayList<Object> existingData = DatabaseConnector.getData("appointment", a_id, "a_id");

        // Data Change
        System.out.println("$ Enter New Appointment Date (Current: " + existingData.get(1).toString() + ")");
        scanner.nextLine();
        String a_date = scanner.nextLine();

        System.out.println("$ Enter Patient's ID (Current: " + existingData.get(2) + ")");
        String p_id = scanner.nextLine();

        System.out.println("$ Enter Doctor's ID (Current: " + existingData.get(3) + ")");
        String d_id = scanner.nextLine();


        // Creating Column Name, Value Pair
        ArrayList<DataStructure> data = new ArrayList<>();
        data.add(new DataStructure("a_date", a_date));
        data.add(new DataStructure("p_id", p_id));
        data.add(new DataStructure("d_id", d_id));

        // Database Update
        DatabaseConnector.update("doctor", a_id, data);
    }
}
