package oop.enum_exception.exception;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TryCatchExample2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[] arr = new int[5];

        try {
            System.out.print("정수: ");
            int n = sc.nextInt();

            int result = 100 / n;

            System.out.println(arr[result]);

            String s = null;
            s.equals("메롱");

        } catch (InputMismatchException | ArithmeticException e) {
            System.out.println("입력값이 잘못되었습니다!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("인덱스 범위를 벗어났어요!");
        } catch (Exception e) {
            System.out.println("예상치 못한 예외 발생!");
            e.printStackTrace();
        } finally {
            System.out.println("예외 발생 여부와 관계없이 항상 실행되는 공간!");
            sc.close(); // Scanner 객체를 메모리에서 해제
        }


    }

}
