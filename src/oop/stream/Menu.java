package oop.stream;

import java.util.List;

public class Menu {

    public static final List<Dish> MENU_LIST;

    // 정적 초기화자 (static initializer)
    // static 필드를 초기화 하는 용도로 사용합니다. (객체에 생성자가 있는 것처럼)
    static {
        MENU_LIST = List.of(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 400, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );
    }
}
