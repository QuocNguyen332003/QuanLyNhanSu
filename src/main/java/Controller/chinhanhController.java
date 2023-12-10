package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.*;
import Model.*;

@WebServlet(name = "chinhanh", urlPatterns = { "/deleteChiNhanh", "/addChiNhanh","/insertChiNhanh","/editChiNhanh","/updateChiNhanh"})
public class chinhanhController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private chinhanhDAO cnDAO ;
    public void init() {
        cnDAO = new chinhanhDAO();
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
                case "/deleteChiNhanh":
                    deleteChiNhanh(request, response);
                    break;
                case "/addChiNhanh":
                    FormThemChiNhanh(request, response);
                    break;
                case "/editChiNhanh":
                    FormEditChiNhanh(request, response);
                    break;
                case "/insertChiNhanh":
                    insertChiNhanh(request, response);
                    break;
                case "/updateChiNhanh":
                    updateChiNhanh(request, response);
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
    private void deleteChiNhanh(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String macn = request.getParameter("macn");
        chinhanhDAO dao = new chinhanhDAO();
        dao.deleteChiNhanh(macn);
        response.sendRedirect("quanlychinhanh");
    }
    private void FormThemChiNhanh(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        HttpSession session = request.getSession(false);
        int capbac = (int) session.getAttribute("capbac");
        nhanvien nv = (nhanvien)session.getAttribute("thongtinnv");
        String mapb = nv.getMapb();
        String macn = nv.getMacn();
        if (session != null) {
            List <diachi> listdiachi = diachiDAO.DanhSachDiaChi();
            request.setAttribute("listdiachi", listdiachi);

            List <nhanvien> listnhanvien = qlnhanvienDAO.LayNhanVien();
            request.setAttribute("listnhanvien", listnhanvien);

            RequestDispatcher dispatcher = request.getRequestDispatcher("qlcongty/themchinhanh.jsp");
            dispatcher.forward(request, response);
        }
        else
        {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
            dispatcher.forward(request, response);
        };
    }
    private void FormEditChiNhanh(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        int capbac = (int) session.getAttribute("capbac");
        nhanvien nv = (nhanvien)session.getAttribute("thongtinnv");
        String mapb = nv.getMapb();
        String macn = nv.getMacn();
        if (session != null) {
            String maCN = request.getParameter("macn");
            chinhanh existingChiNhanh = cnDAO.selectChiNhanh(maCN);
            request.setAttribute("chinhanh", existingChiNhanh);

            List <diachi> listdiachi = diachiDAO.DanhSachDiaChi();
            request.setAttribute("listdiachi", listdiachi);

            List <nhanvien> listnhanvien = qlnhanvienDAO.LayNhanVien();
            request.setAttribute("listnhanvien", listnhanvien);

            RequestDispatcher dispatcher = request.getRequestDispatcher("qlcongty/themchinhanh.jsp");
            dispatcher.forward(request, response);
        }
        else{
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login/login.jsp");
            dispatcher.forward(request, response);
        };
    }
    private void insertChiNhanh(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        String macn = request.getParameter("macn");
        String tencn = request.getParameter("tencn");
        String diachi = request.getParameter("diachi");
        String magiamdoc = request.getParameter("magiamdoc");
        String tinhtrang = request.getParameter("tinhtrang");


        // Tạo đối tượng phongban từ thông tin lấy được
        chinhanh newchinhanh = new chinhanh(macn, tencn, diachi, magiamdoc, tinhtrang, LocalDate.now());
        System.out.println(newchinhanh);
        chinhanhDAO cnDAO = new chinhanhDAO();
        try {
            cnDAO.insertChiNhanh(newchinhanh);
            response.sendRedirect("quanlychinhanh"); // Chuyển hướng sau khi thêm thành công
        } catch (SQLException e) {
            // Xử lý lỗi SQL (hiển thị hoặc log lỗi)
            e.printStackTrace();// Chuyển hướng đến trang lỗi nếu có lỗi
        }
    }
    private void updateChiNhanh(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

        String macn = request.getParameter("macn");
        String tencn = request.getParameter("tencn");
        String diachi = request.getParameter("diachi");
        String magiamdoc = request.getParameter("magiamdoc");
        String tinhtrang = request.getParameter("tinhtrang");

        chinhanh updatechinhanh = new chinhanh(macn, tencn, diachi, magiamdoc, tinhtrang, null);
        cnDAO.updateChiNhanh(updatechinhanh);
        response.sendRedirect("quanlychinhanh");
    }
}