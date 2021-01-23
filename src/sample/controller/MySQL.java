package sample.controller;

import java.sql.*;

public class MySQL {
    private Connection con;

    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://cdb-geebna1a.cd.tencentcdb.com:10053/Office?user=HXhlx&password=HXhlx19990627");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet Select(String str, Object... parm) {
        try {
            PreparedStatement sql = con.prepareStatement(str);
            for (int i = 1; i <= parm.length; ++i) sql.setObject(i, parm[i - 1]);
            return sql.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public int Operator(String str, Object... parm) {
        try {
            PreparedStatement sql = con.prepareStatement(str);
            for (int i = 1; i <= parm.length; ++i) sql.setObject(i, parm[i - 1]);
            return sql.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
}
