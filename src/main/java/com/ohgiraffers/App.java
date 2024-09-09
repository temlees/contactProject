package com.ohgiraffers;

import com.ohgiraffers.controller.ContactController;
import com.ohgiraffers.controller.ContactController_lee;
import com.ohgiraffers.dto.UserDTO;
import com.ohgiraffers.controller.Controller;
import com.ohgiraffers.controller.ContactController_YSJ;

import java.util.Scanner;

public class App
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        ContactController contactController = new ContactController();
        ContactController_lee contactController_lee = new ContactController_lee();
        Controller controller = new Controller();
        ContactController_YSJ contactController_YSJ = new ContactController_YSJ();


        UserDTO userDTO = null;
        int userCode = 0; // 이걸로 로그인 후에 setUserCode 해서 넘겨주기

        while(true)
        {
            System.out.println();
            System.out.println("¯\\_( ͡° ͜ʖ ͡°)_/¯ 연락처 관리 프로그램 ¯\\_( ͡° ͜ʖ ͡°)_/ \n¯");
            System.out.println("1. 기존 아이디로 로그인");
            System.out.println("2. 신규 회원 가입");

            try {
                int choice = Integer.parseInt(sc.nextLine());

            if(choice == 1)
            {
                userDTO = contactController.login();
                if(userDTO != null)
                {
                    userCode = userDTO.getUserCode();
                }

                if(userCode <= 0)
                {
                    System.out.println("아이디 또는 비밀번호가 맞지 않습니다( ༎ຶŎ༎ຶ )");
                    System.out.println("메인 화면으로 돌아갑니다.");
                }
                else break;
            }
            else if(choice == 2)
            {
                userDTO = contactController.signup();
                userCode = userDTO.getUserCode();
                break;
            }
            else
            {
                System.out.println("잘못된 입력입니다. 메인 화면으로 돌아갑니다.");
            }
        }catch (NumberFormatException e ) {
                System.out.println("문자나 특수기호 말고  숫자로 입력하시오");
            }
        }

        while(true) {
            System.out.println();
            System.out.println("* ੈ✩‧₊ 사용하실 서비스 번호를 입력해주세요 * ੈ✩‧₊");
            System.out.println("1. 연락처 관리");
            System.out.println("2. 그룹 관리");
            System.out.println("3. 연락처 검색");
            System.out.println("4. 목록 조회");
            System.out.println("5. 통계");
            System.out.println("6. 사용자 설정");
            System.out.println("9. 프로그램 종료");
            System.out.println("｡.ﾟ+:✿｡.ﾟ+:✿｡.ﾟ+:✿｡.ﾟ+:✿｡.ﾟ+:✿｡.ﾟ");
            try {
                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        contactController_YSJ.manageContact(userCode);
                        break;
                    case 2:
                        contactController_YSJ.manageGroup(userCode);
                        break;
                    case 3:
                        contactController.searchContact(userCode);
                        break;
                    case 4:
                        controller.findNumber(userCode);
                        break;
                    case 5:
                        contactController_lee.groupChoose(userCode);
                        break;
                    case 6:
                        contactController.saveUserPrefer(userDTO);
                        break;
                    case 9:
                        System.out.println("프로그램을 종료합니다.");
                        return;
                    default:
                        System.out.println("잘못된 값을 입력하셨습니다. 서비스 선택 창으로 돌아갑니다.");
                        break;
                }
            } catch (NumberFormatException d) {
                System.out.println("문자나 특수기호 말고  숫자로 입력하시오");
            }
        }
    }
}
