package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

import javax.mail.internet.AddressException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.mail.*;

import DAO.loginDAO;
import DAO.changeDAO;
import DAO.forgotDAO;
import Model.taikhoan;
import Model.thongtincanhan;
import DAO.chucvuDAO;
@WebServlet(name = "login", urlPatterns = { "/login", "/forgot", "/change","/sendmail"})
public class loginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private loginDAO loginDao;
    private changeDAO changeDao = new changeDAO();
    private forgotDAO forgotDao = new forgotDAO();
    public void init() {
        loginDao = new loginDAO();
    }
    // Tạo một số ngẫu nhiên gồm 6 chữ số
    Random rand = new Random();
    private int random = rand.nextInt((999999 - 100000) + 1) + 100000;
    private String maOtp = Integer.toString(random);
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try{
            switch (action){
                case  "/login":
                    FromLogin(request, response);
                    break;
                case "/forgot":
                    FromForgot(request, response);
                    break;
                case "/change":
                    FromChange(request, response);
                    break;
            }
        }catch (SQLException ex)
        {throw new ServletException(ex);}
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();
        switch (action){
            case  "/login":
                authenticate(request, response);
                break;
            case "/forgot":
                NewPass(request, response);
                break;
            case "/change":
                ChangePass(request, response);
                break;
            case "/sendmail":
                Forgotpass(request, response);
                break;
        }
    }
    private void authenticate(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        taikhoan loginModel = new taikhoan();
        loginModel.setUsername(username);
        loginModel.setPass(password);

        try {
            HttpSession session = request.getSession(true);
            taikhoan tk = loginDao.validate(loginModel);
            int capbac = chucvuDAO.CapBacQuyenHan(tk.getMatk()); // 0 nhanvien 1 truong phong 2 giam doc 3 admin
            if (tk != null) {
                session.setAttribute("user", tk);
                session.setAttribute("capbac",capbac);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/trangchu");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("error", "Thông tin đăng nhập không hợp lệ");
                //session.setAttribute("user", username);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login");
                dispatcher.forward(request, response);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void Forgotpass(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{

        String host;
        String port;
        String user;
        String pass;

        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        user = context.getInitParameter("user");
        pass = context.getInitParameter("pass");

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String subject = "Mã OTP xác nhận của bạn là:";

        taikhoan usernameModel = new taikhoan();
        usernameModel.setUsername(username);
        thongtincanhan emailModel = new thongtincanhan();
        emailModel.setEmail(email);

        HttpSession session = request.getSession(true);
        session.setAttribute("username", usernameModel);
        session.setAttribute("email", emailModel);
        try {
            boolean kt = forgotDao.kiemtratk(usernameModel,emailModel);
            if(kt){
                forgotDao.sendEmail(host, port, user, pass, email, subject, maOtp);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login/forgot.jsp");
                dispatcher.forward(request, response);
            }
            else{
                request.setAttribute("error", "Tài khoản hoặc email không đúng!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login/forgot.jsp");
                dispatcher.forward(request, response);
            }

        } catch (ClassNotFoundException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private void NewPass(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String otp = request.getParameter("otp");
        String newpassword = request.getParameter("newpassword");

        HttpSession session = request.getSession(false);
        taikhoan usernameModel = (taikhoan) session.getAttribute("username");
        thongtincanhan emailModel = (thongtincanhan) session.getAttribute("email");

        if(!otp.equals(maOtp))
        {
            request.setAttribute("error", "Mã OTP không trùng khớp!");
            maOtp = null;
            session.invalidate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
            dispatcher.forward(request, response);
            return;
        }
        try {
            boolean ischanged = forgotDao.changePass(usernameModel, emailModel, newpassword);
            if(ischanged){
                session.invalidate();
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
                dispatcher.forward(request, response);
            }
            else{
                session.invalidate();
                request.setAttribute("error", "Không thể thay đổi mật khẩu!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login/forgot.jsp");
                dispatcher.forward(request, response);
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private void ChangePass(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        String username = request.getParameter("username");
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String confirmnewpass = request.getParameter("confirmnewpass");

        if (!newpassword.equals(confirmnewpass)) {
            request.setAttribute("error", "Mật khẩu mới không trùng khớp!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/change.jsp");
            dispatcher.forward(request, response);
            return;
        }

        taikhoan loginModel = new taikhoan();
        loginModel.setUsername(username);
        loginModel.setPass(oldpassword);

        try {
            HttpSession session = request.getSession();
            taikhoan tk = loginDao.validate(loginModel);
            if (tk != null) {
                boolean isChanged = changeDao.changePassword(tk, newpassword);
                if (isChanged) {
                    request.setAttribute("message", "Thay đổi mật khẩu thành công!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("error", "Không thể thay đổi mật khẩu!");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login/change.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                request.setAttribute("error", "Tài khoản hoặc Mật khẩu không tồn tại!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login/change.jsp");
                dispatcher.forward(request, response);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void FromLogin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
        dispatcher.forward(request, response);
    }
    private void FromForgot(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login/forgot.jsp");
        dispatcher.forward(request, response);
    }
    private void FromChange(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login/change.jsp");
        dispatcher.forward(request, response);
    }
}