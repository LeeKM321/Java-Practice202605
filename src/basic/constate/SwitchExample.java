package basic.constate;

public class SwitchExample {

    public static void main(String[] args) {

        String gender = "l";

        // if문처럼 논리형 조건식이 들어가는 게 아니라
        // 분기를 나눌 기준값이 괄호 안에 들어갑니다. (문자열, 정수만 가능)
        switch (gender) {
            case "m": case "M": case "남": case "ㅡ":
                System.out.println("남성입니다.");
                break; // 해당 케이스만 실행하고 switch문을 종료해라!

            // java 14버전 이후 개선된 case문
            case "F", "f", "여", "ㄹ":
                System.out.println("여성입니다.");
                break;

            default: // case를 설정하지 않은 값들은 모두 default로 빠짐.
                System.out.println("잘못된 성별입니다.");
        }

    }

}
