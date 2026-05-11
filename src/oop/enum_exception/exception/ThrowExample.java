package oop.enum_exception.exception;

public class ThrowExample {

    static int calcTotal(int end) throws RuntimeException {
        if (end <= 0) {
            throw new RuntimeException("매개값은 양수로 주세요!");
        }

        int total = 0;
        for (int i = 1; i <= end; i++) {
            total += i;
        }
        return total;
    }

    public static void main(String[] args) {

        try {
            int result = calcTotal(100);
            System.out.println("result = " + result);

            int result2 = calcTotal(-120);
            System.out.println("result2 = " + result2);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("프로그램 정상 종료!");

    }

}
