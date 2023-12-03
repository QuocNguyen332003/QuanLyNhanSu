package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import JDBCUtils.JDBCUtils;
import Model.taikhoan;
import Model.thongtincanhan;
public class forgotDAO {
    public boolean kiemtratk(taikhoan tk, thongtincanhan tt) throws ClassNotFoundException {
        boolean result = false;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT * " +
                             "FROM thongtincanhan " +
                             "JOIN taikhoan " +
                             "ON thongtincanhan.matk = taikhoan.matk " +
                             "WHERE username = ? AND email = ? ")) {
            preparedStatement.setString(1, tk.getUsername());
            preparedStatement.setString(2, tt.getEmail());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            result = rs.next();

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return result;
    }
}
