package Servlet;

import DAO.Dao;
import Data.Person;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GeneralServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameterMap().isEmpty()) {
            request.getRequestDispatcher("table.jsp").forward(request, response);
        }
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "edit":
                    request.setAttribute("user", new Dao().getUserById(Integer.parseInt(request.getParameter("id"))));
                    request.getRequestDispatcher("edit.jsp").forward(request, response);
                    break;
                case "save":
                    Person u;
                    String name = request.getParameter("name");
                    String surname = request.getParameter("surname");
                    String email = request.getParameter("email");
                    Integer age = Integer.parseInt(request.getParameter("age"));
                    Integer numb = Integer.parseInt(request.getParameter("number"));
                    Integer series = Integer.parseInt(request.getParameter("series"));
                    if (request.getParameter("id") == null) {
                        u = new Person(name, email, surname, age, numb, series);
                        new Dao().addUser(u);
                    } else {
                        Integer id1 = Integer.parseInt(request.getParameter("id"));
                        u = new Person(id1, name, email, surname, age, numb, series);
                        new Dao().updateUser(u);
                    }
                    response.sendRedirect("/");
                    break;
                case "delete":
                    if (request.getParameter("id").length() > 1) {
                        String id = request.getParameter("id");
                        String chunks[] = id.trim().split(" ");
                        for (int i = 0; i < chunks.length; i++) {
                            new Dao().deleteUser(Integer.parseInt(chunks[i]));
                        }
                    } else {
                        new Dao().deleteUser(Integer.parseInt(request.getParameter("id")));
                    }
                    if (request.getParameter("id") == null){
                        response.sendRedirect("/");
                    }
                    response.sendRedirect("/");
                    break;
            }
        }
    }
}
