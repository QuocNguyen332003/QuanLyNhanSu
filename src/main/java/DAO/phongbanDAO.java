package DAO;

import JDBCUtils.JDBCUtils;
import Model.phongban;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class phongbanDAO {

    private static final String SELECT_ALL = "select * from phongban";
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
}