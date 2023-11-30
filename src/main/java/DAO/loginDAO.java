package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBCUtils.JDBCUtils;
import Model.taikhoan;

public class loginDAO {

    public taikhoan validate(taikhoan tk) throws ClassNotFoundException {
        taikhoan result = null;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection
                     .prepareStatement("select * from taikhoan where username = ? and pass = ? ")) {
            preparedStatement.setString(1, tk.getUsername());
            preparedStatement.setString(2, tk.getMatk());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("pass");
                String matk = rs.getString("matk");
                result = new taikhoan(username, password, matk);
            }

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return result;
    }
}