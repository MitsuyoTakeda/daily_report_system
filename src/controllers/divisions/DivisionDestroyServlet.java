package controllers.divisions;

import java.io.IOException;
import java.sql.Timestamp;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Division;
import utils.DBUtil;

/**
 * Servlet implementation class DivisionDestroyServlet
 */
@WebServlet("/divisions/destroy")
public class DivisionDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DivisionDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");

        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();
            Division d = em.find(Division.class, (Integer)request.getSession().getAttribute("id"));

            d.setDelete_flag(1);
            d.setUpdated_at(new Timestamp(System.currentTimeMillis()));

            em.getTransaction().begin();
            em.getTransaction().commit();
            em.close();

            request.getSession().setAttribute("flush", "削除が完了しました。");
            response.sendRedirect(request.getContextPath() + "/divisions/index");
        }
    }

}
