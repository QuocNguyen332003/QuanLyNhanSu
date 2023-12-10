package DAO;

import JDBCUtils.JDBCUtils;
import Model.yeucau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class yeucauDAO {

    private static final String SELECT_ALL_NGGUI = "select * from yeucau where matk = ?";
    private static final String SELECT_ALL_NGNHAN = "select * from yeucau where nguoinhan = ?";
    private static final String UPDATE_TINHTRANG = "update yeucau set tinhtrang =? where mayeucau = ?;";
    private static List< yeucau > DanhSachYeuCau(String matk, String SQL) {

        List < yeucau > listyeucau = new ArrayList< >();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL);) {
            preparedStatement.setString(1, matk);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String mayc = rs.getString("mayeucau");
                String manguoinhan = rs.getString("matk");
                LocalDate ngaygui = rs.getDate("ngaygui").toLocalDate();
                String nguoinhan = rs.getString("nguoinhan");
                String congviec = rs.getString("congviec");
                String mapb = rs.getString("mapb");
                String tinhtrang = rs.getString("tinhtrang");
                listyeucau.add(new yeucau(mayc,manguoinhan,ngaygui,nguoinhan,congviec,mapb,tinhtrang));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return listyeucau;
    }
    public static List< yeucau > DanhSachYeuCau_TruongPhong(String matk) {
        return DanhSachYeuCau(matk, SELECT_ALL_NGGUI);
    }
    public static List< yeucau > DanhSachYeuCau_GiamDoc(String matk) {
        return DanhSachYeuCau(matk, SELECT_ALL_NGNHAN);
    }
    public static void Update_tinhtrang(String tinhtrang, String mayeucau){
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TINHTRANG)) {
            preparedStatement.setString(1, tinhtrang);
            preparedStatement.setString(2, mayeucau);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
    }
    public static void Update_tinhtrang_yes(String mayeucau){
        Update_tinhtrang("chấp nhận",mayeucau);
    }
    public static void Update_tinhtrang_no(String mayeucau){
        Update_tinhtrang("từ chối",mayeucau);
    }
}