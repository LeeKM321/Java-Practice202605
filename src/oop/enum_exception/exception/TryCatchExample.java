package oop.enum_exception.exception;

public class TryCatchExample {

    public static void main(String[] args) {

        try {
            // try 블록에는 예외 발생 가능성이 있는 코드를 배치합니다.
            int i = 20, j = 0;

            System.out.println(i  + " / " + j + " = " + (i/j));
        } catch (Exception e) {
            // catch 블록에는 try에서 실제 예외가 발생했을 경우
            // 실행할 내용을 작성합니다.
            System.out.println("0으로 나누시면 안돼요!");
        }

        System.out.println("프로그램 정상 종료!");

    }

}
