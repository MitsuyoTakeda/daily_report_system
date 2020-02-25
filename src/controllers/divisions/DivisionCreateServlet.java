package controllers.divisions;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Division;
import models.validators.DivisionValidators;
import utils.DBUtil;

/**
 * Servlet implementation class DivisionCreateServlet
 */
@WebServlet("/divisions/create")
public class DivisionCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DivisionCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = request.getParameter("_token");

        if(_token != null && _token.equals(request.getSession().getId())){
            EntityManager em = DBUtil.createEntityManager();

            Division d = new Division();
            Timestamp currentTime = new Timestamp(System.currentTimeMillis());

            d.setCode(request.getParameter("code"));
            d.setName(request.getParameter("name"));
            d.setDelete_flag(0);
            d.setCreated_at(currentTime);
            d.setUpdated_at(currentTime);

            List<String> errors = DivisionValidators.validators(d, true, true);

            // バリデーションでエラーがあったら新規登録画面に戻す
            if(errors.size() > 0){
                request.setAttribute("division", d);
                request.setAttribute("errors", errors);
                request.setAttribute("_token", request.getSession().getId());

                em.close();

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/divisions/new.jsp");
                rd.forward(request, response);

            } else {

                em.getTransaction().begin();;
                em.persist(d);
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "登録が完了しました。");
                response.sendRedirect(request.getContextPath() + "/divisions/index");
            }

        }
    }

}
