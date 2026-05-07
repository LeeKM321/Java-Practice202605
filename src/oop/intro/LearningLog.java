package oop.intro;

// 객체 생성을 위한 설계도 클래스에는 main 메서드를 작성하지 않습니다.
public class LearningLog {

    // 객체의 속성을 필드(field)라고 합니다.
    String title;
    int minutes;
    boolean publicLog;

    void printSummary() {
        System.out.println(title + " - " + minutes + "분");
    }

    boolean needsReview() {
        return minutes < 60;
    }

}
