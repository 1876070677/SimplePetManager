package DAO;

import Domain.Pet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FindDAO {

    public List<Pet> findPet(String query) {
        List<Pet> petList = new ArrayList<Pet>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT pName, birth, kind, oName, Number FROM pet LEFT JOIN owner on pet.oId = owner.oId WHERE pName LIKE '%"+ query +  "%' ORDER BY pId DESC";
            psmt = conn.prepareStatement(sql);
            rs = psmt.executeQuery();

            while(rs.next()) {
                petList.add(new Pet(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (psmt != null)
                    psmt.close();
                if (conn != null)
                    conn.close();
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }

        return petList;
    }
}
