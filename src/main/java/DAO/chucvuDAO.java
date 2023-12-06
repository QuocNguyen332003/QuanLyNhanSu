package DAO;

import JDBCUtils.JDBCUtils;
import Model.congtac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Objects;

public class chucvuDAO {
    private static final String SELECT_ALL = "select * from chucvu where matk = ?";
    public static int CapBacQuyenHan(String matk) {
        int capbac = 0;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            preparedStatement.setString(1, matk);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                if (Objects.equals(rs.getString("tentk"), "Trưởng Phòng") && capbac < 1){
                    capbac = 1;
                } else if (Objects.equals(rs.getString("tentk"), "Giám Đốc") && capbac < 2) {
                    capbac = 2;
                } else if (Objects.equals(rs.getString("tentk"), "Quản Trị") && capbac < 3) {
                    capbac = 3;
                }
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return capbac;
    }

    public static String TenCapBac(String matk) {
        String tencapbac = null;
        int capbac = 0;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            preparedStatement.setString(1, matk);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                tencapbac = rs.getString("tentk");
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return tencapbac;
    }
}
