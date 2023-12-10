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
@WebServlet(name = "qlnhanvien", urlPatterns = {"/xemthongtinnhanvien", "/themnhanvien","/sathainhanvien","/chidinhnhanvien","/tuyennhanvien"})
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
                    ThemNhanVien(request, response);
                    break;
                case "/sathainhanvien":
                    SaThaiNhanVien(request, response);
                    break;
                case "/chidinhnhanvien":
                    ChiDinhNhanVien(request, response);
                    break;
                case "/tuyennhanvien":
                    TuyenNhanVien(request, response);
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

            cancuoccongdan cccd = thongtincanhanDAO.layCCCD(matk);
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
    public void ThemNhanVien(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        request.setAttribute("thongtincanhan", null);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/qlnhanvien/chitietnhanvien.jsp");
        dispatcher.forward(request, response);
    }
    public void SaThaiNhanVien(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        String matk = request.getParameter("matk");
        qlnhanvienDAO.SaThaiNhanVien(matk);
        response.sendRedirect("quanlynhanvien");
    }
    public void ChiDinhNhanVien(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        String matk = request.getParameter("matk");
        String mapb = request.getParameter("mapb");
        String macn = request.getParameter("macn");
        String congviec = request.getParameter("congviec");
        qlnhanvienDAO.ChiDinhNhanVien(matk, mapb, macn,congviec);
        response.sendRedirect("quanlynhanvien");
    }
    public void TuyenNhanVien(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException{
        String matk = forgotDAO.getNewMatk();
        String hoten = request.getParameter("hoten");
        LocalDate ngaysinh = LocalDate.parse(request.getParameter("ngaysinh"));
        String gioitinh = request.getParameter("gioitinh");
        String so_cccd = request.getParameter("cccd");
        String diachi = request.getParameter("diachi");
        String sdt = request.getParameter("sdt");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String pass = request.getParameter("pass");
        String congviec = request.getParameter("congviec");
        String chucvu = request.getParameter("chucvu");
        String phongban = request.getParameter("phongban");
        String chinhanh = request.getParameter("chinhanh");
        String bangcap = request.getParameter("bangcap");
        LocalDate ngaybatdau = LocalDate.parse(request.getParameter("ngaybatdau"));


        thongtincanhan ttcn = new thongtincanhan(matk, hoten, ngaysinh, gioitinh,null,sdt,email,bangcap);
        cancuoccongdan cccd = new cancuoccongdan(matk, so_cccd,ngaybatdau,null);
        nhanvien nv = new nhanvien(matk,chinhanh,phongban,ngaybatdau,"Đang hoạt động",congviec);
        taikhoan tk = new taikhoan(username,pass,matk);

        qlnhanvienDAO.ThemNhanVien(nv);
        forgotDAO.ThemTaiKhoan(tk);
        thongtincanhanDAO.ThemThongTinCaNhan(ttcn);
        thongtincanhanDAO.ThemCCCD(cccd);

        response.sendRedirect("quanlynhanvien");
    }
}