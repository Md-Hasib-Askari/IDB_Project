package essentials;

import essentials.Insert.InsertData;

import java.sql.*;

public class DatabaseConnector {
    static String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/xe";
    private static final String username = "hms";
    private static final String password = "hms";
    private static Connection connection;

    public static void init() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connected to Oracle database as user: " + username);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void close() {
        try {
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }
    public static int getID(String tableName, String idCol) {
        int id = -1;
        try {
            Statement stmtID = connection.createStatement();
            ResultSet rs = stmtID.executeQuery("select max("+ idCol +") from " + tableName);
            while (rs.next())
                id = rs.getInt(1);
            return id;
        } catch (Exception e) {
            System.out.println(e);
        }
        return id;
    }

    public static void getTables() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select table_name from user_tables");
            while (rs.next())
                System.out.println(rs.getString(1));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void insert(Object[] data, String tableName) {
        if (tableName.equals("doctor"))
            InsertData.insertDoctor(data, tableName);
        else if (tableName.equals("patient"))
            InsertData.insertPatient(data, tableName);
        else if (tableName.equals("employee"))
            InsertData.insertEmployee(data, tableName);
        else if (tableName.equals("appointment"))
            InsertData.insertAppointment(data, tableName);
        else if (tableName.equals("record"))
            InsertData.insertMedicalRecord(data, tableName);
        else
            System.out.println("Invalid table name");
    }

    public static void getAll(String tableName) {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from " + tableName);
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3)+" "+rs.getString(4));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void getUser(int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("select id, name, password, gender from users where id = ?");
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getString(4));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void update(String password, int id) {
        try {
            PreparedStatement stmt = connection.prepareStatement("update users set password = ? where id = ?");
            stmt.setString(1, password);
            stmt.setInt(2, id);
            stmt.execute();
            if (stmt.getUpdateCount() == 1) {
                System.out.println("Update Successful!!!");
                getUser(id);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private static void delete(int id) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
            PreparedStatement stmt = con.prepareStatement("delete from users where id = ?");
            stmt.setInt(1, id);
            stmt.execute();
//            if (stmt.getUpdateCount() == 1) {
//                System.out.println("Delete Successful!!!");
//                getAll();
//            }
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
