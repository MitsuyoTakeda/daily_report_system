package controllers.divisions;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Division;
import utils.DBUtil;

/**
 * Servlet implementation class DivisonIndexServlet
 */
@WebServlet("/divisions/index")
public class DivisonIndexServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DivisonIndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = DBUtil.createEntityManager();
        int page;

        try{
            page = Integer.parseInt(request.getParameter("page"));

        } catch(Exception e) {
            page = 1;
        }

        List<Division> divisions = em.createNamedQuery("getAllDivisions", Division.class)
                                       .setFirstResult(15 * (page - 1))
                                       .setMaxResults(15)
                                       .getResultList();

        long divisions_count = em.createNamedQuery("getDivisionsCount",Long.class)
                                    .getSingleResult();

        em.close();

        request.setAttribute("divisions", divisions);
        request.setAttribute("divisions_count", divisions_count);
        request.setAttribute("page", page);

        if(request.getSession().getAttribute("flush") != null){
            request.setAttribute("flush", request.getSession().getAttribute("flush"));
            request.getSession().removeAttribute("flush");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/divisions/index.jsp");
        rd.forward(request, response);

    }

}
