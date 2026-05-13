package oop.stream;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static oop.stream.Menu.*;

public class Grouping {

    public static void main(String[] args) {

        // 채식요리와 아닌 요리로 메뉴를 나눠보자
        // 두 분류로 나누어 줌. (분류 기준을 논리값으로 전달해 주어야 합니다.)
        Map<Boolean, List<Dish>> veggieMenu = MENU_LIST.stream()
                .collect(Collectors.partitioningBy(Dish::isVegetarian));

        System.out.println("--- 채식 메뉴 ---");
        veggieMenu.get(true).forEach(System.out::println);

        System.out.println("--- 일반 메뉴 ---");
        veggieMenu.get(false).forEach(System.out::println);

        System.out.println("=============================================================");

        // 요리를 종류(Type)별로 그룹화 해 줘!
        Map<Dish.Type, List<Dish>> menuByType = MENU_LIST.stream()
                .collect(Collectors.groupingBy(Dish::getType));

        System.out.println("--- 기타 메뉴 ---");
        menuByType.get(Dish.Type.OTHER).forEach(System.out::println);

        System.out.println("=============================================================");

        // 종류별로 나눈 메뉴를 칼로리 기준(500 넘으면 고칼로리, 나머지 저칼로리)으로 한번 더 그룹화 해 줘.
        Map<Dish.Type, Map<String, List<Dish>>> detailedMenu
                = MENU_LIST.stream()
                .collect(Collectors.groupingBy(Dish::getType, // 1차 분류: Type
                        Collectors.groupingBy(dish -> {
                            if (dish.getCalories() > 500) return "고칼로리";
                            else return "저칼로리";
                        }) // 2차 분류: 칼로리
                ));

        detailedMenu.get(Dish.Type.OTHER).get("저칼로리").forEach(System.out::println);

        System.out.println("=============================================================");

        // 종류 별 요리 개수
        Map<Dish.Type, Long> dishCount = MENU_LIST.stream()
                .collect(Collectors.groupingBy(Dish::getType, Collectors.counting()));

        System.out.println("dishCount = " + dishCount);

        // 종류별 요리 총 칼로리
        Map<Dish.Type, Integer> totalCalByType = MENU_LIST.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.summingInt(Dish::getCalories)));

        System.out.println("totalCalByType = " + totalCalByType);

        System.out.println("=============================================================");

        Map<Dish.Type, IntSummaryStatistics> calorieStats = MENU_LIST.stream()
                .collect(Collectors.groupingBy(Dish::getType,
                        Collectors.summarizingInt(Dish::getCalories)));

        // 요리 종류별 그룹화 후 칼로리에 대한 통계 꾸러미가 리턴됨 (개수, 평균, 합계, 최대/최소)
        System.out.println(calorieStats);
    }

}












