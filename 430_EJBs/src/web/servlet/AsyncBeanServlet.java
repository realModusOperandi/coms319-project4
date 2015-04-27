package web.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.ejb.MyAsyncBean;

@WebServlet("/AsyncBeanServlet")
public class AsyncBeanServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @EJB
    MyAsyncBean bean;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String operation = request.getParameter("operation");

        if ("putDataAsync".equals(operation)) {
            long start = System.currentTimeMillis();
            bean.putDataAsync();
            request.setAttribute("putDataAsync", (System.currentTimeMillis() - start) + " ms");
        }
        else {
            long start = System.currentTimeMillis();
            bean.putDataBlocking();
            request.setAttribute("putDataBlocking", (System.currentTimeMillis() - start) + " ms");
        }

        request.setAttribute("BEAN_ID", bean.toString());

        request.getRequestDispatcher("asyncbeans.jsp").forward(request, response);
    }
}
