package etc.api.lang.math;

public class MathExample {

    public static void main(String[] args) {

        System.out.println(Math.max(3, 7));     // 7
        System.out.println(Math.min(3, 7));     // 3
        System.out.println(Math.abs(-5));        // 5
        System.out.println(Math.pow(2, 10));     // 1024.0  ← double!
        System.out.println(Math.sqrt(144));      // 12.0
        System.out.println(Math.round(3.5));     // 4
        System.out.println(Math.floor(3.7));     // 3.0
        System.out.println(Math.ceil(3.2));      // 4.0


        long result = Math.round(3.14159265 * 100);
        System.out.println(result * 0.01);

        System.out.println("=================================================");

        double randomResult = Math.random();
        System.out.println("randomResult = " + randomResult);

        // 1 ~ 45까지의 정수 난수
        int result2 = (int) (Math.random()*45 + 1);
        System.out.println("result2 = " + result2);

        // 47 ~ 223까지의 정수 난수
        int result3 = (int) (Math.random()*177 + 47);
        System.out.println("result3 = " + result3);

    }

}
