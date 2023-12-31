package Database.Search;

import Database.DatabaseConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SearchData {
    public static void doctorSearch(String name) {
        System.out.println("============================================================================");
        System.out.println("ID | Doctor Name \t| Gender | Specialization \t| Experience | Phone");
        System.out.println("============================================================================");
        Connection connection = DatabaseConnector.getConnection();
        String query = "SELECT doctor.*, doctor_phone.d_phone  FROM doctor JOIN doctor_phone ON doctor.d_id = doctor_phone.d_id where d_name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1)+"\t"+ rs.getString(2)+"\t\t\t\t"+ rs.getString(3)+"\t\t"+ rs.getString(4)+"\t\t\t\t\t"+ rs.getInt(5)+"\t\t\t"+ rs.getString(6));
            }
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void patientSearch(String name) {
        System.out.println("============================================================================");
        System.out.println("ID | Patient Name \t| Age | Gender | City \t\t\t| Country | Phone");
        System.out.println("============================================================================");
        Connection connection = DatabaseConnector.getConnection();
        String query = "SELECT patient.*, patient_address.country, patient_phone.p_phone  FROM patient JOIN patient_phone ON patient.p_id = patient_phone.p_id JOIN patient_address ON patient.city = patient_address.city where p_name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1)+"\t"+ rs.getString(2)+"\t\t\t\t"+ rs.getInt(3)+"\t"+ rs.getString(4)+"\t\t\t"+ rs.getString(5)+"\t\t"+ rs.getString(6) +"\t\t"+ rs.getString(7));
            }
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void employeeSearch(String name) {
        System.out.println("============================================================================");
        System.out.println("ID | Employee Name \t| Gender | Job \t| Doctor | Phone");
        System.out.println("============================================================================");
        Connection connection = DatabaseConnector.getConnection();
        String query = "SELECT emp.e_id, emp.e_name, emp.e_gender, emp.job, doctor.d_name, employee_phone.e_phone  FROM employee emp JOIN employee_phone ON emp.e_id = employee_phone.e_id JOIN doctor ON emp.d_id = doctor.d_id where e_name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1)+"\t"+ rs.getString(2)+"\t\t\t\t"+ rs.getString(3)+"\t"+ rs.getString(4)+"\t"+ rs.getString(5)+"\t\t"+ rs.getString(6));
            }
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void appointmentSearch(String p_name) {
        System.out.println("============================================================================");
        System.out.println("ID | Patient Name \t| Age | Gender \t| Doctor's Name | Specialist");
        System.out.println("============================================================================");
        Connection connection = DatabaseConnector.getConnection();
        String query = "SELECT app.a_id, patient.p_name, patient.p_age, patient.p_gender, doctor.d_name, doctor.specialist  FROM appointment app JOIN patient ON patient.p_id = app.p_id JOIN doctor ON app.d_id = doctor.d_id where patient.p_name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, p_name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1)+"\t"+ rs.getString(2)+"\t\t\t\t"+ rs.getInt(3)+"\t"+ rs.getString(4)+"\t\t\t"+ rs.getString(5)+"\t\t"+ rs.getString(6));
            }
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void recordSearch(String p_name) {
        System.out.println("============================================================================");
        System.out.println("ID | Patient Name \t| Diagnosis \t\t| Date\t | Reference");
        System.out.println("============================================================================");
        Connection connection = DatabaseConnector.getConnection();
        String query = "SELECT record.r_id, patient.p_name, record.diagnosis, record.r_date, doctor.d_name  FROM record JOIN appointment app ON app.a_id=record.a_id JOIN patient ON patient.p_id = app.p_id JOIN doctor ON app.d_id = doctor.d_id where patient.p_name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, p_name);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1)+"\t"+ rs.getString(2)+"\t\t\t\t"+ rs.getString(3)+"\t\t"+ rs.getDate(4)+"\t"+ rs.getString(5));
            }
            preparedStatement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
