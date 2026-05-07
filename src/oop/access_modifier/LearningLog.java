package oop.access_modifier;

public class LearningLog {

    // 접근 제한자를 활용한 정보 은닉과 캡슐화 (information hiding, encapsulation)
    // 1. 필드에 private 접근 제한을 붙여 외부에서의 직접적인 접근을 허용하지 않게 막습니다.
    private String title;
    private int minutes;
    private boolean publicLog;

    LearningLog() {
        System.out.println("기본 생성자 호출!");
    }

    LearningLog(String title, int minutes) {
        this(title, minutes, true);
    }

    LearningLog(String title, int minutes, boolean publicLog) {
        this.title = title;
        this.minutes = minutes;
        this.publicLog = publicLog;
    }

    // private 접근 제한을 지정하니까, 제대로 된 값도 수정이 불가능한 것을 확인했습니다.
    // 필드 값을 대신 받아서 할당하고, 값을 돌려줄 수 있는 메서드를 활용해서 값을 보호하겠습니다.
    // 이 때 사용하는 메서드의 이름을 getter, setter라고 합니다.

    public void setMinutes(int minutes) {
        if (minutes <= 0) {
            System.out.println("잘못된 공부 시간입니다.");
            return; // void 메서드에서 return은 메서드를 강제 종료합니다.
        }

        this.minutes += minutes;
    }


    void printSummary() {
        System.out.println(this.title + " - " + this.minutes + "분");
    }

    boolean needsReview() {
        return minutes < 60;
    }

}
