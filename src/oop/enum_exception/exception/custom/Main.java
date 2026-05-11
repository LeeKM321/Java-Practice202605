package oop.enum_exception.exception.custom;

public class Main {

    public static void main(String[] args) {

        LoginUser user = new LoginUser("abc1234", "aaa1111!");

        String result = null;
        try {
            result = user.authenticate("abc1234", "aaa111!");
        } catch (LoginInvalidException e) {
            System.out.println(e.getMessage());
            System.out.println("입력하신 값: " + e.getInputAccount());
        }

        System.out.println("result = " + result);

    }

}
