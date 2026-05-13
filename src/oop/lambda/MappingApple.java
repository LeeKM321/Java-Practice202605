package oop.lambda;

import java.util.ArrayList;
import java.util.List;

public class MappingApple {

    // 사과의 색상만 추출
    public static List<Color> mappingAppleByColor(List<Apple> apples) {
        List<Color> colorList = new ArrayList<>();
        for (Apple apple : apples) {
            colorList.add(apple.getColor());
        }
        return colorList;
    }

    // 어떤 타입의 리스트를 받아도 맵핑이 가능한 전역 메서드를 선언해 보자! (X -> Y)
    public static <X, Y> List<Y> map(List<X> list, GenericFunction<X, Y> mapper) {
        List<Y> mappedList = new ArrayList<>();
        for (X x : list) {
            Y y = mapper.apply(x);
            mappedList.add(y);
        }

        return mappedList;
    }

}












