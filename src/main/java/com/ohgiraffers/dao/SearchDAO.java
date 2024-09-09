package com.ohgiraffers.dao;

import com.ohgiraffers.dto.ContactDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class SearchDAO
{
    private Properties prop = new Properties();

    public SearchDAO(String url)
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

    public List<ContactDTO> search(Connection con, String condition, String searchVal, int userCode)
    {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = null;

        List<ContactDTO> contacts = new ArrayList<>();

        if(condition.equals("1") || condition.equals("이름"))
        {
            query = prop.getProperty("searchByName");
        }
        else if(condition.equals("2") || condition.equals("전화번호"))
        {
            query = prop.getProperty("searchByPhoneNumber");
        }
        else if(condition.equals("3") || condition.equals("이메일"))
        {
            query = prop.getProperty("searchByEmail");
        }
        else if(condition.equals("4") || condition.equals("주소"))
        {
            query = prop.getProperty("searchByAddress");
        }
        else
        {
            return contacts;
        }

        try
        {
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + searchVal + "%");
            ps.setInt(2, userCode);

            rs = ps.executeQuery();

            while(rs.next())
            {
                ContactDTO contact = new ContactDTO();
                contact.setContactName(rs.getString("contact_name"));
                contact.setPhoneNumber(rs.getString("phonenumber"));
                contact.setEmail(rs.getString("email"));
                contact.setAddress(rs.getString("address"));
                contact.setBirthday(rs.getString("birthday"));
                contact.setGroupName(rs.getString("groupname") == null ? " " : rs.getString("groupname"));
                contacts.add(contact);
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

        return contacts;
    }
}
