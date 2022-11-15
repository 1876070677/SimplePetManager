package Controller;

import DAO.FindDAO;
import DAO.RegisterDAO;
import Domain.Pet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FindController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String query = request.getParameter("query");

        List<Pet> petList = null;
        FindDAO findDAO = new FindDAO();
        petList = findDAO.findPet(query);

        request.setAttribute("petList", petList);
        request.setAttribute("result", "1");

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/jsp/Find.jsp");
        requestDispatcher.forward(request, response);
    }
}
