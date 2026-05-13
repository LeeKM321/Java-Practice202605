package oop.stream;

import java.util.Comparator;

import static oop.stream.Menu.*;

public class Finding {

    public static void main(String[] args) {

        // 메뉴 목록에서 500칼로리 이상 요리 중 채식주의자가 먹을 수 있는 요리가 하나라도 존재 하는가?
        boolean flag1 = MENU_LIST.stream()
                .filter(dish -> dish.getCalories() >= 500)
                .anyMatch(dish -> dish.isVegetarian());
        System.out.println("flag1 = " + flag1);


        // 메뉴 목록 중 칼로리가 100 이하인 요리가 하나라도 존재 하는가?
        boolean flag2 = MENU_LIST.stream()
                .anyMatch(dish -> dish.getCalories() <= 100); // 하나라도 조건에 일치하는가?
        System.out.println("flag2 = " + flag2);

        // 음식 메뉴 중에 모든 요리가 1000칼로리 미만 인가요?
        boolean flag3 = MENU_LIST.stream()
                .allMatch(d -> d.getCalories() < 1000); // 모두 일치 하는가?
        System.out.println("flag3 = " + flag3);


        // 음식 메뉴 중에 모든 요리가 1000칼로리 미만이 아닌가요?
        boolean flag4 = MENU_LIST.stream()
                .noneMatch(d -> d.getCalories() > 1000); // 전부 불일치 하는가?
        System.out.println("flag4 = " + flag4);


        // 음식 중에 칼로리가 제일 낮은 애가 누구에요?
        Dish dish = MENU_LIST.stream()
                .min(Comparator.comparing(d -> d.getCalories()))
                .get();
        System.out.println("dish = " + dish);


    }

}










