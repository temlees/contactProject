package com.ohgiraffers.controller;

import com.ohgiraffers.function.GroupUpdateManager;
import com.ohgiraffers.dao.ContactDAO_YSJ;
import com.ohgiraffers.dto.ContactDTO_YSJ;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static com.ohgiraffers.common.JDBCTemplate.*;



public class ContactController_YSJ {

    GroupUpdateManager groupUpdateManager = new GroupUpdateManager();
    Controller controller = new Controller();
    private ContactDAO_YSJ contactDAO = new ContactDAO_YSJ("src/main/resources/mapper/contact-query.xml");

public void insertcontact(int userCode){  //contact_name,phonenumber, email, address, birthday, groupnumber
    Scanner scr = new Scanner(System.in);
    ContactDTO_YSJ contactDTO = new ContactDTO_YSJ();


    System.out.println("연락처에 추가하고 싶은 이름을 입력 해주세요: ");
    contactDTO.contact_name(scr.nextLine());
    System.out.println("연락처에 추가할 전화번호를 입력 해주세요 ('-'포함) ex)010-1234-5678 : ");
    String phone = scr.nextLine();
    if (isValidPhoneNumber(phone)){
        contactDTO.phonenumber(phone);
    }else {
        System.out.println("* ੈ✩‧₊입력하신 형식이 맞지 않습니다! 되돌아갑니다 * ੈ✩‧₊ ");
        manageContact(userCode);
        return;
    }
    System.out.println("연락처에 추가할 이메일을 입력 해주세요 ex)gangnam@gamil.com : ");
    String email = scr.nextLine();
    if (isValidEmail(email)){
        contactDTO.email(email);
    }else {
        System.out.println("* ੈ✩‧₊입력하신 형식이 맞지 않습니다! 되돌아갑니다 * ੈ✩‧₊ ");
        manageContact(userCode);
        return;
    }
    System.out.println("연락처에 추가할 주소를 입력 해주세요 : ");
    contactDTO.address(scr.nextLine());
    System.out.println("연락처에 추가할 생일을 입력 해주세요 ex)00월 00일, 01월 23일 : ");
    String birth = scr.nextLine();
    if(isValidBirthday(birth)){
        contactDTO.birthday(birth);
    }else {
        System.out.println("* ੈ✩‧₊입력하신 형식이 맞지 않습니다! 되돌아갑니다 * ੈ✩‧₊ ");
        manageContact(userCode);
        return;
    }

    contactDTO.userCode(userCode);


    int result = contactDAO.insertcontact(getConnection(), contactDTO);
    
    if(result == 1)
    {
        // insert 성공했을때
    }
    else
    {
        // insert 실패했을 때
    }


}

public void updatecontact(int userCode) {
    Scanner scr = new Scanner(System.in);    // 바꿀 연락처 (b) 변수만 쓰면된다. 나중에 여유되면 나머지 필요없는 변수 삭제

    ContactDTO_YSJ contactDTO = new ContactDTO_YSJ();
    controller.test2(userCode);

    System.out.println("수정하고 싶은 연락처의 번호를 입력해주세요 ex)010-1234-5678 : ");
    String phone = scr.nextLine();

    if (!contactDAO.isPhonenumberExists(getConnection(), phone)){
        System.out.println("입력하신 번호가 연락처에 존재하지 않습니다.");
        manageContact(userCode);
        return;   //true(전화번호가 존재) 일 경우에 false가 되게해서 다음걸로 넘어가게 하는 코드!
    }

    System.out.println("연락처의 이름을 어떻게 바꾸시겠습니까? : ");

    contactDTO.contact_name(scr.nextLine());

    System.out.println("연락처의 번호를 어떻게 바꾸시겠습니까? ex)010-1234-5678 :  ");
        String updatephone = scr.nextLine();
    if (isValidPhoneNumber(updatephone)){
        contactDTO.phonenumber(updatephone);
    }else {
        System.out.println("* ੈ✩‧₊입력하신 형식이 맞지 않습니다! 되돌아갑니다 * ੈ✩‧₊");
        manageContact(userCode);
        return ;
    }

    System.out.println("연락처의 이메일을 어떻게 바꾸시겠습니까? ex)gangnam@gamil.com : ");
    String email = scr.nextLine();
    if (isValidEmail(email)){
        contactDTO.email(email);
    }else {
        System.out.println("* ੈ✩‧₊입력하신 형식이 맞지 않습니다! 되돌아갑니다 * ੈ✩‧₊ ");
        manageContact(userCode);
        return;
    }

    System.out.println("연락처의 주소를 어떻게 바꾸시겠습니까? ");
    contactDTO.address(scr.nextLine());

    System.out.println("연락처의 생일을 어떻게 바꾸시겠습니까? ex)00월 00일, 01월 23일 ");
    String birth = scr.nextLine();
    if(isValidBirthday(birth)){
        contactDTO.birthday(birth);
    }else {
        System.out.println("* ੈ✩‧₊입력하신 형식이 맞지 않습니다! 되돌아갑니다 * ੈ✩‧₊ ");
        manageContact(userCode);
        return;
    }
    contactDTO.userCode(userCode);

    int result = contactDAO.updatecontact(getConnection(), contactDTO, phone);

    }

    public void deletecontact(int userCode){
    Scanner scr = new Scanner(System.in);
    ContactDTO_YSJ contactDTO = new ContactDTO_YSJ();

        System.out.println("* ੈ✩‧₊ 삭제할 연락처번호를 입력 해주세요 : * ੈ✩‧₊");
        String phone = scr.nextLine();
        if (isValidPhoneNumber(phone)){
            contactDTO.phonenumber(phone);
        }else {
            System.out.println("* ੈ✩‧₊입력하신 형식이 맞지 않습니다! 되돌아갑니다 * ੈ✩‧₊ ");
            manageContact(userCode);
            return;
        }

        int result = contactDAO.deletecontact(getConnection(), contactDTO, userCode);

    }

    public void insertGroup(int userCode){

        Scanner scr = new Scanner(System.in);

        System.out.println("* ੈ✩‧₊ 추가하실 그룹명을 입력해주세요 : * ੈ✩‧₊");
        String newGroup = scr.nextLine();

        int result = contactDAO.insertGroup(getConnection(), newGroup, userCode);


    }

    public void manageContact(int userCode)
    {
        System.out.println("* ੈ✩‧₊ 1. 추가 2. 수정 3. 삭제 4. 이전으로 돌아가기 * ੈ✩‧₊");
        Scanner scr = new Scanner(System.in);
        String choice = scr.nextLine();
        switch (choice)
        {
            case "1":
            case "추가":
                insertcontact(userCode);
                break;
            case "2":
            case "수정":
                updatecontact(userCode);
                break;
            case "3":
            case "삭제":
                deletecontact(userCode);
                break;
            case "4" : break;
            default:
                System.out.println("* ੈ✩‧₊ 잘못된 입력입니다. * ੈ✩‧₊");
                break;
        }
    }

    public void manageGroup(int userCode)
    {
        System.out.println("* ੈ✩‧₊ 1. 추가 2. 삭제 3. 그룹 내에서 연락처 추가, 삭제 4. 이전으로 되돌아가기 * ੈ✩‧₊");
        Scanner scr = new Scanner(System.in);
        String choice = scr.nextLine();

        switch (choice)
        {
            case "1":
            case "추가":
                insertGroup(userCode);
                break;
            case "2":
            case "삭제":
                groupUpdateManager.deleteGroup(userCode);
                break;
            case "3":
            case "그룹 내 연락처 수정":
                groupUpdateManager.updateGroup(userCode);
                break;
            case "4": break;
            default:
                System.out.println("* ੈ✩‧₊ 잘못된 입력입니다. * ੈ✩‧₊");
                break;
        }

    }



    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^\\d{3}-\\d{4}-\\d{4}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        Matcher matcher = phonePattern.matcher(phoneNumber);
        return matcher.matches();
    }

    // 이메일 유효성 검사
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$";
        Pattern emailPattern = Pattern.compile(emailRegex);
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    // 생일 유효성 검사
    public static boolean isValidBirthday(String birthday) {
        String birthdayRegex = "^(0[1-9]|1[0-2])월 (0[1-9]|[12][0-9]|3[01])일$"; // ex) 08월 13일
        Pattern birthdayPattern = Pattern.compile(birthdayRegex);
        Matcher matcher = birthdayPattern.matcher(birthday);
        return matcher.matches();
    }


}
