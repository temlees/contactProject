package com.ohgiraffers.dto;

public class GroupContactDTO {

    private String contact_Name;
    private String phonenumber;
    private String email;
    private String address;

    public GroupContactDTO() {
    }

    public GroupContactDTO(String contact_Name, String phonenumber, String email, String address) {
        this.contact_Name = contact_Name;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
    }

    public String getContact_Name() {
        return contact_Name;
    }

    public void setContact_Name(String contact_Name) {
        this.contact_Name = contact_Name;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return
                "이름='" + contact_Name + '\'' +
                ", 핸드폰 번호 : " + phonenumber +
                ", 이메일 : '" + email + '\'' +
                ", 그룹 이름 : '" + address + '\'' ;
    }
}
