package DAO;

import JDBCUtils.JDBCUtils;
import Model.nhanvien;
import Model.phongban;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class qlnhanvienDAO {
    private static final String SELECT_ALL_THONGTIN = "select * from nhanvien where matk = ?;";
    private static final String SELECT_ALL_NV = "select * from nhanvien;";
    private static final String SELECT_NHANVIEN_PB = "select * from nhanvien where mapb = ?;";
    private static final String SELECT_NHANVIEN_CN = "select * from nhanvien where macn = ?;";
    public static nhanvien LayThongTinNhanVien(String matk){
        nhanvien nv = new nhanvien();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_THONGTIN);) {
             preparedStatement.setString(1, matk);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String macn = rs.getString("macn");
                String mapb = rs.getString("mapb");
                LocalDate ngaybatdau = rs.getDate("ngaybatdau").toLocalDate();
                String tinhtrang = rs.getString("tinhtrang");
                String congviec = rs.getString("congviec");
                nv = new nhanvien(matk,macn,mapb,ngaybatdau,tinhtrang,congviec);
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return nv;
    }
    public static List<nhanvien> LayDanhSachNhanVien(String SQL, String dk){
        List < nhanvien > listNhanvien = new ArrayList< >();

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);) {
            if (dk != null){
                preparedStatement.setString(1, dk);
            }
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String matk = rs.getString("matk");
                String macn = rs.getString("macn");
                String mapb = rs.getString("mapb");
                LocalDate ngaybatdau = rs.getDate("ngaybatdau").toLocalDate();
                String tinhtrang = rs.getString("tinhtrang");
                String congviec = rs.getString("congviec");
                listNhanvien.add(new nhanvien(matk,macn,mapb,ngaybatdau,tinhtrang,congviec));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return listNhanvien;
    }
    public static List< nhanvien > selectAllnhanvien(String maCN) {

        List < nhanvien > listnhanvien = new ArrayList< >();

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NHANVIEN_CN);) {
            System.out.println(preparedStatement);
            if (maCN != null){
                preparedStatement.setString(1, maCN);
            }
            // Step 3: Execute the query or update queryc
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String matk = rs.getString("matk");
                String macn = rs.getString("macn");
                String mapb = rs.getString("mapb");
                LocalDate ngaybatdau = rs.getDate("ngaybatdau").toLocalDate();
                String tinhtrang = rs.getString("tinhtrang");
                String congviec = rs.getString("congviec");
                listnhanvien.add(new nhanvien(matk,macn,mapb,ngaybatdau,tinhtrang,congviec));
                System.out.println(listnhanvien);

            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return listnhanvien;
    }
    public static List<String> selectAllNhanVienNames(String maCN) {
        List<String> employeeNames = new ArrayList<>();

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();
             // Step 2: Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NHANVIEN_CN);) {
            System.out.println(preparedStatement);
            if (maCN != null) {
                preparedStatement.setString(1, maCN);
            }
            // Step 3: Execute the query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String matk = rs.getString("matk"); // Replace "tennhanvien" with the actual column name for the employee's name
                employeeNames.add(matk);
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return employeeNames;
    }

    public static List<nhanvien> LayNhanVien(){
        return LayDanhSachNhanVien(SELECT_ALL_NV, null);
    }
    public static List<nhanvien> LayNhanVienPB(String mapb) {
        return LayDanhSachNhanVien(SELECT_NHANVIEN_PB, mapb);
    }
    public static List<nhanvien> LayNhanVienCN(String macn) {
        return LayDanhSachNhanVien(SELECT_NHANVIEN_CN, macn);
    }
}
