package com.ohgiraffers;

public enum Prefer {


    EMAIL_ASC("email ASC","이메일 오름차순"),

    EMAIL_DESC("email DESC","이메일 내림차순"),

    NAME_ASC("contact_name ASC","이름 오름차순"),

    NAME_DESC("contact_name DESC","이름 내림차순"),

    BIRTHDAY_ASC("birthday ASC","생일 오름차순"),

    BIRTHDAY_DESC("birthday DESC","생일 내림차순"),

    ADDRESS_ASC("address ASC","주소 오름차순"),

    ADDRESS_DESC("address DESC","주소 내림차순");









    private final String description;
    private final String count;



    public static  String description(String prefer) {
        String des = null;
        for(Prefer p : Prefer.values()) {
            if(p.getCount().equals(prefer) || p.getDescription().equals(prefer)){
                des = p.getDescription();


            }

        }
        return des;
    }

    Prefer(String description, String count) {     // 자동으로 상수의 속성(소괄호안의값)이 description으로 들어온다!
        this.description = description;
        this.count = count;// 즉, 인스턴스를 만들면 상수의 속성이 따라온다!
    }

    public String getDescription() {
        return description;
    }
    public String getCount() {
        return count;
    }

}
