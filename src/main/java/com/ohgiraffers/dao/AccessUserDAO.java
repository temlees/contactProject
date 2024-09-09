package com.ohgiraffers.dao;

import com.ohgiraffers.dto.UserDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class AccessUserDAO
{
    //DB랑 연결해주는 역할만
    private Properties prop = new Properties();

    public AccessUserDAO(String url)
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

    public UserDTO getUserInfo(Connection con, String id, String pwd)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = prop.getProperty("getUserInfo");

        UserDTO user = null;

        try
        {
            ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.setString(2, pwd);
            rs = ps.executeQuery();

            while(rs.next())
            {
                user = new UserDTO(rs.getInt("user_code"), rs.getString("user_name"), rs.getString("id"), rs.getString("pwd"), rs.getString("prefer"));
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            close(con);
            close(ps);
            close(rs);
        }

        return user;
    }

    public void signup(Connection con, String name, String id, String pwd)
    {
        PreparedStatement ps = null;
        int result = 0;
        String query = prop.getProperty("setNewUser");

        try
        {
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, id);
            ps.setString(3, pwd);
            result = ps.executeUpdate();

            System.out.println(result == 1 ? "가입 완료" : "가입 실패");
        }
        catch (SQLException e)
        {
            System.out.println("가입에 실패했습니다. 제대로 입력하셨나요?");
        }
        finally
        {
            close(con);
            close(ps);
        }

    }
    public String dupSign(Connection con, String id){
        PreparedStatement ps = null;
        ResultSet rset = null;
        String query = prop.getProperty("dupSign");

        String a = null;

        try {
            ps = con.prepareStatement(query);
            ps.setString(1, id);

            rset = ps.executeQuery();

            while(rset.next()){

                a = rset.getString("id");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally{
            close(con);
            close(ps);
            close(rset);
        }return a;


    }
}
