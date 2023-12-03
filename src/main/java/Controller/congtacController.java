package Controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

import DAO.congtacDAO;
import Model.congtac;
import Model.taikhoan;
import DAO.phongbanDAO;

@WebServlet(name = "congtac", urlPatterns = { "/themcongtac","/thaydoicongtac","/xoacongtac","/xemcongtac"})
public class congtacController extends HttpServlet {
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
                case "/themcongtac":
                    Themcongtac(request, response);
                    break;
                case "/thaydoicongtac":
                    Thaydoicongtac(request, response);
                    break;
                case "/xoacongtac":
                    Xoacongtac(request, response);
                    break;
                case "/xemcongtac":
                    Xemcongtac(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/congtac/congtac.jsp");
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
    private void Themcongtac(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String matk = getMatk(request, response);
        if (matk != null) {
            LocalDate ngaybatdau = LocalDate.parse(request.getParameter("ngaybatdau"));
            String tentc = request.getParameter("tentochuc");
            String diachi = request.getParameter("diachi");
            String chucvu = request.getParameter("chucvu");
            String lydo = request.getParameter("lydo");
            congtac newcongtac = new congtac(matk, ngaybatdau, tentc, diachi, chucvu, lydo);
            congtacDAO.ThemCongTac(newcongtac);
            response.sendRedirect("congtac");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/congtac/congtac.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void Thaydoicongtac(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String matk = getMatk(request, response);
        if (matk != null) {
            LocalDate ngaybatdau = LocalDate.parse(request.getParameter("ngaybatdau"));
            String tentc = request.getParameter("tentochuc");
            String diachi = request.getParameter("diachi");
            String chucvu = request.getParameter("chucvu");
            String lydo = request.getParameter("lydo");
            congtac newcongtac = new congtac(matk, ngaybatdau, tentc, diachi, chucvu, lydo);
            congtacDAO.ThayDoiCongTac(newcongtac);
            response.sendRedirect("congtac");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/congtac/congtac.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void Xoacongtac(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String matk = getMatk(request, response);
        if (matk != null) {
            LocalDate ngaybatdau = LocalDate.parse(request.getParameter("ngaybatdau"));
            String tentc = request.getParameter("tentochuc");

            congtac newcongtac = new congtac(matk, ngaybatdau, tentc, null, null, null);
            congtacDAO.XoaCongTac(newcongtac);
            response.sendRedirect("congtac");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/congtac/congtac.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void Xemcongtac(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);
        int capbac = (int) session.getAttribute("capbac");
        if (capbac == 1){
            String mapb = phongbanDAO.LayMaPB(getMatk(request,response));
            List < congtac > listcongtac = congtacDAO.DanhSachCongTac_NV_PB(mapb);
            request.setAttribute("listcongtac_nv", listcongtac);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/congtac/xem_congtac.jsp");
        dispatcher.forward(request, response);
    }
}