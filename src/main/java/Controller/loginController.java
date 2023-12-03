package Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.loginDAO;
import Model.taikhoan;
import DAO.chucvuDAO;
@WebServlet("/login")
public class loginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private loginDAO loginDao;

    public void init() {
        loginDao = new loginDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("/login/login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authenticate(request, response);
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        taikhoan loginModel = new taikhoan();
        loginModel.setUsername(username);
        loginModel.setMatk(password);

        try {
            taikhoan tk = loginDao.validate(loginModel);
            int capbac = chucvuDAO.CapBacQuyenHan(tk.getMatk()); // 0 nhanvien 1 truong phong 2 giam doc 3 admin
            if (tk != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute("user", tk);
                session.setAttribute("capbac",capbac);
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
}