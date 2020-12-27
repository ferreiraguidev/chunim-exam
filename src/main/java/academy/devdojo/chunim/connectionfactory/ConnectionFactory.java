package academy.devdojo.chunim.connectionfactory;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionFactory  {

    public ConnectionFactory() {
    }

    public static Connection getConexao() {
        String url = "jdbc:mysql://localhost/chunim?useTimezone=true&serverTimezone=UTC";//3306
        String user = "root";
        String password = "Masteronxdarimidex";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println(connection);
            return connection;

        } catch (SQLException var4) {
            var4.printStackTrace();

        } catch (Exception e) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, e);

        }

        return null;
    }


    public static void close(Connection connection, Statement stmt) {
        try {
            if (stmt != null) {     // STATEMENT
            }

            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

