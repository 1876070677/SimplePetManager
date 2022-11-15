package DAO;

import Domain.Pet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListDAO {

    public List<Pet> getAllList() {
        List<Pet> petList = new ArrayList<Pet>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT pName, birth, kind, oName, Number FROM pet LEFT JOIN owner on pet.oId = owner.oId ORDER BY pId DESC";
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

    public List<Pet> getSortedNameList() {
        List<Pet> petList = new ArrayList<Pet>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT pName, birth, kind, oName, Number FROM pet LEFT JOIN owner on pet.oId = owner.oId ORDER BY pName";
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

    public List<Pet> getSortedOwnerList() {
        List<Pet> petList = new ArrayList<Pet>();
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.getConnection();
            String sql = "SELECT pName, birth, kind, oName, Number FROM pet LEFT JOIN owner on pet.oId = owner.oId ORDER BY oName";
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
