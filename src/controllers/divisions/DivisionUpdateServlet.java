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
 * Servlet implementation class DivisionUpdateServlet
 */
@WebServlet("/divisions/update")
public class DivisionUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DivisionUpdateServlet() {
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
            Division d = em.find(Division.class, request.getSession().getAttribute("id"));

            // バリデーション用のフラグ設定
            Boolean code_duplicate_flag = true;
            Boolean name_duplicate_flag = true;

            // 入力値と取得値が一致していたらfalseに変更
            if(d.getCode().equals(request.getParameter("code"))){
                code_duplicate_flag = false;

            } else {
                d.setCode(request.getParameter("code"));

            }

            if(d.getName().equals(request.getParameter("name"))){
                name_duplicate_flag = false;

            } else {
                d.setName(request.getParameter("name"));

            }

            Timestamp currentTime = new Timestamp(System.currentTimeMillis());
            d.setUpdated_at(currentTime);
            d.setDelete_flag(0);

            List<String> errors = DivisionValidators.validators(d, code_duplicate_flag, name_duplicate_flag);


            if(errors.size() > 0){

                em.close();

                request.setAttribute("division", d);
                request.setAttribute("errors", errors);
                request.setAttribute("_token", request.getSession().getId());

                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/divisions/edit.jsp");
                rd.forward(request, response);

            } else {

                em.getTransaction().begin();
                em.getTransaction().commit();
                em.close();

                request.getSession().setAttribute("flush", "更新が完了しました");
                request.getSession().removeAttribute("id");
                response.sendRedirect(request.getContextPath() + "/divisions/index");

            }

        }

    }

}
