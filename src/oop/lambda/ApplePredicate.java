package oop.lambda;

// 함수형 인터페이스는 추상메서드가 오직 하나여야 합니다.(그래야 람다식 사용 가능)
@FunctionalInterface
public interface ApplePredicate {

    // 사과를 전달받으면 조건에 맞는 검사를 수행하고, 결과를 리턴하는 메서드
    boolean test(Apple apple);

}
