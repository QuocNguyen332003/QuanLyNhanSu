package Controller;

import DAO.qlnhanvienDAO;
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

@WebServlet(name = "thongtincanhan", urlPatterns = { "/thaydoithongtin","/thaydoitaikhoan","/luuthongtin"})
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

        switch (action) {
            case "/thaydoithongtin":
                break;
            case "/thaydoitaikhoan":
                break;
            case "/luuthongtin":
                break;
            default:
                RequestDispatcher dispatcher = request.getRequestDispatcher("/thongtincanhan/thongtincanhan.jsp");
                dispatcher.forward(request, response);
                break;
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
}
