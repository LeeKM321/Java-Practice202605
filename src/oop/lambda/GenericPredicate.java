package oop.lambda;

@FunctionalInterface
public interface GenericPredicate<T> {

    boolean test(T t);

}
