package DAO;

import JDBCUtils.JDBCUtils;
import Model.phongban;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class phongbanDAO {

    private static final String SELECT_ALL = "select * from phongban";
    private static final String DELETE_PB_BY_MAPB = "delete from phongban where mapb = ?;";
    private static final String SELECT_TRUONGPHONG = "select mapb from phongban where matrphong = ?;";
    public static List< phongban > selectAllphongban() {

        List < phongban > listphongban = new ArrayList< >();

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String mapb = rs.getString("mapb");
                String tenpb = rs.getString("tenpb");
                String macn = rs.getString("macn");
                String matrphong = rs.getString("matrphong");
                Date ngaytao = rs.getDate("ngaytao");  // Use java.util.Date
                String mapbtr = rs.getString("mapbtr");

                listphongban.add(new phongban(mapb, tenpb, macn, matrphong, ngaytao, mapbtr));

            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return listphongban;
    }
    public boolean deletePhongBan(String mapb) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PB_BY_MAPB);) {
            statement.setString(1, mapb);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }
    public static String LayMaPB(String matrphong) throws SQLException {
        String result = null;
        boolean rowDeleted;
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_TRUONGPHONG);) {
            statement.setString(1, matrphong);
            ResultSet rs = statement.executeQuery();
            System.out.println(statement);
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                result = rs.getString("mapb");
            }
        }
        return result;
    }
}