package essentials.View;

import essentials.DatabaseConnector;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ShowData {
    public static void showDoctors() {
        try {
            Connection connection = DatabaseConnector.getConnection();
            System.out.println("================");
            System.out.println("Doctors");
            System.out.println("================");
            System.out.println("ID\tName\tGender\tSpecialist\tExperience");
            System.out.println("====================================================");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from doctor order by d_id");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" +
                        rs.getString(3) + "\t" + rs.getString(4) + "\t" +
                        rs.getInt(5));
            }
            System.out.println("================");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void showDoctor(int p_id) {
        try {
            Connection connection = DatabaseConnector.getConnection();
            System.out.println("================");
            System.out.println("Doctors");
            System.out.println("================");
            System.out.println("ID\tName\tGender\tSpecialist\tExperience");
            System.out.println("====================================================");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from doctor where d_id=(select d_id from appointment where p_id = " + p_id + ") order by d_id");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" +
                        rs.getString(3) + "\t" + rs.getString(4) + "\t" +
                        rs.getInt(5));
            }
            System.out.println("================");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void getPatient(String phone) {
        try {
            Connection connection = DatabaseConnector.getConnection();
            System.out.println("================");
            System.out.println("Patients");
            System.out.println("================");
            System.out.println("ID\tName\tAge\tGender\tCity");
            System.out.println("====================================================");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from patient " +
                    "where p_id=(select p_id from patient_phone " +
                                "where p_phone="+phone+") order by p_id");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" +
                        rs.getInt(3) + "\t" + rs.getString(4) + "\t" +
                        rs.getString(5));
            }
            System.out.println("================");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void getPatientRecord(String phone) {
        try {
            Connection connection = DatabaseConnector.getConnection();
            System.out.println("================");
            System.out.println("Patients");
            System.out.println("================");
            System.out.println("ID\tName\tAge\tGender\tCity");
            System.out.println("====================================================");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select p_id from patient_phone where p_phone = " + phone + " order by p_id");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" +
                        rs.getString(3) + "\t" + rs.getString(4) + "\t" +
                        rs.getInt(5));
            }
            System.out.println("================");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void getAppointmentID(int p_id, int d_id) {
        try {
            Connection connection = DatabaseConnector.getConnection();
            System.out.println("================");
            System.out.println("Appointment");
            System.out.println("================");
            System.out.println("ID\tPatient\tDoctor\tDate");
            System.out.println("====================================================");
            Statement stmt = connection.createStatement();
            String query = "select appointment.a_id, patient.p_name, doctor.d_name, appointment.a_date " +
                    "from appointment join patient on patient.p_id=appointment.p_id " +
                    "join doctor on doctor.d_id=appointment.d_id " +
                    "where appointment.p_id = " + p_id + " and appointment.d_id = " + d_id + " order by appointment.a_id";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" +
                        rs.getString(3) + "\t" + rs.getDate(4).toString());
            }
            System.out.println("================");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
