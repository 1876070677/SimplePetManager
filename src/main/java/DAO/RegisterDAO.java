package DAO;

import java.sql.*;

public class RegisterDAO {
    public int ownerCheck(String name, String number) {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnect.getConnection();

            String sql = "SELECT oId, oName, number FROM owner WHERE number = ?";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, number);
            rs = psmt.executeQuery();

            // number이 UNIQUE KEY 이므로 결과는 0개 아니면 1개이다.
            if (rs.next()) {
                // oId를 넘겨준다
                int oId = rs.getInt(1);
                String oName = rs.getString(2);
                if (oName.equals(name))
                    return rs.getInt(1);
                else
                    // 전화번호는 존재하나 사용자 이름이 다를경우 등록 불가능 = 중복확인
                    return -2;
            } else {
                // 검색 결과가 없을 경우
                    return -1;
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
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return 0;
    }
    public void registerOwnerAndPet(String pName, String birth, String kind, String oName, String oPhone) {
        // 동시에 두개의 테이블에 접근 필요 = 트랜잭션
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        int id = -1;

        try {
            conn = DBConnect.getConnection();
            conn.setAutoCommit(false);

            String sql1 = "INSERT INTO owner(oName, number) VALUES (?, ?)";
            psmt = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            psmt.setString(1, oName);
            psmt.setString(2, oPhone);
            psmt.executeUpdate();

            // auto_increment로 생성된 oId값을 가져온다.
            rs = psmt.getGeneratedKeys();
            if(rs.next()) {
                id = rs.getInt(1);
            }

            String sql2 = "INSERT INTO pet(pName, birth, kind, oId) VALUES(?, ?, ?, ?)";
            psmt = conn.prepareStatement(sql2);
            psmt.setString(1, pName);
            psmt.setString(2, birth);
            psmt.setString(3, kind);
            psmt.setInt(4, id);
            psmt.executeUpdate();

            conn.commit();
        } catch(SQLException e) {
            try {
                if (conn != null)
                    conn.rollback();
            } catch(SQLException e2) {
                e2.printStackTrace();
            }
        } finally {
            try {
                if (psmt != null)
                    psmt.close();
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void registerPet(String petName, String petBirth, String kind, int oId) {
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = DBConnect.getConnection();

            String sql = "INSERT INTO pet(pName, birth, kind, oId) VALUES(?, ?, ?, ?)";
            psmt = conn.prepareStatement(sql);
            psmt.setString(1, petName);
            psmt.setString(2, petBirth);
            psmt.setString(3, kind);
            psmt.setInt(4, oId);
            psmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if (psmt != null)
                    psmt.close();
                if (conn != null)
                    conn.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
