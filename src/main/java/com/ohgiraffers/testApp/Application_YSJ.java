package com.ohgiraffers.testApp;

import com.ohgiraffers.controller.ContactController_YSJ;

import java.util.Scanner;

public class Application_YSJ {
    public static void main(String[] args) {

        Scanner scr = new Scanner(System.in);
        ContactController_YSJ contactController = new ContactController_YSJ();

           while(true){
            System.out.println("사용할 기능을 선택해주세요!");
            System.out.println("1. 연락처 추가");
            System.out.println("2. 연락처 수정");
            System.out.println("3. 연락처 제거");
            System.out.println("4. 그룹 추가");
            System.out.println("5. 그룹 제거");
            System.out.println("9. 프로그램 종료");
            int choice = scr.nextInt();

            switch(choice){

                case 1 : contactController.insertcontact(1); break;
                case 2 : contactController.updatecontact(1); break;
                case 3 : contactController.deletecontact(1); break;
                case 4 : contactController.insertGroup(1); break;
                case 5 : break;
                case 9 : return;
                default:
                    System.out.println("입력을 잘못하셨습니다. 다시 실행해주세요");
                    break;
            }

        }


    }


}
