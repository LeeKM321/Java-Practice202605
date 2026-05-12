package oop.lambda;

import java.util.List;

import static oop.lambda.Color.*;

public class Main {

    public static void main(String[] args) {

        // 사과 바구니 생성
        // List.of -> 고정형 리스트 생성 (Arrays.asList와 유사)
        List<Apple> appleBasket = List.of(
                new Apple(80, GREEN)
                , new Apple(155, GREEN)
                , new Apple(120, RED)
                , new Apple(97, RED)
                , new Apple(200, GREEN)
                , new Apple(50, RED)
                , new Apple(85, YELLOW)
                , new Apple(75, YELLOW)
        );

        System.out.println("========== 녹색 사과만 필터링 ==========");


    }

}
