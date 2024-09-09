package com.ohgiraffers.dto;

public class ContactDTO {

    private int contactCode;
    private String contactName;
    private String phoneNumber;
    private String email;
    private String address;
    private String birthday;
    private String groupNumber;
    private String groupName;

    public ContactDTO() {
    }

    public ContactDTO(int contactCode, String contactName, String phoneNumber, String email, String address, String birthday, String groupNumber) {
        this.contactCode = contactCode;
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.groupNumber = groupNumber;
    }

    public String getGroupName()
    {
        return groupName;
    }

    public void setGroupName(String groupName)
    {
        this.groupName = groupName;
    }

    public ContactDTO(String contactName, String phoneNumber, String groupName)
    {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.groupName = groupName;
    }

    public ContactDTO(String contactName, String phoneNumber, String email, String address, String birthday, String groupName) {
        this.contactName = contactName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.groupName = groupName;
    }

    public int getContactCode() {
        return contactCode;
    }

    public void setContactCode(int contactCode) {
        this.contactCode = contactCode;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }


    @Override
    public String toString() {
        return

                "  이름 ='" + contactName + '\'' +
                ", 전화번호 ='" + phoneNumber + '\'' +
                ", 이메일 ='" + email + '\'' +
                ", 주소 ='" + address + '\'' +
                ", 생일 ='" + birthday + '\'' +
                ", 그룹 ='" + groupName + '\''
                ;
    }
}
