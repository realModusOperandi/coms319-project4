package web.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.ejb.PersonBean;

@WebServlet("/PersonServlet")
public class PersonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    PersonBean ub;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String function = request.getParameter("FUNCTION");
        if ("ADDUSER".equalsIgnoreCase(function))
            doAddUser(request, response);
        else if ("EDITUSER".equalsIgnoreCase(function))
            doEditUser(request, response);
        else {
            request.setAttribute("RETURN_MESSAGE", "Invalid function: " + function);
            request.getRequestDispatcher("entitybeans.jsp").forward(request, response);
        }
    }

    protected void doAddUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        try {
            ub.addPerson(username, email);
            request.setAttribute("RETURN_MESSAGE", "User " + username + " has been added.");
        } catch (Exception e) {
            request.setAttribute("RETURN_MESSAGE", "User could not be added because username or email already exists.");
        } finally {
            request.getRequestDispatcher("entitybeans.jsp").forward(request, response);
        }
    }

    protected void doEditUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String passwd = request.getParameter("passwd");
        try {
            ub.updatePerson(username, email, passwd);
            request.setAttribute("RETURN_MESSAGE", "User " + username + " has been updated.");
        } catch (Exception e) {
            request.setAttribute("RETURN_MESSAGE", "User could not be updated.");
        } finally {
            request.getRequestDispatcher("entitybeans.jsp").forward(request, response);
        }
    }
}
