package Database.Update;

import Database.DataStructure;
import Database.DatabaseConnector;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class UpdateData {
    public static void updateDoctor(int id, ArrayList<DataStructure> data) {
        Connection connection = DatabaseConnector.getConnection();
        String d_name = (String) data.get(0).getValue();
        String d_gender = (String) data.get(1).getValue();
        String specialist = (String) data.get(2).getValue();
        int experience = (int) data.get(3).getValue();
        String d_phone = (String) data.get(4).getValue();

        try {
            System.out.println("================");
            String query = "update doctor set " + data.get(0).getColumn() + "=?, " +
                    data.get(1).getColumn() + "=?, " +
                    data.get(2).getColumn() + "=?, " +
                    data.get(3).getColumn() + "=? where d_id=" + id;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, d_name);
            statement.setString(2, d_gender);
            statement.setString(3, specialist);
            statement.setInt(4, experience);

            statement.executeUpdate();
            System.out.println("Doctor Updated Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            System.out.println("================");
            String query = "update doctor_phone set " + data.get(4).getColumn() + "=? where d_id=" + id;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, d_phone);

            statement.executeUpdate();
            System.out.println("Doctor Phone Updated Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void updatePatient(int id, ArrayList<DataStructure> data) {
        Connection connection = DatabaseConnector.getConnection();
        String p_name = (String) data.get(0).getValue();
        int p_age = (int) data.get(1).getValue();
        String p_gender = (String) data.get(2).getValue();
        String city = (String) data.get(3).getValue();
        String country = (String) data.get(4).getValue();
        String p_phone = (String) data.get(5).getValue();

        // update city country
        System.out.println("================");
        try {
            String query = "insert into patient_address (" + data.get(3).getColumn() + ", " +
                    data.get(4).getColumn() + ") values (?,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, city);
            statement.setString(2, country);
            statement.executeUpdate();

            System.out.println("Patient Address Updated Successfully");
        } catch (Exception e) {
            System.out.println("City Country Already Exists");
        }

        try {
            String query = "update patient set " + data.get(0).getColumn() + "=?, " +
                    data.get(1).getColumn() + "=?, " +
                    data.get(2).getColumn() + "=?, " +
                    data.get(3).getColumn() + "=? where p_id=" + id;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, p_name);
            statement.setInt(2, p_age);
            statement.setString(3, p_gender);
            statement.setString(4, city);

            statement.executeUpdate();
            System.out.println("Patient Updated Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            System.out.println("================");
            String query = "update patient_phone set " + data.get(5).getColumn() + "=? where p_id=" + id;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, p_phone);

            statement.executeUpdate();
            System.out.println("Patient Phone Updated Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void updateEmployee(int id, ArrayList<DataStructure> data) {
        Connection connection = DatabaseConnector.getConnection();
        String e_name = (String) data.get(0).getValue();
        String e_gender = (String) data.get(1).getValue();
        String job = (String) data.get(2).getValue();
        int d_id = (int) data.get(3).getValue();
        String e_phone = (String) data.get(4).getValue();

        try {
            System.out.println("================");
            String query = "update employee set " + data.get(0).getColumn() + "=?, " +
                    data.get(1).getColumn() + "=?, " +
                    data.get(2).getColumn() + "=?, " +
                    data.get(3).getColumn() + "=? where e_id=" + id;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, e_name);
            statement.setString(2, e_gender);
            statement.setString(3, job);
            statement.setInt(4, d_id);

            statement.executeUpdate();
            System.out.println("Employee Updated Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            System.out.println("================");
            String query = "update employee_phone set " + data.get(4).getColumn() + "=? where e_id=" + id;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, e_phone);
            statement.executeUpdate();
            System.out.println("Employee Phone Updated Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void updateMedicalRecord(int id, ArrayList<DataStructure> data) {
        Connection connection = DatabaseConnector.getConnection();
        String diagnosis = (String) data.get(0).getValue();
        Date r_date = (Date) data.get(1).getValue();
        int reference = (int) data.get(2).getValue();
        int a_id = (int) data.get(3).getValue();
        ArrayList<String> drugs = (ArrayList<String>) data.get(4).getValue();

        try {
            System.out.println("================");
            String query = "update record set " + data.get(0).getColumn() + "=?, " +
                    data.get(1).getColumn() + "=?, " +
                    data.get(2).getColumn() + "=?, " +
                    data.get(3).getColumn() + "=? where r_id=" + id;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, diagnosis);
            statement.setDate(2, r_date);
            statement.setInt(3, reference);
            statement.setInt(4, a_id);

            statement.executeUpdate();
            System.out.println("Record Updated Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            System.out.println("================");
            String query = "update record_drugs set " + data.get(1).getColumn() + "=? where r_id=" + id;
            for (int i = 0; i < drugs.size(); i++) {
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setString(1, drugs.get(i));
                statement.executeUpdate();
            }
            System.out.println("Doctor Phone Updated Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void updateAppointment(int id, ArrayList<DataStructure> data) {
        Connection connection = DatabaseConnector.getConnection();
        Date a_date = (Date) data.get(0).getValue();
        int p_id = (int) data.get(1).getValue();
        int d_id = (int) data.get(2).getValue();

        try {
            System.out.println("================");
            String query = "update appointment set " + data.get(0).getColumn() + "=?, " +
                    data.get(1).getColumn() + "=?, " +
                    data.get(2).getColumn() + "=? where a_id=" + id;
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, a_date);
            statement.setInt(2, p_id);
            statement.setInt(3, d_id);

            statement.executeUpdate();
            System.out.println("Appointment Updated Successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
