package com.ohgiraffers.function;

import com.ohgiraffers.dto.ContactDTO;
import com.ohgiraffers.dto.GroupDTO;
import com.ohgiraffers.dao.ContactDAO_YSJ;
import com.ohgiraffers.dto.ContactDTO_YSJ;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class GroupUpdateManager
{
    ContactDAO_YSJ contactDAO = new ContactDAO_YSJ("src/main/resources/mapper/contact-query.xml");
    List<GroupDTO> groups = null;
    Scanner scr = new Scanner(System.in);

    private int groupNum = 0;

    public void deleteGroup(int userCode){

        Scanner scr = new Scanner(System.in);
        ContactDTO_YSJ contactDTO = new ContactDTO_YSJ();

        List<GroupDTO> groupsInUser = contactDAO.getAllGroups(getConnection(), userCode);
        System.out.println(groupsInUser);
        if(groupsInUser.isEmpty())
        {
            System.out.println("저장된 그룹이 없습니다.");
        }
        else
        {
            System.out.println("* ੈ✩‧₊ 삭제하고 싶은 그룹의 이름을 입력해주세요 : * ੈ✩‧₊");

            System.out.println("삭제하고 싶은 그룹의 이름을 입력해주세요 : ");
            String groupName = scr.nextLine();
            contactDTO.groupname(groupName);

            // userCode 가지고 있는 그룹 가져와서
            // groupName이랑 비교 => 다르면 초기화 할 필요가 없고 해당 그룹을 찾을 수 없다고 표시

            boolean isInUserContact = false;
            for(GroupDTO group : groupsInUser)
            {
                isInUserContact = group.getGroupName().equals(groupName);
                if(isInUserContact) break;
            }

            if(isInUserContact)
            {
                int initResult = contactDAO.updateForDeleteGroup(getConnection(), contactDTO, userCode);

                if (initResult > 0){
                    System.out.println("* ੈ✩‧₊ 그룹 삭제를 위한 초기화 성공 * ੈ✩‧₊");
                    System.out.println();

                    int groupResult = contactDAO.deleteGroup(getConnection(), groupName, userCode);
                    System.out.println(groupResult > 0 ? "[ " + groupName + " ] 그룹 삭제에 성공했습니다." : "[ " + groupName + " ] 그룹 삭제에 실패했습니다.");
                    System.out.println();
                }
                else
                {
                    System.out.println("* ੈ✩‧₊ 초기화 실패하셨습니다. 다시 시도해주세요. * ੈ✩‧₊");
                }
            }
            else
            {
                System.out.println("해당 그룹을 찾을 수 없습니다.");
            }

        }
    }


    public void updateGroup(int userCode)
    {
        boolean isTrue = false;

        // 해당 유저코드가 가지고 있는 그룹명 출력
        groups = contactDAO.getAllGroups(getConnection(), userCode);
        System.out.println("*** 저장된 그룹 ***");
        for (GroupDTO groupDTO : groups)
        {
            System.out.println("- " + groupDTO.getGroupName());
        }

        System.out.println("편집하고 싶은 그룹을 입력해주세요:  ");
        String selectedGroup = scr.nextLine();
        for (GroupDTO groupDTO : groups)
        {
            if(groupDTO.getGroupName().equals(selectedGroup))
            {
                groupNum = groupDTO.getGroupIndex();
                isTrue = true;
                break;
            }
        }

        if(isTrue)
        {
            while(true)
            {
                System.out.println("***** 서비스를 선택하세요 *****");
                System.out.println("- 추가\n- 삭제\n- 해당 메뉴 나가기(0번)");
                String choice = scr.nextLine();

                if(choice.equals("추가"))
                {
                    addContactInGroup(userCode);
                    break;
                }
                else if(choice.equals("삭제"))
                {
                    deleteContactsInGroup(groupNum, userCode);
                    break;
                }
                else if(choice.equals("0"))
                {
                    return;
                }
                else
                {
                    System.out.println("잘못된 입력입니다.");
                }

            }
        }
        else
        {
            System.out.println("해당 그룹을 찾을 수 없습니다.");
        }

    }

    private void addContactInGroup(int userCode)
    {
        //그룹에 연락처 추가
        // 그룹 출력해줬으니까 연락처 출력해서 연락처를 추가하고 싶은

        //TODO
        List<ContactDTO> contacts = contactDAO.getAllContacts(getConnection(), userCode); // 수정해라
        List<String> phoneNumList = new ArrayList<>(); // 내가 입력 받을 휴대폰 번호
        List<String> notInContacts = new ArrayList<>();

        boolean isInContacts = false;

        int result = 0;

        for (ContactDTO contactDTO : contacts)
        {
            System.out.println(contactDTO.getContactName() + " " + contactDTO.getPhoneNumber() + " " + contactDTO.getGroupName());
        }

        while(true)
        {
            System.out.println("그룹에 추가할 휴대폰 번호를 입력해주세요: ");
            String phoneNum = scr.nextLine();

            System.out.println("더 추가하시겠습니까? (네/아니오)");
            String answer = scr.nextLine();

            if(answer.equals("yes") || answer.equals("네") || answer.equals("예"))
            {
                phoneNumList.add(phoneNum);
                continue;
            }
            else if(answer.equals("no") || answer.equals("아니요") || answer.equals("아니") || answer.equals("아니오"))
            {
                phoneNumList.add(phoneNum);
                break;
            }
            else
            {
                System.out.println("잘못된 입력입니다. [ " + phoneNum + " 그룹 추가 취소 ] ");
                break;
            }
        }

        //contacts 조회해서 phoneNumList에 내 연락처에 없는 전화번호가 있을 경우에
        //"연락처 내에서 번호 " + phoneNumList.get(j) + " 를 찾을 수 없어 그룹에 추가하지 못했습니다."

        for(int i = 0 ; i < phoneNumList.size() ; i++)
        {
            isInContacts = false;

            for(int j = 0 ; j < contacts.size() ; j++)
            {
                if(contacts.get(j).getPhoneNumber().equals(phoneNumList.get(i)))
                {
                    // contacts[i]의 groupnumber를 내가 선택한 그룹 이름의 넘버로 바꿔줄거예요
                    isInContacts = true;
                    result = contactDAO.changeGroupNumberOfContact(getConnection(), groupNum, userCode, phoneNumList.get(i));
                }
            }

            if(!isInContacts)
            {
                notInContacts.add(phoneNumList.get(i));
            }

        }

        if(!notInContacts.isEmpty())
        {
            for(String phoneNum : notInContacts)
            {
                System.out.println("연락처 내에서 번호 " + phoneNum + " 를 찾을 수 없어 그룹에 추가하지 못했습니다.");
            }
        }

        System.out.println(result > 0 ? "* ੈ✩‧₊ 그룹 설정에 성공했습니다. * ੈ✩‧₊" : "");
    }

    private void deleteContactsInGroup(int groupNum, int userCode)
    {
        // 그룹 내에서 연락처 삭제하는 메소드
        // 그룹넘버와 유저코드 일치하는 연락처 전체 조회 후 출력

        List<ContactDTO> contacts = contactDAO.printContactsInGroup(getConnection(), groupNum, userCode);

        // 삭제할 연락처 선택 => 삭제
        // 해당 그룹 넘버에 저장된 유저 없을 경우 없다고 출력

        if(!contacts.isEmpty())
        {
            for(ContactDTO contactDTO : contacts)
            {
                System.out.println(contactDTO.getContactName() + " " + contactDTO.getPhoneNumber());
            }
            System.out.println("삭제하실 휴대폰 번호를 입력해주세요.");
            String phoneNum = scr.nextLine();

            int result = contactDAO.deleteContactInGroup(getConnection(), groupNum, phoneNum, userCode);

            System.out.println(result < 1 ? phoneNum + "의 그룹 갱신에 실패했습니다." : "그룹에서 " + phoneNum + " 을 삭제했습니다.");
        }
        else
        {
            System.out.println("해당 그룹에 저장된 연락처가 없습니다.");
        }
    }
}
