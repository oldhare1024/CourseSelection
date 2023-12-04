package rjgc.body;

import java.sql.*;

public class DBUtil {
    public static Statement st;
    public static ResultSet rs;
    static Connection con;
    static PreparedStatement ps;
    static String url = "jdbc:mysql://localhost:3306/studb?useUnicode=true&characterEncoding=UTF-8";
    static String name = "root";
    static String pwd = "110120";

    public static void initst() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, name, pwd);
            st = con.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void initps(String sql) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, name, pwd);
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void closeDB() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (st != null) {
                st.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
}
