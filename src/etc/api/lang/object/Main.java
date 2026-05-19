package etc.api.lang.object;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Person kim = new Person("김춘식", 30);
        Person kim2 = new Person("김춘식", 30);

        System.out.println(kim.equals(kim2));
        System.out.println(kim.hashCode());
        System.out.println(kim2.hashCode());

        // equals에서 동등한 객체라고 판명이 되었다면, 서로 다른 객체더라도
        // 객체의 고유한 주소를 동일하게 처리해서 서로 같은 객체임을 명확히 해야 합니다.
        // 그래야, 자바에서 기본으로 제공하는 클래스 중에 hash를 비교해서 동등 객체인지 아닌지를
        // 판별하는 자료구조(HashSet, HashMap)들이 정상적으로 동작할 수 있습니다.
        Set<Person> set = new HashSet<>();
        
        set.add(kim);
        set.add(kim2);
        System.out.println("set = " + set);

        try {
            Person clonePerson = (Person) kim2.clone();
            System.out.println("clonePerson = " + clonePerson);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }


    }

}
