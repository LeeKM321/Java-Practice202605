package oop.lambda;

import java.util.ArrayList;
import java.util.List;

// 사과를 여러가지 방법으로 필터링하는 역할을 담당
public class FilterApple {

    /**
     * @solution - try1: 사과바구니에서 녹색 사과만 필터링하자
     * @param1 - 전체 사과가 들어있는 바구니
     * @return - 녹색 사과만 필터링 된 바구니
     *
     * @problem - 만약에 다른 색깔의 사과를 필터링 해야 한다면??
     *          새로운 메서드를 계속 선언해야 함...
     */
    public static List<Apple> filterGreenApple(List<Apple> basket) {
        List<Apple> greenBasket = new ArrayList<>();

        for (Apple apple : basket) {
            if (apple.getColor() == Color.GREEN) {
                greenBasket.add(apple);
            }
        }

        return greenBasket;
    }

    /**
     * @ solution - try2: 색상을 파라미터화 하자!
     * @ problem - 만약 필터 기준이 색상이 아니라 무게라면?
     */
    public static List<Apple> filterAppleByColor(List<Apple> basket, Color color) {
        List<Apple> filteredBasket = new ArrayList<>();

        for (Apple apple : basket) {
            if (apple.getColor() == color) {
                filteredBasket.add(apple);
            }
        }

        return filteredBasket;
    }

    /**
     * @return
     * @solution - try3: 동작을 파라미터화 하면 해결된다!
     * - 함수를 전달 받을 수 있다면?
     * - 자바는 함수를 전달할 수 있는가?
     * -> 불가능하다. 자바의 함수는 일급 객체가 아닙니다.
     * -> 함수 개념이 아니라 객체에 소속된 메서드 개념.
     * - 그런데 전달되는 함수가 고정되어 있는가?
     * -> 필터 조건에 따라 함수 내용이 달라야 한다.
     * -> 그러면 어떻게 함수 이름이 통일 되면서
     * 다른 내용을 전달할 수 있을까?
     * -> 오버라이딩 (상속, 인터페이스)
     */
    public static List<Apple> filterApple(List<Apple> basket, ApplePredicate predicate) {
        List<Apple> filteredBasket = new ArrayList<>();

        for (Apple apple : basket) {
            if (predicate.test(apple)) {
                filteredBasket.add(apple);
            }
        }

        return filteredBasket;
    }








}










