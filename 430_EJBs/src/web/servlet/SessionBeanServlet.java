package web.servlet;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.ejb.MyStatefulBean;
import web.ejb.MyStatelessBean;

@WebServlet("/SessionBeanServlet")
public class SessionBeanServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Use a stateless bean
        if ("stateless".equals(request.getParameter("beantype"))) {
            MyStatelessBean statelessBean = (MyStatelessBean) getBean(request, "MyStatelessBean");

            statelessBean.useBean();

            request.setAttribute("RETURN_STATELESS", statelessBean.getUseCount());
            request.setAttribute("BEAN_ID", "Used bean: " + statelessBean.toString());
        }
        // Use a stateful bean
        else if ("stateful".equals(request.getParameter("beantype"))) {
            MyStatefulBean statefulBean = (MyStatefulBean) getBean(request, "MyStatefulBean");

            statefulBean.useBean();

            request.setAttribute("RETURN_STATEFUL", statefulBean.getUseCount());
            request.setAttribute("BEAN_ID", "Used bean: " + statefulBean.toString());
        }
        // restart the session
        else {
            request.getSession().invalidate();
        }

        request.getRequestDispatcher("sessionbeans.jsp").forward(request, response);
    }

    /**
     * Given a request and a bean name, checks if there is a bean stored in the session already.
     * If not, it looks up a new bean from the ejb container and stores it in the session.
     */
    private Object getBean(HttpServletRequest request, String beanName) {
        Object bean = request.getSession().getAttribute(beanName);
        if (bean == null) {
            try {
                bean = new InitialContext().lookup("java:app/430_EJBs/" + beanName);
                request.getSession().setAttribute(beanName, bean);
            } catch (NamingException e) {
            }
        }
        return bean;
    }
}
