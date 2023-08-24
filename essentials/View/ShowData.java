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
}
