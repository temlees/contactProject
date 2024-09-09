package com.ohgiraffers.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class UserPreferDAO
{
    private Properties prop = new Properties();

    public UserPreferDAO(String url)
    {
        try
        {
            prop.loadFromXML(new FileInputStream(url));
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void saveUserPrefer(Connection con, String value, int userCode)
    {
        PreparedStatement ps = null;
        int result = 0;
        String query = null;

        query = prop.getProperty("orderUpdatePrefer");

        try
        {
            ps = con.prepareStatement(query);
            ps.setString(1, value);
            ps.setInt(2, userCode);

            result = ps.executeUpdate();

            System.out.println(result == 1 ? "업데이트 성공" : "업데이트에 실패했습니다.");
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            close(con);
            close(ps);
        }

    }

    public String saveUserPrefer1(Connection con, int userCode)
    {
        PreparedStatement ps = null;
        ResultSet result = null;
        String query = null;
        String prefer = null;

        query = prop.getProperty("savePrefer");

        try
        {
            ps = con.prepareStatement(query);
            ps.setInt(1, userCode);

            result = ps.executeQuery();

            while (result.next()){
                prefer = result.getString("prefer");
            }


        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }finally{
            close(con);
            close(ps);
        }

        return prefer;

    }


}
