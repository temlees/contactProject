package com.ohgiraffers.dto;

public class ContactDTO_YSJ {

    private String contactName;

    private String phonenumber;

    private String email;

    private String address;

    private String birthday;

    private int groupnumber;

    private int userCode;

    private String groupname;


    public ContactDTO_YSJ() {
    }

    public ContactDTO_YSJ(String groupname) {
        this.groupname = groupname;
    }

    public ContactDTO_YSJ(String contactName, String phonenumber, String email, String address, String birthday, int user_code) {
        this.contactName = contactName;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.userCode = user_code;
    }

    public ContactDTO_YSJ contact_name(String name){
        this.contactName = name;
        return this;
    }



    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public ContactDTO_YSJ groupname (String groupname){
        this.groupname = groupname;
        return this;
    }


    public ContactDTO_YSJ phonenumber (String phonenumber){
        this.phonenumber = phonenumber;
        return this;
    }

    public ContactDTO_YSJ email (String email){
        this.email = email;
        return this;
    }

    public ContactDTO_YSJ address (String address){
        this.address = address;
        return this;
    }

    public ContactDTO_YSJ birthday (String birthday){
        this.birthday = birthday;
        return this;
    }

    public ContactDTO_YSJ groupnumber (int groupnumber){
        this.groupnumber = groupnumber;
        if(groupnumber <= 0){
            System.out.println("다시입력해주세요");}
        return this;
    }

    public ContactDTO_YSJ userCode(int userCode){
        this.userCode = userCode;
        return this;
    }

    public int getUser_code() {
        return userCode;
    }

    public String getContactName() {
        return contactName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthday() {
        return birthday;
    }

    public int getGroupnumber() {
        return groupnumber;
    }

    @Override
    public String toString() {
        return "ContactDTO_YSJ{" +
                "groupname='" + groupname + '\'' +
                '}';
    }

}
