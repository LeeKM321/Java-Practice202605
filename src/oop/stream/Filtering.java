package oop.stream;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static oop.stream.Menu.*;

public class Filtering {

    public static void main(String[] args) {

        // 요리 메뉴 중 채식주의자가 먹을 수 있는 요리만 필터링
        /*
        Stream<Dish> stream = MENU_LIST.stream();
        Stream<Dish> dishStream = stream.filter(new Predicate<Dish>() {
            @Override
            public boolean test(Dish dish) {
                return dish.isVegetarian();
            }
        });
        List<Dish> dishList = dishStream.toList();
        for (Dish dish : dishList) {
            System.out.println(dish);
        }
        */
        List<Dish> dishList = MENU_LIST.stream()
                .filter(Dish::isVegetarian)
                .toList();

        dishList.forEach(dish -> System.out.println(dish));

        System.out.println("=============================================================");

        // 메뉴 목록중에 육류이면서 600칼로리 미만인 요리를 필터링 해서 출력
        // 메서드 체이닝 (method chaining): 리턴된 결과물을 변수에 할당 없이 바로 호출하는 문법
        MENU_LIST.stream()
                .filter(dish -> dish.getType() == Dish.Type.MEAT
                        && dish.getCalories() < 600)
                .toList()
                .forEach(System.out::println);

        System.out.println("=============================================================");

        // 메뉴 목록에서 요리 이름이 4글자인 것만 필터링 해서 출력해줘.
        MENU_LIST.stream()
                .filter(dish -> dish.getName().length() == 4)
                .toList()
                .forEach(System.out::println);

        System.out.println("=============================================================");

        // 칼로리가 300칼로리보다 큰 요리 중에 앞에 3개만 가져와
        MENU_LIST.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3) // 앞에서부터 3개만 추출
                .toList()
                .forEach(System.out::println);

        System.out.println("=============================================================");

        // 칼로리가 300칼로리보다 큰 요리 중에 처음 2개 제끼고 가져와
        MENU_LIST.stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(2) // 맨 앞 2개 제외
                .toList()
                .forEach(System.out::println);

        System.out.println("=============================================================");

        List<Integer> numbers = List.of(1, 2, 1, 3, 3, 2, 4, 4, 4, 5, 6);

        // 리스트에서 짝수만 필터링 (중복은 제거해서)
        numbers.stream()
                .filter(n -> n % 2 == 0)
                .distinct()
                .toList()
                .forEach(System.out::println);

    }

}














