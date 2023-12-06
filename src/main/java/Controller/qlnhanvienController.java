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
@WebServlet(name = "qlnhanvien", urlPatterns = {"/xemthongtinnhanvien"})
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
        String matk = request.getParameter("matk");
        thongtincanhan ttcn = thongtincanhanDAO.layThongTinCaNhan(matk);
        request.setAttribute("thongtincanhanh", ttcn);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/qlnhanvien/chitietnhanvien.jsp");
        dispatcher.forward(request, response);
    }
}