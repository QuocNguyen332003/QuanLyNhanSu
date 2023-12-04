package DAO;

import JDBCUtils.JDBCUtils;
import Model.nhanvien;
import Model.phongban;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class qlnhanvienDAO {
    private static final String SELECT_NHANVIEN_PB = "select matk from nhanvien where mapb = ?;";
    public static List<nhanvien> LayNhanVienPB(String mapb) {

        List < nhanvien > listNhanvien = new ArrayList< >();

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NHANVIEN_PB);) {
            preparedStatement.setString(1, mapb);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String matk = rs.getString("matk");
                listNhanvien.add(new nhanvien(matk,null,null,null,null,null));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return listNhanvien;
    }
    public static List<nhanvien> LayNhanVienCN(String macn) {

        List <phongban> listphongban = phongbanDAO.selectAllphongban_CN(macn);
        List < nhanvien > listNhanvien = new ArrayList< >();

        for (phongban pb: listphongban) {
            try (Connection connection = JDBCUtils.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NHANVIEN_PB);) {
                preparedStatement.setString(1, pb.getMapb());
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    String matk = rs.getString("matk");
                    listNhanvien.add(new nhanvien(matk,null,null,null,null,null));
                }
            } catch (SQLException exception) {
                JDBCUtils.printSQLException(exception);
            }
        }
        return listNhanvien;
    }
}
