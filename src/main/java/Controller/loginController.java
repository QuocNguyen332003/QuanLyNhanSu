package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.loginDAO;
import DAO.changeDAO;
import Model.taikhoan;
@WebServlet(name = "login", urlPatterns = { "/login", "/forgot", "/change"})
public class loginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private loginDAO loginDao;
    private changeDAO changeDao = new changeDAO();

    public void init() {
        loginDao = new loginDAO();
    }
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
                Forgotpass(request, response);
                break;
            case "/change":
                ChangePass(request, response);
                break;
        }
    }
    private void authenticate(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        taikhoan loginModel = new taikhoan();
        loginModel.setUsername(username);
        loginModel.setMatk(password);

        try {
            HttpSession session = request.getSession();
            taikhoan tk = loginDao.validate(loginModel);
            if (tk != null) {
                session.setAttribute("user", tk);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/trangchu");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("error", "Thông tin đăng nhập không hợp lệ");
                // session.setAttribute("user", username);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
                dispatcher.forward(request, response);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void Forgotpass(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{

    }

    private void ChangePass(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException{
        String username = request.getParameter("username");
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String confirmnewpass = request.getParameter("confirmnewpass");

        if (!newpassword.equals(confirmnewpass)) {
            request.setAttribute("error", "Mật khẩu mới và xác nhận mật khẩu không khớp");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/changePassword/changePassword.jsp");
            dispatcher.forward(request, response);
            return;
        }

        taikhoan loginModel = new taikhoan();
        loginModel.setUsername(username);
        loginModel.setMatk(oldpassword);

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