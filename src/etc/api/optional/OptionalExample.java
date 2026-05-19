package etc.api.optional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OptionalExample {

    public static void main(String[] args) {

        Map<String, String> phoneByName = new HashMap<>();
        phoneByName.put("kim", "010-1234-5678");

        String phone = phoneByName.get("lee");   // 없음
//        System.out.println(phone.length());      // NullPointerException!


        String str = null;
        Optional<String> name = Optional.ofNullable(str);
        name.ifPresent(n -> System.out.println("환영합니다, " + n.length()));
        System.out.println(name.isPresent());

        System.out.println("================================================");

        List<User> userList = List.of(
                new User("kim", "kim1234@naver.com", 24),
                new User("lee", "lee1234@naver.com", 20),
                new User("choi", "choi1234@naver.com", 32),
                new User("park", "park1234@naver.com", 28),
                new User("jung", "jung1234@naver.com", 40)
        );

        int max = userList.stream()
                .filter(user -> user.getAge() >= 200)
                .mapToInt(User::getAge)
                .max()
                .orElse(0);
        System.out.println("max = " + max);


    }

}
