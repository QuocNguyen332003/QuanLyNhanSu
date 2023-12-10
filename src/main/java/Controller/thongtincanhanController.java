package Controller;

import Model.cancuoccongdan;
import Model.taikhoan;
import Model.thongtincanhan;

import DAO.thongtincanhanDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet(name = "thongtincanhan", urlPatterns = { "/thaydoithongtin"})
public class thongtincanhanController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public void init() {
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/thaydoithongtin":
                    capNhatThongTin(request, response);
                    capNhatCCCD(request, response);
                    capNhatMatKhau(request, response);
                    response.sendRedirect("thongtincanhan");
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/thongtincanhan/thongtincanhan.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private String getMatk(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            taikhoan username = (taikhoan) session.getAttribute("user");
            return username.getMatk();
        }
        else {
            return null;
        }
    }
    private void capNhatThongTin(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String matk = getMatk(request, response);
        if (matk != null) {
            String hoten = request.getParameter("hoten");
            LocalDate ngaysinh = LocalDate.parse(request.getParameter("ngaysinh"));
            String gioitinh = request.getParameter("gioitinh");
            String diachi = request.getParameter("diachi");
            String sdt = request.getParameter("sdt");
            String email = request.getParameter("email");
            thongtincanhan tt = new thongtincanhan(matk, hoten, ngaysinh, gioitinh, diachi, sdt, email);
            thongtincanhanDAO.capNhatThongTinCaNhan(tt);

        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/thongtincanhan/thongtincanhan.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void capNhatCCCD(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String matk = getMatk(request, response);
        if (matk != null) {
            String cccd = request.getParameter("cccd");
            cancuoccongdan cancuoc = new cancuoccongdan(matk, cccd);
            thongtincanhanDAO.capNhatCCCD(cancuoc);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/thongtincanhan/thongtincanhan.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void capNhatMatKhau(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String matk = getMatk(request, response);
        String user = "";
        if (matk != null) {
            String pass = request.getParameter("pass");
            taikhoan tk = new taikhoan(user,pass,matk);
            thongtincanhanDAO.capNhatMatKhau(tk);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/thongtincanhan/thongtincanhan.jsp");
            dispatcher.forward(request, response);
        }
    }
}
