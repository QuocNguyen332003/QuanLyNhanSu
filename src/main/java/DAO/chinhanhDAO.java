package DAO;

import JDBCUtils.JDBCUtils;
import Model.chinhanh;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class chinhanhDAO {
    private static final String SELECT_ALL = "select * from chinhanh";
    private static final String DELETE_CN_BY_MACN = "delete from chinhanh where macn = ?;";
    public static List<chinhanh> selectAllchinhanh() {

        List < chinhanh > listchinhanh = new ArrayList< >();

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String macn = rs.getString("macn");
                String tencn = rs.getString("tencn");
                String diachi = rs.getString("diachi");
                String magiamdoc = rs.getString("magiamdoc");
                String tinhtrang = rs.getString("tinhtrang");
                Date ngaytao = rs.getDate("ngaytao");  // Use java.util.Date

                listchinhanh.add(new chinhanh(macn, tencn, diachi, magiamdoc, tinhtrang, ngaytao));

            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return listchinhanh;
    }
    public boolean deleteChiNhanh(String macn) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CN_BY_MACN);) {
            statement.setString(1, macn);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
}
