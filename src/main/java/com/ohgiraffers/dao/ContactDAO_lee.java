package com.ohgiraffers.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class ContactDAO_lee {

    private Properties prop = new Properties();

    public ContactDAO_lee(String url) {
        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int totalCount(Connection con,int a){
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        int result = 0;
        String query = prop.getProperty("countAllContact");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, a);
            rset = pstmt.executeQuery();

            if(rset.next()){
                result = rset.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }
        return result;
    }

    public List<Map<String, Integer>> groupByPhoneNumber(Connection con,int a){
            PreparedStatement pstmt = null;
            ResultSet rset = null;
            List<Map<String, Integer>> result = new ArrayList<>();
            String query = prop.getProperty("groupCount");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, a);
            rset = pstmt.executeQuery();

            while(rset.next()){
                Map<String, Integer> map = new HashMap<>();
                map.put(rset.getString("groupname"), rset.getInt("count(*)"));
                result.add(map);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }
        return result;

    }


//    public List selectLastContact(Connection con){
//        List result = new ArrayList();
//        PreparedStatement pstmt = null;
//        ResultSet rset = null;
//        String query = prop.getProperty("selectLastContact");
//                con.prepareStatement();
//    }

    public List selectLastContact(Connection con,int a){
        List result = new ArrayList();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("selectLastContact");
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, a);
              rset = pstmt.executeQuery();
              if(rset.next()){
                  result.add(rset.getString(2)+" "+  rset.getString(3)+" "+  rset.getString(4)
                  +" "+  rset.getString(5)+" "+  rset.getString(6)+" "+ rset.getString(7) );
              }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);
            close(rset);
        }
        return result;
    }

}



