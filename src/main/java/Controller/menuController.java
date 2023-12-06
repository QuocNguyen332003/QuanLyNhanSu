package Controller;

import java.io.IOException;
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

@WebServlet(name = "menu", urlPatterns = { "/trangchu", "/thongtincanhan", "/congtac", "/khenthuongkyluat", "/quanlynhanvien", "/quanlyphongban","/quanlychinhanh"})
public class menuController extends HttpServlet {
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
                case "/trangchu":
                    Formtrangchu(request, response);
                    break;
                case "/thongtincanhan":
                    Formthongtincanhan(request, response);
                    break;
                case "/congtac":
                    Formcongtac(request, response);
                    break;
                case "/khenthuongkyluat":
                    Formkhenthuongkyluat(request, response);
                    break;
                case "/quanlynhanvien":
                    Formquanlynhanvien(request, response);
                    break;
                case "/quanlyphongban":
                    Formquanlyphongban(request, response);
                    break;
                case "/quanlychinhanh":
                    Formquanlychinhanh(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
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
    private void Formtrangchu(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/sodocay/sodo.jsp");
        dispatcher.forward(request, response);
    }
    private void Formthongtincanhan(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        if(session != null){
            taikhoan tk = (taikhoan) session.getAttribute("user");

            String matk = getMatk(request,response);

            thongtincanhan tt = thongtincanhanDAO.layThongTinCaNhan(matk);
            request.setAttribute("thongtincanhan", tt);

            String cccd = thongtincanhanDAO.layCCCD(matk);
            request.setAttribute("cancuoc",cccd);

            taikhoan tkhoan = thongtincanhanDAO.layTaiKhoan(matk);
            request.setAttribute("taikhoan", tkhoan);

            String chucvu = thongtincanhanDAO.layChucVu(matk);
            request.setAttribute("chucvu", chucvu);
            System.out.println(chucvu);

            String congviec = thongtincanhanDAO.LayCongViec(matk);
            request.setAttribute("congviec",congviec);

            LocalDate ngaybatdau = thongtincanhanDAO.layNgayBatDau(matk);
            request.setAttribute("ngaybatdau",ngaybatdau);

            String tenpb = thongtincanhanDAO.layTenPB(matk);
            request.setAttribute("tenpb",tenpb);

            String tencn = thongtincanhanDAO.layTenCN(matk);
            request.setAttribute("tencn",tencn);



            RequestDispatcher dispatcher = request.getRequestDispatcher("/thongtincanhan/thongtincanhan.jsp");
            dispatcher.forward(request, response);
        }else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
            dispatcher.forward(request, response);
        }

    }
    private void Formcongtac(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            taikhoan username = (taikhoan) session.getAttribute("user");
            List < congtac > listcongtac = congtacDAO.DanhSachCongTac(username.getMatk());
            request.setAttribute("listcongtac", listcongtac);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/congtac/congtac.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void Formkhenthuongkyluat(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            taikhoan username = (taikhoan) session.getAttribute("user");
            List <thanhtichkyluat> listKTKL = khenthuongkyluatDAO.DanhSachKTKL(username.getMatk());
            request.setAttribute("listKTKL", listKTKL);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/khenthuongkyluat/khenthuongkyluat.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void Formquanlynhanvien(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        HttpSession session = request.getSession(false);
        if (session != null) {
            taikhoan username = (taikhoan) session.getAttribute("user");
            int capbac = (int) session.getAttribute("capbac");
            List <nhanvien> listnv = null;
            if (capbac == 1){
                String mapb = phongbanDAO.LayMaPB(username.getMatk());
                listnv = qlnhanvienDAO.LayNhanVienPB(mapb);
            } else if (capbac == 2) {
                String macn = chinhanhDAO.LayMaCN(username.getMatk());
                listnv = qlnhanvienDAO.LayNhanVienCN(macn);
            } else if (capbac == 3) {
                listnv = qlnhanvienDAO.LayNhanVien();
            }
            if (capbac != 0){
                List<String> listmatk = new ArrayList<>();
                List<String> listmapb = new ArrayList<>();
                List<String> listmacn = new ArrayList<>();
                for (nhanvien nv: listnv) {
                    listmatk.add(nv.getMatk());
                    listmapb.add(nv.getMapb());
                    listmacn.add(nv.getMacn());
                }
                Set<String> setmatk_nv = new HashSet<String>(listmatk);
                Set<String> setmapb_nv = new HashSet<String>(listmapb);
                Set<String> setmacn_nv = new HashSet<String>(listmacn);
                request.setAttribute("setmatk_nv", setmatk_nv);
                request.setAttribute("setmapb_nv", setmapb_nv);
                request.setAttribute("setmacn_nv", setmacn_nv);
            }
            request.setAttribute("listnv", listnv);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/qlnhanvien/quanlynhanvien.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void Formquanlyphongban(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        int capbac = (int) session.getAttribute("capbac");
        nhanvien nv = (nhanvien)session.getAttribute("thongtinnv");
        String mapb = nv.getMapb();
        String macn = nv.getMacn();
        if (session != null) {
            taikhoan username = (taikhoan) session.getAttribute("user");
            if(capbac == 3) {
                List<phongban> listphongban = phongbanDAO.selectAllphongban();
                request.setAttribute("listphongban", listphongban);
            }
            if(capbac == 2){
                List<phongban> listphongban = phongbanDAO.selectAllphongban_CN(macn);
                request.setAttribute("listphongban", listphongban);
            }
            if(capbac == 1) {
                List<phongban> listphongban = phongbanDAO.selectAllphongban_PB(mapb);
                request.setAttribute("listphongban", listphongban);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/qlcongty/quanlyphongban.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void Formquanlychinhanh(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            taikhoan username = (taikhoan) session.getAttribute("user");
            List <chinhanh> listchinhanh = chinhanhDAO.selectAllchinhanh();
            request.setAttribute("listchinhanh", listchinhanh);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/qlcongty/quanlychinhanh.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}