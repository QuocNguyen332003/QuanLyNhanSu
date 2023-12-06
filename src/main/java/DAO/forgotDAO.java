package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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

    public static void sendEmail(String host, String port,
                                 final String userName, final String password, String toAddress,String subject,
                                 String message) throws AddressException,
            MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };

        Session session = Session.getInstance(properties, auth);
        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setText(message);

        Transport.send(msg);
    }

    public boolean changePass(taikhoan tk, thongtincanhan tt, String newPassword) throws ClassNotFoundException {
        boolean rowUpdated = false;
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("UPDATE taikhoan t JOIN thongtincanhan tc ON t.matk = tc.matk " +
                             "SET t.pass = ? WHERE t.username = ? AND tc.email = ?")) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setString(2, tk.getUsername());
            preparedStatement.setString(3, tt.getEmail());

            System.out.println(preparedStatement);
            boolean isResultSet = preparedStatement.execute();
            if (!isResultSet) {
                int rowsAffected = preparedStatement.getUpdateCount();
                rowUpdated = rowsAffected > 0;
            }
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
        return rowUpdated;
    }
}
