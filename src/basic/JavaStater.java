package basic;

public class JavaStater {

    public static void main(String[] args) {
        // 주석(comments)은 프로그램의 실행에 영향을 주지 않습니다.
        // 인텔리제이 출력 함수 생성: sout
        System.out.println("Hello Java!");

        /*
        여기는 문장 주석 공간입니다.
        오호호호~~
         */

        String title = "Java 변수와 자료형";
        int studyMinutes = 90;
        int targetMinutes = 120;
        int extraMinutes = 60;

        studyMinutes = 100;

        boolean isPublic = true;
        char level = 'A';

        // 자바에서는 int와 int의 연산 결과는 항상 int 입니다.
        // 그래서 소수점을 표현하고 싶다면 연산하고자 하는 값의 타입을 실수형(double)로 변경해야 합니다.
        double progressRate = (double) studyMinutes / targetMinutes;
        boolean isEnoughStudy = studyMinutes >= 60;

        // 문자열의 덧셈 연산은 뒤에 어떤 타입이 들어와도 가능합니다.
        // 연산의 결과는 항상 문자열입니다.
        System.out.println("제목: " + title);
        System.out.println("학습 시간: " + studyMinutes + "분");
        System.out.println("목표 시간: " + targetMinutes + "분");
        System.out.println("추가 시간: " + extraMinutes);
        System.out.println("중요도: " + level);
        System.out.println("진도율: " + progressRate + "%");
        System.out.println("충분히 학습했나요?: " + isEnoughStudy);

    }

}



// 여기다 달아도 됨!