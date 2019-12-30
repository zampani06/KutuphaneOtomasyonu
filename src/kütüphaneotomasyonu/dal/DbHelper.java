package kütüphaneotomasyonu.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbHelper {

    private final String userName = "root";
    private final String password = "ozgurutku";
    private final String dbUrl = "jdbc:mysql://localhost:3306/kütüphaneotomasyonu?userSSL=false&serverTimezone=UTC";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, userName, password);

    }

    public void showErrorMessage(SQLException exception) {
        System.out.println("Error:" + exception.getMessage());
        System.out.println("Error code:" + exception.getErrorCode());

    }

}
