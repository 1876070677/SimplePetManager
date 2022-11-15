package Controller;

import DAO.RegisterDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegisterController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("get");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String petName = request.getParameter("petName");
        String petBirth = request.getParameter("petBirth");
        String kind = request.getParameter("kind");
        String ownerName = request.getParameter("ownerName");
        String ownerPhone = request.getParameter("ownerPhone");

        //값 넘어오는거 확인
        System.out.println(petName +", " + petBirth +", " + kind +", " + ownerName +", " + ownerPhone);

        RegisterDAO registerDAO = new RegisterDAO();

        int oid = registerDAO.ownerCheck(ownerName, ownerPhone);

        if (oid > -1) {
            // 주인이 이미 존재할 경우, 받아온 oid를 이용해 펫만 등록
            registerDAO.registerPet(petName, petBirth, kind, oid);
        } else if (oid == -1){
            // 주인이 존재하지 않는 경우, 주인과 펫을 전부 등록
            registerDAO.registerOwnerAndPet(petName, petBirth, kind, ownerName, ownerPhone);
        } else {
            //중복확인에 걸림 동물등록 실페
            response.sendRedirect("/jsp/Register.jsp?res=fail");
            return;
        }
        // 등록이 끝나고 메인 페이지로 이동
        response.sendRedirect("/");
    }
}
