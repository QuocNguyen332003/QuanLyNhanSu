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

public class congtacDAO {

    private static final String SELECT_ALL = "select * from congtac where matk = 'N0101'";
    public static List< congtac > selectAllcongtac() {

        List < congtac > listcongtac = new ArrayList< >();

        // Step 1: Establishing a Connection
        try (Connection connection = JDBCUtils.getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String matk = rs.getString("matk");
                LocalDate ngaybatdau = rs.getDate("ngaybatdau").toLocalDate();
                String tentochuc = rs.getString("tentochuc");
                String diachi = rs.getString("diachi");
                String chucvu = rs.getString("chucvu");
                String lydo = rs.getString("lydo");
                listcongtac.add(new congtac(matk,ngaybatdau,tentochuc,diachi,chucvu,lydo));
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }
        return listcongtac;
    }
}