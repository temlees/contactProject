package com.ohgiraffers.dto;

public class UserDTO
{
    private int userCode;
    private String userName;
    private String id;
    private String pwd;
    private String prefer;  // 사용자 선호 정렬 타입

    public UserDTO()
    {
    }

    public UserDTO(String userName, String id, String pwd, String prefer)
    {
        this.userName = userName;
        this.id = id;
        this.pwd = pwd;
        this.prefer = prefer;
    }

    public UserDTO(int userCode, String userName, String id, String pwd, String prefer)
    {
        this.userCode = userCode;
        this.userName = userName;
        this.id = id;
        this.pwd = pwd;
        this.prefer = prefer;
    }

    public int getUserCode()
    {
        return userCode;
    }

    public void setUserCode(int userCode)
    {
        this.userCode = userCode;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getPwd()
    {
        return pwd;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }

    public String getPrefer()
    {
        return prefer;
    }

    public void setPrefer(String prefer)
    {
        this.prefer = prefer;
    }

    @Override
    public String toString()
    {
        return "UserDTO{" +
                "userCode=" + userCode +
                ", userName='" + userName + '\'' +
                ", id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                ", prefer='" + prefer + '\'' +
                '}';
    }
}
