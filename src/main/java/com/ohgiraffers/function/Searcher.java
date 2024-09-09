package com.ohgiraffers.function;

import com.ohgiraffers.dao.SearchDAO;
import com.ohgiraffers.dto.ContactDTO;

import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class Searcher
{
    //이름, 전화번호, 이메일, 주소 등을 기준으로 연락처를 검색할 수 있어야 한다.
    //검색 결과는 리스트 형태로 표시되며, 검색 조건에 따라 필터링할 수 있다.
    SearchDAO searchDAO = new SearchDAO("src/main/resources/mapper/contact-query.xml");
    Scanner sc = new Scanner(System.in);

    public void search(int userCode)
    {
        System.out.println();
        System.out.println("***** 연락처 검색 *****");
        System.out.println("1. 이름으로 검색");
        System.out.println("2. 전화번호로 검색");
        System.out.println("3. 이메일로 검색");
        System.out.println("4. 주소로 검색");
        System.out.println("0. 메뉴로 돌아가기");

        String choice = sc.nextLine();

        if (choice.equals("0"))
        {
            return;
        }
        else if(!choice.equals("1") && !choice.equals("2") && !choice.equals("3") && !choice.equals("4") && !choice.equals("이름") && !choice.equals("전화번호") && !choice.equals("이메일") && !choice.equals("주소"))
        {
            System.out.println("올바른 값을 입력해주세요");
        }

        else
        {
            System.out.print("입력: ");
            String searchValue = sc.nextLine();

            List<ContactDTO> contacts = searchDAO.search(getConnection(), choice, searchValue, userCode);
            if(contacts.isEmpty())
            {
                System.out.println("일치하는 검색 결과가 없습니다.");
            }
            else
            {
                for(ContactDTO contact : contacts)
                {
                    System.out.println(contact);
                }
            }
        }
    }


    private void printContactInfo(String name, String phoneNum, String email, String address, String birthday)
    {
        System.out.println("이름: " + name + "\n전화번호: " + phoneNum + "\n이메일: " + email + "\n주소: " + address + "\n생일: " + birthday);
    }
}
