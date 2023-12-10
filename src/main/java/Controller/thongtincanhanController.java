package Controller;

import Model.cancuoccongdan;
import Model.taikhoan;
import Model.thongtincanhan;
import Model.diachi;

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

@WebServlet(name = "thongtincanhan", urlPatterns = { "/thaydoithongtin","/thaydoidiachi","/thaydoicccd"})
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
                    capNhatMatKhau(request, response);
                    response.sendRedirect("thongtincanhan");
                    break;
                case "/thaydoicccd":
                    capNhatCCCD(request, response);
                    break;
                case "/thaydoidiachi":
                    capNhatDiaChi(request,response);
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
            String sdt = request.getParameter("sdt");
            String email = request.getParameter("email");
            thongtincanhan tt = new thongtincanhan(matk, hoten, ngaysinh, gioitinh, sdt, email);
            thongtincanhanDAO.capNhatThongTinCaNhan(tt);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/thongtincanhan/thongtincanhan.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void capNhatCCCD(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String matk = getMatk(request, response);
        String madc = "";
        if (matk != null) {
            String cccd = request.getParameter("cccd");
            LocalDate ngaycap = LocalDate.parse(request.getParameter("ngaycap"));
            cancuoccongdan cancuoc = new cancuoccongdan(matk, cccd, ngaycap,madc);
            thongtincanhanDAO.capNhatCCCD(cancuoc);
            response.sendRedirect("thongtincanhan");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/thongtincanhan/thongtincanhan.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void capNhatDiaChi(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String matk = getMatk(request, response);
        thongtincanhan tt = thongtincanhanDAO.layThongTinCaNhan(matk);
        String madc = tt.getDiachi();
        if (matk != null) {
            String tinhtp = request.getParameter("tinhtp");
            String quanhuyen = request.getParameter("quanhuyen");
            String phuongxa = request.getParameter("phuongxa");
            String sonha = request.getParameter("sonha");
            diachi dc = new diachi(madc,tinhtp,quanhuyen,phuongxa,sonha);
            thongtincanhanDAO.capNhatDiaChi(dc);
            response.sendRedirect("thongtincanhan");
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
