package Controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.*;
import Model.*;
@WebServlet(name = "qlnhanvien", urlPatterns = {"/xemthongtinnhanvien", "/themnhanvien"})
public class qlnhanvienController extends HttpServlet {
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
                case "/xemthongtinnhanvien":
                    XemThongTinCaNhan(request, response);
                    break;
                case "/themnhanvien":
                    XemThongTinCaNhan(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/qlnhanvien/quanlynhanvien.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    public void XemThongTinCaNhan(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        if(session != null){
            taikhoan tk = (taikhoan) session.getAttribute("user");

            String matk = request.getParameter("matk");

            thongtincanhan tt = thongtincanhanDAO.layThongTinCaNhan(matk);
            request.setAttribute("thongtincanhan", tt);

            String cccd = thongtincanhanDAO.layCCCD(matk);
            request.setAttribute("cancuoc",cccd);

            taikhoan tkhoan = thongtincanhanDAO.layTaiKhoan(matk);
            request.setAttribute("taikhoan", tkhoan);

            String chucvu = thongtincanhanDAO.layChucVu(matk);
            request.setAttribute("chucvu", chucvu);

            String congviec = thongtincanhanDAO.LayCongViec(matk);
            request.setAttribute("congviec",congviec);

            LocalDate ngaybatdau = thongtincanhanDAO.layNgayBatDau(matk);
            request.setAttribute("ngaybatdau",ngaybatdau);

            String tenpb = thongtincanhanDAO.layTenPB(matk);
            request.setAttribute("tenpb",tenpb);

            String tencn = thongtincanhanDAO.layTenCN(matk);
            request.setAttribute("tencn",tencn);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/qlnhanvien/chitietnhanvien.jsp");
            dispatcher.forward(request, response);
        }else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}