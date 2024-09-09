package com.ohgiraffers.dao;

import com.ohgiraffers.dto.ContactDTO;
import com.ohgiraffers.dto.GroupDTO;
import com.ohgiraffers.dto.ContactDTO_YSJ;

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

public class ContactDAO_YSJ {

    private Properties prop = new Properties();

    public ContactDAO_YSJ(String url){
        try {
            prop.loadFromXML(new FileInputStream(url));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int insertcontact(Connection con, ContactDTO_YSJ contactDTO){

        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("insertcontact");


        try {

            pstmt = con.prepareStatement(query); // contact_name,phonenumber, email, address, birthday, groupnumber
            pstmt.setString(1, contactDTO.getContactName());
            pstmt.setString(2, contactDTO.getPhonenumber());
            pstmt.setString(3, contactDTO.getEmail());
            pstmt.setString(4, contactDTO.getAddress());
            pstmt.setString(5, contactDTO.getBirthday());
            pstmt.setInt(6, contactDTO.getUser_code());

            result = pstmt.executeUpdate();

            System.out.println();
            System.out.println("* ੈ✩‧₊ 연락처가 추가되었습니다. * ੈ✩‧₊");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("잘못된 값을 입력하셨습니다.");
        } finally {
            close(con);
            close(pstmt);
        }
        return result;
    }

    public int updatecontact(Connection con, ContactDTO_YSJ contactDTO, String phone){

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/contact-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("updatecontact"));


            pstmt.setString(1,contactDTO.getContactName());
            pstmt.setString(7, phone);
            pstmt.setString(2, contactDTO.getPhonenumber());
            pstmt.setString(3, contactDTO.getEmail());
            pstmt.setString(4, contactDTO.getAddress());
            pstmt.setString(5, contactDTO.getBirthday());
            pstmt.setInt(6, contactDTO.getUser_code());

            result = pstmt.executeUpdate();

            if (result == 1){
                System.out.println("* ੈ✩‧₊ 연락처 변경 성공 * ੈ✩‧₊");
            } else {
                System.out.println("* ੈ✩‧₊ 연락처 변경에 실패하셨습니다. 수정할 번호를 제대로 입력하셨는지 확인해주세요! * ੈ✩‧₊");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            close(con);
            close(pstmt);

        }
        return result;
    }

    public boolean isPhonenumberExists(Connection con, String phonenumber){
        String query = prop.getProperty("countphonenumber");

        try {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, phonenumber);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1) > 0; //핸드폰번호를 카운트해서 0보다 크면 updatecontact 메소드로 반환
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;  // rs값이 없을때 false 인데, 문법상 적어준것! (딱히 의미는 x) 어차피 위 if 문에서 다 해결됨!

    }


    public int deletecontact(Connection con, ContactDTO_YSJ contactDTO, int userCode){

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/contact-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("deletecontact"));
            pstmt.setString(1, contactDTO.getPhonenumber());
            pstmt.setInt(2, userCode);

            result = pstmt.executeUpdate();

            if (result == 1){
                System.out.println("* ੈ✩‧₊ 연락처 제거에 성공하셨습니다. * ੈ✩‧₊");
            }else {
                System.out.println("* ੈ✩‧₊ 연락처 제거에 실패하셨습니다. * ੈ✩‧₊");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }
    return result;
    }

    public int insertGroup(Connection con, String group, int userCode) {

        PreparedStatement pstmt = null;
        int result = 0;

        String query = prop.getProperty("insertGroup");

        try {
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, group);
            pstmt.setInt(2, userCode);

            result = pstmt.executeUpdate();

            if(result == 1){
                System.out.println("* ੈ✩‧₊ 그룹 추가가 완료되었습니다.* ੈ✩‧₊");

            }else{

                System.out.println("* ੈ✩‧₊ 그룹 추가를 실패하였습니다. * ੈ✩‧₊");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally
        {
            close(con);
            close(pstmt);
        }
        return result;

    }
    public int deleteGroup(Connection con, String groupName, int userCode){
        PreparedStatement pstmt = null;
        int result = 0;

        String query = prop.getProperty("deleteGroup");

        try {
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, groupName);
            pstmt.setInt(2, userCode);

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(con);
            close(pstmt);

        }return result;

    }

    public List<ContactDTO> getAllContacts(Connection con, int userCode)
    {
        List<ContactDTO> userContacts = new ArrayList<ContactDTO>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = prop.getProperty("printContactList");

        try
        {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, userCode);

            rs = pstmt.executeQuery();
            while(rs.next())
            {
                ContactDTO contactDTO = new ContactDTO();
                contactDTO.setContactName(rs.getString("contact_name"));
                contactDTO.setPhoneNumber(rs.getString("phonenumber"));
                if(rs.getString("groupname") != null)
                {
                    contactDTO.setGroupName(rs.getString("groupname"));
                }
                else
                {
                    contactDTO.setGroupName("그룹없음");
                }

                userContacts.add(contactDTO);
            }
        }
        catch (SQLException e)
        {
            System.out.println("* ੈ✩‧₊ 연락처를 찾을 수 없습니다. * ੈ✩‧₊");
        }
        finally
        {
            close(con);
            close(pstmt);
            close(rs);
        }


        return userContacts;
    }

    public List<GroupDTO> getAllGroups(Connection con, int userCode)
    {
        List<GroupDTO> groups = new ArrayList<>();

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String query = prop.getProperty("printGroupList");

        try
        {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, userCode);

            rs = pstmt.executeQuery();
            while(rs.next())
            {
                groups.add(new GroupDTO(rs.getInt("groupnumber"), rs.getString("groupname")));
            }
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        finally
        {
            close(con);
            close(pstmt);
            close(rs);
        }

        return groups;
    }

    public int changeGroupNumberOfContact(Connection con, int groupNum, int userCode, String phoneNum)
    {
        PreparedStatement pstmt = null;
        int result = 0;
        String query = prop.getProperty("changeGroupNumber");

        try
        {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, groupNum);
            pstmt.setInt(2, userCode);
            pstmt.setString(3, phoneNum);
            result = pstmt.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("* ੈ✩‧₊" + phoneNum + " 의 그룹 설정에 실패했습니다. * ੈ✩‧₊");
            return -1;

        }finally{
            close(con);
            close(pstmt);
        }
        return result;
    }


    public int updateForDeleteGroup(Connection con, ContactDTO_YSJ contactDTO, int userCode){

        // 그룹 삭제를 하기 위해 연락처의 그룹 정보를 null로 설정

        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();


        try {
            prop.loadFromXML(new FileInputStream("src/main/resources/mapper/contact-query.xml"));
            pstmt = con.prepareStatement(prop.getProperty("updatefordeletegroup"));

            pstmt.setString(1, contactDTO.getGroupname());
            pstmt.setInt(2, userCode);

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(pstmt);
        }

    return result;

    }

    public List<ContactDTO> printContactsInGroup(Connection con, int groupNum, int userCode)
    {
        //그룹
        List<ContactDTO> contacts = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String query = prop.getProperty("getContactsInGroup");

        try
        {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, userCode);
            pstmt.setInt(2, groupNum);

            rs = pstmt.executeQuery();

            while(rs.next())
            {
                ContactDTO contact = new ContactDTO();
                contact.setContactName(rs.getString("contact_name"));
                contact.setPhoneNumber(rs.getString("phonenumber"));
                contact.setGroupName(rs.getString("groupname"));
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
            close(pstmt);
            close(rs);
        }

        return contacts;
    }

    public int deleteContactInGroup(Connection con, int groupNum, String phoneNum, int userCode)
    {
        // 그룹 내에서 연락처 삭제 => 해당 그룹에 속해있던 연락처들의 그룹 정보를 null로 바꿔줌
        
        PreparedStatement pstmt = null;
        int result = 0;
        
        String query = prop.getProperty("deleteContactInGroup");

        try
        {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, groupNum);
            pstmt.setString(2, phoneNum);
            pstmt.setInt(3, userCode);
            result = pstmt.executeUpdate();
            
        }
        catch (SQLException e)
        {
            return -1;
        }
        finally
        {
            close(con);
            close(pstmt);
        }

        return result;
    }

}
