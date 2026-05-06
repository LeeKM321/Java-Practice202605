package loop;

public class WhileExample {

    public static void main(String[] args) {

        // 반복문의 필수 3요소 (begin - end - step)
        // 안녕하세요! n번 고객님!

        int i = 1; // begin
        while (i <= 10) { // end
            System.out.println("안녕하세요! " + i + "번 고객님!");
//            i += 1; // step
            i++; // 증감 연산자 (단순 변수의 값을 하나 올리거나 내릴때 사용)
        }

        // for (begin; end; step)
        for (int j=1; j<=10; j++) {
            System.out.println("안녕하세요! " + j + "번 고객님!");
        }

        System.out.println("===================================================");

        // 1 ~ 100까지의 누적합계 구하기
        int k = 1;
        int total = 0; // 누적합을 담아줄 변수
        while (k <= 100) {
            total += k;
            k++;
        }
        System.out.println("1 ~ 100까지의 누적합: " + total);

        int result = 0;
        for (int m=1; m<=100; m++) {
            result += m;
        }
        System.out.println("result = " + result);

        System.out.println("==============================================");

        // 특정 값이 소수인지 소수가 아닌지 판별하는 문제
        int num = 413;
        int n = 1;
        int count = 0; // 나누어 떨어진 횟수를 세 줄 변수
        while (n <= num) {
            if (num % n == 0) {
                count++;
            }
            n++;
        }

        if (count == 2) {
            System.out.println(num + "은(는) 소수입니다.");
        } else {
            System.out.println(num + "은(는) 소수가 아닙니다.");
        }

        System.out.println("==============================================");

        int s = 2;
        while (num % s != 0) {
            s++;
        }

        if (num == s) {
            System.out.println(num + "은 소수입니다.");
        } else {
            System.out.println(num + "은 소수가 아닙니다.");
        }





    }

}
