package com.ohgiraffers.function;


import com.ohgiraffers.dto.ContactDTO;
import com.ohgiraffers.dto.GroupContactDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.close;


public class FindPhoneNumber {

    /*      - **연락처 목록 조회**
            - 저장된 모든 연락처를 목록 형태로 조회할 수 있어야 한다. //리스트
            !-- 목록은 이름, 전화번호, 생일, 그룹을 기준으로 정렬할 수 있다.
            - 연락처는 그룹별로 조회할 수 있는 기능을 제공한다.
     */

    private Properties prop = new Properties();

    public FindPhoneNumber(String url) {

        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public List<ContactDTO> findPhoneNumbers1(Connection con, int userCode, String prefer) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<ContactDTO> categoryList = new ArrayList<>();
        try {
            String query = prop.getProperty("findsort")+prefer;
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, userCode);

            rset = pstmt.executeQuery();

            System.out.println("* ੈ✩‧₊ 전화번호부 * ੈ✩‧₊");
            System.out.println();

            while (rset.next()) {
                ContactDTO contactDTO = new ContactDTO(rset.getString("contact_name"), rset.getString("phonenumber"), rset.getString("email"),rset.getString("address"),rset.getString("birthday"), (rset.getString("groupname") == null ? " " : rset.getString("groupname")));
                categoryList.add(contactDTO);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }


        return categoryList;

    }

    public List<ContactDTO> findsort(Connection con, int userCode, String sort) {

        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<ContactDTO> categoryList =  new ArrayList<>();
        try {
            String query = prop.getProperty("findsort");
            pstmt = con.prepareStatement(query + sort);
            pstmt.setInt(1, userCode);
            rset = pstmt.executeQuery();


            System.out.println("* ੈ✩‧₊ 전화번호부 * ੈ✩‧₊");

            while (rset.next()) {
                ContactDTO contactDTO = new ContactDTO(rset.getInt("contact_code"),rset.getString("contact_name"), rset.getString("phonenumber"),
                        rset.getString("email"),rset.getString("address"),rset.getString("birthday"), rset.getString("groupname"));
                categoryList.add(contactDTO);

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }

        return categoryList;
    }




    public List<GroupContactDTO> groupFindPhoneNumber1(Connection con, String groupName) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        List<GroupContactDTO> contactInGroup = null;



        try {
            String query = prop.getProperty("findGroup");
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, groupName);
            rset = pstmt.executeQuery();
            contactInGroup = new ArrayList<>();





                while (rset.next()) {

                    GroupContactDTO groupContactDTO = new GroupContactDTO(rset.getString("contact_name"),
                            rset.getString("phonenumber"), rset.getString("birthday"), rset.getString("groupname"));

                    contactInGroup.add(groupContactDTO);


                }




        } catch (SQLException e) {
            System.out.println();
            System.out.println("해당 그룹을 찾을 수 없습니다.");
            System.out.println();

        } finally {
            close(con);
            close(pstmt);
            close(rset);
        }

        return contactInGroup;
    }



}












