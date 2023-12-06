package DAO;

import JDBCUtils.JDBCUtils;
import Model.congtac;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Model.diachi;

public class diachiDAO {
    private static final String SELECT_ALL_DIACHI = "select * from diachi";

    public static List<diachi> DanhSachDiaChi() {

        List < diachi > listdiachi = new ArrayList< >();

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_DIACHI);) {
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String madc = rs.getString("madc");
                String tinhtp = rs.getString("tinhtp");
                String quanhuyen = rs.getString("quanhuyen");
                String phuongxa = rs.getString("phuongxa");
                String sonha = rs.getString("sonha");
                listdiachi.add(new diachi(madc, tinhtp, quanhuyen, phuongxa, sonha));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return listdiachi;
    }
}
