package essentials.Insert;

import essentials.DataStructure;
import essentials.DatabaseConnector;

import java.sql.*;

public class InsertData {
    public static void insertDoctor(Object[] data, String tableName) {
        Connection connection = DatabaseConnector.getConnection();
        try {
            System.out.println("================");
            PreparedStatement stmt = null;
            DataStructure[] convertedData = new DataStructure[data.length];
            for (int i = 0; i < data.length; i++) {
                convertedData[i] = (DataStructure) data[i];
            }

            // Column wise data
            int d_id = (int) convertedData[0].getValue();
            String d_name = (String) convertedData[1].getValue();
            String d_gender = (String) convertedData[2].getValue();
            String specialist = (String) convertedData[3].getValue();
            int experience = (int) convertedData[4].getValue();
            String d_phone = (String) convertedData[5].getValue();

            // Query
            String queryColumn = "";
            for (int i = 0; i < data.length - 1; i++) {
                queryColumn += convertedData[i].getColumn();
                if (i != data.length - 2)
                    queryColumn += ", ";
            }

            // Prepared Statement
            stmt = connection.prepareStatement("insert into " + tableName
                    + " (" + queryColumn + ") " +
                    "values (?, ?, ?, ?, ?)");
            stmt.setInt(1, d_id);
            stmt.setString(2, d_name);
            stmt.setString(3, d_gender);
            stmt.setString(4, specialist);
            stmt.setInt(5, experience);
            stmt.execute();

            // Phone Data to doctor-phone table
            Statement stmt1 = connection.createStatement();
            stmt1.execute("insert into " + tableName + "_phone (" +
                    convertedData[0].getColumn() + "," + convertedData[data.length-1].getColumn() + ") " +
                    "values (" + d_id + "," + d_phone + ")");
            System.out.println("Data inserted successfully in " + tableName);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void insertPatient(Object[] data, String tableName) {
        Connection connection = DatabaseConnector.getConnection();
        System.out.println("================");
        DataStructure[] convertedData = new DataStructure[data.length];
        for (int i = 0; i < data.length; i++) {
            convertedData[i] = (DataStructure) data[i];
        }

        // Column wise data
        int p_id = (int) convertedData[0].getValue();
        String p_name = (String) convertedData[1].getValue();
        int p_age = (int) convertedData[2].getValue();
        String p_gender = (String) convertedData[3].getValue();
        String city = (String) convertedData[4].getValue();
        String country = (String) convertedData[5].getValue();
        String p_phone = (String) convertedData[6].getValue();
        int d_id = (int) convertedData[7].getValue();

        // Query
        String queryColumn = "";
        for (int i = 0; i < data.length - 3; i++) {
            queryColumn += convertedData[i].getColumn();
            if (i != data.length - 4)
                queryColumn += ", ";
        }

        // Prepared Statement
        PreparedStatement stmt = null;

        // City Data to patient-address table (Foreign Key of patient table)
        try {
            String query = "insert into " + tableName + "_address ("
                    + convertedData[4].getColumn() + "," + convertedData[5].getColumn() + ") " +
                    "values (?, ?)";
            stmt = connection.prepareStatement(query);
            stmt.setString(1, city);
            stmt.setString(2, country);
            stmt.execute();
            System.out.println("New City inserted successfully in " + tableName);
        } catch (Exception e) {
            System.out.println("City already exists in " + tableName);
        }


        // Prepared Statement
        try {
            stmt = connection.prepareStatement("insert into " + tableName
                    + " (" + queryColumn + ") " +
                    "values (?, ?, ?, ?, ?)");
            stmt.setInt(1, p_id);
            stmt.setString(2, p_name);
            stmt.setInt(3, p_age);
            stmt.setString(4, p_gender);
            stmt.setString(5, city);
            stmt.execute();
            System.out.println("New Patient inserted successfully in " + tableName);
        } catch (Exception e) {
            System.out.println("Patient already exists in " + tableName);
        }

        // Phone Data to patient-phone table
        try {
            stmt.execute("insert into " + tableName + "_phone (" +
                    convertedData[0].getColumn() + "," + convertedData[data.length-2].getColumn() + ") " +
                    "values (" + p_id + "," + p_phone + ")");
            System.out.println("Phone added for patient inserted successfully in " + tableName);
        } catch (Exception e) {
            System.out.println(e);
        }


        // Appointment table
        int id = DatabaseConnector.getID("appointment", "a_id");
        if (++id == 0)
            id = 1;

        try {
            stmt = connection.prepareStatement("insert into appointment (a_id, a_date, p_id, d_id) " +
                    "values (?, ?, ?, ?)");
            stmt.setInt(1, id);
            stmt.setDate(2, new Date(System.currentTimeMillis()));
            stmt.setInt(3, p_id);
            stmt.setInt(4, d_id);

            stmt.execute();
            System.out.println("New Appointment inserted successfully in appointment");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void insertEmployee(Object[] data, String tableName) {
        Connection connection = DatabaseConnector.getConnection();

        System.out.println("================");
        PreparedStatement stmt;
        DataStructure[] convertedData = new DataStructure[data.length];
        for (int i = 0; i < data.length; i++) {
            convertedData[i] = (DataStructure) data[i];
        }

        // Column wise data
        int e_id = (int) convertedData[0].getValue();
        String e_name = (String) convertedData[1].getValue();
        String e_gender = (String) convertedData[2].getValue();
        String job = (String) convertedData[3].getValue();
        int d_id = (int) convertedData[4].getValue();
        String e_phone = (String) convertedData[5].getValue();

        // Query
        String queryColumn = "";
        for (int i = 0; i < data.length - 1; i++) {
            queryColumn += convertedData[i].getColumn();
            if (i != data.length - 2)
                queryColumn += ", ";
        }

        // Prepared Statement
        try {
            stmt = connection.prepareStatement("insert into " + tableName
                    + " (" + queryColumn + ") " +
                    "values (?, ?, ?, ?, ?)");
            stmt.setInt(1, e_id);
            stmt.setString(2, e_name);
            stmt.setString(3, e_gender);
            stmt.setString(4, job);
            stmt.setInt(5, d_id);
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Employee already exists in " + tableName);
        }

        // Phone Data to doctor-phone table
        try {
            Statement stmt1 = connection.createStatement();
            stmt1.execute("insert into " + tableName + "_phone (" +
                    convertedData[0].getColumn() + "," + convertedData[data.length-1].getColumn() + ") " +
                    "values (" + e_id + "," + e_phone + ")");
            System.out.println("Data inserted successfully in " + tableName);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
