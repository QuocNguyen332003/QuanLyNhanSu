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

import DAO.congtacDAO;
import DAO.chinhanhDAO;
import DAO.phongbanDAO;
import Model.congtac;
import Model.chinhanh;
import Model.phongban;
@WebServlet(name = "phongban", urlPatterns = { "/delete"})
public class chinhanhController extends HttpServlet {
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
                case "/delete":
                    deleteChiNhanh(request, response);
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
}