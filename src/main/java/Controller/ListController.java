package Controller;

import DAO.ListDAO;
import Domain.Pet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ListController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //filter 조건
        String filter = request.getParameter("filter");
        int filtering = 0;
        if (filter != null) {
            filtering = Integer.parseInt(filter);
        }

        //List를 불러와야한다.
        List<Pet> petList = null;
        ListDAO listDAO = new ListDAO();

        if (filtering == 1) {
            petList = listDAO.getSortedNameList();
        }
        else if (filtering == 2) {
            petList = listDAO.getSortedOwnerList();
        }
        else {
            petList = listDAO.getAllList();
        }

        request.setAttribute("petList", petList);
        request.setAttribute("filter", filtering);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/List.jsp");
        requestDispatcher.forward(request, response);
    }
}
